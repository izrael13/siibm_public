<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Golpes pendientes de fabricar</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscar()
{
	window.location.replace('<c:url value="/buscargplpenfab2" />?aniomes='+document.getElementById("aniomes").value);
}
function FExcel()
{
	window.location.replace('<c:url value="/Excel_golpesPend2" />?aniomes='+document.getElementById("aniomes").value);
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
		<span class="badge badge-secondary">Golpes pendientes de fabricar</span>
	</div>
		<br>
	<div align="center" class="container rounded border border-info">
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
        	<button type="button" class="btn btn-outline-primary" Onclick="FExcel()">
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
    	<thead>
    		<tr>
    			<th class="border border-secondary" rowspan="3">Fecha</th>
    			<th class="border border-primary" colspan="6">Fabricados</th>
    			<th class="border border-success" colspan="6">Entregados</th>
    			<th class="border border-danger" colspan="3" rowspan="2">Total GOLPES pendientes</th>
    			<th class="border border-danger" colspan="2" rowspan="2">Capacidad</th>
    			<th class="border border-danger" colspan="3" rowspan="2">Total KILOS pendientes</th>
    		</tr>
    		<tr>
    			<th class="border border-primary" colspan="3">Golpes</th>
    			<th class="border border-primary" colspan="3">Kilos</th>
    			<th class="border border-success" colspan="3">Golpes</th>
    			<th class="border border-success" colspan="3">Kilos</th>
    		</tr>
    		<tr>
    			<th class="border border-primary">Flexos</th>
    			<th class="border border-primary">Troq</th>
    			<th class="border border-primary">Otros</th>
    			<th class="border border-primary">Flexos</th>
    			<th class="border border-primary">Troq</th>
    			<th class="border border-primary">Otros</th>
    			
    			<th class="border border-success">Flexos</th>
    			<th class="border border-success">Troq</th>
    			<th class="border border-success">Otros</th>
    			<th class="border border-success">Flexos</th>
    			<th class="border border-success">Troq</th>
    			<th class="border border-success">Otros</th>
    			
    			<th class="border border-danger">Flexos</th>
    			<th class="border border-danger">Troq</th>
    			<th class="border border-danger">Otros</th>
    			<th class="border border-primary">Flexos</th>
    			<th class="border border-success">Troq</th>
    			
    			<th class="border border-primary">Fabric.</th>
    			<th class="border border-success">Entrega.</th>
    			<th class="border border-danger">Pendient</th>
    			
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="item" items="${reporte}">
    		<tr>
    			<td class="border border-secondary">${fn:substring(item.fecha, 0, 10)}</td>
    			<td class="border border-primary"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.flexosgp}" /></td>
    			<td class="border border-primary"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.troqgp}" /></td>
    			<td class="border border-primary"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.otrosgp}" /></td>
    			<td class="border border-primary"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.flexoskl}" /></td>
    			<td class="border border-primary"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.troqkl}" /></td>
    			<td class="border border-primary"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.otroskl}" /></td>
    			
    			<td class="border border-success"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.flexosgpalm}" /></td>
    			<td class="border border-success"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.troqgpalm}" /></td>
    			<td class="border border-success"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.otrosgpalm}" /></td>
    			<td class="border border-success"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.flexosklalm}" /></td>
    			<td class="border border-success"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.troqklalm}" /></td>
    			<td class="border border-success"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.otrosklalm}" /></td> 
    			
    			<td class="border border-danger"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.gppenflx}" /></td>
    			<td class="border border-danger"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.gppentroq}" /></td>
    			<td class="border border-danger"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.gppenotros}" /></td>
    			<td class="border border-primary"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.cap_flexos}" /></td>
    			<td class="border border-success"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.cap_troq}" /></td>
    			
    			<td class="border border-primary"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.kilos_tkf}" /></td>
    			<td class="border border-success"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.kilos_tke}" /></td>
    			<td class="border border-danger"><fmt:formatNumber type = "number" pattern = "###,###,###,###,###.##" maxFractionDigits = "2" value = "${item.kilos_pend}" /></td>
    		</tr>
    		</c:forEach>
    	</tbody>
    </table>
    </div>
    <br>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>