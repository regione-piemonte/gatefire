<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<html lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="currentMenu" content="admin" />
<title>Configurazione Certification Authority</title>



<style>
#tabCa tr:hover {
	background-color: #fdc6d0 !important;
}

#tabCa tr {
	cursor: pointer !important;
}

#tabUris input, #tabUris select, #tabRepo input, #tabRepo select {
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
	width: 900px;
}
</style>


</head>
<body>


	<h2>Configurazione Repositories</h2>



	<leoTag:messages dismissible="true"></leoTag:messages>
	<div class="sceltaRepo">
		<div class="sezOK" id="repoSezOK"></div>
		<div class="sezErrori" id="repoSezErrori"></div>



		<table class="table table-data table-striped table-bordered"
			id="tabRepo">

			<thead>
				<tr>
					<th scope="col">Codice</th>
					<th scope="col">Nome</th>
					<th scope="col">Descrizione</th>
					<th scope="col">Collocazione</th>
					<th scope="col">Autenticazione STS</th>
					<th scope="col">Gestione Consensi</th>
					<th scope="col">Gestione Id Episodio</th>
					<th scope="col">Tipo Repository</th>
					<th></th>
				</tr>
			</thead>
			<tbody>

			</tbody>
			<tfoot></tfoot>
		</table>



	</div>

	<div class="sezOK" id="mainSezOK"></div>
	<div class="sezErrori" id="mainSezErrori"></div>
	<div id="repoUrisDiv" style="display: none; margin-top: 30px;">


		<div class="caption" id="captionUri" style="width: 100%"></div>
		<table class="table table-data table-striped table-bordered"
			id="tabUris">

			<thead>
				<tr>
					<th scope="col">Transazione</th>
					<th scope="col">uri</th>

					<th scope="col"></th>
				</tr>
			</thead>
			<tbody></tbody>
			<tfoot></tfoot>
		</table>

		<div>
			<form id="formData" style="border: 0;">

				<input type="hidden" name="codiceRepository" id="codiceRepository"
					value="" /> <input type="hidden" name="id" id="id" value="" /> <input
					type="hidden" name="transazione" id="transazione" /> <input
					type="hidden" name="uri" id="uri" />



			</form>

			<form id="repoFormData" style="border: 0;">

				<input type="hidden" name="codice" id="codice" value="" /> <input
					type="hidden" name="collocazione" id="collocazione" value="" /> <input
					type="hidden" name="descrizione" id="descrizione" /> <input
					type="hidden" name="authenticationRequired"
					id="authenticationRequired" /> <input type="hidden"
					name="gestioneConsensi" id="gestioneConsensi" /> <input
					type="hidden" name="gestioneIdEpisodio" id="gestioneIdEpisodio" />
				<input type="hidden" name="tipoRepository" id="tipoRepository" />


			</form>

			<form id="accountFormData" style="border: 0;">

				<input type="hidden" name="codiceRepository" id="codiceRepositoryAccount"
					value="" /> <input type="hidden" name="id" id="idAccount" value="" /> <input
					type="hidden" name="username" id="username" /> <input
					type="hidden" name="hashedPassword" id="hashedPassword" /> <input
					type="hidden" name="codiceApplicazione" id="codiceApplicazione"
					value="" />



			</form>
		</div>

	</div>

	<div id="repoAccountsDiv" style="display: none; margin-top: 30px;">


		<div class="caption" id="captionAccounts" style="width: 100%"></div>
		<table class="table table-data table-striped table-bordered"
			id="tabAccounts">

			<thead>
				<tr>
					<th scope="col">Applicazione</th>
					<th scope="col">Username</th>
					<th scope="col">Salt</th>
					<th scope="col">EncryptedPsw</th>
					<th scope="col">Password</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody></tbody>
			<tfoot></tfoot>
		</table>
	</div>

	<div style="display: none;">
		<select id="transazioniDisponibili">

			<c:forEach items="${transazioniDisponibili}" var="parametro">
				<option value="${parametro}">${parametro}</option>
			</c:forEach>
		</select> <select id="tipoRepoDisponibili">

			<c:forEach items="${tipoRepoDisponibili}" var="tRepo">
				<option value="${tRepo}">${tRepo}</option>
			</c:forEach>
		</select> <select id="appDisponibili">

			<c:forEach items="${appDisponibili}" var="tApp">
				<option value="${tApp.codice}">${tApp.nome}</option>
			</c:forEach>
		</select>

	</div>

	<div class="modal fade" id="editAccount" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="editAccountLabel">Nuovo Account</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
			
					<div class="sezErrori" id="accountSezErrori"></div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<p> 
							<label style="width:120px">Applicazione</label> <select id="applicazione_new"></select><br />
							<label style="width:120px">Username</label> <input type="text" id="username_new" /><br />
							<label style="width:120px">Password</label> <input type="text" id="password_new" />
					</div>


				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
					<button class="btn btn-primary btn-xs" style="margin: 10px 0;" id="saveAccountBtn"
						type="button" onclick="saveAccount()">Salva</button>

					<button id="appFiltro" class="btn btn-secondary btn-xs"
						style="margin: 10px 0;" type="button" data-dismiss="modal"
						aria-label="Close">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/resources/js/repo/repoConfig.js">
		
	</script>
	<script src="${ctx }/resources/js/repo/repoAccountsConfig.js"></script>
</body>
</html>
