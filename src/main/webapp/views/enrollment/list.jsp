<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<display:table name="enrollments" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	
	<display:column titleKey="enrollment.member">
		<a href="enrollment/view.do?id=${row.enrollment.id}">
			<jstl:out value="${row.enrollment.member}"/>
		</a>
	</display:column>
	<display:column class="moment" titleKey="enrollment.moment">
		<fmt:formatDate  value="${row.moment}" />
	</display:column>
	<display:column property="ticker" titleKey="enrollment.ticker"/>
	<display:column>
		<jstl:if test="${!row.locked}">
			<a href="lorem/customer/edit.do?id=${row.id}">
					<spring:message code="lorem.edit"/>			</a>
		</jstl:if>
	</display:column>
	<display:column>
		<jstl:if test="${!row.locked}">
			<a href="enrollment/customer/delete.do?id=${row.id}">
			<spring:message code="enrollment.delete"/>	</a>
		</jstl:if>
	</display:column>

	
	
</display:table>