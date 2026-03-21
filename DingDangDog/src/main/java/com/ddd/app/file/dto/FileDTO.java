package com.ddd.app.file.dto;

public class FileDTO {

//	--보호소 첨부파일
//	CREATE TABLE DDD_FILE (
//	   file_system_name VARCHAR2(300) ,
//	   file_original_name varchar2(300),
//	   USER_NUMBER NUMBER,
//	   CONSTRAINT PK_file_number PRIMARY KEY (file_system_name),
//	   CONSTRAINT FK_file_user_number FOREIGN KEY (USER_NUMBER) REFERENCES ddd_user(USER_NUMBER) ON DELETE CASCADE 
//	);

	private String fileSystemName;
	private String fileOriginalName;
	private int userNumber;

	public String getFileSystemName() {
		return fileSystemName;
	}

	public void setFileSystemName(String fileSystemName) {
		this.fileSystemName = fileSystemName;
	}

	public String getFileOriginalName() {
		return fileOriginalName;
	}

	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	@Override
	public String toString() {
		return "FileDTO [fileSystemName=" + fileSystemName + ", fileOriginalName=" + fileOriginalName + ", userNumber="
				+ userNumber + "]";
	}

}
