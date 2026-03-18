package com.ddd.app.doglog.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.doglog.dao.LogDAO;
import com.ddd.app.doglog.dao.LogImgDAO;
import com.ddd.app.doglog.dto.LogDTO;
import com.ddd.app.doglog.dto.LogImgDTO;

public class DogLogWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("멍! 로그 작성 완료 진입");

		HttpSession session = request.getSession();
		Object userNumberObj = session.getAttribute("userNumber");

		if (userNumberObj == null) {
			Result result = new Result();
			result.setPath(request.getContextPath() + "/member/login.me");
			result.setRedirect(true);
			return result;
		}

		int userNumber = (Integer) userNumberObj;
		String logTitle = request.getParameter("logTitle");
		String logPost = request.getParameter("logPost");

		if (logTitle == null || logTitle.trim().isEmpty()
				|| logPost == null || logPost.trim().isEmpty()) {
			Result result = new Result();
			result.setPath(request.getContextPath() + "/log/write.lo");
			result.setRedirect(true);
			return result;
		}

		// 1. 게시글 저장
		LogDTO logDTO = new LogDTO();
		logDTO.setUserNumber(userNumber);
		logDTO.setLogTitle(logTitle);
		logDTO.setLogPost(logPost);

		LogDAO logDAO = new LogDAO();
		logDAO.insert(logDTO);

		int logNumber = logDTO.getLogNumber();

		// 2. 이미지 저장
		LogImgDAO logImgDAO = new LogImgDAO();

		String uploadPath = request.getServletContext().getRealPath("/upload/doglog");
		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		Collection<Part> parts = request.getParts();

		for (Part part : parts) {
			if (!"logImages".equals(part.getName())) continue;
			if (part.getSize() == 0) continue;

			String submittedFileName = part.getSubmittedFileName();
			if (submittedFileName == null || submittedFileName.isBlank()) continue;

			String savedFileName = System.currentTimeMillis() + "_" + submittedFileName;
			File saveFile = new File(uploadDir, savedFileName);
			part.write(saveFile.getAbsolutePath());

			LogImgDTO logImgDTO = new LogImgDTO();
			logImgDTO.setLogNumber(logNumber);
			logImgDTO.setLogImgPath("/upload/doglog/" + savedFileName);

			logImgDAO.insertImage(logImgDTO);
		}

		Result result = new Result();
		result.setPath(request.getContextPath() + "/log/detail.lo?logNumber=" + logNumber);
		result.setRedirect(true);
		return result;
	}
}