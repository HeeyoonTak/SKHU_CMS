package com.sofCap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.Dao.AccountDao;
import com.sofCap.dto.AccountDto;
import com.sofCap.model.SemDate;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired AccountDao accountDao;

	@Override
	public List<AccountDto> findAll(){
		return accountDao.findAll();
	}

	@Override
	public List<AccountDto> findByClubId(int club_id){
		return accountDao.findByClubId(club_id);
	}
	
	@Override
	public List<AccountDto> findBySem(SemDate sem_name){
		return accountDao.findBySem(sem_name);
	}


}
