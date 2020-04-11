package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.ClubDto;


@Mapper
public interface ClubMapper {
	List<ClubDto> findAll();
}
