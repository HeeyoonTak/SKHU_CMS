package com.sofCap.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.AccountDto;
import com.sofCap.mapper.AccountMapper;
import com.sofCap.model.SemDate;

@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired AccountMapper accountMapper;

	@Override
	public List<AccountDto> findAll(){
		return accountMapper.findAll();
	}

	@Override
	public 	List<AccountDto> findByClubIdAndSem(int club_id,String sem_name){
		return accountMapper.findByClubIdAndSem(club_id,sem_name);
	}

	@Override
	public List<AccountDto> findBySem(SemDate sem_name){
		return accountMapper.findBySem(sem_name);
	}

	@Override
	public void update(AccountDto account) {
		accountMapper.update(account);
		return;
	}

	@Override
	public void insert(AccountDto account) {
		accountMapper.insert(account);
		return;
	}

	@Override
	public void delete(int id) {
		accountMapper.delete(id);
	}

	@Override
	public List<AccountDto> getTotalByClubId(String sem_name) {
		return accountMapper.getTotalByClubId(sem_name);
	}
}
