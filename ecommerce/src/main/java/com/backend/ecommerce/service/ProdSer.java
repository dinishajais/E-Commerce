package com.backend.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.ecommerce.dao.CatRepo;
import com.backend.ecommerce.dao.ProdRepo;
import com.backend.ecommerce.model.Category;
import com.backend.ecommerce.model.Product_table;

@Service
public class ProdSer {
	
	@Autowired
	ProdRepo prodRepo;
	
	
	@Autowired
	CatRepo catRepo;
	
	public ResponseEntity<List<Product_table>> getAllProducts(){
		try {
		return ResponseEntity.ok(prodRepo.findAll());
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	public ResponseEntity<List<Product_table>> searchByName(String name){
		try {
		return ResponseEntity.ok(prodRepo.findByNameContainingIgnoreCase(name));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	public ResponseEntity<List<Product_table>> searchByCategory(String catgeory){
		Category cat=catRepo.findByName(catgeory);
		Long id=cat.getcategory_id();		
		try {
		return ResponseEntity.ok(prodRepo.findBycategory_id(id));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	

	public ResponseEntity<String> add(Product_table product) {
		try {
			prodRepo.save(product);
			return  ResponseEntity.ok("Saved");
		}
		catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	public ResponseEntity<String> update(Long id, Product_table prod) {
		Optional<Product_table> op = prodRepo.findById(id);
		if (op.isPresent()) {
			try {
				op.get().setDescription((prod.getDescription()));
				op.get().setName((prod.getName()));
				op.get().setPrice(prod.getPrice());;
				op.get().setQuantity_available(prod.getQuantity_available());;

				prodRepo.save(op.get());
				return ResponseEntity.ok("Updated");
			} catch (Exception e) {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("exception occured");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("exception occured");
		}
	}
	
}
