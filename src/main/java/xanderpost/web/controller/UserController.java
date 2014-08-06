package xanderpost.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xanderpost.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @Secured("ROLE_USER")
    public ModelAndView currentAction(@AuthenticationPrincipal User user) {
        ModelAndView response = new ModelAndView();
        /*Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();*/
        response.addObject("user", user/*authentication.getPrincipal()*/);
        return response;
    }
}
