<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista de embarque</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscar()
{
	window.location.replace('<c:url value="/reportes/embarques/listadeembarque" />?fechaini='+document.getElementById("TFechaini").value+'&fechafin='+document.getElementById('TFechafin').value);
}
</script>
</head>
<body>	
<div class="col-12">
	 <div class="row ">
		 <div class="badge badge-primary col-12">
		 	Lista de embarque
		 </div>
	 </div>
 </div>
<div align="center" class="container">
<div class="row">
		<div class="col-md"> 
	      <div class="">
			<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
	           <strong>Fecha inicial:</strong> <input id="TFechaini" onkeypress="return false" value="${fechaini}" data-target="#datetimepicker4" placeholder="yyyy-mm-dd" class="border border-primary"/>
	           <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
	               <div class="input-group-text"><i class="fa fa-calendar"></i></div>
	           </div>
	        </div>
	       </div>
		<script type="text/javascript">
	           $(function () {
	               $('#datetimepicker4').datetimepicker({
	                   format: 'YYYY-MM-DD'
	               });
	           });
	     </script>
	</div>
	<div class="col-md">
	      <div class="">
			<div class="input-group date" id="datetimepicker5" data-target-input="nearest">
	           <strong>Fecha final:</strong> <input id="TFechafin" onkeypress="return false" value="${fechafin}" data-target="#datetimepicker5" placeholder="yyyy-mm-dd" class="border border-primary"/>
	           <div class="input-group-append" data-target="#datetimepicker5" data-toggle="datetimepicker">
	               <div class="input-group-text"><i class="fa fa-calendar"></i></div>
	           </div>
	        </div>
	       </div>
		<script type="text/javascript">
	           $(function () {
	               $('#datetimepicker5').datetimepicker({
	                   format: 'YYYY-MM-DD'
	               });
	           });
	     </script>
	</div>
	<div class="col-md">
		<button type="button" class="btn btn-outline-primary" onClick="FBuscar()">
			<i class="fa fa-search"></i>
			Buscar
		</button>
	</div>
</div>
</div>

<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto small"><!-- mx-auto  para centrar en pantalla -->
		<thead>
			<tr>
				<th>Pedido</th>
				<th>Fecha producción</th>
				<th>Fechan entrega</th>
				<th>Nombre</th>
				<th>Clave</th>
				<th>Símbolo</th>
				<th>Cantidad solicitada</th>
				<th>Cantidad embarcada</th>
				<th>Stock lote</th>
				<th>Kilos</th>
				<th>Lugar entrega</th>
				<th>Observaciones</th>
				<th>Vendedor</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" items="${Lista}">
			<tr>
				<td>${i.pedido}</td>
				<td>${i.fechaproduccion == '1900-01-01 00:00:00.0' ? '' : fn:substring(i.fechaproduccion, 0, 10)}</td>
				<td>${i.fechaentregacliente == '1900-01-01 00:00:00.0' ? '' : fn:substring(i.fechaentregacliente, 0, 10)}</td>
				<td>${i.nombre}</td>
				<td>${i.clave}</td>
				<td>${i.simbolo}</td>
				<td>${i.cansol}</td>
				<td>${i.canembarcada}</td>
				<td>${i.stocklote}</td>
				<td>${i.kilos}</td>
				<td>${i.lugarentrega}</td>
				<td>${i.observaciones}</td>
				<td>${i.slpname}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</body>
</html>