package com.ddd.app.inquiry.dto;

public class InquiryWriteDTO {

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

	private int userNumber;
	private String inquiryTitle;
	private String inquiryPost;
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
	@Override
	public String toString() {
		return "InquiryWriteDTO [userNumber=" + userNumber + ", inquiryTitle=" + inquiryTitle + ", inquiryPost="
				+ inquiryPost + "]";
	}

}
