package com.ddd.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.admin.dao.AdminDAO;
import com.ddd.app.admin.dto.AdminInquiryDTO;

public class AdminInquiryDetailOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("===AdminInquiryDetailOkController 실행===");
		AdminDAO adminDAO = new AdminDAO();
		AdminInquiryDTO adminInquiryDTO = new AdminInquiryDTO();
		Result result = new Result();

		int inquiryNumber = Integer.parseInt(request.getParameter("inquiryNumber"));

		adminInquiryDTO = adminDAO.selectInquiryDetail(inquiryNumber);

		System.out.println("조회한 문의 : " + adminInquiryDTO);

		request.setAttribute("inquiry", adminInquiryDTO);

		result.setPath("/app/admin/inquiry/admin_inquiry_detail.jsp");

		result.setRedirect(false);

		return result;
	}

}
