<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Liberar arrastres</title>
<script>
function FArrastreA(id)
{
		var r = confirm("�Asignar arrastre?");
	
	if(r)
	{
		$("#mensajes" ).text("Procesando petici�n, por favor espere...").removeClass().addClass("alert alert-info");
		var http = new XMLHttpRequest();
		var url = '<c:url value="/cotizador/arrastres/arrastresabc"/>';
		var params = 'idcot='+id+'&coment='+$("#TComent"+id).val()+'&ban='+3;
		
		http.open('POST', url, true);
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		http.onreadystatechange = function() {//Call a function when the state changes.
		    if(http.readyState == 4 && http.status == 200) 
		    {
		    	if (http.responseText.search(/Login page/i) != -1) {
		    		alert("La sessi�n ha expirado, Por favor vuelva a intentarlo.");
	    			window.location.replace('<c:url value="/login?expired"/>');
		    	}
	    		else
	    		{
	    			if (http.responseText.search("OK") != -1)
	    			{    					
	    				alert("Arrastre liberado correctamente!!");
			    		window.location.replace('<c:url value="/cotizador/arrastres/liberar_arrastres"/>');
	    			}
	    			else
	    			{
	    				alert("Algo sali� mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/arrastres/liberar_arrastres"/>');
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo sali� mal, por favor vuelva a intentarlo: "+http.responseText);
		    		window.location.replace('<c:url value="/cotizador/arrastres/liberar_arrastres"/>');
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
		<span class="badge badge-secondary">Liberar arrastres</span>
	</div>
	<br>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<div align="center" class="container-fluid">
	<table class="container-fluid table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>Folio</th>
				<th>S�mbolo</th>
				<th>Resistencia</th>
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
				<th>Liberar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDet}" varStatus="counter">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${item['id']}" />
			<tr>
				<td>${i}</td>
				<c:forEach var="det" items="${item['ListaDetalles']}" varStatus="counter">
						<td>${det['simbolo']}</td>
						<td>R:${det['resistencia']} F:${det['flauta']} P:${det['papel']}</td>
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
				<td><a href="javascript:FArrastreA(${i})"><i class="fa fa-floppy-o" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>