package com.ddd.app.doglog.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.doglog.dao.LogDAO;
import com.ddd.app.doglog.dao.LogImgDAO;
import com.ddd.app.doglog.dto.LogDTO;
import com.ddd.app.doglog.dto.LogImgDTO;

public class DogLogEditOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("멍! 로그 수정 완료 진입");

		Integer loginUserNumber = (Integer) request.getSession().getAttribute("userNumber");
		if (loginUserNumber == null) {
			Result result = new Result();
			result.setPath(request.getContextPath() + "/member/login.me");
			result.setRedirect(true);
			return result;
		}

		int logNumber = Integer.parseInt(request.getParameter("logNumber"));
		String logTitle = request.getParameter("logTitle");
		String logPost = request.getParameter("logPost");
		String deleteImageIds = request.getParameter("deleteImageIds");

		LogDAO updateDAO = new LogDAO();
		LogDTO originLog = updateDAO.select(logNumber);

		if (originLog == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if (originLog.getUserNumber() != loginUserNumber) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		// 1. 게시글 수정
		LogDTO updateLog = new LogDTO();
		updateLog.setLogNumber(logNumber);
		updateLog.setLogTitle(logTitle);
		updateLog.setLogPost(logPost);
		updateDAO.update(updateLog);

		LogImgDAO logImgDAO = new LogImgDAO();

		// 2. 삭제할 기존 이미지 삭제
		if (deleteImageIds != null && !deleteImageIds.trim().isEmpty()) {
			String[] deleteIds = deleteImageIds.split(",");

			for (String id : deleteIds) {
				if (id != null && !id.trim().isEmpty()) {
					int logImgNumber = Integer.parseInt(id.trim());
					logImgDAO.deleteImageByImageNumber(logImgNumber);
				}
			}
		}

		// 3. 새 이미지 업로드 및 DB 저장
		Collection<Part> parts = request.getParts();
		String uploadPath = request.getServletContext().getRealPath("/upload/doglog");
		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

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