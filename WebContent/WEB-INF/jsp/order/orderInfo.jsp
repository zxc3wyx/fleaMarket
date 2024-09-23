<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>用户[${sessionScope.user.uid }],您好，您当前的订单详情如下：</h1>
<table border="1" align="center" width="55%">
 <tr>
  <th>#</th><th>商品编号</th><th>商品名称</th><th>商品价格</th><th>商品数量</th><th>总金额</th>
 </tr>
 <c:choose>
    <c:when test="${!empty(sessionScope.cart) }">
      <c:forEach items="${sessionScope.cart.items }" var="item" varStatus="status">
       <tr>
          <td>${status.count }</td>
          <td>${item.product.pid }</td>
          <td>${item.product.name }</td>
           <td>${item.product.price }</td>
          <td>${item.number }</td>
          <td>${item.sumMoney }</td>
       </tr>
      </c:forEach>
      <tr><td colspan="6" align="right">总金额共计：[${sessionScope.cart.totalMoney }]</td></tr>
    </c:when>
    <c:otherwise>
     <tr><td colspan="6">目前您的购物车中没有任何可供结账的商品!</td></tr>
    </c:otherwise>
 </c:choose>
  </table>
  <h1>请再次确认您的个人信息是否正确：</h1>
  <table border="1" width="55%" align="center">
   <tr><td>用户编号：</td><td>${sessionScope.user.uid }</td></tr>
   <tr><td>用户姓名：</td><td>${sessionScope.user.userName }</td></tr>
   <tr><td>用户联系电话：</td><td>${sessionScope.user.mobile }</td></tr>
   <tr><td>送货地址：</td><td>${sessionScope.user.addr }</td></tr>
  </table>
  <h2 align="center"><a href="${pageContext.request.contextPath }/user/order">确认并下订单</a></h2>
</body>
</html>