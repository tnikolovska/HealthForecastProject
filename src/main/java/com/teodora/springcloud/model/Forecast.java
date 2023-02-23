package com.teodora.springcloud.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="forecast")
public class Forecast {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="date")
	private Date date;
	@Column(name="value")
	private BigDecimal value;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="category_value")
	private int categoryValue;
	@Column(name="text")
	private String text;
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="category_id",referencedColumnName = "id")
	private Category category;
	
	public Forecast(){}
	public Forecast(String name, Date date, BigDecimal value,String categoryName,int categoryValue, Category category,String text){
		this.name=name;
		this.date=date;
		this.value=value;
		this.categoryValue=categoryValue;
		this.category=category;
		this.text=text;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getCategoryValue() {
		return categoryValue;
	}
	public void setCategoryValue(int categoryValue) {
		this.categoryValue = categoryValue;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
