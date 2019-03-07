<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="brotherhoodFloat" id="row" requestURI="bfloat/display.do" class="displaytag">

	<spring:message code="bFloat.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="false" />
	
	<spring:message code="bFloat.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	

</display:table>

<display:table name="brotherhood"  id="row" >

	<spring:message code="bFloat.brotherhood.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" href="brotherhood/display.do?broID=${brotherhood.id}" sortable="false" />
	
	
<display:caption><spring:message code="bFloat.brotherhood"/></display:caption>


</display:table>

<display:table name="floatPictures"  id="row" >

	<spring:message code="bFloat.floatPicture.url" var="urlHeader" />
	<display:column property="url" title="${urlHeader}" href="${floatPicture.url}" sortable="false" />
	
<display:caption><spring:message code="bFloat.floatPictures"/></display:caption>


</display:table>






