<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Seguimiento de cotizaciones info</title>
<%@include file="../../appconfig/imports.jsp"%>
<script>
function AmpliarImg(scr)
{
	foto=new Image();
	foto.src=scr;
	//document.images[0].src=foto.src;
	ancho=foto.width;
	alto=foto.height;

	foto.width = ancho;
	foto.height = alto;
	popupwindow(scr,'imagenestarjetas',alto,ancho);
	//alert(ancho)
	//alert(alto)
}
function FImprimirCot(id)
{
	var redirectWindow = window.open('<c:url value="/cotizador/ventas/imprimircotizador"/>?id='+id);
	redirectWindow.replace;
}
function FImprimirTF(id,iddet)
{
	var redirectWindow = window.open('<c:url value="/tarjeta/ingenieria/imprimirtf"/>?id='+id+'&iddet='+iddet);
	redirectWindow.replace;
}
function FImprimirReq(id)
{
	var redirectWindow = window.open('<c:url value="/cotizador/ingenieria/imprimirreq"/>?id='+id);
	redirectWindow.replace;
}
function FImprimirTFCTE(id,iddet)
{
	var redirectWindow = window.open('<c:url value="/cotizador/vendedor/imprimirtarjetacte"/>?id='+id+'&iddet='+iddet);
	redirectWindow.replace;
}
</script>
</head>
<body>
<div class = "container-fluid">
	<div class="row">
		<div class="badge badge-primary col-12">Cotización</div>
	</div>
	<div class="row small">
		<fmt:parseNumber var = "i" integerOnly = "true" pattern="##############" type = "number" value = "${cot['id']}" />
		<fmt:parseNumber var = "idboceto" integerOnly = "true" pattern="##############" type = "number" value = "${cot['idboceto']}" />
		<fmt:parseNumber var = "idet" integerOnly = "true" pattern="##############" type = "number" value = "${cotdet['iddetalle']}" />
		<div class="col-1">Folio: ${i}</div>
		<div class="col-5">Cliente: ${cot['cliente']}</div>
		<div class="col-6">Dirección: ${cot['lab']}</div>		
	</div>
	<div class="row small">
		<div class="col-8">Cliente factura: ${cot['cliente_factura']}</div>
	</div>
	<div class="row small">
		<div class="col-3">Vendedor: ${cot['representante']}</div>
		<div class="col-3">Fecha creación: ${cot['fecha_insert']}</div>
		<div class="col-3">Fecha ult actualización: ${cot['fecha_update']}</div>
		<div class="col-3">Fecha envío aut: ${cot['fecha_envia_ventas']}</div>
	</div>
	<div class="row small">
		<div class="col-3">Fecha rech ventas: ${cot['fecha_rech_ventas']}</div>
		<div class="col-3">Fecha rech prog: ${cot['fecha_rech_prog']}</div>
		<div class="col-3">Fecha aut ventas: ${cot['fecha_aut_ventas']}</div>
		<div class="col-3">Fecha aut prog: ${cot['fecha_aut_prog']}</div>
	</div>
	<div class="row small">
		<div class="col-4">Obs ventas: ${cot['observaciones_ventas']}</div>
		<div class="col-4">Obs prog: ${cot['observaciones_prog']}</div>
		<div class="col-4">Obs diseñador: ${cot['observaciones_diseniador']}</div>
	</div>
	<div class="row small">
		<div class="col-3">Fecha asig diseñador: ${cot['fecha_asign_diseniador']}</div>
		<div class="col-3">Diseñador: ${cot['diseniador']}</div>
		<div class="col-3">Fecha rech diseñador: ${cot['fecha_rech_diseniador']}</div>
		<div class="col-3">Rech diseñador: ${cot['diseniador_rech']}</div>
	</div>
	<div class="row small">
		<div class="col-3">Fecha aut calidad: ${cot['fecha_aut_calidad']}</div>
		<div class="col-3">Fecha rech calidad: ${cot['fecha_rech_calidad']}</div>
		<div class="col-6">Obs calidad: ${cot['obsevaciones_calidad']}</div>
	</div>
	<div class="row small">
		<div class="col-3">Fecha cancel: ${cot['fecha_cancel']}</div>
		<div class="col-3">Usuario cancel: ${cot['usuario_cancel']}</div>
		<div class="col-5">Símbolo: ${cotdet['simbolo']}</div>
	</div>
	<div class="row">
		<div class="badge badge-primary col-12">Detalle</div>
	</div>
	<div class="row small">
		<div class="col-1">Emplayado: ${cot['emplayado'] == true ? 'Sí' : ''}</div>
		<div class="col-2">Vueltas emplaye: ${cot['vueltas_emplaye']}</div>
		<div class="col-1">Factura: ${cot['factura'] == true ? 'Sí' : ''}</div>
		<div class="col-2">Certif calidad: ${cot['certif_calidad'] == true ? 'Sí' : ''}</div>
		<div class="col-2">Imprimir OC: ${cot['imprimir_oc'] == true ? 'Sí' : ''}</div>
		<div class="col-1">Caja seca: ${cot['caja_seca'] == true ? 'Sí' : ''}</div>
		<div class="col-2">Certif fumig: ${cot['certif_fumig'] == true ? 'Sí' : ''}</div>
	</div>
	<div class="row small">
		<div class="col-3">Proteccion: ${cot['protecciones_nom']}</div>
		<div class="col-3">Identificador: ${cot['identificador_nom']}</div>
		<div class="col-3">COBB: ${cot['cobb']}</div>
	</div>
	<div class="row small">
		<div class="col-1">EPP: ${cot['epp_transportista'] == true ? 'Sí' : ''}</div>
		<div class="col-2">Imprimir fechador: ${cot['imprimir_fechador'] == true ? 'Sí' : ''}</div>
		<div class="col-2">Imprimir pedido: ${cot['imprimir_pedido'] == true ? 'Sí' : ''}</div>
		<div class="col-2">Tarima x unitizado: ${cot['tarimaxunitizado'] == true ? 'Sí' : ''}</div>
		<div class="col-3">Fecha entrega OC: ${cot['fecha_entrega_oc']}</div>
		<div class="col-2">Se entregó: ${cot['se_entrego']}</div>
	</div>
	<div class="row small">
		<div class="col-2">Tolerancia: ${cot['tolerancia_pedido']}</div>
		<div class="col-2">Diseño: ${cot['disenio']}</div>
		<div class="col-1">Caja: ${cotdet['estilo_caja']}</div>
		<div class="col-1">Largo: ${cotdet['largo']}</div>
		<div class="col-1">Ancho: ${cotdet['ancho']}</div>
		<div class="col-1">Fondo: ${cotdet['fondo']}</div>
		<div class="col-2">Resistencia: ${cotdet['resistencia']}</div>
		<div class="col-1">Flauta: ${cotdet['flauta']}</div>
		<div class="col-1">Papel: ${cotdet['papel']}</div>
	</div>
	<div class="row small">
		<div class="col-1">Sello: ${cotdet['resis_cte']}</div>
		<div class="col-2">Cierre: ${cotdet['cierre']}</div>
		<div class="col-2">Cierre det: ${cotdet['cierre_detalle']}</div>
		<div class="col-2">Piezas x juego: ${cotdet['piezasxjuego']}</div>
		<div class="col-5">Obs vendedor: ${cotdet['observaciones_vendedor']}</div>
	</div>
	<div class="row small">
		<div class="col-1">Esp inf: ${cotdet['esp_inf']}</div>
		<div class="col-1">Esp sup: ${cotdet['esp_sup']}</div>
		<div class="col-2">Pedido mensual: ${cotdet['cantidad_pedido_mes']}</div>
		<div class="col-2">Precio objetivo: ${cotdet['precio_objetivo']}</div>
		<div class="col-2">Piezas x tarima: ${cotdet['piezasxtarima']}</div>
		<div class="col-1">Imp reb: ${cotdet['score']}</div>
		<div class="col-1">Tintas: ${cotdet['num_tintas']}</div>
		<div class="col-2">Medida lámina: ${cotdet['medida_lamina']}</div>
	</div>
	<div class="row small">
		<div class="col-2">Área unitaria: ${cotdet['area_unitaria']}</div>
		<div class="col-1">M2: ${cotdet['m2']}</div>
		<div class="col-1">KG: ${cotdet['kg']}</div>
		<div class="col-2">Peso teórico: ${cotdet['peso_teorico']}</div>
		<div class="col-2">Precio neto: ${cotdet['precio_neto']}</div>
		<div class="col-2">Precio sugerido: ${cotdet['precio_sugerido']}</div>
		<div class="col-2">Costo papel: ${cotdet['costo_papel']}</div>
	</div>
	<div class="row small">
		<div class="col-2">% Comisión: ${cotdet['porcentaje_comision']}</div>
		<div class="col-2">Descuento vendedor: ${cotdet['descuento_vendedor']}</div>
		<div class="col-2">Peso pieza: ${cotdet['peso_pieza']}</div>
		<div class="col-2">Ref comisión: ${cotdet['ref_para_comision']}</div>
		<div class="col-2">Comisión x millar: ${cotdet['comisionxmillar']}</div>
		<div class="col-2">CPCC: ${cotdet['cpcc']}</div>
	</div>
	<div class="row small">
		<div class="col-2">Peso resistencia: ${cotdet['peso_resis']}</div>
		<div class="col-2">Largo pliego: ${cotdet['largo_pliego']}</div>
		<div class="col-2">Ancho pliego: ${cotdet['ancho_pliego']}</div>
		<div class="col-2">Total especialidades: ${cotdet['total_especialidades']}</div>
		<div class="col-2">Area total: ${cotdet['area_total']}</div>
		<div class="col-2">Peso juego: ${cotdet['peso_juego']}</div>
	</div>
	<div class="row small">
		<div class="col-2">PK teórico: ${cotdet['pk_teorico']}</div>
		<div class="col-2">Ceja desplegada: ${cot['ceja_desplegada'] == true ? 'Sí' : ''}</div>
		<div class="col-2">Num ranuras: ${cotdet['num_ranuras']}</div>
		<div class="col-2">Cancelar/Sustituir: ${cot['cancelar_sustituir']  == true ? 'Sí' : ''}</div>
		<div class="col-2">TF: ${cot['tf_cs']}</div>
		<div class="col-2">Altura pallet: ${cotdet['altura_pallet']}</div>
	</div>
	<div class="row small">
		<div class="col-2">Camas pallet: ${cotdet['camas_pallet']}</div>
		<div class="col-2">Flejes pallet: ${cotdet['flejes_pallet']}</div>
		<div class="col-2">Flejes atado: ${cotdet['flejes_atado']}</div>
		<div class="col-2">Atados cama: ${cotdet['atados_cama']}</div>
		<div class="col-2">Piezas atado: ${cotdet['pzas_atado']}</div>
		<div class="col-2">A granel: ${cotdet['agranel']}</div>
	</div>
	<sec:authorize access="hasRole('ADMIN') or hasRole('VENTAS')">
	<div class="row small">
		<div class="col-2">Flete: ${cot['costo_flete']}</div>
		<div class="col-2">Costo flete: ${cotdet['costo_flete']}</div>
		<div class="col-2">Comisión directo: ${cotdet['comision_directo']}</div>
		<div class="col-2">Porcentaje flete: ${cotdet['porc_flete']}</div>
		<div class="col-2">Costo papel resitencia: ${cotdet['costo_papel_resis']}</div>
	</div>
	</sec:authorize>
	<div class="row small">
		<div class="col-2" style="background:${cotdet['color1c']}">Color1: ${cotdet['color1n']}</div>
		<div class="col-2" style="background:${cotdet['color2c']}">Color2: ${cotdet['color2n']}</div>
		<div class="col-2" style="background:${cotdet['color3c']}">Color3: ${cotdet['color3n']}</div>
		<div class="col-2" style="background:${cotdet['color4c']}">Color4: ${cotdet['color4n']}</div>
	</div>
	<div class="row small">
		<div class="col-2" style="background:${cotdet['color5c']}">Color5: ${cotdet['color5n']}</div>
		<div class="col-2" style="background:${cotdet['color6c']}">Color6: ${cotdet['color6n']}</div>
		<div class="col-2" style="background:${cotdet['color7c']}">Color7: ${cotdet['color7n']}</div>
		<sec:authorize access="hasRole('ADMIN') or hasRole('VENTAS') or hasRole('VENDEDOR') or hasRole('PROGRAMACION') or hasRole('INGENIERIA')">
			<div class="col-2"><a href="javascript:FImprimirReq(${i})"><i class="fa fa-print" aria-hidden="true">Imprimir requerimiento</i></a></div>
			<div class="col-2"><a href="<c:url value="/static/bocetos/${i}-${idboceto}.pdf"/>" target="_blank"><i class="fa fa-print" aria-hidden="true">Boceto</i></a></div>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN') or hasRole('VENTAS') or hasRole('INGENIERIA')">
			<div class="col-2"><a href="javascript:FImprimirCot(${i})"><i class="fa fa-print" aria-hidden="true">Imprimir cotización</i></a></div>
			<div class="col-2"><a href="javascript:FImprimirTF(${i},${idet})"><i class="fa fa-print" aria-hidden="true">Imprimir TF Ingeniería</i></a></div>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN') or hasRole('VENDEDOR')">
			<div class="col-2"><a href="javascript:FImprimirTFCTE(${i},${idet})"><i class="fa fa-print" aria-hidden="true">Imprimir TF Cliente</i></a></div>
		</sec:authorize>
	</div>
	<div class="badge badge-info col-12">Especialidades</div>
	<div class="row small">
		<div class="col font-weight-bold">Especialidad</div>
		<div class="col font-weight-bold">Costo</div>
		<div class="col font-weight-bold">Ajuste</div>
		<div class="col font-weight-bold">CM</div>
	</div>
	<c:forEach var="item" items="${cotdet['ListaEsp']}">
		<div class="row small">
			<div class="col">${item['especialidad']}</div>
			<div class="col">${item['costo']}</div>
			<div class="col">${item['ajuste']}</div>
			<div class="col">${item['cm']}</div>
		</div>
	</c:forEach>	
			    
    <div class="badge badge-info col-12">Código de barras</div>
	<div class="row small">
		<div class="col font-weight-bold">Código</div>
		<div class="col font-weight-bold">Observaciones</div>
	</div>
	<c:forEach var="item" items="${cotdet['codigo_barra_cotizador']}">
		<div class="row small">
			<div class="col">${item['idcodigo']}</div>
			<div class="col">${item['observaciones']}</div>
		</div>
	</c:forEach>
	<div class="row">
		<div class="badge badge-primary col-12">Tarjeta</div>
	</div>
	<div class="row small">
		<div class="col-1">TF: ${tar['folio_tarjeta']}</div>
		<div class="col-5">Descripción factura: ${tar.descripcion_factura}</div>
		<div class="col-2">Num partes: ${tar.num_partes}</div>
		<div class="col-2">Piezas x largo: ${tar.pzasxlargo}</div>
		<div class="col-2">Piezas x ancho: ${tar.pzasxancho}</div>
	</div>
	<div class="row small">
		<div class="col-2">Medida pliego: ${tar.medida_pliego}</div>
		<div class="col-4">Medidas internas-> Largo:${cotdet.mil} Ancho:${cotdet.mia} Fondo:${cotdet.mif} </div>
		<div class="col-2">Grabado: ${gra.nombre}</div>
		<div class="col-2">Suaje: ${sua.nombre}</div>
		<div class="col-2">Rayado1: ${tar.rayado1}</div>
	</div>
	<div class="row small">
		<div class="col-2">Rayado2: ${tar.rayado2}</div>
		<div class="col-2">Rayado3: ${tar.rayado3}</div>
		<div class="col-2">Rayado4: ${tar.rayado4}</div>
		<div class="col-2">Rayado5: ${tar.rayado5}</div>
		<div class="col-2">Rayado6: ${tar.rayado6}</div>
		<div class="col-2">Área total: ${tar.area_total}</div>
	</div>
	<div class="row small">
		<div class="col-2">Pegado/Grabado: ${tar.pegado_grapado}</div>
		<div class="col-5">Obs TF: ${tar.observaciones_tf}</div>
		<div class="col-5">Obs: ${tar.observaciones}</div>
	</div>
	<div class="row small">
		<div class="col-6">Obs diseñador: ${tar.observaciones_disenador}</div>
		<div class="col-3">Fecha aut diseñador: ${tar.fecha_aut_diseniador}</div>
		<div class="col-3">Fecha aut calidad: ${tar.fecha_aut_calidad}</div>
	</div>
	<div class="row small">
		<div class="col-6">Obs calidad: ${tar.observaciones_calidad}</div>
		<div class="col-3">Fecha rech calidad: ${tar.fecha_rech_calidad}</div>
	</div>
	<div class="row small">
		<div class="col-3">Fecha aut producción: ${tar['fecha_aut_produccion']}</div>
		<div class="col-3">Fecha rech producción: ${tar['fecha_rech_produccion']}</div>
		<div class="col-3">Obs producción: ${tar['observaciones_produccion']}</div>
	</div>
	<div class="row small">
		<div class="col-3">Fecha aut ingeniería: ${tar.fecha_aut_ing}</div>
	</div>
	<div class="row small">
		<div class="col-6">Obs ingeniería: ${tar.observaciones_ing}</div>
		<div class="col-3">Fecha rech ingeniería: ${tar.fecha_rech_ing}</div>
		<div class="col-3">Fecha aut cliente: ${tar.fecha_aut_cliente}</div>
	</div>
	<div class="row small">
		<div class="col-6">Obs cliente: ${tar.observaciones_cliente}</div>
		<div class="col-3">Fecha rech ingeniería: ${tar.fecha_rech_cliente}</div>
		<div class="col-3">Fecha cancela: ${tar.fecha_cancela}</div>
	</div>
	<div class="row small">
		<div class="col-6">Usuario cancela:  ${tar.usr_cancela}</div>
	</div>
	<div class="badge badge-info col-12">Máquinas</div>
	<c:forEach var="item" items="${tar.catalogo_maquinas_sap_vw}">
		<div class="row small">
			<div class="col">${item.name}</div>
		</div>
	</c:forEach>
	<div class="badge badge-info col-12">Imágenes</div>
	<br><br>
<div class = "row mx-auto">
		<div id="carouselExampleIndicators" class="carousel slide" data-interval="false" data-ride="carousel">
		  <div id="DImg" class="carousel-inner">
		  		<div class="carousel-item active">
		  			<img height="400" width="400" src="<c:url value="/static/img_tarjetas/${tar.cama_nombre}"/>" alt="${tar.cama_nombre}" id="" onclick="AmpliarImg('<c:url value="/static/img_tarjetas/${tar.cama_nombre}"/>')">
		  		</div>
		  		<div class="carousel-item">
		  			<img height="400" width="400" src="<c:url value="/static/img_tarjetas/${tar.principal_nombre}"/>" alt="${tar.principal_nombre}" id="" onclick="AmpliarImg('<c:url value="/static/img_tarjetas/${tar.principal_nombre}"/>')">
		  		</div>
			<c:forEach var="item" items="${tar.tarjeta_img}" varStatus="status">
			    <div class="carousel-item">
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
	
	<div class="col-12" align="center"><a href="javascript:window.close()" class="btn btn-outline-primary"><i class="fa fa-window-close-o" aria-hidden="true"> Cerrar</i></a></div>
</div>
<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>