<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Imprimir/Asignar diseñador requerimientos</title>
<script>
function FTarjeta(id,b)
{
	if(b == 0)
		var r = confirm("Grabar comentario!");
	else
	{
		if(b == 1)
			var r = confirm("¿Covertir a tarjeta?");
		else
		{
			if(b == 2)
				var r = confirm("¿¡¡¡¡CANCELAR COTIZACIÓN!!!!?");
			else
				var r = confirm("¿¡¡¡¡RECHAZAR COTIZACIÓN!!!!?");
		}	
	}
	
	if(r == true)
	{
		$("#mensajes" ).text("Procesando petición, por favor espere...");
		$("#mensajes").removeClass().addClass("alert alert-info");
		
		if(id > 0)
		{			
			var http = new XMLHttpRequest();
			var url = '<c:url value="/cotizador/ingenieria/convertiratarjeta"/>';
			var params = 'idcot='+id+'&coment='+$("#TComent"+id).val()+'&ban='+b;
			
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
		    			if (http.responseText.search("OK") != -1)
		    			{
		    				if(b == 0)
		    				{
		    					alert("Comentario grabado correctamente.");
					    		window.location.replace('<c:url value="/cotizador/ingenieria/requerimientoabc"/>');
		    				}
		    				else
		    				{	
		    					if(b == 1)
		    					{
				    				alert(http.responseText);
						    		window.location.replace('<c:url value="/cotizador/ingenieria/requerimientoabc"/>');
		    					}
		    					else
		    					{
		    						if(b == 2)
		    						{
			    						alert("¡¡¡¡COTIZACIÓN CANCELADA CORRECTAMENTE!!!");
							    		window.location.replace('<c:url value="/cotizador/ingenieria/requerimientoabc"/>');
							    	}
		    						else
		    						{
		    							alert("¡¡¡¡COTIZACIÓN RECAHZADA CORRECTAMENTE!!!");
							    		window.location.replace('<c:url value="/cotizador/ingenieria/requerimientoabc"/>');
		    						}
		    					}
		    				}
		    			}
		    			else
		    			{
		    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
				    		window.location.replace('<c:url value="/cotizador/ingenieria/requerimientoabc"/>');
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/ingenieria/requerimientoabc"/>');
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
</script>
</head>
<body>
		<br>
	<div align="center">
		<span class="badge badge-secondary">Imprimir/Asignar diseñador requerimientos</span>
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
				<th>Comentarios</th>
				<th>Grabar comentario</th>
				<th>Imprimir</th>
				<th class="text-success">Convertir a Tarjeta</th>
				<th class="text-warning">Rechazar</th>
				<th class="text-danger">Cancelar</th>
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
				<td><input id="TComent${item.id}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" value="${item.observaciones_diseniador == 'null' ? '' : item.observaciones_diseniador}" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FTarjeta(${item.id},0)"><i class="fa fa-floppy-o" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FImprimir(${item.id})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FTarjeta(${item.id},1)"><i class="text-success fa fa-file-text-o" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FTarjeta(${item.id},3)"><i class="text-warning fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FTarjeta(${item.id},2)"><i class="text-danger fa fa-times-circle-o" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>