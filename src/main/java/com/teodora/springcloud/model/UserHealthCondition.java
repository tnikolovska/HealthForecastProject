package com.teodora.springcloud.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class UserHealthCondition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="healthcondition_id",referencedColumnName = "id")
	private HealthCondition healthCondition;
	
	UserHealthCondition(){}
	public UserHealthCondition(User user, HealthCondition healthCondition) {
		this.user=user;
		this.healthCondition=healthCondition;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public HealthCondition getHealthCondition() {
		return healthCondition;
	}
	public void setHealthCondition(HealthCondition healthCondition) {
		this.healthCondition = healthCondition;
	}
	
	
}
