<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cotizador b�squeda</title>
<%@include file="../../appconfig/imports.jsp"%>
<script type="text/javascript">
function FBuscarId(id,iddet)
{
	window.opener.FBuscarxId(id,iddet);
	window.close();
}
</script>
</head>
<body>
	<div id="tabla1" class = "table-responsive-xl container">
		<table class="table-hover table-bordered text-center small">
		<thead>
			<tr><th colspan="4">Seleccionar encabezado para agregar detalle</th></tr>
			<tr>
				<td>Folio</td>
				<td>Cliente</td>
				<td>Direcci�n</td>
				<td>Fecha alta</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<tr>
				<td><a href="javascript:FBuscarId(${item.id},0)">${item.id}</a></td>
				<td>${item.cardname}</td>
				<td>${item.direccion}</td>
				<td>${item.fecha_insert}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br>
	<table class="table-hover table-bordered text-center small">
		<thead>
			<tr><th colspan="4">Seleccionar Detalle</th></tr>
			<tr>
				<td>Folio</td>
				<td>Cliente</td>
				<td>Direcci�n</td>
				<td>Fecha alta</td>
				<td>S�mbolo</td>
				<td>Caja</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDet}">
			<tr>
				<td><a href="javascript:FBuscarId(${item.id},${item.iddet})">${item.id}</a></td>
				<td>${item.cardname}</td>
				<td>${item.direccion}</td>
				<td>${item.fecha_insert}</td>
				<td>${item.simbolo}</td>
				<td>${item.caja}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>