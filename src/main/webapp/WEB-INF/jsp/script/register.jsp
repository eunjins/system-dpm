<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
<h2>스크립트 등록</h2>
<hr>
<form action="/scripts/distribute" method="POST" enctype="multipart/form-data">
    <table>
        <tr>
            <td>측정 결과 명:</td>
            <td>
                <input type="text" name="name"/>
            </td>
        </tr>
        <tr>
            <td>소스 파일:</td>
            <td>
                <input type="file" name="sourceFile" accept=".java" />JDK 버전 확인
            </td>
        </tr>
        <tr>
            <td>클래스 파일:</td>
            <td>
                <input type="file" name="classFile" accept=".class"/>JDK 버전 확인
            </td>
        </tr>
    </table>

    <input type="submit" value="배포"/>
    <a href="/devices"><input type="button" value="목록"/></a>
</form>
</body>
</html>