<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1 align="center">请登录</h1>
    <div align="center">
        <form action="<%=request.getContextPath()%>/login" method="post">
            用户名：<input type="text" name="username"/><br/>
              密码：<input type="password" name="pass"/><br/>
            <input type="submit" value="登录"/>
            <input type="reset" value="重置"/>
        </form>
    </div>
</body>
</html>
