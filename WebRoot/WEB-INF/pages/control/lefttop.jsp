<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<nav class="side_nav navbar navbar-fixed-top navbar-inverse" role="navigation">
       <!-- Brand and toggle get grouped for better mobile display -->
       <div class="navbar-header">
           <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav-top">
               <div>菜单</div>
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>

           </button>
           <a class="navbar-brand text-info" href="<html:rewrite action='/control/center/main'/>">题立搜后台</a>
       </div>

       <!-- Collect the nav links, forms, and other content for toggling -->
       <div class="collapse navbar-collapse" id="nav-top" >
           <ul class="nav navbar-nav" id="accordion" data-toggle="buttons">
               <li class="panel">
                   <a href="#collapse1" class="dropdown-toggle"  data-toggle="collapse" data-parent="#accordion"><span class="glyphicon glyphicon-list"></span> 题目管理<b class="caret"></b></a>
                   <ul id="collapse1" class="collapse list-unstyled sednav">
                       <li><a href="#" onclick="javascript:timuListInit();"><span class="glyphicon glyphicon-eye-open"></span>查看题目</a></li>
                       <li><a href="#" onclick="javascript:timuImport();"><span class="glyphicon glyphicon-import"></span>导入题目</a></li>
                   </ul>
               </li>
               <li class="panel">
                   <a href="#collapse2" class="dropdown-toggle"  data-toggle="collapse" data-parent="#accordion"><span class="glyphicon glyphicon-cog"></span> 系统管理<b class="caret"></b></a>
                   <ul id="collapse2" class="collapse list-unstyled sednav">
                       <li><a href="#" onclick="javascript:changepsw();"><span class="glyphicon glyphicon-edit"></span>修改密码</a></li>
                       <li><a href="#" onclick="javascript:logout();"><span class="glyphicon glyphicon-log-out"></span>注销退出</a></li>
                   </ul>
               </li>
           </ul>
       </div><!-- /.navbar-collapse -->
   </nav>

   
<div class="containerk" id="main">
       <div class="row top_search">

           <div class="col-xs-6">
           <form action="/control/timu/list.do" enctype="multipart/form-data" method="post" name="search" id="search" class="form-inline form-group" role="search">
               <input name="query" value="true" type="hidden">
               <div class="input-group">
                   <input type="text" name="word" class="form-control" placeholder="输入要搜索的题目信息">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
						</span>
               </div><!-- /input-group -->
           </form>
           </div>

           <div class="col-xs-6 info_top">
               <div class="btn-group uers_info">
                   <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span><em class="text-success">${admin.name }</em><span class="caret"></span></button>
                   <ul class="dropdown-menu" role="menu">
                       <li><a href="#" onclick="javascript:changepsw();"><span class="glyphicon glyphicon-edit"></span>修改密码</a></li>
                       <li class="divider"></li>
                       <li><a href="#" onclick="javascript:logout();"><span class="glyphicon glyphicon-log-out"></span>退出登录</a></li>
                   </ul>
               </div><!-- /btn-group -->
           </div>


       </div><!-- /top_search -->
   