<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Seguimiento de arrastres/muestras</title>
<script>
function FBuscar()
{
	var folio = $("#TFolio").val();
	var emp = $("#SEmp").val();
	var ven = $("#SVen").val();
	var est = $("#SEst").val();
	var tipo = $("#STipo").val();
	
	if(folio != "" || emp != "" || ven != "" || est != "" || tipo != "")
	{
		window.location.replace('<c:url value="/cotizador/vendedor/seguimiento_arrastres_muestras" />?folio='+folio+'&emp='+emp+'&ven='+ven+'&est='+est+'&tipo='+tipo);
	}
}
</script>
</head>
<body>
	<div align="center">
		<span class="badge badge-secondary">Seguimiento de arrastres/muestras</span>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<div class="col-sm">
		<div class="badge badge-info col-12">Búsqueda</div>
		<div class="row small">		
			<div class="col-2 font-weight-bold">Folio:
				<input id="TFolio" class="border border-primary" size="10" maxlength="8" type="text" onkeypress="return Enteros(event);"/>
			</div>			
			<div class="col-3 font-weight-bold">Muestrista/Arrastre:
				<select id="SEmp" class="border border-primary">
					<option value=""></option>
					<c:forEach var="emp" items="${ListaEmp}">
						<option value="${emp.id}"><c:out value="${emp.firstName} ${emp.lastName}"/></option>
					</c:forEach>
				</select>
			</div>
			<div class="col-3 font-weight-bold">Vendedor
				<select id="SVen" class="border border-primary">
					<option value=""></option>
					<c:forEach var="ven" items="${vendedores}">
						<option value="${ven.clavevendedor}"><c:out value="${ven.nombre}"/></option>
					</c:forEach>
				</select>
			</div>
			<div class="col-2 font-weight-bold"><a href="javascript:FBuscar()" class="btn btn-outline-primary"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
		</div>
	</div>
	<div class="col-sm">
		<div class="row small">
		<div class="col-2 font-weight-bold">Estatus
			<select id="SEst" class="border border-primary">
				<option value=""></option>
				<option value="1"><c:out value="Enviadas"/></option>
				<option value="2"><c:out value="Asignadas"/></option>
				<option value="3"><c:out value="Liberadas"/></option>
				<option value="4"><c:out value="Canceladas"/></option>
				<option value="5"><c:out value="Rechazadas"/></option>
			</select>
		</div>
		<div class="col-2 font-weight-bold">Tipo
			<select id="STipo" class="border border-primary">
				<option value="1"><c:out value="Muestra simple"/></option>
				<option value="2"><c:out value="Muestra vestida"/></option>
				<option value="3"><c:out value="Arrastre"/></option>
			</select>
		</div>
		</div>
	</div>	
	<br>
	<div align="center" class="container-fluid">
	<table class="container-fluid table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>Folio</th>
				<th>Detalles</th>
				<th>Fecha creación</th>
				<th>Fecha envío</th>				
				<th>Fecha asignación</th>
				<th>Fecha rechazo</th>
				<th>Fecha liberación</th>
				<th>Fecha cancelación</th>
				<th>Muestrista/Arr</th>
				<th>Vendedor</th>
				<th>Tipo</th>
				<th>Comentarios</th>
				<th>Imprimir</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${Lista}" varStatus="counter">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${item['id']}" />
			<tr>
				<td>${i}</td>
				<td>
				<c:forEach var="itemd" items="${item['ListaDetalles']}" varStatus="counter">
					<table class="container-fluid table-hover text-center">
						<th>Símbolo</th>
						<th>Resistencia</th>
						<tr>
							<td>${itemd['simbolo']}</td>
							<td>R:${itemd['resistencia']} F:${itemd['flauta']} P:${itemd['papel']}</td>
						</tr>
					</table>
				</c:forEach>
				</td>
				<td>${item['fecha_insert']}</td>
				<td>${item['fecha_envia_arrmues']}</td>
				<td>${item['fecha_asigna_arrastre']}</td>
				<td>${item['fecha_rech_arrastre']}</td>
				<td>${item['fecha_libera_arrastre']}</td>
				<td>${item['fecha_cancel']}</td>
				<td>${item['arrmuestrista']}</td>
				<td>${item['representante']}</td>
				<td>${item['idtiporequerimiento'] == 1 ? 'Muestra simple' : item['idtiporequerimiento'] == 2 ? 'Muestra vestida' : 'Arrastre'}</td>
				<td>${item['observaciones_arrastre']}</td>
				<td><a href="javascript:FImprimir(${i})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>