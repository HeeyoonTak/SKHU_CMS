package com.sofCap.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.AccountDto;
import com.sofCap.mapper.AccountMapper;

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


}
