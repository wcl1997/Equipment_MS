<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/admin/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css" />
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">添加用户</strong><small></small></div>
    </div>
    <hr>

    <div class="edit_content">
        <form action="${ctx }/UserServlet?action=add" method="post" id="add_form" style="background: none; width: 750px;">
            <span style="color:red">${err }</span>
            <div class="item1">
                <div>
                    <span>用户名：</span>
                    <input type="text" class="am-form-field" name="username">&nbsp;&nbsp;
                </div>
                <br>
                <div>
                    <span>密&nbsp;&nbsp;&nbsp;码：</span>
                    <input type="text" class="am-form-field" name="password">
                </div>

            </div>

            <div class="item1">
                <br>
                <div>
                    <span>类&nbsp;&nbsp;&nbsp;型：</span>
                    <select id="category_select" name="type">
                        <option value="buyer_manager">采购员</option>
                        <option value="repair_manager">维修员</option>
                        <option value="analy_manager">分析员</option>
                        <option value="maintain_manager">养护员</option>
                        <option value="user">访客</option>
                    </select>
                    &nbsp;
                </div>
            </div>


            <div style="margin-top: 100px">
                <button class="am-btn am-btn-default" type="button" id="add" style="margin-left: 40px">添加</button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button class="am-btn am-btn-default" type="reset" id="reset" style="margin-left: 50px">重置</button>
            </div>
        </form>
    </div>

</div>

<script src="${ctx }/admin/js/jquery.min.js"></script>

<script>

    $("#add").click(function () {
        //获取表单  让其提交
        $("#add_form").submit();
    });

</script>
</body>
</html>