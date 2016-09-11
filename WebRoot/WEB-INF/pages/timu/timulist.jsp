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

    <title>题立搜后台：题目列表</title>
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

			<div class="col-md-12">
				<html:form action="/control/timu/list" method="post">
				<input type="hidden" name="word" value="${word }">
				<html:hidden property="page"/>
				<html:hidden property="query" value="${query }"/>
				<html:hidden property="tId"/>
				<html:hidden property="type"/>
				<html:hidden property="grade"/>

                <p>题目总数：共<span class="label label-success">${pageView.totalrecord}</span>条  每页显示：<span class="label label-success">${pageView.maxresult}</span>条 总页数：共<span class="label label-success">${pageView.totalpage}</span>页  <span class="pull-right">当前页：第<span class="badge">${pageView.currentpage}</span>页</span> </p>
                <div class="table-responsive box">
                    <h2 class="title_box text-center">题目列表</h2>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>题目ID</th>
                            <th>来源说明</th>
                            <th>题目图片</th>
                            <th>解答图片</th>
                            <th>题目OCR文字内容</th>
                            <th class="text-right">发布/更新时间</th>
                            <th class="text-right">科目</th>
                            <th class="text-right">年级</th>
                            <th class="text-right">类型</th>
                            <th class="text-right"><span class="glyphicon glyphicon-edit"></span>修改</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!---------------------------LOOP START------------------------------>
						<c:forEach items="${pageView.records}" var="entry">
                            <tr>
                                <td>${entry.tId }</td>
                                <td>${entry.proSourceDesc }</td>
                                <td><img class="img-responsive img-thumbnail" src="<%=basePath %>${entry.qPicUrl }" alt="题目图片"></td>
                                <td><img class="img-responsive img-thumbnail" src="<%=basePath %>${entry.aPicUrl }" alt="解答图片"></td>
                                <td>${entry.problemContentText }</td>
                                <td>${entry.pubDate }</td>
                                <td>${entry.subject.name }</td>
                                <td>${entry.grade.name }</td>
                                <td>${entry.type }</td>
                                <td class="text-center "><a href="<html:rewrite action="/control/timu/manage"/>?method=editUI&tId=${entry.tId}"><span class="glyphicon glyphicon-edit"></span></a></td>
                            </tr>
						</c:forEach>
                  <!----------------------LOOP END------------------------------->
                        </tbody>
                    </table>

                    <!--页码指示器-->
                    <div class="text-center">
		                <ul class="pagination text-center">
		                	<li><!--第一页-->
		                        <a href='javascript:topage(1)'>首页</a>
		                    </li>
		                    <c:if test="${pageView.currentpage!=1}"><li><!--非第一页时-->
		                        <a href="javascript:topage('${pageView.currentpage-1}')"><span class="glyphicon glyphicon-chevron-left"></span>上一页</a>
		                    </li></c:if>
		                    <c:if test="${pageView.currentpage==1}"><li class="disabled"><!--第一页时-->
		                        <a href="javascript:topage('${pageView.currentpage-1}')"><span class="glyphicon glyphicon-chevron-left"></span>上一页</a>
		                    </li></c:if>
		                    
			                <c:forEach begin="${pageView.pageIndex.startIndex}" end="${pageView.pageIndex.endIndex}" var="wp">
							    <c:if test="${pageView.currentpage==wp}"><li class="active"><a>${wp}</a></li></c:if>
							    <c:if test="${pageView.currentpage!=wp}">
							    <li><a href="javascript:topage('${wp}')">[${wp}]</a>
							    </li></c:if>
							</c:forEach>
							
		                    <c:if test="${pageView.currentpage<pageView.totalpage}"><li><!--非末页时-->
		                        <a href="javascript:topage('${pageView.currentpage+1}')">下一页<span class="glyphicon glyphicon-chevron-right"></span></a>
		                    </li></c:if>
		                    <c:if test="${pageView.currentpage>=pageView.totalpage}"><li class="disabled"><!--第一页时-->
		                        <a href="javascript:topage('${pageView.currentpage+1}')">下一页<span class="glyphicon glyphicon-chevron-right"></span></a>
		                    </li></c:if>
		                    <li><!--最后一夜-->
		                        <a href="javascript:topage('${pageView.totalpage }')">末页</a>
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
                </html:form>
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
	$($("ul#collapse1>li")[0]).addClass("bg-danger");	
});

//到指定的分页页面
function topage(page){	
	var form = document.forms[1];
	$(form.page).val(page);
	var postForm=$(form);
	postForm.submit();
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