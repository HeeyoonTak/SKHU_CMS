package com.sofCap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AttendanceDto;
import com.sofCap.model.SemDate;

@Mapper
public interface AttendanceMapper {

	List<AttendanceDto> findByDate();

	List<String> findDate();

	List<AttendanceDto> findBySem(SemDate sem_name);
}