<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="procession/edit.do" modelAttribute="procession">
	
	<%-- Hidden properties from procession--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="brotherhood" />
	<form:hidden path="ticker"/>

	
	<%-- description--%>
	<form:label path="description">
		<spring:message code="procession.description" />
	</form:label>
	<form:textarea path="description" />	
	<form:errors class="error" path="description" />
	<br><br>

	<%-- title --%>
	<form:label path="title">
		<spring:message code="procession.title" />
	</form:label>
	<form:input path="title" />	
	<form:errors class="error" path="title" />
	<br><br>
	
	<%-- moment --%>
	<form:label path="moment">
		<spring:message code="procession.moment" />
	</form:label>
	<form:input path="moment" placeholder="dd/mm/yyyy" format="{0,date,dd/MM/yyyy HH:mm}" />	
	<form:errors class="error" path="moment" />
	<br><br>
	
	<%-- Buttons --%>
	<security:authorize access="hasRole('BROTHERHOOD')">
		<input type="submit" name="save" 
			value="<spring:message code="procession.save"/>"/>
		
		<jstl:if test="${procession.id != 0}">	
		<input type="submit" name="delete" 
			value="<spring:message code="procession.delete"/>"/>
		</jstl:if>
			
		<input type="button" name="cancel"
			value="<spring:message code="procession.cancel" />"
			onClick="javascript: window.location.replace('procession/myList.do')" />
			
	</security:authorize>
	<br><br>
</form:form>