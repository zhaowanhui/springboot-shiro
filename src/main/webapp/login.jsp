<%@page  pageEncoding="utf-8" %>
<html>
<body>
    <form action="${pageContext.request.contextPath}/user/loginUser">
        <input type="text" name="username"/>
        <input type="text" name="password"/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
