<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="bfloat/edit.do" modelAttribute="brotherhoodFloat">
	
	<%-- Hidden properties from bFloat--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="floatPictures" />
	<form:hidden path="brotherhood" />
	
	<%-- description--%>
	<form:label path="description">
		<spring:message code="bFloat.description" />
	</form:label>
	<form:textarea path="description" />	
	<form:errors class="error" path="description" />
	<br><br>

	<%-- title --%>
	<form:label path="title">
		<spring:message code="bFloat.title" />
	</form:label>
	<form:input path="title" />	
	<form:errors class="error" path="title" />
	<br><br>
	
	<%-- Buttons --%>
	<security:authorize access="hasRole('BROTHERHOOD')">
		<input type="submit" name="save" 
			value="<spring:message code="bFloat.save"/>"/>
		
		<jstl:if test="${bFloat.id != 0}">	
		<input type="submit" name="delete" 
			value="<spring:message code="bFloat.delete"/>"/>
		</jstl:if>
			
		<input type="button" name="cancel"
			value="<spring:message code="bFloat.cancel" />"
			onClick="javascript: window.location.replace('bFloat/myList.do')" />
			
	</security:authorize>
	<br><br>
</form:form>