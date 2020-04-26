package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.ClubDto;
import com.sofCap.dto.UserDto;

@Mapper
public interface UserMapper {
	UserDto findByLoginId(String login_id);
	UserDto findOne(int id);
	List<UserDto> findAll();
	List<ClubDto> findByMember(String login_id);
	List<ClubDto> findByNotMember(String login_id);
	void insert(UserDto user);
	void update(UserDto user);
	void updateMypage(UserDto user);
	void updateRole(UserDto user);
	void delete(int id);
}