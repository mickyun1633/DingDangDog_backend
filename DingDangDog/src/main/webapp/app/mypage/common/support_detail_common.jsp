<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>문의글 작성(일반회원)</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mypage/common/support_detail_common.css" />
</head>

<body>
	<!-- header -->
	<div id="header-container"></div>
	<main class="support-detail-common">
		<div class="container">
		<aside class="sidebar">
			<nav class="side-menu">
			<a class="btn-side-link" href="./profile_edit_common.html">내 정보 변경</a>
			<hr>
			<a class="btn-side-link" href="./volunteer_status_list_common.html">멍! 케어 신청 확인</a>
			<hr>
			<a class="btn-side-link" href="./review_list.html">내가 작성한 멍! 로그 목록</a>
			<hr>
			<a class="btn-side-link" href="./support_list_common.html">1 : 1 문의</a>
			</nav>
		</aside>

    	<section class="content">
            <div class="content-box">
                <div class="panel">

                    <!-- 제목 -->
                    <div class="panel-head">
                        <h2 class="panel-title">
                            ${inquiry.inquiryTitle}
                        </h2>
                    </div>

                    <div class="panel-body">

                        <!-- 문의 상태 -->
                        <div class="status">
                            <h2>문의 상태</h2>
                            <div class="reply-status">
                                <span class="status-text ${inquiry.answerStatus}">
                                    <c:choose>
										<c:when test="${inquiry.answerStatus eq 'Y' or inquiry.answerStatus eq 'y'}">
                                        	답변완료
                                        </c:when>
                                        <c:otherwise>
                                            답변 대기
                                        </c:otherwise>
                                    </c:choose>

                                </span>
                            </div>
                        </div>

                        <!-- 문의 정보 -->
                        <div class="info">
                            <h2>문의 내용</h2>
                            <div class="date">
                                <h3>문의 일자</h3>
                                <div class="write-date">
                                    ${inquiry.inquiryDate}
                                </div>
                            </div>
                        </div>

                        <!-- 문의 본문 -->
                        <div class="question">
                            ${inquiry.inquiryPost}
                        </div>

                        <!-- 답변이 있을 때만 출력 -->
                        <c:if test="${not empty inquiry.answerPost}">
                            <div class="info">
                                <h2>답변 내용</h2>
                                <div class="date">
                                    <h3>답변 일자</h3>
                                    <div class="answer-date">
                                        ${inquiry.answerDate}
                                    </div>
                                </div>
                            </div>

                            <div class="answer">
                                ${inquiry.answerPost}
                            </div>
                        </c:if>

                        <!-- 하단 버튼 -->
                        <div class="panel-footer">

                            <a class="btn-back"
                               href="${pageContext.request.contextPath}/inquiry/inquiryListOk.in">
                                목록으로
                            </a>

                            <!-- 삭제는 반드시 POST -->
                            <form action="${pageContext.request.contextPath}/inquiry/inquiryDeleteOk.in"
                                  method="post"
                                  style="display:inline;">
                                <input type="hidden"
                                       name="inquiryNumber"
                                       value="${inquiry.inquiryNumber}">
                                <button type="submit" class="btn-delete">
                                    문의글 삭제
                                </button>
                            </form>

                        </div>

                    </div>
                </div>
            </div>
        </section>

    </div>
</main>

<div id="footer-container"></div>

</body>
</html>