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
            <strong class="am-text-primary am-text-lg">采购信息管理</strong><small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 新增
                    </button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3"></div>
        <div class="am-u-sm-12 am-u-md-3">
            <%--<form action="${ctx }/EquipmentServlet?action=getEquipment" method="post" style="background: none; height: 50px;">
                <input type="text" class="am-form-field" id="input_search" style="height: 35px;width: 200px" name="search">
                <span class="am-input-group-btn">
						<button class="am-btn am-btn-default" type="submit" style="margin-top: 7px"
                                id="input_search_btn">搜索</button>
					</span>
            </form>--%>
        </div>
    </div>

</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>设备名称</li>
        <li>设备价格</li>
        <li>生产厂家</li>
        <li>购买数量</li>
        <li>状态</li>
        <li>修改</li>
        <li>删除</li>
    </ul>


    <c:forEach items="${allPurchases }" var="purchase" varStatus="status">

        <ul class="list_goods_ul">
            <li>${status.index + 1}</li>
            <li>${purchase.p_ename} </li>
            <li>${purchase.p_price} </li>
            <li>${purchase.p_manufacture }</li>
            <li>${purchase.p_amount }</li>
            <li><span style="color: grey">${purchase.p_state }</span></li>
            <li><a href="${ctx }/PurchaseServlet?action=editUI&id=${purchase.id}"><img class="img_icon" src="${ctx }/admin/images/edit_icon.png" ></a></li>
            <li><a href="${ctx }/PurchaseServlet?action=delPurchase&id=${purchase.id}"><img class="img_icon" src="${ctx }/admin/images/delete_icon.png"></a></li>
        </ul>
    </c:forEach>

    <!--分页-->
    <div id="page" class="page_div"></div>
</div>

<script src="${ctx }/admin/js/jquery.min.js"></script>
<script>
    $("#add").click(function () {
        $(window).attr('location',"${ctx}/admin/purchase.jsp");
    });
</script>

</body>
</html>