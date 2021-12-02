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

    <jsp:include page="/WEB-INF/jsp/common/top.jsp"/>
</head>

<body data-layout="horizontal" data-layout-size="boxed"
      style="font-family: 'NanumSquare'; font-size: medium; font-weigth: bold">

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
                            <h2 class="page-title mb-0 font-size-40">스크립트 측정 결과 목록</h2>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <!-- start row -->
                <div class="row">
                    <div class="col-xl-12">

                        <div class="row">
                            <div class="col-sm-12 col-md-12" style="text-align: right">
                                <div id="datatable_filter" class="dataTables_filter">
                                    <label>
                                    <select name="datatable_type" aria-controls="datatable"
                                            class="custom-select form-control form-select">
                                        <option value="measureName">측정 결과 명</option>
                                        <option value="scriptName">스크립트 명</option>
                                        <option value="uploadPoint">업로드 일자</option>
                                    </select>
                                    </label>
                                    <label>
                                        <input type="search"
                                               class="form-control"
                                               placeholder="검색어를 입력하세요"
                                               aria-controls="datatable" style="text-align: left">
                                    </label>
                                    <button type="button"
                                            class="btn btn-default btn-primary waves-effect waves-light">검색
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-body">

                                <div class="col-sm-12">
                                    <table id="datatable"
                                           class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline"
                                           style="border-collapse: collapse; border-spacing: 0px; width: 100%; text-align: center; outline-style: solid;
                                                  outline-width: thin" ; role="grid" aria-describedby="datatable_info">
                                        <thead>
                                        <tr role="row" bgcolor="#4169e1" style="color: #FFFFFF">
                                            <th class="sorting_asc" tabindex="0" aria-controls="datatable" rowspan="1"
                                                colspan="1" style="width: 5%;" aria-sort="ascending"
                                                aria-label="Name: activate to sort column descending">번호
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                colspan="1" style="width: 25%;"
                                                aria-label="Position: activate to sort column ascending">측정 결과 명
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                colspan="1" style="width: 25%;"
                                                aria-label="Office: activate to sort column ascending">스크립트 명
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                colspan="1" style="width: 30%;"
                                                aria-label="Office: activate to sort column ascending">업로드 일시
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                colspan="1"
                                                aria-label="Office: activate to sort column ascending">상태
                                            </th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach items="${scripts}" var="script" varStatus="object">
                                            <tr class="odd">
                                                <td class="dtr-control sorting_1" tabindex="0">${object.count}</td>
                                                <td style="text-align: left">${scriptMeasure[object.count - 1].name}</td>
                                                <td style="text-align: left">${script.name}</td>
                                                <td>${script.uploadPoint}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${scriptMeasure[object.count - 1].status eq 'N'}">
                                                            측정 중
                                                        </c:when>
                                                        <c:when test="${scriptMeasure[object.count - 1].status eq 'Y'}">
                                                            <a href="${contextPath}/scripts/${script.no}">결과 보기</a>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12 col-md-12">
                                        <div class="dataTables_paginate paging_simple_numbers"
                                             id="datatable_paginate">
                                            <ul class="pagination justify-content-center">
                                                <li class="paginate_button page-item previous disabled"
                                                    id="datatable_previous"><a href="#" aria-controls="datatable"
                                                                               data-dt-idx="0" tabindex="0"
                                                                               class="page-link">&lt;</a></li>
                                                <li class="paginate_button page-item active"><a href="#"
                                                                                                aria-controls="datatable"
                                                                                                data-dt-idx="1"
                                                                                                tabindex="0"
                                                                                                class="page-link">1</a>
                                                </li>
                                                <li class="paginate_button page-item "><a href="#"
                                                                                          aria-controls="datatable"
                                                                                          data-dt-idx="2"
                                                                                          tabindex="0"
                                                                                          class="page-link">2</a>
                                                </li>
                                                <li class="paginate_button page-item "><a href="#"
                                                                                          aria-controls="datatable"
                                                                                          data-dt-idx="3"
                                                                                          tabindex="0"
                                                                                          class="page-link">3</a>
                                                </li>
                                                <li class="paginate_button page-item "><a href="#"
                                                                                          aria-controls="datatable"
                                                                                          data-dt-idx="4"
                                                                                          tabindex="0"
                                                                                          class="page-link">4</a>
                                                </li>
                                                <li class="paginate_button page-item "><a href="#"
                                                                                          aria-controls="datatable"
                                                                                          data-dt-idx="5"
                                                                                          tabindex="0"
                                                                                          class="page-link">5</a>
                                                </li>
                                                <li class="paginate_button page-item "><a href="#"
                                                                                          aria-controls="datatable"
                                                                                          data-dt-idx="6"
                                                                                          tabindex="0"
                                                                                          class="page-link">6</a>
                                                </li>
                                                <li class="paginate_button page-item next" id="datatable_next"><a
                                                        href="#" aria-controls="datatable" data-dt-idx="7"
                                                        tabindex="0" class="page-link">&gt;</a></li>
                                            </ul>
                                        </div>
                                        <div class="button-items">
                                            <a href="${contextPath}/scripts/form">
                                                <button type="button"
                                                        class="btn btn-default btn-primary waves-effect waves-light"
                                                        style="float: right">등록
                                                </button>
                                            </a>
                                        </div>
                                    </div>
<%--                                    <div class="col-sm-12 col-md-5">--%>

<%--                                    </div>--%>
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

<script src="/assets/js/pages/dashboard.init.js"></script>

<script src="/assets/js/app.js"></script>

<script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
<script type="text/javascript"
        src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>


</body>

</html>