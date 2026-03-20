<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>멍! 로그 게시글</title>

    <link rel="stylesheet" href="${contextPath}/assets/css/admin/admin.css" />
    <link rel="stylesheet" href="${contextPath}/assets/css/admin/doglog/admin_doglog_detail.css" />

    <!-- 서버 데이터 -->
    <script>
      const contextPath = "${contextPath}";

      const logDetailData = {
        logNumber: ${empty log.logNumber ? 0 : log.logNumber},
        title: "<c:out value='${log.logTitle}'/>",
        writer: "<c:out value='${log.userNickname}'/>",
        date: "<c:out value='${log.logDate}'/>",
        post: "<c:out value='${log.logPost}'/>",
        imagePath: "<c:out value='${mainImagePath}'/>"
      };

      const commentData = [
        <c:forEach var="comment" items="${commentList}" varStatus="status">
          {
            commentNumber: ${empty comment.commentNumber ? 0 : comment.commentNumber},
            writer: "<c:out value='${comment.userNickname}'/>",
            date: "<c:out value='${comment.commentDate}'/>",
            post: "<c:out value='${comment.commentPost}'/>"
          }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
      ];
    </script>

    <script defer src="${contextPath}/assets/js/admin/admin_doglog_detail.js"></script>
  </head>

  <body>
    <main class="admin-main-container">
      <!-- 사이드바 -->
      <aside class="admin-sidebar">
        <jsp:include page="/app/admin/admin_sidebar.jsp" />
      </aside>

      <!-- 메인 화면 -->
      <section class="admin-main-section">
        <!-- 페이지 상단 -->
        <div class="admin-main-section-header">
          <div class="admin-title">멍! 로그 게시글</div>
        </div>

        <!-- 페이지 컨텐츠 -->
        <div class="admin-main-content">
          <div class="doglog-detail-container">
            <div class="detail-title" id="detailTitle"></div>

            <div class="detail-info-container">
              <div class="detail-info-name" id="detailWriter"></div>
              <div class="detail-info-date" id="detailDate"></div>
            </div>

            <div class="detail-main-container">
              <div class="detail-main-img" id="detailImageBox"></div>
              <div class="detail-main-post" id="detailPost"></div>
            </div>

            <hr />

            <div id="commentListBox"></div>
          </div>
        </div>

        <!-- 페이지 하단 -->
        <div class="admin-main-section-footer">
          <div class="btn-container">
            <button
              type="button"
              class="btn-backlist btn"
              onclick="location.href='${contextPath}/admin/adminLogListOk.ad'">
              목록으로
            </button>

            <button
              type="button"
              class="btn-doglog-delete btn"
              id="btn-remove-detail">
              게시글 삭제
            </button>
          </div>
        </div>
      </section>
    </main>
  </body>
</html>