package com.teodora.springcloud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.teodora.springcloud.dao.HealthConditionDaoImp;


@Entity
@Table(name="user_health_condition_symptom")
public class UserHealthConditionSymptom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="healthcondition_id",referencedColumnName = "id")
	private HealthCondition healthCondition;
	@Transient
	private List<String> userMigraineSymptoms;
	@Transient
	private List<String> userArthritisSymptoms;
	@Transient
	private List<String> userSinusSymptoms;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;
	
	public UserHealthConditionSymptom(){}
	public UserHealthConditionSymptom(HealthCondition healthCondition,List<String> userMigraineSymptoms,List<String> userArthritisSymptoms, List<String> userSinusSymptoms, User user){
		this.healthCondition=healthCondition;
		this.userMigraineSymptoms=userMigraineSymptoms;
		this.userArthritisSymptoms=userArthritisSymptoms;
		this.userSinusSymptoms=userSinusSymptoms;
		this.user=user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public HealthCondition getHealthCondition() {
		return healthCondition;
	}
	public void setHealthCondition(HealthCondition healthCondition) {
		this.healthCondition = healthCondition;
	}
	
	
	
	public List<String> getUserMigraineSymptoms() {
		return userMigraineSymptoms;
	}
	public void setUserMigraineSymptoms(List<String> userMigraineSymptoms) {
		this.userMigraineSymptoms = userMigraineSymptoms;
	}
	public List<String> getUserArthritisSymptoms() {
		return userArthritisSymptoms;
	}
	public void setUserArthritisSymptoms(List<String> userArthritisSymptoms) {
		this.userArthritisSymptoms = userArthritisSymptoms;
	}
	public List<String> getUserSinusSymptoms() {
		return userSinusSymptoms;
	}
	public void setUserSinusSymptoms(List<String> userSinusSymptoms) {
		this.userSinusSymptoms = userSinusSymptoms;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
