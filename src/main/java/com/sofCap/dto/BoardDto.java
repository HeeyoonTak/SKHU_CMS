package com.sofCap.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	int id;
	int board_name_id;
	String title;
	String content;
	Date date;
	int file_id;
	int club_id;
	Date start_date;
	Date end_date;
}
