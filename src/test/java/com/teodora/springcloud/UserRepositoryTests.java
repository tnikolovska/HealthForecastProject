package com.teodora.springcloud;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.teodora.springcloud.model.Role;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.repos.RoleRepo;
import com.teodora.springcloud.repos.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired 
	private UserRepo repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Test
	public void testCreateUser() {
		/*User user = new User();
		user.setEmail("nikolovskat95@gmail.com");
		user.setPassword("Mypassword1!");
		user.setFirstName("Teodora");
		user.setLastName("Nikolovska");
		user.setEnabled(false);
		user.setBirthDate(Date.valueOf("1995-25-10"));*/
		
		User user = new User();
		user.setEmail("teodora.nikolovska95@outlook.com");
		user.setPassword("Mypassword1!");
		user.setFirstName("Teodora");
		user.setLastName("Nikolovska");
		user.setEnabled(false);
		user.setBirthDate(Date.valueOf("1995-25-10"));
		
		User savedUser = repo.save(user);
		User existUser = entityManager.find(User.class,savedUser.getId());
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "nikolovskat95@gmail.com";
		User user = repo.findByEmail(email);
		assertThat(user).isNotNull();
		
	}
	@Test
	public void testAddRoleToNewUser() {
		User user = new User();
		user.setEmail("nikolovskat95@gmail.com");
		user.setPassword("Mypassword1!");
		user.setFirstName("Teodora");
		user.setLastName("Nikolovska");
		user.setEnabled(false);
		user.setBirthDate(Date.valueOf("1995-10-25"));
		
		Role roleUser=roleRepo.findByName("User");
		user.addRole(roleUser);
		
		//repo.save(user);
		User savedUser = repo.save(user);
		assertThat(savedUser.getRoles().size()).isEqualTo(1);	
	}
	@Test
	public void testAddRoleToExistingUser() {
		User user = repo.findById(2L).get();
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		
		//Role roleAdmin = new Role(2L);
		Role roleAdmin = roleRepo.findByName("Admin");
		user.addRole(roleAdmin);
		
		User savedUser = repo.save(user);
		assertThat(savedUser.getRoles().size()).isEqualTo(2);
		
	}
	
}
