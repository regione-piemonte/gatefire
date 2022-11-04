var modificato = false;

var MENU_CONST = {
	HOME : 'home',
	EVENTI : 'eventi',
	CONF_CA : 'conf_ca',
	PARAMS : 'params',
	ADMIN_MAIN : 'mainAdmin',
	ADMIN_CRYPT : 'adminCript',
	ADMIN_CACHE : 'adminCache',
	ADMIN_PING : 'adminPing',
	ADMIN_LOGS : 'adminLogs',
	REPOSITORY : 'repoConfig'

};

function navigate(elem) {
	if (elem == MENU_CONST.HOME) {
		navigateUrl('/home');
	} else if (elem == MENU_CONST.EVENTI) {
		navigateUrl('/audit/listaEventi');
	} else if (elem == MENU_CONST.CONF_CA) {
		navigateUrl('/conf/configCA');
	} else if (elem == MENU_CONST.PARAMS) {
		navigateUrl('/conf/configParam');
	} else if (elem == MENU_CONST.ADMIN_MAIN) {
		navigateUrl('/admin/admin');
	} else if (elem == MENU_CONST.ADMIN_CRYPT) {
		navigateUrl('/admin/criptaPsw');
	} else if (elem == MENU_CONST.ADMIN_CACHE) {
		navigateUrl('/admin/refreshCache');
	} else if (elem == MENU_CONST.ADMIN_PING) {
		navigateUrl('/admin/pingCA');
	} else if (elem == MENU_CONST.ADMIN_LOGS) {
		navigateUrl('/admin/logList');
	} else if (elem == MENU_CONST.REPOSITORY) {
		navigateUrl('/repo/repoConfig');
	}

}

function navigateUrl(url) {

	goUrl(url);

}

function goUrl(url) {
	var letter = url.substr(0, 1);

	var loc = '';
	if (letter == '/')
		loc = ctx + url;
	else
		loc = ctx + '/' + url;

	// add dummy param
	var dummy = 'dy1=' + getCurrMills();
	var targetIndex = loc.indexOf("#");

	var target = '';
	if (targetIndex != -1) {
		target = loc.substring(targetIndex);
		loc = loc.substring(0, targetIndex);
	}

	if (loc.indexOf("?") > -1) {
		loc += '&' + dummy;
	} else {
		loc += '?' + dummy;
	}
	showLoading();
	if (modificato) {
		hideLoading();
	}
	if (targetIndex != -1) {
		loc += target;
	}

	document.location = loc;
}

function fillDummy(url) {
	var letter = url.substr(0, 1);

	var loc = '';
	if (letter == '/')
		loc = ctx + url;
	else
		loc = ctx + '/' + url;

	// add dummy param
	var dummy = 'dy1=' + getBgg();
	if (loc.indexOf("?") > -1) {
		loc += '&' + dummy;
	} else {
		loc += '?' + dummy;
	}
	return loc;
}

/*
 * GESTIONE LOADING DIV
 */
function showLoading(msg) {

	$('#loadingDiv').html("Caricamento in Corso ...");

	$('#loadingDiv_overlay').show();

	if (msg != null && msg != '') {
		$('#loadingDiv').html(msg);
	}
	$('#loadingDiv').show();

}

function hideLoading() {
	// waitingDialog.hide();

	$('#loadingDiv').hide();
	$('#loadingDiv_overlay').hide();

}

function getCurrMills() {
	var data = new Date();
	return data.getTime();
}

function submitForm(myForm) {

	showLoading();

	$('#' + myForm).submit();
}

function formattaDataDaJava(millis, visualizzaOra, visualizzaSecondi) {

	if (millis != null) {
		var dataIn = new Date(millis);
		var mese = dataIn.getMonth() + 1;
		var giorno = dataIn.getDate();
		var anno = dataIn.getFullYear();
		var ora = dataIn.getHours();
		var minuti = dataIn.getMinutes();
		var secondi = dataIn.getSeconds();

		if (giorno < 10) {
			giorno = "0" + giorno;
		}

		if (mese < 10) {
			mese = "0" + mese;
		}
		var returnDate = giorno + "/" + mese + "/" + anno;

		if (visualizzaOra) {

			if (ora < 10) {
				ora = "0" + ora;
			}

			if (minuti < 10) {
				minuti = "0" + minuti;
			}

			if (secondi < 10) {
				secondi = "0" + secondi;
			}

			returnDate += " " + ora + ":" + minuti;
			if (visualizzaSecondi)
				returnDate += ":" + secondi;
		}

		return returnDate;
	} else {
		return '';
	}
}

function collectFormData(fields) {
	var data = {};
	for (var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		data[$item.attr('name')] = $item.val();
	}
	return data;
}

function resetDropdown(select_id) {

	$('#' + select_id).html('');

}

function populateDropdown(select_id, data, firstRowMsg) {

	$('#' + select_id).html('');
	var items = '';
	if (data.length > 1) {
		items = '<option value="">' + firstRowMsg + '</option>';
	}
	$.each(data, function(id, option) {
		items += '<option value="' + option + '"';

		items += '>' + option + '</option>';
	});

	$('#' + select_id).html(items);

}

function addRow(table, html) {
	$("#" + table).last().append(html);
}
function removeRows(table) {
	$("#" + table + " tbody").empty();

}

function writeCell(input, cssClass, style) {
	var stile = '';
	var classe = '';

	if (cssClass)
		classe = 'class="' + cssClass + '"';

	if (style)
		stile = 'style="' + style + '"';
	var html = '<td ' + stile + ' ' + classe + '>';

	if (input != null)
		html += escapeHtml(input);
	html += '</td>';
	return html;
}

function writeCellNoEscapizz(input, cssClass, style) {
	var stile = '';
	var classe = '';

	if (cssClass)
		classe = 'class="' + cssClass + '"';

	if (style)
		stile = 'style="' + style + '"';
	var html = '<td ' + stile + ' ' + classe + '>';
	if (input != null)
		html += input;
	html += '</td>';
	return html;
}

var entityMap = {
	"&" : "&amp;",
	"<" : "&lt;",
	">" : "&gt;",
	'"' : '&quot;',
	"'" : '&#39;',
	"/" : '&#x2F;'
};

function escapeHtml(string) {
	return String(string).replace(/[&<>"'\/]/g, function(s) {
		return entityMap[s];
	});
}

function pulisci(campo) {
	$('#' + campo).val('');
}

function checkSession(jqXHR) {

	var loginPageRedirectHeader = jqXHR.getResponseHeader("LoginPage");

	console.log(jqXHR.getAllResponseHeaders());
	if (jqXHR.status == 403) {
		window.location.replace(ctx);
		return false;
	}
	if (loginPageRedirectHeader && loginPageRedirectHeader !== "") {

		window.location.replace(ctx);
		return false;
	}

	return true;

}

function checkRedirect(jqXHR) {
	if ((jqXHR.status === 200 && contentType.toLowerCase().indexOf("text/html") >= 0)
			|| jqXHR.status == 403 || (jqXHR.status == 0)) {

		window.location.replace(ctx);
	}
}

function resetForm(idForm) {
	$('#' + idForm)
			.find("input[type=text],input[type=hidden], textarea,select").val(
					"");

}

function resetTable(tableid, preserveScroll) {

	var scrollPos = $(".dataTables_scrollBody").scrollTop();

	$('#' + tableid).DataTable().draw(false);
	if (preserveScroll) {
		$(".dataTables_scrollBody").scrollTop(scrollPos);
	}
}

function formattaNumero(number, decimals, dec_point, thousands_sep) {

	var n = number, c = isNaN(decimals = Math.abs(decimals)) ? 2 : decimals;
	var d = dec_point == undefined ? "." : dec_point;
	var t = thousands_sep == undefined ? "," : thousands_sep, s = n < 0 ? "-"
			: "";
	var i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "", j = (j = i.length) > 3 ? j % 3
			: 0;

	return s + (j ? i.substr(0, j) + t : "")
			+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t)
			+ (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
}

Number.prototype.countDecimals = function() {
	if (Math.floor(this.valueOf()) === this.valueOf())
		return 0;
	return this.toString().split(".")[1].length || 0;
}

function formatNumAuto(number) {
	var numDecimals = parseFloat(number).countDecimals();
	return formattaNumero(number, numDecimals, ",", ".");
}

function formattaValuta(number, decimals, auto) {

	if (auto == true) {
		var numDecimals = parseFloat(number).countDecimals();
		if (numDecimals > decimals) {
			return formattaNumero(number, numDecimals, ",", ".");
		}
	}

	return formattaNumero(number, decimals, ",", ".");
}

function drawPagination(numPages, currPage) {

	var numElemsToView = 7;

	currPage = parseInt(currPage, 10);

	var html = '<nav><ul class="pagination pagination-sm">';

	html += '<li';
	if (currPage == 1) {
		html += ' class="disabled"><a ';
	} else {
		html += '> <a href="javascript:goPage(\'' + (currPage - 1)
				+ '\')" title="Precedente"';
	}
	html += '><span class="glyphicon glyphicon-chevron-left"></span></a></li>'

	if (numPages > numElemsToView) {

		html += drawLiPage(1, currPage);

		if (currPage >= 5) {
			html += '<li><a href="#">...</a></li>';
		} else {
			html += drawLiPage(2, currPage);
		}
		if (currPage >= 5 && currPage <= (numPages - 3)) {
			html += drawLiPage((currPage - 1), currPage);
			html += drawLiPage(currPage, currPage);
			var numero = currPage + 1;

			html += drawLiPage(numero, currPage);

		} else if (currPage <= 5) {
			html += drawLiPage(3, currPage);
			html += drawLiPage(4, currPage);
			html += drawLiPage(5, currPage);

		} else if (currPage >= (numPages - 3)) {
			html += drawLiPage((numPages - 4), currPage);
			html += drawLiPage((numPages - 3), currPage);
			html += drawLiPage((numPages - 2), currPage);

		}

		if (currPage >= (numPages - 3)) {
			html += drawLiPage((numPages - 1), currPage);
		} else {
			html += '<li><a href="#">...</a></li>';
		}

		html += drawLiPage(numPages, currPage);

	} else {
		for (var i = 1; i <= numPages; i++) {
			html += drawLiPage(i, currPage);
		}
	}
	html += '<li';
	if (currPage == numPages) {
		html += ' class="disabled"><a ';
	} else {
		html += '> <a href="javascript:goPage(\'' + (currPage + 1)
				+ '\')"  title="Successiva"';
	}
	html += '><span class="glyphicon glyphicon-chevron-right"></span></a></li>'
	html += '</ul></nav>';
	return html;

}

function drawLiPage(num, currPage) {
	var html = '<li';
	if (num == currPage) {
		html += ' class="active"';

	}
	html += '>';
	html += '<a href="javascript:goPage(\'' + num + '\')">' + num + '</a>';
	html += '</li>';
	return html;

}

function selectAllStati(selezionato) {

	$("input[name=stato]").prop('checked', selezionato);

}

function checkStatoSel() {
	$('#statoSelection').dropdown('hide');
	var boxes = $("input[name=stato]:checkbox:checked");

	if (boxes.length == 0) {

		bootbox.alert({
			size : 'small',
			message : "Selezionare almeno uno stato",
			animate : false,

			callback : function() {
				$('#statoSelection').dropdown('show');
			},
			buttons : {
				'ok' : {
					label : 'OK',
					className : 'btn btn-default'
				}
			}
		});

		return false;
	} else {
		return true;
	}
}

function indietro() {

	navigateUrl(historyUrl);

}

function escapeJqId(myid) {

	return "#" + myid.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1");

}

function logout() {
	submitForm('logoutForm');
}

$.fn.appendRow = function($rows) {
	if ($(this).parent().hasClass("dataTables_wrapper")) {
		var dt = $(this).DataTable();
		$rows.each(function() {
			dt.row.add(this);
		});
		dt.draw(false);
	} else {
		$(this).append($rows);
	}
}

function controllaPartitaIVA(pi) {
	if (pi == '')
		return 'Non hai inserito alcun valore';
	else if (!/^[0-9]{11}$/.test(pi))
		return 'La partita IVA deve contenere 11 cifre.';
	else {
		var s = 0;
		for (var i = 0; i <= 9; i += 2) {
			s += pi.charCodeAt(i) - '0'.charCodeAt(0);
		}
		for (i = 1; i <= 9; i += 2) {
			var c = 2 * (pi.charCodeAt(i) - '0'.charCodeAt(0));
			if (c > 9)
				c = c - 9;
			s += c;
		}
		var controllo = (10 - s % 10) % 10;
		if (controllo != pi.charCodeAt(10) - '0'.charCodeAt(0))
			return 'La partita IVA non sembra corretta';
		else
			return '';
	}
}

function controllaCF(cf) {

	var regex = /^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/;
	// Definisco un pattern per il confronto
	// var pattern =
	// /^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$/;

	// utilizzo il metodo search per verificare che il valore inserito nel campo
	// di input rispetti la stringa di verifica (pattern)
	if (cf.search(regex) == -1) {

		return "Il valore inserito non è un codice fiscale!";

	} else {
		return "";
	}
}

function resetDropdown(select_id) {

	$('#' + select_id).html('');

}



