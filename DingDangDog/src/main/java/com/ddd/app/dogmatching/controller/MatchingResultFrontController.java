package com.ddd.app.dogmatching.controller;

// ===== 멍! 매칭 Front =====
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ddd.app.Result;

public class MatchingResultFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ===== Encoding - UTF-8 =====
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String target = request.getRequestURI().substring(request.getContextPath().length());
		Result result = null;

		request.getSession().setAttribute("userNumber", 10001);
		// ===== 요청별 분기 처리 =====
		switch (target) {

		// ===== 설문 시작 =====
		case "/matching/test.ma": // 테스트 시작 페이지 이동
			result = new Result();
			result.setPath("/app/dogmatching/dogmatching.jsp");
			result.setRedirect(false);
			break;

		// ===== 매칭 제출 =====
		case "/matching/matchingOk.ma": // 설문 저장 로직 실행 및 결과 페이지로 이동
			result = new MatchingResultOkController().execute(request, response);
			break;

		// 결과 저장
		case "/matching/save.ma":
			result = new MatchingResultSaveController().execute(request, response);
			break;

		// 저장된 결과 다시보기
		case "/matching/result.ma":
			result = new MatchingResultController().execute(request, response);
			break;
		}

		// ===== 최종 페이지 이동 =====
		if (result != null && result.getPath() != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
}
}
	