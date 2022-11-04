<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<html lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="currentMenu" content="admin" />
<title>Audit eventi</title>


<style>
.stato-toggle.dropdown-toggle::after {
	border-bottom: .3em solid;
	border-right: .3em solid transparent;
	border-top: 0;
	border-left: .3em solid transparent;
}

#tabEventi tbody tr:hover {
	background-color: #fdc6d0 !important;
}

.modal-content {
	min-height: 800px;
}
</style>



</head>
<body>

	<h2 id="header">Eventi</h2>


	<leoTag:messages dismissible="true" />




	<div class="sezErrori" id="mainSezErrori"></div>

	<form:form modelAttribute="filterBean" id="filterForm" method="post"
		action="${ctx }/audit/dettEvento">
		<legend>Imposta filtri</legend>
		<input type="hidden" name="id" id="id" />
		<input type="hidden" name="search" value="Y" />


		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">


				<form:label path="codiceTipoEvento">Tipo Evento</form:label>
				<br />
				<form:select path="codiceTipoEvento" style="max-width:250px;">

					<form:option value="">Tutti</form:option>
					<c:forEach items="${tipiEvento }" var="tEvento">
						<form:option value="${tEvento.codice }">${tEvento.descrizione}</form:option>
					</c:forEach>
				</form:select>

			</div>




			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<label for="statoSelection">Stato</label><br> <select
					name="stato" id="stato">
					<option value="">Tutte</option>
					<c:forEach items="${statiFiltro}" var="stato" varStatus="status">
						<option value="${stato.codice}"><c:choose>
								<c:when test="${fn:length(stato.descrizione )>23 }">${fn:substring(stato.descrizione,0,23) }</c:when>
								<c:otherwise>${stato.descrizione }</c:otherwise>
							</c:choose>
						</option>
					</c:forEach>
				</select>


			</div>

			<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
				<label for="CA">CA</label><br> <select
					name="codiceCertificationAuthority"
					id="codiceCertificationAuthority">
					<option value="">Tutte</option>
					<c:forEach items="${cas }" var="ca">
						<option value="${ca.codice}">${ca.ragioneSociale}</option>
					</c:forEach>


				</select>
			</div>




			<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
				<label for="dataDa">Data inizio</label> <br> <input
					name="dataDa" type="text" id="dataDa" class="data"
					value="<fmt:formatDate value="${filterBean.dataDa }" pattern="dd/MM/yyyy"/>"
					placeholder="GG/MM/AAAA">
			</div>
			<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
				<label for="dataA">Data fine</label><br> <input name="dataA"
					type="text" id="dataA" class="data" placeholder="GG/MM/AAAA"
					autocomplete="off">
			</div>

			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<label for="applicazione">Applicazione</label><br> <select
					name="codiceApplicazione" id="codiceApplicazione">
					<option value="">Tutte</option>
					<c:forEach items="${applicazioni}" var="app">
						<option value="${app.codice}">${app.descrizione }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<label for="applicazione">Collocazione</label><br> <select
					name="collocazione" id="collocazione">
					<option value="">Tutte</option>
					<c:forEach items="${collocazioni}" var="col">
						<option value="${col}">${col }</option>
					</c:forEach>
				</select>
			</div>



			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<label for="codiceFiscale">Codice fiscale</label><br> <input
					name="codiceFiscale" type="text" id="codiceFiscale" />
			</div>

			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<label for="nomeFile">Nome file</label><br> <input
					name="nomeFile" type="text" id="nomeFile">
			</div>




			<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12"
				style="padding-top: 30px;">

				<button id="appFiltro" class="btn btn-primary btn-xs"
					style="margin-top: 5px;" type="button" onclick="filtra()">Applica
					filtri</button>

				<button id="btnReset" class="btn btn-primary btn-xs"
					style="margin-top: 5px;" type="button" onclick="doreset()">Pulisci
					filtri</button>
			</div>

		</div>


	</form:form>




	<div id="sezTabella" style="display: none;">

		<table class="table table-data table-striped table-bordered"
			id="tabEventi" style="width: 100%">
			<thead>
				<tr>

					<th scope="col" style="width: 50px;"></th>
					<th scope="col" style="width: 50px;">Id evento</th>
					<th scope="col" class="datafull">Data inizio</th>
					<th scope="col" style="width: 180px;">Tipo Evento</th>
					<th scope="col" style="width: 120px;">Stato</th>
					<th scope="col" class="datafull">Data stato</th>
					<th scope="col" class="datafull">Nome file</th>
					<th scope="col" style="width: 180px;">Applicazione</th>
					<th scope="col" style="width: 100px;">Collocazione</th>
					<th scope="col" style="width: 200px;">Codice fiscale</th>
					<th scope="col" style="width: 100px;">CA</th>




				</tr>



			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div class="modal fade" id="dettaglio" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="exampleModalLabel">Lista step
						dell'evento</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="dettaglioStep">
							<dl>
								<div>
									<dt>Id evento</dt>
									<dd id="pop_id"></dd>
								</div>
								<div>
									<dt>Tipo evento</dt>

									<dd id="pop_codiceTipoEvento"></dd>
								</div>
								<div>
									<dt>Stato</dt>

									<dd id="pop_codiceStatoEvento"></dd>
								</div>
								<div>
									<dt>Data Stato</dt>
									<dd id="pop_dataAggStato"></dd>
								</div>
								<div>
									<dt>Nome file</dt>
									<dd id="pop_nomeFile"></dd>
								</div>
								<div>
									<dt>Applicazione</dt>
									<dd id="pop_codiceApplicazione"></dd>
								</div>
								<div>
									<dt>Collocazione</dt>
									<dd id="pop_collocazione"></dd>
								</div>
								<div>
									<dt>IP Applicazione</dt>
									<dd id="pop_ipApplicazione"></dd>
								</div>
								<div>
									<dt>Codice Fiscale</dt>
									<dd id="pop_codiceFiscale"></dd>
								</div>
								<div>
									<dt>CA</dt>
									<dd id="pop_codiceCertificationAuthority"></dd>
								</div>
								
								<div id="div_idSessione" style=""display:none;">
									<dt>Id sessione:</dt>
									<dd id="pop_idSessione"></dd>
								</div>

							</dl>

							<table class="table table-data table-striped table-bordered"
								id="tabLog" style="width: 100%">

								<thead>
									<tr role="row">

										<th scope="col">Id</th>
										<th scope="col">Data</th>
										<th scope="col">Tipo passo</th>
										<th scope="col">Stato</th>
										<th scope="col">Descr.</th>
										<th scope="col">Ret. code</th>
										<th scope="col">Ret. code descr.</th>


									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>


				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
					<button id="appFiltro" class="btn btn-primary btn-xs"
						style="margin: 10px 0;" type="button" data-dismiss="modal"
						aria-label="Close">Chiudi</button>
				</div>
			</div>
		</div>
	</div>
	<script>
var search=<c:out value="${!empty search}"/>;

</script>

	<script src="${ctx }/resources/js/audit/listaEventi.js">
		
	</script>

</body>
</html>
