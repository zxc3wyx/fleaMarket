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
  <br><br>
  <h1>恭喜用户[${sessionScope.user.userName }]下单成功，其订单详情如下：</h1>
  <table border="1" width="85%">
   <tr>
    <td>您的订单编号为：</td><td>${order.oid }</td> 
   </tr>
   <tr>
    <td>您的订单总金额为：</td><td>${order.totalMoney }</td> 
   </tr>
   <tr>
    <td>商品详情如下：</td>
    <td>
       <table border="1" width="100%">
        <tr><th>#</th><th>商品名称</th><th>商品价格</th><th>商品数量</th><th>条目总金额</th></tr>
        <c:forEach items="${order.shoppingCart.items }" var="item" varStatus="status">
         <tr>
           <td>${item.id }</td>
           <td>${item.product.name }</td>
           <td>${item.product.price}</td>
           <td>${item.number }</td>
           <td>${item.sumMoney }</td>
         </tr>
        </c:forEach>
       </table>
    </td>
   </tr>
   <tr><td>信息提醒：</td><td>尊敬的用户[${order.user.uid }],近期请保持您的电话[${order.user.mobile }]畅通，收货地址：${order.user.addr }</td></tr>
  </table>
  <h3 align="center"><a href="${pageContext.request.contextPath }/index.jsp">回到购物页面</a></h3>
</body>
</html>