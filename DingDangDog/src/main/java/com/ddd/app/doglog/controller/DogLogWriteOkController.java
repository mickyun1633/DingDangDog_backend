package com.ddd.app.doglog.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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
			System.out.println("세션에 userNumber 없음");
			Result result = new Result();
			result.setPath(request.getContextPath() + "/member/login.me");
			result.setRedirect(true);
			return result;
		}

		int userNumber = (Integer) userNumberObj;
		String logTitle = request.getParameter("logTitle");
		String logPost = request.getParameter("logPost");

		System.out.println("userNumber = " + userNumber);
		System.out.println("logTitle = " + logTitle);
		System.out.println("원본 logPost 길이 = " + (logPost == null ? 0 : logPost.length()));

		if (logTitle == null || logTitle.trim().isEmpty()
				|| logPost == null || logPost.trim().isEmpty()) {
			System.out.println("제목 또는 내용 비어 있음");
			Result result = new Result();
			result.setPath(request.getContextPath() + "/log/write.lo");
			result.setRedirect(true);
			return result;
		}

		String uploadPath = request.getServletContext().getRealPath("/upload/doglog");
		System.out.println("uploadPath = " + uploadPath);

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			boolean made = uploadDir.mkdirs();
			System.out.println("upload 폴더 생성 여부 = " + made);
		}

		Collection<Part> parts = request.getParts();
		System.out.println("parts 개수 = " + parts.size());

		List<LogImgDTO> imageList = new ArrayList<>();
		int imageIndex = 0;

		for (Part part : parts) {
			System.out.println("part name = " + part.getName());
			System.out.println("submittedFileName = " + part.getSubmittedFileName());
			System.out.println("size = " + part.getSize());

			if (!"logImages".equals(part.getName())) continue;
			if (part.getSize() == 0) continue;

			String submittedFileName = part.getSubmittedFileName();
			if (submittedFileName == null || submittedFileName.isBlank()) continue;

			String contentType = part.getContentType();
			if (contentType == null || !contentType.startsWith("image/")) continue;

			String ext = "";
			int dotIndex = submittedFileName.lastIndexOf(".");
			if (dotIndex != -1) {
				ext = submittedFileName.substring(dotIndex);
			}

			String savedFileName = UUID.randomUUID().toString() + ext;
			File saveFile = new File(uploadDir, savedFileName);

			System.out.println("저장 파일명 = " + savedFileName);
			System.out.println("저장 전체경로 = " + saveFile.getAbsolutePath());

			part.write(saveFile.getAbsolutePath());

			String dbImagePath = "/upload/doglog/" + savedFileName;
			String viewImagePath = request.getContextPath() + dbImagePath;

			LogImgDTO logImgDTO = new LogImgDTO();
			logImgDTO.setLogImgName(savedFileName);
			logImgDTO.setLogImgPath(dbImagePath);
			imageList.add(logImgDTO);

			String marker = "[[IMG_" + imageIndex + "]]";
			String imgTag = "<img src=\"" + viewImagePath + "\" alt=\"본문 이미지\">";
			logPost = logPost.replace(marker, imgTag);

			imageIndex++;
		}

		System.out.println("치환 후 logPost 길이 = " + (logPost == null ? 0 : logPost.length()));

		LogDTO logDTO = new LogDTO();
		logDTO.setUserNumber(userNumber);
		logDTO.setLogTitle(logTitle);
		logDTO.setLogPost(logPost);

		LogDAO logDAO = new LogDAO();
		logDAO.insert(logDTO);

		int logNumber = logDTO.getLogNumber();
		System.out.println("생성된 logNumber = " + logNumber);

		LogImgDAO logImgDAO = new LogImgDAO();

		for (LogImgDTO image : imageList) {
			image.setLogNumber(logNumber);
			logImgDAO.insertImage(image);
			System.out.println("DB 이미지 저장 완료");
		}

		Result result = new Result();
		result.setPath(request.getContextPath() + "/log/detail.lo?logNumber=" + logNumber);
		result.setRedirect(true);
		return result;

	
	}
}