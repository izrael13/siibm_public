<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Autorización Logística</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function Detalle(nviaje)
{
	popupwindow('<c:url value="/viajes/detalle" />?num_viaje='+nviaje,'Detalle de viaje',800,800);
	//alert(nviaje);
	//window.open('<c:url value="/viajes/detalle" />?num_viaje='+nviaje,null,
	//"height=800,width=1000,status=yes,toolbar=no,menubar=no,location=no");
}
function Autorizar(nviaje)
{
	if(confirm("¿Autorizar el viaje: "+nviaje +"?"))
	{
		window.location.replace('<c:url value="/viajes/set_aut_logistica" />?num_viaje='+nviaje);
	}
}
function Regresar(nviaje)
{
	if(confirm("¿Regresar viaje a embarques: "+nviaje +"?"))
	{
		window.location.replace('<c:url value="/viajes/set_reg_embarques" />?num_viaje='+nviaje);
	}
}
$(document).ready(function() {
	$('#tablePag').DataTable();
	} );
/*function FHistorial()
{
	popupwindow('<c:url value="/viajes/hisotia___" />','Historial de viajes',800,800);
	//window.open('<c:url value="/viajes/hisotia___" />',null,
	//"height=800,width=1000,status=yes,toolbar=no,menubar=no,location=no");
}*/
</script>
</head>
<body>
		<br>
	<div align="center">
		<span class="badge badge-secondary">Autorización Logística</span>
	</div>
	<br>
	<div align="center" id="tabla1" class = "table-responsive-xl container">
		<table id="tablePag" class="table-hover table-bordered text-center small mx-auto">
		<thead>
			<tr>
				<th>Viaje</th>
				<th>Fecha registro</th>
				<th>Kilos</th>
				<th>Repartos</th>
				<th>Cambios de estado</th>
				<th>Maniobras</th>
				<th>Costo</th>
				<th>Demoras</th>
				<th>Devoluciones</th>
				<th>Autorizado embarques</th>
				<th>Regresar</th>
				<th>Autorizar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${viajes}">
			<tr>
				<td><a href="javascript:Detalle(${item.u_num_viaje})">${item.u_num_viaje}</a></td>
				<td>${item.u_fecha_reg}</td>
				<td>${item.u_kilos}</td>
				<td>${item.u_repartos}</td>
				<td>${item.u_cambio_edo}</td>
				<td>${item.u_maniobras}</td>
				<td>${item.u_costo}</td>
				<td>${item.u_demoras}</td>
				<td>${item.u_devoluciones}</td>
				<td>${item.u_aut_ambarques}</td>
				<td>
					<a href="javascript:Regresar(${item.u_num_viaje})"><i class="fa fa-thumbs-o-down fa-2x"></i></a>
				</td>
				<td>
					<a href="javascript:Autorizar(${item.u_num_viaje})"><i class="fa fa-thumbs-o-up fa-2x"></i></a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<!-- <br>
	<div align="center">
		<button onclick="FHistorial()" class="btn btn-outline-info">Historial de viajes</button>
	</div> -->
	<div class="container">
		<div class="row">
		<div class="col-lg-2 col-md-2">
		</div>
			<div class="col-lg-8 col-md-8">
				<div id="mensajes" class="${not empty result ? 'alert alert-success' : ''}">${result} </div>
			</div>
		</div>
	</div>
	<br>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>