package com.sofCap.dao;

import com.sofCap.dto.FilesDto;

public interface FileDao {
	void accountFileUpload(FilesDto file);
	void clubFileUpload(FilesDto file);
	FilesDto getReceiptImage(int id);
	void delete(int id);
}
