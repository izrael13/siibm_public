<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cambio de contraseña</title>
	<%@include file="authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Editar datos de usuario</span>
	</div>
	<br>

	<form:form method="POST" modelAttribute="user" class="mx-auto" autocomplete="off">
	<form:input type="hidden" path="id" id="id"/>
	<div class="container">
		<div class="row">
			<div class="col col-10">
				<label class="control-lable" for="ssoId">Usuario</label>
				<div>
					<form:input readonly="true" type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="ssoId" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col col-10">
				<label class="control-lable" for="firstName">Nombres</label>
				<div class="">
					<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="firstName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col col-10">
				<label class="control-lable" for="lastName">Apellidos</label>
				<div class="">
					<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="lastName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col col-10">
				<label class="control-lable" for="email">Email</label>
				<div class="">
					<form:input type="text" path="email" id="email" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="email" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
			
		<div style="visibility:hidden" class="row">
			<div class="">
				<div class="">
					<form:input type="hidden" path="password" id="password"/>
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
			<div class="col col-10">
				<input type="submit" value="Editar" class="btn btn-outline-primary"/>  <a class ="btn btn-outline-primary" href="<c:url value='/principal' />">Regresar</a>
			</div>
		</div>
	</div>
	</form:form>
	<br>
	<div class="container">
		<div class="row">
			<div class="col">
				<div id="mensajes" class="${not empty success ? 'alert alert-success' : ''}">${success} </div>
				<div id="mensajes" class="${not empty error ? 'alert alert-warning' : ''}">${error} </div>
			</div>
		</div>
	</div>
	<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
	<%@include file="authfootter.jsp"%>
</body>
</html>