package com.sofCap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sofCap.dto.FilesDto;
import com.sofCap.mapper.FileMapper;

@Repository
public class FileDaoImpl implements FileDao {
	@Autowired FileMapper fileMapper;

	@Override
	public void accountFileUpload(FilesDto file) {
		fileMapper.accountFileUpload(file);
		return;
	}


}
