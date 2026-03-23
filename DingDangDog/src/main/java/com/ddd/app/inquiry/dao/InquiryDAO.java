package com.ddd.app.inquiry.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ddd.app.inquiry.dto.InquiryDetailDTO;
import com.ddd.app.inquiry.dto.InquiryListDTO;
import com.ddd.app.inquiry.dto.InquiryWriteDTO;
import com.ddd.config.MyBatisConfig;

public class InquiryDAO {

	public SqlSession sqlSession;

	public InquiryDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 문의글 총 개수 가져오는 메소드
	public int getTotal(int userNumber) {
		System.out.println("문의글 총 개수 조회 - getTotal 메소드 실행");
		return sqlSession.selectOne("inquiry.getTotal", userNumber);
	}

	// 문의글 삭제 메소드
	public void deleteInquiry(InquiryListDTO delteInquiry) {
		System.out.println("문의글 삭제 - deleteinquiry 메소드 실행");
		sqlSession.delete("inquiry.delete",delteInquiry );
		System.out.println("문의글 삭제 쿼리 실행 완료");
	}

	// 문의글 작성 메소드
	public void insertInquiry(InquiryWriteDTO inquiryWriteDTO) {
			System.out.println("문의글 작성 - insertinquiry 메소드 실행");
			sqlSession.insert("inquiry.insert", inquiryWriteDTO);
			System.out.println("문의글 작성 쿼리 실행 완료");
	}

	// 문의글 상세 페이지 조회 메소드
	public InquiryDetailDTO selectInquiry(InquiryDetailDTO inquiryDetailDTO) {
		System.out.println("게시글 상세 페이지 조회(단건조회) - selectBoard 메소드 실행");
		return sqlSession.selectOne("inquiry.select", inquiryDetailDTO);
	}


	//전체 문의글 조회
	public List<InquiryListDTO> selectAll(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행 : " + pageMap);
		List<InquiryListDTO> list = sqlSession.selectList("inquiry.selectAll", pageMap);
		System.out.println("조회 결과 : " + list);
		return list;
	}



}
