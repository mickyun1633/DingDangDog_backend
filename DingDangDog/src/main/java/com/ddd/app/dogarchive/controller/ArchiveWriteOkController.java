package com.ddd.app.dogarchive.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogarchive.dao.ArchiveDAO;
import com.ddd.app.dogarchive.dto.ArchiveWriteDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ArchiveWriteOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Result result = new Result();
		ArchiveDAO dao = new ArchiveDAO();
		ArchiveWriteDTO writeDTO = new ArchiveWriteDTO();
		HttpSession session = request.getSession();

		// 1. [필살기] 팀원 누구의 컴퓨터에서도 작동하는 동적 경로 설정
		String webappRoot = request.getServletContext().getRealPath("/"); 
		String relativePath = "upload/archive/test/"; 
		String uploadPath = webappRoot + relativePath;

		// 2. [자동화] 폴더가 없으면 코드가 알아서 생성 (팀원들끼리 폴더 맞출 필요 없음)
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs(); // 하위 폴더까지 싹 다 만듭니다.
			System.out.println("--- [시스템] 저장 폴더 자동 생성 완료: " + uploadPath);
		}

		int fileSize = 20 * 1024 * 1024; // 20MB 제한

		try {
			// 3. MultipartRequest 객체 생성 (이미지 업로드 실행)
			MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());

			// 4. 데이터 추출 (request가 아니라 multi에서 꺼내야 값이 null이 아닙니다!)
			writeDTO.setDogName(multi.getParameter("dogName"));
			writeDTO.setDogBreed(multi.getParameter("dogBreed"));
			writeDTO.setDogGender(multi.getParameter("dogGender"));

			// 나이와 몸무게 처리 (getIntValue/getDoubleValue 사용)
			writeDTO.setDogAge(String.valueOf(getIntValue(multi.getParameter("dogAge"))));
			writeDTO.setDogWeight(getDoubleValue(multi.getParameter("dogWeight")));

			// 상세 설명 (에디터 내용)
			writeDTO.setDogDetail(multi.getParameter("dogContent"));

			// 5. 날짜 데이터 처리
			String dogSafeDateStr = multi.getParameter("dogRescueDate"); 
			if (dogSafeDateStr != null && !dogSafeDateStr.trim().equals("")) {
				writeDTO.setDogSafeDate(LocalDateTime.parse(dogSafeDateStr + "T00:00:00"));
			} else {
				writeDTO.setDogSafeDate(LocalDateTime.now());
			}

			// 6. 성향 점수 세팅
			writeDTO.setDogActivity(getIntValue(multi.getParameter("scoreActivity")));
			writeDTO.setDogSociality(getIntValue(multi.getParameter("scoreSocial")));
			writeDTO.setDogIndependence(getIntValue(multi.getParameter("scoreIndependence")));
			writeDTO.setDogBarking(getIntValue(multi.getParameter("scoreBark")));
			writeDTO.setDogGrooming(getIntValue(multi.getParameter("scoreGrooming")));

			// 7. 이미지 정보 저장 (파일명만 세팅)
			writeDTO.setArchiveImgName(multi.getFilesystemName("dogImage"));
			writeDTO.setArchiveImgPath("/" + relativePath); // 웹에서 접근 가능한 경로로 저장

			// 8. 회원정보 (세션)
			Object userNumObj = session.getAttribute("userNumber");
			writeDTO.setUserNumber(userNumObj != null ? Integer.parseInt(String.valueOf(userNumObj)) : 1);

			// 9. DB 저장 실행
			System.out.println("--- [DB 저장 시도] ---");
			System.out.println("데이터 확인: " + writeDTO.toString());

			dao.insert(writeDTO);

			System.out.println("--- [DB 저장 성공!] ---");

			// 성공 시 리스트 페이지로 이동
			result.setPath(request.getContextPath() + "/archive/list.ar");
			result.setRedirect(true);

		} catch (Exception e) {
			System.err.println("!!! ArchiveWriteOkController 에러 !!!");
			e.printStackTrace();
			// 에러 발생 시 다시 작성 페이지로 (또는 에러 페이지로)
			result.setPath(request.getContextPath() + "/archive/write.ar");
			result.setRedirect(true);
		}
		return result;
	}

	// 문자열을 안전하게 int로 변환하는 메서드
	private int getIntValue(String value) {
		if (value == null || value.trim().equals("")) return 0;
		try { return Integer.parseInt(value); } catch (Exception e) { return 0; }
	}

	// 문자열을 안전하게 double로 변환하는 메서드
	private double getDoubleValue(String value) {
		if (value == null || value.trim().equals("")) return 0.0;
		try { return Double.parseDouble(value); } catch (Exception e) { return 0.0; }
	}
}