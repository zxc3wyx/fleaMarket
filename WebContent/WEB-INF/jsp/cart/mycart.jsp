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
 <h1 align="center">我的购物车</h1>
 <table border="1" align="center" width="55%">
 <tr>
  <th>#</th><th>商品编号</th><th>商品名称</th><th>商品价格</th><th>商品数量</th><th>总金额</th><th>移除该商品</th>
 </tr>
 <c:choose>
   <c:when test="${!empty(sessionScope.cart) }">
    <c:forEach items="${cart.items }" var="item" varStatus="status">
     <tr>
       <td>${item.id }</td>
       <td>${item.product.pid }</td>
        <td>${item.product.name }</td>
         <td>${item.product.price }</td>
          <td>${item.number }</td>
           <td>${item.sumMoney }</td>
            <td><a href="${pageContext.request.contextPath }/cart/delete?pid=${item.product.pid}">移除</a></td>
     </tr>
    </c:forEach>
    <tr>
     <td colspan="7" align="right">总金额：${cart.totalMoney }</td>
    </tr>
   </c:when>
   <c:otherwise>
    <tr><td colspan="7">目前您的购物车中还没有任何的商品！</td></tr>
   </c:otherwise>
 </c:choose>
 <tr><td colspan="7"><a href="${pageContext.request.contextPath }/index.jsp">继续购物</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
       <a href="${pageContext.request.contextPath }/pay">结账</a></td></tr>
 </table>
</body>
</html>