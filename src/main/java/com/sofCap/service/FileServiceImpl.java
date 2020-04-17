package com.sofCap.service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
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
	public int accountFileUpload(MultipartFile uploadFile) {
		String fileName = System.currentTimeMillis() + "_" + uploadFile.getOriginalFilename();
		byte[] data = null;
		long size = uploadFile.getSize();

		try {
			data = uploadFile.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return 0;
		}		
		
		afd.setFile_name(fileName);
		afd.setData(data);
		afd.setSize(size);

		accountFileUpload(afd);
		return afd.getId();
	}

}
