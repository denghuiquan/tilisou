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

    <title>题立搜后台默认页</title>
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
            
            <div class="col-md-12 text-center bg-default" style="height:550px;">
                <h2>Welcome，<em class="text-success">${admin.name }</em>！欢迎来到题立搜后台管理系统！</h2>
            </div>		
            
        </div>

    </div><!-- /containerk -->
	
	<%@ include file="/WEB-INF/pages/control/footer.jsp" %>
</div>

<!-- script references -->
<script src="/js/jquery-2.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script>
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