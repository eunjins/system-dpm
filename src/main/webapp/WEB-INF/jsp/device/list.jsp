<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>디바이스 정보 목록</h2>
<hr>

상태
<select name="status">
    <option value="all" selected="selected">전체</option>
    <option value="activation">활성화</option>
    <option value="disabled">비활성화</option>
</select>

검색 조건
<select name="status">
    <option value="name" selected="selected">디바이스 명</option>
    <option value="insertDate">등록 일자</option>
</select>
<input type="text" name="message" />
<input type="button" value="검색" />

<table border="1">
    <tr>
        <td>번호</td>
        <td>디바이스 명</td>
        <td>호스트 명</td>
        <td>등록 일자</td>
        <td>JDK 버전</td>
        <td>상태</td>
    </tr>

    <c:forEach items="${devices}" var="device" varStatus="object">
        <tr>
            <td width="50">${object.count}</td>
            <td width="200">
                <a href="${contextPath}/devices/${device.id}">${device.name}</a>
            </td>
            <td>${device.hostName}</td>
            <td>${device.insertDate}</td>
            <td>${device.jdkVersion}</td>
            <td>
                <c:choose>
                    <c:when test="${device.status eq 'Y'}">
                        활성화
                    </c:when>
                    <c:when test="${device.status eq 'N'}">
                        비활성화
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

<table border="1">
    <tr>
        <td>
            <a href="${contextPath}/devices/${device.id}"> << </a>
        </td>
        <td>
            <a href="${contextPath}/devices/${device.id}"> < </a>
        </td>
        <td>
            <a href="${contextPath}/devices/${device.id}"> 1 </a>
        </td>
        <td>
            <a href="${contextPath}/devices/${device.id}"> 2 </a>
        </td>
        <td>
            <a href="${contextPath}/devices/${device.id}"> 3 </a>
        </td>
        <td>
            <a href="${contextPath}/devices/${device.id}"> 4 </a>
        </td>
        <td>
            <a href="${contextPath}/devices/${device.id}"> 5 </a>
        </td>
        <td>
            <a href="${contextPath}/devices/${device.id}"> > </a>
        </td>
        <td>
            <a href="${contextPath}/devices/${device.id}"> >> </a>
        </td>
    </tr>
</table>

</body>
</html>
