package com.ddd.app.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.admin.dao.AdminLogDAO;
import com.ddd.app.admin.dto.AdminLogDTO;

public class AdminLogDetailOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("관리자 멍! 로그 상세 조회");

		String logNumberStr = request.getParameter("logNumber");
		int logNumber = Integer.parseInt(logNumberStr);

		AdminLogDAO adminLogDAO = new AdminLogDAO();

		AdminLogDTO log = adminLogDAO.selectLogDetail(logNumber);
		AdminLogDTO mainImage = adminLogDAO.selectMainImage(logNumber);
		List<AdminLogDTO> commentList = adminLogDAO.selectCommentList(logNumber);

		request.setAttribute("log", log);
		request.setAttribute("mainImagePath", mainImage != null ? mainImage.getLogImgPath() : "");
		request.setAttribute("commentList", commentList);

		Result result = new Result();
		result.setPath("/app/admin/doglog/admin_doglog_detail.jsp");
		result.setRedirect(false);

		return result;
	}
}