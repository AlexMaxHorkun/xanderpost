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
import javax.xml.bind.annotation.XmlRootElement;
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PostValidator());
    }

    @RequestMapping(value =  "/post" , method = RequestMethod.GET)
    public  ModelAndView postsListAction(@RequestHeader("Accept") String acceptHeader){
        List<Post> posts=(ArrayList<Post>)postDao.findAll();
        ModelAndView response=new ModelAndView();
        response.addObject("posts", posts);
        if(acceptHeader.contains("application/json")){
            response.setViewName("jsonView");
        }
        else{
            response.setViewName("xmlView");
        }
        return response;
    }

    @RequestMapping(value="/post", method = RequestMethod.POST)
    public ModelAndView postAddAction(@RequestHeader("Accept") String acceptHeader, @Valid Post post){
        ModelAndView response=new ModelAndView();
        postDao.persist(post);
        response.addObject("post",post);
        if(acceptHeader.contains("application/json")){
            response.setViewName("jsonView");
        }
        else{
            response.setViewName("xmlView");
        }
        return response;
    }
}
