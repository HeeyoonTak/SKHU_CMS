package com.sofCap.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SemDateDto {
	int id;
	
	Date start_date;
	Date end_date;
	String sem_name;
}
