<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"/>
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Autorización de tarjetas Cliente</title>
</head>
<body>
	<br>	
	<div align="center">
		<span class="badge badge-secondary">Autorización de tarjetas Cliente</span>
	</div>
	<br>
	<div align="center" class="container-fluid">
	<table class="container-fluid table-hover text-center table-bordered small">
	<thead>
		<tr>
			<th>Folio</th>
			<th>Cotización</th>
			<th>Desc factura</th>
			<th>Obs Ingeniería</th>
			<th>Fecha aut Ingeniería</th>
			<th>Comentario</th>
			<th>Ver</th>
			<th>Autorizar</th>
			<th>Rechazar</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${tarjetas}">
		<tr>
			<td>${item.folio_tarjeta}</td>
			<td>${item.idcotizacion}</td>
			<td>${item.descripcion_factura}</td>
			<td>${item.observaciones_ing}</td>
			<td>${item.fecha_aut_ing}</td>
			<td><input id="TComent${item.folio_tarjeta}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" class="border border-primary"/></td>
			<td><a href="javascript:FImprimir(${item.folio_tarjeta})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
			<td><a href="javascript:FAut(${item.folio_tarjeta})"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></a></td>
			<td><a href="javascript:FReach(${item.folio_tarjeta})"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</div>
</body>
</html>