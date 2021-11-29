<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" charset="utf-8" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
<h2>디바이스 정보 목록</h2>
<hr>
<div style="float: left">
    상태
    <select name="status" id="select_status">
        <option value="all" selected="selected">전체</option>
        <option value="activation">활성화</option>
        <option value="disabled">비활성화</option>
    </select>

    검색 조건
    <select name="condition" id="select_condition">
        <option value="name" selected="selected">디바이스 명</option>
        <option value="insertDate">등록 일자</option>
    </select>
</div>
<div id = "search_bar" style="float: left">
    <input type="text" name="message" id="search_message"/>
</div>
<button id = "button_search" >검색</button>
<p>
<table border="1" id="table">
</table>
</p>
<p>
<table border="1">
    <tr id="pageNo">
        <td>
            <a id="firstPage" href="javascript:void(0);" onclick="changPage(this.id);"><<</a>
        </td>
        <td>
            <a id="backPage" href="javascript:void(0);" onclick="changPage(this.id)"><</a>
        </td>

        <td>
            <a id="nextPage" href="javascript:void(0);" onclick="changPage(this.id)">></a>
        </td>
        <td>
            <a id="lastPage" href="javascript:void(0);" onclick="changPage(this.id)">>></a>
        </td>
    </tr>
</table>
</p>
<script>
    var allDeviceNo;
    var pageNo = 0;

    search();

    document.getElementById("button_search").addEventListener("click", search, false);
    document.getElementById("select_status").addEventListener("change", search, false);
    document.getElementById("select_condition").addEventListener("change", condition, false);

    function changPage(pageButtonId) {
        if (pageButtonId == "firstPage") {
            pageNo = 0;

        } else if (pageButtonId == "backPage") {
            if (pageNo != 0) {
                pageNo -= 1;
            }

        } else if (pageButtonId == "nextPage") {
            if (pageNo != 0) {
                pageNo -= 1;
            }

        } else if (pageButtonId == "backPage") {
            if (pageNo !== (parseInt(allDeviceNo / 10))) {
                pageNo += 1;
            }

        } else if (pageButtonId == "lastPage") {
            pageNo = (parseInt(allDeviceNo / 10));

        } else {
            pageNo = pageButtonId;
        }
    }

    function condition() {
        const selectCondition = document.getElementById("select_condition").value;
        if (selectCondition == "name") {
            document.getElementById("search_bar").innerHTML = '<input type="text" name="message" id="search_message"/>';
        } else {
            document.getElementById("search_bar").innerHTML = '<input type="date" name="message" id="search_message"/>';
        }
    }

    function search() {
        const selectStatus = document.getElementById("select_status").value;
        let status = "";
        if (selectStatus == "all") {
            status = null;
        } else if (selectStatus == "activation") {
            status = "Y";
        } else if (selectStatus == "disabled") {
            status = "N";
        }
        let searchKeyword = "";
        const selectCondition = document.getElementById("select_condition").value;
        if (selectCondition == "name") {
            searchKeyword = {"name": document.getElementById("search_message").value, "status" : status};
        } else {
            searchKeyword = {"insertDate" : document.getElementById("search_message").value,  "status" : status};
        }

        $.ajax({
            url: "${pageContext.request.contextPath}/devices",
            type: "POST",
            data: JSON.stringify(searchKeyword),
            headers: { "Content-Type" : "application/json;charset=UTF-8" },
            success: function(rows) {
                drawTable(rows);
            }
        })
    }

    function drawTable(responseJSON) {
        allDeviceNo = responseJSON.length;

        console.log((parseInt(allDeviceNo / 10)));

        let text = "";
        text +=
            "<tr>" +
            "        <td>번호</td>" +
            "        <td>디바이스 명</td>" +
            "        <td>호스트 명</td> " +
            "        <td>등록 일자</td>" +
            "        <td>JDK 버전</td>" +
            "        <td>상태</td>" +
            "</tr>";

        let endDeviceNo;

        if (allDeviceNo - (pageNo * 10) < 10) {
            endDeviceNo = allDeviceNo;
        } else {
            endDeviceNo = ((pageNo + 1) * 10);
        }

        for (let i = pageNo * 10; i < endDeviceNo; i++) {
            text +=
                "<tr>" +
                "<td width='50'>" + (i + 1).toString() + "</td>" +
                "<td width='200'>" +
                "<a href=" + sessionStorage.getItem("contextpath") + "/devices/" + responseJSON[i].id + ">" +
                responseJSON[i].name +
                "</a>" +
                "</td>" +
                "<td>" + responseJSON[i].hostName + "</td>" +
                "<td>" + responseJSON[i].insertDate + "</td>" +
                "<td>" + responseJSON[i].jdkVersion + "</td>";
            if (responseJSON[i].status == 'Y') {
                text += "<td>" + "활성화" + "</td>";
            } else {
                text += "<td>" + "비활성화" + "</td>";
            }
            text += "</tr>";
        }
        const table = document.getElementById("table");
        table.innerHTML = text;

        let endPageNo;
        if ((parseInt(allDeviceNo / 10)) < ((pageNo / 5) * 5) + 5) {
            endPageNo = ((parseInt(allDeviceNo / 10))) + 1;
        } else {
            endPageNo = ((pageNo / 5) * 5) + 5;
        }

        let pageTable = document.getElementById("pageNo");
        for (let i = ((pageNo / 5) * 5); i < endPageNo; i++) {
            let cell = pageTable.insertCell(i + 2);

            cell.innerHTML = " <a id=" + (i) + " href='javascript:void(0);' onclick='changPage(this.id)'>" + (i + 1) + "</a>";
        }

    }
</script>
</body>
</html>