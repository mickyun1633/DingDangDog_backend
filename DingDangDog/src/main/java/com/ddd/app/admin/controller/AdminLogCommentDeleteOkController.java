package com.ddd.app.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.admin.dao.AdminLogDAO;

public class AdminLogCommentDeleteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("관리자 멍! 로그 댓글 삭제");

		int commentNumber = Integer.parseInt(request.getParameter("commentNumber"));
		int logNumber = Integer.parseInt(request.getParameter("logNumber"));

		AdminLogDAO adminLogDAO = new AdminLogDAO();
		adminLogDAO.deleteComment(commentNumber);

		Result result = new Result();
		result.setPath(request.getContextPath() + "/admin/adminLogDetailOk.ad?logNumber=" + logNumber);
		result.setRedirect(true);

		return result;
	}
}