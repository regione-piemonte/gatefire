<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<html lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="currentMenu" content="admin" />
<title>Configurazione Parametri</title>



<style>
#tabCa tr:hover {
	background-color: #FFF7E0 !important;
}

#tabCa tr {
	cursor: pointer !important;
}

#tabParams {
	width: 1200px;
}

#tabParams input, #tabParams select {
	margin-bottom: 0;
}

#newRow td {
	background-color: #FFF7E0;
}

.valore {
	white-space: nowrap;
	width: 400px;
	/*display: block;
	width: 100%;
	min-width: 1px;*/
}

#captionParams {
	width: 100%;
}
</style>


</head>
<body>


	<h2>Configurazione Parametri generici</h2>



	<leoTag:messages dismissible="true"></leoTag:messages>


	<div class="sezOK" id="mainSezOK"></div>
	<div class="sezErrori" id="mainSezErrori"></div>
	<div id="paramDiv" style="margin-top: 30px; display: none;">



		<table class="table table-data table-striped table-bordered"
			id="tabParams" style="width: 1200px;">

			<thead>
				<tr>
					<th scope="col">Parametro</th>

					<th scope="col">Valore</th>
					<th scope="col" style="width: 80px;"></th>
				</tr>
			</thead>
			<tbody></tbody>

		</table>

		<form id="formData" style="border: 0">

			<input type="hidden" name="params" id="params" value="true" /> <input
				type="hidden" name="id" id="id" value="" /> <input type="hidden"
				name="parametro" id="parametro" /> <input type="hidden"
				name="codiceFunzione" id="codiceFunzione" /> <input type="hidden"
				name="codiceApplicazione" id="codiceApplicazione" /> <input
				type="hidden" name="valore" id="valore" />
		</form>
	</div>




	<div style="display: none;">
		<select id="opzioniDisponibili">
			<option value=""></option>
			<c:forEach items="${parametriDisponibili}" var="parametro">
				<option value="${parametro.parametro}"
					title="${parametro.descrizione}"
					data-param-type="${parametro.tipoParametro}">${parametro.parametro}</option>
			</c:forEach>
		</select> <select id="funzioniDisponibili">
			<option value=""></option>
			<c:forEach items="${funzioniDisponibili}" var="parametro">
				<option value="${parametro.codice}" title="${parametro.descrizione}">${parametro.codice}</option>
			</c:forEach>
		</select> <select id="applicazioniDisponibili">
			<option value=""></option>
			<c:forEach items="${applicazioniDisponibili}" var="parametro">
				<option value="${parametro.codice}" title="${parametro.descrizione}"
					data-nome="${parametro.nome}">${parametro.nome}-
					${parametro.descrizione }</option>
			</c:forEach>
		</select>
	</div>
	<script src="${ctx }/resources/js/config/params.js">
		
	</script>
</body>
</html>
