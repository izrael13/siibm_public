<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Imprimir/Asignar diseñador requerimientos</title>
<script>
function FTarjeta()
{

}
</script>
</head>
<body>
		<br>
	<div align="center">
		<span class="badge badge-secondary">Imprimir/Asignar diseñador requerimientos</span>
	</div>
	<br>
	<div align="center" class="container">
	<table class="table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>Folio</th>
				<th>Cliente</th>
				<th>Símbolo</th>
				<th>Caja</th>
				<th>%Comisión</th>
				<th>$ Objetivo</th>
				<th>$ sugerido</th>
				<th>$ neto</th>
				<th>Desc vendedor</th>
				<th>Comentarios</th>
				<th>Imprimir</th>
				<th>Convertir a Tarjeta</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDet}">
			<tr>
				<td>${item.id}</td>
				<td>${item.cardname}</td>
				<td>${item.simbolo}</td>
				<td>${item.nombrecorto}</td>
				<td>${item.comision_directo}</td>
				<td>${item.precio_objetivo}</td>
				<td>${item.precio_sugerido}</td>
				<td>${item.precio_neto}</td>
				<td>${item.descuento_vendedor}</td>
				<td><input id="TComent${item.id}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FImprimir(${item.id})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FTarjeta(${item.id})"><i class="fa fa-file-text-o" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>