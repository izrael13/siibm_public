<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Viajes por ciudad</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script type="text/javascript">
function FBuscar()
{
	//alert(document.getElementById("aniomes").value);
	window.location.replace('<c:url value="/reportes/ventas/buscarporMes" />?aniomes='+document.getElementById("aniomes").value);
}
</script>
</head>
<body>
	<br>
		<div align="center">
			<span class="badge badge-secondary">Viajes por ciudad</span>
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
				<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla1')">
					<i class="fa fa-print"></i>
					Imprimir
				</button>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<br>
	<div id="tabla1" class = "table-responsive-xl container">
		<table class="table table-hover table-bordered text-center small">
			<thead>
				<tr>
					<th>Estado</th>
					<th>Ciudad</th>
					<th>Torton</th>
					<th>Trailer</th>
					<th>Sin Datos</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${reporte}">
				<tr>
					<td>${item.estado}</td>
					<td>${item.ciudad}</td>
					<td>${item.tortone}</td>
					<td>${item.trailere}</td>
					<td>${item.otrose}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>