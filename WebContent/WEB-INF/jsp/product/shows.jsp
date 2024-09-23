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
  <h1 align="center">商品信息页面[<font color="red">
    <c:choose>
      <c:when test="${!empty(sessionScope.user) }">
          ${user.userName }
      </c:when>
      <c:otherwise>
                    游客
      </c:otherwise>
    </c:choose>
    </font>]
  </h1>
  
  <c:if test="${!empty(sessionScope.cart )}">
   <h3 align="center"><a href="${pageContext.request.contextPath }/cart/show">查看我的购物车</a></h3>
  </c:if>
  <table border="1" width="65%" align="center">
  <tr>
   <th>商品图片</th><th>商品详情</th>
  </tr>
   <c:forEach items="${pageInfo.list }" var="product" varStatus="status">
   <c:choose>
     <c:when test="${status.index%2==0 }">
     <tr>
      <td><img alt="" src="${pageContext.request.contextPath }/images/${product.imagePath}" /></td>
      <td >
         <table border="0" width="100%" align="center">
             <tr><td>商品编号：</td><td>${product.pid }</td></tr>
              <tr><td>商品名称：</td><td>${product.name }</td></tr>
             <tr> <td>商品价格：</td><td>${product.price}</td></tr>
              <tr><td>商品描述：</td><td>${product.pdesc }</td></tr>
            <tr> <td>添加购物车：</td> <td>
                 <a href="${pageContext.request.contextPath }/cart/add?pid=${product.pid}">
                   <img alt="" src="${pageContext.request.contextPath }/images/cart.png">
               </a>
             </td>
            </tr>
        </table>
      </td>  
    </tr>
     </c:when>
     <c:otherwise>
         <td><img alt="" src="${pageContext.request.contextPath }/images/${product.imagePath}" /></td>
      <td>
         <table border="0" width="100%" align="center">
            <tr><td>商品编号：</td><td>${product.pid }</td></tr>
              <tr><td>商品名称：</td><td>${product.name }</td></tr>
             <tr> <td>商品价格：</td><td>${product.price}</td></tr>
              <tr><td>商品描述：</td><td>${product.pdesc }</td></tr>
            <tr> <td>添加购物车：</td> <td>
                 <a href="${pageContext.request.contextPath }/cart/add?pid=${product.pid}">
                   <img alt="" src="${pageContext.request.contextPath }/images/cart.png">
               </a>
             </td>
            </tr>
        </table>
      </td>  
     </c:otherwise>
   </c:choose>
    
   </c:forEach>
     <tr>
      <td colspan="2" align="center">
       <c:if test="${pageInfo.pageNum<=0 }">
         <a hidden="true" href="${pageContext.request.contextPath }/product/shows?page=${pageInfo.pageNum-1}">上一页</a>
       </c:if>
       <c:if test="${pageInfo.pageNum>0 }">
       <a href="${pageContext.request.contextPath }/product/shows?page=0">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;|
       <a href="${pageContext.request.contextPath }/product/shows?page=${pageInfo.pageNum-1}">上一页</a>
       </c:if>
       &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
       <c:if test="${pageInfo.pageNum<=pageInfo.totalPages }">
        <a href="${pageContext.request.contextPath }/product/shows?page=${pageInfo.pageNum+1}">下一页</a>
        |&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/product/shows?page=${pageInfo.totalPages}">末页</a>
       </c:if>
      </td>
     </tr>
  </table>
</body>
</html>