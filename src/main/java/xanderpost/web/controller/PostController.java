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
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private SessionFactory hbm;

    public SessionFactory getHbm() {
        return hbm;
    }

    public void setHbm(SessionFactory hbm) {
        this.hbm = hbm;
    }

    @RequestMapping(value = {"/", "/posts"}, method = RequestMethod.GET)
    public ModelAndView postsListAction(){
        ModelAndView response=new ModelAndView();
        Session ormSession=hbm.openSession();
        List<Post> posts=(ArrayList<Post>)ormSession.createCriteria(Post.class).list();
        response.addObject("posts", posts);
        response.setViewName("postsList");
        ormSession.close();
        return response;
    }
}
