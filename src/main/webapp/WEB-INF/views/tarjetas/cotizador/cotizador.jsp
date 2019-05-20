<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
        			window.location.replace('<c:url value="/login"/>');
				    return true;
				  }
	        	opciones = opciones + "<option value='"+0 + "'> - - - </option>";
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
    			window.location.replace('<c:url value="/login"/>');
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
</script>
<title>Registro cotizaciones</title>
<%@include file="../../appconfig/authheader2.jsp"%>
</head>
	<body>
	<!-- <div align="center">
		<span class="badge badge-secondary">Registro cotizaciones</span>
	</div> -->
	<div align="center" class = "container-fluid">
	 <form:form  method="POST" modelAttribute="cotizadordatabean" class="form-horizontal" autocomplete="off">
		<div class="row">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				 <div class="row justify-content-md-center">
					 <div class="badge badge-primary col-12">
					 	Datos generales
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
									<div class="col-sm-1"><form:input class="border border-primary" size="9" maxlength="9" onkeypress="return Enteros(event)" id="TId" readonly="true" type="text" value="${empty cotizadordatabean.cotizador.id ? 0 : cotizadordatabean.cotizador.id}"  path="cotizador.id"/></div>
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
										<form:input readonly = "true" size="10" type="text" id= "TFlete" path="cotizador.costo_flete" class="border border-primary"/>
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
											<form:select Onchange="FBuscarInfoDir()" path="cotizador.linenum_dir_entrega" id = "direcciones" items="${direcciones}" multiple="false" itemValue="linenum" itemLabel="direccion" class="border border-primary"/>
											<div id="imgload" style='display: none;'><img width="20px" height="20px" src='<c:url value="/static/img/sun_watch.gif"/>' /></div>
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
									<div class="col-3"><div id="DContacto"></div></div>
									<div class="col-1">Teléfono</div>
									<div class="col-3"><div id="DTelefono"></div></div>
									<div class="col-1">Email</div>
									<div class="col-3"><div id="DEmail"></div></div>
								</div>
							</div>
							<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
								<div class="row border border-right">
									<div class="col-1">Fecha Alta</div>
									<div class="col-3">${cotizadordatabean.cotizador.fecha_insert}</div>
									<div class="col-2">Fecha Actualización</div>
									<div class="col-3">${cotizadordatabean.cotizador.fecha_insert}</div>
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
									<div class="col-sm-1">detalles</div>
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
									<div class="col-sm-1">Especilidades</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		 </div>
		 <div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	</form:form>
	</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
	</body>
</html>