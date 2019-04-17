<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Consumo de papel - pedidos captados</title>
<script type="text/javascript">
function FBuscar()
{
	//alert(document.getElementById("aniomes").value);
	window.location.replace('<c:url value="/reportes/papel/buscarMes" />?aniomes='+document.getElementById("aniomes").value);
}
</script>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Consumo de papel - pedidos captados</span>
	</div>
	<br>
		
	<div align="center" class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-2">Año - Mes</div>
			<div class="col-md-2">
				<form:select id="aniomes" path="mesesanio" class="form-control input-sm" onchange="FBuscar()">
					<c:forEach var="i" items="${mesesanio}">
						<c:set var="anio_mes_">${i.anio}${i.mes}</c:set>
						<c:choose>
							<c:when
								test="${not empty selectedValue && selectedValue eq anio_mes_ }">
								<option value="${i.anio}${i.mes}" selected>${i.anio} - ${i.mes}</option>
							</c:when>
							<c:otherwise>
								<option value="${i.anio}${i.mes}">${i.anio} - ${i.mes} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla_ult_sem')">
					<i class="fa fa-print"></i>
					Imprimir
				</button>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
		
	<br>
	<div id="tabla_ult_sem" class = "table-responsive-xl container">
	<table class="table table-hover table-bordered text-center small">
		<thead>
		<tr>
			<th>Tipo papel</th>
			<th>Toneladas captadas mes anterior</th>
			<th>Toneladas captadas mes actual</th>
			<th>Promedio captado últimos 3 meses</th>
			<th>% Incremento o decremento de un mes a otro</th>
			<th>% Incremento o decremento en base al promedio trimestral</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${reporte}">
			<tr>
				<th>${item.tipo_papel}</th>
				<td>${item.peso_papel_mant}</td>
				<td>${item.peso_papel_mact}</td>
				<td>${item.prom_3_meses_ant}</td>
				<td class="${item.inc_dec_porc_mant < -10 ? 'alert alert-danger' : item.inc_dec_porc_mant > 10 ? 'alert alert-danger' :''}">${item.inc_dec_porc_mant}</td>
				<td class="${item.inc_dec_porc_3mant < -10 ? 'alert alert-danger' : item.inc_dec_porc_3mant > 10 ? 'alert alert-danger' :''}">${item.inc_dec_porc_3mant}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>