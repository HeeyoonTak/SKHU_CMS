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

	@Override
	public void clubFileUpload(FilesDto file) {
		fileMapper.clubFileUpload(file);
		return;
	}
	
	@Override
	public FilesDto getReceiptImage(int id) {
		return fileMapper.getReceiptImage(id);
	}
	
	@Override
	public void delete(int id) {
		fileMapper.delete(id);
		return;
	}

}
