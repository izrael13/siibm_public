<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"/>
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Tarjetas de Fabricación</title>
<script>
$(document).ready(function() {

	if(('${tarjeta_fabricacion.idcotizacion}' == "") || ('${tarjeta_fabricacion.fecha_aut_diseniador}' != "" && '${tarjeta_fabricacion.usuario_aut_diseniador}' > 0)
		|| ('${tarjeta_fabricacion.fecha_cancela}' != "" && '${tarjeta_fabricacion.usuario_cancela}' > 0))
	{		

			$("#BCancelar").prop("disabled", true );
			$("#BEnvAut").prop("disabled", true );
			$("#BGrabar").prop("disabled", true );
			$("#file").prop("disabled", true );
			$("#CCama").prop("disabled", true );
			var lista=document.getElementsByName("ABorrarImg");
			for(var i=0; i<lista.length; i++){
				$("#"+lista[i].id).prop("disabled", true );		
			}
	}
});

function FBuscar()
{
	if($( "#TFolioTF" ).val() =="")
		$( "#TFolioTF" ).val("");
	
	if($( "#TIdCot" ).val() =="")
		$( "#TIdCot" ).val("0");
	
	var isDisabled = $("#BGrabar").prop('disabled');
	
	$( "#TIdCot" ).prop( "readonly", false );
	$( "#TFolioTF" ).prop( "readonly", false );
	
	$( "#BGrabar" ).prop( "disabled", true );
	
	if(($( "#TIdCot" ).val() > 0 || $( "#TFolioTF" ).val() != ""))
		popupwindow('<c:url value="/tarjeta/ingenieria/tarjeta_fabricacion_buscar" />?idcot='+$( "#TIdCot" ).val()+'&foliotf='+$( "#TFolioTF" ).val(),'Búsqueda de tarjetas',800,1000);

		$( "#TIdCot").removeClass().addClass("border border-danger");
		$( "#TFolioTF").removeClass().addClass("border border-danger");
		$( "#TFolioTF" ).focus();
	
}
function FBuscarxFolio(folio)
{
	window.location.replace('<c:url value="/tarjeta/ingenieria/tarjeta_fabricacion" />?folio='+folio);
}
function FLimpiar()
{
	window.location.replace('<c:url value="/tarjeta/ingenieria/tarjeta_fabricacion" />');
}
function GeneraCarousel(r)
{
	var jsonEsp = JSON.parse(r);
	
	var nuevaFila = '';
	for( var i=0; i<jsonEsp.length; i++ ){
		if(i == (jsonEsp.length - 1))
			nuevaFila   = nuevaFila + ' <div class="carousel-item active"> ';
		else
			nuevaFila   = nuevaFila + ' <div class="carousel-item"> ';
			
		var ruta = "/barcasii/static/img_tarjetas/"+jsonEsp[i].nombre;
		var cama = "";
		jsonEsp[i].cama == true ? cama="CAMA" : ""; 
		nuevaFila   = nuevaFila + ' <img height="400" width="400" src="<c:url value="'+ruta+'"/>" alt="'+jsonEsp[i].nombre+'"> ';
		nuevaFila   = nuevaFila + ' <div class="carousel-caption"> ';
		nuevaFila   = nuevaFila + ' <h3>'+jsonEsp[i].nombre+'</h3> ';
		nuevaFila   = nuevaFila + ' <p>'+cama+'</p> ';
		nuevaFila   = nuevaFila + ' <p><a href="javascript:FBorrarImg('+jsonEsp[i].idcotizacion+','+jsonEsp[i].iddetalle+',\''+jsonEsp[i].nombre+'\')">Borrar</a></p> ';
		nuevaFila   = nuevaFila + ' </div> ';
		nuevaFila   = nuevaFila + ' </div> ';
	  }
	
	return nuevaFila;
}
function FAddImagen()
{
	var data = new FormData();
	jQuery.each(jQuery('#file')[0].files, function(i, file) {
		data.append('file', file);
	});
	data.append('folio_tarjeta', $( "#TFolioTF" ).val());
	data.append('idcotizacion', $( "#TIdCot" ).val());
	data.append('iddetalle', $( "#TIdDet" ).val());
	data.append('cama', $("#CCama").prop('checked'));
	
	$("#mensajes" ).text("Subiendo imagen.");
	$("#mensajes").removeClass().addClass("alert alert-info");
	
	$.ajax({
	    url: '<c:url value="/tarjeta/ingenieria/subir_imagen_tarjeta"/>',
	    data: data,
	    type : 'POST',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        before: function() {

  	    },
	    success: function(r) {
	    	
	    	if (r.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
	    	
	    	document.getElementById("DImg").innerHTML = "";	    	
    		document.getElementById("DImg").innerHTML = GeneraCarousel(r);
	    	
	    	$("#mensajes" ).text("Imagen subida OK");
			$("#mensajes").removeClass().addClass("alert alert-success");
	    },
	    error: function(xhr, status, error) {
	    	var err = xhr.responseText;
	    	
	    	$("#mensajes" ).text("Error: "+err.Message+" - "+error);
			$("#mensajes").removeClass().addClass("alert alert-danger");
	    }
	  }); 
	
}
function FBorrarImg(idcot,iddet,nombre)
{
	var r = confirm("¿Desea eliminar esta imagen: ? "+nombre);
	if (r == true) 
	{	 	
		var data = new FormData();
		data.append('idcotizacion', idcot);
		data.append('iddetalle', iddet);
		data.append('nombre', nombre);
		
		$("#mensajes" ).text("Eliminando imagen.");
		$("#mensajes").removeClass().addClass("alert alert-info");
		
		$.ajax({
		    url: '<c:url value="/tarjeta/ingenieria/borrar_imagen_tarjeta"/>',
		    data: data,
		    type : 'POST',
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false,
	        before: function() {
	
	  	    },
		    success: function(r) {
		    	
		    	if (r.search(/Login page/i) != -1) {
        			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }
		    	
		    	document.getElementById("DImg").innerHTML = "";	    	
	    		document.getElementById("DImg").innerHTML = GeneraCarousel(r);
		    	
		    	$("#mensajes" ).text("Imagen eliminada OK");
				$("#mensajes").removeClass().addClass("alert alert-success");
		    },
		    error: function(xhr, status, error) {
		    	var err = xhr.responseText;
		    	
		    	$("#mensajes" ).text("Error: "+err.Message+" - "+error);
				$("#mensajes").removeClass().addClass("alert alert-danger");
		    }
		  }); 
	}

}

function FEnviarAut()
{
	var data = new FormData();
	
	data.append('idcotizacion', $( "#TIdCot" ).val());
	data.append('iddetalle', $( "#TIdDet" ).val());
	data.append('folio_tarjeta', $( "#TFolioTF" ).val());
	
	$("#mensajes" ).text("Enviando tarjeta.");
	$("#mensajes").removeClass().addClass("alert alert-info");
	
	$.ajax({
	    url: '<c:url value="/tarjeta/ingenieria/enviar_tarjeta_aut"/>',
	    data: data,
	    type : 'POST',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        before: function() {

  	    },
	    success: function(r) {
	    	
	    	if (r.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }    	
	    	$("#mensajes" ).text("Tarjeta enviada OK");
			$("#mensajes").removeClass().addClass("alert alert-success");
			window.location.replace('<c:url value="/tarjeta/ingenieria/tarjeta_fabricacion" />');
	    },
	    error: function(xhr, status, error) {
	    	var err = xhr.responseText;
	    	
	    	$("#mensajes" ).text("Error: "+err.Message+" - "+error);
			$("#mensajes").removeClass().addClass("alert alert-danger");
	    }
	  }); 
}
function FCancelar()
{
	var data = new FormData();
	
	data.append('idcotizacion', $( "#TIdCot" ).val());
	data.append('iddetalle', $( "#TIdDet" ).val());
	data.append('folio_tarjeta', $( "#TFolioTF" ).val());
	data.append('coment_diseniador', $( "#TComDis" ).val());
	
	$("#mensajes" ).text("Cancelando tarjetas.");
	$("#mensajes").removeClass().addClass("alert alert-info");
	
	$.ajax({
	    url: '<c:url value="/tarjeta/ingenieria/cancelar_tarjetas"/>',
	    data: data,
	    type : 'POST',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        before: function() {

  	    },
	    success: function(r) {	    	
	    	if (r.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }	    	
	    	$("#mensajes" ).text("Tarjetas canceladas OK");
			$("#mensajes").removeClass().addClass("alert alert-success");
			window.location.replace('<c:url value="/tarjeta/ingenieria/tarjeta_fabricacion" />');
	    },
	    error: function(xhr, status, error) {
	    	var err = xhr.responseText;	    	
	    	$("#mensajes" ).text("Error: "+err.Message+" - "+error);
			$("#mensajes").removeClass().addClass("alert alert-danger");
	    }
	  }); 
}
</script>
</head>
<body>
<div class = "container-fluid">
 <form:form id="form" method="POST" modelAttribute="tarjeta_fabricacion" class="form-horizontal" autocomplete="off">
 	<div class="row">
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			 <div class="row ">
				 <div class="badge badge-primary col-12">
				 	Datos de la tarjeta (Ingeniería / Diseñador)
				 </div>
			 </div>
		 </div>
		 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small">
				<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
					<div class="row border border-right">
						<div class="col-sm-2">Folio tarjeta:
							<form:input id="TFolioTF" class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)"  readonly="true" type="text" path="folio_tarjeta"/>
						</div>
						<div class="col-sm-2">Folio cotización:
							<form:input id="TIdCot" class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)"  readonly="true" type="text" path="idcotizacion"/>
							<form:input id="TIdDet" class="border border-secondary" size="9" maxlength="8" type="hidden" path="iddetalle"/>
						</div>
						<div class="col-sm-2">Descripción Factura:
							<form:input class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)"  readonly="true" type="text" path="descripcion_factura"/>
						</div>
						<div class="col-sm-2">Número de partes:
							<form:select path="num_partes" multiple="false" class="border border-primary">
								<form:option value="1">1</form:option>
								<form:option value="2">2</form:option>
							</form:select>
						</div>
						<div class="col-sm-2">Piezas x largo:
							<form:input class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" type="text" path="pzasxlargo"/>
							<div class="has-error"><form:errors path="pzasxlargo" class="badge badge-danger small"/></div>
						</div>
						<div class="col-sm-2">Piezas x ancho:
							<form:input class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" type="text" path="pzasxancho"/>
							<div class="has-error"><form:errors path="pzasxancho" class="badge badge-danger small"/></div>
						</div>
					</div>
				</div>
			</div>
			<div class="row small">
				<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
					<div class="row border border-right">
						<div class="col-sm-2">Medida pliego:
							<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="medida_pliego"/>
							<div class="has-error"><form:errors path="medida_pliego" class="badge badge-danger small"/></div>
						</div>
						<div class="col-sm-2">Medidas internas:
							<form:input class="border border-primary" size="9" maxlength="20" onkeypress="return SinCaracteresEspeciales(event)" type="text" path="medidas_internas"/>
							<div class="has-error"><form:errors path="medidas_internas" class="badge badge-danger small"/></div>
						</div>
						<div class="col-sm-2">Cliché:
							<form:input class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" type="text" path="cliche"/>
							<div class="has-error"><form:errors path="cliche" class="badge badge-danger small"/></div>
						</div>
						<div class="col-sm-2">Costo cliché:
							<form:input class="border border-primary" size="9" maxlength="8" onkeypress="return filterFloat(event,this);" type="text" path="costo_cliche"/>
							<div class="has-error"><form:errors path="costo_cliche" class="badge badge-danger small"/></div>
						</div>
						<div class="col-sm-2">Suaje:
							<form:input class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" type="text" path="suaje"/>
							<div class="has-error"><form:errors path="suaje" class="badge badge-danger small"/></div>
						</div>
						<div class="col-sm-2">Pegado/Grapado:
							<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="pegado_grapado"/>
						</div>						
					</div>
				</div>
			</div>
			<div class="row small">
				<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
					<div class="row border border-right">
						<div class="col-sm-2">Rayado 1:
							<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="rayado1"/>
						</div>
						<div class="col-sm-2">Rayado 2:
							<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="rayado2"/>
						</div>
						<div class="col-sm-2">Rayado 3:
							<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="rayado3"/>
						</div>
						<div class="col-sm-2">Rayado 4:
							<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="rayado4"/>
						</div>
						<div class="col-sm-2">Rayado 5:
							<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="rayado5"/>
						</div>
						<div class="col-sm-2">Rayado 6:
							<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="rayado6"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row small">
				<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
					<div class="row border border-right">
						<div class="col-sm-4">Observaciones TF:
							<form:input class="border border-primary" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="observaciones_tf"/>
						</div>
						<div class="col-sm-4">Observaciones:
							<form:input class="border border-primary" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="observaciones"/>
						</div>
						<div class="col-sm-4">Observaciones diseñador:
							<form:input id="TComDis" class="border border-primary" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="observaciones_disenador"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div align="center" class = "container-fluid">
			<div class = "row" align="center">
				<div class="col col-lg-1"></div>			
				<div class="col col-lg-1"><form:button id="BGrabar" class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
				<div class="col col-lg-1"><a href="javascript:FBuscar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
				<div class="col col-lg-1"><a href="javascript:FLimpiar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a></div>
				<div class="col col-lg-2"><button type="button" class="btn btn-outline-primary btn-sm" data-toggle="modal" data-target=".bd-example-modal-xl"><i class="fa fa-picture-o" aria-hidden="true"> Imágenes</i></button></div>
				<div class="col col-lg-3"><button id="BEnvAut" type="button" data-toggle="modal" data-target="#EnvModal" class="btn btn-outline-primary btn-sm"><i class="fa fa-paper-plane-o" aria-hidden="true"> Enviar para autorizaciones</i></button></div>
				<div class="col col-lg-2"><button id="BCancelar" type="button" data-toggle="modal" data-target="#CancelModal" class="btn btn-outline-primary btn-sm"><i class="fa fa-paper-plane-o" aria-hidden="true"> Cancelar tarjeta</i></button></div>
			</div>
		</div>
		
		<div class = "container-fluid">
			<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
		</div>

		<div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		    	<div class="modal-header alert alert-info">
	        		<h5 class="modal-title">Imágenes</h5>
	      		</div>
		    	<div class = "container d-flex justify-content-center">
					<div class = "row">
						<div id="carouselExampleIndicators" class="carousel slide" data-interval="false" data-ride="carousel">
						  <div id="DImg" class="carousel-inner">
							<c:forEach var="item" items="${tarjeta_fabricacion.tarjeta_img}" varStatus="status">
							    <div class="${status.index == 0 ? 'carousel-item active':'carousel-item'}">
							      <img height="400" width="400" src="<c:url value="/static/img_tarjetas/${item.nombre}"/>" alt="${item.nombre}">
							      <div class="carousel-caption">
								    <h3>${item.nombre}</h3>
								    <p class="h2">${item.cama == true ? 'CAMA' : ''}</p>
								    <p><button type="button" class="btn btn-outline-primary btn-sm" id="ABorrarImg${status.index}" name="ABorrarImg" onclick="FBorrarImg(${item.idcotizacion},${item.iddetalle},'${item.nombre}')">Borrar</button></p>
								  </div>
							    </div>
							</c:forEach>
						  </div>
						  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
						    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
						    <span class="sr-only">Anterior</span>
						  </a>
						  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
						    <span class="carousel-control-next-icon" aria-hidden="true"></span>
						    <span class="sr-only">Siguiente</span>
						  </a>
						</div>
					</div>
				</div>		    	
		    	<div class="modal-footer">
		    		<small>Cama: <input id="CCama" class="border border-primary" type="checkbox" />
					<input class="btn btn-outline-primary btn-sm" name="file" id="file" type="file" onchange="FAddImagen()" accept="image/*"/></small>
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
			     </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="EnvModal" tabindex="-1" role="dialog" aria-labelledby="VtaModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header alert alert-info">
		        <h5 class="modal-title">Enviar para autorizaciones</h5>
		      </div>
		      <div class="modal-body alert alert-warning">
		        ¡¡¡ATENCIÓN!!! ¿Desea enviar esta tarjeta a autorización?
		      </div>
		      <div id="DivMensaje" class="modal-footer">
		        <button type="button" class="btn btn-primary" onClick="FEnviarAut()">Enviar</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade" id="CancelModal" tabindex="-1" role="dialog" aria-labelledby="CancelModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header alert alert-info">
		        <h5 class="modal-title">CANCELAR TARJETA</h5>
		      </div>
		      <div class="modal-body alert alert-danger">
		        ¡¡¡ATENCIÓN!!! ¿Desea CANCELAR esta tarjeta? IMPORTANTE!!!! ---> También se cancelará la Cotización y las otras Tarjetas relacionadas!!!
		      </div>
		      <div id="DivMensaje" class="modal-footer">
		        <button type="button" class="btn btn-primary" onClick="FCancelar()">Cancelar</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		      </div>
		    </div>
		  </div>
		</div>	
		
	</div>
 </form:form>
</div>
<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>