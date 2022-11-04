<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="NO-CACHE" />
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="${ctx}/images/favicon.ico" />


<spring:eval expression="@propertyConfigurer.getProperty('app.version')"
	var="versione" />
<title>SIRF - v. ${versione} - <sitemesh:write
		property='title' /></title>


<!-- 
<link href="https://fonts.googleapis.com/css?family=Titillium+Web" rel="stylesheet"> 
 -->
<!-- Bootstrap core CSS -->
<link href="${ctx }/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/jPushMenu.css" />



<link href="${ctx }/css/jquery-ui.min.css" rel="stylesheet"
	type="text/css" />

<link href="${ctx }/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/css/theme3/jquery-ui.theme.min.css" rel="stylesheet"
	type="text/css" />


<link href="${ctx }/css/main.css" rel="stylesheet" type="text/css" />


<style>
body {
	font-size: 14px;
}
</style>


<script type="text/javascript" src="${ctx }/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx }/js/bootstrap.min.js"></script>


<script type="text/javascript"
	src="${ctx }/js/lang/jquery.ui.datepicker-it.js"></script>


<script type="text/javascript" src="${ctx }/js/bootbox.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.dataTables.min.js"></script>

<script>
	var ctx = '${ctx}';
	var lastUrl = '${history.lastUrl}';
	var historyUrl = '${history.url}';
</script>

<script type="text/javascript" src="${ctx }/js/common.js"></script>





<sitemesh:write property='head' />


</head>
<body style="padding-top: 0;">



	<div id="loadingDiv_overlay"></div>
	<div id="loadingDiv">Caricamento in Corso ...</div>
	<!-- 

 -->


	<div class="container-fluid" id="mainPopupBody"
		style="padding-left: 0; padding-right: 0;">
		<sitemesh:write property='body' />
	</div>





</body>
</html>