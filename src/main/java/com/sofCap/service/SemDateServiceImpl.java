package com.sofCap.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.SemDateDao;
import com.sofCap.dto.SemDateDto;

@Service
public class SemDateServiceImpl implements SemDateService {
	@Autowired SemDateDao semdateDao;
	
	@Override
	public List<SemDateDto> findAll(){
		return semdateDao.findAll();
	}
	
	@Override
	public String findByDate(Date date) {
		return semdateDao.findByDate(date);
	}
}
