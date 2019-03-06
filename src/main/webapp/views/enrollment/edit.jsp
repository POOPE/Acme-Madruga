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
<jstl:set var="df">
	<spring:message code="lorem.pattern" />
</jstl:set>
<div>
	<button type="button" name="back" onclick="history.back()">
		<spring:message code="back" />
	</button>
</div>
<form:form modelAttribute="lorem" action="lorem/customer/edit.do">
	<form:hidden path="id" />	
	<form:hidden path="version" />
	<form:hidden path="fixupTask" value="${lorem.fixupTask.id}"/>
	<form:hidden path="author" value="${lorem.author.id}"/>
	<form:hidden path="ticker" />
	<form:hidden path="publishDate" />
	<form:hidden path="locked" />
	
	<form:errors cssClass="error" path="id" />
	<form:errors cssClass="error" path="version" />
	<form:errors cssClass="error" path="fixupTask" />
	<form:errors cssClass="error" path="author" />
	<form:errors cssClass="error" path="ticker" />
	<form:errors cssClass="error" path="publishDate" />
	<form:errors cssClass="error" path="locked" />
	
	<div>
		<form:label path="body">
			<spring:message code="lorem.body" />
		</form:label>
		<form:input path="body" />
		<form:errors cssClass="error" path="body" />
	</div>
	<div>
		<form:label path="imgURL">
			<spring:message code="lorem.url" />
		</form:label>
		<form:input path="imgURL" />
		<form:errors cssClass="error" path="imgURL" />
	</div>
	<div>
		<input type="submit" name="save" value="<spring:message code="save"/>" />
	</div>
</form:form>