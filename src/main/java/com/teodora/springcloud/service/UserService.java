package com.teodora.springcloud.service;

import java.util.Date;
import java.util.List;

import com.teodora.springcloud.exception.UserAlreadyExistsException;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.model.VerificationToken;

public interface UserService {
	User create(String firstName, String lastName, Date birthDate, String email, String password);
	User getUser(Long id);
	void updateUser(Long id, String firstName, String lastName, Date birthDate, String email, String password);
	void deleteUser(User user);
	List<User> getUsers();
    void registerUser(User user);
   
    		User registerNewUserAccount(User user) 
    	      throws UserAlreadyExistsException;

    	    User getUser(String verificationToken);

    	    void saveRegisteredUser(User user);

    	    void createVerificationToken(User user, String token);

    	    VerificationToken getVerificationToken(String VerificationToken);
    	    
    	    void createVerificationTokenForUser(User user, String token);
    	    
    	    String validateVerificationToken(String token);
}
