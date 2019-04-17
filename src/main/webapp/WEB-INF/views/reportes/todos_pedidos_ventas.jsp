<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Todos pedidos</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script type="text/javascript">
function FBuscar()
{
	//alert(document.getElementById("SSelect").value);
	window.location.replace('<c:url value="/reportes/ventas/buscarpedido"/>?anio='+document.getElementById("SSelect").value);
}
function FExcel()
{
	window.location.replace('<c:url value="/reportes/ventas/excelpedido"/>?anio='+document.getElementById("SSelect").value);
}

$(document).ready(function() {
	$('#tablePag').DataTable(
			{
				"searching": true,
				"paging": true,
				"dom": 'lrtip'
			}
		);
	$('input.column_filter').on( 'keyup click', function () {
        filterColumn();
    } );
} );

function filterColumn () {
	var i = document.getElementById("indexcol").value;
    $('#tablePag').DataTable().column(i).search(
        $('#col_filter').val(),
        false,
        true
    ).draw();
}

function FColumn(i)
{
	document.getElementById("indexcol").value = i;
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
		<span class="badge badge-secondary">Todos pedidos</span>
	</div>
	<br>
		<div align="center" class="container">
		<div class="row">
			<div class="col-md-2"><input type="hidden" id = "indexcol" value="0"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2">
				<select id="SSelect" onChange="FBuscar()">
					<option value="2010" ${select eq 2010 ? 'selected' : ''}>2010</option>
					<option value="2011" ${select eq 2011 ? 'selected' : ''}>2011</option>
					<option value="2012" ${select eq 2012 ? 'selected' : ''}>2012</option>
					<option value="2013" ${select eq 2013 ? 'selected' : ''}>2013</option>
					<option value="2014" ${select eq 2014 ? 'selected' : ''}>2014</option>
					<option value="2015" ${select eq 2015 ? 'selected' : ''}>2015</option>
					<option value="2016" ${select eq 2016 ? 'selected' : ''}>2016</option>
					<option value="2017" ${select eq 2017 ? 'selected' : ''}>2017</option>
					<option value="2018" ${select eq 2018 ? 'selected' : ''}>2018</option>
					<option value="2019" ${select eq 2019 ? 'selected' : ''}>2019</option>
					<option value="2020" ${select eq 2020 ? 'selected' : ''}>2020</option>
					<option value="2021" ${select eq 2021 ? 'selected' : ''}>2021</option>
					<option value="2022" ${select eq 2022 ? 'selected' : ''}>2022</option>
					<option value="2023" ${select eq 2023 ? 'selected' : ''}>2023</option>
					<option value="2024" ${select eq 2024 ? 'selected' : ''}>2024</option>
					<option value="2025" ${select eq 2025 ? 'selected' : ''}>2025</option>
					<option value="2026" ${select eq 2026 ? 'selected' : ''}>2026</option>
					<option value="2027" ${select eq 2027 ? 'selected' : ''}>2027</option>
					<option value="2028" ${select eq 2028 ? 'selected' : ''}>2028</option>
					<option value="2029" ${select eq 2029 ? 'selected' : ''}>2029</option>
					<option value="2030" ${select eq 2030 ? 'selected' : ''}>2030</option>
				</select>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-outline-primary" onClick="FExcel()">
					<i class="fa fa-print"></i>
					Descargar Excel
				</button>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<br>
	<div align="center" id="tabla1" class = "table-responsive-xl">
		<input type="text" class="column_filter" id="col_filter" placeholder="Buscar ..." 
		title="Seleccione la columna por la que desea buscar, depués capture el texto.">
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto"><!-- mx-auto  para centrar en pantalla -->
		<thead>
			<tr>
				<th onclick="FColumn(0)">docentry</th>
				<th onclick="FColumn(1)">u_tf</th>
				<th onclick="FColumn(2)">clavesap</th>
				<th onclick="FColumn(3)">pedido</th>
				<th onclick="FColumn(4)">cardname</th>
				<th onclick="FColumn(5)">orden_compra</th>
				<th onclick="FColumn(6)">elaboracion</th>
				<th onclick="FColumn(7)">entrega</th>
				<!-- <th>clavesap</th> -->
				<th onclick="FColumn(8)">simbolo</th>
				<th onclick="FColumn(9)">impresion</th>
				<th onclick="FColumn(10)">cierre</th>
				<th onclick="FColumn(11)">piezasxatado</th>
				<th onclick="FColumn(12)">resistencia</th>
				<th onclick="FColumn(13)">u_l</th>
				<th onclick="FColumn(14)">u_a</th>
				<th onclick="FColumn(15)">u_f</th>
				<th onclick="FColumn(16)">areajuego</th>
				<th onclick="FColumn(17)">cantidad</th>
				<th onclick="FColumn(18)">peso</th>
				<th onclick="FColumn(19)">totalmetros</th>
				<th onclick="FColumn(20)">combinacion</th>
				<th onclick="FColumn(21)">totalkilos</th>
				<th onclick="FColumn(22)">totaldocumento</th>
				<th onclick="FColumn(23)">precio</th>
				<!-- <th>shiptocode</th> -->
				<!-- <th>entrega</th> -->
				<th onclick="FColumn(24)">preciom2</th>
				<th onclick="FColumn(25)">docstatus</th>
				<th onclick="FColumn(26)">precioneto</th>
				<th onclick="FColumn(27)">descuento</th>
				<th onclick="FColumn(28)">canceled</th>
				<th onclick="FColumn(29)">pk</th>
				<th onclick="FColumn(30)">comision_porc</th>
				<th onclick="FColumn(31)">importecomision</th>
				<th onclick="FColumn(32)">pkr</th>
				<th onclick="FColumn(33)">tipo</th>
				<th onclick="FColumn(34)">importetotal</th>
				<th onclick="FColumn(35)">comments</th>
				<th onclick="FColumn(36)">especialidad</th>
				<th onclick="FColumn(37)">u_ctopapel</th>
				<th onclick="FColumn(38)">u_costo_flete</th>
				<th onclick="FColumn(39)">u_costesp</th>
				<th onclick="FColumn(40)">u_ctopsc</th>
				<th onclick="FColumn(41)">u_ctopcc</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${reporte}">
			<tr>
				<td>${item.docentry}</td>
				<td>${item.u_tf}</td>
				<td>${item.clavesap}</td>
				<td>${item.pedido}</td>
				<td>${item.cardname}</td>
				<td>${item.orden_compra}</td>
				<td>${item.elaboracion}</td>
				<td>${item.entrega}</td>
				<!-- <td>${item.clavesap}</td> -->
				<td>${item.simbolo}</td>
				<td>${item.impresion}</td>
				<td>${item.cierre}</td>
				<td>${item.piezasxatado}</td>
				<td>${item.resistencia}</td>
				<td>${item.u_l}</td>
				<td>${item.u_a}</td>
				<td>${item.u_f}</td>
				<td>${item.areajuego}</td>
				<td>${item.cantidad}</td>
				<td>${item.peso}</td>
				<td>${item.totalmetros}</td>
				<td>${item.combinacion}</td>
				<td>${item.totalkilos}</td>
				<td>${item.totaldocumento}</td>
				<td>${item.precio}</td>
				<!-- <td>${item.shiptocode}</td> -->
				<!-- <td>${item.entrega}</td> -->
				<td>${item.preciom2}</td>
				<td>${item.docstatus}</td>
				<td>${item.precioneto}</td>
				<td>${item.descuento}</td>
				<td>${item.canceled}</td>
				<td>${item.pk}</td>
				<td>${item.comision_porc}</td>
				<td>${item.importecomision}</td>
				<td>${item.pkr}</td>
				<td>${item.tipo}</td>
				<td>${item.importetotal}</td>
				<td>${item.comments}</td>
				<td>${item.especialidad}</td>
				<td>${item.u_ctopapel}</td>
				<td>${item.u_costo_flete}</td>
				<td>${item.u_costesp}</td>
				<td>${item.u_ctopsc}</td>
				<td>${item.u_ctopcc}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>