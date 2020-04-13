package com.sofCap.model;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SemDate {
	String sem_name = "2020-1학기";
	
	public String getQueryString() {
		return String.format("semdate=%s", sem_name);
	}
}
