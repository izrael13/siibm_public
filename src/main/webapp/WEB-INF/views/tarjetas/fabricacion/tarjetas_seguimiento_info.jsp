<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/imports.jsp"%>
<title>Tarjetas seguimiento info</title>
<script>
function AmpliarImg(scr)
{
	foto=new Image();
	foto.src=scr;
	document.images[0].src=foto.src;
	ancho=foto.width;
	alto=foto.height;

	foto.width = ancho;
	foto.height = alto;
	popupwindow(scr,'imgen',alto,ancho);
	//alert(ancho)
	//alert(alto)
}
</script>
</head>
<body>
 <div class="container">
   	  <div class="badge badge-info col-12">
        Tarjetas seguimiento info
      </div>
      
	  <div class="row small">
	    <div class="col-sm">
	      Fecha aut calidad: ${tarjeta.fecha_aut_calidad}			    
	    </div>
	    <div class="col-sm">
			      Observaciones calidad: ${tarjeta.observaciones_calidad}			    
			    </div>
			    <div class="col-sm">
			      Fecha rech calidad: ${tarjeta.fecha_rech_calidad}			    
			    </div>
			  </div>
			  <div class="row small">
			    <div class="col-sm">
			      Fecha aut producción: ${tarjeta.fecha_aut_produccion}			    
			    </div>
			    <div class="col-sm">
			      Observaciones producción: ${tarjeta.observaciones_produccion}			    
			    </div>
			    <div class="col-sm">
			      Fecha rech producción: ${tarjeta.fecha_rech_produccion}			    
			    </div>
			  </div>
			  <div class="row small">
			    <div class="col-sm">
			      Fecha aut ingeniería: ${tarjeta.fecha_aut_ing}			    
			    </div>
			    <div class="col-sm">
			      Observaciones ingeniería: ${tarjeta.observaciones_ing}			    
			    </div>
			    <div class="col-sm">
			      Fecha rech ingeniería: ${tarjeta.fecha_rech_ing}			    
			    </div>
			  </div>
			  <div class="row small">
			    <div class="col-sm">
			      Fecha aut cliente: ${tarjeta.fecha_aut_cliente}			    
			    </div>
			    <div class="col-sm">
			      Observaciones cliente: ${tarjeta.observaciones_cliente}			    
			    </div>
			    <div class="col-sm">
			      Fecha rech cliente: ${tarjeta.fecha_rech_cliente}			    
			    </div>
			  </div>
			  <div class="row small">
			    <div class="col-sm">
			      Fecha enviada a autorizaciónes: ${tarjeta.fecha_aut_diseniador}			    
			    </div>
			    <div class="col-sm">
			      Fecha cancelación: ${tarjeta.fecha_cancela}			    
			    </div>
			    <div class="col-sm">
			      Fecha últ autorización: ${cotizador.fecha_aut_prog le cotizador.fecha_aut_ventas ? cotizador.fecha_aut_ventas : cotizador.fecha_aut_prog}			    
			    </div>
			  </div>
			  <div class="badge badge-info col-12">Especialidades</div>
				<div class="row small">
					<div class="col font-weight-bold">Especialidad</div>
					<div class="col font-weight-bold">Costo</div>
					<div class="col font-weight-bold">Ajuste</div>
					<div class="col font-weight-bold">CM</div>
				</div>
				<c:forEach var="item" items="${especialidades}">
					<div class="row small">
						<div class="col">${item[0]}</div>
						<div class="col">${item[1]}</div>
						<div class="col">${item[2]}</div>
						<div class="col">${item[4]}</div>
					</div>
				</c:forEach>
				
				<div class="badge badge-info col-12">Código de barras</div>
				<div class="row small">
					<div class="col font-weight-bold">Código</div>
					<div class="col font-weight-bold">Observaciones</div>
				</div>
				<c:forEach var="item" items="${cod_barras}">
					<div class="row small">
						<div class="col">${item.idcodigo}</div>
						<div class="col">${item.observaciones}</div>
					</div>
				</c:forEach>
				
				<div class="badge badge-info col-12">Detalles de caja</div>
				<div class="row small">
				    <div class="col-6">Caja: ${caja.nombrelargo}</div>
				    <div class="col">Largo: ${detalle.largo}</div>
				    <div class="col">Ancho: ${detalle.ancho}</div>
				</div>
			    <div class="row small">
			    	<div class="col-2">Fondo: ${detalle.fondo}</div>
				    <div class="col-5">Resistencia Barca: ${resis.resistencia} Flauta: ${resis.corrugado} Color: ${resis.color} $M2: ${resis.preciom2}</div>
				    <div class="col">Sello: ${sello.sellos}</div>
				    <div class="col">Cierre: ${detalle.cierre}</div>
				    <div class="col">Cierre detalle: ${detalle.cierre_detalle}</div>
			    </div>
			    <div class="row small">
			    	<div class="col">Ceja despl: ${detalle.ceja_desplegada == true ? 'Sí' : 'No'}</div>
				    <div class="col">Precio obj: ${detalle.precio_objetivo}</div>
				    <div class="col">Imp rebasada: ${detalle.score}</div>
				    <div class="col">Esp Inf: ${detalle.esp_inf}</div>
				    <div class="col">Esp Sup: ${detalle.esp_sup}</div>
				    <div class="col">Medida lámina: ${detalle.medida_lamina}</div>
				</div>
				<div class="row small">
					<div class="col">Área unitaria: ${detalle.area_unitaria}</div>
					<div class="col">Peso teórico: ${detalle.peso_teorico}</div>
					<div class="col">Num ranuras: ${detalle.num_raturas}</div>
					<div class="col">Tolerancia: ${cotizacion.tolerancia_pedido}</div>
					<div class="col">Pzas x juego: ${detalle.piezasxjuego}</div>
					<div class="col">Entrega OC: ${fn:substring(cotizador.fecha_entrega_oc,0,10)}</div>
				</div>
				<div class="row small">
					<div class="col">Piezas por tarima: ${detalle.piezasxtarima}</div>
				</div>
				
				<div class="badge badge-info col-12">Tintas</div>
				<div class="row small">
					<div class="col font-weight-bold">Color 1</div>
					<div class="col font-weight-bold">Color 2</div>
					<div class="col font-weight-bold">Color 3</div>
					<div class="col font-weight-bold">Color 4</div>
					<div class="col font-weight-bold">Color 5</div>
					<div class="col font-weight-bold">Color 6</div>
					<div class="col font-weight-bold">Color 7</div>
				</div>
				<div class="row small">
					<div class="col"><span style="background:#${color1.color_est}">${color1.color}</span></div>
				</div>
				
				<div class="badge badge-info col-12">Ruta</div>
				<div class="row small">
					<div class="col font-weight-bold">Maquina</div>
				</div>
				<c:forEach var="item" items="${tarjeta.catalogo_maquinas_sap_vw}">
					<div class="row small">
						<div class="col">${item.name}</div>
					</div>
				</c:forEach>
				
				<div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg">
				    <div class="modal-content">
				    	<div class="modal-header alert alert-info">
			        		<h5 class="modal-title">Imágenes</h5>
			      		</div>
				    	<div class = "container d-flex justify-content-center">
							<div class = "row">
								<div id="carouselExampleIndicators" class="carousel slide" data-interval="false" data-ride="carousel">
								  <div id="DImg" class="carousel-inner">
									<c:forEach var="item" items="${tarjeta.tarjeta_img}" varStatus="status">
									    <div class="${status.index == 0 ? 'carousel-item active':'carousel-item'}">
									      <img height="400" width="400" src="<c:url value="/static/img_tarjetas/${item.nombre}"/>" alt="${item.nombre}" id="" onclick="AmpliarImg('<c:url value="/static/img_tarjetas/${item.nombre}"/>')">
									      <div class="carousel-caption">
										    <h3>${item.nombre}</h3>
										    <p class="h2">${item.cama == true ? 'CAMA' : ''}</p>
										  </div>
									    </div>
									</c:forEach>
								  </div>
								  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
								    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
								    <span class="sr-only">Anterior</span>
								  </a>
								  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
								    <span class="carousel-control-next-icon" aria-hidden="true"></span>
								    <span class="sr-only">Siguiente</span>
								  </a>
								</div>
							</div>
						</div>		    	
				    </div>
				  </div>
				</div>
				<div class="badge badge-light col-12"><button type="button" class="btn btn-outline-primary btn-sm" data-toggle="modal" data-target=".bd-example-modal-xl"><i class="fa fa-picture-o" aria-hidden="true"> Imágenes</i></button></div>
				<div class="col-12"><a href="javascript:window.close()" class="btn btn-outline-primary"><i class="fa fa-window-close-o" aria-hidden="true"> Cerrar</i></a></div>
				
</div>  
</body>
</html>