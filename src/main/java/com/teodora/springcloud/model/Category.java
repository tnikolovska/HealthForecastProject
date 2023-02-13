package com.teodora.springcloud.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="begin_range")
	private BigDecimal beginRange;
	@Column(name="end_range")
	private BigDecimal endRange;
	
	public Category() {}

	public Category(String name, BigDecimal beginRange, BigDecimal endRange) {
		this.name = name;
		this.beginRange = beginRange;
		this.endRange = endRange;
	}
	
	
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
