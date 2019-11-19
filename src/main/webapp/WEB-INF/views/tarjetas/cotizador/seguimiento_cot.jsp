<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Seguimiento de cotizaciones</title>
<script>
$(document).ready(function() {
	
	$('#SClientes').selectize({
	    create: true,
	    sortField: 'text'
	});

	$('#SVen').selectize({
	    create: true,
	    sortField: 'text'
	});
});
function FBuscar()
{
	var fol = $('#TFolio').val();
	var ven = $('#SVen').val();
	var cte = $('#SClientes').val();
	var stt = $('#SEst').val();
	if(fol != "" || cte != "" || ven != "" || stt != "")
	{
		window.location.replace('<c:url value="/cotizador/vendedor/seguimiento_cot" />?folio='+fol+'&cte='+cte+'&ven='+ven+'&est='+stt);
	}
}
function FBuscarHist(id,iddet)
{
	var redirectWindow = window.open('<c:url value="/cotizador/vendedor/seguimiento_cot_hist" />?id='+id+'&iddet='+iddet);
	redirectWindow.replace();
}
</script>
</head>
<body>
	<div align="center">
		<span class="badge badge-secondary">Seguimiento de cotizaciones</span>
	</div>
	<div class="col-sm">
		<div class="badge badge-info col-12">Búsqueda</div>
		<div class="row small">		
			<div class="col-2 font-weight-bold">Folio:
				<input id="TFolio" class="border border-primary" size="10" maxlength="8" type="text" onkeypress="return Enteros(event);"/>
			</div>
			<div class="col-1 font-weight-bold">Vendedor:</div>
			<div class="col-3 font-weight-bold">
				<select id="SVen" class="border border-primary" ${empty CteVen ? '' : 'disabled'}>
					<option value=""></option>
					<c:forEach var="ven" items="${ListaVen}">
						<option value="${ven.clavevendedor}" ${CteVen == ven.clavevendedor ? 'selected' : ''}><c:out value="${ven.nombre}"/></option>
					</c:forEach>
				</select>
			</div>
			<div class="col-3 font-weight-bold">Estatus:
			<select id="SEst" class="border border-primary">
				<option value=""></option>
				<option value="1"><c:out value="Enviadas"/></option>
				<option value="2"><c:out value="Ventas"/></option>
				<option value="3"><c:out value="Programacíon"/></option>
				<option value="4"><c:out value="Pendientes asignar diseñador"/></option>
				<option value="5"><c:out value="Convertidas a tarjetas"/></option>
				<option value="6"><c:out value="Canceladas"/></option>
				<!-- <option value="7"><c:out value="Rechazadas"/></option>  -->
			</select>
			</div>
		<div class="col-2 font-weight-bold"><a href="javascript:FBuscar()" class="btn btn-outline-primary"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
		</div>
	</div>
	<div class="col-sm">
		<div class="row small">
		<div class="col-1 font-weight-bold">Cliente:</div>
		<div class="col-10 font-weight-bold">
				<select id=SClientes class="border border-primary">
					<option value=""></option>
					<c:forEach var="cte" items="${ListaCte}">
						<option value="${cte.cardcode}"><c:out value="${cte.cardname}"/></option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>	
	<br>
		<table class="container-fluid table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>Folio</th>
				<th colspan="6">Detalles</th>
				<th>Cliente</th>
				<th>Vendedor</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${Lista}" varStatus="counter">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${item['id']}" />
			<tr>
				<td>${i}</td>
				<td colspan="6">
					<table class="container-fluid table-hover text-left">
					<c:forEach var="det" items="${item['ListaDetalles']}" varStatus="counter">
					<tr>
						<fmt:parseNumber var = "ii" integerOnly = "true" pattern="##############" type = "number" value = "${det['iddetalle']}" />
						<td><a href="javascript:FBuscarHist(${i},${ii})">${det['simbolo']}</a></td>
					</tr>
					</c:forEach>
					</table>
				</td>	
				<td>${item['cliente']}</td>
				<td>${item['representante']}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>