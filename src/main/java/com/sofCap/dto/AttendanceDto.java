package com.sofCap.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDto {
	int id;
	int club_id;
	int check; //0이면 false, 1이면 true
	Date date;
	int user_id;

	String name; //user 이름 출력
}