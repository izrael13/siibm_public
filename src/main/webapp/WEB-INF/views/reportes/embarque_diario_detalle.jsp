<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Embarque diario detalle</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscar()
{
	window.location.replace('<c:url value="/reportes/embarques/embarquediariodetalle" />?fecha='+$("#TDTP").val());
}
function Keypress()
{
	return false;
}
</script>
</head>
<body>
<br>
	<div align="center">
		<span class="badge badge-secondary">Embarque diario detalle</span>
	</div>
<br>
<div align="center" class="container rounded border border-info">
	<div class="row">
	<div class="col-md-4"></div>
	<div class="col-md-3">
	<div align="center">
		<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
		    <input onkeypress="return Keypress()" id="TDTP" data-target="#datetimepicker4" placeholder="yyyy-mm-dd" class="border border-primary" value="${selectedValue}"/>
		    <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
		        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
		    </div>
	    </div>
    </div>
	<script type="text/javascript">
        $(function () {
            $('#datetimepicker4').datetimepicker({
                format: 'YYYY-MM-DD'
            });
        });
    </script>
    </div>
    <div class="col-md-2">
    	<button onclick="FBuscar()" class="btn btn-outline-primary"><i class="fa fa-search"></i>Buscar</button>
    </div>
    </div>
</div>
<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered mx-auto small"><!-- mx-auto  para centrar en pantalla -->
		<thead>
			<tr align="center">
				<th>Num Sap</th>
				<th>Remisión</th>
				<th>Vendedor</th>
				<th>descripción</th>
				<th>Cantidad</th>
				<th>Peso</th>
				<th>Cliente</th>
				<th>Chofer</th>
				<th>Hora</th>
				<th>Embarca</th>
				<th>Comentarios</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<tr>
				<td>${item.docnum}</td>
				<td>${item.numatcard}</td>
				<td>${item.slpname}</td>
				<td>${item.dscription}</td>
				<td>${item.quantity}</td>
				<td>${item.u_peso}</td>
				<td>${item.cardname}</td>
				<td>${item.u_chofer}</td>
				<td>${item.doctime}</td>
				<td>${item.u_embarca}</td>
				<td>${item.comments}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>