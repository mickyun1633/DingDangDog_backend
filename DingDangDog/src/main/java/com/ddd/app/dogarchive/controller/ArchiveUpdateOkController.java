package com.ddd.app.dogarchive.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogarchive.dao.ArchiveDAO;
import com.ddd.app.dogarchive.dto.ArchiveUpdateDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 멍! 카이브 수정 처리 컨트롤러
 * 작성자: 윤철민 (맥/윈도우 공용 대응 버전)
 */
public class ArchiveUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 객체 준비
		ArchiveDAO dao = new ArchiveDAO();
		ArchiveUpdateDTO updateDTO = new ArchiveUpdateDTO();

		// 2. 운영체제 맞춤형 경로 설정 (맥/윈도우 호환)
		// 서버의 실제 루트 경로를 가져옵니다.
		String rootPath = request.getServletContext().getRealPath("/");
		
		// File.separator를 사용하여 맥(/)과 윈도우(\) 경로 구분자 문제를 해결합니다.
		String uploadPath = rootPath + File.separator + "upload";
		
		// 3. 물리적 창고(폴더)가 없는 경우 자동 생성 (Not a directory 에러 방지)
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs(); // 폴더가 없으면 즉시 생성
			System.out.println("--- [DingDongDog] 업로드 폴더 생성 완료: " + uploadPath);
		}

		int fileSize = 1024 * 1024 * 10; // 10MB 제한

		try {
			// 4. MultipartRequest 생성 (이제 폴더가 확실히 있으므로 안전하게 저장됩니다)
			MultipartRequest multi = new MultipartRequest(
					request, 
					uploadPath, 
					fileSize, 
					"UTF-8", 
					new DefaultFileRenamePolicy()
			);

			// 5. 데이터 추출 (request가 아닌 multi에서 꺼내야 함!)
			int dogNumber = Integer.parseInt(multi.getParameter("dogNumber"));
			String dogName = multi.getParameter("dogName");
			String dogBreed = multi.getParameter("dogBreed");
			String dogGender = multi.getParameter("dogGender");
			String dogAge = multi.getParameter("dogAge");
			double dogWeight = Double.parseDouble(multi.getParameter("dogWeight"));
			String dogDetail = multi.getParameter("dogDetail");

			// 성향 점수 추출
			int dogActivity = Integer.parseInt(multi.getParameter("dogActivity"));
			int dogSociality = Integer.parseInt(multi.getParameter("dogSociality"));
			int dogIndependence = Integer.parseInt(multi.getParameter("dogIndependence"));
			int dogBarking = Integer.parseInt(multi.getParameter("dogBarking"));
			int dogGrooming = Integer.parseInt(multi.getParameter("dogGrooming"));

			// 6. DTO 데이터 세팅
			updateDTO.setDogNumber(dogNumber);
			updateDTO.setDogName(dogName);
			updateDTO.setDogBreed(dogBreed);
			updateDTO.setDogGender(dogGender);
			updateDTO.setDogAge(dogAge);
			updateDTO.setDogWeight(dogWeight);
			updateDTO.setDogDetail(dogDetail);
			updateDTO.setDogActivity(dogActivity);
			updateDTO.setDogSociality(dogSociality);
			updateDTO.setDogIndependence(dogIndependence);
			updateDTO.setDogBarking(dogBarking);
			updateDTO.setDogGrooming(dogGrooming);

			// 7. 이미지 파일 정보 처리
			// JSP의 input name인 "dogImage"로 가져옵니다.
			String fileName = multi.getFilesystemName("dogImage"); 
			
			if (fileName != null) {
				updateDTO.setArchiveImgName(fileName);
				updateDTO.setArchiveImgPath("/upload/" + fileName);
			}

			// 8. DB 업데이트 실행 (DAO의 update 메서드 호출)
			dao.update(updateDTO);

			// 9. 비동기 응답 (JS Fetch API 대응)
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("{\"status\": \"success\", \"dogNumber\": " + dogNumber + "}");

		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("{\"status\": \"fail\"}");
		}

		return null;
	}
}