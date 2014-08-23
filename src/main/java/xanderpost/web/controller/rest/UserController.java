package xanderpost.web.controller.rest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xanderpost.entity.User;
import xanderpost.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private Validator validator;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @Secured("ROLE_USER")
    public Map<String, Object> currentAction(@AuthenticationPrincipal User user) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("user", userService.findUserInfo(user));
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Model userAddAction(@Valid @ModelAttribute User user, BindingResult binding, Model model, HttpServletResponse response) {
        boolean error = false;
        if (!binding.hasErrors()) {
            try {
                getUserService().persist(user);
            } catch (DataIntegrityViolationException violationEx) {
                error = true;
            }
        } else {
            error = true;
        }

        if (error) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return model;
    }

    @RequestMapping(value = "/{u}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    @Secured("ROLE_ADMIN")
    public Model userDeleteAction(@PathVariable User u, Model response, Boolean hard) {
        if (hard == null) {
            hard = false;
        }
        userService.remove(u, hard);
        response.addAttribute("user", u);
        return response;
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH, produces = {"application/json", "application/xml"})
    @Secured("ROLE_USER")
    public Model userSelfEditAction(String email, String password, @AuthenticationPrincipal User currentUser, Model response) {
        if (email != null) {
            currentUser.setEmail(email);
        }
        if (password != null) {
            currentUser.setPassword(password);
        }
        Set<ConstraintViolation<User>> errors = validator.validate(currentUser);
        if (errors.size() > 0) {
            throw new RuntimeException("Given values are not valid");
        } else {
            userService.save(currentUser, password != null); //encode password and save user
        }
        response.addAttribute("user", currentUser);
        return response;
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Secured("ROLE_ADMIN")
    public Model userEditAction(String email, String password, @PathVariable User user, Model model) {
        if (email != null) {
            user.setEmail(email);
        }
        if (password != null) {
            user.setPassword(password);
        }
        Set<ConstraintViolation<User>> errors = validator.validate(user);
        if (errors.size() > 0) {
            throw new RuntimeException("Given values are not valid");
        } else {
            userService.save(user, password != null); //encode password and save user
        }
        model.addAttribute("user", user);
        return model;
    }
}
