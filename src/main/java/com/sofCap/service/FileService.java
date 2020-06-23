package com.sofCap.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sofCap.dto.FilesDto;

public interface FileService {
	void accountFileUpload(FilesDto file);
	int accountFileUpload(MultipartFile uploadFile) throws IOException;
	void clubFileUpload(FilesDto file);
	int clubFileUpload(MultipartFile uploadFile) throws IOException;
	FilesDto getReceiptImage(int id);
	void delete(int id);

}
