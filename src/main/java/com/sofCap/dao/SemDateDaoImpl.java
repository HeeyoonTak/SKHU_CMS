package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.SemDateDto;
import com.sofCap.mapper.SemDateMapper;

@Repository
public class SemDateDaoImpl implements SemDateDao{
	@Autowired SemDateMapper semdateMapper;
	
	@Override
	public List<SemDateDto> findAll(){
		return semdateMapper.findAll();
	}
	
	@Override
	public String findByDate(Date date) {
		return semdateMapper.findByDate(date);
	}
	
	@Override
	public SemDateDto findStartAndEndDate(String sem_name){
		return semdateMapper.findStartAndEndDate(sem_name);
	}
	
}

