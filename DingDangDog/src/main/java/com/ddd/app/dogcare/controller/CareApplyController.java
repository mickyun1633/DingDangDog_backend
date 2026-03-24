package com.ddd.app.dogcare.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.dogcare.dao.CareDAO;
import com.ddd.app.dogcare.dto.CareApplyDTO;

public class CareApplyController implements Execute {

    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("=== CareApplyController 실행 ===");

        CareDAO careDAO = new CareDAO();
        Result result = new Result();
        
        // 1. 게시글 번호 받기 (userNumber는 파라미터로 받지 않음)
        int careNumber = Integer.parseInt(request.getParameter("careNumber"));
        
        // 2. 세션에서 userNumber 가져오기 (가장 먼저 확인!)
        Integer sessionUserNumber = (Integer) request.getSession().getAttribute("userNumber");
        
        // 3. 비회원 체크 (세션이 null인 경우)
        if (sessionUserNumber == null) {
            // 비회원인 경우, alertMessage를 설정하고 상세 페이지로 포워드
            request.setAttribute("alertMessage", "일반회원만 이용 가능한 서비스입니다. 로그인 후 이용해주세요.");
            
            // 참고: 바로 로그인 페이지로 보내고 싶다면 아래 경로를 "/login.jsp" 등으로 수정하세요.
            result.setPath("/care/detail.ca?careNumber=" + careNumber); 
            result.setRedirect(false); 
            return result;
        }

        // 4. 로그인된 사용자라면 DTO 세팅 (세션 값을 그대로 사용 - 보안상 안전)
        CareApplyDTO dto = new CareApplyDTO();
        int userNumber = sessionUserNumber; 
        
        dto.setCareNumber(careNumber);
        dto.setUserNumber(userNumber);

        // 5. 중복 신청 체크
        int count = careDAO.checkDuplicateApply(careNumber, userNumber);
        
        if (count > 0) {
            request.setAttribute("alertMessage", "이미 신청된 봉사입니다.");
            result.setPath("/care/detail.ca?careNumber=" + careNumber);
            result.setRedirect(false);
            return result;
        }

        // 6. 봉사 신청 처리
        int applyResult = careDAO.applyCare(dto);

        if (applyResult == 0) {
            request.setAttribute("alertMessage", "모집이 마감되었거나 신청할 수 없는 상태입니다.");
            result.setPath("/care/detail.ca?careNumber=" + careNumber);
            result.setRedirect(false);
            return result;
        }

        // 신청 후 현재 인원 기준으로 상태 재계산
        careDAO.syncCareStatus(careNumber);

        // 신청 완료 메시지 설정
        request.setAttribute("alertMessage", "신청 완료되었습니다.");
        result.setPath("/care/detail.ca?careNumber=" + careNumber);
        result.setRedirect(false);

        return result;
    }
}