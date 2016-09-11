<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<!--[if lt IE 7 ]><html class="ie ie6" > <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" > <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" > <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html  > <!--<![endif]-->
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if ie]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]-->

    <title>题立搜后台：导入题目</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

    <meta name="Keywords" content="题立搜,高中题目,专业题库"/>
    <meta name="description" content="免费开放的高中题库大全，可以进行图片搜题，方便快捷。"/>
	
	<!-- 指定favicon的URI -->  
	<link rel="shortcut icon" href="/images/icon_64X64.ico">
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the pages via file:// -->
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.js"></script>
    <![endif]-->
    <link href="/css/normalize.css" type="text/css" media="screen"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="/css/default.css" rel="stylesheet" type="text/css"/>

    <!--style-->
</head>
<body>
<div class="main">
	<%--导航栏开始	--%>
	<%@ include file="/WEB-INF/pages/control/lefttop.jsp" %>
    <%--导航栏结束	--%>

        <div class="row" id="mainContent">
		<!--导入题目表单栏，抽取形成新页面，通过Ajax请求获取，替换上面部分-->
		
            <div class="col-md-12 bg-white">
                <div class="box">
                    <h2 class="title_box">导入新题目</h2>
                    <div class="text-danger text-center"><span id="message"></span><span><c:if test="${message!=null }">提示：${message}</c:if></span></div>
                    <div class="form-group form-inline col-md-10 col-md-offset-1">
                        <html:form action="/control/timu/manage.do" enctype="multipart/form-data" method="post" >
                            <input type="hidden" name="method" value="add">
                            
                            <div class="form-group col-md-3 col-sm-4 col-md-offset-1">
                                <label for="subject">科目：</label>
                                    <select name="subject" id="subject" class="form-control">
                                        <option value="YW">语文</option>
                                        <option value="SX" selected="selected">数学</option>
                                        <option value="YY">英语</option>
                                        <option value="WL">物理</option>
                                        <option value="SW">生物</option>
                                        <option value="HX">化学</option>
                                        <option value="LS">历史</option>
                                        <option value="DL">地理</option>
                                        <option value="ZZ">政治</option>
                                    </select>
                                    <hr/>
                            </div>

                            <div class="form-group col-md-3 col-sm-4 col-md-offset-1">
                                <label for="grade">年级：</label>
                                    <select name="grade" class="form-control">
                                        <option value="G_1">高一</option>
                                        <option value="G_2">高二</option>
                                        <option value="G_3" selected="selected">高三</option>
                                    </select> 
                                <hr/>                               
                            </div>

                            <div class="form-group col-md-3 col-sm-4 col-md-offset-1">
                            <label for="type">题型：</label>
                            	<select id="type" name="type" class="form-control">
					               <option selected="selected">选择题</option>
					               <option>填空题</option>
					               <option>简答题</option>					               
					               <option>判断题</option>
					               <option>计算题</option>
					               <option>应用题</option>
					               <option>解答题</option>
					               <option>作文题</option>
					               <option>翻译题</option>
					               <option>听力题</option>
					               <option>阅读题</option>
					               <option>其他</option>
					            </select>
							<hr/>
			            	</div>
							
							
                            <div class="input-group col-md-10 col-md-offset-1">
                            	<label for="proSourceDesc">题目来源说明：</label>
                            	<input type="text" maxlength="300" class="form-control" id="proSourceDesc" name="proSourceDesc" placeholder="请输入，如：广东2014高考文科数学题" />
                            	<hr/>
                            </div>

                            <div class="form-group col-md-10 col-md-offset-1">
                                <label for="qPicFile">题目图片：</label>
                                <input name="qPicFile"  class="form-control" id="qPicFile" type="file"/>
								<div>显示题目图片预览的</div>
								<hr/>
                            </div>
							
							<div class="form-group col-md-10 col-md-offset-1">
                                <label for="aPicFile">解答图片：</label>
                                <input type="file" class="form-control" id="aPicFile" name="aPicFile"/>
								<div>显示解答图片预览的</div>
								<hr/>
                            </div>
                            
                            <div class="text-center col-md-12">
	                            <button type="button" onclick="javascript:importnow(this.form);" class="btn btn-default">立即导入</button>
	                            <input type="button" name="backbtn" value="返 回 " class="btn btn-danger" onclick="javascript:history.back()">
                            </div>                            
                        </html:form>
                    </div>
                </div>
            </div>
            
        </div>

    </div><!-- /containerk -->
	
	<%@ include file="/WEB-INF/pages/control/footer.jsp" %>
</div>

<!-- script references -->
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$($("ul#accordion>li")[0]).addClass("active");
	$($("ul#accordion>li")[0]).find("a").attr("aria-expanded",true);
	$($("ul#accordion>li")[0]).find("a").removeClass("collapsed");
	$("ul#collapse1").addClass("in");
	$($("ul#collapse1>li")[1]).addClass("bg-danger");	
});

function validateForm(form){
	if (form.grade.value==null) {
		$("#message").html("请选择科目");
		form.grade.focus();
		return false;
	}
	if (form.subject.value==null) {
		$("#message").html("请选择年级");
		form.subject.focus();
		return false;
	}
	if (form.type.value==null) {
		$("#message").html("请选择题型");
		form.type.focus();
		return false;
	}
	if (form.proSourceDesc.value==null||form.proSourceDesc.value.trim()=="") {
		$("#message").html("请填写题目来源说明");
		form.proSourceDesc.focus();
		return false;
	}
	var qPicFile = form.qPicFile.value;	
	if (qPicFile=="") {
		$("#message").html("请选择题目图片！");
		form.qPicFile.focus();
			return false; 
	}else if (qPicFile) {
		var ext = qPicFile.substring(qPicFile.length-3).toLowerCase();
		if (ext!="jpg"){
			$("#message").html("图片只允许上传jpg图片！");			
			form.qPicFile.focus();
			return false; 
		}
	}
	var aPicFile = form.aPicFile.value;
	if (aPicFile=="") {
		$("#message").html("请选择解答图片！");		
		form.aPicFile.focus();
			return false; 
	}else if (aPicFile) {
		var ext = aPicFile.substring(aPicFile.length-3).toLowerCase();
		if (ext!="jpg"){
			$("#message").html("图片只允许上传jpg图片！");
			form.aPicFile.focus();
			return false; 
		}
	}
	return true;
}

function importnow(form){
	if(validateForm(form)){	
		form.submit();	
	};	
}

function timuListInit(){
	window.location="<html:rewrite action='/control/timu/list'/>";	
}

function timuImport(){
	window.location="<html:rewrite action='/control/timu/manage'/>?method=addUI";
}

function viewmain(){
	window.location="<html:rewrite action='/control/center/main'/>";
}

function logout(){
	window.location="<html:rewrite action='/admin/logout'/>";
}

function changepsw(){
	window.location="<html:rewrite action='/control/admin/manage'/>?method=editpasswordUI";
}

    function pageScroll(){
//把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
        window.scrollBy(0,-100);
//延时递归调用，模拟滚动向上效果
        scrolldelay = setTimeout('pageScroll()',30);
//获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值
        var sTop=document.documentElement.scrollTop+document.body.scrollTop;
//判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
        if(sTop==0||sTop<0) clearTimeout(scrolldelay);
    }


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
        min_height ? min_height = min_height : min_height = 100;
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
    backTotop();

</script>

</body>
</html>