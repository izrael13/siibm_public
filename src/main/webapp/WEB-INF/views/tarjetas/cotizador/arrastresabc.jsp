<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<script src="<c:url value="/static/js/sistemas_tarjetas/cotizador.js" />"></script>
<title>Arrastres</title>
<script>
$(document).ready(function() {
	if('${cotizadordatabean.cotizador.id}' > 0)
	{
		if(('${cotizadordatabean.cotizador.usuario_envia_arrmues}' > 0 && '${cotizadordatabean.cotizador.fecha_envia_arrmues}' != '')||
		   ('${cotizadordatabean.cotizador.usuario_cancel}' > 0  && '${cotizadordatabean.cotizador.fecha_cancel}' != ''))
			{
				$("#SClientes option:not(:selected)").prop("disabled", true);
				$("#direcciones option:not(:selected)").prop("disabled", true);
				$("#TSimbolo").attr("readonly","readonly");
				$("#SNumTintas option:not(:selected)").prop("disabled", true);
				$("#SColor1 option:not(:selected)").prop("disabled", true);
				$("#SColor2 option:not(:selected)").prop("disabled", true);
				$("#SColor3 option:not(:selected)").prop("disabled", true);
				$("#SColor4 option:not(:selected)").prop("disabled", true);
				$("#SColor5 option:not(:selected)").prop("disabled", true);
				$("#SColor6 option:not(:selected)").prop("disabled", true);
				$("#SColor7 option:not(:selected)").prop("disabled", true);
				$("#BGrabar").prop('disabled',true);
				$("#BEnvVtas").prop('disabled',true);
				$("#BCancel").prop('disabled',true);
				$("#TObs").attr("readonly","readonly");
				$("#TPzasxjgo").attr("readonly","readonly");
			}
	}
	
});

function FBuscarDirecciones()
{
	var cardcode = document.getElementById("SClientes").value;
	var opciones = "";
	$.ajax({
		url: '<c:url value="/cotizador/vendedor/buscardirecciones"/>?cardcode='+cardcode,
		beforeSend: function(xhr) {
							  $("#imgload").show();
							  $("#TFlete").val(0);
							  $("#DContacto").text("");
				        	  $("#DTelefono").text("");
				        	  $("#DEmail").text("");
							  $("#direcciones" ).empty();
							  $("#mensajes" ).text("").removeClass();
					        },	
        success : function(data) {
        		if (data.search(/Login page/i) != -1) {
        			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }
	        	opciones = opciones + "<option value='-1'> - - - </option>";
	        	$.each(jQuery.parseJSON(data),function(index, value){
	        		opciones = opciones + "<option value='"+value.linenum + "'>"+value.address +" - "+value.direccion + "</option>";
	        	});
	        	
	        	$( "#direcciones" ).append(opciones);
	        	$( "#imgload").hide();
        },
        error: function(xhr, status, error) {
        	  $( "#direcciones" ).empty();
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
}
function FBuscarInfoDir()
{
	var cardcode = document.getElementById("SClientes").value;
	var linenum = document.getElementById("direcciones").value;
	
	$.ajax({
		url: '<c:url value="/cotizador/vendedor/buscarinfodir"/>?cardcode='+cardcode+'&linenum='+linenum,
		beforeSend: function(xhr) {
							  $("#imgload").show();
							  $("#TFlete").val(0);
							  $("#DContacto").text("");
				        	  $("#DTelefono").text("");
				        	  $("#DEmail").text("");
							  $("#mensajes" ).text("").removeClass();
					        },	
        success : function(data) {
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
        	$.each(jQuery.parseJSON(data),function(index, value){
        		$("#DContacto").text(value.contacto);
        		$("#DTelefono").text(value.telefono);
        		$("#DEmail").text(value.email);
        		 
        	});
        	
        	$("#imgload").hide();
        },
        error: function(xhr, status, error) {
        	  $("#TFlete").val(0);
        	  $("#DContacto").text("");
        	  $("#DTelefono").text("");
        	  $("#DEmail").text("");
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
	
}
function FLimpar()
{
	window.location.replace('<c:url value="/cotizador/vendedor/arrastresabc"/>');
}
function FCancelar()
{
	var idcot = +$("#TId").val();
	var iddet = $("#TIdDet").val();
	if(idcot > 0)
	{
		var http = new XMLHttpRequest();
		var url = '<c:url value="/cotizador/vendedor/cancelararrastre"/>';
		var params = 'idcot='+idcot;
		
		$("#DivMensaje").text("Procesando petición. Por favor espere...").removeClass().addClass("alert alert-danger");
		
		http.open('POST', url, true);
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
		http.onreadystatechange = function() {//Call a function when the state changes.
		    if(http.readyState == 4 && http.status == 200) 
		    {
		    	if (http.responseText.search(/Login page/i) != -1) {
		    		alert("La sessión ha expirado, Por favor vuelva a intentarlo.");
	    			window.location.replace('<c:url value="/login?expired"/>');
		    	}
	    		else{
	    			if(http.responseText === 'OK')
	    			{
	    				alert("ARRASTRE CANCELADO!!!!!!");
			    		window.location.replace('<c:url value="/cotizador/vendedor/arrastresabc"/>?id=0'+'&iddet='+0);
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/vendedor/arrastresabc"/>?id='+idcot+'&iddet='+iddet);
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
		    		window.location.replace('<c:url value="/cotizador/vendedor/arrastresabc"/>?id='+idcot+'&iddet='+iddet);
		    	}
		    }
		    
		}
		http.send(encodeURI(params));
	}
}
function FBuscar()
{
	if($( "#TId" ).val() =="")
	{
		$( "#TId" ).val("0");
	}
	
	var isDisabled = $("#BGrabar").prop('disabled');
	var isReadOnly = $("#TId").prop('readonly');

	$( "#TId" ).prop( "readonly", false );
	if(($( "#TId" ).val() > 0 || $( "#SClientes" ).val() != "0") && isDisabled == true && isReadOnly == false)
	{
		popupwindow('<c:url value="/cotizador/vendedor/arrastrebusqueda" />?id='+$( "#TId" ).val()+'&cardcode='+$( "#SClientes" ).val(),'Detalle de viaje',800,1000);
	}

		$( "#BGrabar" ).prop( "disabled", true );
		$( "#TId").removeClass().addClass("border border-danger");
		$( "#SClientes").removeClass().addClass("border border-danger");
		$( "#TId" ).focus();
	
}
function FBuscarxId(id,iddet)
{
	window.location.replace('<c:url value="/cotizador/vendedor/arrastresabc" />?id='+id+'&iddet='+iddet);
}
function FEnviar()
{
	var idcot = +$("#TId").val();
	var iddet = $("#TIdDet").val();
	if(idcot > 0)
	{
		var http = new XMLHttpRequest();
		var url = '<c:url value="/cotizador/vendedor/enviararrastre"/>';
		var params = 'idcot='+idcot;
		
		$("#DivMensaje").text("Procesando petición. Por favor espere...");
    	$("#DivMensaje").removeClass().addClass("alert alert-danger");
		
		http.open('POST', url, true);
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
		http.onreadystatechange = function() {//Call a function when the state changes.
		    if(http.readyState == 4 && http.status == 200) 
		    {
		    	if (http.responseText.search(/Login page/i) != -1) {
		    		alert("La sessión ha expirado, Por favor vuelva a intentarlo.");
	    			window.location.replace('<c:url value="/login?expired"/>');
		    	}
	    		else{
	    			if(http.responseText === 'OK')
	    			{
	    				alert("Exitoso envío a autorización de ventas.");
			    		window.location.replace('<c:url value="/cotizador/vendedor/arrastresabc"/>?id=0'+'&iddet='+0);
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/vendedor/arrastresabc"/>?id='+idcot+'&iddet='+iddet);
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
		    		window.location.replace('<c:url value="/cotizador/vendedor/arrastresabc"/>?id='+idcot+'&iddet='+iddet);
		    	}
		    }
		    
		}
		http.send(encodeURI(params));
	}
}
</script>
</head>
<body>
	<div align="center">
		<span class="badge badge-secondary">Arrastres</span>
	</div>
	<div align="center">
		 	<span id="imgload" style='display: none;'><img width="20px" height="20px" src='<c:url value="/static/img/sun_watch.gif"/>' /></span>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<div class = "container-fluid">
	 <form:form id="form" method="POST" modelAttribute="cotizadordatabean" class="form-horizontal" autocomplete="off">
		<div class="row">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="badge badge-primary col-12">
				 	Datos del cliente
				 </div>
			 </div>
			 <div class="col-12 small"><!-- mx-auto  para centrar en pantalla -->
			 	<div class="row border border-right">
					<div class="col col-lg-1">Folio</div>
					<div class="col col-lg-1">
						<form:input class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)" id="TId" readonly="true" type="text" 
						 value="${empty cotizadordatabean.cotizador.id ? 0 : cotizadordatabean.cotizador.id}" path="cotizador.id"/>
					</div>
					<div class="col col-lg-1">Cliente</div>
					<div class="col col-lg-7">
						<form:select Onchange="FBuscarDirecciones()" id="SClientes" path="cotizador.cardcode" multiple="false" class="border border-primary">
							<form:option value="0">Seleccione un cliente</form:option>
							<c:forEach var="cte" items="${clientes}">
								<form:option value="${cte.cardcode}"><c:out value="${cte.cardname}"/></form:option>
							</c:forEach>
						</form:select>
						<div class="has-error">
							<form:errors path="cotizador.cardcode" class="badge badge-danger small"/>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12 small"><!-- mx-auto  para centrar en pantalla -->
			<div class="row border border-right">
				<div class="col col-lg-1">Dirección</div>
				<div class="col col-lg-10">
					<div class ="input-group">
						<form:select Onchange="FBuscarInfoDir()" path="cotizador.linenum_dir_entrega" id = "direcciones" multiple="false" class="border border-primary">
							<form:option value="-1"> - - - </form:option>
							<c:forEach var="dir" items="${direcciones}">
								<form:option value="${dir.linenum}"><c:out value="${dir.address} - ${dir.direccion}"/></form:option>
							</c:forEach>
						</form:select>
						<div class="has-error">
							<form:errors path="cotizador.linenum_dir_entrega" class="badge badge-danger small"/>
						</div>
					</div>
				</div>
				<div class="col col-lg-1"><button type="button" data-toggle="modal" data-target="#AutModal" class="float-right btn btn-outline-primary btn-sm"><i class="fa fa-info-circle" aria-hidden="true"> Info</i></button></div>
			</div>
		</div>
		<div class="col-12 small"><!-- mx-auto  para centrar en pantalla -->
			<div class="row border border-right">
				<div class="col col-lg-4">Contacto: <span id="DContacto">${direccionSelect[0].contacto}</span></div>
				<div class="col col-lg-4">Teléfono: <span id="DTelefono">${direccionSelect[0].telefono}</span></div>
				<div class="col col-lg-4">Email: <span id="DEmail">${direccionSelect[0].email}</span></div>
			</div>
		</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			 <div class="row badge badge-info col-12">
				 	Detalles de caja
			 </div>
		 </div>
		<div class="col-12 small"><!-- mx-auto  para centrar en pantalla -->
			<div class="row border border-right">
				<div class="col col-lg-1">
					<form:input id="TIdCotDet" class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)" readonly="true" type="hidden"  
					 value="${empty cotizadordatabean.cotizador_detalles.idcotizacion ? 0 : cotizadordatabean.cotizador_detalles.idcotizacion}" path="cotizador_detalles.idcotizacion"/>
					<form:input id="TIdDet" class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)" readonly="true" type="text" 
					 value="${empty cotizadordatabean.cotizador_detalles.iddetalle ? 0 : cotizadordatabean.cotizador_detalles.iddetalle}" path="cotizador_detalles.iddetalle"/>
				</div>
				<div class="col col-lg-1">Símbolo</div>
				<div class="col col-lg-3">
					<form:input id="TSimbolo" type="text" size="40" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="cotizador_detalles.simbolo" class="border border-primary"/>
					<div class="has-error">
						<form:errors path="cotizador_detalles.simbolo" class="badge badge-danger small"/>
					</div>
				</div>
				<div class="col-sm-1">Observaciones</div>
				<div class="col-sm-3">
					<form:input id="TObs" type="text" size="40" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="cotizador_detalles.observaciones_vendedor" class="border border-primary"/>
					<div class="has-error">
						<form:errors path="cotizador_detalles.observaciones_vendedor" class="badge badge-danger small"/>
					</div>
				</div>
				<div class="col-sm-1">Cantidad</div>
				<div class="col col-lg-1">
					<form:input id="TPzasxjgo" size="10" maxlength="8" type="text" path="cotizador_detalles.cantidad_pedido_mes" onkeypress="return Enteros(event);" class="border border-primary"/>
					<div class="has-error">
						<form:errors path="cotizador_detalles.cantidad_pedido_mes" class="badge badge-danger small"/>
					</div>
				</div>
			</div>
			<div class="row border border-right">
			<div class="col col-lg-1">N Tintas</div>
			<div class="col col-lg-1">
				<form:select id="SNumTintas" onChange="FColores()" path="cotizador_detalles.num_tintas" multiple="false" class="border border-primary">
					<form:option value="0">0</form:option>
					<form:option value="1">1</form:option>
					<form:option value="2">2</form:option>
					<form:option value="3">3</form:option>
					<form:option value="4">4</form:option>
					<form:option value="5">5</form:option>
					<form:option value="6">6</form:option>
					<form:option value="7">7</form:option>
				</form:select>
				<div class="has-error">
					<form:errors path="cotizador_detalles.num_tintas" class="badge badge-danger small"/>
				</div>
			</div>
			<div class="col col-lg-10">
				<form:select id="SColor1" path="cotizador_detalles.color1" multiple="false" class="border border-primary"
				style="${!empty cotizadordatabean.cotizador_detalles.color1 ? (cotizadordatabean.cotizador_detalles.color1 > 0 ? 'visibility:visible' : 'visibility:hidden') : 'visibility:hidden'}">
					<form:option value="">COLOR1</form:option>
					<c:forEach var="col" items="${colores}">
						<form:option style="background:#${col.color_est}" value="${col.id}"><c:out value="${col.color}"/> </form:option>
					</c:forEach>
				</form:select>
			
				<form:select id="SColor2" path="cotizador_detalles.color2" multiple="false" class="border border-primary"
				style="${!empty cotizadordatabean.cotizador_detalles.color2 ? (cotizadordatabean.cotizador_detalles.color2 > 0 ? 'visibility:visible' : 'visibility:hidden') : 'visibility:hidden'}">
					<form:option value="">COLOR2</form:option>
					<c:forEach var="col" items="${colores}">
						<form:option style="background:#${col.color_est}" value="${col.id}"><c:out value="${col.color}"/> </form:option>
					</c:forEach>
				</form:select>

				<form:select id="SColor3" path="cotizador_detalles.color3" multiple="false" class="border border-primary"
				style="${!empty cotizadordatabean.cotizador_detalles.color3 ? (cotizadordatabean.cotizador_detalles.color3 > 0 ? 'visibility:visible' : 'visibility:hidden') : 'visibility:hidden'}">
					<form:option value="">COLOR3</form:option>
					<c:forEach var="col" items="${colores}">
						<form:option style="background:#${col.color_est}" value="${col.id}"><c:out value="${col.color}"/> </form:option>
					</c:forEach>
				</form:select>

				<form:select id="SColor4" path="cotizador_detalles.color4" multiple="false" class="border border-primary"
				style="${!empty cotizadordatabean.cotizador_detalles.color4 ? (cotizadordatabean.cotizador_detalles.color4 > 0 ? 'visibility:visible' : 'visibility:hidden') : 'visibility:hidden'}">
					<form:option value="">COLOR4</form:option>
					<c:forEach var="col" items="${colores}">
						<form:option style="background:#${col.color_est}" value="${col.id}"><c:out value="${col.color}"/> </form:option>
					</c:forEach>
				</form:select>

				<form:select id="SColor5" path="cotizador_detalles.color5" multiple="false" class="border border-primary"
				style="${!empty cotizadordatabean.cotizador_detalles.color5 ? (cotizadordatabean.cotizador_detalles.color5 > 0 ? 'visibility:visible' : 'visibility:hidden') : 'visibility:hidden'}">
					<form:option value="">COLOR5</form:option>
					<c:forEach var="col" items="${colores}">
						<form:option style="background:#${col.color_est}" value="${col.id}"><c:out value="${col.color}"/> </form:option>
					</c:forEach>
				</form:select>

				<form:select id="SColor6" path="cotizador_detalles.color6" multiple="false" class="border border-primary"
				style="${!empty cotizadordatabean.cotizador_detalles.color6 ? (cotizadordatabean.cotizador_detalles.color6 > 0 ? 'visibility:visible' : 'visibility:hidden') : 'visibility:hidden'}">
					<form:option value="">COLOR6</form:option>
					<c:forEach var="col" items="${colores}">
						<form:option style="background:#${col.color_est}" value="${col.id}"><c:out value="${col.color}"/> </form:option>
					</c:forEach>
				</form:select>

				<form:select id="SColor7" path="cotizador_detalles.color7" multiple="false" class="border border-primary"
				style="${!empty cotizadordatabean.cotizador_detalles.color7 ? (cotizadordatabean.cotizador_detalles.color7 > 0 ? 'visibility:visible' : 'visibility:hidden') : 'visibility:hidden'}">
					<form:option value="">COLOR7</form:option>
					<c:forEach var="col" items="${colores}">
						<form:option style="background:#${col.color_est}" value="${col.id}"><c:out value="${col.color}"/> </form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		</div>
		<div class="col-12 small">
			<div class="row border border-right">
				<div class="col col-lg-1">Tipo de papel</div>
				<div class="col col-lg-11">
					<form:select id="SResis" path="cotizador_detalles.idresistencia_barca" multiple="false" class="border border-primary">
					<form:option value="0">Seleccione resistencia</form:option>
					<c:forEach var="res" items="${resistencias}">
						<form:option value="${res.idresistencia}"><c:out value="${res.resistencia} Flauta:${res.corrugado} Papel:${res.color}"/></form:option>
					</c:forEach>
					</form:select>
					<div class="has-error">
						<form:errors path="cotizador_detalles.idresistencia_barca" class="badge badge-danger small"/>
					</div>
				</div>
			</div>
		</div>
		<div align="left" class = "container">
		<div class = "row" align="center">			
			<div class="col col-lg-2"><form:button id="BGrabar" class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
			<div class="col col-lg-2"><a href="javascript:FBuscar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
			<div class="col col-lg-2"><button type="button" onClick="FLimpar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></button></div>
			<div class="col col-lg-3"><button id="BEnvVtas" type="button" data-toggle="modal" data-target="#VtaModal" class="btn btn-outline-primary btn-sm"><i class="fa fa-paper-plane-o" aria-hidden="true"> Enviar autorización</i></button></div>
			<div class="col col-lg-2"><button id="BCancel" type="button" data-toggle="modal" data-target="#CancelModal" class="btn btn-outline-primary btn-sm"><i class="fa fa-times-circle-o" aria-hidden="true"> Cancelar</i></button></div>
		</div>
		</div>
		<form:input type="hidden" path="cotizador.idtiporequerimiento" value="3"/>
	</form:form>
	<div class="modal fade" id="CancelModal" tabindex="-1" role="dialog" aria-labelledby="CancelModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title">CANCELAR COTIZACIÓN</h5>
	      </div>
	      <div class="modal-body alert alert-danger">
	        ¡¡¡ATENCIÓN!!! ¿Desea CANCELAR esta cotización?
	      </div>
	      <div id="DivMensaje" class="modal-footer">
	        <button type="button" class="btn btn-primary" onClick="FCancelar()">Cancelar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="modal fade" id="VtaModal" tabindex="-1" role="dialog" aria-labelledby="VtaModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title">Enviar a autorización</h5>
	      </div>
	      <div class="modal-body alert alert-warning">
	        ¡¡¡ATENCIÓN!!! ¿Desea enviar esta cotización a autorización?
	      </div>
	      <div id="DivMensaje" class="modal-footer">
	        <button type="button" class="btn btn-primary" onClick="FEnviar()">Enviar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade bd-example-modal-xl" id="AutModal" tabindex="-1" role="dialog" aria-labelledby="AutModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-xl">
	    <div class="modal-content">
	   	  <div class="modal-header alert alert-info">
	        <h5 class="modal-title" id="ReqModal">Información adicional</h5>
	      </div>
	      <div class="container">
		  <div class="row">
		    <div class="col-sm">
		      Fecha creación:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_insert}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha actualización:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_update}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Fecha envío:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_arrmues}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha asigna:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_asigna_arrastre}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		   <div class="row">
		    <div class="col-sm">
		      Fecha rechazo:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_arrastre}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha liberación:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_libera_arrastre}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Fecha cancelación:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_cancel}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Comentarios:
		    </div>
		    <div class="col-sm">
		      ${cotizadordatabean.cotizador.observaciones_arrastre}
		    </div>
		  </div>		  
		  <div class="modal-footer">
	        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
		</div>
	    </div>
	  </div>
	</div>
	
	</div>
</body>
</html>