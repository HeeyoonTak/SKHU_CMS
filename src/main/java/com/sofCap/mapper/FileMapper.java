package com.sofCap.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sofCap.dto.AccountFilesDto;

@Mapper
public interface FileMapper {
	void accountFileUpload(AccountFilesDto file);

}
