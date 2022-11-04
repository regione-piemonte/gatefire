/**
 * 
 */

var theDataTable;
var params = {};
var emptyTable = true;

var currTagCa;

function dettRepository(codice) {
	undoNew();

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$('#captionUri')
			.html(
					'Uri Repository <strong>'
							+ codice
							+ '</strong>'
							+ '<a href="javascript:addUri(\''
							+ codice
							+ '\')" style="float:right;color:#0a3e94;;float:right;margin-right:5px;"><i class="fas fa-plus-square" ></i>&nbsp;Aggiungi parametro</a>');

	$('#codiceRepository').val(codice);

	var url = ctx + '/repo/getRepoUri.json?codice=' + codice;
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

					if (checkSession(jqXHR)) {

						$('#repoUrisDiv').show();

						if (theDataTable != null) {

							theDataTable.clear().destroy();
						}

						removeRows('tabUris');

						if (response.length > 0) {
							params = response;
							var html = "";
							$
									.each(
											response,
											function(index, param) {

												var html = '<tr id="tr_uri_'
														+ param.id + '">';

												html += '<td class="transazione">';

												html += param.transazione;

												html += '</td>';
												html += '<td class="uri">';

												html += param.uri;

												html += '</td>';

												html += '<td class="glifo" style="width:70px;">';

												html += '<a style="color:#0a3e94;;float:right;margin-right:5px;" title="Elimina elemento" id="delete_'
														+ param.id
														+ '" href="javascript:deleteUri('
														+ param.id
														+ ')">'
														+ '<i class="fa fa-trash"></i>'
														+ '</a>';
												html += '<a style="color:#0a3e94;;float:left;margin-left:5px;" title="modifica elemento" id="edit_'
														+ param.id
														+ '" href="javascript:edit(\''
														+ param.id
														+ '\')">'
														+ '<i class="fas fa-pencil-alt"></i>'
														+ '</a>';

												html += '<a style="display:none;color:#0a3e94;;float:right;margin-right:5px;" title="Annulla" id="undo_'
														+ param.id
														+ '" href="javascript:undo(\''
														+ param.id
														+ '\')">'
														+ '<i class="fas fa-undo"></i>'
														+ '</a>';

												html += '<a style="display:none;color:#0a3e94;;float:left;margin-left:5px;" title="salva elemento" id="save_'
														+ param.id
														+ '" href="javascript:save(\''
														+ param.id
														+ '\')">'
														+ '<i class="fas fa-save"></i>'
														+ '</a>';

												html += '</td>';
												html += '</tr>';

												addRow('tabUris', html);

											});
							emptyTable = false;
						} else {
							emptyTable = true;
						}
						generaDataTable();

					}
					hideLoading();

				},
				error : function(jqXHR, textStatus, errorThrown) {
					hideLoading();
					alert(errorThrown);
				}

			});

}

function generaDataTable() {

	theDataTable = $('#tabUris').DataTable({
		retrieve : true,
		"sScrollY" : "340px",

		'aaSorting' : [],

		"bInfo" : false,
		"bScrollCollapse" : true,
		"bPaginate" : false,
		"bFilter" : false,
		"sDom" : 'frt<"bottom"<"chiusura">i>',
		"language" : {
			"info" : "Visualizzati _START_ a _END_ di _TOTAL_ elementi totali",
			"infoEmpty" : "Visualizzati 0 elementi",
			"infoThousands" : ".",
			"infoFiltered" : " - filtrati da _MAX_ elementi totali",
			"emptyTable" : "Nessun elemento presente",
			"zeroRecords" : "Nessun elemento trovato",
			"search" : "Filtra"
		},

	});
}

function edit(id) {

	if ($('#newRow').length) {

		undoNew();
	}
	undoCurrent();
	$.each(params, function(index, param) {

		if (param.id == id) {
			$('#id').val(id);
			$('#transazione').val(param.transazione);

			var elem = '<input type="text" id="tempUri" value="' + param.uri
					+ '" style="width:100%"/>'
			$('#tr_uri_' + id + ' td:nth-child(2)').html(elem);

			$('#edit_' + id).hide();
			$('#delete_' + id).hide();
			$('#save_' + id).show();
			$('#undo_' + id).show();
			return false;

		}
	});
}

function undo(id) {
	$('#mainSezErrori').hide();
	$.each(params, function(index, param) {

		if (param.id == id) {

			$('#tr_uri_' + id + ' td:nth-child(2)').html(param.uri);

			$('#edit_' + id).show();
			$('#delete_' + id).show();
			$('#save_' + id).hide();
			$('#undo_' + id).hide();
			$('#id').val('');
			$('#transazione').val('');
			return false;

		}
	});
}

function save(id) {

	$('#uri').val($('#new_uri').val());

	$('#mainSezErrori').hide();
	var $form = $('#formData');
	var $inputs = $form.find('input, select, textarea');
	showLoading('Salvataggio in corso');
	var formdata = ($inputs).serialize();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = ctx + '/repo/updateUri.json';

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
						dettRepository($('#codiceRepository').val());

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
function addUri(tagCa) {
	if (!$('#newRow').length) {
		undoCurrent();
		$('#id').val('');
		$('#transazione').val('');
		if (theDataTable != null) {
			theDataTable.destroy();
		}

		if (emptyTable) {
			removeRows('tabUris');
		}

		var disabilitato = false;

		if (!$('#newRow').length) {
			var html = '<tr id="newRow">';

			var opzioni = '';

			$("#transazioniDisponibili > option").each(
					function(index, element) {

						var trovato = false;

						$.each(params, function(index, param) {

							if (param.transazione == element.value) {
								trovato = true;

								return false;

							}
						});

						if (!trovato) {
							opzioni += '<option value="' + element.value + '">'
									+ element.value + '</option>';
						}

					});

			var selectFunzione = '<select id="new_transazione" style="width:100%">';

			if (opzioni != '') {
				opzioni = '<option></option>' + opzioni;
			} else {
				opzioni = '<option>Nessuna transazione disponibile</option>'
						+ opzioni;
				disabilitato = true;

			}

			if (disabilitato) {
				selectFunzione = '<select id="new_transazione" style="width:100%" disabled="disabled">';
			}

			selectFunzione += opzioni;
			selectFunzione += '</select>';

			html += '<td>' + selectFunzione + '</td>';

			html += '<td  class="valore">';
			if (!disabilitato) {
				html += '<input type="text" style="width:100%" id="new_uri"/>';
			} else {
				html += '<input type="text" style="width:100%" id="new_uri" disabled="disabled"/>';
			}

			html += '</td>';
			html += '<td class="glifo" style="width:70px">';
			html += '<a style="color:#0a3e94;;float:right;margin-right:5px;" title="Annulla" href="javascript:undoNew()">'
					+ '<i class="fas fa-undo"></i>' + '</a>';
			if (!disabilitato) {
				html += '<a style="color:#0a3e94;;float:left;margin-left:5px;" title="salva elemento" href="javascript:saveNew()">'
						+ '<i class="fas fa-save"></i>' + '</a>';
			}
			html += '</td>';
			html += '</tr>';

			if ($('#tabUris tbody tr').length > 0) {
				$('#tabUris tr:first').before(html);
			} else {
				addRow('tabUris', html);
			}

			// generaDataTable();
		}
	}
}

function saveNew() {
	$('#id').val('');
	$('#transazione').val($('#new_transazione').val());
	$('#uri').val($('#new_uri').val());

	$('#mainSezErrori').hide();
	var $form = $('#formData');
	var $inputs = $form.find('input, select, textarea');
	showLoading('Salvataggio in corso');
	var formdata = ($inputs).serialize();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = ctx + '/repo/insertUri.json';
	generaDataTable();
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
						dettRepository($('#codiceRepository').val());

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

function undoNew() {
	// theDataTable.destroy();
	$('#mainSezErrori').hide();
	$('#newRow').remove();
	generaDataTable();
}

function undoCurrent() {

	if ($('#id').val() != '') {

		undo($('#id').val());
	}
}
function deleteUri(id) {
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
				$('#id').val(id);
				$('#mainSezErrori').hide();
				var $form = $('#formData');
				var $inputs = $form.find('input, select, textarea');
				showLoading('Cancellazione in corso in corso');
				var formdata = ($inputs).serialize();
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				var url = ctx + '/repo/deleteUri.json';

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
							$('#mainSezOK').html("Uri cancellata con successo")
							$('#mainSezOK').show();
							$('#mainSezOK').delay(5000).fadeOut('slow');
							dettRepository($('#codiceRepository').val());

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