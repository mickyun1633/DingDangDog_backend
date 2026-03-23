package com.ddd.app.inquiry.dto;

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

	private int inquiryNumber;
	private String inquiryTitle;
    private String inquiryDate;
	private String inquiryPost;
	private String answerStatus;
    private String answerDate;
	private String answerPost;
	private int userNumber;
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
	public String getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(String inquiryDate) {
		this.inquiryDate = inquiryDate;
	}
	public String getInquiryPost() {
		return inquiryPost;
	}
	public void setInquiryPost(String inquiryPost) {
		this.inquiryPost = inquiryPost;
	}
	public String getAnswerStatus() {
		return answerStatus;
	}
	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	public String getAnswerPost() {
		return answerPost;
	}
	public void setAnswerPost(String answerPost) {
		this.answerPost = answerPost;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	@Override
	public String toString() {
		return "InquiryDetailDTO [inquiryNumber=" + inquiryNumber + ", inquiryTitle=" + inquiryTitle + ", inquiryDate="
				+ inquiryDate + ", inquiryPost=" + inquiryPost + ", answerStatus=" + answerStatus + ", answerDate="
				+ answerDate + ", answerPost=" + answerPost + ", userNumber=" + userNumber + "]";
	}

}
