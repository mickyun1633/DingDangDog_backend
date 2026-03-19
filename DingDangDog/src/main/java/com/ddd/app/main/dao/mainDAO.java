package com.ddd.app.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ddd.app.main.dto.mainArchiveDTO;
import com.ddd.app.main.dto.mainCareDTO;
import com.ddd.config.MyBatisConfig;



public class mainDAO {
	private SqlSession sqlSession;

	public mainDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	public List<mainArchiveDTO> selectMainArchiveList() {
		return sqlSession.selectList("main.selectMainArchiveList");
	}

	public List<mainCareDTO> selectMainCareList() {
		return sqlSession.selectList("main.selectMainCareList");
	}
}