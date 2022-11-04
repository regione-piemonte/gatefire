<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="shortcut icon"
	href="<c:url value="/resources/images/favicon.ico"/>" />

<spring:eval expression="@propertyConfigurer.getProperty('app.version')"
	var="versione" />


<spring:eval
	expression="@propertyConfigurer.getProperty('role.amministratore')"
	var="role_amministratore" />

<spring:eval
	expression="@propertyConfigurer.getProperty('role.operatore')"
	var="role_operatore" />



<title>Gateway Firma - v. ${versione} - <sitemesh:write
		property='title' /></title>


<link href="<c:url value="/resources/css/jquery-ui.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/bootstrap-datepicker.min.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/theme3/jquery-ui.theme.min.css"/>"
	rel="stylesheet" type="text/css" />

<link href="<c:url value="/resources/css/all.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">

<script>
	var ctx = '${ctx }';
</script>
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/popper.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/datatables.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/locales/bootstrap-datepicker.it.min.js"/>"></script>

<script src="<c:url value="/resources/js/common.js"/>"></script>

<sitemesh:write property='head' />



</head>
<body>




	<div id="loadingDiv_overlay"></div>
	<div id="loadingDiv">Caricamento in Corso ...</div>

	<form:form id="logoutForm" method="post"
		action="${ctx }/perform_logout">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form:form>



	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
		<a class="navbar-brand" href="${ctx }/"> <img
			src="<c:url value="/resources/images/Logo_CSI_negativo.png" />"
			alt="logo CSI" /></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="${ctx }/">Home
				</a></li>

				<security:authorize
					access="hasAnyAuthority('${role_amministratore }','${role_operatore }')">
					<li class="nav-item"><a class="nav-link"
						href="javascript:navigate(MENU_CONST.EVENTI)">Eventi</a></li>
				</security:authorize>
				<security:authorize
					access="hasAnyAuthority('${role_amministratore }')">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Configurazione </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item"
								href="javascript:navigate(MENU_CONST.CONF_CA)">Parametri CA</a>

							<a class="dropdown-item"
								href="javascript:navigate(MENU_CONST.PARAMS)">Parametri
								Generici</a> <a class="dropdown-item"
								href="javascript:navigate(MENU_CONST.REPOSITORY)">Repository</a>

						</div></li>


				</security:authorize>


				<security:authorize
					access="hasAnyAuthority('${role_amministratore }')">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Amministrazione </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">


							<a class="dropdown-item"
								href="javascript:navigate(MENU_CONST.ADMIN_CACHE)">Refresh
								cache</a> <a class="dropdown-item"
								href="javascript:navigate(MENU_CONST.ADMIN_PING)">Controllo
								raggiungibilit&agrave; CA </a> <a class="dropdown-item"
								href="javascript:navigate(MENU_CONST.ADMIN_CRYPT)">Generazione
								password cryptate </a>

						</div></li>
				</security:authorize>

			</ul>
			<c:if test="${!empty pageContext.request.userPrincipal}">
				<ul class="nav-user">
					<li>Ciao <span style="font-weight: bold;"><security:authentication
								property="principal.nome" />&nbsp;<security:authentication
								property="principal.cognome" /></span></li>
					<li>Ruolo: <span style="font-weight: bold;"><security:authentication
								property="principal.mainRuolo" /></span>
					<li class="header-item">
						<%--<a href="${ctx }/j_spring_security_logout">--%> <a
						href="javascript:logout();"> <i class="fas fa-sign-out-alt"></i>
							Esci
					</a>
					</li>
				</ul>

			</c:if>
		</div>
	</nav>



	<div class="container-fluid" id="container">

		<main id="mainBody">

			<sitemesh:write property='body' />

		</main>

		<div id="push"></div>
	</div>
	<div id="footer"></div>


	<!-- /.container-fluid -->
	<!-- Active profile=<%=System.getProperty("spring.profiles.active")%> -->
</body>


</html>