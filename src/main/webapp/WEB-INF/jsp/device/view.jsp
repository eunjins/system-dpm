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

<body data-layout="horizontal" data-layout-size="boxed" style="font-family: 'NanumSquare'; font-size: medium; font-weigth: bold">

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

                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">디바이스 ID:</label>
                                    <label class="col-md-6 col-form-label" >${device.id}</label>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">디바이스 명:</label>
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

                                <div class="row">
                                    <div class="button-items">
                                        <a href="${contextPath}/devices">
                                            <button type="button"
                                                    class="btn btn-default btn-primary waves-effect waves-light"
                                                    style="float: right">목록
                                            </button>
                                        </a>
<%--                                        <a href="${contextPath}/devices/${device.id}/form">--%>
<%--                                            <button type="button"--%>
<%--                                                    class="btn btn-default btn-primary waves-effect waves-light"--%>
<%--                                                    style="float: right">수정--%>
<%--                                            </button>--%>
<%--                                        </a>--%>
                                        <button type="button" class="btn btn-default btn-primary" style="float: right"
                                                data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                            수정
                                        </button>
                                    </div>
                                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" style="display: none;" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="staticBackdropLabel" >수정 확인
                                                    </h4>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body" style="font-size: large; color: blue; text-align: center" >
                                                    디바이스를 수정 하시겠습니까?
                                                </div>
                                                <div class="modal-footer">
                                                    <a href="${contextPath}/devices/${device.id}/form" class="btn btn-default btn-primary">예</a>
                                                    <a href="${contextPath}/devices/${device.id}">
                                                        <button type="button"
                                                                class="btn btn-default btn-primary waves-effect waves-light"
                                                                style="float: right">취소
                                                        </button>
                                                    </a>
<%--                                                    <button type="button" class="btn btn-primary">예</button>--%>
<%--                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>--%>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
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