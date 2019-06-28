<%--
  Created by IntelliJ IDEA.
  User: 王成龙
  Date: 2019/6/27
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    request.getSession().setAttribute("id",id);
%>
<span style="color: #0e90d2">修改密码:</span>
<form action="${ctx}/UserServlet?action=edit" method="post" style="margin-left: 300px" name="form" onsubmit="return check()">
    请输入密码：<input type="password" name="pwd"><br>
    请再次输入：<input type="password" name="repwd"><br>
    <input type="submit" value="提交">
</form>

<script>
    function check() {
        if (form.pwd.value == ""){
            alert("密码不能为空！");
        } else if (form.pwd.value == form.repwd.value){
            return true;
        } else {
            alert("两次密码不一致!");
            form.pwd.focus();
            return false;
        }
    }
</script>
</body>
</html>
