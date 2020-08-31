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
function FBoceto(id)
{
	var redirectWindow = window.open('<c:url value="/cotizador/ingenieria/bocetosabc"/>?id='+id);
	redirectWindow.replace;
}
function FImprimir(id)
{
	var redirectWindow = window.open('<c:url value="/cotizador/ingenieria/imprimirreq"/>?id='+id);
	redirectWindow.replace;
}
function FSeguimiento(id, iddet)
{
	var redirectWindow = window.open('<c:url value="/cotizador/vendedor/seguimiento_cot_hist" />?id='+id+'&iddet='+iddet);
	redirectWindow.replace();
}
function FRefreshParent()
{
	window.location.replace('<c:url value="/cotizador/ingenieria/requerimientoabc"/>');
}
</script>
</head>
<body>
		<br>
	<div align="center">
		<span class="badge badge-secondary">Imprimir/Asignar diseñador requerimientos</span>
	</div>
	<br>
	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	<div align="center" class="container-fluid">
	<div class="row ">	
	 	<button data-toggle="modal" data-target="#ValidaModal" type="button" class="badge badge-info col-6">Valida</button>
	 	<button data-toggle="modal" data-target="#VerificaModal" type="button" class="badge badge-info col-6">Verifica</button>
	 </div>
	<table class="container-fluid table-hover text-center table-bordered small">
		<thead>
			<tr>
				<th>Folio</th>
				<th>Cliente</th>
				<th>Vendedor</th>
				<th colspan="6">Detalles</th>
				<th>Comentarios</th>
				<th>Grabar comentario</th>
				<th>Imprimir</th>
				<th class="text-info">Boceto</th>
				<th class="text-success">Convertir a Tarjeta</th>
				<th class="text-warning">Rechazar</th>
				<th class="text-danger">Cancelar</th>
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
					<tr>
					<th>Det</th>
					<th>Largo</th>
					<th>Ancho</th>
					<th>Color</th>
					<th>Flauta</th>
					<th>M pliego</th>
					<th>Especialidades</th>
					</tr>
					<c:forEach var="det" items="${item['ListaDetalles']}" varStatus="counter">
						<fmt:parseNumber var = "iddet" integerOnly = "true" pattern="##############" type = "number" value = "${det['iddetalle']}" />
					<tr>
						<td><a href="javascript:FSeguimiento(${i},${iddet})">${iddet}</a></td>
						<td>${det['largo']}</td>
						<td>${det['ancho']}</td>
						<td>${det['papel']}</td>
						<td>${det['flauta']}</td>
						<td>${det['medida_lamina']}</td>
						<td>
							<table class="container-fluid text-center">
								<c:forEach var="esp" items="${det['ListaEsp']}" varStatus="counter">
									<tr><td>${esp['especialidad']}</td></tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					</c:forEach>
					</table>
					</td>	
				<td><input id="TComent${i}" type="text" size="50" onkeypress="return SinCaracteresEspeciales(event)" value="${item['observaciones_diseniador']}" maxlength="100" class="border border-primary"/></td>
				<td><a href="javascript:FTarjeta(${i},0)"><i class="fa fa-floppy-o" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FImprimir(${i})"><i class="fa fa-print" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FBoceto(${i})"><i class="text-info fa fa-cloud-upload" aria-hidden="true"></i></a></td>
				<c:if test="${!empty item['fecha_envia_ing'] && !empty item['fecha_aut_prog'] && 
								  !empty item['fecha_aut_ventas'] && empty item['fecha_cancel'] &&
								  !empty item['fecha_asign_diseniador'] && !empty item['fecha_aut_calidad']}">
				<td><a href="javascript:FTarjeta(${i},1)"><i class="text-success fa fa-file-text-o" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FTarjeta(${i},3)"><i class="text-warning fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
				<td><a href="javascript:FTarjeta(${i},2)"><i class="text-danger fa fa-times-circle-o" aria-hidden="true"></i></a></td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	
				<!-- REGION DE MODALS -->
		<div class="modal fade bd-example-modal-lg" id="ValidaModal" tabindex="-1" role="dialog" aria-labelledby="ValidaModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header alert alert-info">
		        <h5 class="modal-title">Lista de validación</h5>
		      </div>
		      <div class="modal-body">
		        <table class="table table-sm table-bordered table-hover">
		        	<thead>
		        		<tr>
		        			<th>Actividad</th>
		        		</tr>
		        	</thead>
		        	<tbody id="TBodyCodBarras">
		        	<c:forEach var="item" items="${listaAproValida}" varStatus="status">
		        		<tr>
		        			<td>
		        				${item.descripcion}
		        			</td>
		        		</tr>
		        	</c:forEach>
		        	</tbody>
		        </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="modal fade bd-example-modal-lg" id="VerificaModal" tabindex="-1" role="dialog" aria-labelledby="VerificaModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header alert alert-info">
		        <h5 class="modal-title">Lista de verificación</h5>
		      </div>
		      <div class="modal-body">
		        <table class="table table-sm table-bordered table-hover">
		        	<thead>
		        		<tr>
		        			<th>Actividad</th>
		        		</tr>
		        	</thead>
		        	<tbody id="TBodyCodBarras">
		        	<c:forEach var="item" items="${listaAproVerifica}" varStatus="status">
		        		<tr>
		        			<td>
		        				${item.descripcion}
		        			</td>
		        		</tr>
		        	</c:forEach>
		        	</tbody>
		        </table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		      </div>
		    </div>
		  </div>
		</div>
	
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>