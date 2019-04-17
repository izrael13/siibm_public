<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Historial de viajes</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function Detalle(nviaje)
{
	popupwindow('<c:url value="/viajes/detalle" />?num_viaje='+nviaje,'Detalle de viaje',800,800);
	//alert(nviaje);
	//window.open('<c:url value="/viajes/detalle" />?num_viaje='+nviaje,null,
	//"height=800,width=1000,status=yes,toolbar=no,menubar=no,location=no");
}
$(document).ready(function() {
$('#tablePag').DataTable();
} ); 
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Historial de viajes</span>
	</div>
	<br>
	<div align="center" id="tabla1" class = "table-responsive-xl container">
		<table id="tablePag" class="table-hover table-bordered text-center small">
		<thead>
			<tr>
				<th>Viaje</th>
				<th>Fecha registro</th>
				<th>Kilos</th>
				<th>Repartos</th>
				<th>Cambios de estado</th>
				<th>Costo</th>
				<th>Demoras</th>
				<th>Devoluciones</th>
				<th>Aut Embarques</th>
				<th>Aut Logística</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${viajes}">
			<tr>
				<td><a href="javascript:Detalle(${item.u_num_viaje})">${item.u_num_viaje}</a></td>
				<td>${item.u_fecha_reg}</td>
				<td>${item.u_kilos}</td>
				<td>${item.u_repartos}</td>
				<td>${item.u_cambio_edo}</td>
				<td>${item.u_costo}</td>
				<td>${item.u_demoras}</td>
				<td>${item.u_devoluciones}</td>
				<td>${item.u_aut_ambarques}</td>
				<td>${item.u_aut_logistica}</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>