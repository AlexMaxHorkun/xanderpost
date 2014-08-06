package xanderpost.security;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import xanderpost.entity.User;

import javax.servlet.http.HttpServletResponse;

public class RestAuthSuccessHandler implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws java.io.IOException, javax.servlet.ServletException {
        if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            user.eraseCredentials();
        }
    }
}
