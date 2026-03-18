<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>내가 작성한 멍! 로그 목록</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mypage/common/review_list.css" />
  <script defer src="${pageContext.request.contextPath}/assets/js/mypage/common/review_list.js"></script>
</head>

<body>
  <div id="header-container"></div>

  <main class="review-list">
    <div class="container">
      <aside class="sidebar">
      </aside>

      <section class="content">
        <div class="content-box">
          <div class="panel">
            <div class="panel-head">
              <h2 class="panel-title">내가 작성한 멍! 로그 목록</h2>
            </div>

            <div class="panel-body">
              <div class="review-table">
                <div class="review-table-head">
                  <div class="col-number">글 번호</div>
                  <div class="col-title">제목</div>
                  <div class="col-date">작성일자</div>
                </div>

                <div id="reviewTableBody">
                  <c:choose>
                    <c:when test="${not empty logList}">
                      <c:forEach var="log" items="${logList}">
                        <div class="review-table-row" data-log-number="${log.logNumber}">
                          <div class="col-number">${log.logNumber}</div>
                          <div class="col-title">
                            <c:choose>
                              <c:when test="${not empty log.logTitle}">
                                ${log.logTitle}
                              </c:when>
                              <c:otherwise>
                                제목 없음
                              </c:otherwise>
                            </c:choose>
                          </div>
                          <div class="col-date">${log.logDate}</div>
                        </div>
                      </c:forEach>
                    </c:when>

                    <c:otherwise>
                      <div class="empty-box">작성한 멍! 로그가 없습니다.</div>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>

              <div class="panel-footer">
                <div class="pagination">
                  <ul class="page-list" id="pagination">
                    <li>
                      <button type="button" class="prev-btn">
                        <span>&lt;</span>
                      </button>
                    </li>
                    <li>
                      <button type="button" class="next-btn">
                        <span>&gt;</span>
                      </button>
                    </li>
                  </ul>
                </div>
              </div>
            </div>

          </div>
        </div>
      </section>
    </div>
  </main>

  <div id="footer-container"></div>
  <script src="${pageContext.request.contextPath}/assets/js/header-footer.js"></script>
</body>
</html>