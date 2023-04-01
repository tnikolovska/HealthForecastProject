package com.teodora.springcloud.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.teodora.springcloud.service.CustomDetailsService;
import com.teodora.springcloud.service.UserServiceImp;

import lombok.AllArgsConstructor;


@Configuration
//@ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
//@Profile("!https")
//@SuppressWarnings("deprecation")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
    private DataSource dataSource;
    
    @Autowired
    private UserServiceImp userService;
    
    //@Override
    /*protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }*/
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomDetailsService();
    }
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    
    
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/createUserView").permitAll()
                        .antMatchers("/login").permitAll()
                        .antMatchers("/confirm-account").permitAll()
                        .antMatchers("/index").permitAll()
                        .antMatchers("/category-list").permitAll()
                        .antMatchers("/healthCondition-list").permitAll()
                        .antMatchers("/symptom-list").permitAll()
                        .antMatchers("/css/**").permitAll()
                        .antMatchers("/js/**").permitAll()
                        .antMatchers("/fonts/**").permitAll()
                        .antMatchers("/images/**").permitAll()
                        .antMatchers("/intlTellInput/**").permitAll()
                        //.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        //.antMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/user/")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }*/
    //@Override
    /*protected void configure(final AuthenticationManagerBuilder auth)throws Exception{
    	auth.inMemoryAuthentication()
    	.withUser("user").password(passwordEncoder().encode("spring123")).roles("USER")
    	.and()
    	.withUser("admin").password(passwordEncoder().encode("admin123"))
    	.roles("ADMIN","USER");
    }*/
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	//ova e osnovno resenie
    	/*http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .and()
            .logout().permitAll();*/
    	http.authorizeHttpRequests()
    	.antMatchers("/*").permitAll()
    	.antMatchers("/login").permitAll()
        .antMatchers("/confirm-account").permitAll()
        .antMatchers("/index").permitAll()
        .antMatchers("/createUserView").permitAll()
        .antMatchers("/category-list").permitAll()
        .antMatchers("/healthCondition-list").permitAll()
        .antMatchers("/healthCondition").permitAll()
        .antMatchers("/symptom-list").permitAll()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/fonts/**").permitAll()
        .antMatchers("/images/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/intlTellInput/**").permitAll()
        //.antMatchers("/category").permitAll()
        .antMatchers("/createCategoryView").authenticated()
        .antMatchers("/delete/**").authenticated()
        .antMatchers("/edit/**").authenticated()
        .antMatchers("/result/**").authenticated()
        .antMatchers("/createHealthConditionView").authenticated()
        .antMatchers("/delete-healthCondition/**").authenticated()
        .antMatchers("/edit-healthCondition/**").authenticated()
        .antMatchers("/healthCondition-list").authenticated()
        .antMatchers("/healthCondition").authenticated()
        .antMatchers("/symptom-list").authenticated()
        //.antMatchers("/symptom-list").authenticated()
        .antMatchers("/edit-symptom/**").authenticated()
        .antMatchers("/delete-symptom/**").authenticated()
        .antMatchers("/createSymptomView").hasAuthority("ADMIN")
        .antMatchers("/symptom").authenticated()
        .antMatchers("/userHealthCondition-list").authenticated()
        .antMatchers("/userHealthCondition-edit/**").authenticated()
        .antMatchers("/delete/**").hasAuthority("ADMIN")
        .antMatchers("/createUserHealthConditionView").hasAuthority("ADMIN")
        .antMatchers("/user-health-condition").hasAuthority("ADMIN")
        .antMatchers("/determine-user-health").hasAuthority("ADMIN")
        .antMatchers("/user/**").authenticated()
        .antMatchers("/user-list").hasAuthority("ADMIN")
        .antMatchers("/edit-user/**").hasAuthority("ADMIN")
        .antMatchers("/delete-user/**").hasAuthority("ADMIN")
        //.requestMatchers("/delete/**").has
        //.antMatchers("/").authenticated()
        //.anyRequest().authenticated()
        .and()
        .formLogin()
        //.loginPage("/login")
        .and()
        //.logout().permitAll()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403-access-denied");
    	
    	 http.httpBasic().disable();
         http.csrf().disable();
         http.headers().frameOptions().sameOrigin();

    }
    
    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {
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
    }*/
    //@Override
    /*protected void configure(HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
         .antMatchers("/users").authenticated()
         .anyRequest().permitAll()
         .and()
         .formLogin()
             .usernameParameter("email")
             .defaultSuccessUrl("/users")
             .loginPage("login")
             .permitAll()
         .and()
         .logout().logoutSuccessUrl("/").permitAll();
     http.httpBasic().disable();
     http.csrf().disable();
     http.headers().frameOptions().sameOrigin();
    }*/
    //@Bean
    /*public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*http.csrf()
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
            //.loginPage("/login")
            //.loginProcessingUrl("/perform_login")
            .defaultSuccessUrl("/index", true)
            // .failureUrl("/login.html?error=true")
            .failureHandler(authenticationFailureHandler())
            .and()
            .logout()
            .logoutUrl("/perform_logout")
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler());
        // .and()
        // .exceptionHandling().accessDeniedPage("/accessDenied");
        // .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        return http.build();*/
    	/*http.authorizeRequests()
        .antMatchers("/users").authenticated()
        .anyRequest().permitAll()
        .and()
        .formLogin()
            .usernameParameter("email")
            .defaultSuccessUrl("/users")
            .loginPage("login")
            .permitAll()
        .and()
        .logout().logoutSuccessUrl("/").permitAll();
    http.httpBasic().disable();
    http.csrf().disable();
    http.headers().frameOptions().sameOrigin();*/
		    	/*http.authorizeRequests()
		        .antMatchers("/users").authenticated()
		        .anyRequest().permitAll()
		        .and()
		        .formLogin()
		            .usernameParameter("email")
		            .defaultSuccessUrl("/users")
		            .loginPage("login")
		            .permitAll()
		        .and()
		        .logout().logoutSuccessUrl("/").permitAll();
		    http.httpBasic().disable();
		    http.csrf().disable();
		    http.headers().frameOptions().sameOrigin();
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
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
    

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.inMemoryAuthentication()
                         .withUser("admin@codeit.com").password("admin@123").authorities("USER","ADMIN");
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
         .antMatchers("/users").authenticated()
         .anyRequest().permitAll()
         .and()
         .formLogin()
             .usernameParameter("email")
             .defaultSuccessUrl("/users")
             .loginPage("login")
             .permitAll()
         .and()
         .logout().logoutSuccessUrl("/").permitAll();
     http.httpBasic().disable();
     http.csrf().disable();
     http.headers().frameOptions().sameOrigin();
    	/*http.authorizeRequests()
		.antMatchers("/index").access("hasRole('ROLE_USER')")
		.and()
			.formLogin().loginPage("/login")
			.defaultSuccessUrl("/index")
			.failureUrl("/loginPage?error")
			.usernameParameter("username").passwordParameter("password")				
		.and()
			.logout().logoutSuccessUrl("/loginPage?logout"); */
		    	/*http.authorizeRequests()
		        .antMatchers("/users").authenticated()
		        .anyRequest().permitAll()
		        .and()
		        .formLogin()
		            .usernameParameter("email")
		            .defaultSuccessUrl("/users")
		            .loginPage("login")
		            .permitAll()
		        .and()
		        .logout().logoutSuccessUrl("/").permitAll();
		    http.httpBasic().disable();
		    http.csrf().disable();
		    http.headers().frameOptions().sameOrigin();*/
   /* }*/
    
    //@Autowired
    /*public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("password")).roles("USER");
    }*/
    
    //@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //authProvider.setUserDetailsService(userDetailsService());
        authProvider.setUserDetailsService(userService);
       // authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*@Bean
    public CustomPasswordEncoder customPasswordEncoder(){
        return new CustomPasswordEncoder();
    }*/
}
