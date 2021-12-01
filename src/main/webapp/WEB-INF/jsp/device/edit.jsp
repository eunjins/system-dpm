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

    <!-- jquery.vectormap css -->
    <link href="/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet"
          type="text/css"/>

    <jsp:include page="/WEB-INF/jsp/common/top.jsp"/>

</head>

<body data-layout="horizontal" data-layout-size="boxed" style="font-family: 'NanumSquare'">

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
                            <h2 class="page-title mb-0 font-size-40">디바이스 상세 정보</h2>
                        </div>
                    </div>
                </div>
                <!-- end page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">

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
                                            <input class="form-control" type="text" name=name value="${device.name}"
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

                                    <div class="row">
                                        <div class="button-items">
                                            <a href="${contextPath}/devices/${device.id}">
                                                <button type="button"
                                                        class="btn btn-outline-primary waves-effect waves-light"
                                                        style="float: right">취소
                                                </button>
                                            </a>
                                            <button type="submit"
                                                    class="btn btn-outline-primary waves-effect waves-light" style="float: right">수정
                                            </button>
                                        </div>
                                    </div>
                                </form>
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

<!-- Buttons examples -->
<script src="/assets/libs/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="/assets/libs/datatables.net-buttons-bs4/js/buttons.bootstrap4.min.js"></script>
<script src="/assets/libs/jszip/jszip.min.js"></script>
<script src="/assets/libs/pdfmake/build/pdfmake.min.js"></script>
<script src="/assets/libs/pdfmake/build/vfs_fonts.js"></script>
<script src="/assets/libs/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="/assets/libs/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="/assets/libs/datatables.net-buttons/js/buttons.colVis.min.js"></script>

<!-- apexcharts -->
<script src="/assets/libs/apexcharts/apexcharts.min.js"></script>

<!-- jquery.vectormap map -->
<script src="/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/assets/libs/admin-resources/jquery.vectormap/maps/jquery-jvectormap-us-merc-en.js"></script>

<%--<script src="/assets/js/pages/dashboard.init.js"></script>--%>

<script src="/assets/js/app.js"></script>

<script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
<script type="text/javascript"
        src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>


</body>

</html>