<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Control de peso merma</title>
<script>
function CalcularPeso()
{
	//alert($("#STaras").val());
	//alert($("#TPesoTotal").val());
	//alert(document.getElementById("SEstado").value);
	var id_tara = $("#STaras").val();
	
	$.ajax({
		url: '<c:url value="/costos/controlpesomerma/calcularpeso"/>?id_tara='+id_tara,
		beforeSend: function(xhr) {
							  $( "#imgload").show();
					        },	
        success : function(data) {
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
        	
        	var obj = JSON.parse(data);
        	
        	$("#TPesoReal").val(obj.peso_real);
        	$("#TPesoTotal").val(obj.pesototal);
        	$( "#imgload").hide();
        },
        error: function(xhr, status, error) {
        	  $("#imgload").hide();
			  $("#mensajes").text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
		  }
	 });
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
	<div align="center">
		<span class="badge badge-secondary">Control de peso merma</span>
	</div>
	<form:form method="POST" modelAttribute="control_peso_merma" class="form-horizontal" autocomplete="off">
	<div align="left" class = "container">
		<div class="row small">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				<div class="row border border-right">
					<div class="col-sm-2">Folio: <form:input class="border border-primary" size="5" maxlength="9" readonly="true" type="text" path="id" value="${empty id ? 0 : id}"/></div>
					<div class="col-sm-2">Pedido: <form:input class="border border-primary" size="10" maxlength="15" type="text" path="pedido" onkeypress="return SinCaracteresEspeciales(event)"/>
						<div class="has-error">
							<form:errors path="pedido" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-2">Tara:
						<form:select id="STaras" path="idtara" multiple="false" class="border border-primary" onchange="CalcularPeso()">
							<form:option value="0">Seleccione tara</form:option>
							<c:forEach var="tara" items="${ListaTaras}">
								<form:option value="${tara.id}"><c:out value="${tara.descripcion} - ${tara.pesokg}"/></form:option>
							</c:forEach>
						</form:select>
						<div class="has-error">
							<form:errors path="idtara" class="badge badge-danger small"/>
						</div> 
					</div>
					<div class="col-sm-2">Peso total: <form:input id="TPesoTotal" class="border border-primary" size="10" maxlength="15" type="text" path="pesokg_total"  readonly="true"/>
						<div class="has-error">
							<form:errors path="pesokg_total" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-2">Peso real: <form:input id="TPesoReal" class="border border-primary" size="10" maxlength="15" type="text" path="pesokg_real" readonly="true"/>
						<div class="has-error">
							<form:errors path="pesokg_real" class="badge badge-danger small"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row small">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				<div class="row border border-right">
					<div class="col-sm-10">Comentarios: 
						<form:input class="border border-primary" size="100" maxlength="100" type="text" path="comentarios" onkeypress="return SinCaracteresEspeciales(event)"/>
					</div>
					<div class="col-sm-2"><form:button type="button" onClick="CalcularPeso()" class="btn btn-outline-primary"><i class="fa fa-floppy-o" aria-hidden="true"> Recargar</i></form:button></div>
				</div>
			</div>
		</div>		
	</div>
	<br>
	<div align="left" class = "container">
	<div id="imgload" align="center" style='display: none;'><img width="20px" height="20px" src='<c:url value="/static/img/sun_watch.gif"/>' /></div>
	<div class="row" align="center">
		<div class="col-sm-3"></div>
		<div class="col-sm-2"><form:button id="BGrabar" class="btn btn-outline-primary"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
	</div>
	</div>
			<br>
		<div class="col-sm">
 		<div class="badge badge-info col-12">Lista Capturas</div>
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
	</form:form>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>