package com.sofCap.model;


import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import com.sofCap.mapper.SemDateMapper;

import lombok.Data;

@Data
public class SemDate implements Serializable {
	Date now = Date.valueOf(LocalDate.now());
	String sem_name = SemDateMapper.findByDate(now);
	int id = SemDateMapper.findIdByDate(now);

//	String sem_name = "2020-1학기";
//	public String getQueryString() {
//		return String.format("semdate=%s", sem_name);
//	}
}

