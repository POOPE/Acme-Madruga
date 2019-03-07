<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="permitAll">
	<jstl:set var="uri" value="procession/list.do"/>
</security:authorize>
<security:authorize access="hasRole('BROTHERHOOD')">
	<jstl:set var="uri" value="procession/myList.do"/>
</security:authorize>
<display:table name="processions" id="row" requestURI="${uri}"
	pagesize="5" class="displaytag">

	<jstl:if test="${row.isInFinalMode}">
	<!-- ticker -->
	<spring:message code="procession.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}"
		sortable="true" />


	<!-- title -->
	<spring:message code="procession.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />


	<!-- description -->
	<spring:message code="procession.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="false" />


	<!-- moment -->
	<spring:message code="procession.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"
		sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />


	<!-- category -->
	<spring:message code="procession.brotherhood" var="brotherhoodHeader" />
	<display:column property="procession.brotherhood.title" title="${brotherhoodHeader}"
		sortable="true" />



	<!-- Display -->	
	<spring:message code="procession.display" var="displayHeader" />
	<display:column title="${displayHeader}">
		<a href="procession/display.do?processionID=${row.id}"> <spring:message
				code="procession.display" /></a>
	</display:column>
	
	</jstl:if>
	
	<!-- Update -->
	<security:authorize access="hasRole('BROTHERHOOD')">
		<spring:message code="procession.update" var="updateHeader" />
		<display:column title="${updateHeader}">
			<a href="procession/update.do?processionID=${row.id}"> <spring:message
					code="procession.update" /></a>
		</display:column>
	</security:authorize>
</display:table>