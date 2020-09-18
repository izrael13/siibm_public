<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bocetos</title>
<%@include file="../../appconfig/imports.jsp"%>
<script>
function FBuscarxID(id,idb)
{
	window.location.replace('<c:url value="/cotizador/ingenieria/bocetosabc" />?id='+id+'&idb='+idb);
}
function FEnviar(id,idb)
{
	var r = confirm("Enviar boceto a vendedor?");
	
	if(r == true)
	{
		$("#mensajes" ).text("Procesando petición, por favor espere...");
		$("#mensajes").removeClass().addClass("alert alert-info");
		
		if(id > 0)
		{			
			var http = new XMLHttpRequest();
			var url = '<c:url value="/cotizador/ingenieria/enviarboceto" />';
			var params = 'id='+id+'&idb='+idb;
			
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
		    			if (http.responseText.search("1") != -1)
		    			{
		    				alert("¡Boceto enviado correctamente!");
		    				window.location.replace('<c:url value="/cotizador/ingenieria/bocetosabc" />?id='+id);
		    			}
		    			else
		    			{
		    				alert("¡Este boceto está en revisión por el vendedor!");
				    		window.location.replace('<c:url value="/cotizador/ingenieria/bocetosabc" />?id='+id);
		    			}
		    		}
			    }
			    else
			    {
			    	if(http.readyState == 4 && http.status != 200){
			    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
			    		window.location.replace('<c:url value="/cotizador/ingenieria/bocetosabc" />?id='+id);
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
		<div align="center" class="border border-secondary">Bocetos</div>
	</div>
	<br>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<div id = "errores" class = "${!empty errores ? 'alert alert-danger' : ''}">${errores}</div>
	<form:form id="form" method="POST" modelAttribute="Boceto" class="form-horizontal" enctype="multipart/form-data" autocomplete="off">
		<div class="col-12">
			<div class="row border border-right">
				<div class="col col-lg-1">Cotización</div>
				<div class="col col-lg-1">
					<form:input readonly="true" type="hidden" path="id"/>
					<form:input value="${idcot}" class="border border-secondary" size="9" maxlength="8" readonly="true" type="text" path="idcotizacion"/>
					<div class="has-error">
						<form:errors path="idcotizacion" class="badge badge-danger small"/>
					</div>
				</div>
				<div class="col col-lg-2">Num proyecto</div>
				<div class="col col-lg-3">
					<form:input type="text" size="40" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="num_proyecto" class="border border-primary"/>
					<div class="has-error">
						<form:errors path="num_proyecto" class="badge badge-danger small"/>
					</div>
				</div>
				<div class="col col-lg-1">Versión</div>
				<div class="col col-lg-3">
				<form:input size="10" maxlength="10" type="text" path="version" onkeypress="return SinCaracteresEspeciales(event)" class="border border-primary"/>
				<div class="has-error">
					<form:errors path="version" class="badge badge-danger small"/>
				</div>
				</div>
			</div>
		</div>
		<div class="col-12">
			<div class="row border border-right">
				<div class="col col-lg-2">Observaciones diseñador</div>
				<div class="col col-lg-3">
					<form:input type="text" size="40" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="observaciones_diseniador" class="border border-primary"/>
					<div class="has-error">
						<form:errors path="observaciones_diseniador" class="badge badge-danger small"/>
					</div>
				</div>
				<div class="col col-lg-2">Observaciones Vendedor</div>
				<div class="col col-lg-3">
					<form:input type="text" size="40" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" readonly="true" path="observaciones_vendedor" class="border border-secondary"/>
					<div class="has-error">
						<form:errors path="observaciones_vendedor" class="badge badge-danger small"/>
					</div>
				</div>
			</div>
		</div>
		<div class="col-12">
			<div class="row border border-right">
				<div class="col col-lg-2">Subir archivo</div>
				<div class="col col-lg-3">
					<input type="file" name="file" class="btn btn-outline-primary btn-sm" accept="application/pdf"/>
				</div>
			</div>
		</div>
		<br>
		<div class = "row" align="center">		
		<div class="col col-lg-4"><form:button class="btn btn-outline-primary btn-sm" disabled="${ExisteBocAut > 0 ? 'true' : 'false'}">
									<i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i>
								  </form:button></div>
		<div class="col col-lg-4"><a href="<c:url value="/cotizador/ingenieria/bocetosabc?id=${idcot}"/>" target="_self" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a></div>
		<div class="col col-lg-4"><form:button type="button" onclick="window.close()" class="btn btn-outline-primary btn-sm"> <i class="fa fa-window-close-o" aria-hidden="true"> Cerrar</i>
								  </form:button></div>
		</div>
	</form:form>
	<br>
	<table class="container-fluid table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>N</th>
				<th>Num proyecto</th>
				<th>Versión</th>
				<th>Observaciones diseñador</th>
				<th>Observaciones vendedor</th>
				<th>Archivo</th>
				<th>fecha alta</th>
				<th>fecha acepta</th>
				<th>fecha rechaza</th>
				<th>Enviar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${ListaBocetos}" varStatus="counter">
				<tr>
				<td>
					<c:choose>
					  <c:when test="${empty item.fecha_envio && empty item.usuario_envio}">
					    <a href="javascript:FBuscarxID(${idcot},${item.id})">${item.id}</a>
					  </c:when>
					 <c:otherwise>
					 	${item.id}
					 </c:otherwise>
					</c:choose>
				</td>
				<td>${item.num_proyecto}</td>
				<td>${item.version}</td>
				<td>${item.observaciones_diseniador}</td>
				<td>${item.observaciones_vendedor}</td>
				<td><a href="<c:url value="/static/bocetos/${item.nombre_archivo}.pdf"/>" target="_blank">${item.nombre_archivo}</a></td>
				<td><fmt:formatDate value="${item.fecha_insert}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${item.fecha_acepta}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td><fmt:formatDate value="${item.fecha_rechazo}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>
					<c:if test="${empty item.fecha_envio && empty item.usuario_envio}">
					<a href="javascript:FEnviar(${idcot},${item.id})"><i class="fa fa-paper-plane" aria-hidden="true"></i></a>
					</c:if>
				</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>