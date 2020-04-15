package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.AttendanceDto;

public interface AttendanceService {

	List<String> findDate(int semId);

	List<AttendanceDto> findBySemDate(int semId);

	List<String> findAdmin(int semId);
}