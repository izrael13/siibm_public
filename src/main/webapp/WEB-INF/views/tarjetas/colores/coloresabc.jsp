<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" />
<html>
<head>
<%@ include file="../../appconfig/authheader2.jsp" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/2.3.3/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/2.3.3/js/bootstrap-colorpicker.min.js"></script>  
<title>Colores</title>
</head>
<body>
<br>
	<div class = "container-fluid" align="center">
		<span class="badge badge-secondary">Colores</span>
 		<form:form method="POST" modelAttribute="color" class="form-horizontal" autocomplete="off">
 			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-2"></div>
				<div class="col-sm-2">Id:
					<form:input size="10" class="border border-secondary" type="text" readonly="true" path="id"/>
				</div>
				<div class="col-sm-2">					
				    <div id="cp2" class="input-group colorpicker-component">
				    	EX: 
						<form:input class="border border-secondary" type="text" readonly="true" path="color_est" value="#00AABB" /> 
						<span class="input-group-addon"><i></i></span>
						<script type="text/javascript">
							$('#cp2').colorpicker();
						</script>
						<div class="has-error"><form:errors path="color_est" class="badge badge-danger small"/></div>
					</div>
				</div>
				<div class="col-sm-3">Arrastre:
					<form:input class="border border-primary" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="40" path="arrastre"/>
					<div class="has-error"><form:errors path="arrastre" class="badge badge-danger small"/></div>
				</div>
				<div class="col-sm-1">
					<form:button class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button>
				</div>
			</div>
			<div class="row small border border-right">
				<div class="col-sm-2"></div>
				<div class="col-sm-2">Color:
				<form:input class="border border-primary" id="color-picker-3" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="12" path="color"/>
				<div class="has-error"><form:errors path="color" class="badge badge-danger small"/></div>
				</div>
				<div class="col-sm-2">CMYK:
				<form:input class="border border-primary" onkeypress="return SinCaracteresEspeciales(event)" maxlength="15" type="text" path="cmyk"/>
				<div class="has-error"><form:errors path="cmyk" class="badge badge-danger small"/></div>
				</div>
				<div class="col-sm-3">Versión:
					<form:input class="border border-primary" onkeypress="return Enteros(event)" maxlength="5" type="text" path="version"/>
					<div class="has-error"><form:errors path="version" class="badge badge-danger small"/></div>
				</div>
			</div>
		</div>
 		</form:form>
 	</div>
 <br>
 <c:if test="${mensaje != null}"><div class="alert alert-success" role="alert">${mensaje}</div></c:if>
 <c:if test="${error != null}"><div class="alert alert-danger" role="alert">${error}</div></c:if>
<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered text-center mx-auto small"><!-- mx-auto  para centrar en pantalla -->
	<thead>
		<tr>
		<th>Id</th>
		<th>Color</th>
		<th>CMYK</th>
		<th>Arrastre</th>
		<th>Color Est</th>
		<th>Fecha captura</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${listacolores}">
		<tr>
			<td style="background:#${item.color_est}">${item.id}</td>
			<td>${item.color}</td>
			<td>${item.cmyk}</td>
			<td>${item.arrastre}</td>
			<td>${item.color_est}</td>
			<td>${item.fecha_captura}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>