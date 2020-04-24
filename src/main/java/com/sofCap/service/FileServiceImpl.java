package com.sofCap.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sofCap.dao.FileDao;
import com.sofCap.dto.FilesDto;
import com.sofCap.mapper.FileMapper;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	FileDao fileDao;
	@Autowired
	FileMapper fileMapper;
	FilesDto afd = new FilesDto();

	@Override
	public void accountFileUpload(FilesDto file) {
		fileDao.accountFileUpload(file);
		return;
	}

	@Override
	public int accountFileUpload(MultipartFile uploadFile) throws IOException {
		String fileName = System.currentTimeMillis() + "_" + uploadFile.getOriginalFilename();
		byte[] data = null;
		long size = uploadFile.getSize();
		data = uploadFile.getBytes();
		
		afd.setFile_name(fileName);
		afd.setData(data);
		afd.setSize(size);

		accountFileUpload(afd);
		return afd.getId();
	}

}