package com.backend.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecommerce.model.user_table;
import com.backend.ecommerce.service.UserSer;

@RestController
@RequestMapping("/user")
public class UserCon {
	
	@Autowired
	UserSer userSer;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody user_table user){
		try {
		return new ResponseEntity<String> (userSer.register(user), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String> ("couldnt add exception occured", HttpStatus.BAD_GATEWAY);
		}
	}
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){
		try{
			return userSer.login(username,password);
		}
		catch(Exception e) {
			return new ResponseEntity<String> ("couldnt add exception occured", HttpStatus.BAD_GATEWAY);
		}
		
	}
	@PutMapping("/update/{username}")
	public ResponseEntity<Optional<user_table>> update(@PathVariable String username, @RequestBody user_table user){
		return userSer.update(username,user);
	}
	@PutMapping("/reset/{username}")
	public ResponseEntity<String> reset(@PathVariable String username ,@RequestParam String password){
		return userSer.reset(username,password);
	}

}
