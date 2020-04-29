package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import com.sofCap.dto.SemDateDto;

public interface SemDateDao {
	List<SemDateDto> findAll();
	String findByDate(Date date);
	SemDateDto findStartAndEndDate(String sem_name);
}
