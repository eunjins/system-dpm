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
                    <div class="col-sm-12 col-md-6">
                        <div class="dataTables_length" id="datatable_length"><label>
                            <select id="select_status"
                                    name="datatable_length" aria-controls="datatable"
                                    class="custom-select form-control form-select">
                                <option value="">전체</option>
                                <option value="Y">활성화</option>
                                <option value="N">비활성화</option>
                            </select></label></div>
                    </div>
                    <div class="col-sm-12 col-md-7" style="text-align: right">
                        <label>
                            <select id="select_condition"
                                    name="datatable_type" aria-controls="datatable"
                                    class="custom-select form-control form-select">
                                <option value="measureName">디바이스 명</option>
                                <option value="insertDate">등록 일자</option>
                            </select>
                        </label>

                        <div class="col-sm-12 col-md-6" style="text-align: right">
                            <div id="datatable_filter" class="dataTables_filter">
                                <label id="search_bar">
                                    <input id="search_message" type="search" class="form-control" placeholder="검색어를 입력하세요"
                                           aria-controls="datatable" style="text-align: left">
                                </label>
                                <button id="button_search" type="button" class="btn btn-default btn-primary waves-effect waves-light">검색
                                </button>

                            </div>
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
                                                            aria-label="Position: activate to sort column ascending">
                                                            디바이스 명
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
                                                            aria-label="Office: activate to sort column ascending">JDK
                                                            버전
                                                        </th>
                                                        <th class="sorting" tabindex="0" aria-controls="datatable"
                                                            rowspan="1"
                                                            colspan="1"
                                                            aria-label="Office: activate to sort column ascending">상태
                                                        </th>
                                                    </tr>
                                                    </thead>

                                                    <tbody id="table">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12 col-md-12">
                                                <div class="dataTables_paginate paging_simple_numbers"
                                                     id="datatable_paginate">
                                                    <ul class="pagination justify-content-center" id="pageNo">
                                                        <li class="paginate_button page-item previous disabled"
                                                            id="datatable_previous"><a id="backPage"
                                                                                       href="#"
                                                                                       aria-controls="datatable"
                                                                                       data-dt-idx="0" tabindex="0"
                                                                                       class="page-link"
                                                                                       onclick="changePage(this.id)">&lt;</a>
                                                        </li>
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
                                                        <li class="paginate_button page-item next" id="datatable_next">
                                                            <a id="nextPage" onclick="changePage(this.id)"
                                                               href="#" aria-controls="datatable" data-dt-idx="6"
                                                               tabindex="0" class="page-link">&gt;</a></li>
                                                    </ul>
                                                </div>
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

<!-- JAVASCRIPT -->
<!-- JAVASCRIPT -->
<script>
    var allDeviceNo;
    var pageNo = 0;

    search();

    document.getElementById("button_search").addEventListener("click", search, false);
    document.getElementById("select_status").addEventListener("change", search, false);
    document.getElementById("select_condition").addEventListener("change", condition, false);

    function changePage(pageButtonId) {
        if (pageButtonId == "backPage") {
            if (pageNo != 0) {
                pageNo -= 1;
            }

        } else if (pageButtonId == "nextPage") {
            if (pageNo !== (parseInt(allDeviceNo / 10))) {
                pageNo += 1;
            }

        } else {
            pageNo = pageButtonId;
        }
    }

    function condition() {
        const selectCondition = document.getElementById("select_condition").value;
        if (selectCondition == "measureName") {
            document.getElementById("search_bar").innerHTML = '<input id="search_message" type="search" class="form-control" placeholder="검색어를 입력하세요"' +
                'aria-controls="datatable" style="text-align: left">';
        } else {
            document.getElementById("search_bar").innerHTML = '<input id="search_message" class="form-control" type="date" value="">';
        }
    }

    function search() {
        const selectStatus = document.getElementById("select_status").value;
        let status = "";
        if (selectStatus == "") {
            status = null;
        } else if (selectStatus == "Y") {
            status = "Y";
        } else if (selectStatus == "N") {
            status = "N";
        }
        let searchKeyword = "";
        const selectCondition = document.getElementById("select_condition").value;
        if (selectCondition == "measureName") {
            searchKeyword = {"name": document.getElementById("search_message").value, "status": status};
        } else {
            searchKeyword = {"insertDate": document.getElementById("search_message").value, "status": status};
        }

        $.ajax({
            url: "${pageContext.request.contextPath}/devices",
            type: "POST",
            data: JSON.stringify(searchKeyword),
            headers: {"Content-Type": "application/json;charset=UTF-8"},
            success: function (rows) {
                drawTable(rows);
            }
        })
    }

    function drawTable(responseJSON) {
        allDeviceNo = responseJSON.length;

        console.log((parseInt(allDeviceNo / 10)));

        let text = "";

        let endDeviceNo;

        if (allDeviceNo - (pageNo * 10) < 10) {
            endDeviceNo = allDeviceNo;
        } else {
            endDeviceNo = ((pageNo + 1) * 10);
        }

        for (let i = pageNo * 10; i < endDeviceNo; i++) {
            text +=
                '<tr class="odd">' +
                '<td style="">' + (i + 1) + '</td>' +
                '<td style="text-align: left"><a href="${contextPath}/devices/' + responseJSON[i].id + '">' + responseJSON[i].name +'</a>' +
                '</td>' +
                '<td style="text-align: left">' + responseJSON[i].hostName + '</td>' +
                '<td style="">' + responseJSON[i].insertDate + '</td>' +
                '<td style="">' + responseJSON[i].jdkVersion + '</td>' +
                '<td style="">';
                if ("Y" == responseJSON[i].status) {
                    text += '활성화';
                } else {
                    text += '비활성화';
                }
                text += '</td>' +
                '</tr>';
        }
        const table = document.getElementById("table");
        table.innerHTML = text;

        let endPageNo;
        if ((parseInt(allDeviceNo / 10)) < ((pageNo / 5) * 5) + 5) {
            endPageNo = ((parseInt(allDeviceNo / 10))) + 1;
        } else {
            endPageNo = ((pageNo / 5) * 5) + 5;
        }

        let pageNoHtml = "";

        pageNoHtml += '<li class="paginate_button page-item previous disabled"' +
            'id="datatable_previous"><a id="backPage"' +
            'href="#"' +
            'aria-controls="datatable"' +
            'data-dt-idx="0" tabindex="0"' +
            'class="page-link" onclick="changePage(this.id)">&lt;</a></li>';

        let count = 1;
        for (let i = ((pageNo / 5) * 5); i < endPageNo; i++) {
            if (pageNo == i) {
                pageNoHtml += '<li class="paginate_button page-item active"><a id="' + (i) + '" href="#"';
            } else {
                pageNoHtml += '<li class="paginate_button page-item "><a href="#"';
            }

            pageNoHtml += 'aria-controls="datatable"' +
                'data-dt-idx="' + (count++) + '"' +
                'tabindex="0"' +
                'class="page-link" onclick="changePage(this.id)" >' + (i + 1) + '</a>';
        }
        pageNoHtml += '<li class="paginate_button page-item next" id="datatable_next">' +
            '<a id="nextPage" onclick="changePage(this.id)"' +
            'href="#" aria-controls="datatable" data-dt-idx="' + (count) + '"' +
            'tabindex="0" class="page-link" onclick="changePage(this.id)">&gt;</a></li>'

        let pageTable = document.getElementById("pageNo");

        pageTable.innerHTML = pageNoHtml;
    }
</script>

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