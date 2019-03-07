<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="bfloat/super/edit.do" modelAttribute="floatForm">

	<%-- Hidden properties from bFloat--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="owner" />

	<acme:textbox code="bFloat.title" path="title" />
	<acme:textbox code="bFloat.description" path="description" />

	<div>
		<spring:message code="bFloat.pictures" />
		<form:hidden path="photos" id="hidden-attachments" />
		<input type="text" id="attachment-input" />
		<button id="attachment-add">
			<spring:message code="add" />
		</button>
	</div>
	<div id="attachments"></div>
	<br>
	<%-- Buttons --%>
	<security:authorize access="hasRole('BROTHERHOOD')">
		<input type="submit" name="save"
			value="<spring:message code="bFloat.save"/>" />

		<jstl:if test="${floatForm.id != 0}">
			<a href="bfloat/super/delete.do?id=${floatForm.id}" class="button"><button type="button"><spring:message code="bFloat.delete"/></button> </a>
		</jstl:if>

		<input type="button" name="cancel"
			value="<spring:message code="bFloat.cancel" />"
			onClick="javascript: window.location.replace('bFloat/myList.do')" />

	</security:authorize>
	<br>
</form:form>

<script type="text/javascript" src="scripts/attachments.js"></script>