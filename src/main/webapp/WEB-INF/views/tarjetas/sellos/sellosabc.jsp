<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" />
<html>
<head>
<%@ include file="../../appconfig/authheader2.jsp" %>
<title>Sellos</title>
</head>
<body>
<br>
<div class = "container-fluid" align="center">
	<span class="badge badge-secondary">Sellos</span>
	<form:form method="POST" modelAttribute="sello" class="form-horizontal" autocomplete="off">
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
		<div class="row small border border-right">
			<div class="col-sm-3"></div>
			<div class="col-sm-2">Id:
				<form:input size="10" class="border border-secondary" type="text" readonly="true" path="id"/>
			</div>
			<div class="col-sm-2">Sello:
				<form:input size="10" class="border border-primary" maxlength="40" onkeypress="return SinCaracteresEspeciales(event)" type="text" path="sellos"/>
				<div class="has-error"><form:errors path="sellos" class="badge badge-danger small"/></div>
			</div>
			<div class="col-sm-1">
				<form:button class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button>
			</div>
		</div>
		</div>
	</form:form>
</div>
 <br>
 <c:if test="${mensaje != null}"><div class="alert alert-success" role="alert">${mensaje}</div></c:if>
 <c:if test="${error != null}"><div class="alert alert-danger" role="alert">${error}</div></c:if>
<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto small"><!-- mx-auto  para centrar en pantalla -->
	<thead>
		<tr>
		<th>Id</th>
		<th>Sellos</th>
		<th>Fecha captura</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${sellos}">
		<tr>
			<td>${item.id}</td>
			<td>${item.sellos}</td>
			<td>${item.fecha_insert}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>