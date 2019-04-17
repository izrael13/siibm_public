<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Solicitud caducada</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2">
			</div>
			<div class="col-lg-8 col-md-8">
				<div id="mensajes" class="${not empty error ? 'alert alert-warning' : ''}">${error} </div>
			</div>
		</div>
	</div>
</body>
</html>