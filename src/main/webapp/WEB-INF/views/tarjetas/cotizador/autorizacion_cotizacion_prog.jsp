<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Autorización de cotizaciones por Programación</title>
<script>
function FAut(id)
{
	var r = confirm("Enviar autorización!");
	if(r == true)
	{
		if(id > 0)
		{
			var http = new XMLHttpRequest();
			var url = '<c:url value="/cotizador/programacion/autorizacion_cotizacion_prog_desicion"/>';
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
				    		window.location.replace('<c:url value="/cotizador/programacion/autorizacion_cotizacion_prog"/>');
		    			}
		    			else
		    			{
		    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
				    		window.location.replace('<c:url value="/cotizador/programacion/autorizacion_cotizacion_prog"/>');
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/programacion/autorizacion_cotizacion_prog"/>');
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
			var url = '<c:url value="/cotizador/programacion/autorizacion_cotizacion_prog_desicion"/>';
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
				    		window.location.replace('<c:url value="/cotizador/programacion/autorizacion_cotizacion_prog"/>');
		    			}
		    			else
		    			{
		    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
				    		window.location.replace('<c:url value="/cotizador/programacion/autorizacion_cotizacion_prog"/>');
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/programacion/autorizacion_cotizacion_prog"/>');
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
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Autorización de cotizaciones por Programación</span>
	</div>
	<br>
	<div align="center" class="container-fluid small">
	<table class="container-fluid table-hover text-center table-bordered">
		<thead>
			<tr>
				<th>Folio</th>
				<th>Largo</th>
				<th>Ancho</th>
				<th>Color</th>
				<th>Flauta</th>
				<th>Medidas pliego</th>
				<th>Especialidades</th>
				<th>Comentarios</th>
				<th>Autorizar</th>
				<th>Rechazar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${listaDet}">
			<tr>
				<td>${item[5]}</td>
				<td>${item[6]}</td>
				<td>${item[7]}</td>
				<td>${item[18]}</td>
				<td>${item[17]}</td>
				<td>${item[9]}</td>
				<td>${item[65]} ${item[66]} ${item[67]} ${item[68]} ${item[69]} ${item[70]} ${item[71]} ${item[72]} ${item[73]} ${item[74]} ${item[75]} ${item[76]} ${item[77]} ${item[78]} ${item[79]} 
				${item[80]} ${item[81]} ${item[82]} ${item[83]} ${item[84]} ${item[85]} ${item[86]} ${item[87]} ${item[88]} ${item[89]} ${item[90]} ${item[91]} ${item[92]}</td>
				<td><input id="TComent${item[5]}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FAut(${item[5]})"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FReach(${item[5]})"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>