package com.sofCap.model;


import java.io.Serializable;

import lombok.Data;

@Data
public class SemDate implements Serializable {
//	Date now = Date.valueOf(LocalDate.now());

	String sem_name = "2020-1학기";
//	public String getQueryString() {
//		return String.format("semdate=%s", sem_name);
//	}
}

