<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/common.css">
    <style>
        body{
            border-top: 1px solid;
        }
        ul{
            width: 250px;
            padding-top: 50px;
        }
        ul li{
            color: #B5B5B5;
            width: 250px;
            height: 50px;
            line-height: 50px;
            border-bottom: 1px solid rgba(107, 108, 109, 0.19);
            position: relative;
        }
        ul li a{
            display: block;
            width: 250px;
            height: 50px;
            color: #B5B5B5;
            background: transparent;
            text-shadow: none;
            font-size: 15px;
            text-decoration: none;
            float: left;
            padding-left: 50px;
        }
        ul li a:hover{
            background: #1cc09f;
            color: white;
            text-decoration: none;
        }

        ul li a:hover{

        }
        ul li a i{
            display: inline-block;
            width: 30px;
            height: 30px;
            float: left;
            position: absolute;
            left: 10px ;
            top: 8px;
        }
        
        ul li:nth-child(1) a i{
            background: url("images/category_icon.png") no-repeat;
        }
        ul li:nth-child(2) a i{
            background: url("images/category_icon.png") no-repeat;
        }
        ul li:nth-child(3) a i{
            background: url("images/goods_icon.png") no-repeat;
        }
        ul li:nth-child(4) a i{
            background: url("images/goods_icon.png") no-repeat;
        }
        ul li:nth-child(5) a i{
            background: url("images/admin_icon.png") no-repeat;
        }

        ul li:nth-child(1) a:hover i{
            background: url("images/category_hover.png") no-repeat;
        }
        ul li:nth-child(2) a:hover i{
            background: url("images/category_hover.png") no-repeat;
        }
        ul li:nth-child(3) a:hover i{
            background: url("images/goods_hover.png") no-repeat;
        }
        ul li:nth-child(4) a:hover i{
            background: url("images/goods_hover.png") no-repeat;
        }
        ul li:nth-child(5) a:hover i{
            background: url("images/admin_hover.png") no-repeat;
        }


    </style
    >
</head>

<body style="background:#283643;">

<ul>
    <li><a href="${pageContext.request.contextPath }/LoanServlet?action=getListLoans" target="mainFrame"><i></i>租借管理</a></li>
    <li><a href="${pageContext.request.contextPath }/PurchaseServlet?action=getListPurchases" target="mainFrame"><i></i>采购管理</a></li>
    <li><a href="${pageContext.request.contextPath }/AnalyServlet?action=getEquipmentsByCheckM" target="mainFrame"><i></i>维修报废管理</a></li>
    <li><a href="${pageContext.request.contextPath }/EquipmentServlet?action=getPageData&currentPage=1" target="mainFrame"><i></i>设备管理</a></li>
    <li></i><a href="${pageContext.request.contextPath }/UserServlet?action=getAllAdmins" target="mainFrame"><i></i>用户管理</a></li>
</ul>

</body>
</html>