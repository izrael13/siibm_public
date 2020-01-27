<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<script src="<c:url value="/static/js/sistemas_tarjetas/cotizador.js" />"></script>
<script>
$(document).ready(function() {
		
	if('${cotizadordatabean.cotizador.id}' > 0)
	{
		if(('${cotizadordatabean.cotizador.usuario_envia_ventas}' > 0 && '${cotizadordatabean.cotizador.fecha_envia_ventas}' != '') || 
		   ('${cotizadordatabean.cotizador.usuario_envia_a_prog}' > 0 && '${cotizadordatabean.cotizador.fecha_envia_a_prog}' != '')||
		   ('${cotizadordatabean.cotizador.usuario_cancel}' > 0  && '${cotizadordatabean.cotizador.fecha_cancel}' != '') ||
		   ('${cotizadordatabean.cotizador.usuario_envia_arrmues}' > 0  && '${cotizadordatabean.cotizador.fecha_envia_arrmues}' != ''))
		{
			
			if( ('${cotizadordatabean.cotizador.usuario_rech_prog}' == '' && '${cotizadordatabean.cotizador.fecha_rech_prog}' == '') ||
				('${cotizadordatabean.cotizador.usuario_rech_ventas}' == ''  && '${cotizadordatabean.cotizador.fecha_rech_ventas}' == '') ||
			    ('${cotizadordatabean.cotizador.usuario_rech_diseniador}' == '' && '${cotizadordatabean.cotizador.fecha_rech_diseniador}' == '') ||
			    ('${cotizadordatabean.cotizador.usuario_cancel}' > 0  && '${cotizadordatabean.cotizador.fecha_cancel}' != '') ||
			    ('${cotizadordatabean.cotizador.usuario_rech_arrastre}' > 0  && '${cotizadordatabean.cotizador.fecha_rech_arrastre}' != '')
			  )
			{
				FDisableElemens();
			}
			else
				FEmbarques(0);
		}
		else
			FEmbarques(0);
		
		if('${cotizadordatabean.cotizador.usuario_diseniador}' != ''  && '${cotizadordatabean.cotizador.fecha_asign_diseniador}' != '')
			$("#BCancel").prop('disabled',true);
	} 
});
function FValidaLogIn(data)
{
	if (data.search(/Login page/i) != -1) 
		window.location.replace('<c:url value="/login?expired"/>');
}
function FBuscarDirecciones()
{
	var cardcode = document.getElementById("SClientes").value;
	$("#SClientesFactura").val(cardcode);
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
        		FValidaLogIn(data)
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
							  $("#mensajes" ).text("");
							  $("#mensajes").removeClass();
					        },	
        success : function(data) {
        	FValidaLogIn(data)
        	$.each(jQuery.parseJSON(data),function(index, value){
        		$("#TFlete").val(value.flete);
        		if(value.flete == 0)
        		{
        			$("#mensajes" ).text("Este destino no tiene valor de flete.").removeClass().addClass("alert alert-danger");
        		}
        		else
        			CalcularDatos();
        		
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
	window.location.replace('<c:url value="/cotizador/vendedor/cotizadorabc"/>');
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
		popupwindow('<c:url value="/cotizador/vendedor/cotizadorbusqueda" />?id='+$( "#TId" ).val()+'&cardcode='+$( "#SClientes" ).val(),'Detalle de viaje',800,1000);
	}

		$( "#BGrabar" ).prop( "disabled", true );
		$( "#TId").removeClass().addClass("border border-danger");
		$( "#SClientes").removeClass().addClass("border border-danger");
		$( "#TId" ).focus();
	
}
function FBuscarxId(id,iddet)
{
	window.location.replace('<c:url value="/cotizador/vendedor/cotizadorabc" />?id='+id+'&iddet='+iddet);
}
function FBuscarResisId()
{
	//alert(":)");
	var idresis = $("#SResisBarca" ).val();
	
	$.ajax({
		url: '<c:url value="/cotizador/vendedor/buscarinforesistenciabarca"/>?id='+idresis,
		beforeSend: function(xhr) {
							  $("#imgload").show();
							  $("#TPreciom2resis").val(0);
							  $("#TPesoResis").val(0);
							  $("#TDescVen").val(0);
							  $("#TCostoPapelResis").val(0);
							  $("#mensajes" ).text("").removeClass();
					        },	
        success : function(data) {
        	FValidaLogIn(data)
  			var obj = JSON.parse(data);
        	if(obj != null)
        	{
	        	$("#TPreciom2resis").val(obj.preciom2);
	        	$("#TDescVen").val(obj.u_descr);
	        	$("#TPesoResis").val(obj.peso);
	        	$("#TCostoPapelResis").val(obj.u_costopapel);
	        	CalcularDatos();
        	}
        	else
        	{
				  $("#TPreciom2resis").val(0);
				  $("#TPesoResis").val(0);
				  $("#TDescVen").val(0);
				  $("#TCostoPapelResis").val(0);
        	}
        	$("#imgload").hide();
        },
        error: function(xhr, status, error) {
        	  $("#TPreciom2resis").val(0);
        	  $("#TDescVen").val(0);
        	  $("#TPesoResis").val(0);
        	  $("#TCostoPapelResis").val(0);
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
}

function CalcularDatos()
{
	SumarEsp($("#SCajas").val());

	var Parameters = new Object();
	Parameters.ancho = ($("#TAncho").val() === "" ? 0 : $("#TAncho").val());
	Parameters.fondo = ($("#TFondo").val() === "" ? 0 : $("#TFondo").val());
	Parameters.espsup = ($("#TEspSup").val() === "" ? 0 : $("#TEspSup").val());
	Parameters.espinf = ($("#TEspInf").val() === "" ? 0 : $("#TEspInf").val());
	Parameters.score = $("#SScore").val();
	Parameters.largo = ($("#TLargo").val() === "" ? 0 : $("#TLargo").val());
	Parameters.cantpedmes = ($("#TCantPedMes").val() === "" ? 0 : $("#TCantPedMes").val());
	Parameters.precioobj = ($("#TPreciObj").val() === "" ? 0 : $("#TPreciObj").val());
	Parameters.pzasxjgo = ($("#TPzasxjgo").val() === "" ? 0 : $("#TPzasxjgo").val());
	Parameters.totalflete = ($("#TFlete").val() === "" ? 0 : $("#TFlete").val());
	Parameters.cardcode = $("#SClientes").val();
	Parameters.pesoresis = ($("#TPesoResis").val() === "" ? 0 : $("#TPesoResis").val());
	Parameters.preciom2resis = ($("#TPreciom2resis").val() === "" ? 0 : $("#TPreciom2resis").val());
	Parameters.descven = ($("#TDescVen").val() === "" ? 0 : $("#TDescVen").val());
	Parameters.idcaja = $("#SCajas").val();
	Parameters.pzasxtar = ($("#TPzasxTar").val() === "" ? 0 : $("#TPzasxTar").val());
	Parameters.idsesp = ids;
	Parameters.costoscapturados = costoscapturados;
	Parameters.costopapelresis = ($("#TCostoPapelResis").val() === "" ? 0 : $("#TCostoPapelResis").val());
	Parameters.ajustes = ajustes;
	Parameters.esquemas = esquemas;
	Parameters.cm = cm;
	
	var mystring = JSON.stringify(Parameters);
	
	$.ajax({
		url: '<c:url value="/cotizador/vendedor/calculardatos"/>?mystring='+encodeURI(mystring),
		beforeSend: function(xhr) {
							  $("#imgload").show();
							  $("#mensajes" ).text("").removeClass();
								$("#TAreaUni").val(0);
								$("#TPesoT").val(0);
								$("#TPrecioN").val(0);
								$("#TM2").val(0);
								$("#TKg").val(0);
								$("#TMedLamina").val("");
								$("#TComisionDir").val(0);
								$("#TCostoPapel").val(0);
								$("#TCostoFlete").val(0);
								$("#TRefCom").val(0);
								$("#TPorcCom").val(0);
								$("#TComxMillar").val(0);
								$("#TCpcc").val(0);
								$("#TPrecioS").val(0);
								$("#TPorcFlete").val(0);
								$("#TPespPza").val(0);
								$("#TLargoPliego").val(0);
				        		$("#TAnchoPliego").val(0);
				        		$("#TTotEsp").val(0);
					        },	
        success : function(data) {
        	FValidaLogIn(data)
        	try
        	{
	  			var obj = JSON.parse(data);
	        	if(obj != null)
	        	{
	        		$("#TAreaUni").val(obj.AreaUni);
	        		$("#TPesoT").val(obj.PesoTeorico);
	        		$("#TPrecioN").val(obj.PrecioNeto);
	        		$("#TM2").val(obj.M2);
	        		$("#TKg").val(obj.KG);
	        		$("#TMedLamina").val(obj.MedLamina);
	        		$("#TComisionDir").val(obj.ComisionDirecto);
	        		$("#TCostoPapel").val(obj.CostoPapel);
	        		$("#TCostoFlete").val(obj.CostoFlete);
	        		$("#TRefCom").val(obj.CPSC);
	        		$("#TPorcCom").val(obj.PorcComision);
	        		$("#TComxMillar").val(obj.ComXmillar);
	        		$("#TCpcc").val(obj.CPCC);
	        		$("#TPrecioS").val(obj.PrecioSugerido);
	        		$("#TPorcFlete").val(obj.PorcFlete);
	        		$("#TPespPza").val(obj.PesoPza);
	        		$("#TLargoPliego").val(obj.LargoVar);
	        		$("#TAnchoPliego").val(obj.AnchoVar);
	        		$("#TTotEsp").val(obj.TotCostoEsp);
	        		$("#TAreaTotal").val(obj.AreaTotal);
	        		$("#TPesoTotal").val(obj.PesoTotal);
	        		$("#TPkTeorico").val(obj.PK_Teorico);
	        		
	        		var jsonEsp = JSON.parse(obj.Esp);
	
	        		for( var i=0; i<jsonEsp.length; i++ )
	        				$("#TCosto"+jsonEsp[i].id).val(jsonEsp[i].costo);
	
	        	}
        	}
        	catch(err) {
        		  document.getElementById("mensajes").innerHTML = "";
        		}
        	$("#imgload").hide();
        },
        error: function(xhr, status, error) {
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
	
}

function FEnviarVtaProg()
{
	var idcot = +$("#TId").val();
	var iddet = $("#TIdDet").val();
	if(idcot > 0)
	{
		var http = new XMLHttpRequest();
		var url = '<c:url value="/cotizador/vendedor/enviaragerenteventasprog"/>';
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
	    				alert("Exitoso envío a autorización.");
			    		window.location.replace('<c:url value="/cotizador/vendedor/cotizadorabc"/>?id=0'+'&iddet='+0);
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/vendedor/cotizadorabc"/>?id='+idcot+'&iddet='+iddet);
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
		    		window.location.replace('<c:url value="/cotizador/vendedor/cotizadorabc"/>?id='+idcot+'&iddet='+iddet);
		    	}
		    }
		    
		}
		http.send(encodeURI(params));
	}
}
function FCancelar()
{
	var idcot = +$("#TId").val();
	var iddet = $("#TIdDet").val();
	if(idcot > 0)
	{
		var http = new XMLHttpRequest();
		var url = '<c:url value="/cotizador/vendedor/cancelarcotizacion"/>';
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
	    				alert("CANCELACIÓN EXITOSA!!!!!!");
			    		window.location.replace('<c:url value="/cotizador/vendedor/cotizadorabc"/>?id=0'+'&iddet='+0);
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/vendedor/cotizadorabc"/>?id='+idcot+'&iddet='+iddet);
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
		    		window.location.replace('<c:url value="/cotizador/vendedor/cotizadorabc"/>?id='+idcot+'&iddet='+iddet);
		    	}
		    }
		    
		}
		http.send(encodeURI(params));
	}
}
function BuscarResistencias()
{
	var idcaja = $("#SCajas").val();
	var opciones = "";
	
	$("#imgload").show();
	$("#SResisBarca" ).val(0);
	$("#TPreciom2resis").val(0);
	$("#TPesoResis").val(0);
	$("#TDescVen").val(0);
	$("#TCostoPapelResis").val(0);
	$("#mensajes" ).text("").removeClass();
	$("#SResisBarca" ).empty();
	
	$.ajax({
		url: '<c:url value="/cotizador/vendedor/buscarresistenciasbarca"/>?idcaja='+idcaja,
		
		success : function(data) {
			FValidaLogIn(data)
			var obj = JSON.parse(data);
			if(obj != null)
			{
				opciones = opciones + "<option value='"+0+ "'> - - - </option>";
				$.each(jQuery.parseJSON(data),function(index, value){
	        		opciones = opciones + "<option value='"+value.idresistencia + "'>"+ value.resistencia+" Flauta:"+value.corrugado+" Papel:"+value.color+ "</option>";
	        	});
				$( "#SResisBarca" ).append(opciones);
				CalcularDatos()
			}

        	$("#imgload").hide();
        },				
		error: function(xhr, status, error) {
		  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
		  $( "#imgload").hide();
		  }
				
	 });
}
</script>
<title>Registro cotizaciones/Requerimientos/Muestras</title>
</head>
	<body>
	<div style="height: 20px" align="center">
		 	<span id="imgload" style='display: none;'><img width="20px" height="20px" src='<c:url value="/static/img/sun_watch.gif"/>' /></span>
	</div>
	<c:if test="${error != null}">
	<div class="alert alert-warning">
		<p>${error}</p>
	</div>
	</c:if>
	<c:if test="${mensajes != null}">
		<div class="alert alert-success">
			<p>${mensajes}</p>
		</div>
	</c:if>
	<div class = "container-fluid">
	 <form:form id="form" method="POST" modelAttribute="cotizadordatabean" class="form-horizontal" autocomplete="off">
		<div class="row">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">	
					 <div class="badge badge-primary col-12">
					 	Datos del cliente
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col col-lg-1">Folio</div>
									<div class="col col-lg-1">
										<form:input class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)" id="TId" readonly="true" type="text" path="cotizador.id"/>
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
									<div class="col col-lg-1 font-weight-bold">Flete</div>
									<div class="col col-lg-1">
										<form:input ondblclick="FBuscarInfoDir()" readonly = "true" size="10" type="text" id= "TFlete" path="cotizador.costo_flete" class="border border-secondary"
													data-toggle="tooltip" data-placement="top" title="Doble click para actualizar." />
										<div class="has-error">
											<form:errors path="cotizador.costo_flete" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
							</div>
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
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
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col col-lg-2">Cliente factura</div>
									<div class="col col-lg-10">
										<form:select id="SClientesFactura" path="cotizador.cardcode_factura" multiple="false" class="border border-primary">
											<form:option value="0">Seleccione un cliente factura</form:option>
											<c:forEach var="cte" items="${clientes}">
												<form:option value="${cte.cardcode}"><c:out value="${cte.cardname}"/></form:option>
											</c:forEach>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador.cardcode_factura" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
							</div>							
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col col-lg-4">Contacto: <span id="DContacto">${direccionSelect[0].contacto}</span></div>
									<div class="col col-lg-3">Teléfono: <span id="DTelefono">${direccionSelect[0].telefono}</span></div>
									<div class="col col-lg-3">Email: <span id="DEmail">${direccionSelect[0].email}</span></div>
									<div class="col col-lg-2">
									<form:select id="STipoReq" path="cotizador.idtiporequerimiento" multiple="false" class="border border-primary">
										<form:option value="0">Diseño</form:option>
										<form:option value="1">Muestra simple</form:option>
										<form:option value="2">Muestra vestida</form:option>
									</form:select> 
									</div>
								</div>
							</div>
						</div>
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="badge badge-info col-12"> Detalles de caja </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col col-lg-1">
										<form:input id="TIdCotDet" class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)" readonly="true" type="hidden" path="cotizador_detalles.idcotizacion"/>
										<form:input id="TIdDet" class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)" readonly="true" type="text" path="cotizador_detalles.iddetalle"/>
									</div>
									<div class="col col-lg-1">Símbolo</div>
									<div class="col col-lg-3">
										<form:input id="TSimbolo" type="text" size="40" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="cotizador_detalles.simbolo" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.simbolo" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Caja</div>
									<div class="col col-lg-6">
										<form:select onChange="BuscarResistencias()" id="SCajas" path="cotizador_detalles.idcaja_sap" multiple="false" class="border border-primary">
											<form:option value="0">Seleccione caja</form:option>
											<c:forEach var="caj" items="${listacajas}">
												<form:option value="${caj.idtipocaja}"><c:out value="${caj.nombrelargo}"/></form:option>
											</c:forEach>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.idcaja_sap" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col col-lg-1">Largo</div>
									<div class="col col-lg-1">
										<form:input onKeyUp="CalcularDatos()" id="TLargo" size="10" type="text" path="cotizador_detalles.largo" onkeypress="return filterFloat1(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.largo" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Ancho</div>
									<div class="col col-lg-1">
										<form:input onKeyUp="CalcularDatos()" id="TAncho" size="10" type="text" path="cotizador_detalles.ancho" onkeypress="return filterFloat1(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.ancho" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Fondo</div>
									<div class="col col-lg-1">
										<form:input onKeyUp="CalcularDatos()" id="TFondo" size="10" type="text" path="cotizador_detalles.fondo" onkeypress="return filterFloat1(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.fondo" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Resistencia</div>
									<div class="col col-lg-3">
										<form:select onChange="FBuscarResisId()" id="SResisBarca" path="cotizador_detalles.idresistencia_barca" multiple="false" class="border border-primary">
											<form:option value="0">Seleccione resistencia</form:option>
											<c:forEach var="res" items="${listaresisbarca}">
												<form:option value="${res.idresistencia}"><c:out value="${res.resistencia} Flauta:${res.corrugado} Papel:${res.color}"/></form:option>
											</c:forEach>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.idresistencia_barca" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Precio M2</div>
									<div class="col col-lg-1">
										<form:input ondblclick="FBuscarResisId()" readonly="true" id="TPreciom2resis" type="text" size="10" path="cotizador_detalles.preciom2resistencia"  class="border border-secondary"
													data-toggle="tooltip" data-placement="top" title="Doble click para actualizar."/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.preciom2resistencia" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col col-lg-1">ResistenciaCTE</div>
									<div class="col col-lg-2">
										<form:select id="SResisCte" path="cotizador_detalles.resistencia_cte" multiple="false" class="border border-primary">
											<form:option value="0">Seleccione resistencia CTE</form:option>
											<c:forEach var="rescte" items="${listaresiscte}">
												<form:option value="${rescte.id}"><c:out value="${rescte.sellos}"/></form:option>
											</c:forEach>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.resistencia_cte" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Cierre</div>
									<div class="col col-lg-1">
										<form:select id="SCierre" path="cotizador_detalles.cierre" multiple="false" class="border border-primary">
											<form:option value="pegada">PEGADA</form:option>
											<form:option value="grapada">GRAPADA</form:option>
											<form:option value="desplegada">DESPLEGADA</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.cierre" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Cierre detalle</div>
									<div class="col col-lg-1">
										<form:select id="SCierreDet" path="cotizador_detalles.cierre_detalle" multiple="false" class="border border-primary">
											<form:option value="nc">N/C</form:option>
											<form:option value="interior">INTERIOR</form:option>
											<form:option value="exterior">EXTERIOR</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.cierre_detalle" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Ceja despl: <form:checkbox id="CCejaDesp" path="cotizador_detalles.ceja_desplegada"/></div>
									<div class="col col-lg-1">Entrega OC</div>
									<div class="col col-lg-2">
										<div class="">
										<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
						                    <form:input id="TFechaOC" onkeypress="return false" path="cotizador.fecha_entrega_oc" size="10" data-target="#datetimepicker4" placeholder="yyyy-mm-dd" class="border border-primary"/>
						                    <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
						                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
						                    </div>
								            <script type="text/javascript">
									            $(function () {
									                $('#datetimepicker4').datetimepicker({
									                    format: 'YYYY-MM-DD'
									                });
									            });
									        </script>
							            </div>
							            </div>
									</div>
									<form:input id="TCostoPapelResis" size="10" readonly = "true" type="hidden" path="cotizador_detalles.costo_papel_resis" class="border border-secondary"/>
								</div>
								<div class="row border border-right">
									<div class="col col-lg-1">ComisiónMillar</div>
									<div class="col col-lg-1">
										<form:input id="TComxMillar" size="10" readonly = "true" type="text" path="cotizador_detalles.comisionxmillar" class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.comisionxmillar" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Observaciones</div>
									<div class="col-sm-3">
										<form:input id="TObs" type="text" size="40" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="cotizador_detalles.observaciones_vendedor" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.observaciones_vendedor" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Esp Inf</div>
									<div class="col col-lg-1">
										<form:input onKeyUp="CalcularDatos()" id="TEspInf" size="10" type="text" path="cotizador_detalles.esp_inf" onkeypress="return filterFloat2(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.esp_inf" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Esp Sup</div>
									<div class="col col-lg-1">
										<form:input onKeyUp="CalcularDatos()" id="TEspSup" size="10" type="text" path="cotizador_detalles.esp_sup" onkeypress="return filterFloat2(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.esp_sup" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Peso Resis</div>
									<div class="col col-lg-1">
										<form:input id="TPesoResis" size="10" ondblclick="FBuscarResisId()" readonly = "true" type="text" path="cotizador_detalles.peso_resis" class="border border-secondary"
													data-toggle="tooltip" data-placement="top" title="Doble click para actualizar."/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.peso_resis" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col col-lg-1">$ Objetivo</div>
									<div class="col col-lg-1">
										<form:input onKeyUp="CalcularDatos()" id="TPreciObj" size="10" type="text" path="cotizador_detalles.precio_objetivo" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.precio_objetivo" class="badge badge-danger small"/>
										</div>
									</div>
									
									<!-- <div class="col-sm-1">Costo tarima</div>
									<div class="col-sm-1">???</div>  -->
									<div class="col col-lg-1">Imp rebasada</div>
									<div class="col col-lg-1">
										<form:select onChange="CalcularDatos()" id="SScore" path="cotizador_detalles.score" multiple="false" class="border border-primary">
											<form:option value="0">0</form:option>
											<form:option value="1">1</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.score" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Med Lámina</div>
									<div class="col col-lg--1">
										<form:input readonly="true" id="TMedLamina" type="text" size="10" path="cotizador_detalles.medida_lamina"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.medida_lamina" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Área Unitaria</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TAreaUni" type="text" size="10" path="cotizador_detalles.area_unitaria"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.area_unitaria" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Costo flete</div>
									<div class="col col-lg-1">
										<form:input id="TCostoFlete" size="10" readonly = "true" type="text" path="cotizador_detalles.costo_flete" class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.costo_flete" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">% Comisión</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TPorcCom" type="text" size="10" path="cotizador_detalles.porcentaje_comision"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.porcentaje_comision" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col col-lg-1">M2</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TM2" type="text" size="10" path="cotizador_detalles.m2"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.m2" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Kg</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TKg" type="text" size="10" path="cotizador_detalles.kg"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.kg" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Peso teórico</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TPesoT" type="text" size="10" path="cotizador_detalles.peso_teorico"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.peso_teorico" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">$ Neto</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TPrecioN" type="text" size="10" path="cotizador_detalles.precio_neto"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.precio_neto" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">$ Sugerido</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TPrecioS" type="text" size="10" path="cotizador_detalles.precio_sugerido"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.precio_sugerido" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Costo papel</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TCostoPapel" type="text" size="10" path="cotizador_detalles.costo_papel"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.costo_papel" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col col-lg-1">Desc Vend</div>
									<div class="col col-lg-1">
										<form:input ondblclick="FBuscarResisId()" readonly="true" id="TDescVen" type="text" size="10" path="cotizador_detalles.descuento_vendedor"  class="border border-secondary"
													data-toggle="tooltip" data-placement="top" title="Doble click para actualizar." />
										<div class="has-error">
											<form:errors path="cotizador_detalles.descuento_vendedor" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Peso pieza</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TPespPza" type="text" size="10" path="cotizador_detalles.peso_pieza"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.peso_pieza" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Ref comisión</div>
									<div class="col col-lg-1">
										<form:input readonly="true" id="TRefCom" type="text" size="10" path="cotizador_detalles.ref_para_comision"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.ref_para_comision" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Pzas x juego</div>
									<div class="col col-lg-1">
										<form:input onKeyUp="CalcularDatos()" id="TPzasxjgo" size="10" value="${empty cotizadordatabean.cotizador_detalles.iddetalle ? 1 : cotizadordatabean.cotizador_detalles.iddetalle == 1 ? 1: ''}"
											readonly="${empty cotizadordatabean.cotizador.id ? 'true' : cotizadordatabean.cotizador_detalles.iddetalle == 1 ? 'true': 'false'}" 
											maxlength="8" type="text" path="cotizador_detalles.piezasxjuego" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.piezasxjuego" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Pedido Mes</div>
									<div class="col col-lg-1">
										<form:input onKeyUp="CalcularDatos()" id="TCantPedMes" size="10" maxlength="8" type="text" path="cotizador_detalles.cantidad_pedido_mes" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.cantidad_pedido_mes" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Num ranuras</div>
									<div class="col col-lg-1">
										<form:input id="TNumRanuras" size="10" maxlength="8" type="text" path="cotizador_detalles.num_raturas" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.num_raturas" class="badge badge-danger small"/>
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
										<div class="float-right">
											<button id="BCodBarras" type="button" data-toggle="modal" data-target="#CodigoBarras" class="float-right btn btn-outline-primary btn-sm"><i class="fa fa-barcode" aria-hidden="true"> Código de barras</i></button>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col col-lg-1">Se entregó</div>
									<div class="col col-lg-1">
										<form:select id="SEntrego" path="cotizador.se_entrego" multiple="false" class="border border-primary">
											<form:option value="nc">N/C</form:option>
											<form:option value="Muestra">Muestra</form:option>
											<form:option value="Software">Software</form:option>
											<form:option value="Email">Email</form:option>
											<form:option value="Sin entrega">Sin entrega</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador.se_entrego" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Tolerancia</div>
									<div class="col col-lg-1">
										<form:select id="STolerancia" path="cotizador.tolerancia_pedido" multiple="false" class="border border-primary">
											<form:option value="0">0</form:option>
											<form:option value="5">5</form:option>
											<form:option value="10">10</form:option>
										</form:select> 
									</div>
									<div class="col col-lg-4">Diseño:
										<form:select id="SDisenio" onChange="FDisenio()" path="cotizador.disenio" multiple="false" class="border border-primary">
											<form:option value="Nuevo">Nuevo</form:option>
											<form:option value="Con cambios">Con cambios</form:option>
											<form:option value="Referencia de TF">Referencia de TF</form:option>
										</form:select>
										Cancel/Sust: <form:checkbox id="CCancSust" path="cotizador.cancelar_sustituir"/>
										TF: <form:input id="TTF" size="10" maxlength="8" type="text" path="cotizador.tf_cs" onkeypress="return SinCaracteresEspeciales(event);" class="border border-primary"/>
									</div>
									<div class="col col-lg-2">
										Fecha cancelación TF:
									</div>
									<div class="col col-lg-2">
										<div class="input-group date" id="datetimepicker5" data-target-input="nearest">										
						                    <form:input id="TFechaCancelTF" onkeypress="return false" path="cotizador.fecha_cancel_tf" size="10" data-target="#datetimepicker5" placeholder="yyyy-mm-dd" class="border border-primary"/>
						                    <div class="input-group-append" data-target="#datetimepicker5" data-toggle="datetimepicker">
						                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
						                    </div>
								            <script type="text/javascript">
									            $(function () {
									                $('#datetimepicker5').datetimepicker({
									                    format: 'YYYY-MM-DD'
									                });
									            });
									        </script>
							            </div>										
									</div>									
								</div>
							</div>
						</div>	
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
				 <div class="row ">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col col-lg-2">Emplayado: <form:checkbox id="CEmplayado" path="cotizador.emplayado"/></div> 
									<div class="col col-lg-2">Vueltas: <form:input id="TVueltasEmp" size="5" maxlength="8" type="text" path="cotizador.vueltas_emplaye" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col col-lg-1">Factura: <form:checkbox id="CFactura" path="cotizador.factura"/></div>
									<div class="col col-lg-2">Certif calidad: <form:checkbox id="CCertCal" path="cotizador.certif_calidad"/></div>
									<div class="col col-lg-1">Imp OC: <form:checkbox id="CImpOC" path="cotizador.imprimir_oc"/></div>
									<div class="col col-lg-2">Protecciones: <form:checkbox id="CProtecciones" path="cotizador.protecciones"/></div>
									<div class="col col-lg-2">Imp fechador: <form:checkbox id="CImpFech" path="cotizador.imprimir_fechador"/></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col col-lg-1">Caja seca: <form:checkbox id="CCajaSeca" path="cotizador.caja_seca"/></div>					
									<div class="col col-lg-2">Imprimir pedido: <form:checkbox id="CImpPed" path="cotizador.imprimir_pedido"/></div>
									<div class="col col-lg-2">TarimaXunitizado: <form:checkbox id="CTarxUni" path="cotizador.tarimaxunitizado"/></div>
									<div class="col col-lg-2">Certif fumigación: <form:checkbox id="CCertFum" path="cotizador.certif_fumig"/></div>
									<div class="col col-lg-2">EPP transportista: <form:checkbox id="CEPP" path="cotizador.epp_transportista"/></div>
									<div class="col col-lg-1">A granel: <form:checkbox id="CAGranel"  path="cotizador_detalles.agranel" onchange="FEmbarques(1)"/></div>
									<div class="col col-lg-1">Altura pallet</div>
									<div class="col col-lg-1">
										<form:input id="TAltPallet" size="10" maxlength="8" type="text" 
										path="cotizador_detalles.altura_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col col-lg-1">Pzas tarima</div>
									<div class="col col-lg-1">
										<form:input id="TPzasxTar" onKeyUp="CalcularDatos()" size="10" maxlength="8" type="text" 
											path="cotizador_detalles.piezasxtarima" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.piezasxtarima" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col col-lg-1">Flejes atado</div>
									<div class="col col-lg-1"><form:input id="TFlejesAtado" size="10" maxlength="8" type="text" path="cotizador_detalles.flejes_atado" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col col-lg-1">Pzas atado</div>
									<div class="col col-lg-1"><form:input id="TPzasAtado" size="10" maxlength="8" type="text" path="cotizador_detalles.pzas_atado" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col col-lg-1">Atados cama</div>
									<div class="col col-lg-1"><form:input id="TAtaCama" size="10" maxlength="8" type="text" path="cotizador_detalles.atados_cama" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col col-lg-1">Camas pallet</div>
									<div class="col col-lg-1"><form:input id="TCamasPallet" size="10" maxlength="8" type="text" path="cotizador_detalles.camas_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col col-lg-1">Flejes pallet</div>
									<div class="col col-lg-1"><form:input id="TFlejesPallet" size="10" maxlength="8" type="text" path="cotizador_detalles.flejes_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>			
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="badge badge-success col-12">
					 	Especialidades
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div id="DEsp" class="row border border-right">
									<c:forEach var="item" items="${especialidades}" varStatus="status">
									<div class="col col-lg-3">
										<form:checkbox id="ChEsp" onChange="CalcularDatos()"
		                              		path="cotizador_detalles.especialidades_cotizacion[${status.index}].idespecialidad" value="${item.code}"/>${item.name}
		<form:input type="hidden" id="TEsquema${item.code}" 
     	path="cotizador_detalles.especialidades_cotizacion[${status.index}].esquema" value="${item.u_esquema}"  class="border border-primary"/>
     	<form:input type="${item.code != 19 ? 'hidden' : !empty cotizadordatabean.cotizador_detalles.especialidades_cotizacion[status.index].ajuste ? (cotizadordatabean.cotizador_detalles.especialidades_cotizacion[status.index].ajuste > 0 ? 'text' : 'hidden') : 'hidden'}" 
     	id="TAjuste${item.code}" onkeypress="return filterFloat(event,this);" onKeyUp="CalcularDatos();" size="10" maxlength="8" placeholder="ajuste"
     	path="cotizador_detalles.especialidades_cotizacion[${status.index}].ajuste" value="${item.code == 19 ? cotizadordatabean.cotizador_detalles.especialidades_cotizacion[status.index].ajuste : item.u_ajuste}"  class="border border-primary"/>
     	
     	<form:input type="hidden" id="TIdcot${item.code}" 
     	path="cotizador_detalles.especialidades_cotizacion[${status.index}].idcotizacion" value="${cotizadordatabean.cotizador.id}"  class="border border-primary"/>
     	<form:input type="hidden" id="TIdDet${item.code}" 
     	path="cotizador_detalles.especialidades_cotizacion[${status.index}].iddetalle" value="${cotizadordatabean.cotizador_detalles.iddetalle}"  class="border border-primary"/>
     	
     	<form:input type="${!empty cotizadordatabean.cotizador_detalles.especialidades_cotizacion[status.index].cm ? 'text' : 'hidden'}"
     	onkeypress="return filterFloat(event,this);"
     	onKeyUp="CalcularDatos()" 
     	id="TCM${item.code}" size="10" maxlength="8" placeholder="CM"
     	path="cotizador_detalles.especialidades_cotizacion[${status.index}].cm" value="${cotizadordatabean.cotizador_detalles.especialidades_cotizacion[status.index].cm}"  class="border border-primary"/>
     	
								     	<c:choose>
								     		<c:when test="${item.name == 'Bolsa'}">
									     		<form:select id="TCosto${item.code}" 
									     		style="${!empty cotizadordatabean.cotizador_detalles.especialidades_cotizacion[status.index].costo ? (cotizadordatabean.cotizador_detalles.especialidades_cotizacion[status.index].costo > 0 ? 'visibility:visible' : 'visibility:hidden') : 'visibility:hidden'}"
									     		path="cotizador_detalles.especialidades_cotizacion[${status.index}].costo"
									     		 multiple="false" class="border border-primary" onChange="CalcularDatos()">
													<form:option value="">---</form:option>
													<c:forEach var="it" items="${bolsas}">
														<form:option value="${it.u_ajuste}"><c:out value="${it.name}-${it.u_ajuste} "/></form:option>
													</c:forEach>
												</form:select>
								  			</c:when>
								  			<c:otherwise>
									  			<form:input size="10" id="TCosto${item.code}" maxlength="8" readonly="${item.u_esquema == '8' ? 'false' : 'true'}" 
									     		type="${!empty cotizadordatabean.cotizador_detalles.especialidades_cotizacion[status.index].costo ? 'text' : 'hidden'}"  
									     		path="cotizador_detalles.especialidades_cotizacion[${status.index}].costo" onKeyUp="${item.u_esquema == '8' ? 'CalcularDatos()' : ''}" 
									     		onkeypress="return filterFloat(event,this);" class="border border-primary"/>
								  			</c:otherwise>
								     	</c:choose>
								     	
		                              	</div>
		                           	</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		 </div>
		 
		 <form:input id="TComisionDir" type="hidden" path="cotizador_detalles.comision_directo"/>
		 <form:input id="TCpcc" type="hidden" path="cotizador_detalles.cpcc"/>
		 <form:input id="TPorcFlete" type="hidden" path="cotizador_detalles.porc_flete"/>
		 <form:input id="TLargoPliego" type="hidden" path="cotizador_detalles.largo_pliego"/>
		 <form:input id="TAnchoPliego" type="hidden" path="cotizador_detalles.ancho_pliego"/>
		 <form:input id="TTotEsp" type="hidden" path="cotizador_detalles.total_especialidades"/>
		 <form:input id="TAreaTotal" type="hidden" path="cotizador_detalles.area_total"/>
		 <form:input id="TPesoTotal" type="hidden" path="cotizador_detalles.peso_juego"/>
		 <form:input id="TPkTeorico" type="hidden" path="cotizador_detalles.pk_teorico"/>
		 
		 <form:input type="hidden" path="cotizador.usuario_envia_ventas"/>
		 <form:input type="hidden" path="cotizador.usuario_aut_ventas"/>
		 <form:input type="hidden" path="cotizador.usuario_rech_ventas"/>
		 <form:input type="hidden" path="cotizador.usuario_envia_a_prog"/>
		 <form:input type="hidden" path="cotizador.usuario_aut_prog"/>
		 <form:input type="hidden" path="cotizador.usuario_rech_prog"/>		 
		 <form:input type="hidden" path="cotizador.usuario_cancel"/>
		 		 
		 <form:input type="hidden" path="cotizador.fecha_aut_ventas"/>		 
		 <form:input type="hidden" path="cotizador.fecha_rech_ventas"/>		 
		 <form:input type="hidden" path="cotizador.fecha_aut_prog"/>		 
		 <form:input type="hidden" path="cotizador.fecha_rech_prog"/>		 
		 <form:input type="hidden" path="cotizador.fecha_envia_ventas"/>		 
		 <form:input type="hidden" path="cotizador.fecha_envia_a_prog"/> 
		 <form:input type="hidden" path="cotizador.fecha_cancel"/>
		  
		 <form:input type="hidden" path="cotizador.observaciones_ventas"/>
		 <form:input type="hidden" path="cotizador.observaciones_prog"/>
		 
		<div align="left" class = "container">
		<div class = "row" align="center">			
			<div class="col col-lg-2"><form:button id="BGrabar" class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
			<div class="col col-lg-2"><a href="javascript:FBuscar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
			<div class="col col-lg-2"><button type="button" data-toggle="modal" data-target="#LimpiarModal" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></button></div>
			<div class="col col-lg-4"><button id="BEnvVtas" type="button" data-toggle="modal" data-target="#VtaModal" class="btn btn-outline-primary btn-sm"><i class="fa fa-paper-plane-o" aria-hidden="true"> Enviar para autorización</i></button></div>
			<div class="col col-lg-2"><button id="BCancel" type="button" data-toggle="modal" data-target="#CancelModal" class="btn btn-outline-primary btn-sm"><i class="fa fa-times-circle-o" aria-hidden="true"> Cancelar</i></button></div>
		</div>
		</div>
		
			<!-- REGION DE MODALS -->
		<div class="modal fade bd-example-modal-lg" id="CodigoBarras" tabindex="-1" role="dialog" aria-labelledby="CodigoBarrasLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header alert alert-info">
		        <h5 class="modal-title">AGREGAR CÓDIGO DE BARRAS</h5>
		      </div>
		      <div class="modal-body">
		        <table class="table table-sm table-bordered table-hover">
		        	<thead>
		        		<tr>
		        			<th>Código de barras</th>
		        			<th>Comentarios</th>
		        		</tr>
		        	</thead>
		        	<tbody id="TBodyCodBarras">
		        	<c:forEach var="item" items="${cotizadordatabean.cotizador_detalles.codigo_barra_cotizador}" varStatus="status">
		        		<tr>
		        			<td>
		        				<form:input type="text" maxlength="50" id="TCodBarras${status.index}" onkeypress="return SinCaracteresEspeciales(event)" path="cotizador_detalles.codigo_barra_cotizador[${status.index}].idcodigo" value="${item.idcodigo}"  class="border border-primary"/>
		        			</td>
		        			<td>
		        				<form:input type="text" maxlength="100" id="TCodComent${status.index}" onkeypress="return SinCaracteresEspeciales(event)" path="cotizador_detalles.codigo_barra_cotizador[${status.index}].observaciones" value="${item.observaciones}"  class="border border-primary"/>
		        			</td>
		        		</tr>
		        	</c:forEach>
		        	</tbody>
		        </table>
		      </div>
		      <div class="modal-footer">
		        <button id="BAddFila" type="button" class="btn btn-primary" onClick="FAddFila()">Agregar fila</button>
		        <button id="BDelFila" type="button" class="btn btn-warning" onClick="FDelFila()">Borrar fila</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		      </div>
		    </div>
		  </div>
		</div>			
	</form:form>
		<jsp:include page="cotizador_modals.jsp" />
	</div>
	<jsp:include page="../../appconfig/authfootter.jsp" />
	</body>
</html>