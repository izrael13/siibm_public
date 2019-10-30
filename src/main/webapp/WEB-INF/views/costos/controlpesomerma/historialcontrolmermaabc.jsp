<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Historial Control de peso merma</title>
<script>
$(document).ready(function() {
	$('#SEmp').selectize({
	    create: true,
	    sortField: 'text'
	});
});
function FBuscar()
{
	window.location.replace('<c:url value="/costos/controlpesomerma/historialcontrolmermaabc" />?pedido='+$( "#TPedido" ).val()+'&idEmp='+($("#SEmp").val() == "" ? 0 : $("#SEmp").val())+'&idTara='+$( "#STaras" ).val()+'&fecha='+$( "#TFEcha" ).val());
}
function FImprimir(id)
{
	var redirectWindow = window.open('<c:url value="/costos/controlpesomerma/imprimirrep"/>?id='+id, "Reporte merma");
	redirectWindow.replace;
}
</script>
</head>
<body>
<br>

	<div class="col-sm">
		<div class="badge badge-info col-12">Búsqueda</div>
		<div class="row small">
			<div class="col font-weight-bold">Taras:
				<select id="STaras" class="border border-primary">
					<option value="0">Seleccione tara</option>
					<c:forEach var="tara" items="${ListaTaras}">
						<option value="${tara.id}"><c:out value="${tara.descripcion} - ${tara.pesokg}"/></option>
					</c:forEach>
				</select>
			</div>			
			<div class="col font-weight-bold">Pedido:
				<input id="TPedido" class="border border-primary" size="10" maxlength="15" type="text" onkeypress="return SinCaracteresEspeciales(event)"/>
			</div>			
			<div class="col font-weight-bold">Empacador:
				<select id="SEmp" class="border border-primary">
					<option value=""></option>
					<c:forEach var="emp" items="${ListaEmp}">
						<option value="${emp.id}"><c:out value="${emp.firstName} ${emp.lastName}"/></option>
					</c:forEach>
				</select>
			</div>
			
			<div class="col font-weight-bold">Fecha:
				<div class="">
				<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
                    <input id="TFEcha" onkeypress="return false" data-target="#datetimepicker4" placeholder="yyyy-mm-dd" class="border border-primary"/>
                    <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
	            </div>
	            </div>
				<script type="text/javascript">
		            $(function () {
		                $('#datetimepicker4').datetimepicker({
		                    format: 'YYYY-MM-DD'
		                });
		            });
		        </script>
			</div>	
			<div class="col font-weight-bold"><a href="javascript:FBuscar()" class="btn btn-outline-primary"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
			
		</div>
	</div>	
	<div class="col-sm">
		<div class="badge badge-info col-12">Historial Control de peso merma</div>
	<div class="row small">
		<div class="col font-weight-bold">Folio</div>
		<div class="col font-weight-bold">Pedido</div>
		<div class="col font-weight-bold">Empacador</div>
		<div class="col font-weight-bold">Tara</div>
		<div class="col font-weight-bold">Peso tara</div>
		<div class="col font-weight-bold">Peso total</div>
		<div class="col font-weight-bold">Peso real</div>
		<div class="col font-weight-bold">Fecha registro</div>
		<div class="col font-weight-bold">Comentarios</div>
	</div>
	<c:forEach var="item" items="${ListaControlPeso}" varStatus="counter">
		<div class="row small border">
			<div class="col"><a href="javascript:FImprimir(${item['id']})">${item['id']}</a></div>
			<div class="col">${item['pedido']}</div>
			<div class="col">${item['usuario_empacador']}</div>
			<div class="col">${item['idtara']}</div>
			<div class="col">${item['pesokg_tara']}</div>
			<div class="col">${item['pesokg_total']}</div>
			<div class="col">${item['pesokg_real']}</div>
			<div class="col">${item['fecha_registro']}</div>
			<div class="col">${item['comentarios']}</div>
		</div>
	</c:forEach>
		</div>
</body>
</html>