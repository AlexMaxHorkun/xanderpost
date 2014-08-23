package xanderpost.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xanderpost.service.PostService;

@Controller
public class FrontendController {
    private PostService postService;

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = {"/", "/posts"}, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView homepageAction(ModelAndView viewModel) {
        viewModel.addObject("posts", postService.findAll());
        viewModel.setViewName("homepage");
        return viewModel;
    }
}
