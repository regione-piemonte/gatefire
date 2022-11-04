<%@page import="java.util.Date"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="leoTag"%>



<c:set var="ctx" value="${pageContext.request.contextPath}" />


<%
	String backMsg = "<<";
	String less = "<";
%>
<c:set var="backMsg"><%=backMsg%></c:set>
<c:set var="lessMsg"><%=less%></c:set>
<c:set var="currMillis"><%=new Date().getTime()%></c:set>






 