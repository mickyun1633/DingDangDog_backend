package com.ddd.app.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.admin.dao.AdminDAO;
import com.ddd.app.admin.dto.AdminInquiryDTO;

public class AdminInquiryListOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("===AdminInquiryListOkController 실행===");
		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		String answerStatus = request.getParameter("answerStatus");

		String temp = request.getParameter("page");
		int page = (temp == null) ? 1 : Integer.valueOf(temp); // 페이지 번호 기본값을 1로 설정하겠다
		// 한 페이지당 게시글 수
		int rowCount = 15;
		// 페이지 버튼 수
		int pageCount = 5;

		int startRow = (page - 1) * rowCount + 1; // 시작행(1, 11, 21, ..)
		int endRow = startRow + rowCount - 1; // 끝행(10, 20, 30, ..)

		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("answerStatus", answerStatus);

		List<AdminInquiryDTO> inquiryList = null;
		int total = 0;

		if ("N".equals(answerStatus)) {
			inquiryList = adminDAO.selectInquiryListByFilter(pageMap);
			total = adminDAO.getTotalInqByFilter();
		} else {
			inquiryList = adminDAO.selectInquiryList(pageMap);
			total = adminDAO.getTotalInq();
		}

		int realEndPage = (int) (Math.ceil(total / (double) rowCount));
		// 현재 페이지 그룹에서의 마지막 페이지
		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
		// 현재 페이지 그룹에서의 첫 페이지
		int startPage = endPage - (pageCount - 1);

		// endPage가 실제 존재하는 마지막 페이지보다 크면 조정
		endPage = Math.min(endPage, realEndPage);

		// prev, next 버튼 활성화여부 확인
		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("inquiryList", inquiryList);
		request.setAttribute("answerStatus", answerStatus);
		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		System.out.println("======페이징 정보 확인======");
		System.out.println("pageMap : " + pageMap);
		System.out.println("userList : " + inquiryList);
		System.out.println(
				"startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
		System.out.println("=========================");

		result.setPath("/app/admin/inquiry/admin_inquiry.jsp");
		result.setRedirect(false);

		return result;
	}

}
