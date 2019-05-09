<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registro prospectos y actividades</title>
<script>
function FBuscarCiudades()
{
	//alert(document.getElementById("SEstado").value);
	var cve_estado = document.getElementById("SEstado").value;
	var opciones = "";
	$.ajax({
		//dataType: 'text',
		url: '<c:url value="/ventas/tarjetas/prospectos/buscarciudadxestado"/>?cve_estado='+cve_estado,
		//contentType : 'application/json',
		//cache: false,    
		//data: cve_estado,
		beforeSend: function(xhr) {
							  $( "#mensajes").text("Obteniendo ciudades, por favor espere...");
							  $( "#mensajes").removeClass().addClass("alert alert-primary");
					        },	
        success : function(data) {
            //$('#mensajes').html(data);
        	$('#mensajes').html("");
        	$( "#mensajes").removeClass();
        	$( "#ciudades" ).empty();
        	$.each(jQuery.parseJSON(data),function(index, value){
        	    //console.log('My array has at position ' + index + ', this value: ' + value.nombre);
        		opciones = opciones + "<option value='"+value.id_municipio + "'>"+value.nombre + "</option>";
        	});
        	
        	$( "#ciudades" ).append(opciones);
        },
        error: function(xhr, status, error) {
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
			  $( "#mensajes").removeClass().addClass("alert alert-danger");
		  }
	 });
}
function FBuscar()
{
	if($( "#TId" ).val() =="")
	{
		$( "#TId" ).val("0");
	}
	
	var isDisabled = $("#BGrabar").prop('disabled');
	$( "#TId" ).prop( "readonly", false );
	$( "#BGrabar" ).prop( "disabled", true );
	if(($( "#TId" ).val() > 0 || $( "#SClientes" ).val() != "0") && isDisabled == true)
	{
		popupwindow('<c:url value="/ventas/tarjetas/prospectos/prospectosbusqueda" />?id='+$( "#TId" ).val()+'&cardcode='+$( "#SClientes" ).val(),'Detalle de viaje',800,1000);
	}

		$( "#TId").removeClass().addClass("border border-success");
		$( "#SClientes").removeClass().addClass("border border-success");
	
}
function FBuscarxId(id)
{
	window.location.replace('<c:url value="/ventas/tarjetas/prospectos/prospectosabc" />?id='+id);
}
function FLimpar()
{
	window.location.replace('<c:url value="/ventas/tarjetas/prospectos/prospectosabc" />?id='+0);
}
</script>
<style type="text/css">
#tabla1{
	font-size: 13px;
	cellpadding: 2;
	cellspacing: 2;
}
</style>
<%@include file="../../appconfig/authheader2.jsp"%>
</head>
<body>
	<br>	
	<div align="center">
		<span class="badge badge-secondary">Datos Generales del Prospecto</span>
	</div>
	<form:form method="POST" modelAttribute="prospectos_bean" class="form-horizontal">
	<div align="left" class = "container">
		<div class="row small">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				<div class="row border border-right">
					<div class="col-sm-1">Folio</div>
					<div class="col-sm-1"><form:input class="border border-primary" size="5" maxlength="9" onkeypress="return Enteros(event)" id="TId" readonly="true" type="text" value="${empty prospectos_bean.prospectos_ventas.id ? 0 : prospectos_bean.prospectos_ventas.id}"  path="prospectos_ventas.id"/></div>
					<div class="col-sm-1 ">Cliente</div>
					<div class="col-sm-9 border-right">
						<form:select id="SClientes" path="prospectos_ventas.cardcode" multiple="false" class="border border-primary">
							<form:option value="0">Seleccione un cliente</form:option>
							<c:forEach var="cte" items="${clientes}">
								<form:option value="${cte.cardcode}"><c:out value="${cte.cardname} - Giro: ${cte.groupname}"/></form:option>
							</c:forEach>
						</form:select>
						<div class="has-error">
							<form:errors path="prospectos_ventas.cardcode" class="badge badge-danger small"/>
						</div>
					</div>
				</div>
				<div class="row border border-right">
					<div class="col-sm-1">Contacto</div>
					<div class="col-sm-3 border-right">
						<form:input type="text" style="width:250px" onkeypress="return SinCaracteresEspeciales(event)" maxlength="50" path="prospectos_ventas.contacto" class="border border-primary"/>
						<div class="has-error">
							<form:errors path="prospectos_ventas.contacto" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-2">Oportunidad en toneladas</div>
					<div class="col-sm-2 border-right">
						<form:input  type="text" id= "TOporton" path="prospectos_ventas.oportunidad_toneladas" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
						<div class="has-error">
							<form:errors path="prospectos_ventas.oportunidad_toneladas" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-2">Fecha cierre</div>
					<div class="col-sm-2 border-right">
						<form:input type="date" path="prospectos_ventas.fecha_cierre" name="bday" max="3000-12-31" min="1000-01-01" class="border border-primary"/>
						<div class="has-error">
							<form:errors path="prospectos_ventas.fecha_cierre" class="badge badge-danger small"/>
						</div>
					</div>						
				</div>
				<div class="row border border-right">
					<div class="col-sm-1">Teléfono</div>
					<div class="col-sm-3 border-right">
						<form:input type="text" style="width:250px" onkeypress="return SinCaracteresEspeciales(event)" maxlength="20" path="prospectos_ventas.telefono" class="border border-primary"/>
						<div class="has-error">
							<form:errors path="prospectos_ventas.telefono" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-1">Estatus</div>
					<div class="col-sm-3 border-right">
						<form:select path="prospectos_ventas.estatus" class="border border-primary">
							<form:option value="1" label="Pendiente" />
							<form:option value="2" label="Cerrada" />
							<form:option value="3" label="Ganada" />
							<form:option value="4" label="Perdida" />
						</form:select>
						<div class="has-error">
							<form:errors path="prospectos_ventas.estatus" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-1">Prioridad</div>
					<div class="col-sm-3 border-right">
						<form:select path="prospectos_ventas.prioridad" class="border border-primary">
							<form:option value="1" label="1" />
							<form:option value="2" label="2" />
							<form:option value="3" label="3" />
							<form:option value="4" label="4" />
						</form:select>
						<div class="has-error">
							<form:errors path="prospectos_ventas.prioridad" class="badge badge-danger small"/>
						</div>
					</div>
				</div>
				<div class="row border  border-right">
					<div class="col-sm-1">Email</div>
					<div class="col-sm-3 border-right">
						<form:input path="prospectos_ventas.email" maxlength="20" type="email" onkeypress="return SinCaracteresEspeciales(event)" class="border border-primary" id="exampleFormControlInput1" placeholder="name@example.com"/>
						<div class="has-error">
							<form:errors path="prospectos_ventas.email" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-1">Observaciones</div>
					<div class="col-sm-7 border-right">
						<form:input type="text" style="width:600px" maxlength="100" onkeypress="return SinCaracteresEspeciales(event)" path="prospectos_ventas.observaciones" class="border border-primary"/>
						<div class="has-error">
							<form:errors path="prospectos_ventas.observaciones" class="badge badge-danger small"/>
						</div>
					</div>
					<!-- <div class="col-sm-2">Usuario Alta</div>
					<div class="col-sm-2 border-right">$ { prospectos . usuario_insert}</div>  -->
				</div>
				<div class="row border  border-right">
					<div class="col-sm-1">Estado</div>
					<div class="col-sm-3 border-right">
						<form:select path="prospectos_ventas.cve_estado" id="SEstado" Onchange="FBuscarCiudades()" items="${estados}" multiple="false" itemValue="id" itemLabel="nombre" class="border border-primary" />
							<div class="has-error">
								<form:errors path="prospectos_ventas.cve_estado" class="badge badge-danger small"/>
							</div>
					</div>
					<div class="col-sm-2">Prev pond toneladas</div>
					<div class="col-sm-2 border-right">${prospectos_bean.prospectos_ventas.prevision_pon_ton}</div>
					<div class="col-sm-2">Fecha Alta</div>
					<div class="col-sm-2 border-right">${prospectos_bean.prospectos_ventas.fecha_insert}</div>	
				</div>
				<div class="row border  border-right">
					<div class="col-sm-1">Ciudad</div>
					<div class="col-sm-3 border-right">
							<form:select path="prospectos_ventas.cve_ciudad" id = "ciudades" items="${ciudades}" multiple="false" itemValue="id_municipio" itemLabel="nombre" class="border border-primary">
								<option value="0" label="Seleccione ciudad" />
							</form:select>
							<div class="has-error">
								<form:errors path="prospectos_ventas.cve_ciudad" class="badge badge-danger small"/>
							</div>
					</div>
					<div class="col-sm-2">Porcentaje avance</div>
					<div class="col-sm-2 border-right">${prospectos_bean.prospectos_ventas.porcentaje_avance}</div>
					<div class="col-sm-2">Fecha Actualización</div>
					<div class="col-sm-2 border-right">${prospectos_bean.prospectos_ventas.fecha_update}</div>
				</div>				
			</div>
		</div>		
	</div>
	<div id = "mensajes"></div>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Actividades</span>
	</div>
		<div align="left" class = "container">
		<div class="row small">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				<div class="row border border-right">
					<div class="col-sm-2">Fecha cierre</div>
					<div class="col-sm-3 border border-right">Actividad</div>
					<div class="col-sm-7 border border-right">Observaciones</div>
				</div>
				<div class="row border border-right">
					<div class="col-sm-2 border border-right">
						<form:input type="date" path="prospectos_ventas_detalle.fecha_cierre" name="bday" max="3000-12-31" min="1000-01-01" class="border border-primary"/>
						<div class="has-error">
							<form:errors path="prospectos_ventas_detalle.fecha_cierre" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-3">
						<form:select path="prospectos_ventas_detalle.cve_actividad" class="border border-primary">
								<form:option value="0" label="Seleccione actividad" />
								<form:option value="10" label="Identificación" />
								<form:option value="20" label="Contacto cliente" />
								<form:option value="30" label="Recabar información cotización" />
								<form:option value="40" label="Presentar cotización" />
								<form:option value="50" label="Respuesta cotización" />
								<form:option value="60" label="Cliente aceptó cotización" />
								<form:option value="70" label="Desarrollo TF" />
								<form:option value="80" label="Cliente envía OC" />
								<form:option value="100" label="Embarque primer pedido realizado" />
						</form:select>
						<div class="has-error">
							<form:errors path="prospectos_ventas_detalle.cve_actividad" class="badge badge-danger small"/>
						</div>
					</div>
					<div class="col-sm-7 border border-right">
					<form:input type="text" style="width:600px"  maxlength="100" onkeypress="return SinCaracteresEspeciales(event)" path="prospectos_ventas_detalle.observaciones" class="border border-primary"/>
						<div class="has-error">
							<form:errors path="prospectos_ventas_detalle.observaciones" class="badge badge-danger small"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<br>
		<div align="left" class = "container">
		<div class="row" align="center">
			<div class="col-sm-3"></div>
			<div class="col-sm-2"><form:button id="BGrabar" class="btn btn-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"></i> Grabar</form:button></div>
			<div class="col-sm-2"><a href="javascript:FBuscar()" class="btn btn-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
			<div class="col-sm-2"><a href="javascript:FLimpar()" class="btn btn-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a></div>
		</div>
		</div>
	</form:form>
	<br>
	<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto"><!-- mx-auto  para centrar en pantalla -->
		<thead>
			<tr>
				<th>Num</th>
				<th>Fecha cierre</th>
				<th>Actividad</th>
				<th>Observaciones</th>
				<th>Fecha alta</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="it" items="${prospectos_detallelist}">
				<tr>
					<td>${it.id}</td>
					<td>${it.fecha_cierre}</td>
					<td>
						<c:choose>
							<c:when test ="${it.cve_actividad == 10}">Identificación</c:when>
							<c:when test ="${it.cve_actividad == 20}">Contacto cliente</c:when>
							<c:when test ="${it.cve_actividad == 30}">Recabar información cotización</c:when>
							<c:when test ="${it.cve_actividad == 40}">Presentar cotización</c:when>
							<c:when test ="${it.cve_actividad == 50}">Respuesta cotización</c:when>
							<c:when test ="${it.cve_actividad == 60}">Cliente aceptó cotización</c:when>
							<c:when test ="${it.cve_actividad == 70}">Desarrollo TF</c:when>
							<c:when test ="${it.cve_actividad == 80}">Cliente envía OC</c:when>
							<c:when test ="${it.cve_actividad == 100}">Embarque primer pedido realizado</c:when>
						</c:choose>
					</td>
					<td>${it.observaciones}</td>
					<td>${it.fecha_insert}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>