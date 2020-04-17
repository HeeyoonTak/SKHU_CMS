package com.sofCap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.ClubDao;
import com.sofCap.dto.ClubDto;

@Service
public class ClubServiceImpl implements ClubService{
	@Autowired ClubDao clubDao;

	@Override
	public List<ClubDto> findAll(){
		return clubDao.findAll();
	}
}
