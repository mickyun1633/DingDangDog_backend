package com.ddd.app.doglog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.doglog.dao.LogCommentDAO;
import com.ddd.app.doglog.dto.LogCommentDTO;

public class DogLogCommentWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("댓글 작성 완료");

		int writeLogNumber = Integer.parseInt(request.getParameter("logNumber"));
		String commentPost = request.getParameter("commentPost");

		Integer writeUserNumber = (Integer) request.getSession().getAttribute("userNumber");

		if (writeUserNumber == null) {
			Result result = new Result();
			result.setPath(request.getContextPath() + "/member/login.me");
			result.setRedirect(true);
			return result;
		}

		LogCommentDTO writeCommentDTO = new LogCommentDTO();
		writeCommentDTO.setLogNumber(writeLogNumber);
		writeCommentDTO.setUserNumber(writeUserNumber);
		writeCommentDTO.setCommentPost(commentPost);

		LogCommentDAO writeCommentDAO = new LogCommentDAO();
		writeCommentDAO.insertComment(writeCommentDTO);

		Result result = new Result();
		result.setPath(request.getContextPath() + "/log/detail.lo?logNumber=" + writeLogNumber);
		result.setRedirect(true);
		return result;
	}
}