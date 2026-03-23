<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/dogarchive/dogarchive_edit.css?v=1.3" />
<script defer
	src="${pageContext.request.contextPath}/assets/js/dogarchive/dogarchive_edit.js"></script>
<title>멍! 카이브 수정</title>
</head>

<body>
	<c:choose>
		<c:when test="${not empty sessionScope.userNumber}">
			<jsp:include page="/app/header_login.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/app/header_logout.jsp" />
		</c:otherwise>
	</c:choose>

	<form id="editForm" enctype="multipart/form-data">
		<input type="hidden" name="dogNumber" value="${archive.dogNumber}">

		<main class="archive-detail">
			<div class="container">
				<div class="container-header">
					<h2 id="main-title">멍! 카이브 수정</h2>
				</div>

				<div class="container-body">
					<div class="archive-detail-top">
						<div class="archive-image-section">
							<div class="archive-image-box">
								<img id="previewImage"
									src="${pageContext.request.contextPath}${archive.archiveImgPath}"
									alt="대표 이미지" />
							</div>
							<input type="file" id="editImage" name="dogImage"
								accept="image/*" hidden /> <label for="editImage"
								class="btn-upload">이미지 수정</label>
						</div>

						<div class="archive-info-section">
							<div class="dog-name-row">
								<span class="dog-name-label">이름 :</span> <input type="text"
									class="dog-name-input" id="dogName" name="dogName"
									value="${archive.dogName}" />
							</div>

							<div class="dog-info-grid">
								<div class="info-item">
									<span class="info-label">품종 :</span> <input type="text"
										class="info-input" id="dogBreed" name="dogBreed"
										value="${archive.dogBreed}">
								</div>
								<div class="info-item">
									<span class="info-label">성별 :</span> <select class="info-input"
										id="dogGender" name="dogGender">
										<option value="M">M</option>
										<option value="F">F</option>
									</select>
								</div>
								<div class="info-item">
									<span class="info-label">나이 :</span> <input type="text"
										class="info-input" id="dogAge" name="dogAge"
										value="${archive.dogAge}">
								</div>
								<div class="info-item">
									<span class="info-label">몸무게 :</span> <input type="text"
										class="info-input" id="dogWeight" name="dogWeight"
										value="${archive.dogWeight}">
								</div>
								<div class="info-item">
									<span class="info-label">보호소 날짜 :</span> <input type="date"
										class="info-input" id="dogSafeDate" name="dogSafeDate"
										value="${archive.dogSafeDate}">
								</div>
							</div>

							<div class="dog-trait-list">
								<div class="trait-row">
									<span class="trait-label">활동성 :</span> <input type="number"
										class="trait-score-input" name="dogActivity"
										value="${archive.dogActivity}" min="1" max="5">
								</div>
								<div class="trait-row">
									<span class="trait-label">사회성 :</span> <input type="number"
										class="trait-score-input" name="dogSociality"
										value="${archive.dogSociality}" min="1" max="5">
								</div>
								<div class="trait-row">
									<span class="trait-label">독립성 :</span> <input type="number"
										class="trait-score-input" name="dogIndependence"
										value="${archive.dogIndependence}" min="1" max="5">
								</div>
								<div class="trait-row">
									<span class="trait-label">짖음 수준 :</span> <input type="number"
										class="trait-score-input" name="dogBarking"
										value="${archive.dogBarking}" min="1" max="5">
								</div>
								<div class="trait-row">
									<span class="trait-label">털관리 :</span> <input type="number"
										class="trait-score-input" name="dogGrooming"
										value="${archive.dogGrooming}" min="1" max="5">
								</div>
							</div>
						</div>
					</div>

					<div class="archive-action-section">
						<button type="button" class="btn btn-save" id="btnEditSave">수정
							완료</button>
						<button type="button" class="btn btn-cancel" id="btnCancle">취소</button>
					</div>

					<div class="archive-content-section">
						<textarea class="archive-content-editor" id="archiveContentEditor"
							name="dogDetail" placeholder="내용을 입력하세요">${archive.dogDetail}</textarea>
					</div>
				</div>
			</div>
		</main>
	</form>

	<jsp:include page="/app/footer.jsp" />
	<script
		src="${pageContext.request.contextPath}/assets/js/header-footer.js"></script>
</body>
</html>