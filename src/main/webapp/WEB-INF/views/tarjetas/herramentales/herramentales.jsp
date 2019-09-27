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
<title>Herramentales</title>
<script>
function FBuscar(id)
{
	window.location.replace('<c:url value="/herramentales/ingenieria/herramentalesabc" />?id='+id);
}
</script>
</head>
<body>
<br>	
<div align="center">
	<span class="badge badge-secondary">Herramentales</span>
	<div class = "container-fluid">
 		<form:form method="POST" modelAttribute="herramental" class="form-horizontal" autocomplete="off">
 		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-3">Herramental:
					<form:input type="hidden" path="id"/>
					<form:input class="border border-primary" type="text" onkeypress="return SinCaracteresEspeciales(event)" maxlength="100" path="nombre"/>
				</div>
				<div class="col-sm-3">Tipo:
					<form:select path="tipo" multiple="false" class="border border-primary">
						<form:option value="1">Suaje</form:option>
						<form:option value="2">Grabado</form:option>
					</form:select>
				</div>
				<div class="col-sm-3">Costo:
					<form:input class="border border-primary" type="text" onkeypress="return filterFloat(event,this);" maxlength="100" path="costo"/>
				</div>
				<div class="col-sm-3">
					<form:button class="btn btn-outline-primary btn-sm"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button>
				</div>
			</div>
		</div>
 		</form:form>
 		
 		
 		<div class="container-fluid">
  		<div class="row">
  		
    	<div class="col-sm"> 		
	 		<div class="badge badge-info col-12">Suajes</div>
			<div class="row small">
				<div class="col font-weight-bold">Herramental</div>
				<div class="col font-weight-bold">Costo</div>
			</div>
			<c:forEach var="item" items="${listaSuaj}">
				<div class="row small border">
					<div class="col"><a href="javascript:FBuscar(${item.id})">${item.nombre}</a></div>
					<div class="col">${item.costo}</div>
				</div>
			</c:forEach>
 		</div>
 		
 		<div class="col-sm">
 		<div class="badge badge-info col-12">Grabados</div>
		<div class="row small">
			<div class="col font-weight-bold">Herramental</div>
			<div class="col font-weight-bold">Costo</div>
		</div>
		<c:forEach var="item" items="${listaGrab}">
			<div class="row small border">
				<div class="col"><a href="javascript:FBuscar(${item.id})">${item.nombre}</a></div>
				<div class="col">${item.costo}</div>
			</div>
		</c:forEach>
 		</div>
 		
 		</div>
 		</div>
 	</div>
</div>
</body>
</html>