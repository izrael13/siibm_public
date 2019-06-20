<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<script>

$(document).ready(function() {
		
	if('${cotizadordatabean.cotizador.id}' > 0)
	{
		if(('${cotizadordatabean.cotizador.usuario_envia_ventas}' > 0 && '${cotizadordatabean.cotizador.fecha_envia_ventas}' != '') || 
		   ('${cotizadordatabean.cotizador.usuario_envia_a_prog}' > 0 && '${cotizadordatabean.cotizador.fecha_envia_a_prog}' != '')||
		   ('${cotizadordatabean.cotizador.usuario_cancel}' > 0  && '${cotizadordatabean.cotizador.fecha_cancel}' != ''))
		{
			$("#SClientes option:not(:selected)").prop("disabled", true);
			$("#direcciones option:not(:selected)").prop("disabled", true);
			$("#SCajas option:not(:selected)").prop("disabled", true);
			$("#SResisBarca option:not(:selected)").prop("disabled", true);
			
			if(('${cotizadordatabean.cotizador.usuario_rech_prog}' == '' && '${cotizadordatabean.cotizador.fecha_rech_prog}' == '') ||
			   ('${cotizadordatabean.cotizador.usuario_cancel}' > 0  && '${cotizadordatabean.cotizador.fecha_cancel}' != ''))
			{
				$("#BCancel").prop('disabled',true);
				$("#BGrabar").prop('disabled',true);
				$("#BEnvVtas").prop('disabled',true);
				
				$("#TSimbolo").attr("readonly","readonly");				
				$("#TLargo").attr("readonly","readonly");
				$("#TAncho").attr("readonly","readonly");
				$("#TFondo").attr("readonly","readonly");
				$("#SResisCte option:not(:selected)").prop("disabled", true);
				$("#SCierre option:not(:selected)").prop("disabled", true);
				$("#SCierreDet option:not(:selected)").prop("disabled", true);
				$("#TPzasxjgo").attr("readonly","readonly");
				$("#TObs").attr("readonly","readonly");
				$("#TEspSup").attr("readonly","readonly");
				$("#TEspInf").attr("readonly","readonly");
				$("#TCantPedMes").attr("readonly","readonly");
				$("#SScore option:not(:selected)").prop("disabled", true);
				$("#SNumTintas option:not(:selected)").prop("disabled", true);			
			
				$("#TPreciObj").attr("readonly","readonly");
				$("#TPzasxTar").attr("readonly","readonly");
				
				var nodes = document.getElementById("DEsp").getElementsByTagName('*');
				for(var i = 0; i < nodes.length; i++)
				{				
					if(nodes[i].type == 'select-one')
						$("#"+nodes[i].id+" option:not(:selected)").prop("disabled", true);
					else
					{
						if(nodes[i].type == 'checkbox')
						{
							if(nodes[i].id != "")
								{
									nodes[i].addEventListener("click", preventDef, false);
									nodes[i].onchange = "";
								}
						}
						else
						{
							if(nodes[i].id != "")
								$("#"+nodes[i].id).prop("readonly", true);
						}						
				    		
					}
				}
				
				$("#SEntrego option:not(:selected)").prop("disabled", true);
				$("#SColor1 option:not(:selected)").prop("disabled", true);
				$("#SColor2 option:not(:selected)").prop("disabled", true);
				$("#SColor3 option:not(:selected)").prop("disabled", true);
				$("#SColor4 option:not(:selected)").prop("disabled", true);
				$("#SColor5 option:not(:selected)").prop("disabled", true);
				$("#SColor6 option:not(:selected)").prop("disabled", true);
				$("#SColor7 option:not(:selected)").prop("disabled", true);
				$("#TNumRanuras").attr("readonly","readonly");
				$("#TFechaOC").attr("readonly","readonly");
				$("#CCejaDesp").bind("click", preventDef, false);
				$("#STolerancia option:not(:selected)").prop("disabled", true);
				$("#SDisenio option:not(:selected)").prop("disabled", true);
				$("#CCancSust").bind("click", preventDef, false);
				$("#TTF").attr("readonly","readonly");
	
				$("#CEmplayado").bind("click", preventDef, false);
				$("#TVueltasEmp").attr("readonly","readonly");
				$("#CFactura").bind("click", preventDef, false);
				$("#CCertCal").bind("click", preventDef, false);
				$("#CImpOC").bind("click", preventDef, false);
				$("#CProtecciones").bind("click", preventDef, false);
				$("#CCajaSeca").bind("click", preventDef, false);
				$("#CCertFum").bind("click", preventDef, false);
				$("#CEPP").bind("click", preventDef, false);
				$("#TAltPallet").attr("readonly","readonly");
				$("#TCamasPallet").attr("readonly","readonly");
				$("#TFlejesPallet").attr("readonly","readonly");
				$("#TFlejesAtado").attr("readonly","readonly");
				$("#TPzasAtado").attr("readonly","readonly");
				$("#TAtaCama").attr("readonly","readonly");
			}
			
		}
		
		if(('${cotizadordatabean.cotizador.usuario_cancel}' == ''  && '${cotizadordatabean.cotizador.fecha_cancel}' == '') && 
		   ('${cotizadordatabean.cotizador.usuario_rech_ventas}' > 0  && '${cotizadordatabean.cotizador.fecha_rech_ventas}' != ''))
		{
			$("#TPreciObj").attr("readonly",false);
			$("#TPzasxTar").attr("readonly",false);
			$("#BEnvVtas").prop('disabled',false);
			$("#BGrabar").prop('disabled',false);
			$("#BCancel").prop('disabled',false);
		}
	} 
});
function preventDef(event) {
	  event.preventDefault();
	}
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
        		if (data.search(/Login page/i) != -1) {
        			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }
	        	opciones = opciones + "<option value='"+0+ "'> - - - </option>";
	        	$.each(jQuery.parseJSON(data),function(index, value){
	        		opciones = opciones + "<option value='"+value.linenum + "'>"+value.address +" - "+value.direccion + "</option>";
	        	});
	        	
	        	$( "#direcciones" ).append(opciones);
	        	$( "#imgload").hide();
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
        		$("#TFlete").val(value.flete);
        		if(value.flete == 0)
        		{
        			$("#mensajes" ).text("Este destino no tiene valor de flete.");
        			$("#mensajes").removeClass().addClass("alert alert-danger");
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
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
			  $( "#mensajes").removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
	
}
function FLimpar()
{
	window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc"/>?id=0'+'&iddet='+0);
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
		$( "#TId" ).focus();
	
}
function FBuscarxId(id,iddet)
{
	window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc" />?id='+id+'&iddet='+iddet);
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

var ids = ""
var costoscapturados = "";
var ajustes = "";
var esquemas = "";

function SumarEsp()
{
	ids = ""
	costoscapturados = "";
	ajustes = "";
	esquemas = "";

$("input[id='ChEsp']").each(function (){
	idEsp = $(this).val();
	if($(this).prop('checked'))
	{
		if(idEsp == 19)
		{		
			$("#TAjuste"+idEsp).attr("required","required");
			$("#TAjuste"+idEsp).attr("type","text");
		}
		
		$("#TCosto"+idEsp).attr("required","required");
		if($("#TCosto"+idEsp).prop('type') == 'select-one')
		{
			$("#TCosto"+idEsp).css('visibility', 'visible')
		}
		else
		{
			$("#TCosto"+idEsp).attr("type","text"); 
		}
		
		ids = idEsp+"|"+ids;
		costoscapturados = ($("#TCosto"+idEsp).val() == "" ? 0 : ($("#TCosto"+idEsp).val() == null ? 0 : $("#TCosto"+idEsp).val())) +"|"+costoscapturados;
		ajustes = ($("#TAjuste"+idEsp).val() == "" ? 0 : ($("#TAjuste"+idEsp).val() == null ? 0 : $("#TAjuste"+idEsp).val())) +"|"+ajustes;
		esquemas = ($("#TEsquema"+idEsp).val() == "" ? 0 : ($("#TEsquema"+idEsp).val() == null ? 0 : $("#TEsquema"+idEsp).val())) +"|"+esquemas;
	}
	else
	{
		$("#TCosto"+idEsp).attr("required",false);
		
		if(idEsp == 19)
		{
			$("#TAjuste"+idEsp).attr("type","hidden");
			$("#TAjuste"+idEsp).attr("required",false);
			$("#TAjuste"+idEsp).val("");
		}
		
		if($("#TCosto"+idEsp).prop('type') == 'select-one')
		{
			$("#TCosto"+idEsp).css('visibility', 'hidden')
			$("#TCosto"+idEsp).val("0");
		}
		else
		{
			$("#TCosto"+idEsp).attr("type","hidden");
			$("#TCosto"+idEsp).val("");
		}
		
	}
	
});
}

function CalcularDatos()
{
	SumarEsp();

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
								$("#TLargoPliego").val(0);
				        		$("#TAnchoPliego").val(0);
				        		$("#TTotEsp").val(0);
					        },	
        success : function(data) {
        	if (data.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
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
	
	        		for( var i=0; i<jsonEsp.length; i++ ){
	        				$("#TCosto"+jsonEsp[i].id).val(jsonEsp[i].costo);
	        		  }
	
	        	}
        	}
        	catch(err) {
        		  document.getElementById("mensajes").innerHTML = "";
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

function FEnviarVtaProg()
{
	var idcot = +$("#TId").val();
	var iddet = $("#TIdDet").val();
	if(idcot > 0)
	{
		var http = new XMLHttpRequest();
		var url = '<c:url value="/ventas/tarjetas/cotizador/enviaragerenteventasprog"/>';
		var params = 'idcot='+idcot;
		
		http.open('POST', url, true);
	
		//Send the proper header information along with the request
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
		http.onreadystatechange = function() {//Call a function when the state changes.
			$("#DivMensaje").text("Procesando petición. Por favor espere...");
	    	$("#DivMensaje").removeClass().addClass("alert alert-danger");
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
			    		window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc"/>?id=0'+'&iddet='+0);
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc"/>?id='+idcot+'&iddet='+iddet);
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo...");
		    		window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc"/>?id='+idcot+'&iddet='+iddet);
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
		var url = '<c:url value="/ventas/tarjetas/cotizador/cancelarcotizacion"/>';
		var params = 'idcot='+idcot;
		
		http.open('POST', url, true);
	
		//Send the proper header information along with the request
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
		http.onreadystatechange = function() {//Call a function when the state changes.
			$("#DivMensaje").text("Procesando petición. Por favor espere...");
	    	$("#DivMensaje").removeClass().addClass("alert alert-danger");
		    if(http.readyState == 4 && http.status == 200) 
		    {
		    	if (http.responseText.search(/Login page/i) != -1) {
		    		alert("La sessión ha expirado, Por favor vuelva a intentarlo.");
	    			window.location.replace('<c:url value="/login?expired"/>');
		    	}
	    		else{
	    			if(http.responseText === 'OK')
	    			{
	    				alert("COTIZACIÓN CANCELADA!!!!!!");
			    		window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc"/>?id=0'+'&iddet='+0);
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo.");
			    		window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc"/>?id='+idcot+'&iddet='+iddet);
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo...");
		    		window.location.replace('<c:url value="/ventas/tarjetas/cotizador/cotizadorabc"/>?id='+idcot+'&iddet='+iddet);
		    	}
		    }
		    
		}
		http.send(encodeURI(params));
	}
}
function FColores()
{
	var ntintas = $("#SNumTintas").val();

	for(var i = 1; i <= 7; i++)
	{
		if(i <= ntintas)
		{
			$("#SColor"+i).css('visibility', 'visible');
			$("#SColor"+i).attr("required","required");
		}
		else
		{
			$("#SColor"+i).css('visibility', 'hidden');
			$("#SColor"+i).attr("required",false);
			$("#SColor"+i).val("");
		}
	}
	
}

$(document).on("keypress", "input", function (e) {//deshabilitar enter submit
    var code = e.keyCode || e.which;
    if (code == 13) {
        e.preventDefault();
        return false;
    }
});
</script>
<title>Registro cotizaciones</title>
</head>
	<body>
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
									<div class="col-sm-1">Folio</div>
									<div class="col-sm-1"><form:input class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)" id="TId" readonly="true" type="text" value="${empty cotizadordatabean.cotizador.id ? 0 : cotizadordatabean.cotizador.id}"  path="cotizador.id"/></div>
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
									<div class="col-sm-10">
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
									<div class="col-sm-1"><button type="button" data-toggle="modal" data-target="#AutModal" class="btn btn-outline-primary"><i class="fa fa-thumbs-o-up" aria-hidden="true"> Info</i></button></div>
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
						</div>
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="badge badge-info col-12">
					 	Detalles de caja
					 </div>
				 </div>
			 </div>
			 <div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row ">
					 <div class="col-12">
						<div class="row small">
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col-sm-1">
										<form:input id="TIdDet" value="${empty cotizadordatabean.cotizador_detalles.iddetalle ? 0 : cotizadordatabean.cotizador_detalles.iddetalle}" class="border border-secondary" size="9" maxlength="8" onkeypress="return Enteros(event)" readonly="true" type="text"  path="cotizador_detalles.iddetalle"/>
									</div>
									<div class="col-sm-1">Símbolo</div>
									<div class="col-sm-3">
										<form:input id="TSimbolo" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="cotizador_detalles.simbolo" class="border border-primary"/>
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
									<div class="col-sm-1">Cierre</div>
									<div class="col-sm-1">
										<form:select id="SCierre" path="cotizador_detalles.cierre" multiple="false" class="border border-primary">
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
										<form:select id="SCierreDet" path="cotizador_detalles.cierre_detalle" multiple="false" class="border border-primary">
											<form:option value="nc">N/C</form:option>
											<form:option value="interior">INTERIOR</form:option>
											<form:option value="exterior">EXTERIOR</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.cierre_detalle" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Ceja despl: <form:checkbox id="CCejaDesp" path="cotizador_detalles.ceja_desplegada"/></div>
									<div class="col-sm-1">Entrega OC</div>
									<div class="col-sm-2">
										<div class="">
										<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
						                    <form:input id="TFechaOC" onkeypress="return false" path="cotizador_detalles.fecha_entrega_oc" size="10" data-target="#datetimepicker4" placeholder="yyyy-mm-dd" class="border border-primary"/>
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
									<div class="col-sm-1">ComisiónMillar</div>
									<div class="col-sm-1">
										<form:input id="TComxMillar" size="10" readonly = "true" type="text" path="cotizador_detalles.comisionxmillar" class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.comisionxmillar" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Observaciones</div>
									<div class="col-sm-3">
										<form:input id="TObs" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="cotizador_detalles.observaciones_vendedor" class="border border-primary"/>
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
									<div class="col-sm-1">Peso Resis</div>
									<div class="col-sm-1">
										<form:input id="TPesoResis" size="10" ondblclick="FBuscarResisId()" readonly = "true" type="text" path="cotizador_detalles.peso_resis" class="border border-secondary"
													data-toggle="tooltip" data-placement="top" title="Doble click para actualizar."/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.peso_resis" class="badge badge-danger small"/>
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
									<div class="col-sm-1">% Comisión</div>
									<div class="col-sm-1">
										<form:input readonly="true" id="TPorcCom" type="text" size="10" path="cotizador_detalles.porcentaje_comision"  class="border border-secondary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.porcentaje_comision" class="badge badge-danger small"/>
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
									<div class="col-sm-1">Desc Vend</div>
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
										<form:input id="TPzasxTar" onKeyUp="CalcularDatos()" size="10" maxlength="8" type="text" path="cotizador_detalles.piezasxtarima" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.piezasxtarima" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Pzas x juego</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TPzasxjgo" size="10" value="${empty cotizadordatabean.cotizador_detalles.iddetalle ? 1 : cotizadordatabean.cotizador_detalles.iddetalle == 1 ? 1: ''}"
											readonly="${empty cotizadordatabean.cotizador.id ? 'true' : cotizadordatabean.cotizador_detalles.iddetalle == 1 ? 'true': 'false'}" 
											maxlength="8" type="text" path="cotizador_detalles.piezasxjuego" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.piezasxjuego" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Pedido Mes</div>
									<div class="col-sm-1">
										<form:input onKeyUp="CalcularDatos()" id="TCantPedMes" size="10" maxlength="8" type="text" path="cotizador_detalles.cantidad_pedido_mes" onkeypress="return Enteros(event);" class="border border-primary"/>
										<div class="has-error">
											<form:errors path="cotizador_detalles.cantidad_pedido_mes" class="badge badge-danger small"/>
										</div>
									</div>
								</div>
								<div class="row border border-right">
									<div class="col-sm-1">N Tintas</div>
									<div class="col-sm-1">
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
									<div class="col-sm-10">
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
								<div class="row border border-right">
									<div class="col-sm-1">Se entregó</div>
									<div class="col-sm-1">
										<form:select id="SEntrego" path="cotizador_detalles.se_entrego" multiple="false" class="border border-primary">
											<form:option value="nc">N/C</form:option>
											<form:option value="Muestra">Muestra</form:option>
											<form:option value="Software">Software</form:option>
											<form:option value="Email">Email</form:option>
											<form:option value="Sin entrega">Sin entrega</form:option>
										</form:select>
										<div class="has-error">
											<form:errors path="cotizador_detalles.se_entrego" class="badge badge-danger small"/>
										</div>
									</div>
									<div class="col-sm-1">Tolerancia</div>
									<div class="col-sm-1">
										<form:select id="STolerancia" path="cotizador_detalles.tolerancia_pedido" multiple="false" class="border border-primary">
											<form:option value="0">0</form:option>
											<form:option value="5">5</form:option>
											<form:option value="10">10</form:option>
										</form:select> 
									</div>
									<div class="col-sm-5">Diseño
										<form:select id="SDisenio" path="cotizador_detalles.disenio" multiple="false" class="border border-primary">
											<form:option value="Nuevo">Nuevo</form:option>
											<form:option value="Con cambios">Con cambios</form:option>
											<form:option value="Referencia de TF">Referencia de TF</form:option>
										</form:select>
										Cancelar/Sustituir: <form:checkbox id="CCancSust" path="cotizador_detalles.cancelar_sustituir"/>
										TF: <form:input id="TTF" size="10" maxlength="8" type="text" path="cotizador_detalles.tf_cs" onkeypress="return SinCaracteresEspeciales(event);" class="border border-primary"/>
									</div>
									<div class="col-sm-1">Num ranuras</div>
									<div class="col-sm-1"><form:input id="TNumRanuras" size="10" maxlength="8" type="text" path="cotizador_detalles.num_raturas" onkeypress="return Enteros(event);" class="border border-primary"/></div>
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
									<div class="col-sm-12">
										Emplayado: <form:checkbox id="CEmplayado" path="cotizador_detalles.emplayado"/> 
										Vueltas: <form:input id="TVueltasEmp" size="10" maxlength="8" type="text" path="cotizador_detalles.vueltas_emplaye" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
										Factura: <form:checkbox id="CFactura" path="cotizador_detalles.factura"/>
										Certif calidad: <form:checkbox id="CCertCal" path="cotizador_detalles.certif_calidad"/>
										Imprimir OC: <form:checkbox id="CImpOC" path="cotizador_detalles.imprimir_oc"/>
										Protecciones: <form:checkbox id="CProtecciones" path="cotizador_detalles.protecciones"/>
										Caja seca: <form:checkbox id="CCajaSeca" path="cotizador_detalles.caja_seca"/>
										Certif fumigación: <form:checkbox id="CCertFum" path="cotizador_detalles.certif_fumig"/>
										EPP transportista: <form:checkbox id="CEPP" path="cotizador_detalles.epp_transportista"/>
									</div>
									<div class="col-sm-1">Altura pallet</div>
									<div class="col-sm-1"><form:input id="TAltPallet" size="10" maxlength="8" type="text" path="cotizador_detalles.altura_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col-sm-1">Camas pallet</div>
									<div class="col-sm-1"><form:input id="TCamasPallet" size="10" maxlength="8" type="text" path="cotizador_detalles.camas_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col-sm-1">Flejes pallet</div>
									<div class="col-sm-1"><form:input id="TFlejesPallet" size="10" maxlength="8" type="text" path="cotizador_detalles.flejes_pallet" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									
									<div class="col-sm-1">Flejes atado</div>
									<div class="col-sm-1"><form:input id="TFlejesAtado" size="10" maxlength="8" type="text" path="cotizador_detalles.flejes_atado" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col-sm-1">Pzas atado</div>
									<div class="col-sm-1"><form:input id="TPzasAtado" size="10" maxlength="8" type="text" path="cotizador_detalles.pzas_atado" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									<div class="col-sm-1">Atados cama</div>
									<div class="col-sm-1"><form:input id="TAtaCama" size="10" maxlength="8" type="text" path="cotizador_detalles.atados_cama" onkeypress="return filterFloat(event,this);" class="border border-primary"/></div>
									
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
									<div class="col-sm-3">
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
		 
		 <div align="center">
		 	<span id="imgload" style='display: none;'><img width="20px" height="20px" src='<c:url value="/static/img/sun_watch.gif"/>' /></span>
		 </div>
		 <div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
		<div align="left" class = "container">
		<div class = "row" align="center">			
			<div class="col-sm-2"><form:button id="BGrabar" class="btn btn-outline-primary"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
			<div class="col-sm-2"><a href="javascript:FBuscar()" class="btn btn-outline-primary"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
			<div class="col-sm-2"><button type="button" data-toggle="modal" data-target="#LimpiarModal" class="btn btn-outline-primary"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></button></div>
			<div class="col-sm-4"><button id="BEnvVtas" type="button" data-toggle="modal" data-target="#VtaModal" class="btn btn-outline-primary"><i class="fa fa-caret-right" aria-hidden="true">Enviar AUT Ventas y Programación</i></button></div>
			<div class="col-sm-2"><button id="BCancel" type="button" data-toggle="modal" data-target="#CancelModal" class="btn btn-outline-primary"><i class="fa fa-times-circle-o" aria-hidden="true"> Cancelar</i></button></div>
		</div>
		</div>
	</form:form>
	
	<!-- REGION DE MODALS -->
	<div class="modal fade" id="LimpiarModal" tabindex="-1" role="dialog" aria-labelledby="LimpiarModallLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title" id="exampleModalLabel">Limpiar</h5>
	      </div>
	      <div class="modal-footer">
	        <button type="button" onclick="FLimpar()" class="btn btn-outline-primary" data-dismiss="modal">Limpiar TODO</button>
	        <button type="button" onclick="FBuscarxId(${empty cotizadordatabean.cotizador.id ? 0 : cotizadordatabean.cotizador.id},0)" class="btn btn-outline-primary" data-dismiss="modal">Limpiar DETALLE</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade bd-example-modal-lg" id="AutModal" tabindex="-1" role="dialog" aria-labelledby="AutModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
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
		      Fecha envío ventas:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_ventas}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha aut ventas:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_aut_ventas}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		   <div class="row">
		    <div class="col-sm">
		      Fecha envío prog:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_a_prog}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha aut prog:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_aut_prog}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Fecha rechaza vtas:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_ventas}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha rechaza prog:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_prog}" pattern="yyyy-MM-dd hh:mm"/>
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
		      Comentario ventas:
		    </div>
		    <div class="col-sm">
		      ${cotizadordatabean.cotizador.observaciones_ventas}
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Comentario prog:
		    </div>
		    <div class="col-sm">
		     	${cotizadordatabean.cotizador.observaciones_prog}
		    </div>
		    <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
		  </div>
		</div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="VtaModal" tabindex="-1" role="dialog" aria-labelledby="VtaModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title">Enviar a autorización VENTAS y PROGRAMACIÓN</h5>
	      </div>
	      <div class="modal-body alert alert-warning">
	        ¡¡¡ATENCIÓN!!! ¿Desea enviar esta cotización a autorización?
	      </div>
	      <div id="DivMensaje" class="modal-footer">
	        <button type="button" class="btn btn-primary" onClick="FEnviarVtaProg()">Enviar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
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
	<!-- FIN REGION DE MODALS  -->
	
	</div>

	<%@include file="../../appconfig/authfootter.jsp"%>
	</body>
</html>