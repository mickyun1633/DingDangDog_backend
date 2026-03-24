<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>1:1 문의 리스트</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/mypage/common/support_list_common.css" />
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

	<main class="support-list-common">
		<div class="container">

			<!-- 사이드바 -->
			<jsp:include page="/app/mypage/sidebar.jsp" />

			<!-- 본문 -->
			<section class="content">
				<div class="content-box">
					<div class="panel">

						<div class="panel-head">
							<h2 class="panel-title">1:1 문의</h2>
						</div>

						<div class="panel-body">

							<div class="support-table">
								<div class="support-table-head">
									<div class="col-status">답변상태</div>
									<div class="col-number">문의글 번호</div>
									<div class="col-title">제목</div>
									<div class="col-date">작성일자</div>
								</div>


								<div id="supportTableBody">

									<c:choose>
										<c:when test="${not empty inquiryList}">

											<c:forEach var="item" items="${inquiryList}">

												<div class="support-table-row">

													<!-- 답변 상태 -->
													<div class="col-status">
														<span class="status ${item.answerStatus}"> <c:choose>
																<c:when test="${item.answerStatus eq 'Y'}">
                                                                답변 완료
                                                            </c:when>
																<c:otherwise>
                                                                답변 대기
                                                            </c:otherwise>
															</c:choose>
														</span>
													</div>

													<!-- 번호 -->
													<div class="col-number">${item.inquiryNumber}</div>

													<!-- 제목 -->
													<div class="col-title">
														<a
															href="${pageContext.request.contextPath}/inquiry/inquiryReadOk.in?inquiryNumber=${item.inquiryNumber}">
															${item.inquiryTitle} </a>
													</div>

													<!-- 날짜 -->
													<div class="col-date">${item.inquiryDate}</div>

												</div>

											</c:forEach>

										</c:when>
										<c:otherwise>
											<div class="support-table-row">
												<div class="col-title">등록된 문의가 없습니다.</div>
											</div>
										</c:otherwise>

									</c:choose>

								</div>
							</div>

							<!-- 페이지네이션 -->
							<div class="panel-footer">

								<div class="pagination">
									<c:if test="${prev}">
										<a
											href="${pageContext.request.contextPath}/inquiry/inquiryListOk.in?page=${startPage-1}">
											이전 </a>
									</c:if>

									<c:forEach var="i" begin="${startPage}" end="${endPage}">
										<a
											href="${pageContext.request.contextPath}/inquiry/inquiryListOk.in?page=${i}">
											${i} </a>
									</c:forEach>

									<c:if test="${next}">
										<a
											href="${pageContext.request.contextPath}/inquiry/inquirylistOk.in?page=${endPage+1}">
											다음 </a>
									</c:if>
								</div>

								<a class="btn-outline"
									href="${pageContext.request.contextPath}/inquiry/inquiryWrite.in">
									문의글 작성 </a>

							</div>

						</div>
					</div>
				</div>
			</section>

		</div>
	</main>

	<jsp:include page="/app/footer.jsp" />

</body>
</html>