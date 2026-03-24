<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>문의글 상세(일반회원)</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/mypage/common/support_write_common.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css" />

</head>

<body>
	<!-- header -->
	<!-- 유저 번호 확인 존재시 로그인 헤더 -->
	<c:choose>
		<c:when test="${not empty sessionScope.userNumber}">
			<jsp:include page="/app/header_login.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/app/header_logout.jsp" />
		</c:otherwise>
	</c:choose>

	<main class="support-write-common">
		<div class="container">

			<!-- 사이드바 -->
			<jsp:include page="/app/mypage/sidebar.jsp" />

			<!-- 본문 -->
			<section class="content">
				<div class="content-box">
					<div class="panel">

						<div class="panel-head">
							<h2 class="panel-title">문의글 작성</h2>
						</div>

						<div class="panel-body">

							<form
								action="${pageContext.request.contextPath}/inquiry/inquiryWriteOk.in"
								method="post">

								<div class="input-title">
									<h2>문의 제목</h2>
									<input type="text" name="title" required>
								</div>

								<div class="info">
									<h2>문의 내용</h2>
								</div>

								<div class="input-main">
									<textarea name="content" rows="8" required></textarea>
								</div>

								<div class="panel-footer">
									<a class="btn-back"
										href="${pageContext.request.contextPath}/inquiry/inquiryListOk.in">
										목록으로 </a>

									<button type="submit" class="btn-add">문의글 등록</button>
								</div>

							</form>

						</div>

					</div>
				</div>
			</section>

		</div>
	</main>

	<!-- footer -->
	<jsp:include page="/app/footer.jsp" />
</body>
</html>