package com.teodora.springcloud.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.teodora.springcloud.service.CustomDetailsService;

import lombok.AllArgsConstructor;


@Configuration
//@ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
//@Profile("!https")
@SuppressWarnings("deprecation")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/{
	
	 @Autowired
	    private DataSource dataSource;
		
	 
	  @Bean
	    public UserDetailsService userDetailsService() {
	        return new CustomDetailsService();
	    }
	  @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
	        authenticationMgr.inMemoryAuthentication()
	                         .withUser("admin@codeit.com").password("admin@123").authorities("USER","ADMIN");
	    }

		
	
	    
	 @Bean
	    public LogoutSuccessHandler logoutSuccessHandler() {
	        return new CustomLogoutSuccessHandler();
	    }

	    @Bean
	    public AccessDeniedHandler accessDeniedHandler() {
	        return new CustomAccessDeniedHandler();
	    }

	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
		
	//@SuppressWarnings("deprecation")
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	// @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	 //@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	            .antMatchers("/users").authenticated()
	            .anyRequest().permitAll()
	            .and()
	            .formLogin()
	                .usernameParameter("email")
	                .defaultSuccessUrl("/users")
	                .permitAll()
	            .and()
	            .logout().logoutSuccessUrl("/").permitAll();
	        http.httpBasic().disable();
	        http.csrf().disable();
	        http.headers().frameOptions().sameOrigin();
	    }	
}
