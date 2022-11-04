<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<%
response.setHeader("LoginPage", "/");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN</title>
<style>
label {
	padding-left: 0;
}
</style>
</head>
<body>

	<h2>Login</h2>


	<c:if test="${param.error != null}">
		<div class="alert alert-danger" role="alert">
			<fmt:message key="error.login" />
			:
			<c:out value="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}" />
		</div>


		<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" />

	</c:if>
	<div>










		<form:form method="post" id="loginForm" name="loginForm"
			action="${ctx}/perform_login">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="row">

				<div class="col-md-2 col-sm-3 col-xs-6 ">
					<label class="control-label" for="email">Username </label><br /> <input
						type="text" class="text medium" name="username" id="username" />


				</div>
			</div>
			<div class="row">

				<div class="col-md-4 col-sm-6 col-xs-12 ">
					<label class="control-label" for="email">Password </label><br /> <input
						type="password" class="text medium" name="password" id="password" />

					<button type="button" class="btn btn-primary btn-xs"
						id="loginbutton" onclick="login();"
						style="margin-left: 20px; margin-top: -1px;">Login</button>



				</div>


			</div>


		</form:form>
	</div>

	<script>
		$('#username').select();
		$('#username').focus();

		$("#password").keypress(function(e) {
			var keynum;
			if (window.event) { // IE
				keynum = e.keyCode;
			} else { // Opera, Netscape, Firefox
				keynum = e.which;
			}
			if (keynum == 13) {
				login();
			}
		});

		function login() {
			submitForm('loginForm');
		}
	</script>
<!--  
	<table class="table table-data table-striped table-bordered" style="width:600px">
	<caption>SHIB HEADERS</caption>
		<%
		String h = null;
		java.util.Enumeration headers = request.getHeaderNames();
		while (headers != null && headers.hasMoreElements()) {
			h = (String) headers.nextElement();
			if (!h.equals("Shib-Attributes") && !h.equals("Shib-Application-ID")
			&& ((h.startsWith("Shib-") || h.equalsIgnoreCase("remote_user")))) {
		%>
		<tr>
			<td><%=h%></td>
			<td><b><%=request.getHeader(h)%></b></td>
		</tr>

		<%
		}
		}
		%>
	</table>-->
</body>
</html>
