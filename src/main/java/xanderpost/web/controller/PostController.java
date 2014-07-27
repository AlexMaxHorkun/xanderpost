package xanderpost.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import xanderpost.entity.Post;
import xanderpost.entity.validation.PostValidator;
import xanderpost.repository.PostDaoInterface;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class PostController {
    private PostDaoInterface postDao;

    public PostDaoInterface getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoInterface postDao) {
        this.postDao = postDao;
    }

    @RequestMapping(value =  "/post" , method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public  ModelAndView postsListAction(){
        List<Post> posts=(ArrayList<Post>)postDao.findAll();
        ModelAndView response=new ModelAndView();
        response.addObject("posts", posts);
        return response;
    }

    @RequestMapping(value="/post", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ModelAndView postAddAction( @Valid Post post){
        ModelAndView response=new ModelAndView();
        postDao.persist(post);
        response.addObject("post",post);
        return response;
    }
}
