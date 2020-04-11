package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.AttendanceDto;

public interface AttendanceService {
	List<AttendanceDto> findByDate();
	AttendanceDto CountByDate();
}