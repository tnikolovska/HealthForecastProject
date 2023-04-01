package com.teodora.springcloud.service;

import org.springframework.aop.ThrowsAdvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.teodora.springcloud.model.User;
import com.teodora.springcloud.repos.UserRepo;
import java.util.Collection;
public class CustomDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(username);
				//.orElseThrow(() -> new UsernameNotFoundException("Email "+ username+" not found"));
		
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
		
	}
	/*private static Collection<? extends GrantedAuthority> getAuthorities(User user){
		String[] userRoles = user.getRoles().stream().map((role) -> role.getName().toArray(String[]::new));
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}*/
	

}
