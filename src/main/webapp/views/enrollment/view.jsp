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
<br/>
<div>
	<img src="${lorem.imgURL}" />
</div>
<div>
	<h3>
		<jstl:out value="${lorem.ticker}" />
	</h3>
	<span style="color:gray;"><fmt:formatDate  value="${lorem.publishDate}" pattern="${df}" /></span>
</div>
<br/>
<div>
	<a href="fixuptask/view.do?id=${lorem.fixupTask.id}"><jstl:out value="${lorem.fixupTask.ticker}" /></a> <br /> <span>By:&nbsp;<jstl:out
			value="${lorem.author.name} ${lorem.author.surname}" /></span>
</div>
<br/>
<div>
	<jstl:out value="${lorem.body}"/>
</div>