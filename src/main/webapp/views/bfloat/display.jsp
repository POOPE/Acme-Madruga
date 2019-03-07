<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<div>
	<h3>
		<jstl:out value="${brotherhoodFloat.title}"/>
	</h3>
</div>
<div>
	<spring:message code="bFloat.organize" />
	&nbsp;<a href="actor/profile.do?id=${brotherhoodFloat.owner.id}"><jstl:out
			value="${brotherhoodFloat.owner.title}" /></a>
</div>

<div>
	<jstl:if test="${floatPictures != null}">
		<div class="inline-outer">
			<jstl:forEach items="${floatPictures}" var="attachment">
				<div class="inline attachment-150"
					style="background-image:url('${attachment.url}')"></div>
			</jstl:forEach>
		</div>
	</jstl:if>
	<jstl:if test="${empty floatPictures}">
		<span style="color: grey;"><spring:message
				code="profile.nodisplay" /></span>
	</jstl:if>
</div>






