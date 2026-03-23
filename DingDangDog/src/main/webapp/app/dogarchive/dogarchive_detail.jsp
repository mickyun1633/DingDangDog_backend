<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dogarchive/dogarchive_detail.css" />
<title>멍! 카이브 상세</title>
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

	<main class="archive-detail">
		<div class="container">
			<div class="container-header">
				<h2 id="main-title">멍! 카이브</h2>
			</div>

			<div class="container-body">
				<div class="archive-detail-top">
					<div class="archive-image-section">
						<div class="archive-image-box">
							<c:choose>
								<c:when test="${not empty archive.archiveImgPath}">
									<img src="${pageContext.request.contextPath}${archive.archiveImgPath}" alt="${archive.dogName}">
								</c:when>
								<c:otherwise><div class="no-img">이미지 준비중</div></c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="archive-info-section">
						<div class="dog-name-row">
							<span class="dog-name-label">이름 :</span> 
							<span class="dog-name-value">${archive.dogName}</span>
						</div>

						<div class="dog-info-grid">
							<div class="info-item"><span class="info-label">품종 :</span> <span class="info-value">${archive.dogBreed}</span></div>
							<div class="info-item"><span class="info-label">성별 :</span> <span class="info-value">${archive.dogGender == 'M' ? '남아' : '여아'}</span></div>
							<div class="info-item"><span class="info-label">나이 :</span> <span class="info-value">${archive.dogAge}</span></div>
							<div class="info-item"><span class="info-label">몸무게 :</span> <span class="info-value">${archive.dogWeight}kg</span></div>
							<div class="info-item"><span class="info-label">보호소에 들어온 날 :</span> <span class="info-value">${archive.dogSafeDate}</span></div>
						</div>

						<div class="dog-trait-list">
							<div class="trait-row">
								<span class="trait-label">활동성 :</span>
								<span class="trait-text">${archive.dogActivity >= 4 ? '에너지가 넘쳐요' : (archive.dogActivity >= 2 ? '보통이에요' : '조용해요')}</span>
								<span class="trait-score-label">점수:</span>
								<span class="trait-score-value">
                                    <c:forEach begin="1" end="5" var="i">
                                        ${i <= archive.dogActivity ? '★' : '☆'}
                                    </c:forEach>
                                </span>
							</div>
							<div class="trait-row">
								<span class="trait-label">사회성 :</span>
								<span class="trait-text">${archive.dogSociality >= 4 ? '친구를 좋아해요' : '낯을 가려요'}</span>
								<span class="trait-score-label">점수:</span>
								<span class="trait-score-value">
                                    <c:forEach begin="1" end="5" var="i">
                                        ${i <= archive.dogSociality ? '★' : '☆'}
                                    </c:forEach>
                                </span>
							</div>

						</div>
					</div>
				</div>

				<div class="archive-action-section">
					<a href="#" class="btn btn-shelter-link">${archive.shelterName} 보호소 바로가기</a> 
					<a href="${pageContext.request.contextPath}/archive/update.ar?dogNumber=${archive.dogNumber}" class="btn btn-edit">수정하기</a> 
					<a href="${pageContext.request.contextPath}/archive/deleteOk.ar?dogNumber=${archive.dogNumber}" class="btn btn-delete" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
				</div>

				<div class="archive-content-section">
					<div class="archive-content-viewer" style="white-space: pre-wrap;">
						${archive.dogDetail}
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/app/footer.jsp" />
</body>
</html>