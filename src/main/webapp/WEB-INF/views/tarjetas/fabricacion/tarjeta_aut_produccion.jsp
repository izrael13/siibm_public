<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"/>
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Autorización de tarjetas Producción</title>
<script>
function FAut(folio)
{
	var r = confirm("Enviar autorización!");
	if(r == true)
	{
		if(folio != "")
		{
			var http = new XMLHttpRequest();
			var url = '<c:url value="/tarjeta/produccion/tarjeta_aut_produccion_desicion"/>';
			var params = 'folio='+folio+'&coment='+$("#TComent"+folio).val()+'&ban='+1;
			
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
		    				alert("Autorización existosa.");
				    		window.location.replace('<c:url value="/tarjeta/produccion/tarjeta_aut_produccion"/>');
		    			}
		    			else
		    			{
		    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
				    		window.location.replace('<c:url value="/tarjeta/produccion/tarjeta_aut_produccion"/>');
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/tarjeta/produccion/tarjeta_aut_produccion"/>');
			    	}
			    	else
			    	{
				    	$("#mensajes" ).text("Procesando petición, por favor espere...");
						$("#mensajes").removeClass().addClass("alert alert-info");
			    	}
			    }
			    
			}
			http.send(encodeURI(params));
		}
	}
	
}

function FReach(folio)
{
	var r = confirm("Enviar rechazo!");
	if(r == true)
	{
		if(folio != "")
		{
			var http = new XMLHttpRequest();
			var url = '<c:url value="/tarjeta/produccion/tarjeta_aut_produccion_desicion"/>';
			var params = 'folio='+folio+'&coment='+$("#TComent"+folio).val()+'&ban='+0;
			
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
		    				alert("Rechazo enviado.");
				    		window.location.replace('<c:url value="/tarjeta/produccion/tarjeta_aut_produccion"/>');
		    			}
		    			else
		    			{
		    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
				    		window.location.replace('<c:url value="/tarjeta/produccion/tarjeta_aut_produccion"/>');
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/tarjeta/produccion/tarjeta_aut_produccion"/>');
			    	}
			    	else
			    	{
				    	$("#mensajes" ).text("Procesando petición, por favor espere...");
						$("#mensajes").removeClass().addClass("alert alert-info");
			    	}
			    }
			    
			}
			http.send(encodeURI(params));
		}
	}
}
function FSeguimiento(id, iddet)
{
	var redirectWindow = window.open('<c:url value="/cotizador/vendedor/seguimiento_cot_hist" />?id='+id+'&iddet='+iddet);
	redirectWindow.replace();
}
function FImprimirTF(id,iddet)
{
	var redirectWindow = window.open('<c:url value="/tarjeta/ingenieria/imprimirtf"/>?id='+id+'&iddet='+iddet);
	redirectWindow.replace;
}
</script>
</head>
<body>
	<br>	
	<div align="center">
		<span class="badge badge-secondary">Autorización de tarjetas Producción</span>
	</div>
	<br>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<div align="center" class="container-fluid">
	<table class="container-fluid table-hover text-center table-bordered small">
	<thead>
		<tr>
			<th>Folio</th>
			<th>Cotización</th>
			<th>Desc factura</th>
			<th>Obs calidad</th>
			<th>Fecha aut calidad</th>
			<th>Comentario</th>
			<th>Ver</th>
			<th>Autorizar</th>
			<th>Rechazar</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${tarjetas}">
		<tr>
			<td><a href="javascript:FSeguimiento(${item.idcotizacion},${item.iddetalle})">${item.folio_tarjeta}</a></td>
			<td>${item.idcotizacion}</td>
			<td>${item.descripcion_factura}</td>
			<td>${item.observaciones_calidad}</td>
			<td>${item.fecha_aut_calidad}</td>
			<td><input id="TComent${item.folio_tarjeta}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" class="border border-primary"/></td>
			<td><a href="javascript:FImprimirTF(${item.idcotizacion},${item.iddetalle})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
			<td><a href="javascript:FAut(${item.folio_tarjeta})"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></a></td>
			<td><a href="javascript:FReach(${item.folio_tarjeta})"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</div>
</body>
</html>