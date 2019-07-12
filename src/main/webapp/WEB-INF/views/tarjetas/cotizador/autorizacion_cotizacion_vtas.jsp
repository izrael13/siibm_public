<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Autorización de cotizaciones por Gerente de Ventas</title>
<script>
function FAut(id)
{
	var r = confirm("Enviar autorización!");
	if(r == true)
	{
		if(id > 0)
		{
			var http = new XMLHttpRequest();
			var url = '<c:url value="/cotizador/ventas/autorizacion_cotizacion_vtas_desicion"/>';
			var params = 'idcot='+id+'&coment='+$("#TComent"+id).val()+'&ban='+1;
			
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
				    		window.location.replace('<c:url value="/cotizador/ventas/autorizacion_cotizacion_vtas"/>');
		    			}
		    			else
		    			{
		    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
				    		window.location.replace('<c:url value="/cotizador/ventas/autorizacion_cotizacion_vtas"/>');
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/ventas/autorizacion_cotizacion_vtas"/>');
			    	}
			    	else
			    	{
				    	$("#mensajes" ).text("Procesando petición");
						$("#mensajes").removeClass().addClass("alert alert-info");
			    	}
			    }
			    
			}
			http.send(encodeURI(params));
		}
	}
	
}
function FReach(id)
{
	var r = confirm("Enviar rechazo!");
	if(r == true)
	{
		if(id > 0)
		{
			var http = new XMLHttpRequest();
			var url = '<c:url value="/cotizador/ventas/autorizacion_cotizacion_vtas_desicion"/>';
			var params = 'idcot='+id+'&coment='+$("#TComent"+id).val()+'&ban='+0;
			
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
				    		window.location.replace('<c:url value="/cotizador/ventas/autorizacion_cotizacion_vtas"/>');
		    			}
		    			else
		    			{
		    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
				    		window.location.replace('<c:url value="/cotizador/ventas/autorizacion_cotizacion_vtas"/>');
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/ventas/autorizacion_cotizacion_vtas"/>');
			    	}
			    	else
			    	{
				    	$("#mensajes" ).text("Procesando petición");
						$("#mensajes").removeClass().addClass("alert alert-info");
			    	}
			    }
			    
			}
			http.send(encodeURI(params));
		}
	}
}
function FImprimir(id)
{
	var redirectWindow = window.open('<c:url value="/cotizador/ventas/imprimircotizador"/>?id='+id);
	redirectWindow.replace;
}
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Autorización de cotizaciones por Gerente de Ventas</span>
	</div>
	<br>
	<div align="center" class="container-fluid">
	<table class="container-fluid table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>Folio</th>
				<th>Cliente</th>
				<th>Símbolo</th>
				<th>Caja</th>
				<th>%Comisión</th>
				<th>$ Objetivo</th>
				<th>$ sugerido</th>
				<th>$ neto</th>
				<th>Desc vendedor</th>
				<th>CPCC</th>
				<th>CPSC</th>
				
				<th>Resistencia</th>
				<th>Sello</th>
				
				<th>Comentarios</th>
				<th>Imprimir</th>
				<th>Autorizar</th>
				<th>Rechazar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDet}">
			<tr>
				<td>${item.id}</td>
				<td>${item.cardname}</td>
				<td>${item.simbolo}</td>
				<td>${item.nombrecorto}</td>
				<td>${item.comision_directo}</td>
				<td>${item.precio_objetivo}</td>
				<td>${item.precio_sugerido}</td>
				<td>${item.precio_neto}</td>
				<td>${item.descuento_vendedor}</td>
				<td>${item.cpcc}</td>
				<td>${item.ref_para_com}</td>
				
				<td>${item.resistencia} ${item.corrugado} ${item.color}</td>
				<td>${item.sellos}</td>
				
				<td><input id="TComent${item.id}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FImprimir(${item.id})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FAut(${item.id})"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FReach(${item.id})"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>