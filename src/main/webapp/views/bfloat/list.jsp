<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="permitAll">
	<jstl:set var="uri" value="bfloat/list.do" />
</security:authorize>
<security:authorize access="hasRole('BROTHERHOOD')">
	<jstl:set var="uri" value="bfloat/myList.do" />
</security:authorize>
<display:table name="brotherhoodFloats" id="row" requestURI="${uri}"
	pagesize="5" class="displaytag">

	<!-- title -->
	<spring:message code="bFloat.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />

	<!-- description -->
	<spring:message code="bFloat.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="true" />

	<!-- Display -->
	<spring:message code="bFloat.display" var="displayHeader" />
	<display:column title="${displayHeader}">
		<a href="bfloat/display.do?bFloatID=${row.id}"> <spring:message
				code="bFloat.display" /></a>
	</display:column>


	<!-- Update -->
	<display:column>
		<security:authorize access="isAuthenticated()">
			<jstl:set var="user">
				<security:authentication property="principal.username" />
			</jstl:set>
			<jstl:if test="${row.owner.userAccount.username==user}">

				<spring:message code="bFloat.update" var="updateHeader" />

				<a href="bfloat/super/update.do?bFloatID=${row.id}"> <spring:message
						code="bFloat.update" /></a>


			</jstl:if>
		</security:authorize>
	</display:column>

</display:table>