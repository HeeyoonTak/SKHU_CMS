package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.UserDto;

@Mapper
public interface UserMapper {

	UserDto findByLoginId(String login_id);

	List<UserDto> findAll();
}