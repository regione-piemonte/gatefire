/**
 * 
 */

var theAccountDataTable;

var accounts = {};
var emptyTable = true;

function dettAccount(codice) {
	undoNew();
	undoCurrentRepo();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$('#captionAccounts')
			.html(
					'Account Repository <strong>'
							+ codice
							+ '</strong>'
							+ '<a href="javascript:addAccount(\''
							+ codice
							+ '\')" style="float:right;color:#0a3e94;;float:right;margin-right:5px;"><i class="fas fa-plus-square" ></i>&nbsp;Aggiungi Account</a>');

	$('#codiceRepository').val(codice);

	var url = ctx + '/repo/getRepoAccounts.json?codice=' + codice;
	showLoading();
	$
			.ajax({
				url : url,
				datatype : "json",
				method : 'POST',
				cache : false,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},

				success : function(response, status, jqXHR) {
					accounts = response;
					if (checkSession(jqXHR)) {

						$('#repoUrisDiv').hide();
						$('#repoAccountsDiv').show();

						if (theAccountDataTable != null) {

							theAccountDataTable.clear().destroy();
						}

						removeRows('tabAccounts');

						if (response.length > 0) {

							var html = "";
							$
									.each(
											response,
											function(index, param) {

												var html = '<tr id="tr_account_'
														+ param.id + '">';

												html += '<td class="applicazione">';

												html += param.codiceApplicazione;

												html += '</td>';
												html += '<td class="username">';

												html += param.username;

												html += '</td>';
												html += '<td class="salt">';

												html += param.salt;

												html += '</td>';
												html += '<td class="hashedPassword">';

												html += param.hashedPassword;

												html += '</td>';
												html += '<td class="password">******';
												html += '<a style="color:#0a3e94;;float:right;margin-right:5px;" title="Visualizza password" href="javascript:revealPsw('
														+ param.id + ')">';
												html += '<i class="fa fa-eye"></i>'
														+ '</a>';

												html += '</td>';

												html += '<td class="glifo" style="width:70px;">';

												html += '<a style="color:#0a3e94;;float:right;margin-right:5px;" title="Elimina elemento" id="delete_'
														+ param.id
														+ '" href="javascript:deleteAccount('
														+ param.id
														+ ')">'
														+ '<i class="fa fa-trash"></i>'
														+ '</a>';

												html += '</td>';
												html += '</tr>';

												addRow('tabAccounts', html);

											});

						}
						generaAccountDataTable();

					}
					hideLoading();

				},
				error : function(jqXHR, textStatus, errorThrown) {
					hideLoading();
					alert(errorThrown);
				}

			});

}

function generaAccountDataTable() {

	theAccountDataTable = $('#tabAccounts').DataTable({
		retrieve : true,
		"sScrollY" : "340px",

		'aaSorting' : [],

		"bInfo" : false,
		"bScrollCollapse" : true,
		"bPaginate" : false,
		"bFilter" : false,
		"sDom" : 'frt<"bottom"<"chiusura">i>',
		"language" : {

			"infoEmpty" : "Visualizzati 0 elementi",
			"emptyTable" : "Nessun elemento presente"
		},

	});
}

function addAccount(codice) {
	$('#codiceRepositoryAccount').val(codice);

	$('#editAccountLabel').html('Nuovo Account Repository ' + codice)

	$('#applicazione_new').html('');

	$('#username_new').val('');
	$('#password_new').val('');
	var opzioni = '';
	var disabilitato = false;
	$("#appDisponibili > option").each(
			function(index, element) {

				var trovato = false;

				$.each(accounts, function(index, param) {

					if (param.codiceApplicazione == element.value) {
						trovato = true;

						return false;

					}
				});

				if (!trovato) {
					opzioni += '<option value="' + element.value + '">'
							+ element.value + '</option>';
				}

			});

	if (opzioni != '') {
		opzioni = '<option></option>' + opzioni;
	} else {
		opzioni = '<option>Nessuna transazione disponibile</option>' + opzioni;
		disabilitato = true;

	}

	if (disabilitato) {
		$('#applicazione_new').prop('disabled', 'disabled');
		$('#username_new').prop('disabled', 'disabled');
		$('#password_new').prop('disabled', 'disabled');
		$('#saveAccountBtn').prop('disabled', 'disabled');

	} else {
		$('#applicazione_new').prop('disabled', false);
		$('#username_new').prop('disabled', false);
		$('#password_new').prop('disabled', false);
		$('#saveAccountBtn').prop('disabled', false);
	}

	$('#applicazione_new').html(opzioni);
	$('#editAccount').on('hidden.bs.modal', function() {

	});
	$('#editAccount').modal({
		show : true
	});

	// generaDataTable();

}

function saveAccount() {
	$('#codiceApplicazione').val($('#applicazione_new').val());

	$('#username').val($('#username_new').val());
	$('#hashedPassword').val($('#password_new').val());

	$('#accountSezErrori').hide();

	var $form = $('#accountFormData');
	var $inputs = $form.find('input, select, textarea');
	showLoading('Salvataggio in corso');
	var formdata = ($inputs).serialize();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = ctx + '/repo/insertAccount.json';

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
						msg = 'È necessario correggere alcuni errori prima di poter inviare il form:'
								+ '<br/><br/>';

						if (response.errorMessageList != null) {
							$.each(response.errorMessageList, function(index,
									item) {

								if (item.visualizzaMsg == true) {
									if (item.label != '') {

										msg += 'Campo <strong>' + item.label
												+ '</strong>: ' + item.message
												+ '<br/>';
									} else {
										msg += '' + item.message + '<br/>'
									}
								}
								$('#' + item.fieldName).addClass('errorInput');
							});
						}
					} else if (response.status == 'ERROR') {
						msg = 'ERRORE <br/>' + response.message;

					} else {
						$('#mainSezOK').html("Dati salvati con successo")
						$('#mainSezOK').show();
						$('#mainSezOK').delay(5000).fadeOut('slow');

						$('#editAccount').on('hidden.bs.modal', function() {
							dettAccount($('#codiceRepositoryAccount').val());
						});
						$('#editAccount').modal('hide');

					}
					if (msg != '') {
						$('#accountSezErrori').html(msg)
						$('#accountSezErrori').show();

					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					hideLoading();
					checkRedirect(jqXHR);
					alert(errorThrown);
				}

			});

}

function deleteAccount(id) {
	var messagio = "Sei sicuro di voler eliminare l'elemento?";
	bootbox.confirm({
		message : messagio,
		animate : false,
		buttons : {
			'cancel' : {
				label : 'Annulla',
				className : 'btn btn-default btn-xs'
			},
			'confirm' : {
				label : 'Elimina',
				className : 'btn btn-primary btn-xs'
			}
		},
		callback : function(result) {
			if (result) {
				$('#idAccount').val(id);
				$('#mainSezErrori').hide();
				var $form = $('#accountFormData');
				var $inputs = $form.find('input, select, textarea');
				showLoading('Cancellazione in corso in corso');
				var formdata = ($inputs).serialize();
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				var url = ctx + '/repo/deleteAccount.json';

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
						var msg = '';
						if (response.status == 'ERROR') {
							msg = 'ERRORE <br/>' + response.message;

						} else {
							$('#mainSezOK').html(
									"Account cancellato con successo")
							$('#mainSezOK').show();
							$('#mainSezOK').delay(5000).fadeOut('slow');
							dettAccount($('#codiceRepository').val());

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
		}
	});
}

function revealPsw(codice) {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = ctx;

	url += '/repo/revealPsw.json?id=' + codice;

	$
			.ajax({
				url : url,
				datatype : "json",
				method : 'POST',
				cache : false,

				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(response, status, jqXHR) {
					hideLoading();

					var html = response;
					html += '<a style="color:#0a3e94;;float:right;margin-right:5px;" title="Nascondi password" href="javascript:hidePsw('
							+ codice + ')">';
					html += '<i class="fa fa-eye-slash"></i>' + '</a>';
					$('#tr_account_' + codice + ' td:nth-child(5)').html(html);

				},
				error : function(jqXHR, textStatus, errorThrown) {
					hideLoading();
					checkRedirect(jqXHR);
					alert(errorThrown);
				}

			});
}

function hidePsw(codice) {

	var html = '******';
	html += '<a style="color:#0a3e94;;float:right;margin-right:5px;" title="Nascondi password" href="javascript:revealPsw('
			+ codice + ')">';
	html += '<i class="fa fa-eye"></i>' + '</a>';
	$('#tr_account_' + codice + ' td:nth-child(5)').html(html);

}
