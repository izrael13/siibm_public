<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Prospectos gerente de ventas</title>
<%@include file="../../appconfig/authheader2.jsp"%>
<script type="text/javascript">
function FBuscar()
{
	//alert($( "#SVendedores" ).val() + $( "#SClientes" ).val() + $( "#SActividades" ).val() + $( "#SPrioridades" ).val() +" -"+ $( "#TOporton" ).val() +" -"+ $( "#SStatus" ).val());
	window.location.replace('<c:url value="/ventas/tarjetas/prospectos/vistagerenteventas" />?id='+0+'&cardcode='+$( "#SClientes" ).val()+'&cve_vendedor='+$( "#SVendedores" ).val()+'&porcavan='+$( "#SActividades" ).val()+'&prioridad='+$( "#SPrioridades" ).val()+'&oporton='+($( "#TOporton" ).val() == '' ? 0 : $( "#TOporton" ).val())+'&estatus='+$( "#SStatus" ).val()+'&fecha1='+$( "#TFecha1" ).val()+'&fecha2='+$( "#TFecha2" ).val());
}
</script>
<style type="text/css">
#tabla1{
	font-size: 13px;
	cellpadding: 2;
	cellspacing: 2;
}
</style>
</head>
<body>
<br>	
	<div align="center">
		<span class="badge badge-secondary">Prospectos gerente de ventas</span>
	</div>
<br>
<div align="left" class = "container">
	<div class="row small">
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row border border-right">
				<div class="col-sm-4">Vendedor</div>
				<div class="col-sm-3">% avance</div>
				<div class="col-sm-1">Prioridad</div>
				<div class="col-sm-2">Oport Ton</div>
				<div class="col-sm-2">Estatus</div>
			</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row border border-right">
				<div class="col-sm-4">
					<select id="SVendedores" class="border border-primary">
						<option value="0"> - - -</option>
						<c:forEach var="ven" items="${vendedores}">
							<option value="${ven.clavevendedor}">${ven.nombre}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-3">
					<select id = "SActividades" class="border border-primary">
						<option value="0"> - - - </option>
						<option value="10">10 - Identificación </option>
						<option value="20">20 - Contacto cliente </option>
						<option value="30">30 - Recabar información cotización </option>
						<option value="40">40 - Presentar cotización </option>
						<option value="50">50 - Respuesta cotización </option>
						<option value="60">60 - Cliente aceptó cotización </option>
						<option value="70">70 - Desarrollo TF </option>
						<option value="80">80 - Cliente envía OC </option>
						<option value="100">100 - Embarque primer pedido realizado</option>
					</select>
				</div>
				<div class="col-sm-1">
					<select id ="SPrioridades" class="border border-primary">
						<option value="0">-</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" id= "TOporton" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
				</div>
				<div class="col-sm-2">
					<select id = "SStatus" class="border border-primary">
						<option value="0"> - - -</option>
						<option value="1">Pendiente</option>
						<option value="2">Cerrada</option>
						<option value="3">Ganada</option>
						<option value="4">Perdida</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-12">
		<div class="row border border-right">
			<div class="col-sm-1">Cliente</div>
			<div class="col-sm-10">
				<select id = "SClientes" class="border border-primary">
					<option value="0"> - - -</option>
					<c:forEach var="cte" items="${clientes}">
						<option value="${cte.cardcode}">${cte.cardname} - Giro: ${cte.groupname}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-1"><a href="javascript:FBuscar()" class="btn btn-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
		</div>
		</div>
		<div class="col-12">
		<div class="row border border-right">
			<div class="col-sm-2">Fechas de cierre</div>
			<div class="col-sm-5">
				<input type="date" id="TFecha1"  max="3000-12-31" min="1000-01-01" class="border border-primary"/>
			</div>
			<div class="col-sm-5">
				<input type="date" id="TFecha2"  max="3000-12-31" min="1000-01-01" class="border border-primary"/>
			</div>
		</div>
		</div>
	</div>
</div>
<br>
<c:set var="totalton" value="0"/>
<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto"><!-- mx-auto  para centrar en pantalla -->
		<thead>
			<tr>
				<td>Folio</td>
				<td>Vendedor</td>
				<td>Cliente</td>
				<td>Oport Ton</td>
				<td>Prioridad</td>
				<td>%Avance</td>
				<td>Fecha Cierre</td>
				<td>Estatus</td>
				<td>Observaciones</td>
				<td>Fecha alta</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<c:set var="totalton" value="${totalton = totalton + item.opor_ton}"/>
			<tr>
				<td>${item.id}</td>
				<td>${empty item.nom_vendedor ? 0 : item.nom_vendedor}</td>
				<td>${item.cardname}</td>
				<td>${item.opor_ton}</td>
				<td>${item.prioridad}</td>
				<td>${item.porc_avance}</td>
				<td>${item.fecha_cierre}</td>
				<td>${item.estatus}</td>
				<td>${item.observaciones}</td>
				<td>${item.fecha_insert}</td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan="3">Total</td>
				<td>${totalton}</td>
			</tr>
		</tbody>
	</table>
</div>
<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>