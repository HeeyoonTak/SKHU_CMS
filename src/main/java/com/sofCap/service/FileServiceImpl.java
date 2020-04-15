package com.sofCap.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sofCap.dto.AccountFilesDto;
import com.sofCap.mapper.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	@Autowired FileMapper fileMapper;
	@Autowired AccountFilesDto afd = new AccountFilesDto();

	@Override
	public void accountFileUpload(AccountFilesDto file) {
		return;
	}

	@Override
	public int accountFileUpload(MultipartFile uploadFile, int account_id) throws IOException{
		String fileName = System.currentTimeMillis()+"_"+uploadFile.getOriginalFilename();
		byte[] data = uploadFile.getBytes();
		int size = (int) uploadFile.getSize();

		afd.setFile_name(fileName);
		afd.setAccount_id(account_id);
		afd.setData(data);
		afd.setSize(size);

		fileMapper.accountFileUpload(afd);
		return afd.getId();
	}

}
