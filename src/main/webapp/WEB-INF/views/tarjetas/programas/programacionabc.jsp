<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registro de programas</title>
<%@include file="../../appconfig/authheader2.jsp"%>
<script type="text/javascript">
function ListaTFs()
{
	var pedido = $("#TPedido").val();
	
	$("#STarjetasSAP" ).prop( "disabled", true );
	var opciones = "";
	if(pedido != "")
	{
	$.ajax({
		url: '<c:url value="/programas/programacion/buscartarjetas"/>?pedido='+pedido,
		beforeSend: function(xhr) {
								$("#mensaje").text("Buscar tarjetas del pedido").removeClass().addClass("alert alert-info");
					        },	
        success : function(data) {
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
        	$("#STarjetasSAP" ).empty();
			opciones = "";
        	opciones = opciones + "<option value=''> - - - </option>";
        	$.each(jQuery.parseJSON(data),function(index, value){
        		opciones = opciones + "<option value='"+value+ "'>"+value+ "</option>";
        	});
        	$( "#STarjetasSAP" ).append(opciones);
        	$("#mensaje").text("").removeClass();
        },
        error: function(xhr, status, error) {
			  alert(xhr.responseText);
			  $("#mensaje").text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
		  }
	 });
	}
	$("#STarjetasSAP" ).prop( "disabled", false );
}
function FSelectTFP()
{	//buscarpedido
	//$("#TTFPrograma" ).val($("#STarjetasSAP").val());
	var cant_prog_cap = ($("#TCantProgCant").val() == "" ? 0.0 : $("#TCantProgCant").val())
	$.ajax({
		url: '<c:url value="/programas/programacion/buscarpedido"/>?pedido='+$("#TPedido").val()+'&tfsap='+$("#STarjetasSAP").val()+'&cant_prog_cap='+cant_prog_cap+'&id='+$("#TId").val(), 
		beforeSend: function(xhr) {
								$("#mensaje").text("Buscar pedido").removeClass().addClass("alert alert-info");
					        },	
        success : function(data) {
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
        	
        	var obj = JSON.parse(data);

        	if(obj != null)
        	{
        		$("#TTFPrograma" ).val(obj.map.tf);
        		$("#TSimbolo" ).val(obj.map.codigoarticulo);
        		$("#TDescripcion" ).val(obj.map.descripcion);
        		$("#TCantSol" ).val(obj.map.cantidad);
        		$("#TCantAcum" ).val(obj.map.cant_acum);
        		$("#TCantPend" ).val(obj.map.cant_pend);
        	}
        	
        	$("#mensaje").text("").removeClass();
        },
        error: function(xhr, status, error) {
			  alert(xhr.responseText);
			  $("#mensaje").text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
		  }
	 });
}
function FLimpiar()
{
	window.location.replace('<c:url value="/programas/programacion/programacionabc"/>');
}
function FBuscar()
{
	var isDisabled = $("#BGrabar").prop("disabled");
	$("#TPedido").removeClass().addClass("border border-danger").focus();
	$("#STarjetasSAP").removeClass().addClass("border border-danger");
	$("#TTFPrograma").removeClass().addClass("border border-danger");
	$("#TPrograma").removeClass().addClass("border border-danger");
	if(isDisabled)
	{
		$.ajax({
			url: '<c:url value="/programas/programacion/buscarprogramasreg"/>?pedido='+$("#TPedido").val()+'&tfsap='+$("#STarjetasSAP").val()+'&tfprog='+$("#TTFPrograma").val()+'&programa='+$("#TPrograma").val(), 
			beforeSend: function(xhr) {
									$("#mensaje").text("Buscando programas").removeClass().addClass("alert alert-info");
						        },	
	        success : function(data) {
	        	if (data.search(/Login page/i) != -1) {
	    			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }
	        	var nuevaFila   = '';
	        	$.each(jQuery.parseJSON(data),function(index, value){
	        		nuevaFila   = nuevaFila + '<tr>';
	        		nuevaFila   = nuevaFila + '<td><a href="javascript:FBuscarPrograma('+value.map.id+')">'+value.map.id+'</a></td>';
	        		nuevaFila   = nuevaFila + '<td>'+value.map.pedido+'</td>';
	        		nuevaFila   = nuevaFila + '<td>'+value.map.tf_sap+'</td>';
	        		nuevaFila   = nuevaFila + '<td>'+value.map.tf_programa+'</td>';
	        		nuevaFila   = nuevaFila + '<td>'+value.map.programa+'</td>';
	        		nuevaFila   = nuevaFila + '</tr>';
	        	});
	        	document.getElementById("ProgramasBody").innerHTML = nuevaFila;
	        	$("#mensaje").text("").removeClass();
	        },
	        error: function(xhr, status, error) {
				  alert(xhr.responseText);
				  $("#mensaje").text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  }
		 });
		
		$('#BuscarProg').modal('show'); 
	}
	
	$("#BGrabar").prop("disabled", true);
}
function FBuscarPrograma(id)
{
	window.location.replace('<c:url value="/programas/programacion/programacionabc"/>?id='+id);
}
</script>
</head>
<body>
<div class="row">
	<div class="badge badge-primary col-12">Registro de programas</div>
</div>
<form:form method="POST" modelAttribute="Programa" class="form-horizontal" autocomplete="off">
<div class="container">
<div class="row small">
	<div class="col-lg border border-secondary">
		Pedido
	</div>
	<div class="col-lg border border-secondary">
		<form:input id="TId" type="hidden" path="id"/>
		<form:input type="text" path="pedido" id="TPedido" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" onkeyup="ListaTFs()"/>
		<div class="has-error">
			<form:errors path="pedido" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Tarjeta SAP
	</div>
	<div class="col-lg border border-secondary">
		<form:select Onchange="FSelectTFP()" path="tf_sap" id = "STarjetasSAP" multiple="false" class="border border-primary">
			<form:option value=""> - - - </form:option>
			<c:forEach var="tarjeta" items="${Tarjetas}">
				<form:option value="${tarjeta}"><c:out value="${tarjeta}"/></form:option>
			</c:forEach>
		</form:select>
		<div class="has-error">
			<form:errors path="tf_sap" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Tarjeta programa
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="tf_programa" id="TTFPrograma" class="border border-primary" size="9" maxlength="15" onkeypress="return SinCaracteresEspeciales(event)"/>
		<div class="has-error">
			<form:errors path="tf_programa" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Programa
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="programa" id="TPrograma" class="border border-primary" size="9" maxlength="20" onkeypress="return SinCaracteresEspeciales(event)"/>
		<div class="has-error">
			<form:errors path="programa" class="badge badge-danger small"/>
		</div>
	</div>
</div>
<div class="row small">
	<div class="col-lg border border-secondary">
		Símbolo
	</div>
	<div class="col-lg border border-secondary">
		<input type="text" id="TSimbolo" class="border border-secondary" size="9" readonly="true" value="${simbolo}"/>
	</div>
	<div class="col-lg border border-secondary">
		Descripción
	</div>
	<div class="col-lg border border-secondary">
		<input type="text" id="TDescripcion" class="border border-secondary" size="40" readonly="true" value="${desc_simbolo}"/>
	</div>
</div>
<div class="row small">
	<div class="col-lg border border-secondary">
		Cant solicitada
	</div>
	<div class="col-lg border border-secondary">
		<input type="text" id="TCantSol" class="border border-secondary" size="9" readonly="true" value="${cant_sol}"/>
	</div>
	<div class="col-lg border border-secondary">
		Cant acumulada
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" id="TCantAcum" path="cant_acumulada" class="border border-secondary" size="9" readonly="true"/>
		<div class="has-error">
			<form:errors path="cant_acumulada" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Cant programada
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="cant_programada" id="TCantProgCant" class="border border-primary" size="9" maxlength="8" onkeyup="FSelectTFP()" onkeypress="return Enteros(event)"/>
		<div class="has-error">
			<form:errors path="cant_programada" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Cant pendiente
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" id="TCantPend" path="cant_pendiente" class="border border-secondary" size="9" readonly="true"/>
		<div class="has-error">
			<form:errors path="cant_pendiente" class="badge badge-danger small"/>
		</div>
	</div>
</div>
<div class="row small">
	<div class="col-lg border border-secondary">
		Observaciones
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="observaciones" class="border border-primary" size="50" maxlength="100" onkeypress="return SinCaracteresEspeciales(event)"/>
		<div class="has-error">
			<form:errors path="observaciones" class="badge badge-danger small"/>
		</div>
	</div>
</div>
<div align="center">
	<form:button id="BGrabar" class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button>
	<button type="button" onClick="FLimpiar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></button>
	<button type="button" onClick="FBuscar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></button>
</div>
</div>
<c:if test="${error != null}">
	<div class="alert alert-warning">
		<p>${error}</p>
	</div>
</c:if>
<c:if test="${mensaje != null}">
	<div class="alert alert-success">
		<p>${mensaje}</p>
	</div>
</c:if>
<div id="mensaje"></div>
<div class="modal fade bd-example-modal-lg" id="BuscarProg" tabindex="-1" role="dialog" aria-labelledby="BuscarProgLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header alert alert-info">
        <h5 class="modal-title">PROGRAMAS</h5>
      </div>
      <div class="modal-body">
       	<table class="table table-sm table-bordered table-hover">
       		<thead>
       			<tr>
       				<th>Id</th>
       				<th>Pedido</th>
       				<th>Tarjeta SAP</th>
       				<th>Tarjeta Programa</th>
       				<th>Programa</th>
       			</tr>
       		</thead>
       		<tbody id="ProgramasBody">
       			
       		</tbody>
       	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>	

</form:form>
<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>