package com.backend.ecommerce.controller;

import java.util.List;

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

import com.backend.ecommerce.dao.UserRepo;
import com.backend.ecommerce.model.Product_table;
import com.backend.ecommerce.model.user_table;
import com.backend.ecommerce.service.ProdSer;

@RestController
@RequestMapping("/prod")
public class ProdCon {
	
	@Autowired
	ProdSer prodSer;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Product_table product, @RequestParam String username){
		try {
		user_table user=userRepo.findByUsernameAndRoles(username, "ADMIN");
		if(user!=null) {
		return prodSer.add(product);
		}
		else {
			return new ResponseEntity<String> ("user isn't found or user isn't admin", HttpStatus.BAD_REQUEST);
		}
		}
		catch(Exception e) {
			return new ResponseEntity<String> ("provide proper details", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Product_table prod, @RequestParam String username){
		try {
			user_table user=userRepo.findByUsernameAndRoles(username, "ADMIN");
			if(user!=null) {
				return prodSer.update(id,prod);
			}
			else {
				return new ResponseEntity<String> ("Not admin", HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<String> ("provide proper details", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product_table>> search(@RequestParam(required = false) String name , @RequestParam(required = false) String category){
		if(name!=null) {
			return prodSer.searchByName(name);
		}
		else if(category !=null) {
			return prodSer.searchByCategory(category);
		}
		else {
			return prodSer.getAllProducts();
		}
	}
	
	
	
	
	
}
