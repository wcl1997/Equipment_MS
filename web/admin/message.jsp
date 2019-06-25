<%--
  Created by IntelliJ IDEA.
  User: 王成龙
  Date: 2019/6/24
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="margin-bottom: 50px;color: #0e90d2">欢迎访客来踩</h1>
<form action="${ctx }/EquipmentServlet?action=getPageData&currentPage=1" method="post" id="add_form1" style="background: none; width: 1000px;">
    <span style="margin-left: 600px">欢迎留言：</span>
    <div class="item1 item_desc">
        <textarea  id="desc" name="gdesc" rows="10" cols="140" style="margin-left: 150px"></textarea>
    </div>
    <button type="button" id="add" style="margin-left: 450px;margin-top: 20px">添加</button>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" id="reset" style="margin-right: 0px" onclick="window.location.href = '${ctx }/admin/message.jsp'">重置</button>
</form>

<script src="${ctx }/admin/js/jquery.min.js"></script>
<script>
    $("#add").click(function () {
        //获取表单  让其提交
        $("#add_form1").submit();
    });

</script>
</body>
</html>
