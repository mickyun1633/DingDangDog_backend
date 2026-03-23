<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>문의목록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/admin.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/inquiry/admin_inquiry_list.css" />
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
				<div class="admin-title">문의목록</div>
				<div class="section-header-btns">
					<button id="selet-all-btn"
						class="${empty answerStatus ? 'selected' : ''}  admin-box-shadow">
						전체목록</button>
					<button id="filtering-btn"
						class="${answerStatus eq 'N' ? 'selected' : ''}  admin-box-shadow">답변대기</button>
				</div>
			</div>
			<!-- 페이지 컨텐츠 -->
			<div class="admin-main-content admin-box-shadow">
				<!-- 테이블등 정보 -->

				<div class="admin-list-header">
					<div class="inquiry-number">문의글 번호</div>
					<div class="response-Status">답변상태</div>
					<div class="inquiry-title">제목</div>
					<div class="user-nickname">작성자</div>
					<div class="inquiry-date">작성일자</div>
				</div>

				<c:forEach var="inq" items="${inquiryList}">
					<a
						href="${pageContext.request.contextPath}/admin/InquiryDetailOk.ad?inquiryNumber=${inq.inquiryNumber}"
						class="admin-list-row">
						<div class="inquiry-number">${inq.inquiryNumber}</div> <c:if
							test="${inq.answerStatus eq 'N'}">
							<div class="response-Status response-wait">답변대기</div>
						</c:if> <c:if test="${inq.answerStatus  eq 'Y'}">
							<div class="response-Status">답변완료</div>
						</c:if>
						<div class="inquiry-title">${inq.inquiryTitle}</div>
						<div class="user-nickname">${inq.userNickname}</div>
						<div class="inquiry-date">${inq.inquiryDate}</div>
					</a>
				</c:forEach>

			</div>
			<!-- 페이지 하단 (검색, 페이지네이션) -->
			<div class="admin-main-section-footer">

				<!-- 페이지네이션 -->

				<div class="pagination">
					<ul class="page-list">

						<c:if test="${prev}">
							<li>
								<button class="prev-btn"
									onclick="location.href='${pageContext.request.contextPath}/admin/InquiryListOk.ad?page=${startPage - 1}&answerStatus=${answerStatus}'">
									<span>&lt;</span>
								</button>
							</li>
						</c:if>

						<c:set var="realStartPage"
							value="${startPage < 0 ? 0 : startPage}" />
						<c:forEach var="i" begin="${realStartPage}" end="${endPage}">
							<c:choose>
								<c:when test="${!(i == page)}">
									<li>
										<button class="page-item"
											onclick="location.href='${pageContext.request.contextPath}/admin/InquiryListOk.ad?page=${i}&answerStatus=${answerStatus}'">
											<c:out value="${i}" />
										</button>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<button class="page-item current-page">
											<c:out value="${i}" />
										</button>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${next}">
							<li>
								<button class="next-btn"
									onclick="location.href='${pageContext.request.contextPath}/admin/InquiryListOk.ad?page=${endPage + 1}&answerStatus=${answerStatus}'">
									<span>&gt;</span>
								</button>
							</li>
						</c:if>
					</ul>
				</div>


			</div>
		</section>
	</main>
</body>
<script>
	const base = "${pageContext.request.contextPath}";
	const selectAllBtn = document.getElementById("selet-all-btn");
	const filteringBtn = document.getElementById("filtering-btn");
	  
	selectAllBtn.addEventListener("click", () => {
		location.href = base + "/admin/InquiryListOk.ad";
	});
	 
	filteringBtn.addEventListener("click", () => {
		location.href = base + "/admin/InquiryListOk.ad?answerStatus=N";
	}); 
</script>
</html>
