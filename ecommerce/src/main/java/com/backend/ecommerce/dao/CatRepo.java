package com.backend.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.ecommerce.model.Category;

@Repository
public interface CatRepo extends JpaRepository<Category, Long> {
	Category findByName(String name);
}
