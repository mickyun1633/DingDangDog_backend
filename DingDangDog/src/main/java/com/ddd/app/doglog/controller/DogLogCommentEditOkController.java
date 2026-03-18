package com.ddd.app.doglog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.doglog.dao.LogCommentDAO;
import com.ddd.app.doglog.dto.LogCommentDTO;

public class DogLogCommentEditOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("댓글 수정 완료");

		int updateCommentNumber = Integer.parseInt(request.getParameter("commentNumber"));
		int updateLogNumber = Integer.parseInt(request.getParameter("logNumber"));
		String updateCommentPost = request.getParameter("commentPost");

		Integer updateUserNumber = (Integer) request.getSession().getAttribute("userNumber");

		if (updateUserNumber == null) {
			Result result = new Result();
			result.setPath(request.getContextPath() + "/member/login.me");
			result.setRedirect(true);
			return result;
		}

		LogCommentDAO updateCommentDAO = new LogCommentDAO();
		LogCommentDTO originComment = updateCommentDAO.selectComment(updateCommentNumber);

		if (originComment == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if (originComment.getUserNumber() != updateUserNumber) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		LogCommentDTO updateCommentDTO = new LogCommentDTO();
		updateCommentDTO.setCommentNumber(updateCommentNumber);
		updateCommentDTO.setLogNumber(updateLogNumber);
		updateCommentDTO.setUserNumber(updateUserNumber);
		updateCommentDTO.setCommentPost(updateCommentPost);

		updateCommentDAO.updateComment(updateCommentDTO);

		Result result = new Result();
		result.setPath(request.getContextPath() + "/log/detail.lo?logNumber=" + updateLogNumber);
		result.setRedirect(true);
		return result;
	}
}