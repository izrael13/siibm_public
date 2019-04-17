<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function FBuscar()
{
	window.location.replace('<c:url value="/reportes/produccion/buscartroqmes" />?aniomes='+document.getElementById("aniomes").value);
}
function Excel()
{
	window.location.replace('<c:url value="/reportes/produccion/Excel_golpes_maq_mes" />?aniomes='+document.getElementById("aniomes").value);
}
</script>

<style type="text/css">
#tabla1{
	font-size: 12.3px;
	cellpadding: 3;
	cellspacing: 3;
}
</style>
<title>Reporte golpes por máquina</title>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Reporte golpes por máquina</span>
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
        	<button type="button" class="btn btn-outline-primary" Onclick="Excel()">
					<i class="fa fa-file-excel-o"></i>
					Descargar Excel
				</button>
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
	<div id="tabla1" class = "table-responsive-xl">
	<table class="table-hover table-bordered text-center mx-auto"><!-- mx-auto  para centrar en pantalla -->
		<tr>
			<th>Fecha</th>
			<th colspan="3">Flexográfica</th>
			<th colspan="3">Flexotroqueladora</th>
			<th colspan="3">Troqueladora 1</th>
			<th colspan="3">Troqueladora 2</th>
			<th colspan="3">Troqueladora 3</th>
			<th colspan="3">Suma Flexo</th>
			<th colspan="3">Suma Troqueladoras</th>
		</tr>
		<tr>
			<th>aammdd</th>
			<th>Golpes</th>
			<th>Piezas</th>
			<th>Kilos</th>
			<th>Golpes</th>
			<th>Piezas</th>
			<th>Kilos</th>
			<th>Golpes</th>
			<th>Piezas</th>
			<th>Kilos</th>
			<th>Golpes</th>
			<th>Piezas</th>
			<th>Kilos</th>
			<th>Golpes</th>
			<th>Piezas</th>
			<th>Kilos</th>
			<th>Golpes</th>
			<th>Piezas</th>
			<th>Kilos</th>
			<th>Golpes</th>
			<th>Piezas</th>
			<th>Kilos</th>
		</tr>
		<c:forEach var="item" items="${reporte}">
			<tr>
				<td>${fn:substring(item.oprunstartdatetime, 2, 10)}</td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexograficagp}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexograficapz}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexograficakl}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexotroqueladoragp}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexotroqueladorapz}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexotroqueladorakl}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora1gp}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora1pz}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora1kl}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora2gp}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora2pz}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora2kl}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora3gp}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora3pz}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora3kl}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexograficagp + item.flexotroqueladoragp}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexograficapz + item.flexotroqueladorapz}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.flexograficakl + item.flexotroqueladorakl}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora3gp + item.troqueladora2gp + item.troqueladora1gp}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora3pz + item.troqueladora2pz + item.troqueladora1pz}" /></td>
				<td><fmt:formatNumber type = "number" maxFractionDigits = "2" pattern = "###,###,###,###,###.##" value = "${item.troqueladora3kl + item.troqueladora2kl + item.troqueladora1kl}" /></td>
			</tr>
				<c:set var="flexoGraficaT" value="${flexoGraficaT = flexoGraficaT + item.flexograficagp}"/>
				<c:set var="flexotroqueladoraT" value="${flexotroqueladoraT = flexotroqueladoraT + item.flexotroqueladoragp}"/>
				<c:set var="troqueladora1T" value="${troqueladora1T = troqueladora1T + item.troqueladora1gp}"/>
				<c:set var="troqueladora2T" value="${troqueladora2T = troqueladora2T + item.troqueladora2gp}"/>
				<c:set var="troqueladora3T" value="${troqueladora3T = troqueladora3T + item.troqueladora3gp}"/>
				
				<c:set var="flexosT" value="${flexosT = flexosT + item.flexograficagp + item.flexotroqueladoragp}"/>
				<c:set var="TroqueT" value="${TroqueT = TroqueT + item.troqueladora3gp + item.troqueladora2gp + item.troqueladora1gp}"/>
				<!--  -->
				<c:set var="flexoGraficaTpz" value="${flexoGraficaTpz = flexoGraficaTpz + item.flexograficapz}"/>
				<c:set var="flexotroqueladoraTpz" value="${flexotroqueladoraTpz = flexotroqueladoraTpz + item.flexotroqueladorapz}"/>
				<c:set var="troqueladora1Tpz" value="${troqueladora1Tpz = troqueladora1Tpz + item.troqueladora1pz}"/>
				<c:set var="troqueladora2Tpz" value="${troqueladora2Tpz = troqueladora2Tpz + item.troqueladora2pz}"/>
				<c:set var="troqueladora3Tpz" value="${troqueladora3Tpz = troqueladora3Tpz + item.troqueladora3pz}"/>
				
				<c:set var="flexosTpz" value="${flexosTpz = flexosTpz + item.flexograficapz + item.flexotroqueladorapz}"/>
				<c:set var="TroqueTpz" value="${TroqueTpz = TroqueTpz + item.troqueladora3pz + item.troqueladora2pz + item.troqueladora1pz}"/>
				
				<!--  -->
				<c:set var="flexoGraficaTkl" value="${flexoGraficaTkl = flexoGraficaTkl + item.flexograficakl}"/>
				<c:set var="flexotroqueladoraTkl" value="${flexotroqueladoraTkl = flexotroqueladoraTkl + item.flexotroqueladorakl}"/>
				<c:set var="troqueladora1Tkl" value="${troqueladora1Tkl = troqueladora1Tkl + item.troqueladora1kl}"/>
				<c:set var="troqueladora2Tkl" value="${troqueladora2Tkl = troqueladora2Tkl + item.troqueladora2kl}"/>
				<c:set var="troqueladora3Tkl" value="${troqueladora3Tkl = troqueladora3Tkl + item.troqueladora3kl}"/>
				
				<c:set var="flexosTkl" value="${flexosTkl = flexosTkl + item.flexograficakl + item.flexotroqueladorakl}"/>
				<c:set var="TroqueTkl" value="${TroqueTkl = TroqueTkl + item.troqueladora3kl + item.troqueladora2kl + item.troqueladora1kl}"/>
				
		</c:forEach>
		<tr>
			<th>Total</th>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexoGraficaT}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexoGraficaTpz}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexoGraficaTkl}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexotroqueladoraT}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexotroqueladoraTpz}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexotroqueladoraTkl}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora1T}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora1Tpz}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora1Tkl}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora2T}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora2Tpz}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora2Tkl}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora3T}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora3Tpz}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${troqueladora3Tkl}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexosT}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexosTpz}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${flexosTkl}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${TroqueT}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${TroqueTpz}" /></td>
			<td><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${TroqueTkl}" /></td>
		</tr>
	</table>
	</div>
	<br>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>