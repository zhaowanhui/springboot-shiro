<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String path = request.getContextPath();
    String s = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.print(path);
    out.print(s);

%>
<html>
<body>
<h2>Hello<shiro:principal></shiro:principal><a href="${pageContext.request.contextPath}/user/loginOut">登出</a></h2>
<shiro:hasRole name="super">super</shiro:hasRole>
<shiro:hasRole name="admin">admin</shiro:hasRole>
<shiro:hasPermission name="user:add">add</shiro:hasPermission>
<shiro:hasPermission name="user:delete">delete</shiro:hasPermission>
<shiro:hasPermission name="user:update">update</shiro:hasPermission>
<shiro:hasPermission name="user:select">select</shiro:hasPermission>
</body>
</html>
