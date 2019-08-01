<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="imports.jsp"%>
<title>Solicitud de cambio de contraseña</title>
<script>
function FMensaje()
{
	if($("#sso_id").val() != "" && $("#email").val() != "")
	{
		$("#mensajes" ).text("Enviando solicitud.");
		$("#mensajes").removeClass().addClass("alert alert-info");
	}
}
</script>
</head>
<body>
<br>
	<div align="center">
		<h2>Solicitud de cambio de contraseña <span class="badge badge-secondary">SIIBM</span></h2>
	</div>
	<br>
	<div align="center">
	<form:form method="POST" modelAttribute="solicitud" class="mx-auto" autocomplete="off">
	
	<form:input type="hidden" path="id" id="id"/>
	<form:input type="hidden" path="link" id="link"/>
	<form:input type="hidden" value="2018-01-01 01:01:01" path="fecha_sol" id="fecha_sol"/>
	<form:input type="hidden" value="2018-01-01 01:01:01" path="fecha_caducidad" id="fecha_caducidad"/>		
	<div>
			<div class="col-lg-2 col-md-2">
			</div>
			<div class="form-group col-md-8">
				<label class="col-md-3 control-lable" for="sso_id">Escribe tu usuario</label>
				<div class="col-md-7">
					<form:input type="text" path="sso_id" id="sso_id" class="form-control input-sm" required="true"/>
					<div class="has-error">
						<form:errors path="sso_id" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div>
			<div class="col-lg-2 col-md-2">
			</div>
			<div class="form-group col-md-8">
				<label class="col-md-3 control-lable" for="email">Escribe tu correo institucional</label>
				<div class="col-md-7">
					<form:input type="text" path="email" id="email" class="form-control input-sm" required="true"/>
					<div class="has-error">
						<form:errors path="email" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div>
			<div class="col"></div>
			<div class="col form-actions">
				<input onclick="FMensaje()" type="submit" value="Enviar solicitud" class="btn btn-outline-primary"/>    
				<a class="btn btn-outline-primary" href="javascript:window.close()">Cerrar</a>
			</div>
			<div class="col"></div>
		</div>
	</form:form>
	</div>
	<div class="container">
		<div>
		<div class="col-lg-2 col-md-2">
		</div>
			<div class="col-lg-8 col-md-8">
				<div id="mensajes" class="${not empty result ? 'alert alert-success h5' : ''}">${result} </div>
			</div>
		</div>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>