<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<title>Registro de usuarios</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link rel="shortcut icon" href="<c:url value='/static/img/BarcaLogoV.png' />"/>
	<script>
	function Showpass()
	{
		var x = document.getElementById("password");
		  if (x.type === "password") {
			  $( "#Eye").removeClass().addClass("fa fa-eye");
		    x.type = "text";
		  } else {
			  $( "#Eye").removeClass().addClass("fa fa-eye-slash");
		    x.type = "password";
		  }
	}
	</script>
	<%@include file="authheader2.jsp"%>
</head>
<body>
 	<div class="generic-container">
		<div class="well lead">Registro de usuarios</div>
	 	<form:form method="POST" modelAttribute="user" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			
			<div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">Nombres</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="firstName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="lastName">Apellidos</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="ssoId" class="help-inline"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
	
			<div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Contraseña</label>
					<div class="input-group col-md-7">
						<form:input type="password" path="password" id="password" class="form-control input-sm" />
						<a href="">
							<i onmouseover="Showpass()" onmouseout="Showpass()" id="Eye" class="fa fa-eye-slash" aria-hidden="true"></i>
						</a>
						<div class="has-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="email">Email</label>
					<div class="col-md-7">
						<form:input type="text" path="email" id="email" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userProfiles" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userProfiles">Relación Vendedor SAP</label>
					<div class="col-md-7">
						<form:select path="cvevendedor_sap" class="form-control input-sm">
							<form:option value="0"> - - -</form:option>
							<form:options items="${vendedores}" itemValue="clavevendedor" itemLabel="nombre"></form:options>
						</form:select>
						<div class="has-error">
							<form:errors path="cvevendedor_sap" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div>
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Actualizar" class="btn btn-primary btn-sm"/>  -  <a href="<c:url value='/list' />">Regresar</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Registrar" class="btn btn-primary btn-sm"/> -  <a href="<c:url value='/list' />">Regresar</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>