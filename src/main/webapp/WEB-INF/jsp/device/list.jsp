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

    <link href="/assets/css/style.css" type="text/css"/>

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
                            <h2 class="page-title mb-0 font-size-40">디바이스 정보 목록</h2>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12 col-md-5">
                        <div class="dataTables_length" id="datatable_length"><label><select
                                name="datatable_length" aria-controls="datatable"
                                class="custom-select form-control form-select">
                            <option>전체</option>
                            <option value="Y">활성화</option>
                            <option value="N">비활성화</option>
                        </select></label></div>
                    </div>
                    <div class="col-sm-12 col-md-7" style="text-align: right">
                        <label>
                        <select name="datatable_type" aria-controls="datatable"
                                class="custom-select form-control form-select">
                            <option value="measureName">디바이스 명</option>
                            <option value="insertDate">등록 일자</option>
                        </select>
                        </label>

                        <%--                        <div id="datatable_filter" class="dataTables_filter">--%>
<%--                        <div class="input-group" id="datepicker2">--%>
<%--                            <input type="text" class="form-control" placeholder="mm/dd/yyyy" data-date-container="#datepicker2" data-provide="datepicker" data-date-autoclose="true">--%>
<%--                            <span class="input-group-text"><i class="mdi mdi-calendar"></i></span>--%>
<%--                        </div>--%>
                        <label>
                            <input type="search" class="form-control mdi mdi-calendar" placeholder="검색어를 입력하세요"
                                   aria-controls="datatable" style="text-align: left">
<%--                            <i class="mdi mdi-calendar"></i>--%>
                        </label>
                        <button type="button" class="btn btn-default btn-primary waves-effect waves-light">검색
                        </button>
                        <%--                        </div>--%>
                    </div>
                </div>
                <!-- end page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">

                                <div id="datatable_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="datatable"
                                                   class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline"
                                                   style="border-collapse: collapse; border-spacing: 0px; width: 100%; text-align: center; outline-style: solid;
                                                          outline-width: thin" ; role="grid"
                                                   aria-describedby="datatable_info">
                                                <thead>
                                                <tr role="row" bgcolor="#4169e1" style="color: #FFFFFF">
                                                    <th class="sorting_asc" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1" style="width: 5%;" aria-sort="ascending"
                                                        aria-label="Name: activate to sort column descending">번호
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1" style="width: 30%;"
                                                        aria-label="Position: activate to sort column ascending">디바이스 명
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1" style="width: 30%;"
                                                        aria-label="Office: activate to sort column ascending">호스트 명
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1" style="width: 15%;"
                                                        aria-label="Office: activate to sort column ascending">등록 일자
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1" style="width: 10%;"
                                                        aria-label="Office: activate to sort column ascending">JDK 버전
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1"
                                                        colspan="1"
                                                        aria-label="Office: activate to sort column ascending">상태
                                                    </th>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <c:forEach items="${devices}" var="device" varStatus="object">
                                                    <tr class="odd">
                                                        <td style="">${object.count}</td>
                                                        <td style="text-align: left"><a
                                                                href="${contextPath}/devices/${device.id}">${device.name}</a>
                                                        </td>
                                                        <td style="text-align: left">${device.hostName}</td>
                                                        <td style="">${device.insertDate}</td>
                                                        <td style="">${device.jdkVersion}</td>
                                                        <td style="">
                                                            <c:choose>
                                                                <c:when test="${device.status eq 'Y'}">
                                                                    활성화
                                                                </c:when>
                                                                <c:when test="${device.status eq 'N'}">
                                                                    비활성화
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
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

<script src="/assets/js/app.js"></script>

<script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
<script type="text/javascript"
        src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>


</body>

</html>