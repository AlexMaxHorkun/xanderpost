package xanderpost.web.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xanderpost.entity.Post;
import xanderpost.repository.PostDaoInterface;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostDaoInterface postDao;

    public PostDaoInterface getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoInterface postDao) {
        this.postDao = postDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ModelAndView postsListAction() {
        List<Post> posts = (ArrayList<Post>) getPostDao().findAll();
        ModelAndView response = new ModelAndView();
        response.addObject("posts", posts);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ModelAndView postAddAction(@Valid Post post) {
        ModelAndView response = new ModelAndView();
        postDao.persist(post);
        response.addObject("post", post);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public ModelAndView postDeleteAction(@PathVariable long id) {
        ModelAndView response = new ModelAndView();
        Post post = postDao.find(id);
        if (post != null) {
            postDao.delete(post);
        } else {
            throw new InvalidParameterException("Cannot find post with id = " + id);
        }
        response.addObject("post", post);
        return response;
    }

    @RequestMapping(value = "/{post}", method = RequestMethod.PATCH, produces = {"application/json", "application/xml"})
    public ModelAndView postEditAction(@ModelAttribute Post post, BindingResult binding, ModelAndView response) {
        if (post.getId() > 0 && !binding.hasErrors()) {
            getPostDao().save(post);
        } else {
            throw new InvalidParameterException("Post with such ID is not found");
        }
        return response;
    }
}
