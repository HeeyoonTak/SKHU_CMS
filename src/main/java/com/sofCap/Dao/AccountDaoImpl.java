package com.sofCap.Dao;

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
	public 	List<AccountDto> findByClubId(int club_id){
		return accountMapper.findByClubId(club_id);
	}

	@Override
	public List<AccountDto> findBySem(SemDate sem_name){
		return accountMapper.findBySem(sem_name);
	}
	
	@Override
	public void update(AccountDto account) {
	}
	
	@Override
	public void insert(AccountDto account) {
	}
	
	@Override
	public void delete(int id) {
	}

	@Override
	public int getTotal(SemDate semdate, int club_id) {
		return accountMapper.getTotal(semdate, club_id);
	}
}
