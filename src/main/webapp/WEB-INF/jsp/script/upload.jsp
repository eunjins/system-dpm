<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>업로드 폼</h1>
	<form action="/scripts/distribute" method="POST" enctype="multipart/form-data">
        <input type="text" name="name" />
        <input type="file" name="sourceFile" />
        <input type="file" name="classFile" />
        <input type="submit" value="SEND" />
    </form>

</body>
</html>