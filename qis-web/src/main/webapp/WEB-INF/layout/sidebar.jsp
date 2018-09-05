<%@ page import="com.xinri.po.moduleInfo.ModuleInfoes" %>
<%@ page import="com.qis.ShiroUser" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="com.xinri.vo.redis.Redis" %>
<%@ page import="com.xinri.service.ResourceService" %>
<%@ page import="com.xinri.vo.redis.Module" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div class="page-sidebar-wrapper">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar navbar-collapse collapse">

        <ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
        <%
            ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();// 取得当前用户信息
            if(user==null){
        %>
            <script>location.href='${ctx}/login'</script>
        <% return ;
        }%>
        <%
            String url=request.getServletPath();
            org.springframework.context.ApplicationContext ctx = org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
            ResourceService resourceService = (ResourceService)ctx.getBean("resourceService");
            Redis info = resourceService.getInfo(user);
            if(info==null){
        %>
            <li class="nav-item active open">
                <a href="javascript:;" class="nav-link nav-toggle">
                    <i class="glyphicon glyphicon-home "></i>
                    <span class="title">主页</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub-menu">
                    <li class="nav-item active">
                        <a href="${ctx}/main" class="nav-link ">
                            <i class="glyphicon glyphicon-home "></i>
                            <span class="title">首页</span>
                        </a>
                    </li>
                </ul>
            </li>
        <%
            }else{
               java.util.List<Module> resources=info.getModuleInfoesList();
                for (Module resource : resources) {
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
                        <a href="<% if(res.getMenuType()==1){ %>${ctx}<%=res.getLinkUrl()%><%}else{%><%=res.getLinkUrl()%><%}%>" class="nav-link ">
                            <i class="<% if(res.getIcon()!=null&&!"".equals(res.getIcon())){ %><%= res.getIcon()%><% }else{%>glyphicon glyphicon-tint<% }%> "></i>
                            <span class="title"><%= res.getName() %></span>
                        </a>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
            </li>
            <%
                    }
                }
            }
            %>
        </ul>
    <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
</div>