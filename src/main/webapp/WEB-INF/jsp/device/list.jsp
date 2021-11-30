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

<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>

<body data-layout="horizontal" data-layout-size="boxed">

<div class="container-fluid">
    <!-- Begin page -->
    <div id="layout-wrapper">

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
                            <h2 class="page-title mb-0 font-size-40">디바이스 정보 목록</h2>
                        </div>

                        <div class="row">
                            <div class=" col-sm-12 col-md-6"/>
                                <div id="datatable_filter" class="dataTables_filter" >
                                    <form action="/devices">
                                        <input type="search" class="form-control form-control-sm" name="q"
                                                  placeholder="검색어를 입력하세요" aria-controls="datatable">
                                    </form>
                                </div>
                        </div>
                    </div>

                </div>

            </div>

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">


                                <div id="datatable_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="datatable"
                                                   class="table table-bordered dt-responsive nowrap dataTable no-footer dtr-inline"
                                                   style="border-collapse: collapse; border-spacing: 0px; width: 100%;"
                                                   role="grid" aria-describedby="datatable_info">
                                                <thead>
                                                <tr role="row">
                                                    <th class="sorting_asc" tabindex="0" aria-controls="datatable"
                                                        rowspan="1" colspan="1" style="width: 10px;"
                                                        aria-sort="ascending"
                                                        aria-label="Name: activate to sort column descending">번호
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1" colspan="1" style="width: 170px;"
                                                        aria-label="Position: activate to sort column ascending">디바이스 명
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1" colspan="1" style="width: 170px;"
                                                        aria-label="Office: activate to sort column ascending">호스트 명
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1" colspan="1" style="width: 80px;"
                                                        aria-label="Age: activate to sort column ascending">등록 일자
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1" colspan="1" style="width: 50px;"
                                                        aria-label="Start date: activate to sort column ascending">JDK 버전
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable"
                                                        rowspan="1" colspan="1" style="width: 0px;"
                                                        aria-label="Salary: activate to sort column ascending">상태
                                                    </th>
                                                </tr>
                                                </thead>

                                                <tbody>
                                                <c:forEach items="${devices}" var="device" varStatus="object" >
                                                <tr class="odd">
<%--                                                    <td class="dtr-control sorting_1" tabindex="0">${object.count}</td>--%>
                                                    <td style="">${object.count}</td>
                                                    <td style=""><a href="${contextPath}/devices/${device.id}">${device.name}</a></td>
                                                    <td style="">${device.hostName}</td>
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
                                        <div class="col-sm-12 col-md-5">
                                            <div class="dataTables_info" id="datatable_info" role="status"
                                                 aria-live="polite">
                                            </div>
                                        </div>

                                        <div class="col-sm-12 col-md-7">
                                            <div class="dataTables_paginate paging_simple_numbers"
                                                 id="datatable_paginate">
                                                <ul class="pagination">
                                                    <li class="paginate_button page-item previous disabled"
                                                        id="datatable_previous"><a href="#" aria-controls="datatable"
                                                                                   data-dt-idx="0" tabindex="0"
                                                                                   class="page-link"><</a></li>
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
                                                    <li class="paginate_button page-item next" id="datatable_next"><a
                                                            href="#" aria-controls="datatable" data-dt-idx="7"
                                                            tabindex="0" class="page-link">></a></li>
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


                <!-- end row -->

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
<!-- JAVASCRIPT -->
<%--<script src="/assets/libs/jquery/jquery.min.js"></script>--%>
<%--<script src="/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>--%>
<%--<script src="/assets/libs/metismenu/metisMenu.min.js"></script>--%>
<%--<script src="/assets/libs/simplebar/simplebar.min.js"></script>--%>
<%--<script src="/assets/libs/node-waves/waves.min.js"></script>--%>
<%--<script src="/assets/libs/jquery-sparkline/jquery.sparkline.min.js"></script>--%>

<%--<!-- apexcharts -->--%>
<%--<script src="/assets/libs/apexcharts/apexcharts.min.js"></script>--%>

<%--<!-- Required datatable js -->--%>
<script src="/assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
<%--<!-- Buttons examples -->--%>
<%--<script src="/assets/libs/datatables.net-buttons/js/dataTables.buttons.min.js"></script>--%>
<%--<script src="/assets/libs/datatables.net-buttons-bs4/js/buttons.bootstrap4.min.js"></script>--%>
<%--<script src="/assets/libs/jszip/jszip.min.js"></script>--%>
<%--<script src="/assets/libs/pdfmake/build/pdfmake.min.js"></script>--%>
<%--<script src="/assets/libs/pdfmake/build/vfs_fonts.js"></script>--%>
<%--<script src="/assets/libs/datatables.net-buttons/js/buttons.html5.min.js"></script>--%>
<%--<script src="/assets/libs/datatables.net-buttons/js/buttons.print.min.js"></script>--%>
<%--<script src="/assets/libs/datatables.net-buttons/js/buttons.colVis.min.js"></script>--%>

<%--<!-- jquery.vectormap map -->--%>
<%--<script src="/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.min.js"></script>--%>
<%--<script src="/assets/libs/admin-resources/jquery.vectormap/maps/jquery-jvectormap-us-merc-en.js"></script>--%>

<%--<script src="/assets/js/pages/dashboard.init.js"></script>--%>

<%--<script src="/assets/js/app.js"></script>--%>
<%--<!-- Responsive examples -->--%>
<%--<script src="/assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>--%>
<%--<script src="/assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>--%>

<%--<!-- Datatable init js -->--%>
<%--<script src="/assets/js/pages/datatables.init.js"></script>--%>

<%--<!-- App js -->--%>
<%--<script src="/assets/js/app.js"></script>--%>

</body>

</html>