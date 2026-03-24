package com.ddd.app.dogcare.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogcare.dao.CareDAO;

public class CareCancelController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=== CareCancelController 실행 ===");

		CareDAO careDAO = new CareDAO();
		Map<String, Integer> map = new HashMap<>();
		Result result = new Result();

		int careNumber = Integer.parseInt(request.getParameter("careNumber"));

		HttpSession session = request.getSession();
		Integer userNumber = (Integer) session.getAttribute("userNumber");

		if (userNumber == null) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/user/login.us");
			return result;
		}

		map.put("careNumber", careNumber);
		map.put("userNumber", userNumber);

		int cancelResult = careDAO.cancelApply(map);

		if (cancelResult > 0) {
			careDAO.reopenCare(careNumber);
		}

		result.setRedirect(true);
		result.setPath(request.getContextPath() + "/mypage/applyCareList.mp");

		return result;
	}
}