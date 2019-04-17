<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="imports.jsp"%>
<title>Recuperación de contraseña</title>
<script type="text/javascript">
function validar()
{
	if($("#password").val().trim() == "")
	{
		$( "#mensajes" ).text("Capture contraseña nueva");
		$( "#mensajes").removeClass().addClass("alert alert-danger");
		$("#password").focus();
	}
	else
	{
		if($("#password_conf").val().trim() == "")
		{
			$( "#mensajes" ).text("Confirme contraseña");
			$( "#mensajes").removeClass().addClass("alert alert-danger");
			$("#password_conf").focus();
		}
		else
		{
			if($("#password").val().trim() != $("#password_conf").val().trim())
				{
					$( "#mensajes" ).text("No coincide la contraseña, favor de verificar");
					$( "#mensajes").removeClass().addClass("alert alert-danger");
				}
			else
			{
				document.getElementById("myForm").submit();
			}
		}
	}
}

function Showpass()
{
	var x = document.getElementById("password");
	var y = document.getElementById("password_conf");
	
	  if (x.type === "password" && x.type === "password") {
		  $( "#Eye").removeClass().addClass("fa fa-eye fa-2x");
	    x.type = "text";
	    y.type = "text";
	  } else {
		  $( "#Eye").removeClass().addClass("fa fa-eye-slash fa-2x");
	    x.type = "password";
	    y.type = "password";
	  }
}
</script>
</head>
<body>
	<div align="center">
		<h2>Recuperación de contraseña <span class="badge badge-secondary">SIIBM</span></h2>
	</div>
	<br>
	<form:form id="myForm" method="POST" modelAttribute="user" class="mx-auto">
			<form:input type="hidden" path="id" id="id"/>
			<div class="container">
		<div class="row">

				<div class="col-md-4">
					<label class="col-md-4" for="ssoId">Usuario</label>
					<div class="col-md-8">
						<form:input readonly="true" type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="ssoId" class="help-inline"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<label class="col-md-4 control-lable" for="firstName">Nombres</label>
					<div class="col-md-8">
						<form:input readonly="true" type="text" path="firstName" id="firstName" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="firstName" class="help-inline"/>
						</div>
					</div>
				</div>
				<div class="form-group col-md-4">
					<label class="col-md-4 control-lable" for="lastName">Apellidos</label>
					<div class="col-md-8">
						<form:input readonly="true" type="text" path="lastName" id="lastName" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-8">
					<label class="col-md-3 control-lable" for="email">Email</label>
					<div class="col-md-7">
						<form:input readonly="true" type="text" path="email" id="email" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-8">
					<label class="col-md-4 control-lable" for="password">Nueva contraseña</label>
					<div class="col-md-8">
						<form:input required="true" type="password" path="password" id="password" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
				<div class="form-group col-md-8">
					<label class="col-md-4 control-lable">Confirmar contraseña</label>
					<div class="col-md-8">
						<input required="true" type="password" id="password_conf" class="form-control input-sm" />
					</div>
				</div>
				<a href=""><i onmouseover="Showpass()" onmouseout="Showpass()" id="Eye" class="fa fa-eye-slash fa-2x" aria-hidden="true"></i></a>
			</div>
						
			<div style="visibility:hidden" class="">
				<div class="">
					<div class="">
						<form:select path="userProfiles" items="${roles}" multiple="true"
							itemValue="id" itemLabel="type" class="form-control input-sm"
							size="1" />
						<div class="has-error">
							<form:errors path="userProfiles" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col form-actions">
					<input onClick="validar()"  value="Actualizar contraseña" class="btn btn-outline-primary"/>
					<a class="btn btn-outline-primary" href="${linkBarcasii}">Ir a SIIBM</a>
				</div>
			</div>
			</div>
	</form:form>
	<br>
	<div class="container">
		<div class="row">
			<div class="col form-actions">
				<div id="mensajes" class="${not empty success ? 'alert alert-success' : ''}">${success} </div>
				<div id="mensajes" class="${not empty error ? 'alert alert-warning' : ''}">${error} </div>
			</div>
		</div>
	</div>
	<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
	<%@include file="authfootter.jsp"%>
</body>
</html>