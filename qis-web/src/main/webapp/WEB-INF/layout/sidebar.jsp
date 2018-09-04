<%@ page import="com.xinri.po.moduleInfo.ModuleInfoes" %>
<%@ page import="com.qis.ShiroUser" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.xinri.controller.role.RoleController" %>
<%@ page import="com.xinri.vo.redis.Redis" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="page-container">
    <div class="page-sidebar-wrapper">

        <div class="page-sidebar navbar-collapse collapse">


            <ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                <%
                    String url=request.getServletPath();
                    org.springframework.context.ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext(),
                            "org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcherServlet");
//                org.springframework.context.ApplicationContext ctx = org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();

                    ModuleInfoes moduleInfoes=new ModuleInfoes();
                    moduleInfoes.setIsDeleted(0);
                    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();// 取得当前用户信息
                    RoleController roleController=(RoleController)ctx.getBean("roleController");
                    Redis info=roleController.getInfo(user);
                    java.util.List<ModuleInfoes> resources=info.getModuleInfoesList();
//                    java.util.List<ModuleInfoes> resources=((ModuleController)ctx.getBean("ModuleController")).findListBySysUserId(moduleInfoes,user.id);

                    for (ModuleInfoes resource : resources) {
                        if (resource.getIsMenu()==1){
                %>
                <li class="heading">
                    <h3 class="uppercase"><%= resource.getName()%></h3>
                </li>
                <li class="nav-item <%=url.startsWith(resource.getLinkUrl())?"active open":""%>">
                    <a href="javascript:;" class="nav-link nav-toggle">
                        <i class="<% if(resource.getIcon()!=null&&!"".equals(resource.getIcon())){ %><%= resource.getIcon()%><% }else{%>fa fa-home<% }%> "></i>
                        <span class="title"><%= resource.getName()%></span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <%
                            for (ModuleInfoes res : resources) {
                                if (res.getParentModuleId()==resource.getId()){
                        %>
                        <li class="nav-item <%=url.contains(res.getLinkUrl())?"active":""%>">
                            <a href="<% if(res.getMenuType()==1){ %>${ctx}<%=res.getLinkUrl()%>
                                            <%}else{%> <%=res.getLinkUrl()%>
                                            <%}%>" class="nav-link ">
                                <i class="<% if(res.getIcon()!=null&&!"".equals(res.getIcon())){ %><%= res.getIcon()%><% }else{%>glyphicon glyphicon-tint<% }%> "></i>
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