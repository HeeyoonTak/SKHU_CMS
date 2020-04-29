package com.sofCap.service;

import java.sql.Date;
import java.util.List;

import com.sofCap.dto.SemDateDto;

public interface SemDateService {
	List<SemDateDto> findAll();
	String findByDate(Date date);
	SemDateDto findStartAndEndDate(String sem_name);
}
