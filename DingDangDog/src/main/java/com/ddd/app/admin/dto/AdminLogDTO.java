package com.ddd.app.admin.dto;

import java.time.LocalDateTime;

public class AdminLogDTO {
	//log_number
	//log_title
	//user_id
	//user_nickname
	//log_date
	//user_number
	//log_post
	//comment_number
	//comment_post
	//comment_date
	//log_img_number
	//log_img_name
	//log_img_path
	
	private int logNumber;
	private String logTitle;
	private LocalDateTime logDate;
	private String logPost;

	private int logImgNumber;
	private String logImgName;
	private String logImgPath;

	private String userId;
	private String userNickname;
	private int userNumber;

	private int commentNumber;
	private String commentPost;
	private LocalDateTime commentDate;
	
	public int getLogNumber() {
		return logNumber;
	}

	public void setLogNumber(int logNumber) {
		this.logNumber = logNumber;
	}

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public LocalDateTime getLogDate() {
		return logDate;
	}

	public void setLogDate(LocalDateTime logDate) {
		this.logDate = logDate;
	}

	public String getLogPost() {
		return logPost;
	}

	public void setLogPost(String logPost) {
		this.logPost = logPost;
	}

	public int getLogImgNumber() {
		return logImgNumber;
	}

	public void setLogImgNumber(int logImgNumber) {
		this.logImgNumber = logImgNumber;
	}

	public String getLogImgName() {
		return logImgName;
	}

	public void setLogImgName(String logImgName) {
		this.logImgName = logImgName;
	}

	public String getLogImgPath() {
		return logImgPath;
	}

	public void setLogImgPath(String logImgPath) {
		this.logImgPath = logImgPath;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public String getCommentPost() {
		return commentPost;
	}

	public void setCommentPost(String commentPost) {
		this.commentPost = commentPost;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "AdminLogDTO [logNumber=" + logNumber + ", logTitle=" + logTitle + ", logDate=" + logDate + ", logPost="
				+ logPost + ", logImgNumber=" + logImgNumber + ", logImgName=" + logImgName + ", logImgPath="
				+ logImgPath + ", userId=" + userId + ", userNickname=" + userNickname + ", userNumber=" + userNumber
				+ ", commentNumber=" + commentNumber + ", commentPost=" + commentPost + ", commentDate=" + commentDate
				+ "]";
	}
}
