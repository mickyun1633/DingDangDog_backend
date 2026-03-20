package com.ddd.app.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ddd.app.mypage.dto.MypageCcareDTO;
import com.ddd.app.mypage.dto.MypageScareDTO;
import com.ddd.config.MyBatisConfig;

public class MypageCareDAO {
	private SqlSession sqlSession;

	public MypageCareDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 멍케어 신청 목록 조회
	public List<MypageCcareDTO> selectApplyList(int userNumber) {
		return sqlSession.selectList("mypageCcare.selectApplyList", userNumber);
	}

	// 봉사 신청 취소
	public int deleteApply(Map<String, Object> params) {
		return sqlSession.delete("mypageCcare.deleteApply", params);
	}

	// 내가 등록한 멍! 케어 리스트 조회
	public List<MypageScareDTO> selectMyCareList(int userNumber) {
		return sqlSession.selectList("mypageScare.selectMyCareList", userNumber);
	}
}
