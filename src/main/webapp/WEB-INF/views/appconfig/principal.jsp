<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SIIBM</title>
<%@include file="authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Main</span>
	</div>
	 <br>
	<div align="center">
		<img class="img-fluid" alt="Responsive image" src="<c:url value='/static/img/BarcaLogoV.png'/>"align="top">
		<img class="img-fluid" alt="Responsive image" src="<c:url value='/static/img/Barca.png' />" align="top">
	</div>
	<br>
	<%@include file="authfootter.jsp"%>
</body>
</html>