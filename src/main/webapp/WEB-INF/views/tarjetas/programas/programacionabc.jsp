<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registro de programas</title>
<%@include file="../../appconfig/authheader2.jsp"%>
</head>
<body>
<br>	
<div align="center">
	<span class="badge badge-secondary">Registro de programas</span>
</div>
	
<form:form method="POST" modelAttribute="Programa" class="form-horizontal" autocomplete="off">
<div class="container">
<div class="row small">
	<div class="col-lg border border-secondary">
		Pedido
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="hidden" path="id"/>
		<form:input type="text" path="pedido" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)"/>
		<div class="has-error">
			<form:errors path="pedido" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Tarjeta SAP
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="tf_sap" class="border border-primary" size="9" maxlength="15" onkeypress="return SinCaracteresEspeciales(event)"/>
		<div class="has-error">
			<form:errors path="tf_sap" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Tarjeta programa
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="tf_programa" class="border border-primary" size="9" maxlength="15" onkeypress="return SinCaracteresEspeciales(event)"/>
		<div class="has-error">
			<form:errors path="tf_programa" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Programa
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="programa" class="border border-primary" size="9" maxlength="20" onkeypress="return SinCaracteresEspeciales(event)"/>
		<div class="has-error">
			<form:errors path="programa" class="badge badge-danger small"/>
		</div>
	</div>
</div>
<div class="row small">
	<div class="col-lg border border-secondary">
		Símbolo
	</div>
	<div class="col-lg border border-secondary">
		<input type="text" class="border border-secondary" size="9" readonly="true" value="${simbolo}"/>
	</div>
	<div class="col-lg border border-secondary">
		Descripción
	</div>
	<div class="col-lg border border-secondary">
		<input type="text" class="border border-secondary" size="40" readonly="true" value="${desc_simbolo}"/>
	</div>
</div>
<div class="row small">
	<div class="col-lg border border-secondary">
		Cant solicitada
	</div>
	<div class="col-lg border border-secondary">
		<input type="text" class="border border-secondary" size="9" readonly="true" value="${cant_sol}"/>
	</div>
	<div class="col-lg border border-secondary">
		Cant acumulada
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="cant_acumulada" class="border border-secondary" size="9" readonly="true"/>
		<div class="has-error">
			<form:errors path="cant_acumulada" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Cant programada
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="cant_programada" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)"/>
		<div class="has-error">
			<form:errors path="cant_programada" class="badge badge-danger small"/>
		</div>
	</div>
	<div class="col-lg border border-secondary">
		Cant pendiente
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="cant_pendiente" class="border border-secondary" size="9" readonly="true"/>
		<div class="has-error">
			<form:errors path="cant_pendiente" class="badge badge-danger small"/>
		</div>
	</div>
</div>
<div class="row small">
	<div class="col-lg border border-secondary">
		Observaciones
	</div>
	<div class="col-lg border border-secondary">
		<form:input type="text" path="observaciones" class="border border-primary" size="50" maxlength="100" onkeypress="return SinCaracteresEspeciales(event)"/>
		<div class="has-error">
			<form:errors path="observaciones" class="badge badge-danger small"/>
		</div>
	</div>
</div>
</div>
</form:form>
<%@include file="../../appconfig/authfootter.jsp"%>
</body>
</html>