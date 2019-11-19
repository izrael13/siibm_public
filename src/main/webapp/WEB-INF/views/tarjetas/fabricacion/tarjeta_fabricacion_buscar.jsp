<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Búsqueda de tarjetas</title>
<%@include file="../../appconfig/imports.jsp"%>
<script type="text/javascript">
function FBuscarId(folio)
{
	window.opener.FBuscarxFolio(folio);
	window.close();
}
</script>
</head>
<body>
	<div id="tabla1" class = "table-responsive-xl container">
	<table class="table-hover table-bordered text-center small">
		<thead>
			<tr><th colspan="4">Seleccionar Detalle</th></tr>
			<tr>
				<td>Folio tarjeta</td>
				<td>Folio cotización</td>
				<td>Símbolo</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<tr>
				<td><a href="javascript:FBuscarId('${item.folio_tarjeta}')">${item.folio_tarjeta}</a></td>
				<td>${item.idcotizacion}</td>
				<td>${item.descripcion_factura}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>