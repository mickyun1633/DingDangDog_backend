package com.ddd.app.dogmatching.dto;
// ===== 멍! 매칭 결과 =====

public class MatchingResultDTO {
	/**
	 *	@author 윤철민
	 * 
	 *	@since jdk17
	 * 
	 *	매칭 결과 정보를 담기 위한 DTO 클래스
	 *	결과번호, 회원번호, 활동성, 사회성, 독립성, 짖음수준, 털관리, 저장날짜/시간 저장
	 *	
	 *	현재 멍! 매칭 저장 구조는
	 *	1) DDD_MATCH_RESULT        : 설문 결과(성향 점수 5개) 저장
	 *	2) DDD_MATCH_RESULT_DOG    : 해당 결과에 연결된 추천 유기견 번호와 순위 저장
	 *	의 2단계 구조로 사용한다.
	 */

//	--멍매칭결과
//	CREATE TABLE DDD_MATCH_RESULT (
//	   result_number NUMBER,	// int
//	   user_number NUMBER,	// int
//	   dog_activity NUMBER,	// int (4문항 합계, 4~20)
//	   dog_sociality NUMBER,	// int (4문항 합계, 4~20)
//	   dog_independence NUMBER,	// int (4문항 합계, 4~20)
//	   dog_barking NUMBER,	// int	(4문항 합계, 4~20)
//	   dog_grooming NUMBER,	// int (4문항 합계, 4~20)
//	   result_created_date DATE DEFAULT SYSDATE NOT NULL,	// String
//	   CONSTRAINT PK_match_res_number PRIMARY KEY (result_number),
//	   CONSTRAINT FK_match_res_user_number FOREIGN KEY (user_number) REFERENCES ddd_user(user_number) ON DELETE CASCADE ,
//	   CONSTRAINT CK_match_res_acti CHECK (dog_activity BETWEEN 4 AND 20),
//	   CONSTRAINT CK_match_res_soci CHECK (dog_sociality BETWEEN 4 AND 20),
//	   CONSTRAINT CK_match_res_inde CHECK (dog_independence BETWEEN 4 AND 20),
//	   CONSTRAINT CK_match_res_bark CHECK (dog_barking BETWEEN 4 AND 20),
//	   CONSTRAINT CK_match_res_groo CHECK (dog_grooming BETWEEN 4 AND 20)
//	);
//
//	--멍매칭 결과 ++
//	CREATE TABLE DDD_MATCH_RESULT_DOG (
//	   result_dog_number NUMBER,	// int
//	   result_number NUMBER NOT NULL,	// int
//	   dog_number NUMBER NOT NULL,	// int
//	   result_rank NUMBER NOT NULL,	// int
//	   CONSTRAINT PK_match_result_dog PRIMARY KEY (result_dog_number),
//	   CONSTRAINT FK_match_result_dog_result_number FOREIGN KEY (result_number)
//	      REFERENCES DDD_MATCH_RESULT(result_number) ON DELETE CASCADE,
//	   CONSTRAINT FK_match_result_dog_dog_number FOREIGN KEY (dog_number)
//	      REFERENCES ddd_archive(dog_number) ON DELETE CASCADE,
//	   CONSTRAINT CK_match_result_dog_rank CHECK (result_rank BETWEEN 1 AND 8)
//	);
//
//	CREATE SEQUENCE seq_match_result nocache nocycle;
//	CREATE SEQUENCE seq_match_result_dog NOCACHE NOCYCLE;
//	----- 필드 -----
//	DDD_MATCH_RESULT
	private int resultNumber;
	private int userNumber;
	private int dogActivity;
	private int dogSociality;
	private int dogIndependence;
	private int dogBarking;
	private int dogGrooming;
	private String resultCreatedDate;

//	DDD_MATCH_RESULT_DOG
	private int resultDogNumber;
	private int dogNumber;
	private int resultRank;

//	----- Getter & Setter -----
	public int getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(int resultNumber) {
		this.resultNumber = resultNumber;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getDogActivity() {
		return dogActivity;
	}

	public void setDogActivity(int dogActivity) {
		this.dogActivity = dogActivity;
	}

	public int getDogSociality() {
		return dogSociality;
	}

	public void setDogSociality(int dogSociality) {
		this.dogSociality = dogSociality;
	}

	public int getDogIndependence() {
		return dogIndependence;
	}

	public void setDogIndependence(int dogIndependence) {
		this.dogIndependence = dogIndependence;
	}

	public int getDogBarking() {
		return dogBarking;
	}

	public void setDogBarking(int dogBarking) {
		this.dogBarking = dogBarking;
	}

	public int getDogGrooming() {
		return dogGrooming;
	}

	public void setDogGrooming(int dogGrooming) {
		this.dogGrooming = dogGrooming;
	}

	public String getResultCreatedDate() {
		return resultCreatedDate;
	}

	public void setResultCreatedDate(String resultCreatedDate) {
		this.resultCreatedDate = resultCreatedDate;
	}

	public int getResultDogNumber() {
		return resultDogNumber;
	}

	public void setResultDogNumber(int resultDogNumber) {
		this.resultDogNumber = resultDogNumber;
	}

	public int getDogNumber() {
		return dogNumber;
	}

	public void setDogNumber(int dogNumber) {
		this.dogNumber = dogNumber;
	}

	public int getResultRank() {
		return resultRank;
	}

	public void setResultRank(int resultRank) {
		this.resultRank = resultRank;
	}

//	----- Override -----
	@Override
	public String toString() {
		return "MatchingResultDTO [resultNumber=" + resultNumber + ", userNumber=" + userNumber + ", dogActivity="
				+ dogActivity + ", dogSociality=" + dogSociality + ", dogIndependence=" + dogIndependence
				+ ", dogBarking=" + dogBarking + ", dogGrooming=" + dogGrooming + ", resultCreatedDate="
				+ resultCreatedDate + ", resultDogNumber=" + resultDogNumber + ", dogNumber=" + dogNumber
				+ ", resultRank=" + resultRank + "]";
	}
}