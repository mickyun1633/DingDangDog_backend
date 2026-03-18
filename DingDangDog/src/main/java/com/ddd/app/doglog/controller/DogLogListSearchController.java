package com.ddd.app.doglog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.doglog.dao.LogDAO;
import com.ddd.app.doglog.dto.LogDTO;

public class DogLogListSearchController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("멍! 로그 목록(검색)");

		request.setCharacterEncoding("UTF-8");

		LogDAO logDAO = new LogDAO();
		String keyword = request.getParameter("keyword");
		if (keyword == null) {
			keyword = "";
		}
		keyword = keyword.trim();
		System.out.println("검색어 : " + keyword);

		List<LogDTO> logList = logDAO.searchByTitle(keyword);
		System.out.println("조회된 logList 개수 : " + logList.size());

		request.setAttribute("logList", logList);
		request.setAttribute("keyword", keyword);

		Result result = new Result();
		result.setPath("/app/doglog/doglog_list.jsp");
		result.setRedirect(false);
		return result;
	}
}