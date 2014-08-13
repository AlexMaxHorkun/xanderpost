package xanderpost.web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xanderpost.entity.Post;
import xanderpost.entity.User;
import xanderpost.service.PostService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.InvalidParameterException;
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
    public ModelAndView postsListAction() {
        List<Post> posts = (ArrayList<Post>) getPostService().findAll();
        ModelAndView response = new ModelAndView();
        response.addObject("posts", posts);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    @Secured("ROLE_USER")
    public ModelAndView postAddAction(@Valid Post post, @AuthenticationPrincipal User user, HttpServletResponse httpServletResponse) {
        ModelAndView response = new ModelAndView();
        post.setAuthor(user);
        try {
            postService.persist(post);
        } catch (DataIntegrityViolationException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.addObject("post", post);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public ModelAndView postDeleteAction(@PathVariable long id) {
        ModelAndView response = new ModelAndView();
        Post post = postService.find(id);
        if (post != null) {
            postService.delete(post);
        } else {
            throw new InvalidParameterException("Cannot find post with id = " + id);
        }
        response.addObject("post", post);
        return response;
    }

    @RequestMapping(value = "/{post}", method = RequestMethod.PATCH, produces = {"application/json", "application/xml"})
    public ModelAndView postEditAction(@ModelAttribute Post post, BindingResult binding, ModelAndView response) {
        if (post.getId() > 0 && !binding.hasErrors()) {
            getPostService().save(post);
        } else {
            throw new InvalidParameterException("Post with such ID is not found");
        }
        return response;
    }
}
