package xanderpost.web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xanderpost.entity.Post;
import xanderpost.entity.User;
import xanderpost.service.PostService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public Model postsListAction(Model model) {
        List<Post> posts = (ArrayList<Post>) getPostService().findAll();
        model.addAttribute("posts", posts);
        return model;
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    @Secured("ROLE_USER")
    public Model postAddAction(@Valid Post post, @AuthenticationPrincipal User user, HttpServletResponse httpServletResponse, Model model) {
        post.setAuthor(user);
        try {
            postService.persist(post);
        } catch (DataIntegrityViolationException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        model.addAttribute("post", post);
        return model;
    }

    @RequestMapping(value = "/{post}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    @Secured("ROLE_USER")
    public Model postDeleteAction(@PathVariable Post post, HttpServletResponse httpServletResponse, @AuthenticationPrincipal User user, Model model) {
        if (post != null && post.getId() > 0 && (user.equals(post.getAuthor()) || user.hasRole("ROLE_ADMIN"))) {
            postService.delete(post);
            model.addAttribute("post", post);
        } else if (post == null || post.getId() == 0) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        return model;
    }

    @RequestMapping(value = "/{post}", method = RequestMethod.PATCH, produces = {"application/json", "application/xml"})
    @Secured("ROLE_USER")
    public Model postEditAction(@ModelAttribute Post post, BindingResult binding, @AuthenticationPrincipal User user, HttpServletResponse httpServletResponse, Model model) {
        if (post.getId() > 0 && !binding.hasErrors() && (user.equals(post.getAuthor()) || user.hasRole("ROLE_ADMIN"))) {
            getPostService().save(post);
        } else {
            if (post == null || post.getId() == 0 || binding.hasErrors()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
            post = null;
        }
        return model;
    }
}
