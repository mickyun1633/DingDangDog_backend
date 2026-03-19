package com.ddd.app.header.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Result;
import com.ddd.app.main.controller.mainController;

/**
 * Servlet implementation class headerController
 */

public class mainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		System.out.println("LogFrontController 실행!!");

		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("현재 경로 : " + target);

		Result result = null;
		
		
		// 테스트용 하드코딩 세션
		request.getSession().setAttribute("userNumber", 10001);

		switch (target) {
		
		
//		case "/header/logIn.he":
//			System.out.println("로그인 요청");
//			result = new DogLogListController().execute(request, response);
//			break;
		
		
		case "/header/Main.he":
			System.out.println("메인 페이지 요청");
			result = new mainController().execute(request, response);
			break;
			
		case "/header/logOut.he":
			System.out.println("로그 아웃 요청");
			result = new HeaderLogOutOkController().execute(request, response);
			break;
		}
		
		if (result != null) {
		    if (result.isRedirect()) {
		        response.sendRedirect(result.getPath());
		    } else {
		        request.getRequestDispatcher(result.getPath()).forward(request, response);
		    }
		}
		
	}
	
}
