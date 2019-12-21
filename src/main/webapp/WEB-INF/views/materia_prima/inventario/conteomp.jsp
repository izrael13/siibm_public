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
<title>Conteo</title>
<script type="text/javascript">
function FBuscar()
{
	var rolloid = $('#TRolloIdB').val();
	if(rolloid != "")
		window.location.replace('<c:url value="/materia_prima/inventario/conteo"/>?rolloid='+rolloid);
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
<div class="row">
	<div class="badge badge-primary col-12">Conteo</div>
</div>
<div class="row small">
	<div class="col-lg border border-secondary">
		Rollo id
	</div>
	<div class="col-lg border border-secondary">
		<input id="TRolloIdB" type="text" size="15" onkeypress="return SinCaracteresEspeciales(event)" maxlength="50" class="border border-primary"/>
	</div>
	<div class="col-lg border border-secondary">
		<a href="javascript:FBuscar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a>
	</div>
</div>
<br>
<form:form method="POST" modelAttribute="Conteo" class="form-horizontal" autocomplete="off">
<div class="row small">
	<div class="col-lg border border-secondary">
		Rollo id
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="hidden" path="id"/>
		<form:input type="hidden" path="id_inv"/>
		<form:input type="text" size="15" readonly="true"  path="rollo_id" class="border border-secondary"/>
		<div class="has-error">
			<form:errors path="rollo_id" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Tipo
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" size="10" readonly="true" path="tipo" class="border border-secondary"/>
		<div class="has-error">
			<form:errors path="tipo" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Ancho
	</div>
	<div class="col-lg border border-secondary">
		<form:input size="10" type="text" path="ancho" readonly="true" class="border border-secondary"/>
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
		<form:input size="10" type="text" path="peso" readonly="true" class="border border-secondary"/>
		<div class="has-error">
			<form:errors path="peso" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Ubicacion
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" size="10"  path="ubicacion" onkeypress="return SinCaracteresEspeciales(event)" class="border border-primary" maxlength="15"/>
		<div class="has-error">
			<form:errors path="ubicacion" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		CONTEO
	</div>
	<div class="col-lg border border-secondary">
		<form:input size="10" required="true" type="text" path="conteo" onkeypress="return filterFloat(event,this);" class="border border-primary"/>
	</div>
</div>
<div class="row small">
	<div class="col-lg">
		<div class="float-right"><form:button id="BGrabar" class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
	</div>
</div>
</form:form>
</div>
</body>
</html>