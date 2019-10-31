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
			$("#mensajes" ).text("Procesando petición");
			$("#mensajes").removeClass().addClass("alert alert-info");
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
			    	/*else
			    	{
				    	$("#mensajes" ).text("Procesando petición");
						$("#mensajes").removeClass().addClass("alert alert-info");
			    	}*/
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
			$("#mensajes" ).text("Procesando petición");
			$("#mensajes").removeClass().addClass("alert alert-info");
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
			    	/*else
			    	{
				    	$("#mensajes" ).text("Procesando petición");
						$("#mensajes").removeClass().addClass("alert alert-info");
			    	} */
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
				<th colspan="10">Detalles</th>				
				<th>Comentarios</th>
				<th>Imprimir</th>
				<th>Autorizar</th>
				<th>Rechazar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDet}" varStatus="counter">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${item['id']}" />
			<tr>
				<td>${i}</td>
				<td>${item['cliente']}</td>
					<td colspan="10">

					<table class="container-fluid table-hover text-center">
					<tr>
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
					</tr>
					<c:forEach var="det" items="${item['ListaDetalles']}" varStatus="counter">
					<tr>
						<td>${det['simbolo']}</td>
						<td>${det['estilo_caja']}</td>
						<td>${det['porcentaje_comision']}</td>
						<td>${det['precio_objetivo']}</td>
						<td>${det['precio_sugerido']}</td>
						<td>${det['precio_neto']}</td>
						<td>${det['descuento_vendedor']}</td>
						<td>${det['cpcc']}</td>
						<td>${det['ref_para_comision']}</td>
						<td>${det['resistencia']}</td>
						<td>${det['resis_cte']}</td>
					</tr>
					</c:forEach>
					</table>
					</td>	
				<td><input id="TComent${i}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FImprimir(${i})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FAut(${i})"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FReach(${i})"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>