package com.sofCap.model;


import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.sofCap.mapper.SemDateMapper;

import lombok.Data;

@Data
public class SemDate implements Serializable {
//	@Autowired SemDateMapper semdateMapper;
//	
//	Date now = Date.valueOf(LocalDate.now());
//	String sem_name= semdateMapper.findByDate(now);
	String sem_name = "2020-1학기";
	
}

