package com.ddd.app.mypage.dto;

public class MypageLogDTO {
	private int logNumber;
	private int userNumber;
	private String logTitle;
	private String logPost;
	private String logDate;
	
	
	public int getLogNumber() {
		return logNumber;
	}
	public void setLogNumber(int logNumber) {
		this.logNumber = logNumber;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public String getLogTitle() {
		return logTitle;
	}
	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}
	public String getLogPost() {
		return logPost;
	}
	public void setLogPost(String logPost) {
		this.logPost = logPost;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
	
}