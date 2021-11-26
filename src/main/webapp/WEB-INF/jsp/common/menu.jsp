<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>

<html>
<head>
    <title>menu</title>
</head>
<body>
<table border="1">
    <tr>
        <th>
            <a href="${contextPath}/devices">
                디바이스
            </a>
        </th>
        <th>
            <a href="${contextPath}/scripts">
                스크립트 측정 결과
            </a>
        </th>
        <td>
            <a href="${contextPath}/logout">
                <input type="button" name="logout" value="로그아웃" />
            </a>
        </td>
    </tr>
</table>
</body>
</html>
