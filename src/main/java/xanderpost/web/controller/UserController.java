package xanderpost.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xanderpost.entity.User;
import xanderpost.repository.UserDaoInterface;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserDaoInterface userDao;

    public UserDaoInterface getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @Secured("ROLE_USER")
    public Map<String, Object> currentAction(@AuthenticationPrincipal User user) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("user", user);
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    @Secured("ROLE_ADMIN")
    public Model userAdd(@ModelAttribute User user, BindingResult binding, Model model) {
        if(!binding.hasErrors()){
            getUserDao().persist(user);
        }
        return model;
    }
}
