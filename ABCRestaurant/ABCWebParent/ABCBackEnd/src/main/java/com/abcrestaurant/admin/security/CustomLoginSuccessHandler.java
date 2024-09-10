package com.abcrestaurant.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectURL = request.getContextPath();

        System.out.println("User Authorities: " + authorities);  // Debugging

        for (GrantedAuthority authority : authorities) {
            System.out.println("Authority: " + authority.toString());
            if (authority.getAuthority().equals("ROLE_Admin")) {
                redirectURL = "/admin/dashboard";
                System.out.println("Redirecting to Admin Dashboard");  // Debugging
                break;
            } else if (authority.getAuthority().equals("ROLE_Salesperson")) {
                redirectURL = "/admin/dashboard";  // or some other endpoint for Salesperson
                System.out.println("Redirecting to Admin Dashboard for Salesperson");  // Debugging
                break;
            } else if (authority.getAuthority().equals("ROLE_Customer")) {
                redirectURL = "/categories";
                System.out.println("Redirecting to Categories");  // Debugging
                break;
            }
        }
        System.out.println("Final Redirect URL: " + redirectURL);  // Debugging
        response.sendRedirect(redirectURL);
    }
}
