<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Amortización</title>
<%@include file="../appconfig/authheader2.jsp"%>
<style type="text/css">
#tabla1{
	font-size: 11px;
	cellpadding: 2;
	cellspacing: 2;
}
</style>
<script>
$(document).ready(function() {
	$('#tablePag').DataTable();
} );

function FBuscar()
{
	//alert( document.getElementById("SSelect").value );
	window.location.replace('<c:url value="/reportes/ingenieria/buscarTpoHerr" />?select='+document.getElementById("SSelect").value);
}
function FExcel()
{
	window.location.replace('<c:url value="/reportes/ingenieria/excelamortherr" />?select='+document.getElementById("SSelect").value);
}
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Amortización</span>
	</div>
	<br>
	<div align="center" class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2">
				<select id="SSelect" onChange="FBuscar()">
					<option value="0" ${select eq 0 ? 'selected' : ''}>Todos</option>
					<option value="1" ${select eq 1 ? 'selected' : ''}>Amortizados</option>
					<option value="2" ${select eq 2 ? 'selected' : ''}>No Amortizados</option>
				</select>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-outline-primary" onClick="FExcel()">
					<i class="fa fa-file-excel-o"></i>
					Descargar Excel
				</button>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<br>
	<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto"><!-- mx-auto  para centrar en pantalla -->
		<thead>
			<tr>
				<th>Tarjeta</th>
				<th>Vendedor</th>
				<th>Cliente</th>
				<th>Descripción</th>
				<th>Herramental</th>
				<th>Fecha Recepción</th>
				<th>Total Pedidos</th>
				<th>Facturado</th>
				<th>Costo Herramental</th>
				<th>%Herramental</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${reporte}">
			<tr>
				<td>${item.u_tf}</td>
				<td>${item.vendedor}</td>
				<td>${item.cliente}</td>
				<td>${item.descripcion}</td>
				<td>${item.herramental}</td>
				<td>${fn:substring(item.fecha_recep, 0, 10)}</td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.total_pedidos}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.facturado}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.linetotal}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.porcherra}" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>