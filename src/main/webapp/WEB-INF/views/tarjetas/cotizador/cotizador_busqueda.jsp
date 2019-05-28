<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cotizador búsqueda</title>
<%@include file="../../appconfig/imports.jsp"%>
<script type="text/javascript">
function FBuscarId(id)
{
	window.opener.FBuscarxId(id);
	window.close();
}
</script>
<body>
	<div id="tabla1" class = "table-responsive-xl container">
		<table class="table-hover table-bordered text-center small">
		<thead>
			<tr>
				<td>Folio</td>
				<td>Cliente</td>
				<td>Dirección</td>
				<td>Fecha alta</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<tr>
				<td><a href="javascript:FBuscarId(${item.id})">${item.id}</a></td>
				<td>${item.cardname}</td>
				<td>${item.direccion}</td>
				<td>${item.fecha_insert}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>