<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<title>查询商品列表</title>
</head>
<body> 
<script type="text/javascript">
	function sendJson(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/items/sendJson",
			contentType:"application/json;charset=utf-8",
			data:'{"name":"测试商品","price":99.9}',
			success:function(data){
				alert(data);
			}
		});
	}
	
	function getRedis(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/items/getRedis",
			contentType:"application/json;charset=utf-8",
			data:'{"name":"袜子","price":199.9}',
			success:function(data){
				alert(data);
			}
		});
	}
</script>
<input type="button" name="test" value="sendJson" onClick="sendJson()" />
<input type="button" name="redis_test" value="getRedis" onClick="getRedis()" />
<form action="${pageContext.request.contextPath }/items/search" method="post">
 高级查询条件：
<table width="100%" border=1>
<tr>
<!-- 如果controller中接收的是VO，页面上接收的值是vo的属性.属性.属性. -->
<td>商品名称<input type="text" name="items.name"/></td>
<td>商品价格<input type="text" name="items.price"/></td>
<td><input type="submit" value="查询"/></td>
</tr>
</table>
</form>
<form action="${pageContext.request.contextPath }/items/delAll" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td><input type="submit" value="批量删除"/></td>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td></td>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<c:forEach items="${itemList }" var="item">
<tr>
	<td><input type="checkbox" name="ids" value="${item.id }"></td>
	<td>${item.name }</td>
	<td>${item.price }</td>
	<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${item.detail }</td>
</tr>
</c:forEach>

</table>
</form>
<form action="${pageContext.request.contextPath }/items/updateAll" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td><input type="submit" value="批量更新"/></td>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td></td>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<c:forEach items="${itemList }" var="item" varStatus="status">
<tr>
	<!-- controller接收的如果是list类型,input控件里name的值是vo的属性名[${status.index}].list泛型的属性名字 -->
	<td><input type="checkbox" name="ids" value="${item.id }">
		<input type="hidden" name="itemsList[${status.index }].id" value="${item.id}" />
	</td>
	<td><input type="text" name="itemsList[${status.index }].name" value="${item.name}"/></td>
	<td><input type="text" name="itemsList[${status.index }].price" value="${item.price}"/></td>
	<td><input type="text" name="itemsList[${status.index }].createtime" value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
	<td><input type="text" name="itemsList[${status.index }].detail" value="${item.detail}" /></td>
</tr>
</c:forEach>

</table>
</form>
<form action="${pageContext.request.contextPath }/items/queryitem" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td><input type="submit" value="查询"/></td>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<c:forEach items="${itemList }" var="item">
<tr>
	<td>${item.name }</td>
	<td>${item.price }</td>
	<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${item.detail }</td>
	
	<!-- <td><a href="${pageContext.request.contextPath }/items/itemEdit.action?id=${item.id}">修改</a></td>-->
	<!-- restful风格 -->
	<td><a href="${pageContext.request.contextPath }/items/itemEdit/${item.id}">修改</a></td>
</tr>
</c:forEach>

</table>
</form>
</body>

</html>