<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="LimpiarModal" tabindex="-1" role="dialog" aria-labelledby="LimpiarModallLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title" id="exampleModalLabel">Limpiar</h5>
	      </div>
	      <div class="modal-footer">
	        <button type="button" onclick="FLimpar()" class="btn btn-outline-primary" data-dismiss="modal">Limpiar TODO</button>
	        <button type="button" onclick="FBuscarxId(${empty cotizadordatabean.cotizador.id ? 0 : cotizadordatabean.cotizador.id},0)" class="btn btn-outline-primary" data-dismiss="modal">Limpiar DETALLE</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade bd-example-modal-xl" id="AutModal" tabindex="-1" role="dialog" aria-labelledby="AutModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-xl">
	    <div class="modal-content">
	      <div class="container small">
		  <div class="row">
		    <div class="col-sm">
		      Fecha creación:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_insert}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha actualización:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_update}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha cancelación:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_cancel}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		  </div>
		  <div class="badge badge-info col-12">
	       	INGENIERÍA
	      </div>
	      <div class="row">
		    <div class="col-sm">
		      Fecha envío:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_ing}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha asig diseñador
		    </div>
		    <div class="col-sm">
		     	<fmt:formatDate value="${cotizadordatabean.cotizador.fecha_asign_diseniador}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha rechazo diseñador:
		    </div>
		    <div class="col-sm">
		     	<fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_diseniador}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		  </div>
		  <div class="row">
		  	<div class="col-sm">
		      Observaciones diseñador:
		    </div>
		    <div class="col-sm">
		     	${cotizadordatabean.cotizador.observaciones_diseniador}
		    </div>	
		  </div>
		  <div class="badge badge-info col-12">
	       	VENTAS
	      </div>
		  <div class="row">
		    <div class="col-sm">
		      Fecha envío:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_ventas}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha autorización:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_aut_ventas}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha rechazo:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_ventas}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Observaciones:
		    </div>
		    <div class="col-sm">
		      ${cotizadordatabean.cotizador.observaciones_ventas}
		    </div>
		  </div>
		  <div class="badge badge-info col-12">
	       	CALIDAD
	      </div>
		   <div class="row">
			   <div class="col-sm">
			      Fecha envío:
			    </div>
			    <div class="col-sm">
			      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_calidad}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
			   <div class="col-sm">
			      Fecha autorización:
			   </div>
			    <div class="col-sm">
			      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_aut_calidad}" pattern="yyyy-MM-dd HH:mm"/>
			    </div>
			    <div class="col-sm">
			      Fecha rechazo:
			    </div>
			    <div class="col-sm">
			      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_calidad}" pattern="yyyy-MM-dd HH:mm"/>
			    </div>		    
		  </div>
		   <div class="row">
		    <div class="col-sm">
		      Observaciones:
		    </div>
		    <div class="col-sm">
		     	${cotizadordatabean.cotizador.obsevaciones_calidad}
		    </div>
		  </div>
		  <div class="badge badge-info col-12">
	       	PROGRAMACIÓN
	      </div>
		  <div class="row">	
		  <div class="col-sm">
		      Fecha envío:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_a_prog}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha autorización:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_aut_prog}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>	    
		    <div class="col-sm">
		      Fecha rechazo:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_prog}" pattern="yyyy-MM-dd HH:mm"/>
		    </div>		    
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Observaciones:
		    </div>
		    <div class="col-sm">
		     	${cotizadordatabean.cotizador.observaciones_prog}
		    </div>
		    
		  </div>
		  
		  <div class="row">
		    
		  </div>
		 		  
		  <div class="modal-footer">
	        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
		</div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="IngBocModal" tabindex="-1" role="dialog" aria-labelledby="VtaModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title">Enviar a Ingeniería (Boceto)</h5>
	      </div>
	      <div class="modal-body alert alert-warning">
	        ¡¡¡ATENCIÓN!!! ¿Desea enviar a Ingeniería?
	      </div>
	      <div id="DivMensaje" class="modal-footer">
	        <button type="button" class="btn btn-primary" onClick="FEnviarIngBoc()">Enviar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="CancelModal" tabindex="-1" role="dialog" aria-labelledby="CancelModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title">CANCELAR</h5>
	      </div>
	      <div class="modal-body alert alert-danger">
	        ¡¡¡ATENCIÓN!!! ¿Desea CANCELAR ESTE REGISTRO?
	      </div>
	      <div id="DivMensaje" class="modal-footer">
	        <button type="button" class="btn btn-primary" onClick="FCancelar()">Cancelar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade bd-example-modal-xl" id="BocetosModal" tabindex="-1" role="dialog" aria-labelledby="BocetosModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-xl" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title">Bocetos</h5>
	      </div>
	      <div class="modal-body">
	       	<table class="table table-sm table-bordered table-hover">
		        	<thead>
		        		<tr>
		        			<th>Archivo</th>
		        			<th>Proyecto</th>
		        			<th>Versión</th>
		        			<th>Observaciones diseñador</th>
		        			<th>Observaciones vendedor</th>
		        			<th>Aceptar</th>
		        			<th>Rechazar</th>
		        		</tr>
		        	</thead>
		        	<tbody >
		        	<c:forEach var="item" items="${bocetos}" varStatus="status">
		        		<tr>
		        			<td><a href="<c:url value="/static/bocetos/${item.nombre_archivo}.pdf"/>" target="_blank">${item.nombre_archivo}</a></td>
		        			<td>${item.num_proyecto}</td>
		        			<td>${item.version}</td>
		        			<td>${item.observaciones_diseniador}</td>
		        			<td>
		        				<input type="text" maxlength="100" id="TBocObsVen${item.id}" onkeypress="return SinCaracteresEspeciales(event)" class="border border-primary"/>
		        			</td>
		        			<c:if test="${empty cotizadordatabean.cotizador.idboceto}">
			        			<td align="center"><a href="javascript:FAutRechBoc(${cotizadordatabean.cotizador.id},${cotizadordatabean.cotizador_detalles.iddetalle},${item.id},1)"><i class="text-success fa fa-file-text-o" aria-hidden="true"></i></a></td>
								<td align="center"><a href="javascript:FAutRechBoc(${cotizadordatabean.cotizador.id},${cotizadordatabean.cotizador_detalles.iddetalle},${item.id},0)"><i class="text-warning fa fa-thumbs-o-down" aria-hidden="true"></i></a></td>
							</c:if>
		        		</tr>
		        	</c:forEach>
		        	</tbody>
		        </table>
	      </div>
	      <div class="modal-footer">
	      	<div id="DivMsj" class="modal-footer"></div>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>
	