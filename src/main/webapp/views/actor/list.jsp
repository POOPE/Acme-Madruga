<%--
 * list.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="actors" requestURI="${URI}" id="row">

	<!-- Attributes -->
	<jstl:if test="${brother}">
		<spring:message code="actor.name" var="acname" />
		<display:column title="${acname}" sortable="true">
			<a href="actor/profile.do?id=${row.id}"> <jstl:out value="${row.title}" />
			</a>
		</display:column>
		
		
		
	</jstl:if>
	<jstl:if test="${empty brother}">
		<display:column titleKey="actor.firstname" sortable="true">
			<a href="actor/profile.do?id=${row.id}"> <jstl:out value="${row.name}" />&nbsp;<jstl:out
					value="${row.surname}" />
			</a>
		</display:column>

		<spring:message code="actor.email" var="emailHeader" />
		<display:column property="email" title="${emailHeader}"
			sortable="true" />

		<spring:message code="actor.phone" var="phoneHeader" />
		<display:column property="phone" title="${phoneHeader}"
			sortable="true" />

		<spring:message code="actor.address" var="addressHeader" />
		<display:column property="address" title="${addressHeader}"
			sortable="false" />

		<display:column titleKey="actor.display">
			<a href="actor/profile.do?id=${row.id}"> <spring:message
					code="procession.display" /></a>
		</display:column>
	</jstl:if>
</display:table>

