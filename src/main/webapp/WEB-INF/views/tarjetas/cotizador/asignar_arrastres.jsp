<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Asignar arrastres</title>
<script>
function FArrastreA(id, b)
{
	if(b == 1)
		var r = confirm("¿Asignar arrastre?");
	else
	{
		if(b == 2)
			var r = confirm("¿Rechazar arrastre?");
	}
	
	if(r)
	{
		$("#mensajes" ).text("Procesando petición, por favor espere...").removeClass().addClass("alert alert-info");
		var http = new XMLHttpRequest();
		var url = '<c:url value="/cotizador/arrastres/arrastresabc"/>';
		var params = 'idcot='+id+'&coment='+$("#TComent"+id).val()+'&ban='+b;
		
		http.open('POST', url, true);
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		http.onreadystatechange = function() {//Call a function when the state changes.
		    if(http.readyState == 4 && http.status == 200) 
		    {
		    	if (http.responseText.search(/Login page/i) != -1) {
		    		alert("La sessión ha expirado, Por favor vuelva a intentarlo.");
	    			window.location.replace('<c:url value="/login?expired"/>');
		    	}
	    		else
	    		{
	    			if (http.responseText.search("OK") != -1)
	    			{
	    					
    					if(b == 1)
    					{
		    				alert("Arrastre asignado correctamente!!");
				    		window.location.replace('<c:url value="/cotizador/arrastres/asignar_arrastres"/>');
    					}
    					else
    					{
    						if(b == 2)
    						{
	    						alert("Arrastre rechazado correctamente!!");
					    		window.location.replace('<c:url value="/cotizador/arrastres/asignar_arrastres"/>');
					    	}
    						
    					}
	    				
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/arrastres/asignar_arrastres"/>');
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
		    		window.location.replace('<c:url value="/cotizador/arrastres/asignar_arrastres"/>');
		    	}
		    }
		    
		}
		http.send(encodeURI(params));
	}
		
}
function FImprimir(id)
{
	var redirectWindow = window.open('<c:url value="/cotizador/ingenieria/impirmirarr"/>?id='+id);
	redirectWindow.replace;
}
</script>
</head>
<body>
		<br>
	<div align="center">
		<span class="badge badge-secondary">Asignar arrastres</span>
	</div>
	<br>
	<div align="center" class="container-fluid">
	<table class="container-fluid table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>Folio</th>
				<th>Símbolo</th>
				<th>Colo1</th>
				<th>Colo2</th>
				<th>Colo3</th>
				<th>Colo4</th>
				<th>Colo5</th>
				<th>Colo6</th>
				<th>Colo7</th>
				<th>Observaciones Ven</th>
				<th>Comentarios</th>
				<th>Imprimir</th>
				<th>Asignar</th>
				<th>Rechazar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDet}" varStatus="counter">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${item['id']}" />
			<tr>
				<td>${i}</td>
				<c:forEach var="det" items="${item['ListaDetalles']}" varStatus="counter">
						<td>${det['simbolo']}</td>
						<td><div style="background:#${det['color1c']}">${det['color1n']} - ${det['color1c']}</div></td>
						<td><div style="background:#${det['color2c']}">${det['color2n']} - ${det['color2c']}</div></td>
						<td><div style="background:#${det['color3c']}">${det['color3n']} - ${det['color3c']}</div></td>
						<td><div style="background:#${det['color4c']}">${det['color4n']} - ${det['color4c']}</div></td>
						<td><div style="background:#${det['color5c']}">${det['color5n']} - ${det['color5c']}</div></td>
						<td><div style="background:#${det['color6c']}">${det['color6n']} - ${det['color6c']}</div></td>
						<td><div style="background:#${det['color7c']}">${det['color7n']} - ${det['color7c']}</div></td>
						<td>${det['observaciones_vendedor']}</td>
				</c:forEach>
				<td><input id="TComent${i}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" value="${item['observaciones_arrastre']}" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FImprimir(${i})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FArrastreA(${i},1)"><i class="fa fa-floppy-o" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FArrastreA(${i},2)"><i class="fa fa-window-close" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>