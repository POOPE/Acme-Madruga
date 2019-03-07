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
	
	<display:column property="ticker" 
		sortable="true" />


	<!-- title -->
<%-- 	<spring:message code="procession.title" var="titleHeader" /> --%>
	<display:column property="title" titleKey="procession.title"
		sortable="true" />


	<!-- description -->
	<display:column property="description" titleKey="procession.description"
		sortable="false" />


	<!-- moment -->
	
	<display:column property="moment" titleKey="procession.moment"
		sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />


	<!-- category -->
	<display:column property="brotherhood.title" titleKey="procession.brotherhood"
		sortable="true" />



	<!-- Display -->	
	<display:column titleKey="procession.display">
		<a href="procession/display.do?processionID=${row.id}"> <spring:message
				code="procession.display" /></a>
	</display:column>
	
	</jstl:if>
	
	<!-- Update -->
	<security:authorize access="hasRole('BROTHERHOOD')">
		<display:column titleKey="procession.update">
			<a href="procession/update.do?processionID=${row.id}"> <spring:message
					code="procession.update" /></a>
		</display:column>
	</security:authorize>
</display:table>