<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

    <link rel="stylesheet" href="${ctx }/admin/css/style.css"
          type="text/css" />
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css" />
    <link rel="stylesheet" href="${ctx }/admin/css/pageStyle.css">

</head>
<body style="background: #f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong class="am-text-primary am-text-lg">维修员</strong><small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-3"></div>
    <div class="am-u-sm-12 am-u-md-3">

        </div>
    </div>

</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>设备编号</li>
        <li>设备名称</li>
        <li>生产厂家</li>
        <li>维修成功</li>
        <li>维修失败</li>
    </ul>


    <c:forEach items="${equipments }" var="equipment" varStatus="status">

        <ul class="list_goods_ul">
            <li>${status.index + 1}</li>
            <li>${equipment.equip_id} </li>
            <li>${equipment.equip_name }</li>
            <li>${equipment.equip_manufacturer }</li>
            <li><a href="${ctx }/EquipmentServlet?action=repairSuccess&id=${equipment.id}" style="color: #00CC99">维修成功</a></li>
            <li><a href="${ctx }/EquipmentServlet?action=repairFailed&id=${equipment.id}" style="color: #FF0066">维修失败</a></li>
        </ul>
    </c:forEach>

    <!--分页-->
   <div id="page" class="page_div">   </div>
</div>

<script src="${ctx }/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/admin/js/paging.js"></script>
<script>
    //分页
    $("#page").paging({
        pageNo:${page.currentPage},
        totalPage: ${page.totalPage},
        totalSize: ${page.totalCount},
        callback: function(num) {
            // alert(num);
            $(window).attr('location',"${ctx}/EquipmentServlet?action=getPageData&currentPage="+num);
        }
    });

    $("#add").click(function () {
        $(window).attr('location',"${ctx}/admin/add.jsp");
    });

</script>

</body>
</html>