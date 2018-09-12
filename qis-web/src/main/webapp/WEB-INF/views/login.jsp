<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <title>登录页</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>
    <link href="${ctx}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${ctx}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="${ctx}/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="${ctx}/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css"/>
    <link href="${ctx}/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${ctx}/assets/pages/css/login.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/assets/layouts/layout4/css/common.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME LAYOUT STYLES -->
    <!-- END THEME LAYOUT STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->

<body class="login">
<div class="menu-toggler sidebar-toggler"></div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
    <a href="${ctx}/login">
        <img src="${ctx}/assets/layouts/layout4/img/logo-light.png" alt="" width="80"/>
        <span>管理系统</span>
    </a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="${ctx}/login" method="post">
        <h3 class="form-title QIS-text-primary">登录</h3>
        <div class="">
            <%
                String error = (String) request.getAttribute(org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
                if (error != null) {
            %>
            <div class="alert alert-danger">
                <button class="close" data-close="alert"></button>
                <span><%
                    if (error.contains("DisabledAccountException")) {
                        out.print("用户已被屏蔽,请登录其他用户.");
                    } else if (error.contains("UnknownAccountException")) {
                        out.print("用户不存在,请检查后重试!");
                    } else if (error.contains("IncorrectCredentialsException")) {
                        out.print("密码错误,请检查密码!");
                    } else {
                        out.print("登录失败，请重试.");
                    }
                %></span>
            </div>
            <%}%>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">账号</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off"
                   placeholder="账号" name="username"/>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off"
                   placeholder="密码" name="password"/>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="rememberMe" value="true"> 记住我
            </label>
        </div>
        <button type="submit" class="btn btn-block QIS-btn uppercase">登录</button>

    </form>
    <!-- END LOGIN FORM -->


    <!-- END REGISTRATION FORM -->
</div>
<div class="copyright"> @2018 &copy; 江苏新日电动车股份有限公司.</div>
<!--[if lt IE 9]>
<script src="${ctx}/assets/global/plugins/respond.min.js"></script>
<script src="${ctx}/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="${ctx}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${ctx}/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/jquery-validation/js/additional-methods.min.js"
        type="text/javascript"></script>
<script src="${ctx}/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${ctx}/assets/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/assets/pages/scripts/login.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<!-- END THEME LAYOUT SCRIPTS -->
</body>

</html>