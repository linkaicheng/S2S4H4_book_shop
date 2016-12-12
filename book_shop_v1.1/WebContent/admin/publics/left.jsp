<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左边菜单部分</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style1 {
	font-size: 12px;
	color: #435255;
	text-decoration:none;
}
.style2 {
	margin-left:10px;
	width:20px;
	height:20px;
	margin-top:5px;
	}
.style3 {
	font-size: 12px; 
	font-weight: bold; 
    background:url("${pageContext.request.contextPath}/images/main_34.gif");
	height:20px;
	text-align:center;
	padding-top:5px;
}
.style4 {
	font-size: 12px; 
	font-weight: bold; 
    background:url("${pageContext.request.contextPath}/images/main_69.gif");
	height:20px;
	text-align:center;
	padding-top:5px;
	position:fixed;
	bottom:0px;
	width:100%;
}
.style5 {
	margin-top:20px;
}
-->
</style>
</head>

<body>
<!-- 菜单 -->
<!-- 
<div class="style3">用户管理</div>
<div>
	<div>
	<img src="${pageContext.request.contextPath }/images/main_40.gif" class="style2">
	<a href="${pageContext.request.contextPath}/userAdmin_findAll.action?page=1" target="center_right" class="style1">用户管理</a>
	</div>
	
</div>
 -->
<div class="style3 style5">一级分类管理</div>
<div>
	<div>
	<img src="${pageContext.request.contextPath }/images/main_40.gif" class="style2">
	<!-- 该界面中的图书分类信息需要从数据库中先获取出来，因此添加图书信息的界面一定是先由一个servlet跳转过来的 -->
	<a href="${pageContext.request.contextPath}/adminCategory_findAll.action" target="center_right" class="style1">一级分类管理</a>
	</div>
	
</div>
<div class="style3 style5">二级分类管理</div>
<div>
	<div>
	<img src="${pageContext.request.contextPath}/images/main_40.gif" class="style2">
	<a href="${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=1" target="center_right" class="style1">二级分类管理</a>
	
</div>
<div class="style3 style5">商品管理</div>
<div>
	<div>
	<img src="${pageContext.request.contextPath}/images/main_40.gif" class="style2">
	<a href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=1" target="center_right" class="style1">商品管理</a>
	</div>
	
</div>
<div class="style3 style5">订单信息管理</div>
<div>
	<div>
	<img src="${pageContext.request.contextPath}/images/main_40.gif" class="style2">
	<a href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=1" target="center_right" class="style1">订单信息管理</a>
	</div>
	
</div>
<div class="style4">版本：2016 v1.1</div>
</body>
</html>
