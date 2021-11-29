<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>디바이스 성능 측정 통합 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
    <meta content="Themesbrand" name="author" />
    <!-- App favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico">

    <!-- jquery.vectormap css -->
    <link href="/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet"
          type="text/css" />

    <!-- Bootstrap Css -->
    <link href="/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
    <!-- Icons Css -->
    <link href="/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
    <!-- App Css-->
    <link href="/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />
</head>

<header id="page-topbar">
    <div class="navbar-header">
        <div class="d-flex">

            <button type="button" class="btn btn-sm px-3 font-size-16 d-lg-none header-item waves-effect waves-light"
                    data-bs-toggle="collapse" data-bs-target="#topnav-menu-content">
                <i class="fa fa-fw fa-bars"></i>
            </button>

            <div class="topnav">
                <nav class="navbar navbar-light navbar-expand-lg topnav-menu">
                    <div class="dropdown d-inline-block">
                        <a href="${pageContext.request.contextPath}/devices">
                            <button type="button" class="btn btn-link waves-effect">디바이스</button>
                        </a>
                    </div>

                    <div class="dropdown d-inline-block">
                        <a href="${pageContext.request.contextPath}/scripts">
                            <button type="button" class="btn btn-link waves-effect">스크립트 측정</button>
                        </a>
                    </div>
                </nav>
            </div>
        </div>

        <div class="d-flex">
            <div class="dropdown d-inline-block">
                <button type="button" class="btn header-item waves-effect"
                        data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="d-none d-xl-inline-block ms-1" style="font-size:large">로그아웃</span>
                    <i class="mdi mdi-chevron-down d-none d-xl-inline-block"></i>
                </button>
                <div class="dropdown-menu dropdown-menu-end">
                    <a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logout"><i
                            class="bx bx-power-off font-size-16 align-middle me-1 text-danger"></i> Logout</a>
                </div>
            </div>
        </div>
    </div>
</header>

<body>

</body>
</html>
