package com.ddd.app.dogmatching.controller;

// ===== 멍! 매칭 데이터 저장 ===== 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogarchive.dto.ArchiveListDTO;
import com.ddd.app.dogmatching.dao.MatchingResultDAO;
import com.ddd.app.dogmatching.dto.MatchingResultDTO;
import com.ddd.app.dogmatching.service.MatchingResultService;

public class MatchingResultOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 객체
		MatchingResultDAO dao = new MatchingResultDAO();
		MatchingResultService service = new MatchingResultService(dao);

		MatchingResultDTO dto = new MatchingResultDTO();
		Result result = new Result();

		// ===== 로그인한 userNumber 세션에서 확인 =====
		int userNumber = (int) request.getSession().getAttribute("userNumber");

		// ===== 데이터 수집 =====
		dto.setUserNumber(userNumber);
		dto.setDogActivity(Integer.parseInt(request.getParameter("dogActivity")));
		dto.setDogSociality(Integer.parseInt(request.getParameter("dogSociality")));
		dto.setDogIndependence(Integer.parseInt(request.getParameter("dogIndependence")));
		dto.setDogBarking(Integer.parseInt(request.getParameter("dogBarking")));
		dto.setDogGrooming(Integer.parseInt(request.getParameter("dogGrooming")));

		// ===== Service 호출 =====
		List<ArchiveListDTO> recommendedDogs = service.getMatchingRecommendation(dto);
		List<Integer> topDogNumbers = new ArrayList<>();

		for (ArchiveListDTO dog : recommendedDogs) {
			topDogNumbers.add(dog.getDogNumber());
		}
		

		// ===== 결과 문구 생성 =====
		String resultComment = makeResultComment(dto);

		// ===== 추천된 유기견 8마리 전달 및 코멘트 전달 =====
		request.setAttribute("recommendedDogs", recommendedDogs);
		request.setAttribute("resultComment", resultComment);
		
		// ===== 추천된 유기견 8마리 저장용 세션에 저장 =====
		request.getSession().setAttribute("matchingResultDTO", dto);
		request.getSession().setAttribute("topDogNumbers", topDogNumbers);

		// ===== 결과 화면 저장 =====
		result.setPath("/app/dogmatching/dogmatching_result.jsp");
		result.setRedirect(false);

		return result;

	}
	
	private String makeResultComment(MatchingResultDTO dto) {
	    double activityAvg = (double) dto.getDogActivity() / 4;
	    double socialityAvg = (double) dto.getDogSociality() / 4;
	    double independenceAvg = (double) dto.getDogIndependence() / 4;
	    double barkingAvg = (double) dto.getDogBarking() / 4;
	    double groomingAvg = (double) dto.getDogGrooming() / 4;

	    String activityComment = getActivityComment(activityAvg);
	    String socialityComment = getSocialityComment(socialityAvg);
	    String independenceComment = getIndependenceComment(independenceAvg);
	    String barkingComment = getBarkingComment(barkingAvg);
	    String groomingComment = getGroomingComment(groomingAvg);

	    return "입력한 응답을 보면 "
	            + activityComment + ", "
	            + socialityComment +"<br>"
	            +". 또한 "
	            + independenceComment + ", "
	            + barkingComment + ", "+"<br>"
	            + groomingComment + ".";
		}	
	
		private String getActivityComment(double avg) {
		    if (avg >= 4.0) return "활동량이 있는 반려견과 잘 맞는 편이고";
		    if (avg >= 2.5) return "적당한 활동성을 가진 반려견을 선호하는 편이고";
		    return "차분하고 무리한 활동이 적은 반려견이 더 잘 맞는 편이고";
		}
	
		private String getSocialityComment(double avg) {
		    if (avg >= 4.0) return "사회성이 좋은 성향을 중요하게 보는 편이에요";
		    if (avg >= 2.5) return "무난한 사회성을 가진 반려견과 잘 맞는 편이에요";
		    return "조용하고 낯선 환경에 크게 예민하지 않은 성향이 더 잘 맞는 편이에요";
		}
	
		private String getIndependenceComment(double avg) {
		    if (avg >= 4.0) return "혼자 있는 시간에도 안정적인 성향과 잘 맞아요";
		    if (avg >= 2.5) return "독립성과 교감의 균형이 있는 성향을 선호하는 편이에요";
		    return "보호자와의 교감이 많은 성향을 더 선호하는 편이에요";
		}
	
		private String getBarkingComment(double avg) {
		    if (avg >= 4.0) return "생활 소음 부담이 적은 조용한 친구들을 중요하게 보셨어요";
		    if (avg >= 2.5) return "짖음 정도가 과하지 않은 무난한 성향을 선호하는 편이에요";
		    return "짖음에 대해서는 비교적 유연한 편이에요";
		}
	
		private String getGroomingComment(double avg) {
		    if (avg >= 4.0) return "털 관리 부담이 적은 친구들이 더 잘 맞아요";
		    if (avg >= 2.5) return "그루밍 부담이 과하지 않은 친구들을 선호하는 편이에요";
		    return "털관리나 미용에 어느 정도 시간을 들일 수 있는 편이에요";
		}
}