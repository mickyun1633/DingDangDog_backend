package com.ddd.app.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddd.app.Execute;
import com.ddd.app.Result;
import com.ddd.app.inquiry.dao.InquiryDAO;
import com.ddd.app.inquiry.dto.InquiryDetailDTO;


public class InquiryReadOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("문의 상세 페이지 컨트롤러 진입");

        Result result = new Result();
        InquiryDAO inquiryDAO = new InquiryDAO();

        // 로그인 체크
        Integer userNumber = (Integer) request.getSession().getAttribute("userNumber");

        if (userNumber == null) {
            System.out.println("로그인 안됨");
            result.setPath(request.getContextPath() + "/app/login/login.jsp");
            result.setRedirect(true);
            return result;
        }

        // 파라미터 받기
        String inquiryNumberStr = request.getParameter("inquiryNumber");

        if (inquiryNumberStr == null || inquiryNumberStr.trim().isEmpty()) {
            System.out.println("inquiryNumber 값 없음");
            result.setPath(request.getContextPath() + "/inquiry/inquiryListOk.in");
            result.setRedirect(true);
            return result;
        }

        int inquiryNumber = Integer.parseInt(inquiryNumberStr);
        System.out.println("문의번호 : " + inquiryNumber);

        // DTO 세팅
        InquiryDetailDTO detailDTO = new InquiryDetailDTO();
        detailDTO.setInquiryNumber(inquiryNumber);
        detailDTO.setUserNumber(userNumber);

        // DB 조회
        InquiryDetailDTO inquiry = inquiryDAO.selectInquiry(detailDTO);

        // 조회 결과 체크
        if (inquiry == null) {
            System.out.println("조회 결과 없음");
            result.setPath(request.getContextPath() + "/inquiry/inquiryListOk.in");
            result.setRedirect(true);
            return result;
        }

        // request에 담기
        request.setAttribute("inquiry", inquiry);

        // forward 방식
        result.setPath("/app/mypage/common/support_detail_common.jsp");
        result.setRedirect(false);

        return result;
    }
}
