<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Detalle de cobranza</title>
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
	$('#tablePag').DataTable(
			{
				"searching": true,
				"paging": true //,
				//"dom": 'lrtip',
				//"lengthMenu": [[10, 25, 50, 100, 200, -1], [10, 25, 50, 100, 200, "Todo"]]
			}
		);
} );
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Detalle de cobranza</span>
	</div>
	<br>
	<div align="center" id="tabla1" class = "table-responsive-xl">
	<table class="table-hover table-bordered text-center mx-auto">
		<thead>
			<tr>
				<th>Intervalo</th>
				<th>Importe</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="val" items="${acumulado}">
			<tr>
				<td>${val.intervalo}</td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${val.importe}" /></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto">
		<thead>
			<tr>
				<th>Id</th>
				<th>Cliente</th>
				<th>Vendedor</th>
				<th>Fecha</th>
				<th>Factura</th>
				<th>DC</th>
				<th>Retención</th>
				<th>Fecha vencimiento</th>
				<th>Vencido</th>
				<th>Semana 1</th>
				<th>Semana 2</th>
				<th>Semana 3</th>
				<th>Semana 4</th>
				<th>Por vencer</th>
				<th>Total gral</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${reporte}">
			<tr class ="${ item.cte == 'TODOS' ? 'alert alert-success font-weight-bold' : item.fecha == 'TOTAL' ? 'alert alert-primary font-weight-bold' : ''}">
				<td>${item.id}</td>
				<td>${item.cte}</td>
				<td>${item.ven}</td>
				<td>${fn:substring(item.fecha, 0, 10)}</td>
				<td>${item.factura}</td>
				<td>${item.dc}</td>
				<td>${item.retencion}</td>
				<td>${fn:substring(item.fechaven, 0, 10)}</td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.vencido}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.semana1}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.semana2}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.semana3}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.semana4}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.porvencer}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.totalgral}" /></td>
			</tr>
			</c:forEach>
		</tbody>
	</table> 
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>