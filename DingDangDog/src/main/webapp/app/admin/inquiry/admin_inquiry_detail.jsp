<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>문의사항</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/admin.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/inquiry/admin_inquiry_detail.css" />
</head>
<body>
	<!-- 전체화면 -->
	<main class="admin-main-container">
		<!-- 사이드바 -->
		<%@ include file="/app/admin/admin_sidebar.jsp"%>
		<!-- 메인 화면 -->
		<section class="admin-main-section">
			<!-- 페이지 상단 (제목, 버튼) -->
			<div class="admin-main-section-header">
				<div class="admin-title">문의사항</div>
			</div>
			<!-- 페이지 컨텐츠 -->
			<div class="admin-main-content admin-box-shadow">
				<!-- 테이블등 정보 -->
				<div class="inquiry-row">
					<div class="inquiry-title">${inquiry.inquiryTitle}</div>
					<div class="inquiry-response response-wait">${inquiry.answerStatus}</div>
				</div>

				<div class="inquiry-row">
					<div class="inquiry-nickname">${inquiry.userNickname}</div>
					<div class="inquiry-date">${inquiry.inquiryDate}</div>
				</div>
				<div class="inquiry-content-section">
					<div class="inquiry-content">${inquiry.inquiryPost}</div>
				</div>
				<div id="answer-write" class="inquiry-answer-section answer-write">
					<form id="aswer-form" class="answer-container" action=""
						method="get" name="answerPost">
						<input type="hidden" value="${inquiry.inquiryNumber} ">
						<textarea placeholder="답변 입력" spellcheck="false"></textarea>
						<div class="answer-btn-container">
							<button type="submit" id="answer-submit-btn"
								class="answer-submit-btn">답변</button>
						</div>
					</form>
				</div>
				<div id="answer-complete"
					class="inquiry-answer-section answer-complete">
					<div class="answer-header">
						<div class="answer-admin-answer">관리자 답변</div>
						<div class="answer-date"></div>
					</div>
					<div class="answer-content-box"></div>
				</div>
			</div>
			<!-- 페이지 하단 (검색, 페이지네이션) -->
			<div class="admin-main-section-footer">
				<div class="btn-container">
					<button
						onclick="location.href = '${pageContext.request.contextPath}/admin/InquiryListOk.ad'"
						class="return-btn admin-box-shadow">목록으로</button>
				</div>
			</div>
		</section>
	</main>
</body>
<script>
	/* const answerForm = document.getElementById("aswer-form");
	const writeSection = document.getElementById("answer-write");
	const completeSection = document.getElementById("answer-complete");

	answerForm.addEventListener("submit", (e) => {
	  e.preventDefault();
	  alert("답변 등록이 완료되었습니다.");
	  writeSection.style.display = "none";
	  completeSection.style.display = "flex";
	}); */
</script>
</html>
