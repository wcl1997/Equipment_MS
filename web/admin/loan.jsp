<%--
  Created by IntelliJ IDEA.
  User: 王成龙
  Date: 2019/6/25
  Time: 7:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong style="color: #0e90d2">请认真填写租借信息</strong><small></small></div>
</div>
<hr>

<div style="margin:50px auto;width:300px;height:100px">
    <form action="${ctx }/LoanServlet?action=add" method="post" id="add_form" style="background: none; width: 750px;">
        <span style="color:red">${err }</span>
        <div class="item1">
            <div>
                <span>设备编号：</span>
                <input type="text" class="am-form-field" name="loan_eid" value="${loan.loan_eid}">&nbsp;&nbsp;
            </div>

        </div>

        <div class="item1">
            <br>
            <div>
                <span>用&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp途：</span>
                <select id="category_select" name="loan_use" value="${loan.loan_use}">
                    <option value="商业">商业</option>
                    <option value="科研">科研</option>
                    <option value="学习">学习</option>
                    <option value="盈利">盈利</option>
                </select>
                &nbsp;
            </div>
        </div>

        <div class="item1">
            <br>
            <span>租借时间：</span>
            <input type="text" class="am-form-field" name="loan_time" value="${loan.loan_time}">
        </div>

        <div class="item1">
            <br>
            <span>联系方式：</span>
            <input type="text" class="am-form-field" name="loan_tel" value="${loan.loan_tel}">
        </div>

        <div style="margin-top: 50px">
            <button class="am-btn am-btn-default" type="button" id="add" style="margin-left: 40px">提交申请</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="am-btn am-btn-default" type="button" id="reset" style="margin-left: 50px" onclick="window.location.href = '${ctx }/admin/loan.jsp'">重置</button>
        </div>
    </form>
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
