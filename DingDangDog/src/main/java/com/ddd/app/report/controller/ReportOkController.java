package com.ddd.app.report.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogcare.dao.CareDAO;
import com.ddd.app.dogcare.dto.CareDetailDTO;
import com.ddd.app.report.dao.ReportDAO;
import com.ddd.app.report.dto.ReportDTO;

public class ReportOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("=== ReportOkController 실행 ===");

		Result result = new Result();
		ReportDAO reportDAO = new ReportDAO();
		CareDAO careDAO = new CareDAO();
		HttpSession session = request.getSession();

		try {
			Integer reporterUserNumber = (Integer) session.getAttribute("userNumber");

			if (reporterUserNumber == null) {
				result.setPath(request.getContextPath() + "/user/login.us");
				result.setRedirect(true);
				return result;
			}

			int careNumber = Integer.parseInt(request.getParameter("careNumber"));
			int reportedUserNumber = Integer.parseInt(request.getParameter("userNumber"));
			int applyNumber = Integer.parseInt(request.getParameter("applyNumber"));

			String reason = request.getParameter("reason");
			String etcReason = request.getParameter("etcReason");

			// 봉사 날짜 지난 후에만 신고 가능
			CareDetailDTO care = careDAO.selectCare(careNumber);
			if (care == null || care.getCareDate() == null || care.getCareDate().isEmpty()) {
				session.setAttribute("reportMessage", "잘못된 접근입니다.");
				result.setPath(request.getContextPath() + "/care/detail.ca?careNumber=" + careNumber);
				result.setRedirect(true);
				return result;
			}

			LocalDate volunteerDate = LocalDate.parse(care.getCareDate());
			if (!volunteerDate.isBefore(LocalDate.now())) {
				session.setAttribute("reportMessage", "신고는 봉사날짜가 지난 후에만 가능합니다.");
				result.setPath(request.getContextPath() + "/care/detail.ca?careNumber=" + careNumber);
				result.setRedirect(true);
				return result;
			}

			// 중복 신고 체크
			int duplicateCount = reportDAO.checkDuplicateReport(reporterUserNumber, applyNumber);
			if (duplicateCount > 0) {
				session.setAttribute("reportMessage", "이 게시글에서 이미 신고된 회원입니다.");
				result.setPath(request.getContextPath() + "/care/detail.ca?careNumber=" + careNumber);
				result.setRedirect(true);
				return result;
			}

			String finalReason = reason;
			if ("기타".equals(reason)) {
				if (etcReason == null || etcReason.trim().isEmpty()) {
					session.setAttribute("reportMessage", "기타 사유를 입력해주세요.");
					result.setPath(request.getContextPath() + "/care/detail.ca?careNumber=" + careNumber);
					result.setRedirect(true);
					return result;
				}
				finalReason = "기타: " + etcReason.trim();
			}

			ReportDTO reportDTO = new ReportDTO();
			reportDTO.setReporterUserNumber(reporterUserNumber);
			reportDTO.setReportedUserNumber(reportedUserNumber);
			reportDTO.setApplyNumber(applyNumber);
			reportDTO.setReportReason(finalReason);

			int insertResult = reportDAO.insertReport(reportDTO);

			if (insertResult > 0) {
				reportDAO.reportCount(reportedUserNumber);
				reportDAO.updateBlacklist(reportedUserNumber);
				session.setAttribute("reportMessage", "신고 완료되었습니다.");
			} else {
				session.setAttribute("reportMessage", "신고 처리에 실패했습니다.");
			}

			result.setPath(request.getContextPath() + "/care/detail.ca?careNumber=" + careNumber);
			result.setRedirect(true);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("reportMessage", "신고 처리 중 오류가 발생했습니다.");
			result.setPath(request.getContextPath() + "/care/list.ca");
			result.setRedirect(true);
		}

		return result;
	}
}