<%@ tag body-content="scriptless"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="fade" required="false" rtexprvalue="true"%>
<%@ attribute name="style" required="false" rtexprvalue="true"%>
<%@ attribute name="classe" required="false" rtexprvalue="true"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />



<c:set var="stile"></c:set>
<c:if test="${!empty style }">
	<c:set var="stile">style="${style}"</c:set>
</c:if>
<%-- Success Messages --%>

<c:if test="${not empty successInlineMessages}">

	<div class="sezOK ${classe}" id="succInlineMsg" ${stile}>
		<c:forEach var="msg" items="${successInlineMessages}"
			varStatus="status">
			<c:if test="${!status.first}">
			&nbsp;&nbsp;-&nbsp;&nbsp;
			</c:if>
			<c:out value="${msg}" escapeXml="false" />
		</c:forEach>
	</div>
	<c:remove var="successInlineMessages" scope="session" />

	<c:if test="${fade==true}">
		<script>
			$(document).ready(function() {
				$('#succInlineMsg').delay(5000).fadeOut('slow');
			});
		</script>
	</c:if>
</c:if>

<c:if test="${not empty errorInlineMessages}">

	<div class="sezErrori ${classe}" id="errorInlineMsg" ${stile}>
		<c:forEach var="msg" items="${errorInlineMessages}" varStatus="status">
			<c:if test="${!status.first}">
				&nbsp;&nbsp;-&nbsp;&nbsp;
			</c:if>
			<c:out value="${msg}" escapeXml="false" />
		</c:forEach>
	</div>
	<c:remove var="errorInlineMessages" scope="session" />
	<c:if test="${fade==true}">
		<script>
			/*$(document).ready(function() {
				$('#errorInlineMsg').delay(5000).fadeOut('slow');
			});*/
		</script>
	</c:if>

</c:if>


<c:if test="${not empty warningInlineMessages}">

	<div class="sezWarning ${classe}" id="warnInlineMsg" ${stile}>
		<c:forEach var="msg" items="${warningInlineMessages}"
			varStatus="status">
			<c:if test="${!status.first}">
				&nbsp;&nbsp;-&nbsp;&nbsp;
			</c:if>
			<c:out value="${msg}" escapeXml="false" />
		</c:forEach>
		</div>
		<c:remove var="warningInlineMessages" scope="session" />

		<c:if test="${fade==true}">
			<script>
				/*$(document).ready(function() {
					$('#warnInlineMsg').delay(5000).fadeOut('slow');
				});*/
			</script>
		</c:if>
</c:if>



