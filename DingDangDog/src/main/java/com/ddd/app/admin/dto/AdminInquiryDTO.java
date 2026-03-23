package com.ddd.app.admin.dto;

import java.sql.Date;

public class AdminInquiryDTO {

//	CREATE TABLE ddd_inquiry(
//		inquiry_number NUMBER,
//		user_number NUMBER,
//		inquiry_title VARCHAR2(50) NOT NULL,
//		inquiry_post VARCHAR2(4000) NOT NULL,
//		inquiry_date DATE DEFAULT sysdate,
//		answer_status CHAR(1) DEFAULT 'N' NOT NULL,
//	    answer_post VARCHAR2(4000),    
//	    answer_date DATE DEFAULT sysdate,
//	    CONSTRAINT PK_inquiry_number PRIMARY KEY (inquiry_number),
//	    CONSTRAINT FK_inquiry_user_number FOREIGN KEY (user_number) REFERENCES ddd_user (user_number) ON DELETE CASCADE,
//	    CONSTRAINT CK_inquiry_answer_status CHECK (answer_status IN ('Y', 'N'))
//	);

	private int inquiryNumber;
	private int userNumber;
	private String inquiryTitle;
	private String inquiryPost;
	private Date inquiryDate;
	private String answerStatus;
	private String answerPost;
	private Date answerDate;
	private String userNickname;

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

	public Date getInquiryDate() {
		return inquiryDate;
	}

	public void setInquiryDate(Date inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	public String getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}

	public String getAnswerPost() {
		return answerPost;
	}

	public void setAnswerPost(String answerPost) {
		this.answerPost = answerPost;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	@Override
	public String toString() {
		return "AdminInquiryDTO [inquiryNumber=" + inquiryNumber + ", userNumber=" + userNumber + ", inquiryTitle="
				+ inquiryTitle + ", inquiryPost=" + inquiryPost + ", inquiryDate=" + inquiryDate + ", answerStatus="
				+ answerStatus + ", answerPost=" + answerPost + ", answerDate=" + answerDate + ", userNickname="
				+ userNickname + "]";
	}

}
