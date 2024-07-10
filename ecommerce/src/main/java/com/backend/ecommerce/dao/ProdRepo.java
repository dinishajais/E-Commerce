package com.backend.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.ecommerce.model.Product_table;

@Repository
public interface ProdRepo extends JpaRepository<Product_table, Long> {
	
	List<Product_table> findByNameContainingIgnoreCase(String name);
	List<Product_table> findBycategoryId(Long categoryId);
}