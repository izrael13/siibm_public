<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"/>
<html>
<head>
<%@include file="../../appconfig/authheader2.jsp"%>
<title>Consulta de programas</title>
<script type="text/javascript">
function FBuscar()
{
	var pedido = ($("#TPedido").val() == "" ? 0 :  $("#TPedido").val());
	var id1 = ($("#TID1").val() == "" ? 0 :  $("#TID1").val());
	var id2 = ($("#TID2").val() == "" ? 0 :  $("#TID2").val());

	if((pedido > 0) || (id1 > 0 && id2 > 0))
	{
		window.location.replace('<c:url value="/programas/programacion/programaconsulta"/>?pedido='+pedido+'&id1='+id1+'&id2='+id2);
	}
}
function FLimpiar()
{
	window.location.replace('<c:url value="/programas/programacion/programaconsulta"/>');
}
function FImprimirTF(idprog, idtf)
{
	var redirectWindow = window.open('<c:url value="/programas/programacion/imprimirtfprog"/>?idtf='+idtf+'&idprog='+idprog);
	redirectWindow.replace;
}
</script>
</head>
<body>
	<div class="container small">
		<div class="row">
			 <div class="col-lg border border-secondary">Pedido</div>
			 <div class="col-lg border border-secondary">
			 	<input value="${pedido}" type="text" id="TPedido" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" />
			 </div>
			 
		</div>
		<div class="row">
			 <div class="col-lg border border-secondary">ID 1</div>
			 <div class="col-lg border border-secondary">
			 	<input value="${id1}" type="text" id="TID1" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" />
			 </div>
			 <div class="col-lg border border-secondary">ID 2</div>
			 <div class="col-lg border border-secondary">
			 	<input value="${id2}" type="text" id="TID2" class="border border-primary" size="9" maxlength="8" onkeypress="return Enteros(event)" />
			 </div>
		</div>

		<div  align="center">
			<button type="button" onClick="FBuscar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></button>
			<button type="button" onClick="FLimpiar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></button>
		</div>
		
		<table class="table table-sm table-bordered table-hover">
       		<thead>
       			<tr>
       				<th>Id</th>
       				<th>Pedido</th>
       				<th>Tarjeta SAP</th>
       				<th>Tarjeta Programa</th>
       				<th>Programa</th>
       				<th>Cant Acum</th>
       				<th>Cant Prog</th>
       				<th>Cant Pend</th>
       				<th>Observaciones</th>
       				<th>Imprimir TF</th>
       			</tr>
       		</thead>
       		<tbody >
       			<c:forEach var="item" items="${lista}">
					<tr>
						<td>${item.id}</td>
						<td>${item.pedido}</td>
						<td>${item.tf_sap}</td>
						<td>${item.tf_programa}</td>
						<td>${item.programa}</td>
						<td>${item.cant_acumulada}</td>
						<td>${item.cant_programada}</td>
						<td>${item.cant_pendiente}</td>
						<td>${item.observaciones}</td>
						<td><a class="btn btn-outline-primary btn-sm" href="javascript:FImprimirTF(${item.id},'${item.tf_sap}')"><i class="fa fa-print" aria-hidden="true"></i></a></td>
					</tr>
				</c:forEach>
       		</tbody>
       	</table>
		
	</div>
</body>
</html>