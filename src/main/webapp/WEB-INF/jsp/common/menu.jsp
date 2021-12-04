<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>

<header id="page-topbar">
    <div class="navbar-header">
        <div class="d-flex">
            <button type="button" class="btn btn-sm px-3 d-lg-none header-item waves-effect waves-light"
                    data-bs-toggle="collapse" data-bs-target="#topnav-menu-content">
                <i class="fa fa-fw fa-bars"></i>
            </button>
            <div class="topnav">
                <div class="navbar navbar-light navbar-expand-lg topnav-menu">
                    <div class="collapse navbar-collapse" id="topnav-menu-content">
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle arrow-none" style="font-size: larger"
                                   href="${pageContext.request.contextPath}/devices" id="topnav-device"
                                   role="button">
                                    디바이스
                                </a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle arrow-none" style="font-size: larger"
                                   href="${pageContext.request.contextPath}/scripts" id="topnav-script"
                                   role="button">
                                    스크립트 측정
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="d-flex">
            <div class="dropdown d-inline-block">
                <button type="button" class="btn header-item waves-effect">
                    <a class="dropdown-item text-danger" style="font-size: larger"
                       href="${pageContext.request.contextPath}/logout">
                        <i class="bx bx-power-off font-size-16 align-middle me-1 text-danger"></i>로그아웃</a>
                </button>
            </div>
        </div>
    </div>
</header>