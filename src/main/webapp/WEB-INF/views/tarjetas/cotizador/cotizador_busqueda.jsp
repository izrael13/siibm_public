<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cotizador búsqueda</title>
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
	<div class = "container-fluid">
		<table class="container-fluid table-hover table-bordered text-center small">
		<thead>
			<tr><th colspan="5">Seleccionar encabezado para agregar detalle</th></tr>
			<tr>
				<td>Folio</td>
				<td>Cliente</td>
				<td>Fecha alta</td>
				<td>Detalle</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}" varStatus="counter">
		<fmt:parseNumber var = "id" integerOnly = "true" type = "number" value = "${item['id']}" />
			<tr>
				<td><a href="javascript:FBuscarId(${id},0)">${id}</a></td>
				<td>${item['cliente']}</td>
				<td>${item['fecha_insert']}</td>
				<td>
					<table class="table-hover table-bordered text-center container-fluid">
						<thead>
							<tr>
								<td>F Det</td>
								<td>Símbolo</td>
								<td>Caja</td>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="itemd" items="${item['ListaDetalles']}" varStatus="counter">
						<fmt:parseNumber var = "idcot" integerOnly = "true" type = "number" value = "${itemd['iddetalle']}" />
							<tr>
								<td><a href="javascript:FBuscarId(${id},${idcot})">${idcot}</a></td>
								<td>${itemd['simbolo']}</td>
								<td>${itemd['estilo_caja']}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>