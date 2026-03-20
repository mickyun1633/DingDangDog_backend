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

public class AdminLogListOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("관리자 멍! 로그 목록 조회");

		AdminLogDAO adminLogDAO = new AdminLogDAO();

		String keyword = request.getParameter("keyword");
		String searchType = request.getParameter("searchType");

		if (keyword == null) {
			keyword = "";
		}
		keyword = keyword.trim();

		List<AdminLogDTO> logList;

		if (keyword.isEmpty()) {
			logList = adminLogDAO.selectAdminLogList();
		} else {
			logList = adminLogDAO.searchAdminLogList(searchType, keyword);
		}

		request.setAttribute("logList", logList);
		request.setAttribute("keyword", keyword);
		request.setAttribute("searchType", searchType);

		Result result = new Result();
		result.setPath("/app/admin/doglog/admin_doglog_list.jsp");
		result.setRedirect(false);

		return result;
	}
}