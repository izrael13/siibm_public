<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reporte paros máquina por día</title>
<script type="text/javascript">
function FBuscar()
{
	window.location.replace('<c:url value="/reportes/produccion/buscarparosmaqdia" />?aniomes='+document.getElementById("aniomes").value);
}
function Excel()
{
	window.location.replace('<c:url value="/reportes/produccion/Excel_golpes_maq_mes" />?aniomes='+document.getElementById("aniomes").value);
}
</script>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Reporte paros máquina por día</span>
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
    </div>
    </div>
	<br>
	<div id="tabla1" class = "table-responsive-xl container">
		<table class="table table-hover table-bordered text-center small">
			<tr>
				<td>Fecha</td>
				<td>Flexográfica</td>
				<td>Flexotroqueladora</td>
				<td>Troqueladora 1</td>
				<td>Troqueladora 2</td>
				<td>Troqueladora 3</td>
				<td>Suma Flexo</td>
				<td>Suma Troqueladoras</td>
			</tr>
			<c:forEach var="item" items="${reporte}">
			<tr>
				<td>${item.fecha_ini}</td>
				<td>${item.flexografica}</td>
				<td>${item.flexotroqueladora}</td>
				<td>${item.troqueladora1}</td>
				<td>${item.troqueladora2}</td>
				<td>${item.troqueladora3}</td>
				<td>${item.flexografica + item.flexotroqueladora}</td>
				<td>${item.troqueladora3 + item.troqueladora2 + item.troqueladora1}</td>
			</tr>
				<c:set var="flexoGraficaT" value="${flexoGraficaT = flexoGraficaT + item.flexografica}"/>
				<c:set var="flexotroqueladoraT" value="${flexotroqueladoraT = flexotroqueladoraT + item.flexotroqueladora}"/>
				<c:set var="troqueladora1T" value="${troqueladora1T = troqueladora1T + item.troqueladora1}"/>
				<c:set var="troqueladora2T" value="${troqueladora2T = troqueladora2T + item.troqueladora2}"/>
				<c:set var="troqueladora3T" value="${troqueladora3T = troqueladora3T + item.troqueladora3}"/>
				
				<c:set var="flexosT" value="${flexosT = flexosT + item.flexografica + item.flexotroqueladora}"/>
				<c:set var="TroqueT" value="${TroqueT = TroqueT + item.troqueladora3 + item.troqueladora2 + item.troqueladora1}"/>
				
			</c:forEach>
			<tr>
				<td>Total</td>
				<td>${flexoGraficaT}</td>
				<td>${flexotroqueladoraT}</td>
				<td>${troqueladora1T}</td>
				<td>${troqueladora2T}</td>
				<td>${troqueladora3T}</td>
				<td>${flexosT}</td>
				<td>${TroqueT}</td>
			</tr>
		</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>