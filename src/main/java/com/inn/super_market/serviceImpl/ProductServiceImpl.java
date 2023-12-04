package com.inn.super_market.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.super_market.JWT.JwtFilter;
import com.inn.super_market.POJO.Category;
import com.inn.super_market.POJO.Product;
import com.inn.super_market.constents.SuperConstants;
import com.inn.super_market.dao.ProductDao;
import com.inn.super_market.service.ProductService;
import com.inn.super_market.utils.SuperUtils;
import com.inn.super_market.wrapper.ProductWrapper;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
	
	
	@Autowired
	ProductDao productDao;
	
	
	@Autowired
	JwtFilter jwtFilter;

	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		try {
			
			if(jwtFilter.isAdmin()) {
				if(validateProductMap(requestMap,false)) {
					productDao.save(getProductFromMap(requestMap,false));
					return  SuperUtils.getResponseEntity("Product Added Successfully",HttpStatus.OK);
				}
			
				return  SuperUtils.getResponseEntity(SuperConstants.INVALID_DATA,HttpStatus.BAD_REQUEST);

			}
			else {
				return  SuperUtils.getResponseEntity(SuperConstants.UNAUTHORIZES_ACCESS,HttpStatus.UNAUTHORIZED);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return  SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
		if(requestMap.containsKey("name")) {
			if(requestMap.containsKey("id") && validateId) {
				return true;
			}else if (!validateId) {
				return true;
			}
		}
		return false;
	}
	
	private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
		Category category =new Category();
		category.setId(Integer.parseInt(requestMap.get("categoryId")));
		
		Product product = new Product();

		if(isAdd) {
			product.setId(Integer.parseInt(requestMap.get("id")));
		}else {
			product.setStatus("true");
		}
		product.setCategory(category);
		product.setName(requestMap.get("name"));
		product.setDescription(requestMap.get("description"));
		product.setPrice(Integer.parseInt(requestMap.get("price")));
		return product;
		
	}
	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() {
			try {
				
				return new ResponseEntity<>(productDao.getAllProduct(),HttpStatus.OK);
			 }catch(Exception ex){
				ex.printStackTrace();
			}
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()) {
				if(validateProductMap(requestMap,true)) {
					Optional<Product> optional =productDao.findById(Integer.parseInt(requestMap.get("id")));
					if(!optional.isEmpty()) {
						Product product= getProductFromMap(requestMap, true);
						product.setStatus(optional.get().getStatus());
						productDao.save(product);
						return SuperUtils.getResponseEntity("Product Updated Successfully",HttpStatus.OK);
					}
						else {
							return SuperUtils.getResponseEntity("Product id does not exis",HttpStatus.OK);
						}
				}
				else {
					return  SuperUtils.getResponseEntity(SuperConstants.INVALID_DATA,HttpStatus.BAD_REQUEST);
				}
			}
			else {
				return  SuperUtils.getResponseEntity(SuperConstants.UNAUTHORIZES_ACCESS,HttpStatus.UNAUTHORIZED);

			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		try {
			if(jwtFilter.isAdmin()) {
				Optional optional =productDao.findById(id);
				if(!optional.isEmpty()) {
					productDao.deleteById(id);
					return SuperUtils.getResponseEntity("Product Deleted Successfully",HttpStatus.OK);
				}
				return SuperUtils.getResponseEntity("Product id does not exis",HttpStatus.OK);
			}else {
				return  SuperUtils.getResponseEntity(SuperConstants.UNAUTHORIZES_ACCESS,HttpStatus.UNAUTHORIZED);	
			}	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()){
				Optional optional =productDao.findById(Integer.parseInt(requestMap.get("id")));
				if(!optional.isEmpty()) {
					productDao.updateProductStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
					return SuperUtils.getResponseEntity("Product Status Updated Successfully",HttpStatus.OK);	
				}
				return SuperUtils.getResponseEntity("Product id does not exis",HttpStatus.OK);
		
			}else {
				return  SuperUtils.getResponseEntity(SuperConstants.UNAUTHORIZES_ACCESS,HttpStatus.UNAUTHORIZED);	
			}	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
		try {
			
			return new ResponseEntity<>(productDao.getProductByCategory(id),HttpStatus.OK);
		 }catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@Override
	public ResponseEntity<List<ProductWrapper>> getProductById(Integer id) {
		try {
			
			return new ResponseEntity<>(productDao.getProductById(id),HttpStatus.OK);
		 }catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
