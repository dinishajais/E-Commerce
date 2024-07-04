package com.backend.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecommerce.dao.UserRepo;
import com.backend.ecommerce.model.Category;
import com.backend.ecommerce.model.user_table;
import com.backend.ecommerce.service.CatSer;

@RestController
@RequestMapping("/cat")
public class CatCon {
	
	@Autowired
	CatSer catSer;
	
	@Autowired
	UserRepo userRepo;

	
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Category category, @RequestParam String username){
		try {
		user_table user=userRepo.findByUsernameAndRoles(username, "ADMIN");
		if(user!=null) {
		return catSer.add(category);
		}
		else {
			return new ResponseEntity<String> ("user isn't found or user isn't admin", HttpStatus.BAD_REQUEST);
		}
		}
		catch(Exception e) {
			return new ResponseEntity<String> ("provide proper details", HttpStatus.BAD_REQUEST);
		}
	}

}
