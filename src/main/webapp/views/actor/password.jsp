<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form id="passform" action="user/password.do" modelAttribute="user" onSubmit="return validateForm(this)">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="authorities" />

	<form:hidden path="username" />
	<form:hidden id="passval" path="password" />
	<div>
		<spring:message code="user.oldpass" />
		<input type="password" name="oldpass"><i id="oldpassstatus"
			class="fa " aria-hidden="true"></i>
	</div>
	<div>
		<spring:message code="user.newpass" />
		<input type="password" name="newpass">
	</div>
	<div>
		<spring:message code="user.confirmpass" />
		<input type="password" name="confirmpass"><i id="newpassstatus"
			class="fa " aria-hidden="true">
	</div>

	<div>

		<input type="submit" value="<spring:message code="actor.save" />"
			name="save"> <a href="welcome/index.do"><spring:message
				code="actor.cancel" /></a>
	</div>
</form:form>

<script type="text/javascript" src="scripts/changepassword.js"></script>