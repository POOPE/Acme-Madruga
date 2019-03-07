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
	
		<security:authorize access="hasRole('BROTHERHOOD')">
		
		
		 
		<display:column property="member.name" titleKey="enrollment.member"/>
		 
		 
		<display:column titleKey="enrollment.edit">
			<a href="enrollment/edit.do?brodelID=${row.id}">
			 <spring:message code="enrollment.edit" /></a>
		</display:column>
		
		</security:authorize>	
		
		
		<spring:message code="enrollment.status" var="enrollstatus" />
		<display:column title="${enrollstatus}" >
			 <jstl:out value="${row.status}" />
			
		</display:column>
		<security:authorize access="hasRole('MEMBER')">
		
		<display:column property="broder.name" titleKey="enrollment.broder"/>
			
		
		</security:authorize>	
			
	
	<jstl:set var="uri" value="procession/myList.do"/>

	
</display:table>