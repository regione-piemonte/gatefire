/**
 * 
 */

var theDataTable;
var params = {};
var emptyTable = true;

$(document).ready(function() {
	loadParams();
})

function loadParams() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	showLoading();

	var url = ctx + '/conf/getParams.json';

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
						
							$
									.each(
											response,
											function(index, param) {

												var html = '<tr id="tr_param_'
														+ param.id + '">';

												html += '<td>';
												html += '<span title="'
														+ param.descrizione
														+ '">';
												html += param.parametro
												html += '</span>';
												html += '</td>';

												html += '<td class="valore">';
												html += param.valore
												html += '</td>';
												html += '<td class="glifo" style="width:70px;">';

												html += '<a style="color:#0a3e94;float:left;margin-left:5px;" title="modifica elemento" id="edit_'
														+ param.id
														+ '" href="javascript:edit(\''
														+ param.id
														+ '\')">'
														+ '<i class="fas fa-pencil-alt"></i>'
														+ '</a>';

												html += '<a style="display:none;color:#0a3e94;float:right;margin-right:5px;" title="Annulla" id="undo_'
														+ param.id
														+ '" href="javascript:undo(\''
														+ param.id
														+ '\')">'
														+ '<i class="fas fa-undo"></i>'
														+ '</a>';

												html += '<a style="display:none;color:#0a3e94;float:left;margin-left:5px;" title="salva elemento" id="save_'
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
		"sScrollX" : "100%",

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

			var elem = '<input type="text" id="tempValue" value="' + param.valore
					+ '" style="width:100%"/>'
			$('#tr_param_' + id + ' td:nth-child(2)').html(elem);

			$('#edit_' + id).hide();

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

			$('#tr_param_' + id + ' td:nth-child(2)').html(param.valore);

			$('#edit_' + id).show();

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
						loadParams();

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

function undoCurrent() {

	if ($('#id').val() != '') {

		undo($('#id').val());
	}
}
