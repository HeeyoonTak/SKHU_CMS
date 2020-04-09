package com.sofCap.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
	int id;
	
	int club_id;
	int price;
	int total;
	String remark; //비고
	int file_id;
	int account_type; //0이면 중앙회비, 1이면 동아리 자체회비
	Date date;
	
}
