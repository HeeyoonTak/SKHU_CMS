package com.sofCap.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sofCap.dto.AccountFilesDto;

public interface FileService {
	void accountFileUpload(AccountFilesDto file);
	int accountFileUpload(MultipartFile uploadFile, int account_id) throws IOException;

}
