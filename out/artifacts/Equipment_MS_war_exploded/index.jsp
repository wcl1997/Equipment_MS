<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html dir="ltr" lang="en-US">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <title>高校设备管理系统</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/style.css" type="text/css" />

</head>

<body>
<div id="container">

  <!--
  服务器跳转时，路径相对的是服务器WebContent
  浏览器请求跳转时， 路径相对的当前的jsp

   在JSP  凡是有  action  src  href 都要去写绝对路径  加上工程名称的路径
  -->
  <form action="${pageContext.request.contextPath }/AdminServlet">
    <div class="login">高校设备管理系统
      <span style="color:red">${err }</span>
    </div>
    <div class="username-text" style="margin-top: 35px">用户名:</div>
    <div class="password-text" style="margin-top: 35px">密码:</div>
    <div class="username-field">
      <input type="text" name="username" value="" style="height: 20px"/>
    </div>
    <div class="password-field">
      <input type="password" name="password" value="" />
    </div>
    <input type="radio" name="type1" value="manager" style="margin-left: 40px;margin-top: 30px">管理员
    <select id="manager_select" name="type2" style="margin-left: 10px">
      <option value="equit_manager">设备管理员</option>
      <option value="analy_manager">分析员</option>
      <option value="repair_manager">维修员</option>
      <option value="buyer_manager">采购员</option>
      <option value="maintain_manager">养护员</option>
    </select>
    <input type="radio" name="type1" value="user" style="margin-left: 40px">访客
<%--    <input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">记住用户名</label>--%>

<%--    <div class="forgot-usr-pwd">忘记密码</div>--%>
    <input type="submit" value="GO" style="margin-top: 20px;margin-right: 20px;float: right"/>
  </form>
</div>

</body>
</html>
