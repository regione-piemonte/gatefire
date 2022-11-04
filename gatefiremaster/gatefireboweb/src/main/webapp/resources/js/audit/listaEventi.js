var theDataTable;
var theDataTableLog;
var theEvent;

$(document).ready(
		function() {

			$('#dataDa').datepicker({
				format : 'dd/mm/yyyy',
				endDate : '1',
				language : "it",
				orientation : 'bottom',
				todayHighlight : true,
				clearBtn : true
			})
					.on(
							'changeDate',
							function(e) {

								if ($('#dataA').datepicker('getDate') != null
										&& $('#dataA').datepicker('getDate')
												.getTime() < e.date.getTime()) {
									$('#dataA').datepicker('setDate', null);
								}

								$('#dataA').datepicker('setStartDate', e.date);

							});

			$('#dataA').datepicker({
				format : 'dd/mm/yyyy',
				endDate : '1',
				language : "it",
				orientation : 'bottom',
				todayHighlight : true,
				clearBtn : true
			})

			if ($('#dataDa').datepicker('getDate') != null) {
				$('#dataA').datepicker('setStartDate',
						$('#dataDa').datepicker('getDate'));
			}

			if (search) {
				filtra();
			}

			$("#codiceFiscale").autocomplete({
				source : function(request, response) {
					$.ajax({
						url : ctx + '/audit/getCodiceFiscaleLike.json',
						datatype : "json",
						cache : false,
						data : {
							term : request.term
						},

						success : function(data) {
							response(data);
						}
					});
				},
				minLength : 3
			});

		});

function filtra() {

	$('#mainSezErrori').hide();
	var msg = validateForm();
	if (msg != '') {
		msg = 'È necessario correggere alcuni errori prima di poter inviare il form:'
				+ '<br/><br/>' + msg;
		$('#mainSezErrori').html(msg);
		$('#mainSezErrori').show();
		return false;
	}

	var url = ctx + '/audit/listaEventi.json';
	var $form = $('#filterForm');
	var $inputs = $form.find('input, select, textarea');
	showLoading();
	var formdata = ($inputs).serialize();

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$
			.ajax({
				url : url,
				datatype : "json",
				method : 'POST',
				cache : false,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				data : formdata,
				success : function(response, status, jqXHR) {

					if (checkSession(jqXHR)) {

						$('#sezTabella').show();

						if (theDataTable != null) {

							theDataTable.clear().destroy();
						}

						removeRows('tabEventi');

						$
								.each(
										response,
										function(index, evento) {

											var html = "";

											if (index % 2 == 0) {
												html = '<tr class="odd" id="tr_'
														+ evento.id
														+ '" onclick="viewDetails('
														+ evento.id
														+ ');" style="cursor:pointer">';
											} else {
												html = '<tr class="even" id="tr_'
														+ evento.id

														+ '" onclick="viewDetails('
														+ evento.id
														+ ');" style="cursor:pointer">';
											}

											html += '<td style="text-align:center;">';
											if (evento.codiceStatoEvento == 'FINE') {

												html += '<i class="fas fa-check-circle" style="color: green"></i>';

											} else if (evento.codiceStatoEvento == 'ERRORE') {
												html += '<i class="fas fa-exclamation-triangle" style="color: red"></i>';
											}

											else if (evento.codiceStatoEvento == 'CANC') {
												html += '<i class="fas fa-times" style="color: red"></i>';
											}

											else {
												html += '<i class="fas fa-cog" style="color: blue"></i>';
											}

											html += '</td>';

											html += '<td>' + evento.id
													+ '</td>';

											html += writeCellNoEscapizz(
													formattaDataDaJava(
															evento.dataInizioElab,
															true), null, null);

											var tEvento = evento.codiceGatefireDTipoEvento;

											if (evento.idSessione != null) {
												tEvento += '<br/><span class="extraSmallText">Id sessione: '
														+ evento.idSessione
														+ '</span>';
											}
											html += writeCellNoEscapizz(
													tEvento, null, null);
											html += '<td>';

											html += evento.codiceStatoEvento
													+ '</td>';

											html += writeCellNoEscapizz(
													formattaDataDaJava(
															evento.dataAggStato,
															true), null, null);
											html += writeCell(evento.nomeFile,
													null, null);
											html += writeCell(
													evento.codiceApplicazione,
													null, null);

											html += writeCell(
													evento.collocazione, null,
													null);

											html += writeCell(
													evento.codiceFiscale, null,
													null);

											html += writeCell(
													evento.codiceCertificationAuthority,
													null, null);

											html += '</tr>';

											addRow('tabEventi', html);

										});

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

	theDataTable = $('#tabEventi').DataTable({
		retrieve : true,
		"sScrollY" : "340px",
		"sScrollX" : "100%",

		'aaSorting' : [],
		"columnDefs" : [ {
			"sortable" : false,
			targets : [ 0 ]
		}, {
			"type" : "date-it",
			targets : [ 2, 5 ]
		} ],
		"bInfo" : true,
		"bScrollCollapse" : true,
		"bFilter" : true,
		"language" : {
			"info" : "Visualizzati  _TOTAL_ elementi",
			"infoEmpty" : "Visualizzati 0 elementi",
			"infoThousands" : ".",
			"infoFiltered" : " - filtrati da _MAX_ elementi totali",
			"emptyTable" : "Nessun elemento presente",
			"zeroRecords" : "Nessun elemento trovato",
			"search" : "Cerca nella tabella"
		},

		"bPaginate" : false,
		"sDom" : 'frt<"bottom"<"chiusura">i>'

	});
}

function generaDataTableLog() {

	theDataTableLog = $('#tabLog').DataTable({
		retrieve : true,
		"sScrollY" : "600px",
		"sScrollX" : "100%",
		"bSort" : false,
		"bInfo" : false,
		"bScrollCollapse" : true,
		"bFilter" : false,

		"bPaginate" : false,
		"sDom" : 'frt<"bottom"<"chiusura">i>'

	});
}

function viewDetails(id) {

	var url = ctx + '/audit/dettEvento.json?id=' + id;
	showLoading();

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$
			.ajax({
				url : url,
				datatype : "json",
				method : 'GET',
				cache : false,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(evento, status, jqXHR) {
					theEvent = evento;
					if (checkSession(jqXHR)) {
						$('#pop_id').html(evento.id);
						$('#pop_codiceTipoEvento')
								.html(evento.codiceTipoEvento);
						$('#pop_codiceStatoEvento').html(
								evento.codiceStatoEvento);
						$('#pop_dataAggStato').html(
								formattaDataDaJava(evento.dataAggStato, true));
						$('#pop_codiceFiscale').html(evento.codiceFiscale);
						$('#pop_nomeFile').html(evento.nomeFile);

						$('#pop_codiceApplicazione').html(
								evento.codiceApplicazione);
						$('#pop_collocazione').html(evento.collocazione);
						$('#pop_ipApplicazione').html(evento.ipApplicazione);
						$('#pop_codiceCertificationAuthority').html(
								evento.codiceCertificationAuthority);

						if (evento.idSessione != null) {
							$('#pop_idSessione').html(evento.idSessione);

							$('#div_idSessione').show();
						} else {
							$('#div_idSessione').hide();
						}

						if (theDataTableLog != null) {

							theDataTableLog.clear().destroy();
						}

						removeRows('tabLogi');

						$
								.each(
										evento.eventoStepList,
										function(index, log) {

											var html = "";

											if (index % 2 == 0) {
												html = '<tr class="odd" id="tr_'
														+ log.id

														+ '" >';
											} else {
												html = '<tr class="even" id="tr_'
														+ log.id

														+ '" >';
											}

											html += writeCell(log.id, null,
													null);
											html += writeCellNoEscapizz(
													formattaDataDaJava(
															evento.dataCreazione,
															true), null, null);
											html += writeCell(
													log.codiceTipoPassoEventoLog,
													null, null);
											html += writeCell(
													log.codiceStatoEventoLog,
													null, null);
											html += '<td style="width: 400px;">';
											if (log.descrizione != null) {
												html += '<a class="btn btn-primary" data-toggle="collapse" href="#dettaglio_'
														+ log.id
														+ '" role="button" aria-expanded="false"	aria-controls="collapseExample"> view </a>';
												html += '<div class="collapse parametri" style="float: none;"  id="dettaglio_'
														+ log.id + '">';
												if (log.descrizione
														.indexOf('{') != -1) {
													html += '<pre>'
															+ log.descrizione
															+ '</pre>';
												} else {
													html += '<p>'
															+ log.descrizione
															+ '</p>';
												}
												html += '</div>';
											}
											html += '</td>';
											html += writeCell(log.returnCode,
													null, null);
											html += writeCell(
													log.returnCodeDescrizione,
													null, null);

											html += '</tr>';

											addRow('tabLog', html);

										});

						$('#dettaglio').on('shown.bs.modal', function() {
							generaDataTableLog();
						});
						$('#dettaglio').on('hidden.bs.modal', function() {

						});
						$('#dettaglio').modal({
							show : true
						});

					}
					hideLoading();

				},
				error : function(jqXHR, textStatus, errorThrown) {
					hideLoading();
					alert(errorThrown);
				}

			});

}

function doreset() {
	resetForm('filterForm');
	$('#mainSezErrori').hide();
	$('input').removeClass('errorInput');
	$('#sezTabella').hide();

}

function validateForm() {
	$('input').removeClass('errorInput');
	var msg = ''
	if ($('#dataDa').val() == '') {
		$('#dataDa').addClass('errorInput');
		msg += 'Campo <strong>Data inizio</strong>: specificare almeno una data<br/>';
	}
	return msg;

}
