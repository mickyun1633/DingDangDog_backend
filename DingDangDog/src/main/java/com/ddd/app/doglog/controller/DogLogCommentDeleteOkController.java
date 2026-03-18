package com.ddd.app.doglog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.doglog.dao.LogCommentDAO;
import com.ddd.app.doglog.dto.LogCommentDTO;

public class DogLogCommentDeleteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("댓글 삭제 완료");

		int deleteCommentNumber = Integer.parseInt(request.getParameter("commentNumber"));
		int deleteLogNumber = Integer.parseInt(request.getParameter("logNumber"));

		Integer deleteUserNumber = (Integer) request.getSession().getAttribute("userNumber");

		if (deleteUserNumber == null) {
			Result result = new Result();
			result.setPath(request.getContextPath() + "/member/login.me");
			result.setRedirect(true);
			return result;
		}

		LogCommentDAO deleteCommentDAO = new LogCommentDAO();
		LogCommentDTO deleteTargetComment = deleteCommentDAO.selectComment(deleteCommentNumber);

		if (deleteTargetComment == null) {
			return null;
		}

		if (deleteTargetComment.getUserNumber() != deleteUserNumber) {
			return null;
		}

		LogCommentDTO deleteCommentDTO = new LogCommentDTO();
		deleteCommentDTO.setCommentNumber(deleteCommentNumber);
		deleteCommentDTO.setUserNumber(deleteUserNumber);

		deleteCommentDAO.deleteComment(deleteCommentDTO);

		Result result = new Result();
		result.setPath(request.getContextPath() + "/log/detail.lo?logNumber=" + deleteLogNumber);
		result.setRedirect(true);
		return result;
	}
}