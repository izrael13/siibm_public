<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Media pedidos por cliente</title>
<%@include file="../appconfig/authheader2.jsp"%>
<style type="text/css">
#tabla1{
	font-size: 11px;
	cellpadding: 2;
	cellspacing: 2;
}
</style>
<script type="text/javascript">
function Excel()
{
	window.location.replace('<c:url value="/reportes/ventas/mediapedidosExcel"/>');
}
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Media pedidos por cliente</span>
	</div>
	<br>
	<div class="mx-auto col-md-2">
        <button type="button" class="btn btn-outline-primary" Onclick="Excel()">
			<i class="fa fa-file-excel-o"></i>
			Descargar Excel
		</button>
    </div>
	<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto"><!-- mx-auto  para centrar en pantalla -->
		<thead>
			<tr>
				<th>Cliente</th>
				<th>Nombre cliente</th>
				<th>[2017-01-01/2017-12-31]</th>
				<th>[2018-01-01/2018-07-31]</th>
				<th>[2018-08-01/2019-04-30]</th>
				<th>[2019-05-01/2019-12-31]</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${reporte}">
			<tr>
				<td>${item.cardcode}</td>
				<td>${item.cardname}</td>
				<td>${item.periodo1}</td>
				<td>${item.periodo2}</td>
				<td>${item.periodo3}</td>
				<td>${item.periodo4}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>