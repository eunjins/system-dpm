<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>디바이스 정보 조회</h2>
<hr>
<table>
    <tr>
        <td>디바이스 ID:</td>
        <td>
            ${device.id}
        </td>
    </tr>
    <tr>
        <td>디바이스 명:</td>
        <td>
            ${device.name}
        </td>
    </tr>
    <tr>
        <td>호스트 명:</td>
        <td>
            ${device.hostName}
        </td>
    </tr>
    <tr>
        <td>IP 주소:</td>
        <td>
            ${device.ipAddress}
        </td>
    </tr>
    <tr>
        <td>JDK 버전:</td>
        <td>
            ${device.jdkVersion}
        </td>
    </tr>
    <tr>
        <td>등록 일자:</td>
        <td>
            ${device.insertDate}
        </td>
    </tr>
    <tr>
        <td>상태:</td>
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
</table>

<a href="${contextPath}/devices/${device.id}/form">
    <input type="button" value="수정"/>
</a>
<a href="${contextPath}/devices">
    <input type="button" value="목록"/>
</a>
</body>
</html>
