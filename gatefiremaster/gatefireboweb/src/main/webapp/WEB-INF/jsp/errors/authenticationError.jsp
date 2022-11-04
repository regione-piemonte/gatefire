<%@page import="java.io.PrintWriter"%>
<%@page
	import="org.springframework.security.core.AuthenticationException"%>
<%@page
	import="org.springframework.security.core.userdetails.UsernameNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true" pageEncoding="UTF-8"%>
	
	
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="NO-CACHE" />
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="${ctx}/images/favicon.ico" />

<title><fmt:message key="errorPage.authError" /></title>

<!-- Bootstrap core CSS -->
<link href="${ctx }/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/css/mainModal.css" rel="stylesheet" type="text/css" />



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>





<script type="text/javascript" src="${ctx }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx }/js/bootstrap.min.js"></script>
<script>
	var ctx = '${ctx}';
</script>

<script type="text/javascript" src="${ctx }/js/common.js"></script>





</head>
<body>




	<div id="loadingDiv_overlay"></div>
	<div id="loadingDiv">Caricamento in Corso ...</div>
	<!-- 

 -->


		<div class="page-header">
			<h1 id="titoloPagina">
				<fmt:message key="errorPage.authError" />
			</h1>
	




	
		<div class="riquadro">
			<p>${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
			


			<%
				if (exception != null) {
			%>

			<div id="stacktrace" style="display: none;">
				<pre><%=exception.getMessage()%></pre>
				<br /> <br />
				<pre>
			<%
				exception.printStackTrace(new PrintWriter(out));
			%>
		</pre>
			</div>

			<%
				if (exception instanceof UsernameNotFoundException) {
			%>
			<p>
				<fmt:message key="errorPage.userNotFound" />
			</p>
			<%
				} else if (exception instanceof AuthenticationException) {
			%>
			<p>
				<fmt:message key="errorPage.authError" />
			</p>
			<%
				} else {
			%>
			<p>
				<fmt:message key="errorPage.error" />
			</p>
			<%
				}
				}
			%>
		</div>






	</div>
	

	<!-- /.container-fluid -->
	<!-- Active profile=<%=System.getProperty("spring.profiles.active")%> -->

	
</body>
</html>


