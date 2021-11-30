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
    <link rel="shortcut icon" href="/assets/images/favicon.ico">

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
                            <h2 class="page-title mb-0 font-size-40">스크립트 측정 결과</h2>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <!-- start row -->
                <div class="row">
                    <div class="col-xl-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">측정 결과 명 :</label>
                                    <div class="col-md-7">
                                        <label class="col-md-12 col-form-label">${measures[0].name}</label>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">스크립트 명 :</label>
                                    <div class="col-md-7">
                                        <label class="col-md-12 col-form-label">${script.name}</label>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label class="col-md-2 col-form-label">업로드 일시 :</label>
                                    <div class="col-md-7">
                                        <label class="col-md-12 col-form-label">${script.uploadPoint}</label>
                                    </div>
                                </div>
                                <c:forEach items="${attaches}" var="attach">
                                    <c:choose>
                                        <c:when test="${attach.division eq 'S'}">
                                            <div class="mb-3 row">
                                                <label class="col-md-2 col-form-label">소스 파일 :</label>
                                                <div class="col-md-7">
                                                    <label class="col-md-12 col-form-label">
                                                        <a href="/scripts/file/${attach.no}">${attach.name}.java</a>
                                                    </label>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${attach.division eq 'C'}">
                                            <div class="mb-3 row">
                                                <label class="col-md-2 col-form-label">클래스 파일 :</label>
                                                <div class="col-md-8">
                                                    <label class="col-md-10 col-form-label">
                                                        <a href="/scripts/file/${attach.no}">${attach.name}.class</a>
                                                    </label>
                                                </div>
                                                <div class="col-md-2">
                                                    <a href="${contextPath}/scripts/excel/${script.no}">
                                                    <button class="btn btn-outline-primary waves-effect waves-light"
                                                            tabindex="0" aria-controls="datatable"
                                                            type="button" style="float: right"><span>엑셀 다운로드</span>
                                                    </button>
                                                    </a>
                                                </div>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>

                                <div class="col-sm-12">
                                    <table id="datatable"
                                           class="table table-bordered dt-responsive nowrap dataTable no-footer dtr-inline"
                                           style="border-collapse: collapse; border-spacing: 0px; width: 100%;"
                                           role="grid" aria-describedby="datatable_info">
                                        <thead>
                                        <tr role="row">
                                            <th class="sorting_asc" tabindex="0" aria-controls="datatable" rowspan="1"
                                                colspan="1" style="width: 80px;" aria-sort="ascending"
                                                aria-label="Name: activate to sort column descending">
                                                <label class="col-md-5 col-form-label"></label>번호
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                colspan="1" style="width: 250px;"
                                                aria-label="Position: activate to sort column ascending">
                                                <label class="col-md-5 col-form-label"></label>디바이스 명
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                colspan="1" style="width: 100px;"
                                                aria-label="Office: activate to sort column ascending">
                                                <label class="col-md-4 col-form-label"></label>실행 시간 (ms)
                                            </th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach items="${measures}" var="measure" varStatus="object">
                                            <tr class="odd">
                                                <td class="dtr-control sorting_1" tabindex="0" align="right">${object.count}</td>
                                                <td align="right">${measure.deviceName}</td>
                                                <td align="right">${measure.execTime}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <table id="chartable"
                                       class="table table-bordered dt-responsive nowrap dataTable no-footer dtr-inline"
                                       style="border-collapse: collapse; border-spacing: 0px; width: 100%;"
                                       role="grid" aria-describedby="datatable_info">
                                    <div class="col-sm-12" id="chart-container"></div>
                                </table>
                            </div>
                        </div>
                    </div>


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
<script src="/assets/libs/jquery/jquery.min.js"></script>
<script src="/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/libs/metismenu/metisMenu.min.js"></script>
<script src="/assets/libs/simplebar/simplebar.min.js"></script>
<script src="/assets/libs/node-waves/waves.min.js"></script>
<script src="/assets/libs/jquery-sparkline/jquery.sparkline.min.js"></script>

<!-- Required datatable js -->
<script src="/assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
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

<script src="/assets/js/pages/dashboard.init.js"></script>

<script src="/assets/js/app.js"></script>

<script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
<script type="text/javascript"
        src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
<script type="text/javascript">
    var deviceNames = [];
    var execTimes = [];

    const chartData = [
        <c:forEach items="${measures}" var="measure" varStatus="object">
        {
            "label": "${measure.deviceName}",
            "value": "${measure.execTime}"
        },
        </c:forEach>
    ];

    const chartConfig = {
        type: 'column2d',
        renderAt: 'chart-container',
        width: '100%',
        height: '400',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "yAxisName": "실행 시간 (ms)",
                "theme": "fusion",
                "showBorder": 1
            },
            "data": chartData
        }
    };
    FusionCharts.ready(function () {
        var fusioncharts = new FusionCharts(chartConfig);
        fusioncharts.render();
    });

</script>

</body>

</html>