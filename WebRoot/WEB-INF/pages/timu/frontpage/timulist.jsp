<%@page import="com.tilisou.viewmodel.SubjectConverter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--[if lt IE 7 ]><html class="ie ie6" > <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" > <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" > <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html > <!--<![endif]-->
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <!--[if ie]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]-->

    <title>题立搜，高中题库中的专业网站</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

    <meta name="Keywords" content="题立搜,高中题目,专业题库"/>
    <meta name="description" content="免费开放的高中题库大全，可以进行图片搜题，方便快捷。"/>
   	
   	<!-- 指定favicon的URI -->  
	<link rel="shortcut icon" href="/images/icon_64X64.ico"/>
   	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the pages via file:// -->
    <!--[if lt IE 9]>
    <script src="<%=basePath%>/js/html5shiv.js"></script>
    <script src="<%=basePath%>/js/respond.js"></script>
    <![endif]-->
    <link href="<%=basePath%>/css/normalize.css" type="text/css" media="screen"/>
    <link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="<%=basePath%>/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" media="screen"/>

    <!--抽取作为 index.css-->
    <link href="/css/timuindex.css" rel="stylesheet" type="text/css" />
</head>

<body class="">

	<jsp:include page="/WEB-INF/pages/share/Head.jsp"/> 
    
    <!--当前位置指示器-->
    <div id="position_pointer" class="row col-lg-10 col-md-10 col-sm-10 col-xs-10 col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1">
        <ol class="breadcrumb">
            <span class="text-danger">当前位置：</span>
            <li><a href="/"><span class="glyphicon glyphicon-home"></span>题立搜首页</a></li>
            <c:if test="${param.grade!=null&&param.grade!='' }">
			<c:set var="grade" value="<li><a href='/timu/list/display.do?grade=${param.grade}'>${grade.name }</a></li>"/></c:if>
			<c:out value="${grade}" escapeXml="false"></c:out>
			<c:if test="${param.subject!=null&&param.subject!='' }">
			<c:set var="subject" value="<li class='active'><a href='/timu/list/display.do?grade=${param.grade}&subject=${param.subject}'>${subject.name }</a></li>"/></c:if>
			<c:out value="${subject}" escapeXml="false"></c:out>            
        </ol>
    </div>

    <div class="row">
        <!--左侧导航区，含浏览历史-->
        <div id="side_left" class="col-lg-3 col-md-3 col-sm-3 hidden-xs">
            <!--题目类型导航区-->
            <div id="side_menu" class="panel-group panel-title navbar-fixed-top">
                <div class="panel panel-primary text-center"><span class="panel-heading">题目快速导航</span></div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#side_menu"
                               href="#collapseOne">
                                所有科目
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <ul class="list-unstyled list-inline text-center">
                                <li>
                                    <a href="/timu/list/display.do?subject=YW">语文</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=SX">数学</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=YY">英语</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=WL">物理</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=HX">化学</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=SW">生物</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=ZZ">政治</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=LS">历史</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=DL">地理</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#side_menu"
                               href="#collapseTwo">
                                高一题目
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-unstyled list-inline text-center">
                                <li>
                                    <a href="/timu/list/display.do?subject=YW&grade=G_1">语文</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=SX&grade=G_1">数学</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=YY&grade=G_1">英语</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=WL&grade=G_1">物理</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=HX&grade=G_1">化学</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=SW&grade=G_1">生物</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=ZZ&grade=G_1">政治</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=LS&grade=G_1">历史</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=DL&grade=G_1">地理</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#side_menu"
                               href="#collapseThree">
                                高二题目
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-unstyled list-inline text-center">
                                <li>
                                    <a href="/timu/list/display.do?subject=YW&grade=G_2">语文</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=SX&grade=G_2">数学</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=YY&grade=G_2">英语</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=WL&grade=G_2">物理</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=HX&grade=G_2">化学</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=SW&grade=G_2">生物</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=ZZ&grade=G_2">政治</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=LS&grade=G_2">历史</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=DL&grade=G_2">地理</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#side_menu"
                               href="#collapseFour">
                                高三题目
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-unstyled list-inline text-center">
                                <li>
                                    <a href="/timu/list/display.do?subject=YW&grade=G_3">语文</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=SX&grade=G_3">数学</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=YY&grade=G_3">英语</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=WL&grade=G_3">物理</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=HX&grade=G_3">化学</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=SW&grade=G_3">生物</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=ZZ&grade=G_3">政治</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=LS&grade=G_3">历史</a>
                                </li>
                                <li>
                                    <a href="/timu/list/display.do?subject=DL&grade=G_3">地理</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!--end题目导航区-->

            <!--题目浏览历史区-->
            <!--<div id="history" class="panel ">
            	<div class="text-left">您浏览过的题目</div>
                <ul id="viewHistory" class="list-unstyled text-left">
                    
                </ul>
            </div>-->
            <!--end浏览历史区-->

        </div>
        <!--end左侧导航区-->

        <!--题目展示区域-->
        <div id="timu_display_area" class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
        	<!-- 排序方式： -->
        	<div id="sorttype"><span>排序方式：</span>
        		<c:if test="${'pubDatedesc'==param.sort}"><strong><em>更新时间降序</em></strong></c:if><c:if test="${'pubDatedesc'!=param.sort}">
              <a title='按更新时间降序降序' href="<html:rewrite action="/timu/list/display"/>?sort=pubDatedesc&grade=${param.grade}&subject=${param.subject}&type=${type}">更新时间降序</a></c:if>
              | <c:if test="${'pubDateasc'==param.sort}"><strong><em>更新时间升序</em></strong></c:if><c:if test="${'pubDateasc'!=param.sort}">
              <a title='按更新时间升序降序' href="<html:rewrite action="/timu/list/display"/>?sort=pubDateasc&grade=${param.grade}&subject=${param.subject}&type=${type}">更新时间升序</a></c:if>
           	</div>
        	
        	<%-- <div id="typeselect">
				<span><strong>题型导航标签：</strong>：</span>
				<ul class="list-unstyled list-inline"><c:forEach items="${types.resultlist}" var="type">
				<li class="label label-success"><a href="<html:rewrite action="/timu/list/display"/>?sort=${param.sort}&grade=${param.grade}&type=${type}">${type}</a></li></c:forEach>
				</ul>
			 </div> --%>
			 
			 			 
            <p>题目总数：共<span class="label label-success">${pageView.totalrecord}</span>条  每页显示：<span class="label label-success">${pageView.maxresult}</span>条 总页数：共<span class="label label-success">${pageView.totalpage}</span>页  <span class="pull-right">当前页：第<span class="badge">${pageView.currentpage}</span>页</span> </p>
            
            <!--题目列表区-->
            <ul class="list-unstyled " id="timu_list">
                <c:forEach items="${pageView.records}" var="entry">	
                	<li>
                    <div class="caption">
                        <p><h3><em>ID:${entry.tId} </em> ${entry.proSourceDesc}</h3></p>
                        <div class="thumbnail">
                            <img class="img-responsive img-thumbnail" src="<%=basePath%>${entry.qPicUrl}"
                                 alt="题目图片:${fn:substring(entry.problemContentText,0,200)}"/>
                        </div>
                        <p>科目：<span class="label label-primary">${entry.subject.name}</span> 年级：<span class="label label-info">${entry.grade.name}</span> 题型：<span class="label label-success">${entry.type}</span><span class="pull-right">发布更新日期：${entry.pubDate}</span></p>

                        <!--题目答案显示区-->
                        <div class="text-center">
                            <a data-toggle="collapse" data-parent="#accordion"
                                     href="#t${entry.tId}" class="btn btn-primary">显示答案</a>
                            <div id="t${entry.tId}" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <img class="img-responsive img-thumbnail" src="<%=basePath%>${entry.aPicUrl}" alt="答案图片"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                </c:forEach>
            </ul>
            <!--end题目列表-->

            <!--页码指示器-->
            <div class="text-center">
                <ul class="pagination text-center">
                	<li><!--第一页-->
                        <a onclick="javascript:topage(1);" href='#'>首页</a>
                    </li>
                    <c:if test="${pageView.currentpage!=1}"><li><!--非第一页时-->
                        <a onclick="javascript:topage(${pageView.currentpage-1});" href='#'><span class="glyphicon glyphicon-chevron-left"></span>上一页</a>
                    </li></c:if>
                    <c:if test="${pageView.currentpage==1}"><li class="disabled"><!--第一页时-->
                        <a onclick="javascript:topage(${pageView.currentpage-1});" href='#'><span class="glyphicon glyphicon-chevron-left"></span>上一页</a>
                    </li></c:if>
                    
	                <c:forEach begin="${pageView.pageIndex.startIndex}" end="${pageView.pageIndex.endIndex}" var="wp">
					    <c:if test="${pageView.currentpage==wp}"><li class="active"><a>[${wp}]</a></li></c:if>
					    <c:if test="${pageView.currentpage!=wp}">
					    <li><a onclick="javascript:topage(${wp});" href="#">[${wp}]</a>
					    </li></c:if>
					</c:forEach>
					
                    <c:if test="${pageView.currentpage<pageView.totalpage}"><li><!--非末页时-->
                        <a onclick="javascript:topage(${pageView.currentpage+1});" href='#'>下一页<span class="glyphicon glyphicon-chevron-right"></span></a>
                    </li></c:if>
                    <c:if test="${pageView.currentpage>=pageView.totalpage}"><li class="disabled"><!--第一页时-->
                        <a onclick="javascript:topage(${pageView.currentpage+1});" href='#'>下一页<span class="glyphicon glyphicon-chevron-right"></span></a>
                    </li></c:if>
                    <li><!--最后一夜-->
                        <a onclick="javascript:topage(${pageView.totalpage });" href='#'>末页</a>
                    </li>
                </ul>
                <div class="input-group pull-right">
                    <!--允许输入数字直接跳转页面-->跳转到第
					<select name="selectPage" class="kuang" onchange="javaScript:topage(this.value)">
							<c:forEach begin="1" end="${pageView.totalpage}" var="wp">
							<option value="${wp}" <c:if test="${pageView.currentpage==wp}">selected</c:if>> ${wp} </option></c:forEach>
					</select>页
                </div>
            </div>

        </div>
    </div>
    <!-- content-wrapper -->     
    
	<jsp:include page="/WEB-INF/pages/share/Foot.jsp" />

<script src="<%=basePath%>/js/jquery-2.1.1.min.js"></script>
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
<script>
//初始化快速导航的选择项
function initSideMenu(subject,grade){
	var active_a = {
			  "color":"#FFF",
			  "background-color":"#0000ff",
			  "font-family":"Arial",
			  "padding":"3px",
			  "border-radius":"5px",
			};
	var side_menu=$("side_menu");
	if ((subject==""||subject==null)&&(grade==""||grade==null)) {
		$("#navbarmenu>ul>li:eq(0)").addClass("active");
	}else if (subject!==""&&(grade==""||grade==null)) {
		$("#collapseOne").find("a[href$='"+subject+"']").css(active_a);	
	}else if (subject!==""&&grade!=="") {
		$("#collapseOne").removeClass("in");
		if (grade=="G_1") {
			$("#collapseTwo").addClass("in");			
			$("#navbarmenu>ul>li:eq(1)").addClass("active");
			$("#collapseTwo").find("a[href*='"+subject+"']").css(active_a);	
		}
		if (grade=="G_2") {
			$("#collapseThree").addClass("in");			
			$("#navbarmenu>ul>li:eq(2)").addClass("active");
			$("#collapseThree").find("a[href*='"+subject+"']").css(active_a);	
		}
		if (grade=="G_3") {
			$("#collapseFour").addClass("in");
			$("#navbarmenu>ul>li:eq(3)").addClass("active");
			$("#collapseFour").find("a[href*='"+subject+"']").css(active_a);	
		};	
	}else if (grade!==""&&(subject==""||subject==null)) {
		$("#collapseOne").removeClass("in");
		if (grade=="G_1") {
			$("#navbarmenu>ul>li:eq(1)").addClass("active");
			$("#collapseTwo").addClass("in");
		}
		if (grade=="G_2") {
			$("#navbarmenu>ul>li:eq(2)").addClass("active");
			$("#collapseThree").addClass("in");
		}
		if (grade=="G_3") {
			$("#navbarmenu>ul>li:eq(3)").addClass("active");
			$("#collapseFour").addClass("in");
		};
	};
};

initSideMenu("${param.subject}","${param.grade}");


//检查当前浏览器是否支持H5的File API
function isSupportFileApi() {
    if(window.File && window.FileList && window.FileReader && window.Blob) {
        return true;
    }
    return false;
}

function checkTextsearchForm(form){
	if (form.word.value=="") {		
		$("#searchmessage").html("请先填写题目关键词");
		form.word.focus();
		return false;
	}
	return true;	
}

function checkPicsearchForm(form){
	var qPicFile=form.imagefile.value;
	if (qPicFile=="") {		
		$("#searchmessage").html("请先选择题目图片");
		form.imagefile.focus();
		return false;
	}else if (qPicFile){
		var ext = qPicFile.substring(qPicFile.length-3).toLowerCase();
		if (ext!="jpg"){
			$("#searchmessage").html("图片只允许上传jpg图片！");			
			form.imagefile.focus();
			return false; 
		};
	};	
	return true;
}

//页面跳转方法 
function topage(pagenum){
	window.location.href="<html:rewrite action='/timu/list/display'/>?grade=${param.grade}&subject=${param.subject}&sort=${param.sort}&page="+pagenum;
};
	
//回到顶部方法 
function pageScroll(){
//把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
    window.scrollBy(0,-100);
//延时递归调用，模拟滚动向上效果
    scrolldelay = setTimeout('pageScroll()',30);
//获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值
    var sTop=document.documentElement.scrollTop+document.body.scrollTop;
//判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
    if(sTop==0||sTop<0) clearTimeout(scrolldelay);
};


function backTotop(min_height){
//预定义返回顶部的html代码，它的css样式默认为不显示
   var backTotop_html = '<a id="backTotop" class="btn btn-warning" title="Return to top"><i class="glyphicon glyphicon-circle-arrow-up"></i> Top</a>';
//将返回顶部的html代码插入页面上id为page的元素的末尾
    $("#main").append(backTotop_html);
    $("#backTotop").click(//定义返回顶部点击向上滚动的动画
            function(){$('html,body').animate({scrollTop:0},700);
            }).hover(//为返回顶部增加鼠标进入的反馈效果，用添加删除css类实现
            function(){$(this).addClass("hover");},
            function(){$(this).removeClass("hover");
            });
//获取页面的最小高度，无传入值则默认为600像素
    min_height ? min_height = min_height : min_height = 600;
//为窗口的scroll事件绑定处理函数
    $(window).scroll(function(){
//获取窗口的滚动条的垂直位置
       var s = $(window).scrollTop();
//当窗口的滚动条的垂直位置大于页面的最小高度时，让返回顶部元素渐现，否则渐隐
       if( s > min_height){
           $("#backTotop").fadeIn(100);
       }else{
           $("#backTotop").fadeOut(200);
       };
    });
};
backTotop(100);
    
</script>

</body>
</html>