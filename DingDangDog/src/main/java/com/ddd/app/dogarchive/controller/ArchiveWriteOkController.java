package com.ddd.app.dogarchive.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogarchive.dao.ArchiveDAO;
import com.ddd.app.dogarchive.dto.ArchiveWriteDTO;

public class ArchiveWriteOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Result result = new Result();
		ArchiveDAO dao = new ArchiveDAO();
		ArchiveWriteDTO writeDTO = new ArchiveWriteDTO();
		HttpSession session = request.getSession();

		try {
			// ===== 1. 기본 데이터 (숫자형 데이터는 반드시 getIntValue/getDoubleValue 사용) =====
			writeDTO.setDogName(request.getParameter("dogName"));
			writeDTO.setDogBreed(request.getParameter("dogBreed"));
			writeDTO.setDogGender(request.getParameter("dogGender"));

			// ORA-01722 방지: 나이를 int로 변환해서 세팅
			writeDTO.setDogAge(String.valueOf(getIntValue(request.getParameter("dogAge"))));
			// 만약 DTO의 dogAge가 int라면
			// writeDTO.setDogAge(getIntValue(request.getParameter("dogAge")));

			writeDTO.setDogWeight(getDoubleValue(request.getParameter("dogWeight")));
			writeDTO.setDogDetail(request.getParameter("dogDetail"));

			// ===== 2. 날짜 데이터 처리 (형식: yyyy-MM-dd 대응) =====
			String dogSafeDateStr = request.getParameter("DogSafeDate");
			if (dogSafeDateStr != null && !dogSafeDateStr.trim().equals("")) {
			    try {
			        // 로그에 찍힌 2026-03-22 형식을 파싱
			        LocalDate localDate = LocalDate.parse(dogSafeDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			        // DB 전송을 위해 java.sql.Date로 변환 (시분초 제외하고 깔끔하게 날짜만)
			        writeDTO.setDogSafeDate(localDate.atStartOfDay()); 
			    } catch (Exception e) {
			        System.out.println("날짜 파싱 실패 (전달값: " + dogSafeDateStr + ")");
			        writeDTO.setDogSafeDate(LocalDateTime.now());
			    }
			} else {
			    writeDTO.setDogSafeDate(LocalDateTime.now());
			}

			// ===== 3. 성향 점수 (안전하게 int 변환) =====
			writeDTO.setDogActivity(getIntValue(request.getParameter("dogActivity")));
			writeDTO.setDogSociality(getIntValue(request.getParameter("dogSociality")));
			writeDTO.setDogIndependence(getIntValue(request.getParameter("dogIndependence")));
			writeDTO.setDogBarking(getIntValue(request.getParameter("dogBarking")));
			writeDTO.setDogGrooming(getIntValue(request.getParameter("dogGrooming")));

			// ===== 4. 이미지 정보 =====
			writeDTO.setArchiveImgName(request.getParameter("archiveImgName"));
			writeDTO.setArchiveImgPath("/upload/archive/test/");

			// ===== 5. 회원정보 (세션) =====
			Object userNumObj = session.getAttribute("userNumber");
			writeDTO.setUserNumber(userNumObj != null ? Integer.parseInt(String.valueOf(userNumObj)) : 1);

			// ===== 6. DB 저장 실행 =====
			System.out.println("--- [DB 저장 시도] ---");
			System.out.println("데이터 확인: " + writeDTO.toString());

			dao.insert(writeDTO);

			System.out.println("--- [DB 저장 성공!] ---");

			result.setPath(request.getContextPath() + "/archive/list.ar");
			result.setRedirect(true);

		} catch (Exception e) {
			System.err.println("!!! ArchiveWriteOkController 에러 !!!");
			e.printStackTrace();
			result.setPath(request.getContextPath() + "/archive/write.ar");
			result.setRedirect(true);
		}
		return result;
	}

	// 문자열을 안전하게 int로 변환하는 메서드
	private int getIntValue(String value) {
		if (value == null || value.trim().equals(""))
			return 0;
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}

	// 문자열을 안전하게 double로 변환하는 메서드
	private double getDoubleValue(String value) {
		if (value == null || value.trim().equals(""))
			return 0.0;
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return 0.0;
		}
	}
}