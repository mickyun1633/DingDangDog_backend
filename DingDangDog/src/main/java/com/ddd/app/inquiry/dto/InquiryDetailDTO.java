package com.ddd.app.inquiry.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class InquiryDetailDTO {
//	SELECT
//    INQUIRY_NUMBER,
//    INQUIRY_TITLE,
//    TO_CHAR(INQUIRY_DATE, 'YYYY.MM.DD') AS INQUIRY_DATE,
//    INQUIRY_POST,
//    ANSWER_STATUS,
//    TO_CHAR(ANSWER_DATE, 'YYYY.MM.DD') AS ANSWER_DATE,
//    ANSWER_POST
//FROM DDD_INQUIRY
//WHERE INQUIRY_NUMBER = 1
//  AND USER_NUMBER = 10001;

private	int inquiryNumber;
private	String inquiryTitle;
private LocalDateTime inquiryDate;
private	String inquiryPost;
private	char answerStatus;
private String answerStatusText;
private	String answerPost;
private	Date regDate;

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
public LocalDateTime getInquiryDate() {
	return inquiryDate;
}
public void setInquiryDate(LocalDateTime inquiryDate) {
	this.inquiryDate = inquiryDate;
}
public String getInquiryPost() {
	return inquiryPost;
}
public void setInquiryPost(String inquiryPost) {
	this.inquiryPost = inquiryPost;
}
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
public String getAnswerPost() {
	return answerPost;
}
public void setAnswerPost(String answerPost) {
	this.answerPost = answerPost;
}
public Date getRegDate() {
	return regDate;
}
public void setRegDate(Date regDate) {
	this.regDate = regDate;
}

@Override
public String toString() {
	return "InquiryDetailDTO [inquiryNumber=" + inquiryNumber + ", inquiryTitle=" + inquiryTitle + ", inquiryDate="
			+ inquiryDate + ", inquiryPost=" + inquiryPost + ", answerStatus=" + answerStatus + ", answerStatusText="
			+ answerStatusText + ", answerPost=" + answerPost + ", regDate=" + regDate + "]";
}







	
	
}

