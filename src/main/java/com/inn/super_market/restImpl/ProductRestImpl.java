package com.inn.super_market.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.super_market.constents.SuperConstants;
import com.inn.super_market.rest.ProductRest;
import com.inn.super_market.service.ProductService;
import com.inn.super_market.utils.SuperUtils;
import com.inn.super_market.wrapper.ProductWrapper;


@RestController

public class ProductRestImpl implements ProductRest {
	
	
	@Autowired
	ProductService productService;
	

	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {
			return productService.addNewProduct(requestMap);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}


	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() {
		try {
			return productService.getAllProduct();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
			try {
				return productService.updateProduct(requestMap);
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		try {
			
			return productService.deleteProduct(id);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		try {
			return productService.updateStatus(requestMap);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}


	@Override
	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
		try {
			return productService.getByCategory(id);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<List<ProductWrapper>> getProductById(Integer id) {
		try {
			return productService.getProductById(id);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
 