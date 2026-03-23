package com.ddd.app.inquiry.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.inquiry.dao.InquiryDAO;
import com.ddd.app.inquiry.dto.InquiryWriteDTO;

public class InquiryWriteOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InquiryWriteDTO inquiryWriteDTO = new InquiryWriteDTO();
		InquiryDAO inquiryDAO = new InquiryDAO();
		Result result = new Result();
		Integer userNumber = (Integer)request.getSession().getAttribute("userNumber");

		System.out.println("현재 로그인한 회원 번호 : " + userNumber);
		if(userNumber == null) {
			System.out.println("오류 : 로그인 된 사용자가 없습니다");
			result.setPath(request.getContextPath() + "/app/login/login.jsp");
		    result.setRedirect(true);
			return result;
		}

		inquiryWriteDTO.setInquiryTitle(request.getParameter("title"));
		inquiryWriteDTO.setInquiryPost(request.getParameter("content"));
		inquiryWriteDTO.setUserNumber(userNumber);
//		String regDateStr = request.getParameter("regDate");
//		LocalDateTime regDate = LocalDateTime.parse(regDateStr);
//		inquiryWriteDTO.setRegDate(regDate);

		inquiryDAO.insertInquiry(inquiryWriteDTO);

		result.setPath(request.getContextPath() + "/inquiry/inquiryListOk.in");
		result.setRedirect(true);
	    return result;
	}

}
