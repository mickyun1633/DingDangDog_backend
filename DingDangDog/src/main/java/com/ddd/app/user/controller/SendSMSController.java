package com.ddd.app.user.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.user.service.SmsService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SendSMSController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 파라미터 받기 (GET 방식)
		String phoneNumber = request.getParameter("realPhoneNumber");
		System.out.println("서버 수신 번호 : " + phoneNumber);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			// 번호 검증
			if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("{\"ok\": false, \"message\": \"전화번호 없음\"}");
				return null;
			}

			// SMS 발송
			SmsService smsService = new SmsService();
			String verificationCode = smsService.sendVerificationSms(phoneNumber);

			// 세션 저장
			HttpSession session = request.getSession();
			session.setAttribute("verificationCode", verificationCode);

			// 5. 성공 응답
			response.getWriter().write("{\"ok\": true}");
			return null;

		} catch (Exception e) {
			e.printStackTrace();

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("{\"ok\": false, \"message\": \"" + e.getMessage() + "\"}");
			return null;
		}
	}
}

//		// 응답 설정 (성공/실패 모두 JSON으로 응답하기 위해 미리 설정)
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        try {
//            // 1. JSON 데이터 읽기 (BufferedReader 사용)
//            BufferedReader reader = request.getReader();
//            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
//            
//            // 2. JS에서 보낸 'realPhoneNumber' 꺼내기
//            String phoneNumber = json.get("realPhoneNumber").getAsString();
//            
//            System.out.println("서버 수신 번호: " + phoneNumber);
//
//            SmsService smsService = new SmsService();
//            // SMS 발송 및 인증코드 생성
//            String verificationCode = smsService.sendVerificationSms(phoneNumber);
//
//            // 3. 세션에 인증 코드 저장
//            HttpSession session = request.getSession();
//            session.setAttribute("verificationCode", verificationCode);
//
//            // 4. 성공 응답 전송
//            response.getWriter().write("{\"ok\": true}");
//            return null; // AJAX 요청이므로 페이지 이동 없음
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 에러 발생 시 실패 응답 전송
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"ok\": false, \"message\": \"" + e.getMessage() + "\"}");
//            return null;
//        }
