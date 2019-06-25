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
    <div class="am-fl am-cf"><strong style="color: #0e90d2">修改采购信息</strong><small></small></div>
</div>
<hr>

<div style="margin:50px auto;width:300px;height:100px">
    <form action="${ctx }/PurchaseServlet?action=edit" method="post" id="add_form" style="background: none; width: 750px;">
        <input type="text"  name="id" value="${purchase.id }" style="display:none">

        <div class="item1">
            <div>
                <span>设备名称：</span>
                <input type="text" class="am-form-field" name="p_ename" value="${purchase.p_ename}">&nbsp;&nbsp;
            </div>

        </div>

        <div class="item1">
            <br>
            <div>
                <span>价&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp格：</span>
                <input type="text" class="am-form-field" name="p_price" value="${purchase.p_price}">&nbsp;&nbsp;
                &nbsp;
            </div>
        </div>

        <div class="item1">
            <br>
            <span>生产厂家：</span>
            <input type="text" class="am-form-field" name="p_manufacture" value="${purchase.p_manufacture}">
        </div>

        <div class="item1">
            <br>
            <span>购买数量：</span>
            <input type="text" class="am-form-field" name="p_amount" value="${purchase.p_amount}">
        </div>

        <div style="margin-top: 50px">
            <button class="am-btn am-btn-default" type="button" id="add" style="margin-left: 40px">提交申请</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="am-btn am-btn-default" type="button" id="reset" style="margin-left: 50px" onclick="window.location.href = '${ctx }/admin/buyer_apply_edit.jsp'">重置</button>
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
