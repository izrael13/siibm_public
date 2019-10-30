<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reporte de Pedidos Con Retraso</title>
<script type="text/javascript">

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
		<span class="badge badge-secondary">Reporte de Pedidos Con Retraso</span>
	</div>
		<br>
	
        <div class="col-md-2">
        	<button type="button" class="btn btn-outline-primary" onClick="Imprimir('Pedidos_con_retraso')"><i class="fa fa-print"></i>Imprimir</button>
        </div>
	<br>
	<div id="Pedidos_con_retraso" class = "table-responsive-xl container">
	<table class="table table-hover table-bordered text-center small">
	<thead>
			<tr>
				<th>PEDIDO</th>
				<th>CLIENTE</th>
				<th>SÍMBOLO</th>
				<th>FECHA DE ENTREGA</th>
				<th>KILOS</th>
				<th>GOLPES</th>
				<th>MÁQUINA</th>
			</tr>
		</thead>
		<tbody>
	  <tr>
			
 		<c:forEach var="item" items="${pedidosAtra}">
			<tr>
				<td>${item.id}</td>
				<td>${item.cliente}</td>
				<td>${item.simbolo}</td>
				<td>${item.fecha}</td>
				<td>${item.kilos}</td>
				<td>${item.golpes}</td>
				<td>${item.maquina}</td>
				
			</tr>
		</c:forEach>
<!-- <c:forEach var="item" items="${alumnos}">
			<tr>
				<th>${item.id}</th>
				<th>${item.exp}</th>
				<td>${item.nombre}</td>
				<td>${item.fecNan}</td>
				
			</tr>
		</c:forEach>-->
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>