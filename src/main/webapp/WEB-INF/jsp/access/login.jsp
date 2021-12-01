<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!doctype html>
<html>
<head>

    <meta charset="utf-8" />
    <title>디바이스 성능 측정 통합 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
    <meta content="Themesbrand" name="author" />
    <!-- App favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico">

    <!-- Bootstrap Css -->
    <link href="/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
    <!-- Icons Css -->
    <link href="/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
    <!-- App Css-->
    <link href="/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />


</head>

<body style="font-family: 'Nanum Myeongjo', serif">
<div class="account-pages my-5 pt-sm-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-5">
                <div class="card overflow-hidden">
                    <div class="bg-login text-center">
                        <div class="bg-login-overlay"></div>
                        <div class="position-relative">
                            <h5 class="text-white font-size-20">Welcome Back !</h5>
                            <p class="text-white-50 mb-0">Sign in to continue to Qovex.</p>
                        </div>
                    </div>
                    <div class="card-body pt-5">
                        <div class="p-2">
                            <form name="loginForm" class="form-horizontal" action="/login" method="POST">

                                <div class="mb-3">
                                    <label class="form-label" for="id">아이디</label>
                                    <input type="text" class="form-control" id="id" name="id"
                                           placeholder="Enter username">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label" for="password">비밀번호</label>
                                    <input type="password" class="form-control" id="password" name="password"
                                           placeholder="Enter password">
                                </div><br/>

                                <div id="checkLogIn"></div>

                                <div class="mt-3">
                                    <button class="btn btn-primary w-100 waves-effect waves-light"
                                            type="button" onclick="login()">
                                        Log In</button>
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
<!-- JAVASCRIPT -->
<script src="/assets/libs/jquery/jquery.min.js"></script>
<script src="/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/libs/metismenu/metisMenu.min.js"></script>
<script src="/assets/libs/simplebar/simplebar.min.js"></script>
<script src="/assets/libs/node-waves/waves.min.js"></script>
<script src="/assets/libs/jquery-sparkline/jquery.sparkline.min.js"></script>

<script src="/assets/js/app.js"></script>

</body>
</html>