package com.ddd.app.mypage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ddd.app.mypage.dto.MypageLogDTO;
import com.ddd.config.MyBatisConfig;

public class MypageLogDAO {
	
	private SqlSession sqlSession;
	
	public MypageLogDAO(){
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	
	// 게시글 목록 조회
	public List<MypageLogDTO> selectMyLogs(int userNumber) {
		return sqlSession.selectList("mypagelog.selectMyLogs", userNumber);
	}

	
}




