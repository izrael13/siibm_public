<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Inventario Almacén</title>
<script type="text/javascript">
function FBuscar()
{
	window.location.replace('<c:url value="/reportes/vendedores/buscaInvenalm_" />?cve_almacen='+document.getElementById("almacen").value);
}
</script>
<style type="text/css">
#tabla1{
	font-size: 12.3px;
	cellpadding: 3;
	cellspacing: 3;
}
</style>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Inventario Almacén</span>
	</div>
	<br>
	<div align="center" class="container">
    <div class="row">
    	<div class="col-md-2"></div>
        <div class="col-md-2">Almacén</div>
        <div class="col-md-2">
        	<select id="almacen" class="form-control input-sm" onchange="FBuscar()">

						<option value="" ${selectedValue == '' ? 'selected' : ''}>- - -</option>
						<option value="PT-06" ${selectedValue == 'PT-06' ? 'selected' : ''}>PT-06</option>
						<option value="EM-14" ${selectedValue == 'EM-14' ? 'selected' : ''}>EM-14</option>	

			</select>
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
	<table class="table-hover table-bordered mx-auto"><!-- mx-auto  para centrar en pantalla -->
    	<thead>
    	<tr align="center">
    		<th>ItemCode</th>
    		<th>ItemName</th>
    		<th>BatchNum</th>
    		<th>Millar</th>
    		<th>Kilos</th>
    		<th>CardName</th>
    		<th>SlpName</th>
    		<th>Fecha</th>
    		<th>Fecha Entrega SAP</th>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="item" items="${reporte}">
    	<tr>
    		<td>${item.itemcode}</td>
    		<td>${item.itemname}</td>
    		<td align="right">${item.batchnum}</td>
    		<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.millar}" /></td>
    		<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.kilos}" /></td>
    		<td>${item.cardname}</td>
    		<td>${item.slpname}</td>
    		<td align="center">${fn:substring(item.fecha,0,10)}</td>
    		<td align="center">${fn:substring(item.fechaentregasap,0,10)}</td>
    		<c:set var="invTK" value="${invTK = invTK + item.kilos}"/>
    	</tr>
    	</c:forEach>
    	<tr>
    		<th colspan="4">Total Kilos</th>
    		<td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${invTK}" /></td>
    	</tr>
    	</tbody>
    </table>
    </div>
    <br>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>