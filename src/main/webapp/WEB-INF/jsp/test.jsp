<%@page pageEncoding="UTF-8" language="java" contentType="text/html; UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>购买产品测试</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<script type="text/javascript">
    for (var i = 0; i < 4000; i++) {
        var params = {
            userId : 1,
            productId : 1,
            quantity : 3
        };
        $.post("./purchase",params,function (result) {
            alert(result.message);
        });
    }
</script>
<body>
    <h1>抢购产品测试</h1>
</body>
</html>