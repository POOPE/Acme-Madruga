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
<form:form action="enrollment/edit.do" modelAttribute="enrollment">
	
	<%-- Hidden properties from procession--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="brotherhood" />
	<form:hidden path="member"/>
	<form:hidden path="moment"/>
	
	<%-- status--%>
	<form:label path="status">
		<spring:message code="enrollment.status" />
	</form:label>
	<form:textarea path="status" />	
	<form:errors class="error" path="status" />
	<br><br>
	
	<%-- position--%>
	<form:label path="position">
		<spring:message code="enrollment.position" />
	</form:label>
	<form:textarea path="position" />
	<form:errors class="error" path="position" />
	<br><br>

	
	<%-- Buttons --%>
	<security:authorize access="hasRole('BROTHERHOOD')">
		<input type="submit" name="save" 
			value="<spring:message code="enrollment.save"/>"/>
		
		<jstl:if test="${enrollment.id != 0}">	
		<input type="submit" name="delete" 
			value="<spring:message code="enrollment.delete"/>"/>
		</jstl:if>
			
		<input type="button" name="cancel"
			value="<spring:message code="enrollment.cancel" />"
			onClick="javascript: window.location.replace('enrollment/brotherhood/list.do')" />
			
	</security:authorize>
	<br><br>
</form:form>