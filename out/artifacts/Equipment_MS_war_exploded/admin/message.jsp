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

<form action="${ctx }/EquipmentServlet?action=getPageData&currentPage=1" method="post" id="add_form" style="background: none; width: 1000px;">
    <span style="margin-left: 600px">欢迎留言：</span>
    <div class="item1 item_desc">
        <textarea  id="desc" name="gdesc" rows="10" cols="140" style="margin-left: 150px"></textarea>
    </div>
    <button type="submit" id="add" style="margin-left: 450px;margin-top: 20px">添加</button>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" id="reset" style="margin-right: 0px">重置</button>
</form>

<script>
    $("#add").click(function () {
        //让表单提交 GoodsAddServlet
        //获取表单  让其提交
        $("#add_form").submit();
    });

</script>
</body>
</html>
