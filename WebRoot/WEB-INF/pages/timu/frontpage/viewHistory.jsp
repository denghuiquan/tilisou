<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/share/taglib.jsp" %>
<c:forEach items="${viewHistory}" var="viewtimu" varStatus="statu">
	<li class="bj_blue"><a href="<html:rewrite action="/timu/view"/>?tId=${viewtimu.tId}" target="_blank" title="${viewtimu.proSourceDesc}">${viewtimu.proSourceDesc}</a></li>
</c:forEach>			