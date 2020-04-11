package com.sofCap.Dao;

import java.util.List;

import com.sofCap.dto.AttendanceDto;

public interface AttendanceDao {
	List<AttendanceDto> findByDate();

	AttendanceDto CountByDate();
}