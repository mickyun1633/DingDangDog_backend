package com.ddd.app.inquiry.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.inquiry.dao.InquiryDAO;
import com.ddd.app.inquiry.dto.InquiryListDTO;

public class InquiryListOkController implements Execute {

	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("======InquiryListOk 실행");
		InquiryDAO inquiryDAO = new InquiryDAO();
		Result result = new Result();
		
		String temp = request.getParameter("page");
		int page = (temp == null) ? 1 : Integer.valueOf(temp); // 페이지 번호
		int rowCount = 10; //  한 페이지당 문의 글 개수 
		int pageCount = 5; // 페이지네이션 한 문의글에 보이는 버튼 개수
		
		int startRow = (page - 1) * rowCount + 1;
		int endRow = startRow + rowCount - 1;
		
		
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		
		List<InquiryListDTO> inquiryList = inquiryDAO.selectAll(pageMap);
		// DAO에서 select all 메서드 호출 -> 게시글 전체 조회 
		request.setAttribute("inquiryList", inquiryList);
		return result;

		
		
		
		
		
	}
	
	
	
	
	
}
