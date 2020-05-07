package com.sofCap.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	int id;
	int board_name_id;
	String title;
	String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date date;
	int file_id;
	int club_id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date start_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date end_date;
	ClubDto club;
}
