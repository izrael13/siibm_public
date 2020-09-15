<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Conversion Diaria</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
    function getDate(){
      var today = new Date();
      var mes = "" + (today.getMonth() +1);
      if (mes.length < 2){
    	  mes = "0" + mes;
      }
      var dia = "" + today.getDate();
      if (dia.length < 2){
    	  dia = "0" + dia;
      }
      result = today.getFullYear() + "-" + mes + "-" + dia;
      return result;	
    }
    
	function FBuscar() {
	
		if($("#TDTP").val() == ""){
		    $("#TDTP").val(getDate());
		}
		window.location
				.replace('<c:url value="/reportes/produccion/buscarconversiondiaria" />?fecha_ini='
						+ $("#TDTP").val());
	}
	function Keypress() {
		return false;
	}
	
	function Excel() {
		if($("#TDTP").val() == ""){
			$("#TDTP").val(getDate());
		}
		window.location.replace('<c:url value="/reportes/produccion/conversiondiaria_excel" />?fecha_ini='+$("#TDTP").val());
	}
	
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Conversion Diaria</span>
	</div>
	<br>
	<div align="center" class="container rounded border border-info">
		<div class="row" align="center">
			<div class="col-md-3">
				<div align="center">
					<div class="input-group date" id="datetimepicker4"
						data-target-input="nearest">
						<input onkeypress="return Keypress()" id="TDTP"	data-target="#datetimepicker4" placeholder="yyyy-mm-dd"	class="border border-primary" value="${selectedValue}" />
						<div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
							<div class="input-group-text">
								<i class="fa fa-calendar"></i>
							</div>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$(function() {
						$('#datetimepicker4').datetimepicker({
							format : 'YYYY-MM-DD'
						});
					});
				</script>
			</div>
			<div class="col-md-2">
				<button onclick="FBuscar()" class="btn btn-outline-primary">
					<i class="fa fa-search"></i>Buscar
				</button>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla1')">
					<i class="fa fa-print"></i> Imprimir
				</button>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-outline-primary" onClick="Excel()">
					<i class="fa fa-file-excel-o"></i> Excel
				</button>
			</div>
		</div>
	</div>
	<div id="tabla1" class="table-responsive-xl container">
		<table id="tablePag"
			class="table table-hover table-bordered text-center small">
			<thead>
				<tr>
					<th colspan="10">Conversion diaria ${fecha_ini}</th>
				</tr>
				<tr>
					<th>Pedido</th>
					<th>Programadas</th>
					<th>Corrugadas</th>
					<th>Inicio Setup</th>
					<th>Termino Setup</th>
					<th>Inicio Conversion</th>
					<th>Fin Conversion</th>
					<th>Piezas Contadas</th>
					<th>Piezas Buenas</th>
					<th>Laminas Malas</th>
					<th>Malas Setup</th>
					<th>Malas Proceso</th>
					<th>Laminas Por Procesar</th>
					<th>Work Center ID</th>
					<th>Number Out</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${reporte}">
					<tr>
						<td>${item.pedido}</td>
						<td>${item.programadas}</td>
						<td>${item.corrugadas}</td>
						<td>${item.inicioSetup}</td>
						<td>${item.terminoSetup}</td>
						<td>${item.inicioConversion}</td>
						<td>${item.finConversion}</td>
						<td>${item.piezasContadas}</td>
						<td>${item.piezasBuenas}</td>
						<td>${item.laminasMalas}</td>
						<td>${item.malasSetup}</td>
						<td>${item.malasProceso}</td>
						<td>${item.laminasPorProcesar}</td>
						<td>${item.workCenterID}</td>
						<td>${item.numberOut}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>