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

public class DogLogListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("멍! 로그 목록");

		LogDAO logDAO = new LogDAO();
		List<LogDTO> logList = logDAO.selectAll();
		System.out.println("조회된 logList 개수 : " + logList.size());

		request.setAttribute("logList", logList);

		Result result = new Result();
		result.setPath("/app/doglog/doglog_list.jsp");
		result.setRedirect(false);
		return result;
	}
}