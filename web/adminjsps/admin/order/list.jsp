<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${title}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>${title}</h1>
<c:forEach items="${all}" var="ordersExpandList">
<table border="1" width="100%" cellspacing="0" background="black">
	<c:forEach items="${ordersExpandList}" var="ordersExpand">
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			订单编号：${ordersExpand.orders.oid}　成交时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ordersExpand.orders.ordertime}"/>　金额：<font color="red"><b>${ordersExpand.orders.total}</b></font>　
			<c:choose>
				<c:when test="${ordersExpand.orders.state eq 0 }">
					<%--<a href="<c:url value="/order/loadOrder.action?oid=${ordersExpand.orders.oid}"/>">付款</a>--%>
					<%--<a href="<c:url value="/order/deleteOrder.action?oid=${ordersExpand.orders.oid}"/>">删除</a>--%>
				未付款
				</c:when>
				<c:when test="${ordersExpand.orders.state eq 1 }">等待发货</c:when>
				<c:when test="${ordersExpand.orders.state eq 2 }">
					<a href="<c:url value="/order/confirm.action?oid=${ordersExpand.orders.oid}"/>">确认收货</a>
				</c:when>
				<c:when test="${ordersExpand.orders.state eq 3 }">
					交易成功
				</c:when>
			</c:choose>

		</td>
	</tr>
		<tr>
			<th>图片</th>
			<th>书名</th>
			<th>作者</th>
			<th>单价</th>
			<th>数量</th>
			<th>小计</th>
		</tr>
	<c:forEach items="${ordersExpand.orderItemExpandsList}" var="orderItemExpands">
	<tr bordercolor="gray" align="center">
		<td width="15%">
			<div><img src="<c:url value='/${orderItemExpands.book.image}'/>" height="75"/></div>
		</td>
		<td>${orderItemExpands.book.bname}</td>
		<td>${orderItemExpands.book.author}</td>
		<td>${orderItemExpands.book.price}元</td>
		<td>${orderItemExpands.orderItem.count}</td>
		<td>${orderItemExpands.orderItem.subtotal}元</td>
	</tr>
	</c:forEach>
	</c:forEach>
</table>
</c:forEach>
  </body>
</html>
