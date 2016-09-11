<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/pages/share/taglib.jsp" %>
    
<!-- 显示当前时间：<script>setInterval("timeStr=new Date().toLocaleString();timer.innerText=timeStr;",1000);</script> -->
<!-- Start of Header -->
<header class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row">
            <div class="navbar-header col-lg-3 col-md-4 col-sm-5 col-xs-12">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/" target="_top">题立搜<small>--专业的高中题库网</small></a>
            </div>
            <div id="navbarmenu" class="navbar-collapse collapse col-lg-9 col-md-8 col-sm-7  nav-pills">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/"><span class="glyphicon glyphicon-home"></span>首 页</a>
                    </li>
                    <li>
                        <a href="<html:rewrite action="/timu/list/display"/>?grade=G_1">高 一</a>
                    </li>
                    <li>
                        <a href="<html:rewrite action="/timu/list/display"/>?grade=G_2">高 二</a>
                    </li>
                    <li>
                        <a href="<html:rewrite action="/timu/list/display"/>?grade=G_3">高 三</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">科目<strong class="caret"></strong></a>
                        <ul class="dropdown-menu text-center">
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=YW">语文</a>
                            </li>
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=SX">数学</a>
                            </li>
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=YY">英语</a>
                            </li>
                            <li class="divider">
                            </li>
                            <li role="presentation" class="dropdown-header">理科</li>
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=WL">物理</a>
                            </li>
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=HX">化学</a>
                            </li>
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=SW">生物</a>
                            </li>
                            <li class="divider">
                            </li>
                            <li role="presentation" class="dropdown-header">文科</li>
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=ZZ">政治</a>
                            </li>
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=LS">历史</a>
                            </li>
                            <li>
                                <a href="<html:rewrite action="/timu/list/display"/>?subject=DL">地理</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

        </div>
    </div>
</header>
<!-- End of Header -->


<div id="main" class="container-fluid clearfix">
    <!--题目检索栏-->
    <div id="search_bar" class="row col-lg-10 col-lg-offset-1">
    	<div class=" text-center col-md-10 col-md-offset-1"><span id="searchmessage" class="text-danger"><span class="text-danger"><c:if test="${!empty message}">${message }</c:if></span></span></div>
        <div class="col-sm-5 col-md-5 col-lg-5 col-xs-12 col-md-offset-1">        	
            <form action="/timu/query.do" enctype="multipart/form-data" method="post" name="textsearch" id="textsearch" class="form-inline form-group" role="search" onsubmit="return checkTextsearchForm(this)">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入题目关键字" id="word" name="word" accesskey="s" size="100" maxlength="100" value="${param.word }"/><span class="input-group-btn"><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>搜索</button></span>
                </div>
            </form>
        </div>
        
        <div class="col-sm-5 col-md-5 col-lg-5 col-xs-12">        	
            <form action="/timu/query.do" enctype="multipart/form-data" method="post" name="picsearch" id="picsearch" class="form-inline form-group" role="search" onsubmit="return checkPicsearchForm(this)">
                <div class="input-group">
                    <input type="file" class="form-control" placeholder="请选择.jpg格式图片文件" id="pic" name="imagefile" accesskey="s"/><span class="input-group-btn"><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>以图搜题</button></span>
                </div>
            </form>
        </div>
    </div>
    <!--end题目检索栏-->
