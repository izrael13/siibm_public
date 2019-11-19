<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	   	  <div class="modal-header alert alert-info">
	        <h5 class="modal-title" id="ReqModal">Información adicional</h5>
	      </div>
	      <div class="container">
		  <div class="row">
		    <div class="col-sm">
		      Fecha creación:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_insert}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha actualización:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_update}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Fecha envío ventas:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_ventas}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha aut ventas:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_aut_ventas}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		   <div class="row">
		    <div class="col-sm">
		      Fecha envío prog:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_envia_a_prog}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha aut prog:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_aut_prog}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Fecha rechaza vtas:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_ventas}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Fecha rechaza prog:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_rech_prog}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Fecha cancelación:
		    </div>
		    <div class="col-sm">
		      <fmt:formatDate value="${cotizadordatabean.cotizador.fecha_cancel}" pattern="yyyy-MM-dd hh:mm"/>
		    </div>
		    <div class="col-sm">
		      Comentario ventas:
		    </div>
		    <div class="col-sm">
		      ${cotizadordatabean.cotizador.observaciones_ventas}
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-sm">
		      Comentario prog:
		    </div>
		    <div class="col-sm">
		     	${cotizadordatabean.cotizador.observaciones_prog}
		    </div>
		    <div class="col-sm">
		      Fecha rechaza diseñador:
		    </div>
		    <div class="col-sm">
		     	${cotizadordatabean.cotizador.fecha_rech_diseniador}
		    </div>
		  </div>
		  
		  <div class="row">
		    <div class="col-sm">
		      Observaciones diseñador:
		    </div>
		    <div class="col-sm">
		     	${cotizadordatabean.cotizador.observaciones_diseniador}
		    </div>
		    <div class="col-sm">
		      Fecha aut diseñador
		    </div>
		    <div class="col-sm">
		     	${cotizadordatabean.cotizador.fecha_asign_diseniador}
		    </div>
		  </div>
		  
		  <div class="modal-footer">
	        	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
		</div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="VtaModal" tabindex="-1" role="dialog" aria-labelledby="VtaModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title">Enviar a autorización</h5>
	      </div>
	      <div class="modal-body alert alert-warning">
	        ¡¡¡ATENCIÓN!!! ¿Desea enviar a autorización?
	      </div>
	      <div id="DivMensaje" class="modal-footer">
	        <button type="button" class="btn btn-primary" onClick="FEnviarVtaProg()">Enviar</button>
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