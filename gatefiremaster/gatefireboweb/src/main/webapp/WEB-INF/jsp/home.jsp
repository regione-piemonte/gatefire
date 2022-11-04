<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<spring:eval
	expression="@propertyConfigurer.getProperty('role.amministratore')"
	var="role_amministratore" />

<spring:eval
	expression="@propertyConfigurer.getProperty('role.operatore')"
	var="role_operatore" />



<html lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="currentMenu" content="admin" />
<title>Home</title>






</head>
<body>

	<h2 id="header">Home</h2>


	<leoTag:messages dismissible="true" />


	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
			<div class="boxHome">
				<h2>Eventi</h2>
				<a href="javascript:navigate(MENU_CONST.EVENTI)">Visualizza i
					log degli eventi</a>


			</div>
		</div>
		<security:authorize
			access="hasAnyAuthority('${role_amministratore }')">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
				<div class="boxHome">
					<h2>Configurazione</h2>

					<a href="javascript:navigate(MENU_CONST.CONF_CA)">Configura
						Parametri CA</a> <a href="javascript:navigate(MENU_CONST.PARAMS)">Visualizza
						Parametri Generici</a> <a
						href="javascript:navigate(MENU_CONST.REPOSITORY)">Configura
						Repository</a>

				</div>
			</div>

			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
				<div class="boxHome">
					<h2>Amministrazione</h2>

					<a href="javascript:navigate(MENU_CONST.ADMIN_CACHE)">Refresh
						cache</a> <a href="javascript:navigate(MENU_CONST.ADMIN_PING)">Controllo
						raggiungibili&agrave; CA </a>
				</div>
			</div>
		</security:authorize>
	</div>




</body>
</html>
