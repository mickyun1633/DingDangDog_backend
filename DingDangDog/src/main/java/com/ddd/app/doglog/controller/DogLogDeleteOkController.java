package com.ddd.app.doglog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.doglog.dao.LogCommentDAO;
import com.ddd.app.doglog.dao.LogDAO;
import com.ddd.app.doglog.dao.LogImgDAO;
import com.ddd.app.doglog.dto.LogDTO;

public class DogLogDeleteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("멍! 로그 삭제 완료");

		HttpSession session = request.getSession();
		Object userNumberObj = session.getAttribute("userNumber");

		if (userNumberObj == null) {
			Result result = new Result();
			result.setPath(request.getContextPath() + "/member/login.me");
			result.setRedirect(true);
			return result;
		}

		int userNumber = (Integer) userNumberObj;
		int logNumber = Integer.parseInt(request.getParameter("logNumber"));

		LogDAO logDAO = new LogDAO();
		LogImgDAO logImgDAO = new LogImgDAO();
		LogCommentDAO logCommentDAO = new LogCommentDAO();

		LogDTO log = logDAO.select(logNumber);

		if (log == null) {
			return null;
		}

		if (log.getUserNumber() != userNumber) {
			return null;
		}

		logImgDAO.deleteImageByLogNumber(logNumber);
		logCommentDAO.deleteByLogNumber(logNumber);
		logDAO.delete(logNumber);

		Result result = new Result();
		result.setPath(request.getContextPath() + "/log/list.lo");
		result.setRedirect(true);
		return result;
	}
}