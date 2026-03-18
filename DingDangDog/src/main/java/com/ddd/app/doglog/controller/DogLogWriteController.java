package com.ddd.app.doglog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;

public class DogLogWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("멍! 로그 작성 페이지");

		Result result = new Result();
		result.setPath("/app/doglog/doglog_write.jsp");
		result.setRedirect(false);
		return result;
	}
}