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
<!-- 指定favicon的URI -->  
<link rel="shortcut icon" href="/images/icon_64X64.ico">
<title>题立搜-后台管理系统：操作提示</title>

<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/styles.css">

<style type="text/css">
.text-red{
color:red;
}
</style>
</head>
<body>


<div class="wrapper">
	<div class="container">
		<h3>题立搜后台<span class="text-default">：操作结果提示</span></h3><hr/>
		<p align="center" class="text-default"><c:out value="${message}" escapeXml="false"/></p>
		<br/>
		<div class="form">
			<button class="btn btn-danger" type="submit" id="surebutton">　确　定　</button>
		</div>
	</div>
</div>

<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$('#surebutton').click(function(event){
	if ($('.form').get(0)) {
		$('.form').fadeOut(500);
		$('.wrapper').addClass('form-success');
		setTimeout(function(){window.location.href='${urladdress}';}, 375);
	};		
});
</script>

</body>
</html>