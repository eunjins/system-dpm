<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <title>디바이스 성능 측정 통합 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Premium Multipurpose Admin & Dashboard Template" name="description"/>
    <meta content="Themesbrand" name="author"/>
    <!-- App favicon -->
    <link rel="shortcut icon" href="assets/images/favicon.ico">

    <!-- jquery.vectormap css -->
    <link href="/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet"
          type="text/css"/>


    <!-- Bootstrap Css -->
    <link href="/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css"/>
    <!-- Icons Css -->
    <link href="/assets/css/icons.min.css" rel="stylesheet" type="text/css"/>
    <!-- App Css-->
    <link href="/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css"/>

</head>

<body data-layout="horizontal" data-layout-size="boxed">

<div class="container-fluid">
    <!-- Begin page -->
    <div id="layout-wrapper">

        <header id="page-topbar">
            <div class="navbar-header">
                <div class="d-flex">


                    <button type="button"
                            class="btn btn-sm px-3 font-size-16 d-lg-none header-item waves-effect waves-light"
                            data-bs-toggle="collapse" data-bs-target="#topnav-menu-content">
                        <i class="fa fa-fw fa-bars"></i>
                    </button>

                    <div class="topnav">
                        <nav class="navbar navbar-light navbar-expand-lg topnav-menu">
                            <div class="dropdown d-inline-block">
                                <button type="button" class="btn header-item waves-effect"
                                        data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="d-none d-xl-inline-block ms-1" style="font-size:large">디바이스</span>
                                </button>
                            </div>

                            <div class="dropdown d-inline-block">
                                <button type="button" class="btn header-item waves-effect"
                                        data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="d-none d-xl-inline-block ms-1" style="font-size:large">스크립트 측정</span>
                                </button>
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
                            <a class="dropdown-item text-danger" href="#"><i
                                    class="bx bx-power-off font-size-16 align-middle me-1 text-danger"></i> Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- ============================================================== -->
        <!-- Start right Content here -->
        <!-- ============================================================== -->
        <div class="main-content">

            <div class="page-content">

                <!-- start page title -->
                <div class="row">
                    <p></p>
                    <div class="col-12">
                        <div class="page-title-box d-flex align-items-center justify-content-between">
                            <h2 class="page-title mb-0 font-size-40">디바이스 정보 조회</h2>
                        </div>
                    </div>

                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">

                                <%--                                <h1 class="card-title">디바이스 정보 수정</h1><br/><br/>--%>

                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">디바이스 ID:</label>
                                    <label class="col-md-6 col-form-label">${device.id}</label>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">디바이스 명</label>
                                    <label class="col-md-6 col-form-label">${device.name}</label>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">호스트 명:</label>
                                    <label class="col-md-6 col-form-label">${device.hostName}</label>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">IP 주소:</label>
                                    <label class="col-md-6 col-form-label">${device.ipAddress}</label>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">JDK 버전:</label>
                                    <label class="col-md-6 col-form-label">${device.jdkVersion}</label>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">등록 일자:</label>
                                    <label class="col-md-6 col-form-label">${device.insertDate}</label>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">상태:</label>
                                    <c:choose>
                                        <c:when test="${device.status eq 'Y'}">
                                            <label class="col-md-6 col-form-label">활성화</label>
                                        </c:when>
                                        <c:when test="${device.status eq 'N'}">
                                            <label class="col-md-6 col-form-label">비활성화</label>
                                        </c:when>
                                    </c:choose>

                                </div>

                                <div class="mb-3 row">

                                    <div class="col-md-10">

                                    </div>
                                </div>
                                <div class="mb-3 row">

                                    <div class="col-md-10">

                                    </div>
                                </div>
                                <div class="mb-3 row">

                                    <div class="col-md-10">

                                    </div>
                                </div>
                                <div class="mb-3 row">

                                    <div class="col-md-10">

                                    </div>
                                </div>
                                <div class="mb-3 row">

                                    <div class="col-md-10">

                                    </div>
                                </div>
                                <div class="mb-3 row">

                                    <div class="col-md-10">

                                    </div>
                                </div>
                                <div class="mb-3 row">

                                    <div class="col-md-10">

                                    </div>
                                </div>
                                <div class="mb-3 row">

                                    <div class="col-md-10">

                                    </div>
                                </div>
                                <div class="row">

                                    <div class="button-items">
                                        <a href="${contextPath}/devices">
                                            <button type="button"
                                                    class="btn btn-outline-primary waves-effect waves-light"
                                                    style="float: right">목록
                                            </button>
                                        </a>
                                        <a href="${contextPath}/devices/${device.id}/form">
                                            <button type="button"
                                                    class="btn btn-outline-primary waves-effect waves-light"
                                                    style="float: right">수정
                                            </button>
                                        </a>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end col -->
                </div>
                <!-- end row -->
            </div>
            <!-- End Page-content -->

        </div>
        <!-- end main content-->

    </div>
    <!-- END layout-wrapper -->

</div>
<!-- end container-fluid -->

<!-- JAVASCRIPT -->
<!-- JAVASCRIPT -->
<script src="/assets/libs/jquery/jquery.min.js"></script>
<script src="/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/libs/metismenu/metisMenu.min.js"></script>
<script src="/assets/libs/simplebar/simplebar.min.js"></script>
<script src="/assets/libs/node-waves/waves.min.js"></script>
<script src="/assets/libs/jquery-sparkline/jquery.sparkline.min.js"></script>

<!-- apexcharts -->
<script src="/assets/libs/apexcharts/apexcharts.min.js"></script>

<!-- jquery.vectormap map -->
<script src="/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/assets/libs/admin-resources/jquery.vectormap/maps/jquery-jvectormap-us-merc-en.js"></script>

<script src="/assets/js/pages/dashboard.init.js"></script>

<script src="/assets/js/app.js"></script>


</body>

</html>















