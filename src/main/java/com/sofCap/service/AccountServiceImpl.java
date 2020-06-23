package com.sofCap.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofCap.dao.AccountDao;
import com.sofCap.dto.AccountDto;
import com.sofCap.model.SemDate;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired AccountDao accountDao;
	@Autowired FileService fileService;

	@Override
	public List<AccountDto> findAll(){
		return accountDao.findAll();
	}

	@Override
	public List<AccountDto> findByClubIdAndSem(int club_id, String sem_name){
		return accountDao.findByClubIdAndSem(club_id,sem_name);
	}

	@Override
	public List<AccountDto> findBySem(SemDate sem_name){
		return accountDao.findBySem(sem_name);
	}

	@Override
	public void update(AccountDto account) {
		accountDao.update(account);
		return;
	}

	@Override
	public void insert(AccountDto account) {
		accountDao.insert(account);
		return;
	}

	@Override
	public void delete(int id) {
		accountDao.delete(id);
		return;
	}

	@Override
	public List<AccountDto> getTotalByClubId(String sem_name) {
		return accountDao.getTotalByClubId(sem_name);
	}
	
	@Override
	public int findFileId(int id) {
		return accountDao.findFileId(id);
	}
}
