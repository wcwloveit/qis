<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh-cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh-cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh-cn" class="no-js">
        <!--<![endif]-->
        <!-- BEGIN HEAD -->
<head>
        <meta charset="utf-8"/>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
        <title>新日电动车 | QIS平台 |<sitemesh:title/></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <meta content="" name="description"/>
        <meta content="" name="author"/>
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
        type="text/css" />
        <link href="${ctx}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"
        />
        <link href="${ctx}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet"
        type="text/css" />
        <link href="${ctx}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet"
        type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${ctx}/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css"
        />
        <link href="${ctx}/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="${ctx}/assets/layouts/layout4/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/assets/layouts/layout4/css/themes/light.min.css" rel="stylesheet" type="text/css"
        id="style_color" />
        <link href="${ctx}/assets/layouts/layout4/css/custom.min.css" rel="stylesheet" type="text/css" />
        <script src="${ctx}/assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" />
        <sitemesh:head/>
        <!-- END PAGE LEVEL STYLES -->
        <script>var ctx="${ctx}";</script>

        <style type="text/css">　　
                #searchList{
                        position: absolute;
                        left: 0px;
                        top:0px;
                }
        </style>　
</head>
        <!-- END HEAD -->
        <!-- BEGIN BODY -->
<body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo">
        <!-- BEGIN HEADER -->
        <%@ include file="/WEB-INF/layout/header.jsp" %>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->

        <div class="page-container">

         <%@ include file="/WEB-INF/layout/sidebar.jsp" %>

        <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
        <div class="page-content">
        <sitemesh:body/>
        </div>
        <!-- END CONTENT BODY -->
        </div>
        <!-- END CONTENT -->
        </div>
        <div class="page-footer">
        <div class="page-footer-inner"> @2018 &copy; 江苏新日电动车股份有限公司.
        <%--<a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" title="Purchase Metronic just for 27$ and get lifetime updates for free" target="_blank">Purchase Metronic!</a>--%>
        </div>
        <div class="scroll-to-top">
        <i class="icon-arrow-up"></i>
        </div>
        </div>

        <!-- END FOOTER -->
        <!--[if lt IE 9]>
        <script src="${ctx}/assets/global/plugins/respond.min.js"></script>
        <script src="${ctx}/assets/global/plugins/excanvas.min.js"></script>
        <![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="${ctx}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${ctx}/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${ctx}/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
        <script src="${ctx}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
        <script src="${ctx}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${ctx}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
        <script src="${ctx}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
        type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <%--<script src="${ctx}/assets/global/scripts/app.js" type="text/javascript"></script>--%>
        <script src="${ctx}/assets/global/scripts/metronic.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="${ctx}/assets/global/scripts/metronic.js" type="text/javascript"></script>
        <script src="${ctx}/assets/global/scripts/layout.js" type="text/javascript"></script>

        <%--<script src="${ctx}/assets/layouts/layout4/scripts/layout.js" type="text/javascript"></script>--%>
        <%--<script src="${ctx}/assets/layouts/layout4/scripts/demo.js" type="text/javascript"></script>--%>
        <%--<script src="${ctx}/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>--%>
        <script>
        var modules;
        $.ajax({
        url: '${rc.contextPath}/module/search/',
        type: 'GET',
        async:false,
        success: function (msg) {
        modules=msg;
        }
        });

        $("#searchList").hide();

        $("input[name=ajaxtest]").blur(function () {  //失去焦点收起
        setTimeout(function(){
        $("#searchList").hide();
        },500);
        });
        $("input[name=ajaxtest]").keyup(function(event) {
        var name=$("input[name=ajaxtest]").val()
        if( name== ""){
        //如果文本框没有值
        //先清空所有的 li ，即候选框
        $("#searchList li").remove();
        //隐藏 ul ，只是为了美观
        $("#searchList").hide();
        return;
        }
        if(name!=""){
        $("#searchList li").remove();
        for(var i=0;i<modules.length;i++){
        if(((modules[i].name).indexOf(name)!=-1)||((modules[i].code).indexOf(name)!=-1)&&((modules[i].isMenu)==0)){
        //循环添加li节点
        $("#searchList").append("<li><a  tabindex='-1' href='${rc.contextPath}"+modules[i]                                           .linkUrl+"'>"+modules[i].name+"</a></li>");
        $("#searchList").show();
        }
        }
        //显示 ul 节点
        }
        });

        jQuery(document).ready(function(){
        $("#data_table_search").bind("keypress",function(e){ //键盘监听，按enter键搜索
        if(e.keyCode == 13){
        $("#data_table_search .filter-submit").click();
        }
        });
        // initiate layout and plugins
        Metronic.init(); // init metronic core components
        Layout.init();
        });
        </script>
        <sitemesh:getProperty property="page.script"/>
        <!-- END JAVASCRIPTS -->
</body>
        <!-- END BODY -->
</html>