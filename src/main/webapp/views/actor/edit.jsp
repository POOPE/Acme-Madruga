
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

<form:form action="actor/edit.do" modelAttribute="actorForm"
	id="regForm">
	<div class="box">
		<div>
			<b><spring:message code="actor.personalinfo" /></b>
		</div>
		<acme:textbox code="actor.firstname" path="firstName" />
		<acme:textbox code="actor.middlename" path="middleName" />
		<acme:textbox code="actor.lastname" path="lastName" />
		<acme:textbox code="actor.email" path="email" />
		<acme:textbox code="actor.address" path="address" />
		<acme:textbox code="actor.photo" path="photo" />
		<!--  phone number -->
		<form:hidden path="countryCode" id="hidden-cc" />
		<form:hidden path="areaCode" id="hidden-ac" />
		<form:hidden path="phoneNumber" id="hidden-pn" />
		<!-- /phone number -->
		<div>
			<spring:message code="actor.phonenumber" />
			<input type="text" placeholder="+XX (XX) XXXX" id="phoneinput" />
		</div>
	</div>
	<div>
		<acme:submit name="save" code="save" />
	</div>
</form:form>

<script type="text/javascript" src="scripts/phonenum.js"></script>