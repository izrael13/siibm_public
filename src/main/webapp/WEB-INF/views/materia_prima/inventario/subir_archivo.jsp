<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/imports.jsp"%>
<title>Gestión de archivos</title>
<script type="text/javascript">
function FSubirArchivo()
{
	var data = new FormData();
	jQuery.each(jQuery('#TSubirF')[0].files, function(i, file) {
		data.append('file', file);
	});
	data.append('id', '${idinv}');
	
	$("#mensajes" ).text("Subiendo archivo...").removeClass().addClass("alert alert-info");
	
	$.ajax({
	    url: '<c:url value="/materia_prima/genrentemp/subir_archivo"/>',
	    data: data,
	    type : 'POST',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        before: function() {
        	if (r.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
  	    },
	    success: function(r) {	 
	    	$("#mensajes" ).text(r).removeClass().addClass("alert alert-success");
	    	window.location.replace('<c:url value="/materia_prima/genrentemp/gestion_archivo"/>?idinv='+'${idinv}');
	    },
	    error: function(r) {	  
	    	$("#mensajes" ).text("Error: "+r.responseText).removeClass().addClass("alert alert-danger");
	    }
	  }); 
}
function FDescargarExcel()
{
	window.location.replace('<c:url value="/materia_prima/genrentemp/descargar_excel" />?id_inv='+'${idinv}');
}
</script>
</head>
<body>
<div id="mensajes"></div>
<c:if test="${error != null}">
	<div class="alert alert-warning">
		<p>${error}</p>
	</div>
</c:if>
<c:if test="${mensaje != null}">
	<div class="alert alert-success">
		<p>${mensaje}</p>
	</div>
</c:if>
<div class="container">
	<div class="badge badge-primary col-12">Gestión de archivos</div>
</div>
<div class="alert alert-warning col-12" align="center">Atención: Al subir archivos se reemplazará la información si existe, una vez con datos de CONTEO NO podrá subir archivos.</div>
<div class="container">
<form:form method="POST" modelAttribute="Conteo" class="form-horizontal" autocomplete="off">
<div class="row small">
	<div class="col-lg border border-secondary">
		Rollo id
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="hidden" path="id"/>
		<form:input type="hidden" path="id_inv" value="${idinv}"/>
		<form:input type="text" size="15" onkeypress="return SinCaracteresEspeciales(event)" maxlength="50" path="rollo_id" class="border border-primary"/>
		<div class="has-error">
			<form:errors path="rollo_id" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Tipo
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" size="10" onkeypress="return SinCaracteresEspeciales(event)" maxlength="11" path="tipo" class="border border-primary"/>
		<div class="has-error">
			<form:errors path="tipo" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Ancho
	</div>
	<div class="col-lg border border-secondary">
		<form:input size="10" type="text" path="ancho" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
		<div class="has-error">
			<form:errors path="ancho" class="badge badge-danger small"/>
		</div>
	</div>
</div>
<div class="row small">
	<div class="col-lg border border-secondary">
		Peso
	</div>
	<div class="col-lg border border-secondary">
		<form:input size="10" type="text" path="peso" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
		<div class="has-error">
			<form:errors path="peso" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Ubicacion
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" size="10" onkeypress="return SinCaracteresEspeciales(event)" maxlength="15" path="ubicacion" class="border border-primary"/>
		<div class="has-error">
			<form:errors path="ubicacion" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		<form:button id="BGrabar" class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button>
	</div>
</div>
</form:form>
<br>
<div class="row small">
 <div class="col-lg border border-secondary">
   Seleccione archivo
 </div>
 <div class="col-lg border border-secondary">
	<input type="file" id="TSubirF" class="btn btn-outline-primary btn-sm" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
 </div>
</div>
</div>
<div class="col-12" align="center">
	<a href="javascript:window.FSubirArchivo()" class="btn btn-outline-primary btn-sm"><i class="fa fa-cloud-upload" aria-hidden="true">Subir</i></a>
	<a href="javascript:window.close()" class="btn btn-outline-primary btn-sm"><i class="fa fa-window-close-o" aria-hidden="true"> Cerrar</i></a>
</div>
<br>
<a href="javascript:window.FDescargarExcel()" class="btn btn-outline-primary btn-sm"><i class="fa fa-file-excel-o" aria-hidden="true"></i>Descargar a EXCEL</a>
<table class="table table-hover small">
  <thead>
    <tr>
      <th scope="col">N</th>
      <th scope="col">Rollo id</th>
      <th scope="col">Tipo</th>
      <th scope="col">Ancho</th>
      <th scope="col">Peso</th>
      <th scope="col">Ubicacion</th>
      <th scope="col">Conteo</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="it" items="${ListaConteo}" varStatus="status">
    <tr>
      <th scope="row">${status.index + 1}</th>
      <td>${it.rollo_id}</td>
      <td>${it.tipo}</td>
      <td>${it.ancho}"</td>
      <td>${it.peso}</td>
      <td>${it.ubicacion}</td>
      <td>${it.conteo}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>