<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Propectos b�squeda</title>
<%@include file="../../appconfig/imports.jsp"%>
<script type="text/javascript">
function FBuscarId(id)
{
	window.opener.FBuscarxId(id);
	window.close();
}
</script>
</head>
<body>
	<div id="tabla1" class = "table-responsive-xl container">
		<table class="table-hover table-bordered text-center small">
		<thead>
			<tr>
				<td>Folio</td>
				<td>Cliente</td>
				<td>Giro</td>
				<td>Contacto</td>
				<td>Opor Ton</td>
				<td>Fecha cierre</td>
				<td>Tel�fono</td>
				<td>Estado</td>
				<td>ciudad</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<tr>
				<td><a href="javascript:FBuscarId(${item.id})">${item.id}</a></td>
				<td>${item.cardname}</td>
				<td>${item.groupname}</td>
				<td>${item.contacto}</td>
				<td>${item.opor_ton}</td>
				<td>${item.fecha_cierre}</td>
				<td>${item.tel}</td>
				<td>${item.edo}</td>
				<td>${item.ciudad}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>