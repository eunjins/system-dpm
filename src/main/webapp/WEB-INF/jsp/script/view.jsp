<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Data Table | Qovex - Admin &amp; Dashboard Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Premium Multipurpose Admin &amp; Dashboard Template" name="description">
    <meta content="Themesbrand" name="author">
    <!-- App favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico">

    <!-- DataTables -->
    <link href="/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/datatables.net-buttons-bs4/css/buttons.bootstrap4.min.css" rel="stylesheet"
          type="text/css">

    <!-- Responsive datatable examples -->
    <link href="/assets/libs/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css" rel="stylesheet"
          type="text/css">

    <!-- Bootstrap Css -->
    <link href="/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css">
    <!-- Icons Css -->
    <link href="/assets/css/icons.min.css" rel="stylesheet" type="text/css">
    <!-- App Css-->
    <link href="/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css">

    <style type="text/css">.jqstooltip {
        position: absolute;
        left: 0px;
        top: 0px;
        visibility: hidden;
        background: rgb(0, 0, 0) transparent;
        background-color: rgba(0, 0, 0, 0.6);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);
        -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
        color: white;
        font: 10px arial, san serif;
        text-align: left;
        white-space: nowrap;
        padding: 5px;
        border: 1px solid white;
        box-sizing: content-box;
        z-index: 10000;
    }

    .jqsfield {
        color: white;
        font: 10px arial, san serif;
        text-align: left;
    }</style>

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
                                                        <button type="button"
                                                                class="btn btn-secondary waves-effect waves-light"
                                                                style="float: right"><span>엑셀 다운로드</span>
                                                        </button>
                                                    </a>
                                                </div>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                <div id="datatable-buttons_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                                    <div class="row">
                                        <div class="col-sm-12 col-md-6">
                                            <div class="dt-buttons btn-group flex-wrap">

                                            </div>
                                        </div>
                                        <div class="col-sm-12 col-md-6"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="datatable-buttons"
                                                   class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
                                                   style="border-collapse: collapse; border-spacing: 0px; width: 100%;"
                                                   role="grid" aria-describedby="datatable-buttons_info">
                                                <thead>
                                                <tr role="row">
                                                    <th class="sorting_asc" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1" style="width: 50px;" aria-sort="ascending"
                                                        aria-label="번호: activate to sort column descending">
                                                        <label class="col-md-4 col-form-label"></label>번호
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1" style="width: 270px;"
                                                        aria-label="디바이스 명: activate to sort column ascending">
                                                        <label class="col-md-5 col-form-label"></label>디바이스 명
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1" style="width: 270px;"
                                                        aria-label="실행 시간 (ms): activate to sort column ascending">
                                                        <label class="col-md-5 col-form-label"></label>실행 시간 (ms)
                                                    </th>
                                                </tr>
                                                </thead>

                                                <tbody id="measureList">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div id="row">
                                    <div class="col-md-10">
                                        <table id="chartable"
                                               class="table table-bordered dt-responsive nowrap dataTable no-footer dtr-inline"
                                               style="border-collapse: collapse; border-spacing: 0px; width: 100%;"
                                               role="grid" aria-describedby="datatable_info">
                                            <div class="col-sm-12" id="chart-container"></div>
                                        </table>
                                    </div>
                                    <div class="mb-3 row">
                                        <div class="col-md-10">
                                        </div>
                                        <div class="button-items">
                                            <a href="${contextPath}/scripts">
                                                <button type="button"
                                                        class="btn btn-secondary waves-effect waves-light"
                                                        style="float: right">목록
                                                </button>
                                            </a>
                                        </div>
                                    </div>
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
<!-- Responsive examples -->
<script src="/assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="/assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>

<!-- Datatable init js -->
<script src="/assets/js/pages/datatables.init.js"></script>

<!-- App js -->
<script src="/assets/js/app.js"></script>

<script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
<script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
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
<script type="text/javascript">
    let measureList = document.getElementById("measureList");
    measureList.innerHTML = '<c:forEach items="${measures}" var="measure" varStatus="object">' +
        '<tr class="odd">' +
        '<td class="dtr-control sorting_1" tabindex="0" align="right">${object.count}</td>' +
        '<td>${measure.deviceName}</td>' +
        '<td id=execTime align="right">' + Number(${measure.execTime}).toLocaleString('en') + '</td>' +
        ' </tr>' +
        '</c:forEach>'
</script>

</body>

</html>