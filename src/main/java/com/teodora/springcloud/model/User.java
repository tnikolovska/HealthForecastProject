package com.teodora.springcloud.model;
import com.teodora.springcloud.annotations.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.persistence.JoinColumn;

import org.springframework.format.annotation.DateTimeFormat;

import com.teodora.springcloud.annotations.ValidPassword;

import lombok.NonNull;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	@Column(name="email",unique=true)
	@Email
	@NotEmpty(message = "Email should not be empty")
	private String email;
	@Column(name="password")
	//@ValidPassword
	private String password;
	@Column(name = "enabled")
    private boolean enabled;
	
	 @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	/*@ManyToMany(cascade=CascadeType.ALL) */
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JoinColumn(name="role_id",referencedColumnName = "id") 
	private Set<Role> roles = new HashSet<>();
	
	public User(){
		super();
		this.enabled=false;
	}
	public User(String firstName,String lastName, Date birthDate, String email, String password){
		this.firstName=firstName;
		this.lastName=lastName;
		this.birthDate=birthDate;
		this.email=email;
		this.password=password;
		this.enabled=false;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	 /*public Collection<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(final Collection<Role> roles) {
	        this.roles = roles;
	    }*/
	

    public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void addRole(Role role) {
		this.roles.add(role);
	}
	@Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!getEmail().equals(user.getEmail())) {
            return false;
        }
        return true;
    }
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=")
                .append(id)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", birthDate=").append(birthDate)
                .append(", email=").append(email)
                .append(", password=").append(password)
                //.append(", roles=").append(roles)
                .append(", enabled=").append(enabled)
                .append("]");
        return builder.toString();
    }
	
	
}
