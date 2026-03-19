package com.ddd.app.dogmatching.controller;

// ===== 멍! 매칭 데이터 저장 ===== 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogarchive.dto.ArchiveReadDTO;
import com.ddd.app.dogmatching.dao.MatchingResultDAO;
import com.ddd.app.dogmatching.dto.MatchingResultDTO;
import com.ddd.app.dogmatching.service.MatchingResultService;

public class MatchingResultOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 객체
		MatchingResultDAO dao = new MatchingResultDAO();
		MatchingResultService service = new MatchingResultService(dao);

		MatchingResultDTO dto = new MatchingResultDTO();
		Result result = new Result();

		// ===== 로그인한 userNumber 세션에서 확인 =====
		int userNumber = (int) request.getSession().getAttribute("userNumber");

		// ===== 데이터 수집 =====
		dto.setUserNumber(userNumber);
		dto.setDogActivity(Integer.parseInt(request.getParameter("dogActivity")));
		dto.setDogSociality(Integer.parseInt(request.getParameter("dogSociality")));
		dto.setDogIndependence(Integer.parseInt(request.getParameter("dogIndependence")));
		dto.setDogBarking(Integer.parseInt(request.getParameter("dogBarking")));
		dto.setDogGrooming(Integer.parseInt(request.getParameter("dogGrooming")));

		// ===== Service 호출 =====
		List<ArchiveReadDTO> recommendedDogs = service.getMatchingRecommendation(dto);

		// ===== 추천된 유기견 8마리 저장 =====
		request.setAttribute("recommendedDogs", recommendedDogs);

		// ===== 결과 화면 저장 =====
		result.setPath("/app/dogmatching/dogmatching_result.jsp");
		result.setRedirect(false);

		return result;

	}
}