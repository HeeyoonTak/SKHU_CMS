package com.sofCap.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.SemDateDto;

@Mapper
public interface SemDateMapper {
	List<SemDateDto> findAll();
	static String findByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	static int findIdByDate(Date date) {
		return 0;
	}
}
