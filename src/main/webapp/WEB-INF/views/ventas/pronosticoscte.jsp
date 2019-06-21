<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Pronósticos</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script type="text/javascript">
function FSubirQV()
{

	if('<c:out value="${file.size()}"/>' == "")
	{
		alert("Valide archivo.");
	}
	else
	{
		if('<c:out value="${map.size()}"/>' != "" && '<c:out value="${map.size()}"/>' > 0)
		{
			var r = confirm("Existen algunas alertas sobre la información!!!. ¿DESEA SUBIRLO?");
			if (r == true) 
			{
				if(document.getElementById("TMes").value == "")
				{
					alert("Capture mes.");
					document.getElementById("TMes").focus();
				}
				else
				{
					window.location.replace('<c:url value="/qlikview/ventas/subirqv" />?mes='+$("#TMes").val()+'&anio='+$("#TAnioAct").val());
				}
			} 
		}
		else
		{
			if(document.getElementById("TMes").value == "")
			{
				alert("Capture mes.");
				document.getElementById("TMes").focus();
			}
			else
			{
				window.location.replace('<c:url value="/qlikview/ventas/subirqv" />?mes='+$("#TMes").val()+'&anio='+$("#TAnioAct").val());
			}
		}
	}

}
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Pronósticos</span>
	</div>
	<br>
	<form method="POST" action = "${pageContext.request.contextPath}/qlikview/ventas/upload" enctype = "multipart/form-data" autocomplete="off">
	
	<c:if test="${mensaje != null}">
		<div class="alert alert-success" role="alert">
	  		${mensaje}
		</div>
	</c:if>
	<c:if test="${error != null}">
		<div class="alert alert-danger" role="alert">
	  		${error}
		</div>
	</c:if>
	
	<div align="center" class="container">
		<div class="row">
			<div class="col-md-4">	
				<input class="btn btn-outline-primary" value="${filepath}" type="file" id="file" name="file" accept=".xls, .xlsx">
			</div>
			<div class="col-md-2">
				<button type="submit" class="btn btn-outline-primary">
					<i class="fa fa-file-excel-o"></i>
					Validar Excel
				</button>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-outline-primary" onClick="FSubirQV()">
					<i class="fa fa-file-excel-o"></i>
					Subir a Qlick View
				</button>
			</div>
			<div class="col-1"><input onKeyPress="return Enteros(event)" value="${mesact}" id="TMes"/></div>
			<div class="col-1"><input readonly="true" value="${anioact}" id="TAnioAct"/></div>
		</div>
	</div>
	</form>
		<c:if test="${!empty map}">
	<table>
		<thead>
			<tr>
				<th colspan="2">CLIENTES APARECEN DOS O MÁS VECES</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="map" items="${map}">
			<tr>
				<th>${map.n}</th>
				<th>${map.CVE_CLIENTE}</th>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
	<table id="tablePag" class="table table-hover table-bordered text-center small">
		<thead>
			<tr>
				<th>Id</th>
				<th>Clave Cliente</th>
				<th>Cliente</th>
				<th>Vendedor</th>
				<th>Pronóstico</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${file}">
			<tr>
				<td>${item.ID}</td>
				<td>${item.CVE_CLIENTE}</td>
				<td>${item.CLIENTE}</td>
				<td>${item.VENDEDOR}</td>
				<td>${item.PRONOSTICO}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>