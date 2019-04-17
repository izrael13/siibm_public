<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Confirmación de registro</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link rel="shortcut icon" href="<c:url value='/static/img/BarcaLogoV.png' />"/>
</head>
<body>
	<div class="generic-container">
		<%@include file="authheader2.jsp"%>
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			<a href="<c:url value='/list' />">Lista de usuarios</a>
		</span>
	</div>
</body>

</html>