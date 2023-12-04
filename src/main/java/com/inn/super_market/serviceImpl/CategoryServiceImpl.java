package com.inn.super_market.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.inn.super_market.JWT.JwtFilter;
import com.inn.super_market.POJO.Category;
import com.inn.super_market.constents.SuperConstants;
import com.inn.super_market.dao.CategoryDao;
import com.inn.super_market.service.CategoryService;
import com.inn.super_market.utils.SuperUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	
	
	@Autowired
	CategoryDao categoryDao;
	
	
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		
		try {
			
			if(jwtFilter.isAdmin()) {
				if(validateCategoryMap(requestMap,false)) {
					categoryDao.save(getCategoryFromMap(requestMap,false));
					return  SuperUtils.getResponseEntity("Category Added Successfully",HttpStatus.OK);

					
				}
				
			}
			else {
				return  SuperUtils.getResponseEntity(SuperConstants.UNAUTHORIZES_ACCESS,HttpStatus.UNAUTHORIZED);

			}
			
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return  SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

		
		
		
	}

	private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
		if(requestMap.containsKey("name")) {
			if(requestMap.containsKey("id") && validateId) {
				return true;
			}else if (!validateId) {
				return true;
			}
		}
		return false;
	}
	
	private Category getCategoryFromMap(Map<String,String>requestMap,Boolean isAdd) {
		Category category =new Category();
		if(isAdd) {
			category.setId(Integer.parseInt(requestMap.get("id")));
		}
		category.setName(requestMap.get("name"));
		return category;
		
		
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
		final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
		try {
			if(!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
				logger.info("Inside if");
				return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(),HttpStatus.OK);
			}
			else  {
			return new ResponseEntity<>(categoryDao.findAll(),HttpStatus.OK);
			}

			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()) {
				if(validateCategoryMap(requestMap,true)) {
					Optional optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
					if(!optional.isEmpty()) {
						categoryDao.save(getCategoryFromMap(requestMap, true));
						return SuperUtils.getResponseEntity("Category Updated Sussfully",HttpStatus.OK);

						
					}else {
						return SuperUtils.getResponseEntity("Category id does not exis",HttpStatus.OK);
					}
				}	
				return  SuperUtils.getResponseEntity(SuperConstants.INVALID_DATA,HttpStatus.BAD_REQUEST);

			}else {
				return  SuperUtils.getResponseEntity(SuperConstants.UNAUTHORIZES_ACCESS,HttpStatus.UNAUTHORIZED);

			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SuperUtils.getResponseEntity(SuperConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	

}
