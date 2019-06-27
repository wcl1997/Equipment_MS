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
    <link rel="stylesheet" href="${ctx }/admin/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css" />
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">更新设备</strong><small></small></div>
    </div>
    <hr>
	
    <div class="edit_content">
		<form action="${ctx }/EquipmentServlet?action=edit" method="post" id="edit_form" style="background: none; width: 700px;">
            <span style="color:red">${err }</span>
            <input type="text"  name="id" value="${equipment.id }" style="display:none">

            <div class="item1">
                <div>
                    <span>设备编号：</span>
                    <input type="text" class="am-form-field" name="equip_id" value="${equipment.equip_id}">&nbsp;&nbsp;
                </div>
                <br>
                <div>
                    <span>设备名称：</span>
                    <input type="text" class="am-form-field" name="equip_name" value="${equipment.equip_name}">
                </div>

            </div>

            <div class="item1">
                <br>
                <div>
                    <span>设备状态：</span>
                    <select id="category_select" name="equip_state" value="${equipment.equip_state}">
                        <option value="正常">正常</option>
                        <option value="损坏">损坏</option>
                        <option value="故障">故障</option>
                        <option value="轻微故障">轻微故障</option>
                    </select>
                    &nbsp;
                </div>
            </div>

            <div class="item1">
                <br>
                <span>生产厂家：</span>
                <input type="text" class="am-form-field" name="equip_manufacturer" value="${equipment.equip_manufacturer}">
            </div>

            <div style="margin-top: 100px">
                <button class="am-btn am-btn-default" type="button" id="edit" style="margin-left: 40px">更新</button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button class="am-btn am-btn-default" type="reset" id="reset" style="margin-left: 50px">重置</button>
            </div>
		</form>
   </div>

</div>

<script src="${ctx }/admin/js/jquery.min.js"></script>

<script>
	
	$(function () {
        
		 $("#edit").click(function () {
		        //让表单提交 GoodsEditServlet
		        //获取表单  让其提交
		        $("#edit_form").submit();
		  });
		 
		 //让value的值等于商品is_hot的option成为选中状态
		 $("#isHotSel option[value=${goods.is_hot}]").prop("selected",true);
		
		 $("#categorySel option[value=${goods.cid}]").prop("selected",true);
		 
    });

   
    
</script>
</body>
</html>