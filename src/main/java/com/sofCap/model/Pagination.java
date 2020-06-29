package com.sofCap.model;

import lombok.Data;

@Data
public class Pagination {
	int pg = 1;  // 현재 페이지
    int sz = 5; // 페이지 당 레코드 수
    int recordCount;

    public String getQueryString() {
        return String.format("pg=%d&sz=%d", pg, sz);
    }

}
