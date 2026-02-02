package com.slice.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        // 🔍 DEBUG (KEEP FOR NOW)
        authentication.getAuthorities()
            .forEach(a -> System.out.println("LOGIN ROLE = " + a.getAuthority()));

        String contextPath = request.getContextPath(); // safety

        for (GrantedAuthority authority : authentication.getAuthorities()) {

            String role = authority.getAuthority();

            switch (role) {

                case "ROLE_ADMIN":
                    response.sendRedirect(contextPath + "/admin/pizzas");
                    return;

                case "ROLE_STAFF":
                    response.sendRedirect(contextPath + "/staff/dashboard");
                    return;

                case "ROLE_CUSTOMER":
                    response.sendRedirect(contextPath + "/menu");
                    return;
            }
        }

        // ✅ fallback (should never happen)
        response.sendRedirect(contextPath + "/");
    }
}
