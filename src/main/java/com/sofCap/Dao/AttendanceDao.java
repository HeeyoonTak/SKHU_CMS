package com.sofCap.Dao;

import java.util.List;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.model.SemDate;

public interface AttendanceDao {
	List<AttendanceDto> findByDate();

	List<String> findDate();

	List<AttendanceDto> findBySem(SemDate sem_name);
}