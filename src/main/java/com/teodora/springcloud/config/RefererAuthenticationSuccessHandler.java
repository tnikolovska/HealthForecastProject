package com.teodora.springcloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class RefererAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	 public static final Logger LOG = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	    //@Override
	    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
	        Authentication auth = SecurityContextHolder.getContext()
	            .getAuthentication();
	        if (auth != null) {
	            LOG.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
	        }

	        response.sendRedirect(request.getContextPath() + "/accessDenied");
	    }

}
