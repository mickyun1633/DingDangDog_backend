package com.ddd.app.inquiry.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class InquiryWritingDTO {

//	-- 문의 작성
//	INSERT INTO DDD_INQUIRY (
//	    INQUIRY_NUMBER,
//	    USER_NUMBER,
//	    INQUIRY_TITLE,
//	    INQUIRY_POST,
//	    INQUIRY_DATE,
//	    ANSWER_STATUS
//	) VALUES (
//	    SEQ_INQUIRY.NEXTVAL,
//	    10001,
//	    '유기견 등록 문의',
//	    '일반회원은 등록할 수 없나요??',
//	    SYSDATE,
//	    'N'
//	);

	private int inquiryNumber;
	private int userNumber;
	private String inquiryTitle;
	private String inquiryPost;
	private LocalDateTime regDate;
	private char answerStatusText;
	
	public int getInquiryNumber() {
		return inquiryNumber;
	}
	public void setInquiryNumber(int inquiryNumber) {
		this.inquiryNumber = inquiryNumber;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public String getInquiryTitle() {
		return inquiryTitle;
	}
	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}
	public String getInquiryPost() {
		return inquiryPost;
	}
	public void setInquiryPost(String inquiryPost) {
		this.inquiryPost = inquiryPost;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public char getAnswerStatusText() {
		return answerStatusText;
	}
	public void setAnswerStatusText(char answerStatusText) {
		this.answerStatusText = answerStatusText;
	}
	
	
	@Override
	public String toString() {
		return "InquiryWritingDTO [inquiryNumber=" + inquiryNumber + ", userNumber=" + userNumber + ", inquiryTitle="
				+ inquiryTitle + ", inquiryPost=" + inquiryPost + ", regDate=" + regDate + ", answerStatusText="
				+ answerStatusText + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
