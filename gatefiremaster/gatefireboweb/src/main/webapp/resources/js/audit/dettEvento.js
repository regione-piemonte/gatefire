('#tabLog tbody').on('click', 'td.detailLog', function() {

	var tr = $(this).closest('tr');
	gestTabella(tr)

});

function gestTabella(tr) {

	var id = tr.attr("id").substring(3);

	var idSub = 'tr_' + id + '_sub';

	var nCelle = $('#' + idSub).find('td').length;

	if (nCelle > 2) {
		$('#' + idSub).find('td.dummy').remove();
		$('#' + idSub).find('td.tdContainer').attr('colspan', '7');
	}

	if (tr.hasClass("opened")) {
		tr.removeClass("opened");
		$('#' + idSub).hide();
		$('#span_' + id).removeClass('glyphicon-minus');
		$('#span_' + id).addClass('glyphicon-plus');
	} else {
		$('#span_' + id).removeClass('glyphicon-plus');
		$('#span_' + id).addClass('glyphicon-minus');
		tr.addClass("opened");
		$('#' + idSub).show();

	}
}

function indietro() {
	submitForm('filterForm');
}