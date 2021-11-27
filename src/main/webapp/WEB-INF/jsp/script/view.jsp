<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
<h2>스크립트 측정 결과 조회 </h2>
<hr>

측정 결과 명: ${measures[0].name} <br>
스크립트 명: ${script.name} <br>
업로드 일시: ${script.uploadPoint} <br>

<c:forEach items="${attaches}"  var="attach">
    <c:choose>
        <c:when test="${attach.division eq 'S'}">
            소스 파일: ${attach.name}.java <br>
        </c:when>
        <c:when test="${attach.division eq 'C'}">
            클래스 파일: ${attach.name}.class <br>
        </c:when>
    </c:choose>
</c:forEach>

<input type="button" value="엑셀 다운로드" />
<table border="1">
    <tr>
        <td>번호</td>
        <td>디바이스 명</td>
        <td>실행 시간 (ms)</td>
    </tr>
    <c:forEach items="${measures}" var="measure" varStatus="object">
        <tr>
            <td>${object.count}</td>
            <td>${measure.deviceName}</td>
            <td>${measure.execTime}</td>
        </tr>
    </c:forEach>
</table>
<a href="${contextPath}/scripts"><input type="button" value="목록" /></a>
</body>
</html>