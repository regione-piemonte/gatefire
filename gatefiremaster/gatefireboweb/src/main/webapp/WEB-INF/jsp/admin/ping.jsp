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

	<h2 id="header">Controllo raggiungibilit&agrave; CA</h2>

	<div class="alert alert-success alert-dismissible fade show"
		role="alert" id="mainSezOK" style="display: none;"></div>
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert" id="mainSezErrori" style="display: none;"></div>

	<leoTag:messages dismissible="true" />

	<form name="formData" id="formData" method="post">
		<p>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <label>CA</label> <select id="caCode"
				name="caCode">
				<option value="">Selezionare una CA</option>
				<c:forEach items="${caList}" var="ca">
					<option value="${ca.codice }">${ca.ragioneSociale }</option>

				</c:forEach>
			</select>
			<button id="appFiltro" class="btn btn-primary btn-xs "
				style="margin-right: 20px;" type="button" onclick="call()">PING</button>

		</p>
	</form>



	<script>
		function call() {

			$('#mainSezErrori').hide();
			$('#mainSezOK').hide();

			$('#caCode').removeClass('errorInput');

			if ($('#caCode').val() == '') {
				$('#caCode').addClass('errorInput');
				$('#mainSezErrori').html('Specificare una CA');
				$('#mainSezErrori').show();

				$(window).scrollTop($('#mainSezErrori').offset().top - 120);
				return false;
			}

			var $form = $('#formData');
			var $inputs = $form.find('input, select, textarea');
			showLoading('PING in corso');
			var formdata = ($inputs).serialize();
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = ctx;

			url += '/admin/pingCA.json';

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
								msg = '&Egrave; necessario correggere alcuni errori prima di poter inviare il form:'
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
								msg = 'ERRORE: i servizi della CA  non sono raggiungibili<br/>'
										+ response.message;

							} else {
								$('#mainSezOK')
										.html(
												response.message
														+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>')
								$('#mainSezOK').show();

							}
							if (msg != '') {
								$('#mainSezErrori')
										.html(
												msg
														+ '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>')
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
