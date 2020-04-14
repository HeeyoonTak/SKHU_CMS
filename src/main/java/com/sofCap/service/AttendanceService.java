package com.sofCap.service;

import java.util.List;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.model.SemDate;

public interface AttendanceService {
	List<AttendanceDto> findByDate();

	List<String> findDate();

	List<AttendanceDto> findBySem(SemDate sem_name);

}