<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="procession" id="row" requestURI="procession/display.do" class="displaytag">

	<spring:message code="procession.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" sortable="false" />

 	<spring:message code="procession.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="false" 
					format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="procession.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="false" />

	<spring:message code="procession.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false"/>
	
</display:table>

<display:table name="brotherhood"  id="row" >

	<spring:message code="procession.brotherhood.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" href="brotherhood/display.do?brotherhoodID=${brotherhood.id}" sortable="false" />

<display:caption><spring:message code="procession.brotherhood"/></display:caption>
</display:table>




