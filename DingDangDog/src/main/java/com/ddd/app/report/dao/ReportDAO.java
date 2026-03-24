package com.ddd.app.report.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ddd.app.report.dto.ReportDTO;
import com.ddd.config.MyBatisConfig;

public class ReportDAO {

	public SqlSession sqlSession;

	public ReportDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 신고 등록
	public int insertReport(ReportDTO reportDTO) {
		return sqlSession.insert("report.insertReport", reportDTO);
	}

	// 중복 신고 확인
	public int checkDuplicateReport(int reporterUserNumber, int applyNumber) {
		Map<String, Integer> map = new HashMap<>();
		map.put("reporterUserNumber", reporterUserNumber);
		map.put("applyNumber", applyNumber);
		return sqlSession.selectOne("report.checkDuplicateReport", map);
	}

	// 신고 횟수 증가
	public void reportCount(int userNumber) {
		sqlSession.update("report.reportCount", userNumber);
	}

	// 신고 3회 누적 시 블랙리스트 자동 업데이트
	public void updateBlacklist(int userNumber) {
		sqlSession.update("report.updateBlacklist", userNumber);
	}
}