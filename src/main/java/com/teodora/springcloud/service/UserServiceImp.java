package com.teodora.springcloud.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teodora.springcloud.config.CustomPasswordEncoder;
import com.teodora.springcloud.dao.UserDao;
import com.teodora.springcloud.exception.UserAlreadyExistsException;
import com.teodora.springcloud.model.Role;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.model.VerificationToken;
import com.teodora.springcloud.repos.RoleRepo;
import com.teodora.springcloud.repos.UserRepo;
import com.teodora.springcloud.repos.VerificationTokenRepository;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;

import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

@Service
@Transactional
public class UserServiceImp implements UserService,UserDetailsService {
	
	@Autowired
	UserDao userDao;
	
	private UserRepo userRepo;
	private CustomPasswordEncoder customPasswordEncoder;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;
	
	   public static final String TOKEN_INVALID = "invalidToken";
	    public static final String TOKEN_EXPIRED = "expired";
	    public static final String TOKEN_VALID = "valid";
	
	
	/*@Autowired
	RoleRepo roleRepo;*/
	 @Autowired
	    public UserServiceImp(UserRepo userRepository,
	                       @Lazy CustomPasswordEncoder customPasswordEncoder){
	        this.userRepo = userRepository;
	        this.customPasswordEncoder = customPasswordEncoder;
	    }
	 
	 public void registerUser(User user) {
	        //User newUser = new User();
	        //newUser.setId(user.getId());
	        /*newUser.setFirstName(user.getFirstName());
	        newUser.setLastName(user.getLastName());
	        newUser.setEmail(user.getEmail());
	        newUser.setBirthDate(user.getBirthDate());*/
		 	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 	//CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
	        //user.setPassword(customPasswordEncoder.encode(user.getPassword()));
		 	user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userRepo.save(user);
		 	//create(user.getFirstName(), user.getLastName(), user.getBirthDate(),user.getEmail(), user.getPassword());
	 } 
	 
	 public User findUserById(Long id) {
	        return userRepo.getReferenceById(id);
	    }

	 @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userRepo.findByEmail(email);
	    if(user == null) throw new UsernameNotFoundException(email);
	            return new org.springframework.security.core.userdetails.User(email,
	                    null,Collections.emptyList());
	    }
	 
	
	@Override
	public User create(String firstName, String lastName,Date birthDate, String email, String password) {
		// TODO Auto-generated method stub
		User user=new User(firstName,lastName,birthDate,email,password);
		userDao.create(user);
		return user;
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}

	@Override
	public void updateUser(Long id, String firstName, String lastName, Date birthDate, String email, String password) {
		// TODO Auto-generated method stub
		User updateUser = userDao.getUser(id);
		updateUser.setFirstName(firstName);
		updateUser.setLastName(lastName);
		updateUser.setBirthDate(birthDate);
		updateUser.setEmail(email);
		updateUser.setPassword(password);
		userDao.updateUser(updateUser);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userDao.deleteUser(user);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}

	@Override
	public void saveUserWIthDefaultRole(User user) {
		// TODO Auto-generated method stub
		Role role = roleRepo.findByName("ROLE_ADMIN");
		user.addRole(role);
		userRepo.save(user);
		
	}

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String verificationToken) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	/*public UserDetails loadUserByUsername(String email) 
			  throws UsernameNotFoundException {
			 
			    boolean enabled = true;
			    boolean accountNonExpired = true;
			    boolean credentialsNonExpired = true;
			    boolean accountNonLocked = true;
			    try {
			        User user = userRepo.findByEmail(email);
			        if (user == null) {
			            throw new UsernameNotFoundException(
			              "No user found with username: " + email);
			        }
			        
			        return new org.springframework.security.core.userdetails.User(
			          user.getEmail(), 
			          user.getPassword().toLowerCase(), 
			          user.isEnabled(), 
			          accountNonExpired, 
			          credentialsNonExpired, 
			          accountNonLocked, 
			          getAuthorities(user.getRoles()));
			    } catch (Exception e) {
			        throw new RuntimeException(e);
			    }
			}*/
	  /*private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
	        return getGrantedAuthorities(getPrivileges(roles));
	    }
	   private List<String> getPrivileges(final Collection<Role> roles) {
	        final List<String> privileges = new ArrayList<>();
	        final List<Privilege> collection = new ArrayList<>();
	        for (final Role role : roles) {
	            privileges.add(role.getName());
	            collection.addAll(role.getPrivileges());
	        }
	        for (final Privilege item : collection) {
	            privileges.add(item.getName());
	        }

	        return privileges;
	    }*/

	/*@Override
	public User registerNewUserAccount(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		 if (emailExist(user.getEmail())) {
	            throw new UserAlreadyExistsException(
	              "There is an account with that email adress: " 
	              + user.getEmail());
	        }
	        
	        User newUser = new User();
	        newUser.setFirstName(user.getFirstName());
	        newUser.setLastName(user.getLastName());
	        newUser.setPassword(user.getPassword());
	        newUser.setEmail(user.getEmail());
	        newUser.setRoles(Arrays.asList(roleRepo.findByName("ROLE_USER")));
	        return userRepo.save(user);
	}*/
	/* private boolean emailExist(String email) {
	        return userRepo.findByEmail(email) != null;
	    }

	@Override
	public User getUser(String verificationToken) {
		// TODO Auto-generated method stub
		 User user = tokenRepository.findByToken(verificationToken).getUser();
	        return user;
	}

	@Override
	public void saveRegisteredUser(User user) {
		// TODO Auto-generated method stub
		 userRepo.save(user);
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
		
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		// TODO Auto-generated method stub
		return tokenRepository.findByToken(VerificationToken);
	}
	 private Collection<? extends GrantedAuthority> getAuthorities(final Role role) {
	        return getGrantedAuthorities(getPrivileges(role));
	    }
	
	  private List<String> getPrivileges(final Role role) {
	        final List<String> privileges = new ArrayList<>();
	        final List<Privilege> collection = new ArrayList<>();
	        //for (final Role role : roles) {
	            privileges.add(role.getName());
	            collection.addAll(role.getPrivileges());
	        //}
	        for (final Privilege item : collection) {
	            privileges.add(item.getName());
	        }

	        return privileges;
	    }
	  private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
	        final List<GrantedAuthority> authorities = new ArrayList<>();
	        for (final String privilege : privileges) {
	            authorities.add(new SimpleGrantedAuthority(privilege));
	        }
	        return authorities;
	    }

	@Override
	public void createVerificationTokenForUser(User user, String token) {
		// TODO Auto-generated method stub
		 final VerificationToken myToken = new VerificationToken(token, user);
	        tokenRepository.save(myToken);
	}

	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		 final VerificationToken verificationToken = tokenRepository.findByToken(token);
	        if (verificationToken == null) {
	            return TOKEN_INVALID;
	        }

	        final User user = verificationToken.getUser();
	        final Calendar cal = Calendar.getInstance();
	        if ((verificationToken.getExpiryDate()
	            .getTime() - cal.getTime()
	            .getTime()) <= 0) {
	            tokenRepository.delete(verificationToken);
	            return TOKEN_EXPIRED;
	        }

	        user.setEnabled(true);
	        // tokenRepository.delete(verificationToken);
	        userRepo.save(user);
	        return TOKEN_VALID;
	}*/

}
