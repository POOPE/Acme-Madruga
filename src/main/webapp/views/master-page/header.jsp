<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<div>
	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/Acme-Madruga" user="acme-user"
	password="ACME-Us3r-P@ssw0rd" />

<sql:query dataSource="${snapshot}" var="result">
         SELECT * from site_config limit 1;
      </sql:query>

<a href="#">
	<div class="banner" style="background-image:url('${result.rows[0].banner_url}')">

		<div style="color: white; position: absolute; bottom: 0;">
			<h3>
				<jstl:out value="${result.rows[0].site_name}" />
			</h3>
		</div>
	</div>
</a>
</div>

<div>
	<ul id="jMenu">		

		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="config/admin/edit.do"><spring:message code="master.page.admin.config" /></a></li>
					<li><a href="position/admin/list.do"><spring:message code="master.page.admin.position" /></a></li>
				</ul>
			</li>
		</security:authorize>		

		<security:authorize access="hasRole('MEMBER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="announcement/customer/list.do"><spring:message code="master.page.customer.announcements" /></a></li>										
					<li><a href="certification/customer/list.do"><spring:message code="master.page.customer.certifications" /></a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('BROTHERHOOD')">
			<li><a class="fNiv"><spring:message	code="master.page.reviewer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="announcement/reviewer/list-to-review.do"><spring:message code="master.page.customer.list-to-review" /></a></li>					
				</ul>
			</li>
			
				<li><a class="fNiv"><spring:message	code="master.page.brotherhood" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="bfloat/myList.do"><spring:message code="master.page.brotherhood.bfloat.mylist" /></a></li>					
					<li><a href="procession/myList.do"><spring:message code="master.page.brotherhood.procession.mylist" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="actor/register.do"><spring:message code="master.page.register" /></a></li>
			<li><a class="fNiv" href="procession/list.do"><spring:message code="master.page.procession" /></a></li>
			<li><a class="fNiv" href="bfloat/list.do"><spring:message code="master.page.bfloat" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/profile.do"><spring:message code="master.page.profile" /></a></li>
					<li><a href="user/password.do"><spring:message code="master.page.password" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.profile.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
		
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

