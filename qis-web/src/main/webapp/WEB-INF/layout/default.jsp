    <%@ page import="com.xinri.po.module.ModuleInfoes" %>
        <%@ page import="java.util.Collections" %>
        <%@ page import="com.xinri.service.module.impl.ModuleInfoesServiceImpl" %>
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

        </head>
        <!-- END HEAD -->
        <!-- BEGIN BODY -->
        <body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo">
        <!-- BEGIN HEADER -->
        <div class="page-header navbar navbar-fixed-top">
        <!-- BEGIN HEADER INNER -->
        <div class="page-header-inner ">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
        <a href="${ctx}/main">
        <img src="${ctx}/assets/layouts/layout4/img/logo-light.png" alt="logo" class="logo-default" />
        </a>

        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse"
        data-target=".navbar-collapse"> </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN PAGE TOP -->
        <div class="page-top">
        <!-- BEGIN HEADER SEARCH BOX -->
        <!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
        <form class="search-form" action="page_general_search_2.html" method="GET">
        <div class="input-group">
        <input type="text" class="form-control input-sm" placeholder="Search..." name="query">
        <span class="input-group-btn">
        <a href="javascript:;" class="btn submit">
        <i class="icon-magnifier"></i>
        </a>
        </span>
        </div>
        </form>
        <!-- END HEADER SEARCH BOX -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="top-menu">
        <ul class="nav navbar-nav pull-right">
        <li class="separator hide"> </li>
        <!-- BEGIN USER LOGIN DROPDOWN -->
        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
        <li class="dropdown dropdown-user dropdown-dark">
        <a href="javascript:;" class="dropdown-toggle">
        <span class="username username-hide-on-mobile">
        <shiro:principal property="name"/>
        </span>
        <!-- DOC: Do not remove below empty space(&nbsp;) as its purposely used -->
        <img alt="" class="img-circle" src="${ctx}/assets/layouts/layout4/img/avatar9.jpg" /> </a>
        </li>

        <li class="separator hide"> </li>

        <li class="dropdown dropdown dropdown-user dropdown-dark">
        <a href="${ctx}/logout" class="dropdown-toggle">
        <i class="icon-logout"></i>退出</a>
        </li>
        </ul>
        </li>
        </ul>
        </div>
        <!-- END TOP NAVIGATION MENU -->
        </div>
        <!-- END PAGE TOP -->
        </div>
        <!-- END HEADER INNER -->
        </div>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
        <!-- BEGIN SIDEBAR -->
        <div class="page-sidebar-wrapper">
        <!-- BEGIN SIDEBAR -->
        <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
        <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
        <div class="page-sidebar navbar-collapse collapse">

        <!-- BEGIN SIDEBAR MENU -->
        <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu
        style(without borders) -->
        <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable
        hoverable(hover vs accordion) sub menu mode -->
        <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to
        collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
        <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
        <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
        <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
        <ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
            <%
                String url=request.getServletPath();
                org.springframework.context.ApplicationContext ctx = org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
                ModuleInfoes moduleInfoes=new ModuleInfoes();
                moduleInfoes.setIsDeleted(0);
                java.util.List<ModuleInfoes> resources=((ModuleInfoesServiceImpl)ctx.getBean("moduleInfoesService")).findList(moduleInfoes);

                for (ModuleInfoes resource : resources) {
                    if (resource.getIsMenu()==1){
                        %>
                        <li class="heading">
                                <h3 class="uppercase"><%= resource.getName()%></h3>
                        </li>
                        <li class="nav-item <%=url.startsWith(resource.getLinkUrl())?"active open":""%>">
                            <a href="javascript:;" class="nav-link nav-toggle">
                                <i class="icon-settings"></i>
                                <span class="title"><%= resource.getName()%></span>
                                <span class="arrow"></span>
                            </a>
                            <ul class="sub-menu">
                            <%
                                  for (ModuleInfoes res : resources) {
                                      if (res.getParentModuleId()==resource.getId()){
                             %>
                                  <li class="nav-item <%=url.contains(res.getLinkUrl())?"active":""%>">
                                        <a href="<% if(res.getMenuType()==1){ %>
                                            ${ctx}<%=res.getLinkUrl()%>
                                            <%}else{%><%=
                                                res.getLinkUrl()%>
                                            <%}%>" class="nav-link ">
                                               <i class="icon-settings"></i>
                                               <span class="title"><%= res.getName() %></span>
                                        </a>
                                   </li><%
                            }
                        }
                        %></ul></li><%
                     }
                }
            %>
        </ul>
        <!-- END SIDEBAR MENU -->
        </div>
        <!-- END SIDEBAR -->
        </div>
        <!-- END SIDEBAR -->
        <!-- BEGIN CONTENT -->
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