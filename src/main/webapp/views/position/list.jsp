
<%--
 * login.jsp
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="positions" id="row"
		requestURI="${boxRequestURI}" pagesize="10" class="displaytag">
		<display:column titleKey="position.title" property="title"/>
		<display:column>
			<a href="position/admin/edit.do?id=${row.id}">
				<i
					class="fa fa-pencil" aria-hidden="true"></i>
			</a>
		</display:column>
		<display:column>
			<a href="position/admin/delete.do?id=${row.id}">
				<i
					class="fa fa-times" aria-hidden="true"></i>
			</a>
		</display:column>
</display:table>


<form:form action="position/admin/edit.do" modelAttribute="position">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<acme:textbox code="position.title" path="title" />
	<acme:submit name="save" code="save" />
</form:form>