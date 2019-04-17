<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
function FBuscar()
{
	window.location.replace('<c:url value="/reportes/ventas/buscarGKmes" />?aniomes='+document.getElementById("aniomes").value);
}
function Excel()
{
	window.location.replace('<c:url value="/reportes/ventas/Excel_GKmes" />?aniomes='+document.getElementById("aniomes").value);
}
</script>
<title>Golpes Kilos Máquina</title>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
<body>
	<div align="center">
		<span class="badge badge-secondary">Golpes Kilos Máquina</span>
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
        <div class="col-md-2"><button type="button" class="btn btn-outline-primary" Onclick="Excel()">
					<i class="fa fa-file-excel-o"></i>
					Descargar Excel
				</button></div>
        <div class="col-md-2"><button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla1')">
					<i class="fa fa-print"></i>
					Imprimir
				</button></div>
    </div>
    <br>
</div>
	
	<div id="tabla1" class = "table-responsive-xl container">
	<table class="table table-hover table-bordered text-center small">
		<thead>
			<tr>
				<th scope="col">Año</th>
				<th scope="col">Mes</th>
				<th scope="col">Dia</th>
				<th scope="col" colspan="2">Flexográfica</th>
				<th scope="col" colspan="2">Flexotroqueladora</th>
				<th scope="col" colspan="2">Troqueladoras</th>
				<th scope="col" colspan="2">Otros</th>
			</tr>
			<tr>
				<th scope="col" colspan="3"></th>
				<th scope="col">Kilos</th>
				<th scope="col">Golpes</th>
				<th scope="col">Kilos</th>
				<th scope="col">Golpes</th>
				<th scope="col">Kilos</th>
				<th scope="col">Golpes</th>
				<th scope="col">Kilos</th>
				<th scope="col">Golpes</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${reporte}">
				<tr>
					<th scope="row">${item.anio}</th>
					<th scope="row">${item.mes}</th>
					<th scope="row">${item.dia}</th>
	      			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.flexografica_kilos}" /></td>
	      			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.flexografica_golpes}" /></td>
	      			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.flexotroqueladora_kilos}" /></td>
	      			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.flexotroqueladora_golpes}" /></td>
	      			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.troqueladora_kilos}" /></td>
	      			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.troqueladora_golpes}" /></td>
	      			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.otros_kilos}" /></td>
	      			<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.otros_golpes}" /></td>
	      			
	      			<c:set var="flexoKT" value="${flexoKT = flexoKT + item.flexografica_kilos}"/>
	      			<c:set var="flexoGT" value="${flexoGT = flexoGT + item.flexografica_golpes}"/>
	      			<c:set var="flexotKT" value="${flexotKT = flexotKT + item.flexotroqueladora_kilos}"/>
	      			<c:set var="flexotGT" value="${flexotGT = flexotGT + item.flexotroqueladora_golpes}"/>
	      			<c:set var="TroqKT" value="${TroqKT = TroqKT + item.troqueladora_kilos}"/>
	      			<c:set var="TroqGT" value="${TroqGT = TroqGT + item.troqueladora_golpes}"/>
	      			<c:set var="OtrosKT" value="${OtrosKT = OtrosKT + item.otros_kilos}"/>
	      			<c:set var="OtrosGT" value="${OtrosGT = OtrosGT + item.otros_golpes}"/>
				</tr>
			</c:forEach>
			<tr>
				<th scope="row" colspan="3">Total</th>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${flexoKT}" /> </td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${flexoGT}" /> </td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${flexotKT}" /> </td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${flexotGT}" /> </td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${TroqKT}" /> </td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${TroqGT}" /> </td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${OtrosKT}" /> </td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${OtrosGT}" /> </td> 
			</tr>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>