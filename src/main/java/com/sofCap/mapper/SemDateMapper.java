package com.sofCap.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.SemDateDto;

@Mapper
public interface SemDateMapper {
	List<SemDateDto> findAll();
	String findByDate(Date date);
	SemDateDto findStartAndEndDate(String sem_name);
}
