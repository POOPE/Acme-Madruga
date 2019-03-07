<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="procession/super/edit.do" modelAttribute="procession">

	<%-- Hidden properties from procession--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="bFloats"/>

	<acme:textbox code="procession.title" path="title" />
	<acme:textbox code="procession.description" path="description" />
	<acme:textbox code="procession.moment" path="moment" placeholder="dd/mm/yyyy"/>

<br>
	<%-- Buttons --%>
	<security:authorize access="hasRole('BROTHERHOOD')">
		<input type="submit" name="save"
			value="<spring:message code="procession.save"/>" />

		<jstl:if test="${procession.id != 0}">
			<a href="procession/super/delete.do?id=${procession.id}" class="button"><button type="button"><spring:message code="procession.delete"/></button> </a>
		</jstl:if>

		<input type="button" name="cancel"
			value="<spring:message code="procession.cancel" />"
			onClick="javascript: window.location.replace('procession/myList.do')" />

	</security:authorize>
	
	<br>
</form:form>