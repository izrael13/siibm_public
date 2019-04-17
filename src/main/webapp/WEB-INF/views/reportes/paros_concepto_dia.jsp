<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Paros por concepto diarios</title>
<script type="text/javascript">
function FBuscar()
{
	if($("#dtp1").val().trim() != "")
	{
		window.location.replace('<c:url value="/reportes/produccion/buscarparoscondia" />?fecha_ini='+$("#dtp1").val());
	}
	else
	{
		alert("Selecione Fecha");
	}
}

</script>

<%@include file="../appconfig/authheader2.jsp"%>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>

</head>
<body>
	<div align="center">
		<span class="badge badge-secondary">Paros por concepto diarios</span>
	</div>
	<br>
	<div align="center" class="container">
	    <div class="row">
	    	<div class="col-md-2"></div>
	        <div class="col-md-2">Fecha yyyy/mm/dd</div>
	        <div class="col-md-2">
				<div class="container">
   					<div class="row">
			            <div class="form-group">
			                <div class="input-group date" id="datetimepicker4" data-target-input="nearest">
			                    <input id="dtp1"class="form-control datetimepicker-input" data-target="#datetimepicker4" value="${fecha_ini}"/>
			                    <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
			                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
			                    </div>
			                </div>
			            </div>
				        <script type="text/javascript">
				            $(function () {
				                $('#datetimepicker4').datetimepicker({
				                    format: 'YYYY/MM/DD'
				                });
				            });
				        </script>
    				</div>
				</div>
			</div>
	        <div class="col-md-2">
	        	<button type="button" class="btn btn-outline-primary" onclick="FBuscar()">
				<i class="fa fa-search"></i>
				Buscar
				</button></div>
	        <div class="col-md-2">
	        	<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla1')">
				<i class="fa fa-print"></i>
				Imprimir
				</button>
			</div>
	    </div>
    </div>
	<br>
	<div id="tabla1" class = "table-responsive-xl container">
	<table class="table table-hover table-bordered text-center small">
			<tr>
				<th>Fecha</th>
				<th>Concepto</th>
				<th>Flexográfica</th>
				<th>Flexotroqueladora</th>
				<th>Troqueladora 1</th>
				<th>Troqueladora 2</th>
				<th>Troqueladora 3</th>
			</tr>
			<c:forEach var="item" items="${reporte}">
				<tr>
					<td>${item.fecha}</td>
					<td>${item.motivo}</td>
					<td>${item.flexografica}</td>
					<td>${item.flexotroqueladora}</td>
					<td>${item.troqueladora1}</td>
					<td>${item.troqueladora2}</td>
					<td>${item.troqueladora3}</td>
				</tr>
				<c:set var="flexoGraficaT" value="${flexoGraficaT = flexoGraficaT + item.flexografica}"/>
				<c:set var="flexotroqueladoraT" value="${flexotroqueladoraT = flexotroqueladoraT + item.flexotroqueladora}"/>
				<c:set var="troqueladora1T" value="${troqueladora1T = troqueladora1T + item.troqueladora1}"/>
				<c:set var="troqueladora2T" value="${troqueladora2T = troqueladora2T + item.troqueladora2}"/>
				<c:set var="troqueladora3T" value="${troqueladora3T = troqueladora3T + item.troqueladora3}"/>
			</c:forEach>
			<tr>
				<th align="right" colspan="2">Total</th>
				<td>${flexoGraficaT}</td>
				<td>${flexotroqueladoraT}</td>
				<td>${troqueladora1T}</td>
				<td>${troqueladora2T}</td>
				<td>${troqueladora3T}</td>
			</tr>
		</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>