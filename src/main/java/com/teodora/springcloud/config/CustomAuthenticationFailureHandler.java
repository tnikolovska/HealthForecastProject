package com.teodora.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

//@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	/* @Autowired
	    private MessageSource messages;

	    @Autowired
	    private LocaleResolver localeResolver;

	    @Autowired
	    private HttpServletRequest request;*/

	    /*@Autowired
	    private LoginAttemptService loginAttemptService;*/

   /* @Override
    public void onAuthenticationFailure(HttpServletRequest request, 
      HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    	 setDefaultFailureUrl("/login?error=true");

         super.onAuthenticationFailure(request, response, exception);

         final Locale locale = localeResolver.resolveLocale(request);

         String errorMessage = messages.getMessage("message.badCredentials", null, locale);

         /*if (loginAttemptService.isBlocked()) {
             errorMessage = messages.getMessage("auth.message.blocked", null, locale);
         }*/

        /* if (exception.getMessage()
             .equalsIgnoreCase("User is disabled")) {
             errorMessage = messages.getMessage("auth.message.disabled", null, locale);
         } else if (exception.getMessage()
             .equalsIgnoreCase("User account has expired")) {
             errorMessage = messages.getMessage("auth.message.expired", null, locale);
         } else if (exception.getMessage()
             .equalsIgnoreCase("blocked")) {
             errorMessage = messages.getMessage("auth.message.blocked", null, locale);
         } else if (exception.getMessage()
             .equalsIgnoreCase("unusual location")) {
             errorMessage = messages.getMessage("auth.message.unusual.location", null, locale);
         }

         request.getSession()
             .setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
     }*/
	    
	    @Override
	    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
	        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

	        String jsonPayload = "{\"message\" : \"%s\", \"timestamp\" : \"%s\" }";
	        httpServletResponse.getOutputStream().println(String.format(jsonPayload, e.getMessage(), Calendar.getInstance().getTime()));
	    }
}
