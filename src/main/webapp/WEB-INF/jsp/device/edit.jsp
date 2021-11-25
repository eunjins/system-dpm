<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>디바이스 정보 수정</h2>
<hr>
<form action="/devices/${device.id}" method="POST">
    <input type="hidden" name="_method" value="put"/>

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
                <input type="text" name="name" value="${device.name}"/>
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

    <input type="submit" value="수정"/>
    <a href="${contextPath}/devices/${device.id}">
        <input type="button" value="취소"/>
    </a>
</form>
</body>
</html>
