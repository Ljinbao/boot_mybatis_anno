<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>自定义登录表单</title>
</head>
<body>
    <form action="/login/page" method="post">
        <p>
            名称：<input id ="username" name="username" type ="text" value=""/>
        </p>
        <p>
            描述：<input id ="password" name="password" type ="text" value=""/>
        </p>
        <p>
            记住我：<input id ="remember-me" name="remember-me" type ="checkbox"/>
        </p>
        <p>
            <input type="submit" value="登录"/>
        </p>
        <<input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>
</body>
</html>