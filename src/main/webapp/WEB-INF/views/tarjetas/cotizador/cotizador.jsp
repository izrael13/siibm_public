<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function FBuscarDirecciones()
{
	var cardcode = document.getElementById("SClientes").value;
	var opciones = "";
	$.ajax({
		//dataType: 'text',
		url: '<c:url value="/ventas/tarjetas/cotizador/buscardirecciones"/>?cardcode='+cardcode,
		//contentType : 'application/json',
		//cache: false,    
		//data: cve_estado,
		beforeSend: function(xhr) {
							  $("#imgload").show();
							  $("#TFlete").val(0);
							  $("#DContacto").text("");
				        	  $("#DTelefono").text("");
				        	  $("#DEmail").text("");
							  $("#direcciones" ).empty();
							  $("#mensajes" ).text("");
							  $("#mensajes").removeClass();
					        },	
        success : function(data) {
        	//try
        	//{
        		if (data.search(/Login page/i) != -1) {
        			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }
	        	opciones = opciones + "<option value='"+0+ "'> - - - </option>";
	        	$.each(jQuery.parseJSON(data),function(index, value){
	        	    //console.log('My array has at position ' + index + ', this value: ' + value.nombre);
	        		opciones = opciones + "<option value='"+value.linenum + "'>"+value.address +" - "+value.direccion + "</option>";
	        	});
	        	
	        	$( "#direcciones" ).append(opciones);
	        	$( "#imgload").hide();
        	//}
        	//catch(e)
        	//{
        		//window.location.replace('<c:url value="/principal"/>');
        	//}
        },
        error: function(xhr, status, error) {
        	  $( "#direcciones" ).empty();
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
			  $( "#mensajes").removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
}
function FBuscarInfoDir()
{
	//alert(":)");
	var cardcode = document.getElementById("SClientes").value;
	var linenum = document.getElementById("direcciones").value;
	
	$.ajax({
		//dataType: 'text',
		url: '<c:url value="/ventas/tarjetas/cotizador/buscarinfodir"/>?cardcode='+cardcode+'&linenum='+linenum,
		//contentType : 'application/json',
		//cache: false,    
		//data: cve_estado,
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
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
        	$.each(jQuery.parseJSON(data),function(index, value){
        	    //console.log('My array has at position ' + index + ', this value: ' + value.nombre);
        		//opciones = opciones + "<option value='"+value.linenum + "'>"+value.address +" - "+value.direccion + "</option>";
        		$("#TFlete").val(value.flete);
        		if(value.flete == 0)
        		{
        			$("#mensajes" ).text("Este destino no tiene valor de flete.");
        			$("#mensajes").removeClass().addClass("alert alert-danger");
        		}
        		$("#DContacto").text(value.contacto);
        		$("#DTelefono").text(value.telefono);
        		$("#DEmail").text(value.email);
        		 CalcularDatos();
        	});
        	
        	$("#imgload").hide();
        },
        error: function(xhr, status, error) {
        	  $("#TFlete").val(0);
        	  $("#DContacto").text("");
        	  $("#DTelefono").text("");
        	  $("#DEmail").text("");
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
			  $( "#mensajes").removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
	
}
function FLimpar()
{
	window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc"/>?id=0');
}
function FBuscar()
{
	if($( "#TId" ).val() =="")
	{
		$( "#TId" ).val("0");
	}
	
	var isDisabled = $("#BGrabar").prop('disabled');
	$( "#TId" ).prop( "readonly", false );
	$( "#BGrabar" ).prop( "disabled", true );
	if(($( "#TId" ).val() > 0 || $( "#SClientes" ).val() != "0") && isDisabled == true)
	{
		popupwindow('<c:url value="/ventas/tarjetas/cotizador/cotizadorbusqueda" />?id='+$( "#TId" ).val()+'&cardcode='+$( "#SClientes" ).val(),'Detalle de viaje',800,1000);
	}

		$( "#TId").removeClass().addClass("border border-danger");
		$( "#SClientes").removeClass().addClass("border border-danger");
	
}
function FBuscarxId(id)
{
	window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc" />?id='+id);
}
function FBuscarResisId()
{
	//alert(":)");
	var idresis = $("#SResisBarca" ).val();
	
	$.ajax({
		//dataType: 'text',
		url: '<c:url value="/ventas/tarjetas/cotizador/buscarinforesistenciabarca"/>?id='+idresis,
		//contentType : 'application/json',
		//cache: false,    
		//data: cve_estado,
		beforeSend: function(xhr) {
							  $("#imgload").show();
							  $("#TPreciom2resis").val(0);
							  $("#TPesoResis").val(0);
							  $("#TDescVen").val(0);
							  $("#TCostoPapelResis").val(0);
							  $("#mensajes" ).text("");
							  $("#mensajes").removeClass();
					        },	
        success : function(data) {
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
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
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
			  $( "#mensajes").removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
}

function SumarEsp(id)
{
	$.ajax({
		//dataType: 'text',
		url: '<c:url value="/ventas/tarjetas/cotizador/calcular_especialidades"/>?id='+id+'&ajuste='+$("#TAjuste"+id).val()+'&esquema='+$("#TEsquema"+id).val(),
		//contentType : 'application/json',
		//cache: false,    
		//data: cve_estado,
		beforeSend: function(xhr) {
							  $("#imgload").show();
							  $("#mensajes" ).text("");
							  $("#mensajes").removeClass();
					        },	
        success : function(data) {
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
        	alert(data);
/*   			var obj = JSON.parse(data);
        	if(obj != null)
        	{
	        	
        	}
        	else
        	{
				 
        	} */
        	
        	var TotCosto = 0.0;
        	$("input[id='ChEsp']").each(function (){
        		if($(this).prop('checked'))
        		{
        			$("#TCantidad"+$(this).val()).attr("type","text");
        			$("#TCosto"+$(this).val()).attr("type","text");
        			TotCosto = parseFloat(TotCosto) + parseFloat($("#TCosto"+$(this).val()).val() === "" ? 0 : $("#TCosto"+$(this).val()).val());
        		}
        		else
        		{
        			$("#TCantidad"+$(this).val()).attr("type","hidden");
        			$("#TCosto"+$(this).val()).attr("type","hidden");
        			$("#TCantidad"+$(this).val()).val("");
        			$("#TCosto"+$(this).val()).val("");
        		}
        		
        	}); 
        	alert(TotCosto);
        	
        	$("#imgload").hide();
        },
        error: function(xhr, status, error) {
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
			  $( "#mensajes").removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
	
}

function CalcularDatos()
{
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
	Parameters.costoespcialidades = 0;
	Parameters.totalflete = ($("#TFlete").val() === "" ? 0 : $("#TFlete").val());
	Parameters.cardcode = $("#SClientes").val();
	Parameters.pesoresis = ($("#TPesoResis").val() === "" ? 0 : $("#TPesoResis").val());
	Parameters.preciom2resis = ($("#TPreciom2resis").val() === "" ? 0 : $("#TPreciom2resis").val());
	Parameters.descven = ($("#TDescVen").val() === "" ? 0 : $("#TDescVen").val());
	Parameters.idcaja = $("#SCajas").val();
	Parameters.costopapelresis = ($("#TCostoPapelResis").val() === "" ? 0 : $("#TCostoPapelResis").val());
	
	var mystring = JSON.stringify(Parameters);
	
	$.ajax({
		//dataType: 'text',
		url: '<c:url value="/ventas/tarjetas/cotizador/calculardatos"/>?mystring='+encodeURI(mystring),
		//contentType : 'application/json',
		//cache: false,    
		//data: cve_estado,
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
					        },	
        success : function(data) {
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
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
        	}
        	else
        	{
				
        	}
        	$("#imgload").hide();
        },
        error: function(xhr, status, error) {
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
			  $( "#mensajes").removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
	
}
</script>
<title>Registro cotizaciones</title>
<%@include file="../../appconfig/authheader2.jsp"%>
</head>
	<body>
	<!-- <div align="center">
		<span class="badge badge-secondary">Registro cotizaciones</span>
	</div> -->
	<div align="center" class = "container-fluid">
	 <form:form id="form" method="POST" modelAttribute="cotizadordatabean" class="form-horizontal" autocomplete="off">
		<div class="row">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row justify-content-md-center">
					 <div class="badge badge-primary col-12">
					 	Datos del cliente
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row justify-content-md-center">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col-sm-1">Folio</div>
									<div class="col-sm-1"><form:input class="border border-secondary" size="9" maxlength="9" onkeypress="return Enteros(event)" id="TId" readonly="true" type="text" value="${empty cotizadordatabean.cotizador.id ? 0 : cotizadordatabean.cotizador.id}"  path="cotizador.id"/></div>
									<div class="col-sm-1">Cliente</div>
									<div class="col-sm-7">
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
									<div class="col-1 font-weight-bold">Flete</div>
									<div class="col-1">
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
									<div class="col-sm-1">Dirección</div>
									<div class="col-sm-11">
										<div class ="input-group">
											<form:select Onchange="FBuscarInfoDir()" path="cotizador.linenum_dir_entrega" id = "direcciones" multiple="false" class="border border-primary">
												<form:option value="0"> - - - </form:option>
												<c:forEach var="dir" items="${direcciones}">
													<form:option value="${dir.linenum}"><c:out value="${dir.address} - ${dir.direccion}"/></form:option>
												</c:forEach>
											</form:select>
											<div class="has-error">
												<form:errors path="cotizador.linenum_dir_entrega" class="badge badge-danger small"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col-1">Contacto</div>
									<div class="col-3"><div id="DContacto">${direccionSelect[0].contacto}</div></div>
									<div class="col-1">Teléfono</div>
									<div class="col-3"><div id="DTelefono">${direccionSelect[0].telefono}</div></div>
									<div class="col-1">Email</div>
									<div class="col-3"><div id="DEmail">${direccionSelect[0].email}</div></div>
								</div>
							</div>
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col-1">Fecha Alta</div>
									<div class="col-3"><fmt:formatDate value="${cotizadordatabean.cotizador.fecha_insert}" pattern="yyyy-MM-dd hh:mm:ss"/></div>
									<div class="col-2">Fecha Actualización</div>
									<div class="col-3"><fmt:formatDate value="${cotizadordatabean.cotizador.fecha_update}" pattern="yyyy-MM-dd hh:mm:ss"/></div>
								</div>
							</div>
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col-2">Observaciones Ventas:</div>
									<div class="col-4"><fmt:formatDate value="${cotizadordatabean.cotizador.observaciones_ventas}" pattern="yyyy-MM-dd hh:mm:ss"/></div>
									<div class="col-2">Observaciones Ingeniería:</div>
									<div class="col-4"><fmt:formatDate value="${cotizadordatabean.cotizador.observaciones_prog}" pattern="yyyy-MM-dd hh:mm:ss"/></div>
								</div>
							</div>
						</div>
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row justify-content-md-center">
					 <div class="badge badge-info col-12">
					 	Detalles de caja
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row justify-content-md-center">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col-sm-1">
										<form:input value="${empty cotizadordatabean.cotizador_detalles.iddetalle ? 0 : cotizadordatabean.cotizador_detalles.iddetalle}" class="border border-secondary" size="9" maxlength="9" onkeypress="return Enteros(event)" readonly="true" type="text"  path="cotizador_detalles.iddetalle"/>
									</div>
									<div class="col-sm-1">Símbolo</div>
									<div class="col-sm-3">
										<form:input type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="cotizador_detalles.simbolo" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.simbolo" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Caja</div>
									<div class="col-sm-6">
										<form:select onChange="CalcularDatos()" id="SCajas" path="cotizador_detalles.idcaja_sap" multiple="false" class="border border-primary">
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
									<div class="col-sm-1">Largo</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TLargo" size="10" type="text" path="cotizador_detalles.largo" onkeypress="return filterFloat1(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.largo" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Ancho</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TAncho" size="10" type="text" path="cotizador_detalles.ancho" onkeypress="return filterFloat1(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.ancho" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Fondo</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TFondo" size="10" type="text" path="cotizador_detalles.fondo" onkeypress="return filterFloat1(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.fondo" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Resistencia</div>
									<div class="col-sm-3">
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
									<div class="col-sm-1">Precio M2</div>
									<div class="col-sm-1">
										<form:input ondblclick="FBuscarResisId()" readonly="true" id="TPreciom2resis" type="text" size="10" path="cotizador_detalles.preciom2resistencia"  class="border border-secondary"
													data-toggle="tooltip" data-placement="top" title="Doble click para actualizar."/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.preciom2resistencia" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col-sm-1">ResistenciaCTE</div>
									<div class="col-sm-2">
										<form:select path="cotizador_detalles.resistencia_cte" multiple="false" class="border border-primary">
											<form:option value="0">Seleccione resistencia CTE</form:option>
											<c:forEach var="rescte" items="${listaresiscte}">
												<form:option value="${rescte.id}"><c:out value="${rescte.sellos}"/></form:option>
											</c:forEach>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.resistencia_cte" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Cierre</div>
									<div class="col-sm-1">
										<form:select path="cotizador_detalles.cierre" multiple="false" class="border border-primary">
											<form:option value="pegada">PEGADA</form:option>
											<form:option value="grapada">GRAPADA</form:option>
											<form:option value="desplegada">DESPLEGADA</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.cierre" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Cierre detalle</div>
									<div class="col-sm-1">
										<form:select path="cotizador_detalles.cierre_detalle" multiple="false" class="border border-primary">
											<form:option value="nc">N/C</form:option>
											<form:option value="interior">INTERIOR</form:option>
											<form:option value="exterior">EXTERIOR</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.cierre_detalle" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Pzas x juego</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TPzasxjgo" size="10" value="${empty cotizadordatabean.cotizador_detalles.iddetalle ? 1 : cotizadordatabean.cotizador_detalles.iddetalle == 1 ? 1: ''}"
											readonly="${empty cotizadordatabean.cotizador_detalles.iddetalle ? 'true' : cotizadordatabean.cotizador_detalles.iddetalle == 1 ? 'true': 'false'}" 
											maxlength="10" type="text" path="cotizador_detalles.piezasxjuego" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.piezasxjuego" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Peso Resis</div>
									<div class="col-sm-1">
										<form:input id="TPesoResis" size="10" ondblclick="FBuscarResisId()" readonly = "true" type="text" path="cotizador_detalles.peso_resis" class="border border-secondary"
													data-toggle="tooltip" data-placement="top" title="Doble click para actualizar."/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.peso_resis" class="badge badge-danger small"/>
										</div>
									</div>
									<form:input id="TCostoPapelResis" size="10" readonly = "true" type="text" path="cotizador_detalles.costo_papel_resis" class="border border-secondary"/>
								</div>
								<div class="row border border-right">
									<div class="col-sm-1">ComisiónMillar</div>
									<div class="col-sm-1">
										<form:input id="TComxMillar" size="10" readonly = "true" type="text" path="cotizador_detalles.comisionxmillar" class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.comisionxmillar" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Observaciones</div>
									<div class="col-sm-3">
										<form:input type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="cotizador_detalles.observaciones_vendedor" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.observaciones_vendedor" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Esp Inf</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TEspInf" size="10" type="text" path="cotizador_detalles.esp_inf" onkeypress="return filterFloat2(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.esp_inf" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Esp Sup</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TEspSup" size="10" type="text" path="cotizador_detalles.esp_sup" onkeypress="return filterFloat2(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.esp_sup" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Pedido Mes</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TCantPedMes" size="10" maxlength="10" type="text" path="cotizador_detalles.cantidad_pedido_mes" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.cantidad_pedido_mes" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col-sm-1">$ Objetivo</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TPreciObj" size="10" type="text" path="cotizador_detalles.precio_objetivo" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.precio_objetivo" class="badge badge-danger small"/>
										</div>
									</div>
									
									<!-- <div class="col-sm-1">Costo tarima</div>
									<div class="col-sm-1">???</div>  -->
									<div class="col-sm-1">Score</div>
									<div class="col-sm-1">
										<form:select onChange="CalcularDatos()" id="SScore" path="cotizador_detalles.score" multiple="false" class="border border-primary">
											<form:option value="0">0</form:option>
											<form:option value="1">1</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.score" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">N Tintas</div>
									<div class="col-sm-1">
										<form:select path="cotizador_detalles.num_tintas" multiple="false" class="border border-primary">
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
									<div class="col-sm-1">Med Lámina</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TMedLamina" type="text" size="10" path="cotizador_detalles.medida_lamina"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.medida_lamina" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Área Unitaria</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TAreaUni" type="text" size="10" path="cotizador_detalles.area_unitaria"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.area_unitaria" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Costo flete</div>
									<div class="col-sm-1">
										<form:input id="TCostoFlete" size="10" readonly = "true" type="text" path="cotizador_detalles.costo_flete" class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.costo_flete" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col-sm-1">M2</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TM2" type="text" size="10" path="cotizador_detalles.m2"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.m2" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Kg</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TKg" type="text" size="10" path="cotizador_detalles.kg"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.kg" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Peso teórico</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TPesoT" type="text" size="10" path="cotizador_detalles.peso_teorico"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.peso_teorico" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">$ Neto</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TPrecioN" type="text" size="10" path="cotizador_detalles.precio_neto"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.precio_neto" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">$ Sugerido</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TPrecioS" type="text" size="10" path="cotizador_detalles.precio_sugerido"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.precio_sugerido" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Costo papel</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TCostoPapel" type="text" size="10" path="cotizador_detalles.costo_papel"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.costo_papel" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col-sm-1">% Comisión</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TPorcCom" type="text" size="10" path="cotizador_detalles.porcentaje_comision"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.porcentaje_comision" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-2">Descuento vendedor</div>
									<div class="col-sm-1">
										<form:input ondblclick="FBuscarResisId()" readonly="true" id="TDescVen" type="text" size="10" path="cotizador_detalles.descuento_vendedor"  class="border border-secondary"
													data-toggle="tooltip" data-placement="top" title="Doble click para actualizar." />
										<div class="has-error">
											<form:errors path="cotizador_detalles.descuento_vendedor" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Peso pieza</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TPespPza" type="text" size="10" path="cotizador_detalles.peso_pieza"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.peso_pieza" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Ref comisión</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TRefCom" type="text" size="10" path="cotizador_detalles.ref_para_comision"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.ref_para_comision" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Pzas tarima</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" size="10" maxlength="10" type="text" path="cotizador_detalles.piezasxtarima" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.piezasxtarima" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
							</div>
						</div>	
					</div>
				</div>
			</div>
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row justify-content-md-center">
					 <div class="badge badge-success col-12">
					 	Especialidades
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row justify-content-md-center">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<c:set var="i" value="0"/>
									<c:forEach var="item" items="${especialidades}">
		                              <div class="col-sm-4">
		                                 <form:checkbox onChange="SumarEsp(${item.code})"  id="ChEsp" path="cotizador_detalles.especialidades_cotizacion[${i}].idespecialidad" value="${item.code}"/> ${item.name}
		                                 <form:input size="10" id="TCantidad${item.code}" maxlength="10" 
		                                 	type="${!empty cotizadordatabean.cotizador_detalles.especialidades_cotizacion[i].cantidad ? 'text' : !empty cotizadordatabean.cotizador_detalles.especialidades_cotizacion[i].costo ? 'text' : 'hidden'}" 
		                                 	path="cotizador_detalles.especialidades_cotizacion[${i}].cantidad" onkeypress="return Enteros(event);" class="border border-primary"/>
		                                 <form:input size="10" id="TCosto${item.code}" maxlength="10" 
		                                 	type="${!empty cotizadordatabean.cotizador_detalles.especialidades_cotizacion[i].cantidad ? 'text' : !empty cotizadordatabean.cotizador_detalles.especialidades_cotizacion[i].costo ? 'text' : 'hidden'}" 
		                                 	path="cotizador_detalles.especialidades_cotizacion[${i}].costo" onkeypress="return Enteros(event);" class="border border-primary"/>
		                                 <form:input size="10" id="TAjuste${item.code}" maxlength="10" 
		                                 	path="cotizador_detalles.especialidades_cotizacion[${i}].ajuste" readonly="false" value="${item.u_ajuste}"  class="border border-primary"/>
										 <form:input size="10" id="TEsquema${item.code}" maxlength="10" 
		                                 	path="cotizador_detalles.especialidades_cotizacion[${i}].esquema" readonly="false" value="${item.u_esquema}"  class="border border-primary"/>
		                              </div>
		                              <c:set var="i" value="${i = i + 1}"/>
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
		 
		 <span id="imgload" style='display: none;'><img width="20px" height="20px" src='<c:url value="/static/img/sun_watch.gif"/>' /></span>
		 <div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
		<div align="left" class = "container">
		<div class="row" align="center">
			<div class="col-sm-3"></div>
			<div class="col-sm-2"><form:button id="BGrabar" class="btn btn-outline-primary"><i class="fa fa-floppy-o" aria-hidden="true"></i> Grabar</form:button></div>
			<div class="col-sm-2"><a href="javascript:FBuscar()" class="btn btn-outline-primary"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
			<div class="col-sm-2"><a href="javascript:FLimpar()" class="btn btn-outline-primary"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a></div>
		</div>
		</div>
	</form:form>
	</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
	</body>
</html>