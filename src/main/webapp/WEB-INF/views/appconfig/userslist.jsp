<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Lista de usuarios</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link rel="shortcut icon" href="<c:url value='/static/img/BarcaLogoV.png' />"/>
</head>

<body>
	<div class="generic-container">
		<%@include file="authheader2.jsp"%>
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Lista de usuarios </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Nombres</th>
				        <th>Apellidos</th>
				        <th>Email</th>
				        <th>SSO ID</th>
				        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.ssoId}</td>
					    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<td><a href="<c:url value='/edit-user-${user.ssoId}' />" class="btn btn-success custom-width">Editar</a></td>
				        </sec:authorize>
				        <!-- 
				        <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">Borrar</a></td>
        				</sec:authorize>  -->
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		<a href="<c:url value='/newuser' />">Registrar nuevo usuario</a>
		 	</div>
		 	 <!-- <div class="well">
		 		<a href="<c:url value='/cotizador' />">Cotizador</a>
		 	</div>  -->
	 	</sec:authorize>
   	</div>
</body>
</html>