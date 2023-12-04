package com.inn.super_market.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.inn.super_market.POJO.User;
import com.inn.super_market.wrapper.UserWrapper;

public interface UserDao extends JpaRepository<User,Integer> {
	
	User findByEmailId(@Param("email")String email);
	
	List<UserWrapper> getAllUser();
	List<String> getAllAdmin();
	
	
	
	@Transactional
	@Modifying
	Integer updateStatus(@Param("status")String status,@Param("id")Integer id);
	
	User findByEmail(String email);

}