package com.sofCap.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubDto {
	int id;

	String club_name;
	int club_type;
	String content;
	int file_id;
	List<AccountDto> accounts;
}
