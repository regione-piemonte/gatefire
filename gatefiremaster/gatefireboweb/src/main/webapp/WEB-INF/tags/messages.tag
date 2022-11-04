<%@ tag body-content="scriptless"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="width" required="false" rtexprvalue="true"%>
<%@ attribute name="popup" required="false" rtexprvalue="true"%>
<%@ attribute name="dismissible" required="false" rtexprvalue="true" %>
<c:if test="${empty dismissible}" >
 <c:set var="title" value="true" />
</c:if>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="presente" value="${false}" />
<c:if test="${popup=='true'}">
	<div class="modal fade" id="theModalMessages">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
</c:if>
<%-- Error Messages --%>
<c:if test="${not empty errorMessages}">

	<div class="alert alert-danger alert-dismissible" role="alert"
		<c:if test="${!empty width }">style="width:${width };"</c:if>>
		<c:if test="${popup!='true' && dismissible=='true'}">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</c:if>
		<c:forEach var="msg" items="${errorMessages}" varStatus="status">
			<c:if test="${!status.first}">
				<br />

			</c:if>
			<c:out value="${msg}" escapeXml="false" />
		</c:forEach>
	</div>

	<c:remove var="errorMessages" scope="session" />
	<c:set var="presente" value="${true}" />
</c:if>



<%-- Success Messages --%>

<c:if test="${not empty successMessages}">
	<div class="alert alert-success alert-dismissible" role="alert"
		<c:if test="${!empty width }">style="width:${width };"</c:if>>
		<c:if test="${popup!='true'&& dismissible=='true'}">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</c:if>
		<c:forEach var="msg" items="${successMessages}" varStatus="status">
			<c:if test="${!status.first}">
				<br />

			</c:if>
			<c:out value="${msg}" escapeXml="false" />
		</c:forEach>
	</div>
	<c:remove var="successMessages" scope="session" />
	<c:set var="presente" value="${true}" />
</c:if>

<%-- Warning Messages --%>

<c:if test="${not empty warningMessages}">

	<div class="alert alert-warning alert-dismissible" role="alert"
		<c:if test="${!empty width }">style="width:${width };"</c:if>>
		<c:if test="${popup!='true' && dismissible=='true'}">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</c:if>
		<c:forEach var="msg" items="${warningMessages}" varStatus="status">
			<c:if test="${!status.first}">
				<br />

			</c:if> 
			<c:out value="${msg}" escapeXml="false" />
		</c:forEach>
	</div>
	<c:remove var="warningMessages" scope="session" />
	<c:set var="presente" value="${true}" />

</c:if>

<%-- info Messages --%>
<c:if test="${not empty infoMessages}">
	<div class="alert alert-info alert-dismissible" role="alert"
		<c:if test="${!empty width }">style="width:${width };"</c:if>>
		<c:if test="${popup!='true' && dismissible=='true'}">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</c:if>
		<c:forEach var="msg" items="${infoMessages}" varStatus="status">
			<c:if test="${!status.first}">
				<br />

			</c:if>
			<c:out value="${msg}" escapeXml="false" />
		</c:forEach>
	</div>

	<c:remove var="infoMessages" scope="session" />
	<c:set var="presente" value="${true}" />
</c:if>
<c:if test="${ popup==true}">
	</div>
	</div>
	</div>
	</div>
</c:if>

<c:if test="${presente && popup=='true'}">

	<script type="text/javascript">
		$(document).ready(function() {
			$('#theModalMessages').modal();
		});
	</script>
</c:if>

