package com.sofCap.service;

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
	public List<AccountDto> findByClubId(int club_id){
		return accountDao.findByClubId(club_id);
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
	public Integer getTotal(String sem_name, int club_id) {
		return accountDao.getTotal(sem_name, club_id);
	}
}
