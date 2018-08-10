<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>

<!DOCTYPE html>
<html>
<head>
    <title>404 - 页面不存在</title>

 </head>

<body class=" page-404-3">
<div class="page-inner">
    <img src="${ctx}/assets/pages/media/pages/earth.jpg" class="img-responsive" alt=""> </div>
<div class="container error-404">
    <h1>404</h1>
    <h2>对不起，该页面不存在</h2>
    <p> 发生了未知错误！ </p>
    <p>
        <a href="${ctx}/main" class="btn red btn-outline"> 返回首页 </a>
        <br> </p>
</div>

</body>

</html>