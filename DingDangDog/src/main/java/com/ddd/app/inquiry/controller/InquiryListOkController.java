package com.ddd.app.inquiry.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.inquiry.dao.InquiryDAO;
import com.ddd.app.inquiry.dto.InquiryListDTO;

public class InquiryListOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("======InquiryListOk 실행");
		InquiryDAO inquiryDAO = new InquiryDAO();
		Result result = new Result();

		Integer userNumber = (Integer) request.getSession().getAttribute("userNumber");
		System.out.println(userNumber + " 확인 ====");

		if (userNumber != null) {

			result.setPath("/app/mypage/common/support_list_common.jsp");
			result.setRedirect(false);
		} else {
			result.setPath(request.getContextPath() + "/app/login/login.jsp");
			result.setRedirect(true);
			return result;
		}


		String temp = request.getParameter("page");
		int page = (temp == null) ? 1 : Integer.valueOf(temp); // 페이지 번호
		int rowCount = 10; // 한 페이지당 문의 글 개수
		int pageCount = 5; // 페이지네이션 한 문의글에 보이는 버튼 개수

		int startRow = (page - 1) * rowCount + 1;
		int endRow = startRow + rowCount - 1;

		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("userNumber", userNumber);

		List<InquiryListDTO> inquiryList = inquiryDAO.selectAll(pageMap);
		System.out.println("-----------" + inquiryList);
		// DAO에서 select all 메서드 호출 -> 게시글 전체 조회
		request.setAttribute("inquiryList", inquiryList);

		int total = inquiryDAO.getTotal(userNumber);

		// 실제 마지막 페이지(전체 게시글 기준으로 계산)
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

		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		// endPage가 실제 존재하는 마지막 페이지보다 크면 조정
		endPage = Math.min(endPage, realEndPage);

		System.out.println("======페이징 정보 확인======");
		System.out.println("pageMap : " + pageMap);
		System.out.println("inquiryList : " + inquiryList);
		System.out.println(
				"startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
		System.out.println("=========================");

		return result;

	}

}
