package com.ddd.app.dogcare.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogcare.dao.CareDAO;
import com.ddd.app.dogcare.dto.CareApplyDTO;
import com.ddd.app.dogcare.dto.CareDetailDTO;

public class CareDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=== CareDetailController 실행 ===");

		CareDAO careDAO = new CareDAO();
		Result result = new Result();

		// 날짜 지난 글 상태 갱신
		careDAO.updateExpiredStatus();

		int careNumber = Integer.parseInt(request.getParameter("careNumber"));

		CareDetailDTO care = careDAO.selectCare(careNumber);
		List<CareApplyDTO> applyList = careDAO.selectApplyUsers(careNumber);
		String applyStatus = careDAO.getApplyStatus(careNumber);

		care.setApplyStatus(applyStatus);

		// 봉사날짜 지난 경우만 신고 가능
		boolean reportAvailable = false;
		if (care != null && care.getCareDate() != null && !care.getCareDate().isEmpty()) {
			LocalDate volunteerDate = LocalDate.parse(care.getCareDate());
			reportAvailable = volunteerDate.isBefore(LocalDate.now());
		}

		request.setAttribute("care", care);
		request.setAttribute("applyList", applyList);
		request.setAttribute("reportAvailable", reportAvailable);

		result.setPath("/app/dogcare/dogcare_detail_shelter.jsp");
		result.setRedirect(false);

		return result;
	}
}