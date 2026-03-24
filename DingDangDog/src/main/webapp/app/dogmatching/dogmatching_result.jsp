<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>멍! 매칭 결과</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dogmatching/dogmatching_result.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />

  <script>
    const contextPath = "${pageContext.request.contextPath}";
    const isLoggedIn = ${not empty sessionScope.userNumber};
    const recommendedDogs = [
      <c:forEach var="dog" items="${recommendedDogs}" varStatus="status">
        {
          dogNumber: "${dog.dogNumber}",
          dogName: "${dog.dogName}",
          dogBreed: "${dog.dogBreed}",
          shelterName: "${dog.shelterName}",
          dogArchiveDate: "${dog.dogArchiveDate}",
          archiveImgPath: "${dog.archiveImgPath}"
        }<c:if test="${!status.last}">,</c:if>
      </c:forEach>
    ];
  </script>

  <script defer src="${pageContext.request.contextPath}/assets/js/dogmatching/dogmatching_result.js"></script>
</head>

<body>
  <!-- header -->
    <div id="header-container">
  	 <c:choose>
		  <c:when test="${not empty sessionScope.userNumber}">
		    <jsp:include page="/app/header_login.jsp" />
		  </c:when>
		  <c:otherwise>
		    <jsp:include page="/app/header_logout.jsp" />
		 </c:otherwise>
	  	</c:choose>
	  </div>

  <main>
    <div class="dogmatching-main-container">
      <div class="main-header">
        <div class="main-header-title">멍! 매칭 결과</div>
      </div>

      <div class="main-content">
        <div class="result-wrapper">
          <div class="survey-result">
            <c:out value="${resultComment}" escapeXml="false" />
          </div>
          <div class="btn-container">
            <button id="result-save-btn" type="button">결과 저장</button>
          </div>
        </div>

        <!-- JS가 여기 안에 카드 생성 -->
        <div class="card-wrapper" id="card-wrapper">
        </div>
      </div>
    </div>
  </main>

  <jsp:include page="/app/footer.jsp" />
</body>

</html>