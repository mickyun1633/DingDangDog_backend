package com.ddd.app.inquiry.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class InquiryListDTO {

//	-- 일반회원 문의목록조회
//	SELECT
//	    CASE
//	        WHEN ANSWER_STATUS = 'Y' THEN '문의 완료'
//	        WHEN ANSWER_STATUS = 'N' THEN '문의중'
//	    END AS ANSWER_STATUS_TEXT,
//	    INQUIRY_NUMBER,
//	    INQUIRY_TITLE,
//	    TO_CHAR(INQUIRY_DATE, 'YYYY-MM-DD') AS INQUIRY_DATE
//	FROM DDD_INQUIRY
//	WHERE USER_NUMBER = 10001
//	ORDER BY INQUIRY_NUMBER DESC;
//
//	SELECT * FROM DDD_INQUIRY;
	
//	-- 1:1 문의
//	CREATE TABLE ddd_inquiry(
//		inquiry_number NUMBER,
//		user_number NUMBER,
//		inquiry_title VARCHAR2(50) NOT NULL,
//		inquiry_post VARCHAR2(4000) NOT NULL,
//		inquiry_date DATE,
//		answer_status CHAR(1) DEFAULT 'N' CHECK (answer_status IN ('Y', 'N')) NOT NULL,
//	    answer_post VARCHAR2(4000),    
//	    answer_date DATE,
//	    CONSTRAINT pk_inquiry_inquiry_number PRIMARY KEY (inquiry_number),
//	    CONSTRAINT FK_inquiry_user_number FOREIGN KEY (user_number) REFERENCES ddd_user (user_number) ON DELETE CASCADE
//	);

	    private char answerStatus;
	    private String answerStatusText;
	    private int inquiryNumber;
	    private String inquiryTitle;
	    private LocalDateTime regDate;
	    private int userNumber;
	    
	    
		public char getAnswerStatus() {
			return answerStatus;
		}
		public void setAnswerStatus(char answerStatus) {
			this.answerStatus = answerStatus;
		}
		public String getAnswerStatusText() {
			return answerStatusText;
		}
		public void setAnswerStatusText(String answerStatusText) {
			this.answerStatusText = answerStatusText;
		}
		public int getInquiryNumber() {
			return inquiryNumber;
		}
		public void setInquiryNumber(int inquiryNumber) {
			this.inquiryNumber = inquiryNumber;
		}
		public String getInquiryTitle() {
			return inquiryTitle;
		}
		public void setInquiryTitle(String inquiryTitle) {
			this.inquiryTitle = inquiryTitle;
		}
		public LocalDateTime getRegDate() {
			return regDate;
		}
		public void setRegDate(LocalDateTime regDate) {
			this.regDate = regDate;
		}
		public int getUserNumber() {
			return userNumber;
		}
		public void setUserNumber(int userNumber) {
			this.userNumber = userNumber;
		}
		
		
		@Override
		public String toString() {
			return "InquiryListDTO [answerStatus=" + answerStatus + ", answerStatusText=" + answerStatusText
					+ ", inquiryNumber=" + inquiryNumber + ", inquiryTitle=" + inquiryTitle + ", regDate=" + regDate
					+ ", userNumber=" + userNumber + "]";
		}
	    
	    
	
		
	    
	    
		
	    
		
		
	    
	    
	    
	     
	     
	     
	
	
	
	
	
}
