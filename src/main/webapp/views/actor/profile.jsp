
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<security:authorize access="hasRole('BROTHERHOOD')">
	<div>
		<jstl:if test="${photos != null}">
			<jstl:forEach items="${photos}" var="url">
				<div class="attachment" style="background-image:url('${url}')"></div>		
			</jstl:forEach>
		</jstl:if>
		<jstl:if test="${photos == null}">
			<span style="color:grey;"><spring:message code="profile.nodisplay"/></span>
		</jstl:if>
	</div>
	<div>
		<h3><jstl:out value="${actor.title}"/></h3>
		<span style="color:grey;">Est. <fmt:formatDate value="${actor.estDate}" pattern="dd/MM/yyyy" /></span>
	</div>
</security:authorize>
<div class="box">
	<div>
		<div class="attachment" style="background-image:url('${actor.photo}')" ></div>
		<b><jstl:out value="${actor.name}"/>&nbsp;<jstl:if test="${actor.middleName != null}"><jstl:out value="${actor.middleName}"></jstl:out>&nbsp;</jstl:if><jstl:out value="${actor.surname}"/></b>
	</div>
	<div>
		
	</div>
</div>
<div></div>

