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

        <jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>

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
                            <h2 class="page-title mb-0 font-size-40">디바이스 정보 수정</h2>
                        </div>
                    </div>

                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">

                                <%--                                <h1 class="card-title">디바이스 정보 수정</h1><br/><br/>--%>
                                <form action="/devices/${device.id}" method="POST">
                                    <input type="hidden" name="_method" value="put"/>
                                    <input type="hidden" name="id" value="${device.id}"/>
                                    <div class="mb-3 row">
                                        <label class="col-md-2 col-form-label">디바이스 ID:</label>
                                        <label class="col-md-6 col-form-label">${device.id}</label>
                                    </div>
                                    <div class="mb-3 row">
                                        <label for="example-search-input" class="col-md-2 col-form-label">디바이스 명</label>
                                        <div class="col-md-6">
                                            <input class="form-control" type="name" value="${device.name}"
                                                   id="example-search-input">
                                        </div>
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
                                    <label for="example-search-input" class="col-md-2 col-form-label">상태:</label>
                                    <c:choose>
                                        <c:when test="${device.status eq 'Y'}">
                                            <div class="form-check form-check-inline mb-2">
                                                <input class="form-check-input" type="radio" name="status"
                                                       id="flexRadioDefault1" value="Y" checked="checked">
                                                <label class="form-check-label" for="flexRadioDefault1">활성화</label>
                                            </div>
                                            <div class="form-check form-check-inline mb-2">
                                                <input class="form-check-input" type="radio" name="status"
                                                       id="flexRadioDefault2" value="N">
                                                <label class="form-check-label" for="flexRadioDefault2">비활성화</label>
                                            </div>
                                        </c:when>
                                        <c:when test="${device.status eq 'N'}">
                                            <div class="form-check form-check-inline mb-2">
                                                <input class="form-check-input" type="radio" name="status"
                                                       id="flexRadioDefault3" value="Y">
                                                <label class="form-check-label" for="flexRadioDefault3">활성화</label>
                                            </div>
                                            <div class="form-check form-check-inline mb-2">
                                                <input class="form-check-input" type="radio" name="status"
                                                       id="flexRadioDefault4" value="N" checked="checked">
                                                <label class="form-check-label" for="flexRadioDefault4">비활성화</label>
                                            </div>
                                        </c:when>
                                    </c:choose>


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
                                            <a href="${contextPath}/devices/${device.id}">
                                                <button type="button"
                                                        class="btn btn-outline-primary waves-effect waves-light"
                                                        style="float: right">취소
                                                </button>
                                            </a>
                                            <button type="button" class="btn btn-primary waves-effect waves-light"
                                                    id="sa-warning">수정</button>
<%--                                            <label style="float: right">  </label>--%>
<%--                                            <button type="submit"--%>
<%--                                                    class="btn btn-outline-primary waves-effect waves-light" style="float: right">수정--%>
<%--                                            </button>--%>

                                        </div>
                                    </div>
                                </form>
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

<script src="/assets/js/app.js" aria-hidden="true"></script>

<!-- Sweet Alerts js -->
<script src="/assets/libs/sweetalert2/sweetalert2.min.js"></script>

<!-- Sweet alert init js-->
<script src="/assets/js/pages/sweet-alerts.init.js"></script>

</body>

</html>















