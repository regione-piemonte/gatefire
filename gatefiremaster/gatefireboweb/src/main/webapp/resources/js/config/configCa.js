/**
 * 
 */

function changeCa() {
	if ($("input[type='radio'][name='CA']:checked").length > 0) {
		var theradio = $("input[type='radio'][name='CA']:checked");

		console.log(theradio.attr("id"))
		var codice = theradio.attr("id").split('|')[1];
		var tagCa = theradio.attr("id").split('|')[2];
		currTagCa = tagCa;
		dettCa(codice, tagCa)

	}
}
$(document).ready(function() {
	$('input[type="radio"][name="CA"]').on('click', function() {
		changeCa();
	});
	changeCa();
});
var theDataTable;
var params = {};
var emptyTable = true;

var currTagCa;

function dettCa(codice, tagCa) {
	undoNew();

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$('#captionParams')
			.html(
					'Parametri CA <strong>'
							+ codice
							+ '</strong>'
							+ '<a href="javascript:addParam(\''
							+ tagCa
							+ '\')" style="float:right;color:#0a3e94;;float:right;margin-right:5px;"><i class="fas fa-plus-square" ></i>&nbsp;Aggiungi parametro</a>');

	$('#codiceCa').val(codice);

	var url = ctx + '/conf/getCaParams.json?codiceCa=' + codice;
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

						$('#paramDiv').show();

						if (theDataTable != null) {

							theDataTable.clear().destroy();
						}

						removeRows('tabParams');

						if (response.length > 0) {
							params = response;
							var html = "";
							$
									.each(
											response,
											function(index, param) {

												var html = '<tr id="tr_param_'
														+ param.id + '">';

												html += '<td>';

												html += param.parametro
												html += '</td>';
												html += '<td class="funzione">';
												if (param.codiceFunzione != null) {
													html += param.codiceFunzione
												}
												html += '</td>';
												html += '<td class="applicazione">';
												if (param.codiceApplicazione != null) {
													html += param.codiceApplicazione
												}
												html += '</td>';
												html += '<td class="valore">';
												html += param.valore
												html += '</td>';
												html += '<td class="glifo" style="width:70px;">';

												html += '<a style="color:#0a3e94;;float:right;margin-right:5px;" title="Elimina elemento" id="delete_'
														+ param.id
														+ '" href="javascript:deleteParam('
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

												addRow('tabParams', html);

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

	theDataTable = $('#tabParams').DataTable({
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
			$('#parametro').val(param.parametro);

			var codFunzione = '';
			var codApplicazione = '';

			if (param.codiceFunzione != null) {
				codFunzione = param.codiceFunzione;
			}
			if (param.codiceApplicazione != null) {
				codApplicazione = param.codiceApplicazione;
			}

			var elem = '<select  id="tempCodFunzione" style="width:100%"/>'
					+ $('#funzioniDisponibili').html() + '</select>';
			$('#tr_param_' + id + ' td:nth-child(2)').html(elem);

			if (codFunzione != '') {
				$('#tempCodFunzione').val(codFunzione)
			}

			elem = '<select  id="tempCodApplicazione" style="width:100%"/>'
					+ $('#applicazioniDisponibili').html() + '</select>';

			$('#tr_param_' + id + ' td:nth-child(3)').html(elem);

			if (codApplicazione != '') {
				$('#tempCodApplicazione').val(codApplicazione)
			}

			elem = '<input type="text" id="tempValue" value="' + param.valore
					+ '" style="width:100%"/>'
			$('#tr_param_' + id + ' td:nth-child(4)').html(elem);

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
			var codFunzione = '';
			var codApplicazione = '';

			if (param.codiceFunzione != null) {
				codFunzione = param.codiceFunzione;
			}
			if (param.codiceApplicazione != null) {
				codApplicazione = param.codiceApplicazione;
			}
			$('#tr_param_' + id + ' td:nth-child(2)').html(codFunzione);
			$('#tr_param_' + id + ' td:nth-child(3)').html(codApplicazione);
			$('#tr_param_' + id + ' td:nth-child(4)').html(param.valore);

			$('#edit_' + id).show();
			$('#delete_' + id).show();
			$('#save_' + id).hide();
			$('#undo_' + id).hide();
			$('#id').val('');
			$('#parametro').val('');
			return false;

		}
	});
}

function save(id) {

	$('#valore').val($('#tempValue').val());
	$('#codiceFunzione').val($('#tempCodFunzione').val());
	$('#codiceApplicazione').val($('#tempCodApplicazione').val());

	$('#mainSezErrori').hide();
	var $form = $('#formData');
	var $inputs = $form.find('input, select, textarea');
	showLoading('Salvataggio in corso');
	var formdata = ($inputs).serialize();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = ctx + '/conf/updateParam.json';

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
						dettCa($('#codiceCa').val(), currTagCa);

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
function addParam(tagCa) {
	if (!$('#newRow').length) {
		undoCurrent();
		$('#id').val('');
		$('#parametro').val('');
		if (theDataTable != null) {
			theDataTable.destroy();
		}

		if (emptyTable) {
			removeRows('tabParams');
		}

		if (!$('#newRow').length) {
			var html = '<tr id="newRow">';

			var selectParam = '<select id="new_parametro" style="width:100%" >';
			selectParam += '<option value=""></option>';
			selectParam += '<optGroup label="Parametri specifici ' + tagCa
					+ '">'
			$('option[data-param-tagCa="' + tagCa + '"]').each(
					function() {
						selectParam += '<option value="' + $(this).val() + '">'
								+ $(this).html() + '</option>';

					})
			selectParam += '</optGroup>';
			selectParam += '<optGroup label="Altri parametri">'
			$('option[data-param-tagCa=""]').each(
					function() {
						selectParam += '<option value="' + $(this).val() + '">'
								+ $(this).html() + '</option>';

					})
			selectParam += '</optGroup>';
			// selectParam += $('#opzioniDisponibili').html();
			selectParam += '</select>';

			html += '<td>' + selectParam + '</td>';

			var selectFunzione = '<select id="new_funzione" style="width:100%">';
			selectFunzione += $('#funzioniDisponibili').html();
			selectFunzione += '</select>';

			html += '<td>' + selectFunzione + '</td>';

			var selectApp = '<select id="new_app" style="width:100%">';
			selectApp += $('#applicazioniDisponibili').html();
			selectApp += '</select>';

			html += '<td>' + selectApp + '</td>';

			html += '<td  class="valore"><input type="text" style="width:100%" id="new_valore"/></td>';
			html += '<td class="glifo" style="width:70px">';
			html += '<a style="color:#0a3e94;;float:right;margin-right:5px;" title="Annulla" href="javascript:undoNew()">'
					+ '<i class="fas fa-undo"></i>' + '</a>';

			html += '<a style="color:#0a3e94;;float:left;margin-left:5px;" title="salva elemento" href="javascript:saveNew()">'
					+ '<i class="fas fa-save"></i>' + '</a>';
			html += '</td>';
			html += '</tr>';

			if ($('#tabParams tbody tr').length > 0) {
				$('#tabParams tr:first').before(html);
			} else {
				addRow('tabParams', html);
			}

			// generaDataTable();
		}
	}
}

function saveNew() {
	$('#id').val('');
	$('#parametro').val($('#new_parametro').val());
	$('#valore').val($('#new_valore').val());
	$('#codiceFunzione').val($('#new_funzione').val());
	$('#codiceApplicazione').val($('#new_app').val());
	$('#mainSezErrori').hide();
	var $form = $('#formData');
	var $inputs = $form.find('input, select, textarea');
	showLoading('Salvataggio in corso');
	var formdata = ($inputs).serialize();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = ctx + '/conf/insertParam.json';
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
						dettCa($('#codiceCa').val(), currTagCa);

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
function deleteParam(id) {
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
				var url = ctx + '/conf/deleteParam.json';

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
									"Parametro cancellato con successo")
							$('#mainSezOK').show();
							$('#mainSezOK').delay(5000).fadeOut('slow');
							dettCa($('#codiceCa').val(), currTagCa);

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