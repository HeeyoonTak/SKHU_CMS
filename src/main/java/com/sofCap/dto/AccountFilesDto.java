package com.sofCap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountFilesDto {
	int id;

	String file_name;
	int size;
	byte[] data;
	int account_id;
}
