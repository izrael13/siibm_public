	<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
	<%@ page isELIgnored="false" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Consumo en kilos</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<script type="text/javascript">
function FBuscar()
{
	if($("#dtp1").val() == "")
	{
		alert("Seleccione fecha inicial.");
		$("#dtp1").focus();
	}
	else
	{
		if($("#dtp2").val() == "")
		{
			alert("Seleccione fecha final.");
			$("#dtp2").focus();
		}
		else
		{
			window.location.replace('<c:url value="/reportes/papel/buscarconsumokilos" />?fecha_ini='+$("#dtp1").val()+'&fecha_fin='+$("#dtp2").val());
		}
	}
}

function Excel()
{
	if($("#dtp1").val() == "")
	{
		alert("Seleccione fecha inicial.");
		$("#dtp1").focus();
	}
	else
	{
		if($("#dtp2").val() == "")
		{
			alert("Seleccione fecha final.");
			$("#dtp2").focus();
		}
		else
		{
			window.location.replace('<c:url value="/reportes/papel/consumo_kilos_excel" />?fecha_ini='+$("#dtp1").val()+'&fecha_fin='+$("#dtp2").val());
		}
	}
}

$(document).ready(function() {
	$('#tablePag').DataTable();
} );

</script>
</head>
<body>
	<br>	
	<div align="center">
		<span class="badge badge-secondary">Consumo en kilos</span>
	</div>
	<br>
	<div align="center" class="container">
	    <div class="row">
	        <div class="col-md-3">
				<div class="container">
   					<div class="row">
			            <div class="form-group">
			                <div class="input-group date" id="datetimepicker4" data-target-input="nearest">
			                    <input id="dtp1"class="form-control datetimepicker-input" data-target="#datetimepicker4" value="${fecha_ini}" placeholder="yyyy/mm/dd HH:mm"/>
			                    <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
			                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
			                    </div>
			                </div>
			            </div>
				        <script type="text/javascript">
				            $(function () {
				                $('#datetimepicker4').datetimepicker({
				                    format: 'YYYY/MM/DD HH:mm'
				                });
				            });
				        </script>
    				</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="container">
   					<div class="row">
			            <div class="form-group">
			                <div class="input-group date" id="datetimepicker5" data-target-input="nearest">
			                    <input id="dtp2"class="form-control datetimepicker-input" data-target="#datetimepicker5" value="${fecha_fin}" placeholder="yyyy/mm/dd HH:mm"/>
			                    <div class="input-group-append" data-target="#datetimepicker5" data-toggle="datetimepicker">
			                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
			                    </div>
			                </div>
			            </div>
				        <script type="text/javascript">
				            $(function () {
				                $('#datetimepicker5').datetimepicker({
				                    format: 'YYYY/MM/DD HH:mm'
				                });
				            });
				        </script>
    				</div>
				</div>
			</div>
	        <div class="col-md-2">
	        	<button type="button" class="btn btn-outline-primary" onclick="FBuscar()">
				<i class="fa fa-search"></i>
				Buscar
				</button>
			</div>
	        <div class="col-md-2">
	        	<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla1')">
				<i class="fa fa-print"></i>
				Imprimir
				</button>
			</div>
			<div class="col-md-2">
	        	<button type="button" class="btn btn-outline-primary" onClick="Excel()">
				<i class="fa fa-file-excel-o"></i>
				Excel
				</button>
			</div> 
	    </div>
    </div>
    
    <br>
	<div id="tabla1" class = "table-responsive-xl container">
	<table id="tablePag" class="table table-hover table-bordered text-center small">
		<thead>
			<tr>
				<th colspan="10">Consumo en kilos ${fecha_ini} - ${fecha_fin}</th>
			</tr>
			<tr>
				<th>Tipo - Ancho</th>
				<th>Tipo</th>
				<th>Ancho</th>
				<th>Inventario inicial</th>
				<th>Entradas</th>
				<th>Metros lineales</th>
				<th>Programado</th>
				<th>Salidas</th>
				<th>Diferencia</th>
				<th>Inventario final</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${reporte}">
			<tr>
				<td>${item.tipo_papel}</td>
				<td>${item.tipo}</td>
				<td>${item.ancho}</td>
				<td>${item.inv_ini}</td>
				<td>${item.entradas}</td>
				<td>${item.mtrslineales}</td>
				<td>${item.programado}</td>
				<td>${item.salidas}</td>
				<td>${item.diferencia}</td>
				<td>${item.inv_fin}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>