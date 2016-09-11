<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>题立搜-后台管理系统：登录</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">
<script type="text/javaScript" src="/js/FoshanRen.js"></script>
<script type="text/javaScript">
<!--
function locateparentwindow(){
	if (window.parent!=null && window.parent.document.URL!=document.URL){
		window.parent.location= document.URL; 
	}
}
//-->
</script>

<style type="text/css">
.text-red{
color:red;
}
</style>
</head>
<body onload="JavaScript:locateparentwindow()">


<div class="wrapper">

	<div class="container">
		<h1>Welcome to 题立搜-后台管理系统</h1>
		<form class="form" action="/admin/logon.do" method="post">
			<p id="message" class="text-red" align="center"></p>
			<c:if test="${!empty message}"><p align="center"><font color="red">${message }</font></p></c:if>
			<input type="text" name="managername" placeholder="管理员">
			<input type="password" name="password" placeholder="密　码">
			<button type="submit" id="login-button">立即登录</button>
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	
</div>

<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
function verifyForm(objForm){
	if (trim(objForm.managername.value)==""){
		$('#message').show();
		$('#message').html("用户名不能为空!");
		objForm.managername.focus();
		return false; 
	}
	if (trim(objForm.password.value)==""){
	$('#message').show();
	$('#message').html("密码不能为空!");
		objForm.password.focus();
		return false; 
	}
	return true;
}
$('#login-button').click(function(event){
	$('form #message').hide();
	event.preventDefault();
	if (verifyForm($('form').get(0))) {
		$('form').fadeOut(500);
		$('.wrapper').addClass('form-success');
		setTimeout(function(){$('form')[0].submit();}, 750);
	};		
});
</script>

</body>
</html>