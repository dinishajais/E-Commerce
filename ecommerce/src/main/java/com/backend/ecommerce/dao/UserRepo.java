package com.backend.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.ecommerce.model.user_table;

@Repository
public interface UserRepo extends JpaRepository<user_table, Long> {
	
	Optional<user_table> findByusername(String username);
	user_table findByUsernameAndRoles(String username, String roles);
}
