package com.ddd.app.inquiry.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Result;

/**
 * Servlet implementation class inquiryFrontController
 */
public class InquiryFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("InquiryFrontController 현재 경로 : " + target);
		Result result = new Result();
		
		
		switch(target) {
		case "/inquiry/inquirylistOk.in":
			System.out.println("문의 목록 처리 요청");
			result = new InquiryListOkController().execute(request,response);
			System.out.println("문의 목록 처리 완료");
			break;
		case "/inquiry/inquiryReadOk.in":
			System.out.println("문의 상세 페이지 이동 요청");
			result = new InquiryReadOkController().execute(request,response);
			System.out.println("문의 상세 페이지 이동 완료");
			break;
			
		case "/inquiry/inquiryWrite.in":
			System.out.println("문의 작성 페이지 이동 요청");
			result = new InquiryWriteController().execute(request,response);
			System.out.println("문의 작성 페이지 이동 완료");
			break;
			
		case "/inquiry/inquiryWriteOk.in":
			System.out.println("문의 작성 등록 요청");
			result = new InquiryWriteOkController().execute(request,response);
			System.out.println("문의 작성 등록 완료");
			break;
		case "/inquiry/inquiryDeleteOk.in":
			System.out.println("문의 삭제 요청");
			result = new InquiryDeleteOkController().execute(request,response);
			System.out.println("문의 삭제 완료");
			break;
		
		case "/inquiry/inquiryAnswerOk.in":
			System.out.println("문의 답변 상태 요청");
			result = new InquiryAnswerOkController().execute(request,response);
			System.out.println("문의 답변 완료");
			break;
		
	}
		if(result != null && result.getPath() != null) {
			if(result.isRedirect()) {	
				response.sendRedirect(result.getPath());
				}else { 
					request.getRequestDispatcher(result.getPath()).forward(request,response);
				
				}
			}
		}
}
