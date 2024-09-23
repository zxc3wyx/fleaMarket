<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 table,h1{
   text-align:center;
 }
 .left{text-align:right;}
 .right{text-align:left;}
</style>
</head>
<body>
 <h1 >用户登录页面</h1>
 <form action="${pageContext.request.contextPath }/user/login" method="post">
  <table border="1" width="55%" align="center">
   <tr>
     <td class="left" >请输入用户ID：</td><td class="right"><input type="text" name="uid"><font color="red">${errors.uid }</font></td>
   </tr>
   <tr>
     <td class="left">请输入登录密码：</td><td class="right"><input type="password" name="loginPwd"><font color="red">${errors.uid }</font></td>
   </tr>
   <tr><td class="left">一个月内自动登录：</td><td class="right"><input type="checkbox" name="isAutoLogin" value="true">一个月内自动登录</td></tr>
   <tr><td align="center" colspan="2"><input type="submit" value="登录">&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/user/register">注册新用户</a></td></tr>
  </table>
 </form>
</body>
</html>