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
				value="${_csrf.token}" /> <label>Password</label> <input
				type="text" name="psw" id="psw" />



			<button id="appFiltro" class="btn btn-primary btn-xs "
				style="margin-right: 20px;" type="button" onclick="call()">Encrypt</button>


		</p>

		<p>
			<label style="width: 180px;">Salt</label> <input type="text"
				id="salt" disabled="disabled" style="width: 500px; font-size: 11px;" /><br />
			<label style="width: 180px;">Encrypted Password</label> <input
				type="text" id="encryptedPsw" disabled="disabled" 
				style="width: 500px; font-size: 11px;" />
		</p>
	</form>



	<script>
		function call() {

			$('#mainSezErrori').hide();
			$('#mainSezOK').hide();
			$('#encryptedPsw').val('');
			$('#salt').val('');
			$('#psw').removeClass('errorInput');

			if ($('#psw').val() == '') {
				$('#psw').addClass('errorInput');
				$('#mainSezErrori').html('Specificare una Password');
				$('#mainSezErrori').show();

				$(window).scrollTop($('#mainSezErrori').offset().top - 120);
				return false;
			}

			var $form = $('#formData');
			var $inputs = $form.find('input, select, textarea');
			showLoading('Encrypting in corso');
			var formdata = ($inputs).serialize();
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = ctx;

			url += '/admin/criptaPsw.json';

			$.ajax({
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

					$('#encryptedPsw').val(response.encryptedPsw);
					$('#salt').val(response.salt);

				},
				error : function(jqXHR, textStatus, errorThrown) {
					hideLoading();
					checkRedirect(jqXHR);
					alert(errorThrown);
				}

			});
		}

		function copy(id) {
			/* Get the text field */
			var copyText = document.getElementById(id);

			/* Select the text field */
			copyText.select();
			copyText.setSelectionRange(0, 99999); /* For mobile devices */

			/* Copy the text inside the text field */
			navigator.clipboard.writeText(copyText.value);

		}
	</script>


</body>
</html>
