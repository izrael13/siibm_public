<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" />
<html>
<head>
<%@ include file="../../appconfig/authheader2.jsp" %>
<title>Tarjetas seguimiento</title>
<script>
$(document).ready(function() {
	$('#SClientes').selectize({
	    create: true,
	    sortField: 'text'
	});
});
function FBuscar()
{
	window.location.replace('<c:url value="/tarjeta/ingenieria/tarjetas_seguimiento"/>?FolioTF='+$('#TFolioTF').val()+'&Cot='+$( "#TIdCot" ).val()+'&Status='+$( "#SStatus" ).val()+'&CardCode='+$( "#SClientes" ).val());
}
function FBuscarTF(folio)
{
	popupwindow('<c:url value="/tarjeta/ingenieria/tarjeta_seguimiento_info" />?folio='+folio,'Tarjeta seguimiento info',800,1200);
}
</script>
</head>
<body>
	<div align="center">
		<span class="badge badge-secondary">Tarjetas seguimiento</span>
	</div>
	<div align="left" class = "container">
	<div class="row small">
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row border border-right">
				<div class="col-sm-1">Folio TF</div>
				<div class="col-sm-1">
					<input id="TFolioTF" class="border border-primary" size="9" maxlength="8" onkeypress="return SinCaracteresEspeciales(event)" type="text"/>
				</div>
				<div class="col-sm-1">Cotización</div>
				<div class="col-sm-1">
					<input id="TIdCot" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" type="text"/>
				</div>
				<div class="col-sm-1">Status</div>
				<div class="col-sm-2">
					<select id = "SStatus" class="border border-primary">
						<option value="0"> - - -</option>
						<option value="1">Rechazadas</option>
						<option value="2">Canceladas</option>
						<option value="3">Diseño</option>
						<option value="4">Calidad</option>
						<option value="5">Producción</option>
						<option value="6">Ingeniería</option>
						<option value="7">Cliente</option>
						<option value="8">Autorizadas x cliente</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row small">
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row border border-right">
				<div class="col-sm-1">Cliente</div>
				<div class="col-sm-8">
					<select id="SClientes" class="border border-primary">
						<option value=""></option>
						<c:forEach var="cte" items="${clientes}">
							<option value="${cte.cardcode}"><c:out value="${cte.cardname}"/></option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2"><a href="javascript:FBuscar()" class="btn btn-outline-primary"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
			</div>
		</div>
	</div>
	</div>
	<div id="tabla1" class = "table-responsive-xl container">
		<table class="table-hover table-bordered text-center small mx-auto">
		<thead>
			<tr>
				<td>Folio</td>
				<td>Cotización</td>
				<td>Símbolo</td>
				<td>Fecha</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<tr>
				<td><a href="javascript:FBuscarTF(${item.folio_tarjeta})">${item.folio_tarjeta}</a></td>
				<td>${item.idcotizacion}</td>
				<td>${item.descripcion_factura}</td>
				<td>${item.fecha_rech_calidad}${item.fecha_rech_produccion}${item.fecha_rech_ing}${item.fecha_rech_cliente}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>