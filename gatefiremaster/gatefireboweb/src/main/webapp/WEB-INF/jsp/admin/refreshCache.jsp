<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="currentMenu" content="admin" />
<title>Refresh cache</title>
<style>
label {
	width: 120px;
}

p {
	width: 100%;
	margin-top: 20px;
	margin-bottom: s0px;
}
</style>
</head>
<body>

	<h2 id="header">
		Refresh Cache 
	</h2>

	<div class="alert alert-success alert-dismissible fade show"
		role="alert" id="mainSezOK" style="display: none;"></div>
	<div class="sezErrori" id="mainSezErrori"></div>

	<leoTag:messages dismissible="true" />

	<form name="formData" id="formData" method="post">
		<p>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <label>Modalita</label><select id="mode"
				name="mode"><option value="A">Tutto</option>
				<option value="M">Solo Messaggi</option>
				<option value="P">Solo Parametri</option>
			</select>
			<button id="appFiltro" class="btn btn-primary btn-xs "
				style="margin-right: 20px;" type="button"
				onclick="refreshCache('C')">Refresh Cache Console</button>
			<button id="appFiltro" class="btn btn-primary btn-xs "
				style="margin-right: 20px;" type="button"
				onclick="refreshCache('G')">Refresh Cache Gateway</button>
		</p>
	</form>



	<script>
		function refreshCache(tipo) {

			$('#mainSezErrori').hide();
			var $form = $('#formData');
			var $inputs = $form.find('input, select, textarea');
			showLoading('Refreh in corso');
			var formdata = ($inputs).serialize();
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = ctx;
			var prefix = '';
			if (tipo == 'C') {
				url += '/admin/refreshCache.json';
				prefix = 'CACHE WEB CONSOLE <br/>';
			}
			if (tipo == 'G') {
				url += '/admin/refreshGatewayCache.json';
				prefix = 'CACHE GATEWAY <br/>';
			}
			$
					.ajax({
						url : url,
						datatype : "json",
						method : 'POST',
						cache : false,
						data : formdata,
						beforeSend : function(xhr) {
							xhr.setRequestHeader(header, token);
						},
						success : function(response, status, jqXHR) {
							hideLoading();
							var msg = '';
							if (response.status == 'VALIDATION_ERROR') {
								msg = prefix
										+ 'È necessario correggere alcuni errori prima di poter inviare il form:'
										+ '<br/><br/>';

								if (response.errorMessageList != null) {
									$.each(response.errorMessageList, function(
											index, item) {

										if (item.visualizzaMsg == true) {
											if (item.label != '') {

												msg += 'Campo <strong>'
														+ item.label
														+ '</strong>: '
														+ item.message
														+ '<br/>';
											} else {
												msg += '' + item.message
														+ '<br/>'
											}
										}
										$('#' + item.fieldName).addClass(
												'errorInput');
									});
								}
							} else if (response.status == 'ERROR') {
								msg = prefix + 'ERRORE <br/>'
										+ response.message;

							} else {
								$('#mainSezOK')
										.html(
												prefix
														+ response.message
														+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>')
								$('#mainSezOK').show();

							}
							if (msg != '') {
								$('#mainSezErrori').html(msg)
								$('#mainSezErrori').show();

								$(window).scrollTop(
										$('#mainSezErrori').offset().top - 120);

							}

						},
						error : function(jqXHR, textStatus, errorThrown) {
							hideLoading();
							checkRedirect(jqXHR);
							alert(errorThrown);
						}

					});
		}
	</script>


</body>
</html>
