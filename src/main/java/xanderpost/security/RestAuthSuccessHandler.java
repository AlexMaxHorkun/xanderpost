package xanderpost.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class RestAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws java.io.IOException, javax.servlet.ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
