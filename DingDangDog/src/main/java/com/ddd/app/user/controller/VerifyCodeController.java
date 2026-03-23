package com.ddd.app.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class VerifyCodeController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			// JSON 파싱
			JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();

			String userCode = json.get("code").getAsString();
			System.out.println("입력 코드: " + userCode);

			HttpSession session = request.getSession();
			String sessionCode = (String) session.getAttribute("verificationCode");

			System.out.println("세션 코드: " + sessionCode);

			JsonObject responseJson = new JsonObject();

			// 2. 검증
			if (sessionCode != null && sessionCode.equals(userCode)) {
				responseJson.addProperty("success", true);

				// 인증 성공 시 세션 제거
				session.removeAttribute("verificationCode");

			} else {
				responseJson.addProperty("success", false);
			}

			response.getWriter().write(responseJson.toString());
			return null;

		} catch (Exception e) {
			e.printStackTrace();

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			JsonObject errorJson = new JsonObject();
			errorJson.addProperty("success", false);
			errorJson.addProperty("message", "서버 오류");

			response.getWriter().write(errorJson.toString());
			return null;
		}
	}

}
