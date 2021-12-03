<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE HTML>
<html>
<head>

    <jsp:include page="/WEB-INF/jsp/common/top.jsp"/>

</head>

<body style="font-family: 'NanumSqaure'">
<div class="account-pages my-5 pt-sm-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-5">
                <div class="card overflow-hidden">
                    <div class="bg-login text-center">
                        <div class="bg-login-overlay"></div>
                        <div class="position-relative">
                            <h5 class="text-white font-size-20">Welcome Back !</h5>
                            <p class="text-white-50 mb-0">Sign in to continue to DPM-SYSTEM.</p>
                        </div>
                    </div>
                    <div class="card-body pt-5">
                        <div class="p-2">
                            <form name="loginForm" class="form-horizontal" action="/login" method="POST">

                                <div class="mb-3">
<%--                                    <label class="form-label" for="id">아이디</label>--%>
                                    <input type="text" class="form-control" id="id" name="id"
                                           placeholder="아이디">
                                </div>

                                <div class="mb-3">
<%--                                    <label class="form-label" for="password">비밀번호</label>--%>
                                    <input type="password" class="form-control" id="password" name="password"
                                           placeholder="비밀번호">
                                </div><br/>

                                <div id="checkLogIn" style="color: blue"></div>

                                <div class="mt-3">
                                    <button class="btn btn-default btn-primary w-100 waves-effect waves-light"
                                            type="button" onclick="login()">
                                        로그인</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JAVASCRIPT -->
<script>
    var missMatch = document.getElementById("checkLogIn").innerHTML = "${missMatch}";

    function login() {
        var loginInfo = {
            loginForm: document.loginForm,
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