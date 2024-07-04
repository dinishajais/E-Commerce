package com.backend.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.ecommerce.dao.UserRepo;
import com.backend.ecommerce.model.user_table;

@Service
public class UserSer {

	@Autowired
	UserRepo userRepo;

	public String register(user_table user) {
		userRepo.save(user);
		return "Successfully added";
	}

	public ResponseEntity<String> login(String username, String password) {
		if (userRepo.findByusername(username).isPresent()
				&& userRepo.findByusername(username).get().getPassword().equals(password)) {
			return new ResponseEntity<String>("You are successfully loggged in", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Worng credentials", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Optional<user_table>> update(String username, user_table user) {
		Optional<user_table> op = userRepo.findByusername(username);
		if (op.isPresent()) {
			try {
				op.get().setAddress(user.getAddress());
				op.get().setEmail(user.getEmail());
				op.get().setFirst_name(user.getFirst_name());
				op.get().setLast_name(user.getLast_name());
				op.get().setPassword(user.getPassword());
				op.get().setPhone_number(user.getPhone_number());
				op.get().setRoles(user.getRoles());
				op.get().setUsername(user.getUsername());

				userRepo.save(op.get());
				return new ResponseEntity<Optional<user_table>>(op, HttpStatus.CREATED);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public ResponseEntity<String> reset(String username, String password) {
		
		Optional<user_table> user=userRepo.findByusername(username);
		try {
		if(user.isPresent()) {
		user.get().setPassword(password);
		userRepo.save(user.get());
		return new ResponseEntity<String>("PASSWORD RESET DONE", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Username not found", HttpStatus.NOT_FOUND);
		}
		}
		catch(Exception e) {
			return new ResponseEntity<String> ("Exception occured", HttpStatus.BAD_REQUEST);
		}
	}

}
