package com.ddd.app.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddd.app.Execute;
import com.ddd.app.Result;


public class InquiryWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 System.out.println("문의글 작성 페이지 컨트롤러 이동 완료");

		 Result result = new Result();
		 HttpSession session = request.getSession();
		 Integer userNumber = (Integer)session.getAttribute("userNumber");
		 String path = null;

		 if(userNumber == null) {
				path = request.getContextPath() +"/app/login/login.jsp";

				result.setPath(path);
				result.setRedirect(true);

			}else {
				path = "/app/mypage/common/support_write_common.jsp";

				result.setPath(path);
				result.setRedirect(false);

			}



			return result;




	}


}
