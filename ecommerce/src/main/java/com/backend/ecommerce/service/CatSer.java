package com.backend.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.ecommerce.dao.CatRepo;
import com.backend.ecommerce.model.Category;

@Service
public class CatSer {
	
	@Autowired
	CatRepo catRepo;

	public ResponseEntity<String> add(Category category) {
		try {
			catRepo.save(category);
			return new ResponseEntity<String>("Saved",HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Exception occured", HttpStatus.BAD_REQUEST);
		}
		
	}

}
