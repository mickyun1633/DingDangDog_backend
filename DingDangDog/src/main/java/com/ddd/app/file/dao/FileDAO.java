package com.ddd.app.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ddd.app.file.dto.FileDTO;
import com.ddd.config.MyBatisConfig;

public class FileDAO {

	public SqlSession sqlSession;

	public FileDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 파일 추가 메소드
	public void fileInsert(FileDTO fileDTO) {
		System.out.println("파일 DAO - 파일 저장 : " + fileDTO);
		int userNumber = fileDTO.getUserNumber();
		try {
			int result = sqlSession.insert("file.fileInsert", fileDTO);
			System.out.println("파일 저장 완료 - DB에 저장된 행의 개수 : " + result);
			FileDTO uploadFile = selectFile(userNumber);
			System.out.println("db에서 가져온 파일 : " + uploadFile);
		} catch (Exception e) {
			System.out.println("파일 저장이 실패했습니다 : " + e.getMessage());
			e.printStackTrace();
		}
	}
//	public void fileInsert(FileDTO fileDTO) {
//		System.out.println("파일 DAO - 파일 저장 : " + fileDTO);
//		int userNumber = fileDTO.getUserNumber();
//		try {
//			// 파일이 있다면 삭제
//			int fileCount = fileCount(userNumber);
//			if (fileCount > 0) {
//				deleteFile(userNumber);
//			}
//			
//			int result = sqlSession.insert("file.fileInsert", fileDTO);
//			System.out.println("파일 저장 완료 - DB에 저장된 행의 개수 : " + result);
//			List<FileDTO> uploadFile = selectFile(userNumber);
//			System.out.println("db에서 가져온 파일 : " + uploadFile);
//		} catch (Exception e) {
//			System.out.println("파일 저장이 실패했습니다 : " + e.getMessage());
//			e.printStackTrace();
//		}
//	}

	// 파일 개수 조회 메소드
	public int fileCount(int userNumber) {
		System.out.println("파일 개수 조회 ");
		return sqlSession.selectOne("file.fileCount", userNumber);
	}

	// 파일 조회 메소드
	public FileDTO selectFile(int userNumber) {
		System.out.println("파일 DAO - 파일 조회");
		return sqlSession.selectOne("file.selectFile", userNumber);
	}

	// 파일 삭제 메소드
	public int deleteFile(int userNumber) {
		System.out.println("파일 DAO - 파일 삭제");
		return sqlSession.delete("file.deleteFile", userNumber);
	}

}
