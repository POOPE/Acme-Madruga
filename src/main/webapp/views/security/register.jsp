
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

<form:form action="actor/register" modelAttribute="regForm" id="regForm">
	<div class="box">
		<div>
			<b><spring:message code="actor.account" /></b>
		</div>
		<acme:select code="actor.role" items="${roles}" path="role" itemsAsCodes="true"/>
		<acme:textbox code="actor.username" path="username" />
		<acme:password code="actor.password" path="password" id="password" tooltip="actor.password.tooltip"/>
		<div>
			<spring:message code="actor.confirmpass" />
			<input type="password" id="comparepass" />
			<i id="confirmpassstatus" class="fa" aria-hidden="true"></i>
		</div>
	</div>
	<div class="box">
		<div>
			<b><spring:message code="actor.personalinfo" /></b>
		</div>
		<acme:textbox code="actor.firstname" path="firstName" />
		<acme:textbox code="actor.middlename" path="middleName" />
		<acme:textbox code="actor.lastname" path="lastName" />
		<!--  phone number -->
		<form:hidden path="countryCode" id="hidden-cc"/>
		<form:hidden path="areaCode" id="hidden-ac"/>
		<form:hidden path="phoneNumber" id="hidden-pn"/>
		<!-- /phone number -->
		<input type="text" placeholder="+XX (XX) XXXX" id="phoneinput"/>
		<acme:textbox code="actor.address" path="address" />
	</div>
	<div>
		<acme:submit name="save" code="actor.register"/>
	</div>
</form:form>

<script type="text/javascript" src="scripts/password.js"></script>
<script type="text/javascript" src="scripts/phonenum.js"></script>