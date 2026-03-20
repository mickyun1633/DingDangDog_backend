package com.ddd.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.admin.dao.AdminLogDAO;

public class AdminLogDeleteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("관리자 멍! 로그 삭제");

		String logNumberStr = request.getParameter("logNumber");
		int logNumber = Integer.parseInt(logNumberStr);

		AdminLogDAO adminLogDAO = new AdminLogDAO();
		adminLogDAO.deleteLog(logNumber);

		Result result = new Result();
		result.setPath(request.getContextPath() + "/admin/adminLogListOk.ad");
		result.setRedirect(true);

		return result;
	}
}