<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<h1>로그인 폼 </h1>
<br/>
<hr/>
<form name="loginForm" action="/login" method="POST">
    <input id="id" type="text" name="id"/><br/>
    <input id="password" type="password" name="password"/><br/><br/>
    <div id="checkLogIn"></div>
    <input type="button" value="로그인" onclick="login()"/>
</form>

<script>
    var missMatch = document.getElementById("checkLogIn").innerHTML = "${missMatch}";
    function login() {
        var loginInfo = {
            loginForm:document.loginForm,
            id: document.getElementById("id").value,
            password: document.getElementById("password").value
        };
        console.log(loginInfo);

        if (loginInfo.id == null && loginInfo.password == null
            || loginInfo.id == "" && loginInfo.password == "") {
            document.getElementById("checkLogIn").innerHTML = "로그인 정보를 입력하세요";
        } else {
            loginInfo.loginForm.submit();

        }
    }
</script>
</body>
</html>