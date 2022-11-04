/**
 * 
 */

var theDataTable;

var theRepoDataTable;
var params = {};
var repos = {};

var emptyTable = true;

var currTagCa;

function initRepos() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	var url = ctx + '/repo/getRepos.json';
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

						if (theRepoDataTable != null) {

							theRepoDataTable.clear().destroy();
						}

						removeRows('tabRepo');

						if (response.length > 0) {
							repos = response;

							$
									.each(
											response,
											function(index, repo) {

												var html = '<tr id="tr_repo_'
														+ repo.codice + '">';

												html += '<td>'
														+ repo.codice
														+ '<div style="float:right"><a style="float:left;color:#0a3e94;" href="javascript:dettRepository(\''
														+ repo.codice
														+ '\')" title="Gestione Uri Transazioni" ><i class="fas fa-list"></i>&nbsp;Transazioni</a>'
														+ '<a style="float:left;clear:both;color:#0a3e94;" href="javascript:dettAccount(\''
														+ repo.codice
														+ '\')" title="Gestione Account Transazioni" ><i class="fas fa-user"></i>&nbsp;Account</a></div></td>';
												html += '<td>' + repo.nome
														+ '</td>'
												html += '<td>'
														+ repo.descrizione
														+ '</td>'
												html += '<td class="collocazione">'
														+ repo.collocazione
														+ '</td>';

												html += '<td class="autenticazione">'
														+ repo.authenticationRequired
														+ '</td>';

												var consensi = "ALL";
												if (repo.gestioneConsensi == 'S') {
													consensi = "SLOT";
												} else if (repo.gestioneConsensi == 'C') {
													consensi = "Piattaforma Consensi";
												}

												html += '<td class="autenticazione">'
														+ consensi + '</td>';

												html += '<td class="autenticazione">'
														+ repo.gestioneIdEpisodio
														+ '</td>';

												var tipoRepo = repo.tipoRepository != null ? repo.tipoRepository
														: "";

												html += '<td class="autenticazione">'
														+ tipoRepo + '</td>';
												html += '<td class="glifo" style="width: 70px;">';

												html += '<a style="color:#0a3e94;float:left;margin-left:5px;" title="modifica elemento" id="edit_'
														+ repo.codice
														+ '" href="javascript:editRepo(\''
														+ repo.codice
														+ '\')">'
														+ '<i class="fas fa-pencil-alt"></i>'
														+ '</a>';

												html += '<a style="display:none;color:#0a3e94;float:right;margin-right:5px;" title="Annulla" id="undo_'
														+ repo.codice
														+ '" href="javascript:undoRepo(\''
														+ repo.codice
														+ '\')">'
														+ '<i class="fas fa-undo"></i>'
														+ '</a>';

												html += '<a style="display:none;color:#0a3e94;float:left;margin-left:5px;" title="salva elemento" id="save_'
														+ repo.codice
														+ '" href="javascript:saveRepo(\''
														+ repo.codice
														+ '\')">'
														+ '<i class="fas fa-save"></i>'
														+ '</a>';

												html += '</td>';
												html += '</tr>';

												addRow('tabRepo', html);

											});
							emptyTable = false;
						} else {
							emptyTable = true;
						}
						generaRepoDataTable();

					}
					hideLoading();

				},
				error : function(jqXHR, textStatus, errorThrown) {
					hideLoading();
					alert(errorThrown);
				}

			});
}

function dettRepository(codice) {
	undoNew();
	undoCurrentRepo();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$('#captionUri')
			.html(
					'Transazioni Repository <strong>'
							+ codice
							+ '</strong>'
							+ '<a href="javascript:addUri(\''
							+ codice
							+ '\')" style="float:right;color:#0a3e94;;float:right;margin-right:5px;"><i class="fas fa-plus-square" ></i>&nbsp;Aggiungi Transazione</a>');

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
						$('#repoAccountsDiv').hide();

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



function generaRepoDataTable() {

	theRepoDataTable = $('#tabRepo').DataTable({
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



function edit(id) {

	if ($('#newRow').length) {

		undoNew();
	}
	undoCurrent();
	$.each(params, function(index, param) {

		if (param.id == id) {
			$('#id').val(id);
			$('#transazione').val(param.transazione);

			var elem = '<input type="text" id="new_uri" value="' + param.uri
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

function editRepo(codice) {

	if ($('#newRowRepo').length) {

		undoNewRepo();
	}
	undoCurrentRepo();
	$
			.each(
					repos,
					function(index, param) {

						if (param.codice == codice) {
							$('#codice').val(codice);
							var elem = '<input type="text" id="new_descrizione" value="'
									+ param.descrizione
									+ '" style="width:100%"/>'
							$('#tr_repo_' + codice + ' td:nth-child(3)').html(
									elem);

							elem = '<input type="text" id="new_collocazione" value="'
									+ param.collocazione
									+ '" style="width:100%"/>'
							$('#tr_repo_' + codice + ' td:nth-child(4)').html(
									elem);

							var selectFunzione = '<select id="new_authenticationRequired" style="width:100%">';
							selectFunzione += '<option value="true"';

							if (param.authenticationRequired) {
								selectFunzione += ' selected';
							}
							selectFunzione += '>true</option>'

							selectFunzione += '<option value="false"';

							if (!param.authenticationRequired) {
								selectFunzione += ' selected';
							}
							selectFunzione += '>false</option></select>';

							$('#tr_repo_' + codice + ' td:nth-child(5)').html(
									selectFunzione);

							selectFunzione = '<select id="new_gestioneConsensi" style="width:100%">';
							selectFunzione += '<option value="S"';

							if (param.gestioneConsensi == 'S') {
								selectFunzione += ' selected';
							}
							selectFunzione += '>SLOT</option>'

							selectFunzione += '<option value="C"';

							if (param.gestioneConsensi == 'C') {
								selectFunzione += ' selected';
							}
							selectFunzione += '>Piattaforma Consensi</option>';

							selectFunzione += '<option value="A"';

							if (param.gestioneConsensi == 'A') {
								selectFunzione += ' selected';
							}
							selectFunzione += '>All</option></select>';
							$('#tr_repo_' + codice + ' td:nth-child(6)').html(
									selectFunzione);

							selectFunzione = '<select id="new_gestioneIdEpisodio" style="width:100%">';
							selectFunzione += '<option value="true"';

							if (param.gestioneIdEpisodio) {
								selectFunzione += ' selected';
							}
							selectFunzione += '>true</option>'

							selectFunzione += '<option value="false"';

							if (!param.gestioneIdEpisodio) {
								selectFunzione += ' selected';
							}
							selectFunzione += '>false</option></select>';
							$('#tr_repo_' + codice + ' td:nth-child(7)').html(
									selectFunzione);
							selectFunzione = '<select id="new_tipoRepository" style="width:100%">';

							var opzioni = '<option value=""></option>';

							$("#tipoRepoDisponibili > option")
									.each(
											function(index, element) {

												opzioni += '<option value="'
														+ element.value + '"';

												if (param.tipoRepository == element.value) {
													opzioni += ' selected';

												}
												opzioni += '>' + element.value
														+ '</option>';

											});
							selectFunzione += opzioni + '</select>';
							$('#tr_repo_' + codice + ' td:nth-child(8)').html(
									selectFunzione);
							$('#edit_' + codice).hide();

							$('#save_' + codice).show();
							$('#undo_' + codice).show();
							return false;

						}
					});
}

function saveRepo(codice) {

	$('#collocazione').val($('#new_collocazione').val());

	$('#descrizione').val($('#new_descrizione').val());

	$('#authenticationRequired').val($('#new_authenticationRequired').val());
	$('#gestioneConsensi').val($('#new_gestioneConsensi').val());
	$('#gestioneIdEpisodio').val($('#new_gestioneIdEpisodio').val());
	$('#tipoRepository').val($('#new_tipoRepository').val());

	$('#mainSezErrori').hide();
	$('#repoSezErrori').hide();
	var $form = $('#repoFormData');
	var $inputs = $form.find('input, select, textarea');
	showLoading('Salvataggio in corso');
	var formdata = ($inputs).serialize();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = ctx + '/repo/updateRepo.json';

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
						$('#repoSezOK').html("Dati salvati con successo")
						$('#repoSezOK').show();
						$('#repoSezOK').delay(5000).fadeOut('slow');
						initRepos();

					}
					if (msg != '') {
						$('#repoSezErrori').html(msg)
						$('#repoSezErrori').show();

						$(window).scrollTop(
								$('#repoSezErrori').offset().top - 120);

					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					hideLoading();
					checkRedirect(jqXHR);
					alert(errorThrown);
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

function undoCurrentRepo() {

	if ($('#codice').val() != '') {

		undoRepo($('#codice').val());
	}

}

function undoRepo(codice) {
	$('#repoSezErrori').hide();
	$.each(repos, function(index, param) {

		if (param.codice == codice) {

			$('#tr_repo_' + codice + ' td:nth-child(3)')
					.html(param.descrizione);

			$('#tr_repo_' + codice + ' td:nth-child(4)').html(
					param.collocazione);

			$('#tr_repo_' + codice + ' td:nth-child(5)').html(
					'' + param.authenticationRequired);

			var consensi = "ALL";
			if (param.gestioneConsensi == 'S') {
				consensi = "SLOT";
			} else if (param.gestioneConsensi == 'C') {
				consensi = "Piattaforma Consensi";
			}

			$('#tr_repo_' + codice + ' td:nth-child(6)').html('' + consensi);

			$('#tr_repo_' + codice + ' td:nth-child(7)').html(
					'' + param.gestioneIdEpisodio);

			$('#tr_repo_' + codice + ' td:nth-child(8)').html(
					'' + param.tipoRepository);

			$('#edit_' + codice).show();

			$('#save_' + codice).hide();
			$('#undo_' + codice).hide();
			$('#codice').val('');

			return false;

		}
	});
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

$(document).ready(function() {

	initRepos();

});

function dettRepository(codice) {
	undoNew();
	undoCurrentRepo();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$('#captionUri')
			.html(
					'Transazioni Repository <strong>'
							+ codice
							+ '</strong>'
							+ '<a href="javascript:addUri(\''
							+ codice
							+ '\')" style="float:right;color:#0a3e94;;float:right;margin-right:5px;"><i class="fas fa-plus-square" ></i>&nbsp;Aggiungi Transazione</a>');

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
						$('#repoAccountsDiv').hide();

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
