<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Detalle de viajes</title>
<%@include file="../appconfig/imports.jsp"%>
</head>
<body>
	<div id="tabla1" class = "table-responsive-xl container">
		<table class="table-hover table-bordered text-center small">
		<thead>
			<tr><th colspan="15">Viaje: ${num_viaje}</th></tr>
			<tr>
				<th>Pedido</th>
				<th>Remisión</th>
				<th>Descripcion</th>
				<th>Cliente</th>
				<th>Estado</th>
				<th>Ciudad</th>
				<th>Colonia</th>
				<th>Calle</th>
				<th>Chofer</th>
				<th>Kilos</th>
				<th>Cantidad</th>
				<th>Vehículo</th>
				<th>Factura</th>
				<th>Aut embarques</th>
				<th>Aut logística</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${detalle}">
			<tr>
				<td>${item.pedido}</td>
				<td>${item.remision}</td>
				<td>${item.descripcion}</td>
				<td>${item.cliente}</td>
				<td>${item.estado}</td>
				<td>${item.ciudad}</td>
				<td>${item.colonia}</td>
				<td>${item.calle}</td>
				<td>${item.u_chofer}</td>
				<td>${item.kilos}</td>
				<td>${item.cantidad}</td>
				<td>${item.u_vehiculo}</td>
				<td>${item.factura}</td>
				<td>${item.u_aut_ambarques}</td>
				<td>${item.u_aut_logistica}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<br>
	<div align="center">
		<a class="btn btn-outline-primary" href="javascript:window.close()">Cerrar</a>
		<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla1')">
			<i class="fa fa-print"></i>
			Imprimir
		</button>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>