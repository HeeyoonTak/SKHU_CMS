package com.sofCap.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.UserDto;

@Mapper
public interface UserMapper {

	UserDto findByLoginId(String login_id);
}
