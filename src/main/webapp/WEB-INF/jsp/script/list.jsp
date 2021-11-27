<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" charset="utf-8">
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
<h2>스크립트 측정 결과 목록</h2>
<hr>
<div style="float: left">
    검색 조건
    <select name="condition" id="select_condition">
        <option value="measureName" selected="selected">측정 결과 명</option>
        <option value="scriptName">스크립트 명</option>
        <option value="uploadPoint">업로드 일자</option>
    </select>
</div>
<div id = "search_bar" style="float: left">
    <input type="text" name="message" id="search_message"/>
</div>
<button id = "button_search">검색</button>
<p>
<table border="1" id="table">
    <tr>
        <td>번호</td>
        <td>측정 결과 명</td>
        <td>스크립트 명</td>
        <td>업로드 일시</td>
        <td>상태</td>
    </tr>

    <c:forEach items="${scripts}" var="script" varStatus="object">
        <tr>
            <td width="50">${object.count}</td>
            <td width="200">${measure.name}</td>
            <td>${script.name}</td>
            <td>${script.uploadPoint}</td>
            <td>
                <c:choose>
                    <c:when test="${measure.status eq 'Y'}">
                        측정 중
                    </c:when>
                    <c:when test="${measure.status eq 'N'}">
                        <a href="${contextPath}/scripts/${script.id}">결과 보기</a>
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</p>
<p>
<table border="1">
    <tr>
        <td>
            <a href="${contextPath}/scripts"><<</a>
        </td>
        <td>
            <a href="${contextPath}/scripts"><</a>
        </td>
        <td>
            <a href="${contextPath}/scripts">1</a>
        </td>
        <td>
            <a href="${contextPath}/scripts">2</a>
        </td>
        <td>
            <a href="${contextPath}/scripts">3</a>
        </td>
        <td>
            <a href="${contextPath}/scripts">4</a>
        </td>
        <td>
            <a href="${contextPath}/scripts">5</a>
        </td>
        <td>
            <a href="${contextPath}/scripts">></a>
        </td>
        <td>
            <a href="${contextPath}/scripts">>></a>
        </td>
    </tr>
</table>
</p>
<a href="${contextPath}/scripts/form"><input type="button" value="등록" /></a>
</body>
</html>