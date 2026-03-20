package com.ddd.app.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ddd.app.admin.dto.AdminLogDTO;
import com.ddd.config.MyBatisConfig;

public class AdminLogDAO {

	private SqlSession sqlSession;

	public AdminLogDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 관리자 목록
	public List<AdminLogDTO> selectAdminLogList() {
		return sqlSession.selectList("adminLog.selectAdminLogList");
	}

	// 관리자 목록 검색
	public List<AdminLogDTO> searchAdminLogList(String searchType, String keyword) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("searchType", searchType);
		paramMap.put("keyword", keyword);
		return sqlSession.selectList("adminLog.searchAdminLogList", paramMap);
	}

	// 상세
	public AdminLogDTO selectLogDetail(int logNumber) {
		return sqlSession.selectOne("adminLog.selectLogDetail", logNumber);
	}

	// 대표 이미지
	public AdminLogDTO selectMainImage(int logNumber) {
		return sqlSession.selectOne("adminLog.selectMainImage", logNumber);
	}

	// 댓글 목록
	public List<AdminLogDTO> selectCommentList(int logNumber) {
		return sqlSession.selectList("adminLog.selectCommentList", logNumber);
	}

	// 게시글 삭제
	public int deleteLog(int logNumber) {
		sqlSession.delete("log.deleteImageByLogNumber", logNumber);
		sqlSession.delete("adminLog.deleteCommentByLogNumber", logNumber);
		return sqlSession.delete("log.delete", logNumber);
	}

	// 댓글 삭제
	public int deleteComment(int commentNumber) {
		return sqlSession.delete("adminLog.deleteCommentByAdmin", commentNumber);
	}
}