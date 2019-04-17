<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Consumo de papel - última semana</title>
<script type="text/javascript">
function Excel()
{
	/*$("#Mensaje").html("Descargando . . .")
	 $.ajax({url: '<c:url value="/Excel_ult_sem" />', 
			success: function(result)
			{
	        	$("#Mensaje").html(result);
	    	}
	 });*/
	window.location.replace('<c:url value="/reportes/papel/Excel_ult_sem"/>');
}
</script>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Consumo de papel - última semana</span>
	</div>
	<br>
	<div align="center" class="container">
    <div class="row">
    	<div class="col-md-6">
    		<button type="button" class="btn btn-outline-primary" Onclick="Excel()">
					<i class="fa fa-file-excel-o"></i>
					Descargar Excel
			</button>
    	</div>
        <div class="col-md-6">
        	<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla_ult_sem')">
					<i class="fa fa-print"></i>
					Imprimir
			</button>
		</div>
    </div>
    </div>
	
	<br>
		<div id="tabla_ult_sem" class = "table-responsive-xl container">
			<table class="table table-hover table-bordered text-center small">
			<tr>
				<td>Año</td>
				<td>Semana</td>
				<td>Ancho</td>
				<td>Tipo papel</td>
				<td>Promedio 12 semanas</td>
				<td>Inventario inicial</td>
				<td>Programados semana actual</td>
				<td>Diferencia</td>
			</tr>
			<c:forEach var="item" items="${reporte}">
			<tr>
				<td>${item.anio}</td>
				<td>${item.semana}</td>
				<td>${item.ancho}</td>
				<td>${item.tipo_papel}</td>
				<td>${item.prom_utl_sem}</td>
				<td>${item.inventario_inicial}</td>
				<td>${item.prom_act_sem}</td>
				<td class = "${item.diferencia < 0 ? 'alert alert-danger' : ''}">${item.diferencia}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>