<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Asignar diseñador</title>
<script>
function FSeguimiento(id, iddet)
{
	var redirectWindow = window.open('<c:url value="/cotizador/vendedor/seguimiento_cot_hist" />?id='+id+'&iddet='+iddet);
	redirectWindow.replace();
}
function FTarjeta(id,b)
{	
	if(b == 0)
		var r = confirm("Grabar comentario!");
	else
	{		
		if(b == 3)
			var r = confirm("¿¡¡¡¡RECHAZAR COTIZACIÓN!!!!?");
		else
			if(b == 4)
				var r = confirm("¿¡¡¡¡ASIGNAR DISEÑADOR A COTIZACIÓN!!!!?");
			
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
					    		window.location.replace('<c:url value="/cotizador/ingenieria/asignardiseniador"/>');
		    				}
		    				else
		    				{	
		    					if(b == 3)
		    					{
		    						alert("¡¡¡¡COTIZACIÓN RECAHZADA CORRECTAMENTE!!!");
						    		window.location.replace('<c:url value="/cotizador/ingenieria/asignardiseniador"/>');
		    					}
		    					else
		    					{
		    						if(b == 4)
		    						{
			    						alert("¡¡¡¡DISEÑADOR ASIGNADO CORRECTAMENTE!!!");
							    		window.location.replace('<c:url value="/cotizador/ingenieria/asignardiseniador"/>');
							    	}
		    					}
		    				}
		    			}
		    			else
		    			{
		    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
				    		window.location.replace('<c:url value="/cotizador/ingenieria/asignardiseniador"/>');
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/ingenieria/asignardiseniador"/>');
			    	}
			    }
			    
			}
			http.send(encodeURI(params));
		}
	}
}
</script>
</head>
<body>
	<div align="center" class="border border-secondary">Asignar diseñador </div>
	<br>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<div align="center" class="container-fluid">
	<table class="container-fluid table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>Folio</th>
				<th>Cliente</th>
				<th>Vendedor</th>
				<th colspan="6">Símbolos</th>
				<th>Comentarios</th>
				<th>Grabar comentario</th>
				<th class="text-success">Asignar</th>
				<th class="text-warning">Rechazar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDet}" varStatus="counter">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${item['id']}" />
			<tr>
				<td>${i}</td>
				<td>${item['cliente']}</td>
				<td>${item['representante']}</td>
				<td colspan="6">
				<table class="container-fluid table-hover text-center">
				<c:forEach var="det" items="${item['ListaDetalles']}" varStatus="counter">
					<fmt:parseNumber var = "iddet" integerOnly = "true" pattern="##############" type = "number" value = "${det['iddetalle']}" />
				<tr>
					<td><a href="javascript:FSeguimiento(${i},${iddet})">${det['simbolo']}</a></td>
				</tr>
				</c:forEach>
				</table>
				</td>
				<td><input id="TComent${i}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" value="${item['observaciones_diseniador']}" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FTarjeta(${i},0)"><i class="fa fa-floppy-o" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FTarjeta(${i},4)"><i class="text-success fa fa-file-text-o" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FTarjeta(${i},3)"><i class="text-warning fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>