package xanderpost.web.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xanderpost.entity.Post;
import xanderpost.entity.PostDaoHbm;
import xanderpost.entity.PostDaoInterface;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private PostDaoInterface postDao;

    public PostDaoInterface getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoInterface postDao) {
        this.postDao = postDao;
    }

    @RequestMapping(value = {"/", "/posts"}, method = RequestMethod.GET)
    public ModelAndView postsListAction(){
        ModelAndView response=new ModelAndView();
        List<Post> posts=(ArrayList<Post>)postDao.findAll();
        response.addObject("posts", posts);
        response.setViewName("postsList");
        return response;
    }
}
