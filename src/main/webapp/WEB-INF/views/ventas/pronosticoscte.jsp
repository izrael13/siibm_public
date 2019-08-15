<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Pronósticos</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script type="text/javascript">
function FBuscarCtes()
{
	var id = $("#SVendedores").val();
	var opciones = "";
	//alert(id);
	$.ajax({
		//dataType: 'text',
		url: '<c:url value="/qlikview/ventas/buscarclientes"/>?id='+id,
		//contentType : 'application/json',
		//cache: false,    
		//data: cve_estado,
		beforeSend: function(xhr) {
							  $("#imgload").show();
							  $("#SClientes" ).empty();
							  },	
        success : function(data) {
        		if (data.search(/Login page/i) != -1) {
        			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }
	        	opciones = opciones + "<option value=''>Seleccione un cliente</option>";
	        	$.each(jQuery.parseJSON(data),function(index, value){
	        		opciones = opciones + "<option value='"+value.cardcode + "'>"+value.cardname+ "</option>";
	        	});
	        	FBuscar();
	        	$( "#SClientes" ).append(opciones);
	        	$( "#imgload").hide();
        },
        error: function(xhr, status, error) {
        	  $( "#SClientes" ).empty();
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
			  $( "#mensajes").removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 }); 
}
function FGrabar()
{
	var idven = $("#SVendedores").val();
	var idcte = $("#SClientes").val();
	var aniomes = $("#aniomes").val();
	var pron = $("#TPron").val();
	$( "#mensajes" ).text("Procesando petición, porfavor espere...").removeClass().addClass("alert alert-info");
	
	if(idven == 0)
		$( "#mensajes" ).text("Debe seleccionar un vendedor.").removeClass().addClass("alert alert-danger");
	else
	{
		if(idcte == "")
			$( "#mensajes" ).text("Debe seleccionar un cliente.").removeClass().addClass("alert alert-danger");
		else
		{
			if(aniomes.length == 0)
				$( "#mensajes" ).text("Debe seleccionar una fecha.").removeClass().addClass("alert alert-danger");
			else
			{
				if(pron == "" || pron == 0)
					$( "#mensajes" ).text("Debe capturar pronóstico.").removeClass().addClass("alert alert-danger");
				else
				{
					var http = new XMLHttpRequest();
					var url = '<c:url value="/qlikview/ventas/guardarpron"/>';
					var params = 'idven='+idven+'&idcte='+idcte+'&anio='+aniomes.substring(0,4)+'&mes='+aniomes.substring(4,aniomes.length)+'&pron='+pron;
					
					http.open('POST', url, true);
				
					//Send the proper header information along with the request
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
				    				alert("Pronóstico guardado satisfactoriamente.");
				    				$("#TPron").val("");
				    				$("#SClientes").val("");
				    				FBuscar();
						    		//window.location.replace('<c:url value="/qlikview/ventas/pronosticoscte____"/>');
				    			}
				    			else
				    			{
				    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
						    		window.location.replace('<c:url value="/qlikview/ventas/pronosticoscte____"/>');
				    			}
				    		}
					    }
					    else
					    {
					    	if(http.readyState == 4 && http.status != 200){
					    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
					    		window.location.replace('<c:url value="/qlikview/ventas/pronosticoscte____"/>');
					    	}
					    	else
					    	{
						    	$("#mensajes" ).text("Procesando petición, porfavor espere...");
								$("#mensajes").removeClass().addClass("alert alert-info");
					    	}
					    }
					    
					}
					http.send(encodeURI(params));
				}
			}
		}
	}
	
}
function FBuscar()
{
	var idven = $("#SVendedores").val();
	var idcte = $("#SClientes").val();
	var aniomes = $("#aniomes").val();
	var pron = $("#TPron").val();
	var tabla = "";
	
	$("#DLista").text("");
	$( "#mensajes" ).text("").removeClass();
	$.ajax({
		url: '<c:url value="/qlikview/ventas/buscarpron"/>?idven='+idven+'&idcte='+idcte+'&anio='+aniomes.substring(0,4)+'&mes='+aniomes.substring(4,aniomes.length)+'&pron='+pron,
		beforeSend: function(xhr) {
							  $("#imgload").show();
					        },	
        success : function(data) {
        		if (data.search(/Login page/i) != -1) {
        			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }
        		tabla = tabla + '<table id="tablePag" class="table-hover table-bordered text-center mx-auto">';
        		tabla = tabla + '<thead> <tr> ';
        		tabla = tabla + '<th>Cliente</th>';
        		tabla = tabla + '<th>Vendedor</th>';
        		tabla = tabla + '<th>Pronóstico</th>';
        		tabla = tabla + '<th>Fecha</th>';
        		tabla = tabla + '</tr> </thead>';
        		tabla = tabla + '<tbody> ';
        		//alert(data);
	        	$.each(jQuery.parseJSON(data),function(index, value){
	        		//alert(value.id);
	        		tabla = tabla + '<tr>';
	        		tabla = tabla + '<td>'+value.cliente+'</td>';
	        		tabla = tabla + '<td>'+value.vendedor+'</td>';
	        		tabla = tabla + '<td>'+value.pron+'</td>';
	        		tabla = tabla + '<td>'+value.fecha+'</td>';
	        		tabla = tabla + '</tr>';
	        	});
	        	tabla = tabla + '</tbody> ';
	        	tabla = tabla + '</table>';
	        	document.getElementById("DLista").innerHTML = tabla;
	        	$( "#imgload").hide();
	        	$( "#mensajes" ).text("").removeClass();
        },
        error: function(xhr, status, error) {
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
}
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Pronósticos</span>
	</div>
	<br>
	
	<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
		<div class="row small">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				<div class="row border border-right">
					<div class="col-sm-1">Vendedor</div>
					<div class="col-sm-6">
						<select onchange="FBuscarCtes()" id="SVendedores" class="border border-primary">
							<option value="0">Seleccione un vendedor</option>
							<c:forEach var="ven" items="${listavend}">
									<option value="${ven.clavevendedor}" ${ven.clavevendedor == selectedValueSlpCode ? 'selected' : ''}
										<sec:authorize access="!hasRole('ADMIN') or !hasRole('VENTAS')">
											'disabled'
										</sec:authorize>
									><c:out value="${ven.nombre}"/></option>
								
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-1">Fecha</div>
					<div class="col-sm-2">
						<form:select id="aniomes" path="mesesanio" class="border border-primary" onchange="FBuscar()">
						<c:forEach var="i" items="${mesesanio}">
							<c:set var="anio_mes_">${i.anio}${i.mes}</c:set>
							<c:choose>
								<c:when
									test="${not empty selectedValue && selectedValue eq anio_mes_ }">
									<option value="${i.anio}${i.mes}" selected>${i.anio} - ${i.mes}</option>
								</c:when>
								<c:otherwise>
									<option value="${i.anio}${i.mes}">${i.anio} - ${i.mes} </option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
					 </div>
					<div class="col-sm-1">Pronóstico</div>
					<div class="col-sm-1"> <input type="text" id="TPron" class="border border-primary" size="9" maxlength="9" onkeypress="return filterFloat(event,this)"/> </div>
				</div>
				<div class="row border border-right">
					<div class="col-sm-1">Cliente</div>
					<div class="col-sm-10">
						<select id="SClientes" class="border border-primary">
							<option value="">Seleccione un cliente</option>
							<c:forEach var="item" items="${listactes}">
								<option value="${item.cardcode}"  ${item.cardcode == selectedValueCardCode ? 'selected' : ''}>${item.cardname}</option>
							</c:forEach>
						</select>  
					</div>
					<div class="col-sm-1"><button type="button" class="btn btn-outline-primary btn-sm" onClick="FGrabar()">
						<i class="fa fa-floppy-o"></i>
							Grabar
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="DLista">
	</div>
	<div align="center">
	 	<span id="imgload" style='display: none;'><img width="20px" height="20px" src='<c:url value="/static/img/sun_watch.gif"/>' /></span>
	 	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	</div>
</body>
</html>