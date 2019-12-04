<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" />
<html>
<head>
<%@ include file="../../appconfig/authheader2.jsp" %>
<title>Tarjetas de Fabricación</title>
<script defer="defer">
$(document).ready(function() {

	if(('${tdb.tarjeta_fabricacion.idcotizacion}' == "") 
		|| ('${tdb.tarjeta_fabricacion.fecha_aut_diseniador}' != "" && '${tdb.tarjeta_fabricacion.usuario_aut_diseniador}' > 0)
		|| ('${tdb.tarjeta_fabricacion.fecha_cancela}' != "" && '${tdb.tarjeta_fabricacion.usuario_cancela}' > 0))
	{
		$("#BCancelar").prop("disabled", true );
		$("#BEnvAut").prop("disabled", true );
		$("#BGrabar").prop("disabled", true );
		$("#file").prop("disabled", true );
		$("#CCama").prop("disabled", true );
		var lista=document.getElementsByName("ABorrarImg");
		for(var i=0; i<lista.length; i++)
		{
			$("#"+lista[i].id).prop("disabled", true );		
		}
		$("#TPzasxLargo").attr("readonly","readonly");
		$("#TPzasxAncho").attr("readonly","readonly");
		$("#TMedInt").attr("readonly","readonly");
		$("#TRayado4").attr("readonly","readonly");
		$("#TRayado5").attr("readonly","readonly");
		$("#TRayado6").attr("readonly","readonly");
		$("#TCom").attr("readonly","readonly");
		$("#TComTF").attr("readonly","readonly");
		$("#TComDis").attr("readonly","readonly");
		$("#TAltPallet").attr("readonly","readonly");
		$("#TCamasPallet").attr("readonly","readonly");
		$("#TFlejesPallet").attr("readonly","readonly");
		$("#TFlejesAtado").attr("readonly","readonly");
		$("#TPzasAtado").attr("readonly","readonly");
		$("#TAtaCama").attr("readonly","readonly");
		$("#TPzasxTar").attr("readonly","readonly");
		$("#SNPartes option:not(:selected)").prop("disabled", true);
		$("#SGrabado option:not(:selected)").prop("disabled", true);
		$("#SSuaje option:not(:selected)").prop("disabled", true);
		$("#CTxUnit").bind("click", preventDef, false);
		
		var nodes = document.getElementById("CSRutaDiv").getElementsByTagName('*');
		for(var i = 0; i < nodes.length; i++)
		{
			if(nodes[i].type == 'checkbox')
			{
				if(nodes[i].id != "")
				{
					nodes[i].addEventListener("click", preventDef, false);
					nodes[i].onchange = "";
				}
			}
		}
		
	}
});
function preventDef(event) {
	event.preventDefault();
}
function FBuscar()
{
	if($( "#TFolioTF" ).val() =="")
		$( "#TFolioTF" ).val("");
	
	if($( "#TIdCot" ).val() =="")	
		$( "#TIdCot" ).val("0");
	
	var isDisabled = $("#BGrabar").prop('disabled');
	
	$( "#TIdCot" ).prop( "readonly", false );
	$( "#TFolioTF" ).prop( "readonly", false );
	$( "#SClientes" ).prop( "disabled", false );
	
	$('#SClientes').selectize({
	    create: true,
	    sortField: 'text'
	});
	
	$( "#BGrabar" ).prop( "disabled", true );
	
	if($( "#TIdCot" ).val() > 0 || $( "#TFolioTF" ).val() != "" || $( "#SClientes" ).val() != "")
		popupwindow('<c:url value="/tarjeta/ingenieria/tarjeta_fabricacion_buscar" />?idcot='+$( "#TIdCot" ).val()+'&foliotf='+$( "#TFolioTF" ).val()+'&cardcode='+$( "#SClientes" ).val(),'Búsqueda de tarjetas',800,1000);

		$( "#TIdCot").removeClass().addClass("border border-danger");
		$( "#TFolioTF").removeClass().addClass("border border-danger");
		$( "#SClientes").removeClass().addClass("border border-danger");
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
		var principal = "";
		jsonEsp[i].principal == true ? cama="PRINCIPAL" : "";
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
function FCheckImg(ban)
{
	if(ban == 0)
		if($("#CCama").prop('checked'))
			$("#CPrincipal").prop('checked',false);
	
	if(ban == 1)
		if($("#CPrincipal").prop('checked'))
			$("#CCama").prop('checked',false);
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
	data.append('principal', $("#CPrincipal").prop('checked'));
	
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

function FCalcular()
{
	var data = new FormData();
	
	data.append('idcotizacion', $("#TIdCot").val());
	data.append('iddetalle', $("#TIdDet").val());
	data.append('folio_tarjeta', $("#TFolioTF").val());
	data.append('npartes', $("#SNPartes").val());	
	data.append('pzasxlargo', $("#TPzasxLargo").val() == "" ? 0 : $("#TPzasxLargo").val());
	data.append('pzasxancho', $("#TPzasxAncho").val() == "" ? 0 : $("#TPzasxAncho").val());
		
	$("#mensajes" ).text("Calculando, por favor espere.");
	$("#mensajes").removeClass().addClass("alert alert-info");
	
	$.ajax({
	    url: '<c:url value="/tarjeta/ingenieria/calculardatos"/>',
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
	    	$("#mensajes" ).text("").removeClass();
			
	    	try
        	{
	  			var obj = JSON.parse(r);
//	  			alert(obj);
	        	if(obj != null)
	        	{
	        		//$("#TMedLamina").val(obj.medlamina);
	        		$("#TAreaTotal").val(obj.area_total);
	        		$("#TMedPliego").val(obj.med_pliego);
	        	}
        	}
        	catch(err) {
        		  document.getElementById("mensajes").innerHTML = "";
        	}
	    	
	    },
	    error: function(xhr, status, error) {
	    	var err = xhr.responseText;	    	
	    	$("#mensajes" ).text("Error: "+err.Message+" - "+error).removeClass().addClass("alert alert-danger");
	    }
	  });
	
}

</script>
</head>
<body>
<div class = "container-fluid">
 <form:form id="form" method="POST" modelAttribute="tdb" class="form-horizontal" autocomplete="off">
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			 <div class="row ">
				 <div class="badge badge-primary col-12">
				 	Datos de la tarjeta Ingeniería / Diseñador
				 </div>
			 </div>
		 </div>
		 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-2">Folio tarjeta:
					<form:input type="hidden" path="tarjeta_fabricacion.cardcode"/>
					<form:input type="hidden" path="tarjeta_fabricacion.fecha_asig_diseniador"/>
					<form:input type="hidden" path="tarjeta_fabricacion.iddiseniador"/>
					<form:input id="TFolioTF" class="border border-secondary" size="9" maxlength="8" onkeypress="return SinCaracteresEspeciales(event)"  readonly="true" type="text" path="tarjeta_fabricacion.folio_tarjeta"/>
				</div>
				<div class="col-sm-2">Folio cotización:
					<form:input id="TIdCot" class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)"  readonly="true" type="text" path="tarjeta_fabricacion.idcotizacion"/>
					<form:input id="TIdDet" class="border border-secondary" size="9" maxlength="8" type="hidden" path="tarjeta_fabricacion.iddetalle"/>
				</div>
				<div class="col-sm-4">Descripción Factura:
					<form:input class="border border-secondary" size="45" maxlength="8" onkeypress="return Enteros(event)"  readonly="true" type="text" path="tarjeta_fabricacion.descripcion_factura"/>
				</div>
				<div class="col-sm-2">Piezas x largo:
					<form:input id="TPzasxLargo" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" onkeyup="FCalcular()"  type="text" path="tarjeta_fabricacion.pzasxlargo"/>
					<div class="has-error"><form:errors path="tarjeta_fabricacion.pzasxlargo" class="badge badge-danger small"/></div>
				</div>
				<div class="col-sm-2">Piezas x ancho:
					<form:input id="TPzasxAncho" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" onkeyup="FCalcular()" type="text" path="tarjeta_fabricacion.pzasxancho"/>
					<div class="has-error"><form:errors path="tarjeta_fabricacion.pzasxancho" class="badge badge-danger small"/></div>
				</div>
			</div>
		</div>	
				
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-8">Cliente:
					<form:select disabled="true" id="SClientes" path="cotizador.cardcode" multiple="false" class="border border-secondary">
						<form:option value=""></form:option>
						<c:forEach var="cte" items="${clientes}">
							<form:option value="${cte.cardcode}"><c:out value="${cte.cardname}"/></form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-sm-2">Número de partes:
					<form:select onchange="FCalcular()" id="SNPartes" path="tarjeta_fabricacion.num_partes" multiple="false" class="border border-primary">
						<form:option value="1">1</form:option>
						<form:option value="2">2</form:option>
					</form:select>
				</div>
				<div class="col-sm-2">
					<button type="button" data-toggle="modal" data-target="#AutModal" class="float-right btn btn-outline-primary btn-sm"><i class="fa fa-info-circle" aria-hidden="true"> Info</i></button>
				</div>
		 	</div>
		 </div>
		 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-8">Cliente factura: ${cliente_factura}
				</div>
			</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-2">Medida pliego:
					<form:input id="TMedPliego" class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="tarjeta_fabricacion.medida_pliego"/>
					<div class="has-error"><form:errors path="tarjeta_fabricacion.medida_pliego" class="badge badge-danger small"/></div>
				</div>
				<div class="col-sm-2">Medidas internas:
					<form:input id="TMedInt" class="border border-primary" size="9" maxlength="20" onkeypress="return SinCaracteresEspeciales(event)" type="text" path="tarjeta_fabricacion.medidas_internas"/>
					<div class="has-error"><form:errors path="tarjeta_fabricacion.medidas_internas" class="badge badge-danger small"/></div>
				</div>
				<div class="col-sm-2">Grabado:
					<form:select id="SGrabado" path="tarjeta_fabricacion.grabado" multiple="false" class="border border-primary">
						<form:option value="0"> - </form:option>
						<c:forEach var="gr" items="${grabados}">
							<form:option value="${gr.id}"><c:out value="${gr.nombre}-${gr.costo}"/></form:option>
						</c:forEach>
					</form:select>
					<div class="has-error"><form:errors path="tarjeta_fabricacion.grabado" class="badge badge-danger small"/></div>
				</div>
				<div class="col-sm-2">Suaje:
					<form:select id="SSuaje" path="tarjeta_fabricacion.suaje" multiple="false" class="border border-primary">
						<form:option value="0"> - </form:option>
						<c:forEach var="sj" items="${suajes}">
							<form:option value="${sj.id}"><c:out value="${sj.nombre}-${sj.costo}"/></form:option>
						</c:forEach>
					</form:select>
					<div class="has-error"><form:errors path="tarjeta_fabricacion.suaje" class="badge badge-danger small"/></div>
				</div>
				<div class="col-sm-2">Pegado/Grapado:
					<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="tarjeta_fabricacion.pegado_grapado"/>
				</div>						
			</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-2">Rayado 1:
					<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="tarjeta_fabricacion.rayado1"/>
				</div>
				<div class="col-sm-2">Rayado 2:
					<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="tarjeta_fabricacion.rayado2"/>
				</div>
				<div class="col-sm-2">Rayado 3:
					<form:input class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="tarjeta_fabricacion.rayado3"/>
				</div>
				<div class="col-sm-2">Rayado 4:
					<form:input id="TRayado4" onkeypress="return filterFloat(event,this);" class="border border-primary" size="9" maxlength="8" type="text" path="tarjeta_fabricacion.rayado4"/>
				</div>
				<div class="col-sm-2">Rayado 5:
					<form:input id="TRayado5" onkeypress="return filterFloat(event,this);" class="border border-primary" size="9" maxlength="8" type="text" path="tarjeta_fabricacion.rayado5"/>
				</div>
				<div class="col-sm-2">Rayado 6:
					<form:input id="TRayado6" onkeypress="return filterFloat(event,this);" class="border border-primary" size="9" maxlength="8" type="text" path="tarjeta_fabricacion.rayado6"/>
				</div>
			</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-3">Área total:
					<form:input id="TAreaTotal" class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="tarjeta_fabricacion.area_total"/>
				</div>
				<div class="col-sm-3">Observaciones TF:
					<form:input id="TComTF" class="border border-primary" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="tarjeta_fabricacion.observaciones_tf"/>
				</div>
				<div class="col-sm-3">Observaciones:
					<form:input id="TCom" class="border border-primary" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="tarjeta_fabricacion.observaciones"/>
				</div>
				<div class="col-sm-3">Observaciones diseñador:
					<form:input id="TComDis" class="border border-primary" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="tarjeta_fabricacion.observaciones_disenador"/>
				</div>
			</div>
		</div>		
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row ">
			 <div class="badge badge-secondary col-12">
			 	Embarques/Inocuidad
			 </div>
			</div>
		 </div>
		 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col col-lg-2">Altura pallet:
					<form:input id="TAltPallet" size="10" maxlength="8" type="text" path="cotizador_detalles.altura_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
				</div>
				<div class="col col-lg-2">Camas pallet:
					<form:input id="TCamasPallet" size="10" maxlength="8" type="text" path="cotizador_detalles.camas_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
				</div>
				<div class="col col-lg-2">Flejes pallet:
					<form:input id="TFlejesPallet" size="10" maxlength="8" type="text" path="cotizador_detalles.flejes_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
				</div>
				
				<div class="col col-lg-2">Flejes atado:
					<form:input id="TFlejesAtado" size="10" maxlength="8" type="text" path="cotizador_detalles.flejes_atado" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
				</div>
				<div class="col col-lg-2">Pzas atado:
					<form:input id="TPzasAtado" size="10" maxlength="8" type="text" path="cotizador_detalles.pzas_atado" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
				</div>
				<div class="col col-lg-2">Atados cama:
					<form:input id="TAtaCama" size="10" maxlength="8" type="text" path="cotizador_detalles.atados_cama" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
				</div>
			</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col col-lg-1">Pzas tarima</div>
				<div class="col col-lg-1">
					<form:input id="TPzasxTar" onKeyUp="CalcularDatos()" size="10" maxlength="8" type="text" path="cotizador_detalles.piezasxtarima" onkeypress="return Enteros(event);" class="border border-primary"/>
					<div class="has-error">
						<form:errors path="cotizador_detalles.piezasxtarima" class="badge badge-danger small"/>
					</div>
				</div>
				<div class="col col-lg-2">Emplayado: <form:checkbox disabled="true" path="cotizador.emplayado"/></div>
				<div class="col col-lg-2">Vueltas: <form:input disabled="true" size="10" maxlength="8" type="text" path="cotizador.vueltas_emplaye" class="border border-secondaty"/></div>
				<div class="col col-lg-2">Factura: <form:checkbox disabled="true" path="cotizador.factura"/></div>
				<div class="col col-lg-2">Certif calidad: <form:checkbox disabled="true" path="cotizador.certif_calidad"/></div>
				<div class="col col-lg-2">Imprimir OC: <form:checkbox disabled="true" path="cotizador.imprimir_oc"/> </div>
			</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col col-lg-1">A granel: <form:checkbox disabled="true" path="cotizador_detalles.agranel"/></div>
				<div class="col col-lg-2">Protecciones: <form:checkbox disabled="true" path="cotizador.protecciones"/></div>
				<div class="col col-lg-1">Caja seca: <form:checkbox disabled="true" path="cotizador.caja_seca"/></div>
				<div class="col col-lg-2">Certif fumigación: <form:checkbox disabled="true" path="cotizador.certif_fumig"/></div>
				<div class="col col-lg-2">EPP transportista: <form:checkbox disabled="true" path="cotizador.epp_transportista"/></div>
				<div class="col col-lg-1">Imp fech: <form:checkbox disabled="true" path="cotizador.imprimir_fechador"/></div>
				<div class="col col-lg-1">Imp ped: <form:checkbox disabled="true" path="cotizador.imprimir_pedido"/></div>
				<div class="col col-lg-2">TarimaXunitizado: <form:checkbox id="CTxUnit" path="cotizador.tarimaxunitizado"/></div>
			</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			 <div class="row ">
				 <div class="badge badge-success col-12">
				 	Ruta
				 </div>
			 </div>
		 </div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small">
				<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
					<div class="row border border-right">
						<div id="CSRutaDiv" class="col col-lg-12">
							<form:checkboxes id="CSRuta" items="${maquinas}" itemLabel="name" itemValue="code" path="tarjeta_fabricacion.catalogo_maquinas_sap_vw" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/>							
							<div class="has-error">
								<form:errors path="tarjeta_fabricacion.catalogo_maquinas_sap_vw" class="badge badge-danger small"/>
							</div>
							<c:forEach var="item" items="${esp}">
								<c:if test="${item.ruta == 1}">
										<input type="checkbox" disabled checked/>${item.especialidad} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
							</c:forEach>
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
				<div class="col col-lg-2"><button id="BCancelar" type="button" data-toggle="modal" data-target="#CancelModal" class="btn btn-outline-primary btn-sm"><i class="fa fa-times-circle-o" aria-hidden="true"> Cancelar tarjeta</i></button></div>
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
							<c:forEach var="item" items="${tdb.tarjeta_fabricacion.tarjeta_img}" varStatus="status">
							    <div class="${status.index == 0 ? 'carousel-item active':'carousel-item'}">
							      <img height="400" width="400" src="<c:url value="/static/img_tarjetas/${item.nombre}"/>" alt="${item.nombre}">
							      <div class="carousel-caption">
								    <h3>${item.nombre}</h3>
								    <p class="h2">${item.cama == true ? 'CAMA' : ''}${item.principal == true ? 'PRINCIPAL' : ''}</p>
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
		    		<small>Cama: <input onClick="FCheckImg(0)" id="CCama" class="border border-primary" type="checkbox" /> /
		    			   Principal: <input onClick="FCheckImg(1)" id="CPrincipal" class="border border-primary" type="checkbox" />
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
		
		<div class="modal fade bd-example-modal-xl" id="AutModal" tabindex="-1" role="dialog" aria-labelledby="AutModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-xl">
		    <div class="modal-content">
		    <div class="container">
		   	  <div class="badge badge-info col-12">
		        Información adicional
		      </div>
		      
			  <div class="row small">
			    <div class="col-sm">
			      Fecha aut calidad: ${tdb.tarjeta_fabricacion.fecha_aut_calidad}			    
			    </div>
			    <div class="col-sm">
			      Observaciones calidad: ${tdb.tarjeta_fabricacion.observaciones_calidad}			    
			    </div>
			    <div class="col-sm">
			      Fecha rech calidad: ${tdb.tarjeta_fabricacion.fecha_rech_calidad}			    
			    </div>
			  </div>
			  <div class="row small">
			    <div class="col-sm">
			      Fecha aut producción: ${tdb.tarjeta_fabricacion.fecha_aut_produccion}			    
			    </div>
			    <div class="col-sm">
			      Observaciones producción: ${tdb.tarjeta_fabricacion.observaciones_produccion}			    
			    </div>
			    <div class="col-sm">
			      Fecha rech producción: ${tdb.tarjeta_fabricacion.fecha_rech_produccion}			    
			    </div>
			  </div>
			  <div class="row small">
			    <div class="col-sm">
			      Fecha aut ingeniería: ${tdb.tarjeta_fabricacion.fecha_aut_ing}			    
			    </div>
			    <div class="col-sm">
			      Observaciones ingeniería: ${tdb.tarjeta_fabricacion.observaciones_ing}			    
			    </div>
			    <div class="col-sm">
			      Fecha rech ingeniería: ${tdb.tarjeta_fabricacion.fecha_rech_ing}			    
			    </div>
			  </div>
			  <div class="row small">
			    <div class="col-sm">
			      Fecha aut cliente: ${tdb.tarjeta_fabricacion.fecha_aut_cliente}			    
			    </div>
			    <div class="col-sm">
			      Observaciones cliente: ${tdb.tarjeta_fabricacion.observaciones_cliente}			    
			    </div>
			    <div class="col-sm">
			      Fecha rech cliente: ${tdb.tarjeta_fabricacion.fecha_rech_cliente}			    
			    </div>
			  </div>
			  <div class="row small">
			    <div class="col-sm">
			      Fecha enviada a autorizaciónes: ${tdb.tarjeta_fabricacion.fecha_aut_diseniador}			    
			    </div>
			    <div class="col-sm">
			      Fecha cancelación: ${tdb.tarjeta_fabricacion.fecha_cancela}			    
			    </div>
			    <div class="col-sm">
			      Fecha últ autorización: ${tdb.cotizador.fecha_aut_prog le tdb.cotizador.fecha_aut_ventas ? tdb.cotizador.fecha_aut_ventas : tdb.cotizador.fecha_aut_prog}			    
			    </div>
			  </div>			  
			  <div class="badge badge-info col-12">Especialidades</div>
				<div class="row small">
					<div class="col font-weight-bold">Especialidad</div>
					<div class="col font-weight-bold">Costo</div>
					<div class="col font-weight-bold">Ajuste</div>
					<div class="col font-weight-bold">CM</div>
				</div>
				<c:forEach var="item" items="${esp}">
					<div class="row small">
						<div class="col">${item.especialidad}</div>
						<div class="col">${item.costo}</div>
						<div class="col">${item.ajuste}</div>
						<div class="col">${item.cm}</div>
					</div>
				</c:forEach>			    
			    <div class="badge badge-info col-12">Código de barras</div>
				<div class="row small">
					<div class="col font-weight-bold">Código</div>
					<div class="col font-weight-bold">Observaciones</div>
				</div>
				<c:forEach var="item" items="${tdb.cotizador_detalles.codigo_barra_cotizador}">
					<div class="row small">
						<div class="col">${item.idcodigo}</div>
						<div class="col">${item.observaciones}</div>
					</div>
				</c:forEach>
				
				<div class="badge badge-info col-12">Tintas</div>
				<div class="row small">
					<div class="col font-weight-bold">Color 1</div>
					<div class="col font-weight-bold">Color 2</div>
					<div class="col font-weight-bold">Color 3</div>
					<div class="col font-weight-bold">Color 4</div>
					<div class="col font-weight-bold">Color 5</div>
					<div class="col font-weight-bold">Color 6</div>
					<div class="col font-weight-bold">Color 7</div>
				</div>
				<div class="row small">
					<div class="col">
						<c:forEach var="item" items="${colores}">
						  <c:if test="${item.id eq tdb.cotizador_detalles.color1}">
						    <span style="background:#${item.color_est}">${item.color}</span>
						  </c:if>
						</c:forEach>
					</div>
					<div class="col">
						<c:forEach var="item" items="${colores}">
						  <c:if test="${item.id eq tdb.cotizador_detalles.color2}">
						    <span style="background:#${item.color_est}">${item.color}</span>
						  </c:if>
						</c:forEach>
					</div>
					<div class="col">
						<c:forEach var="item" items="${colores}">
						  <c:if test="${item.id eq tdb.cotizador_detalles.color3}">
						    <span style="background:#${item.color_est}">${item.color}</span>
						  </c:if>
						</c:forEach>
					</div>
					<div class="col">
						<c:forEach var="item" items="${colores}">
						  <c:if test="${item.id eq tdb.cotizador_detalles.color4}">
						    <span style="background:#${item.color_est}">${item.color}</span>
						  </c:if>
						</c:forEach>
					</div>
					<div class="col">
						<c:forEach var="item" items="${colores}">
						  <c:if test="${item.id eq tdb.cotizador_detalles.color5}">
						    <span style="background:#${item.color_est}">${item.color}</span>
						  </c:if>
						</c:forEach>
					</div>
					<div class="col">
						<c:forEach var="item" items="${colores}">
						  <c:if test="${item.id eq tdb.cotizador_detalles.color6}">
						    <span style="background:#${item.color_est}">${item.color}</span>
						  </c:if>
						</c:forEach>
					</div>
					<div class="col">
						<c:forEach var="item" items="${colores}">
						  <c:if test="${item.id eq tdb.cotizador_detalles.color7}">
						    <span style="background:#${item.color_est}">${item.color}</span>
						  </c:if>
						</c:forEach>
					</div>
				</div>
				<div class="badge badge-info col-12">Detalles de caja</div>
				<div class="row small">
				    <div class="col-6">Caja: ${caja.nombrelargo}</div>
				    <div class="col">Largo: ${tdb.cotizador_detalles.largo}</div>
				    <div class="col">Ancho: ${tdb.cotizador_detalles.ancho}</div>
				</div>
			    <div class="row small">
			    	<div class="col-2">Fondo: ${tdb.cotizador_detalles.fondo}</div>
				    <div class="col-5">Resistencia Barca: ${resis.resistencia} Flauta: ${resis.corrugado} Color: ${resis.color} $M2: ${resis.preciom2}</div>
				    <div class="col">Sello: ${sello.sellos}</div>
				    <div class="col">Cierre: ${tdb.cotizador_detalles.cierre}</div>
				    <div class="col">Cierre detalle: ${tdb.cotizador_detalles.cierre_detalle}</div>
			    </div>
			    <div class="row small">
			    	<div class="col">Ceja despl: ${tdb.cotizador_detalles.ceja_desplegada == true ? 'Sí' : 'No'}</div>
				    <div class="col">Precio obj: ${tdb.cotizador_detalles.precio_objetivo}</div>
				    <div class="col">Imp rebasada: ${tdb.cotizador_detalles.score}</div>
				    <div class="col">Esp Inf: ${tdb.cotizador_detalles.esp_inf}</div>
				    <div class="col">Esp Sup: ${tdb.cotizador_detalles.esp_sup}</div>
				    <div class="col">Medida lámina: ${tdb.cotizador_detalles.medida_lamina}</div>
				</div>
				<div class="row small">
					<div class="col">Área unitaria: ${tdb.cotizador_detalles.area_unitaria}</div>
					<div class="col">Peso teórico: ${tdb.cotizador_detalles.peso_teorico}</div>
					<div class="col">Num ranuras: ${tdb.cotizador_detalles.num_raturas}</div>
					<div class="col">Tolerancia: ${tdb.cotizador.tolerancia_pedido}</div>
					<div class="col">Pzas x juego: ${tdb.cotizador_detalles.piezasxjuego}</div>
					<div class="col">Entrega OC: ${fn:substring(tdb.cotizador.fecha_entrega_oc,0,10)}</div>
				</div>
				<div class="row small">
					<div class="col">Piezas por tarima: ${tdb.cotizador_detalles.piezasxtarima}</div>
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