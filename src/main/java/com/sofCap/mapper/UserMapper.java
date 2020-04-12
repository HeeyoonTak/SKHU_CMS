package com.sofCap.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sofCap.dto.UserDto;

@Mapper
public interface UserMapper {
	
	UserDto findByLoginId(String login_id);
	UserDto findOne(int id);
	List<UserDto> findAll();
	void insert(UserDto user); 
	void update(UserDto user);
	void delete(int id);	
}