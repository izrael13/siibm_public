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

<script>
/*$(document).ready(function() {
	$('#tablePag').DataTable();	
} ); */

function FBuscar()
{
	//alert( document.getElementById("SSelect").value );
	window.location.replace('<c:url value="/reportes/ingenieria/buscarTpoHerr" />?select='+document.getElementById("SSelect").value+'&herramental='+document.getElementById('THerramental').value);
}
function FExcel()
{
	window.location.replace('<c:url value="/reportes/ingenieria/excelamortherr" />?select='+document.getElementById("SSelect").value+'&herramental='+document.getElementById('THerramental').value);
}
</script>
<style type="text/css">
#tabla1{
	font-size: 11px;
	cellpadding: 2;
	cellspacing: 2;
}
</style>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Amortización</span>
	</div>
	<br>
	<div align="center" class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-2">
				<select id="SSelect">
					<option value="0" ${select eq 0 ? 'selected' : ''}>Todos</option>
					<option value="1" ${select eq 1 ? 'selected' : ''}>No Amortizados</option>
					<option value="2" ${select eq 2 ? 'selected' : ''}>Amortizados</option>
				</select>
			</div>
			<div class="col-md-2">Herramental</div>
			<div class="col-md-2"><input id="THerramental" value="${herramental}"/></div>
			<div class="col-md-2">
				<button type="button" class="btn btn-outline-primary" onClick="FBuscar()">
					<i class="fa fa-search"></i>
					Buscar
				</button>
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
				<th>Herramental</th>
				<th>Vendedor</th>
				<th>Cliente</th>
				<th>Grabados y Suajes Nombres</th>
				<th>Grabados y Suajes</th>
				<th>Fechas recepción</th>
				<th>Total Facturado</th>
				<th>Total Nota de Crédito</th>
				<th>Total(Facturado - NC)</th>
				<th>Total Herramental</th>				
				<th>Amortizado</th>
				<th>Porcentaje</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${reporte}">
			<tr>
				<td>${item.herramental}</td>
				<td>${item.vendedor}</td>
				<td>${item.clientes}</td>
				<td>${item.grab_suaj_nom}</td>
				<td>${item.grabados_suajes}</td>
				<td>${item.fecha_recepcion}</td>
				<td>${item.totalFacturado}</td>
				<td>${item.totalNotaCredito}</td>
				<td>${item.TOTAL}</td>
				<td>${item.totalHerramental}</td>
				<td>${item.AMORTIZADO}</td>
				<td>${item.PORCENTAJE}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>