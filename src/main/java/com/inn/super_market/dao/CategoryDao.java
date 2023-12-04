package com.inn.super_market.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.inn.super_market.POJO.Category;
import com.inn.super_market.POJO.User;
import com.inn.super_market.wrapper.UserWrapper;

public interface CategoryDao extends JpaRepository<Category,Integer> {
	
	List<Category> getAllCategory();
	
	
	
	

}
