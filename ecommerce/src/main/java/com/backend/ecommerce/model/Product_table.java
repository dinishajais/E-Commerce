package com.backend.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product_table {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String name;
	@Column(name="category_id")
	private Long categoryId;
	
	public Long getcategoryId() {
		return categoryId;
	}
	public void setcategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity_available() {
		return quantity_available;
	}
	public void setQuantity_available(int quantity_available) {
		this.quantity_available = quantity_available;
	}
	private int price;
	private int quantity_available;

}
