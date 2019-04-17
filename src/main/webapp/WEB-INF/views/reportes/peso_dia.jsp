<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reporte de embarque diario</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script type="text/javascript">
function FBuscar()
{
	//alert(document.getElementById("aniomes").value);
	window.location.replace('<c:url value="/reportes/ventas/buscarPeso_dia_" />?aniomes='+document.getElementById("aniomes").value);
	//var json = {"firstname" : "Israel", "lastname" : "Duran"};
	/*$.ajax({
		dataType: 'text',
        url : '<c:url value="/profile"/>',
        data:  {"firstname" : "Israel", "lastname" : "Duran"},
        type : "GET",
		
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success : function(response) {
            alert( response );
        },
        error : function(xhr, status, error) {
            alert("Error: " + xhr.responseText + " Codigo" +  error);
        }
    }); FUNCIONA OK CON AJAX*/
}
</script>
</head>
<body>
	<br>
		<div align="center">
			<span class="badge badge-secondary">Reporte de embarque diario</span>
		</div>
	<br>
		<div align="center" class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-2">Año - Mes</div>
			<div class="col-md-2">
				<form:select id="aniomes" path="mesesanio" class="form-control input-sm" onchange="FBuscar()">
					<c:forEach var="i" items="${mesesanio}">
						<c:set var="anio_mes_">${i.anio}${i.mes}</c:set>
						<c:choose>
							<c:when
								test="${not empty selectedValue && selectedValue eq anio_mes_ }">
								<option value="${i.anio}${i.mes}" selected>${i.anio} - ${i.mes}</option>
							</c:when>
							<c:otherwise>
								<option value="${i.anio}${i.mes}">${i.anio} - ${i.mes} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla1')">
					<i class="fa fa-print"></i>
					Imprimir
				</button>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<br>
	<div id="tabla1" style = "width:300px" class = "table-responsive-xl container">
		<table class="table table-hover table-bordered text-center small">
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Peso</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${reporte}">
				<tr>
					<td>${fn:substring(item.fecha_ini,0,10)}</td>
					<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.peso}" /></td>
				</tr>
				<c:set var="pesoT" value="${pesoT = pesoT + item.peso}"/>
				</c:forEach>
				<tr>
					<th>Total</th>
					<td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${pesoT}" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>