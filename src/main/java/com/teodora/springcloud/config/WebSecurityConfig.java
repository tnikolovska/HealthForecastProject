package com.teodora.springcloud.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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


@Configuration
//@ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
//@Profile("!https")
//@SuppressWarnings("deprecation")
public class WebSecurityConfig{
		
	
	
		/*@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.authorizeRequests()
			.antMatchers("/register").permitAll()
			.antMatchers("/confirm").permitAll();
		}*/
		public WebSecurityConfig() {
	      super();
		}
	 @Bean
	    public InMemoryUserDetailsManager userDetailsService() {
	        // InMemoryUserDetailsManager (see below)
		 UserDetails user1 = User.withUsername("user1")
		            .password(passwordEncoder().encode("user1Pass"))
		            .roles("USER")
		            .build();
		        UserDetails user2 = User.withUsername("user2")
		            .password(passwordEncoder().encode("user2Pass"))
		            .roles("USER")
		            .build();
		        UserDetails admin = User.withUsername("admin")
		            .password(passwordEncoder().encode("adminPass"))
		            .roles("ADMIN")
		            .build();
		        return new InMemoryUserDetailsManager(user1, user2, admin);
	    }
	 
	 @Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http.csrf()
         .disable()
         .authorizeRequests()
         .antMatchers("/admin/**")
         .hasRole("ADMIN")
         .antMatchers("/anonymous*")
         .anonymous()
         .antMatchers("/login*")
         .permitAll()
         .anyRequest()
         .authenticated()
         .and()
         .formLogin()
         .loginPage("/login.html")
         .loginProcessingUrl("/perform_login")
         .defaultSuccessUrl("/index.html", true)
         .failureUrl("/login.html?error=true")
         //.failureHandler(authenticationFailureHandler())
         .and()
         .logout()
         .logoutUrl("/perform_logout")
         .deleteCookies("JSESSIONID");
         //.logoutSuccessHandler(logoutSuccessHandler());
		 return http.build();
		}
	    
	 @Bean
	    public LogoutSuccessHandler logoutSuccessHandler() {
	        return new CustomLogoutSuccessHandler();
	    }

	    @Bean
	    public AccessDeniedHandler accessDeniedHandler() {
	        return new CustomAccessDeniedHandler();
	    }

	    /*@Bean
	    public AuthenticationFailureHandler authenticationFailureHandler() {
	        return new CustomAuthenticationFailureHandler();
	    }*/


	 
		@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
