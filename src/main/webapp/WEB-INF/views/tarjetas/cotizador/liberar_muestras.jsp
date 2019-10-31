<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Liberar muestras</title>
<script>
function FArrastreA(id, b)
{
		var r = confirm("¿Liberar muestra?");
	
	if(r)
	{
		$("#mensajes" ).text("Procesando petición, por favor espere...").removeClass().addClass("alert alert-info");
		var http = new XMLHttpRequest();
		var url = '<c:url value="/cotizador/muestras/muestrasabc"/>';
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
	    				alert("Muestra liberada correctamente!!");
			    		window.location.replace('<c:url value="/cotizador/muestras/liberar_muestras"/>');
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/muestras/liberar_muestras"/>');
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
		    		window.location.replace('<c:url value="/cotizador/muestras/liberar_muestras"/>');
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
		<span class="badge badge-secondary">Liberar muestras</span>
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
				<th>Liberar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDetms}" varStatus="counter">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${item['id']}" />
			<tr>
				<td>${i}</td>
				<td>${item['cliente']}</td>
					<td colspan="10">

					<table class="container-fluid table-hover text-center">
					<tr>
						<th>Símbolo</th>
						<th>Caja</th>
						<th>Resistencia</th>
						<th>Sello</th>
					</tr>
					<c:forEach var="det" items="${item['ListaDetalles']}" varStatus="counter">
					<tr>
						<td>${det['simbolo']}</td>
						<td>${det['estilo_caja']}</td>
						<td>${det['resistencia']}</td>
						<td>${det['resis_cte']}</td>
					</tr>
					</c:forEach>
					</table>
					</td>	
				<td><input id="TComent${i}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FImprimir(${i})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FArrastreA(${i},3)"><i class="fa fa-floppy-o" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		<c:forEach var="item" items="${listaDetmv}" varStatus="counter">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${item['id']}" />
			<tr>
				<td>${i}</td>
				<td>${item['cliente']}</td>
					<td colspan="10">

					<table class="container-fluid table-hover text-center">
					<tr>
						<th>Símbolo</th>
						<th>Caja</th>
						<th>Resistencia</th>
						<th>Sello</th>
					</tr>
					<c:forEach var="det" items="${item['ListaDetalles']}" varStatus="counter">
					<tr>
						<td>${det['simbolo']}</td>
						<td>${det['estilo_caja']}</td>
						<td>${det['resistencia']}</td>
						<td>${det['resis_cte']}</td>
					</tr>
					</c:forEach>
					</table>
					</td>	
				<td><input id="TComent${i}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FImprimir(${i})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FArrastreA(${i},3)"><i class="fa fa-floppy-o" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>