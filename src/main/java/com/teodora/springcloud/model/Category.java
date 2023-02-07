package com.teodora.springcloud.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigDecimal beginRange;
	private BigDecimal endRange;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBeginRange() {
		return beginRange;
	}
	public void setBeginRange(BigDecimal beginRange) {
		this.beginRange = beginRange;
	}
	public BigDecimal getEndRange() {
		return endRange;
	}
	public void setEndRange(BigDecimal endRange) {
		this.endRange = endRange;
	}
	
	
	
}
