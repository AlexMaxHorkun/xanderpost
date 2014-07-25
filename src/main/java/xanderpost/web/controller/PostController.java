package xanderpost.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import xanderpost.entity.Post;
import xanderpost.repository.PostDaoInterface;

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

    @RequestMapping(value =  "/posts" , method = RequestMethod.GET)
    public  ModelAndView postsListAction(@RequestHeader("Accept") List<String> acceptHeader){
        List<Post> posts=(ArrayList<Post>)postDao.findAll();
        ModelAndView response=new ModelAndView();
        response.addObject("posts", posts);
        View view=null;
        if(acceptHeader.contains("application/json")){
            MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
            jsonView.setModelKey("posts");
            view=jsonView;
            response.setViewName("jsonView");
        }
        else{
            MarshallingView xmlView=new MarshallingView();
            xmlView.setModelKey("posts");
            xmlView.setBeanName("post");
            view=xmlView;
        }
        return response;
    }
}
