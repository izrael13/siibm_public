<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Catálogo de inventarios</title>
<script type="text/javascript">
function FLimpar()
{
	window.location.replace('<c:url value="/materia_prima/genrentemp/catalogo_inventarios"/>');
}
function FBuscar(id)
{
	window.location.replace('<c:url value="/materia_prima/genrentemp/catalogo_inventarios"/>?id='+id);
}
function FSubirArchivo(id)
{
	var redirectWindow = window.open('<c:url value="/materia_prima/genrentemp/gestion_archivo"/>?idinv='+id,'Subir archivo .');
	redirectWindow.replace();
}
</script>
</head>
<body>
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
	<div class="badge badge-primary col-12">Catálogo de inventarios</div>
</div>
<br>
<form:form method="POST" modelAttribute="Catalogo" class="form-horizontal" autocomplete="off">
<div class="container small">
	<div class="row">
		 <div class="col-lg border border-secondary">
		   Fecha Inicio
		 </div>
		 <div class="col-lg border border-secondary">
		    <div class="">
			<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
	            <form:input onkeypress="return false" path="fecha_inicio" data-target="#datetimepicker4" placeholder="yyyy-mm-dd" class="border border-primary"/>
	            <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
	                <div class="input-group-text"><i class="fa fa-calendar"></i></div>
	            </div>
            </div>
            </div>
			<div class="has-error">
				<form:errors path="fecha_inicio" class="badge badge-danger small"/>
			</div>
			<script type="text/javascript">
	            $(function () {
	                $('#datetimepicker4').datetimepicker({
	                    format: 'YYYY-MM-DD'
	                });
	            });
	        </script>
		 </div>
		 <div class="col-lg border border-secondary">
		   Fecha término
		 </div>
		 <div class="col-lg border border-secondary">
		    <div class="">
			<div class="input-group date" id="datetimepicker5" data-target-input="nearest">
                   <form:input onkeypress="return false" path="fecha_fin" data-target="#datetimepicker5" placeholder="yyyy-mm-dd" class="border border-primary"/>
                   <div class="input-group-append" data-target="#datetimepicker5" data-toggle="datetimepicker">
                       <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                   </div>
            </div>
            </div>
			<div class="has-error">
				<form:errors path="fecha_fin" class="badge badge-danger small"/>
			</div>
			<script type="text/javascript">
	            $(function () {
	                $('#datetimepicker5').datetimepicker({
	                    format: 'YYYY-MM-DD'
	                });
	            });
	        </script>
		 </div>
	</div>
	<div class="row">
		<div class="col-lg border border-secondary">
		   Descripción
		 </div>
		 <div class="col-lg border border-secondary">
		    <form:input type="text" style="width:600px" maxlength="100" onkeypress="return SinCaracteresEspeciales(event)" path="descripcion" class="border border-primary"/>
			<div class="has-error">
				<form:errors path="descripcion" class="badge badge-danger small"/>
			</div> 
		 </div>
	</div>
</div>
<br>
<div align="left" class = "container">
<div class="row" align="center">
	<div class="col-sm-3"></div>
	<div class="col-sm-2"><form:button id="BGrabar" class="btn btn-outline-primary"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
	<div class="col-sm-2"><a href="javascript:FLimpar()" class="btn btn-outline-primary"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a></div>
</div>
</div>
<br>
<table class="table table-hover small">
  <thead>
    <tr>
      <th scope="col">N</th>
      <th scope="col">Fecha inicio</th>
      <th scope="col">Fecha fin</th>
      <th scope="col">Descripción</th>
      <th scope="col">Gestión de archivos</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="it" items="${ListaCatalogos}">
    <tr>
      <th scope="row"><a href="javascript:FBuscar(${it.id})">${it.id}</a></th>
      <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${it.fecha_inicio}" /></td>
      <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${it.fecha_fin}" /></td>
      <td>${it.descripcion}</td>
      <td><a href="javascript:FSubirArchivo(${it.id})"><i class="fa fa-cloud-upload" aria-hidden="true"></i></a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</form:form>
</body>
</html>