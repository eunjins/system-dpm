<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" charset="utf-8">
        sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
    </script>
</head>
<body>
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
    <tr>
        <td>번호</td>
        <td>디바이스 명</td>
        <td>호스트 명</td>
        <td>등록 일자</td>
        <td>JDK 버전</td>
        <td>상태</td>
    </tr>

    <c:forEach items="${devices}" var="device" varStatus="object">
        <tr>
            <td width="50">${object.count}</td>
            <td width="200">
                <a href="${contextPath}/devices/${device.id}">${device.name}</a>
            </td>
            <td>${device.hostName}</td>
            <td>${device.insertDate}</td>
            <td>${device.jdkVersion}</td>
            <td>
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
</table>
</p>
<p>
<table border="1">
    <tr>
        <td>
            <a href="${contextPath}/devices"><<</a>
        </td>
        <td>
            <a href="${contextPath}/devices"><</a>
        </td>
        <td>
            <a href="${contextPath}/devices">1</a>
        </td>
        <td>
            <a href="${contextPath}/devices">2</a>
        </td>
        <td>
            <a href="${contextPath}/devices">3</a>
        </td>
        <td>
            <a href="${contextPath}/devices">4</a>
        </td>
        <td>
            <a href="${contextPath}/devices">5</a>
        </td>
        <td>
            <a href="${contextPath}/devices">></a>
        </td>
        <td>
            <a href="${contextPath}/devices">>></a>
        </td>
    </tr>
</table>
</p>
<script>
    document.getElementById("button_search").addEventListener("click", search, false);
    document.getElementById("select_status").addEventListener("change", search, false);
    document.getElementById("select_condition").addEventListener("change", condition, false);
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
        const xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = function () {
            console.log(xmlHttpRequest.status);
            if (xmlHttpRequest.status == 200) {
                drawTable(JSON.parse(xmlHttpRequest.responseText));
            }
        };
        xmlHttpRequest.open("POST", "${pageContext.request.contextPath}/devices", true);
        xmlHttpRequest.setRequestHeader("Content-Type", "application/json");
        xmlHttpRequest.send(JSON.stringify(searchKeyword));
    }
    function drawTable(responseJSON) {
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

        for (let i = 0; i < responseJSON.length; i++) {
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
    }
</script>
</body>
</html>