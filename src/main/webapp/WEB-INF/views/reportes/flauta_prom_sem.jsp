<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Velocidad promedio semana corrugadora</title>
<script type="text/javascript">
function FBuscar()
{
	//alert('/buscarSem?aniosem='+document.getElementById("aniosem").value);
	window.location.replace('<c:url value="/reportes/produccion/buscarFlautaPromSem" />?aniosem='+document.getElementById("aniosem").value);
	//document.location = '/buscarSem?aniosem='+document.getElementById("aniosem").value;
}
function FExcel()
{
	//window.location.href = '<c:url value="/Excel" />?aniosem='+document.getElementById("aniosem").value;
}
</script>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Velocidad promedio semana corrugadora</span>
	</div>
		<br>
	<div align="center" class="container">
    <div class="row">
    	<div class="col-md-2"></div>
        <div class="col-md-2">Año - Semana</div>
        <div class="col-md-2">
        	<form:select id="aniosem" path="semanas_anio" class="form-control input-sm" onchange="FBuscar()">
				<c:forEach var="i" items="${semanas_anio}">
						<c:choose>
							<c:when
								test="${not empty selectedValue && selectedValue eq i.key}">
								<option value="${i.key}" selected>${i.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${i.key}">${i.value}</option>
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
    </div>
    </div>
	<br>
	<div id="tabla1" class = "table-responsive-xl container">
	<table class="table table-hover table-bordered text-center small">
		<thead>
			<tr>
				<th>Año</th>
				<th>Semana</th>
				<th>Flauta</th>
				<th>Cantidad</th>
				<th>Promedio</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${reporte}">
			<tr>
				<th>${item.anio}</th>
				<th>${item.semana}</th>
				<td>${item.flauta}</td>
				<td>${item.cantidad}</td>
				<td>${item.promedio}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>