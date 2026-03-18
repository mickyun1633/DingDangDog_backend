package com.ddd.app.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.mypage.dao.MypageLogDAO;
import com.ddd.app.mypage.dto.MypageLogDTO;

public class MypageLogListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("내가 작성한 멍! 로그 목록");

		HttpSession session = request.getSession();
		Integer userNumber = (Integer) session.getAttribute("userNumber");

		Result result = new Result();

		MypageLogDAO logDAO = new MypageLogDAO();
		List<MypageLogDTO> logList = logDAO.selectMyLogs(userNumber);

		System.out.println("로그인 userNumber : " + userNumber);
		System.out.println("조회된 logList 개수 : " + logList.size());

		request.setAttribute("logList", logList);

		result.setPath("/app/mypage/common/review_list.jsp");
		result.setRedirect(false);
		return result;
	}
}