<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Comparativo</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscar()
{
	var idven = $("#SVendedores").val();
	var bancte = $("#CBanCte").prop("checked") == true ? 1 : 0; 
	var aniomes = $("#aniomes").val();
	var tabla = "";
	var totalpron = 0.0;
	var totaltkg = 0.0;
	$("#DLista").text("");
	$( "#mensajes" ).text("").removeClass();
	$.ajax({
		url: '<c:url value="/qlikview/ventas/comparativobuscar"/>?idven='+idven+'&bancte='+bancte+'&anio='+aniomes.substring(0,4)+'&mes='+aniomes.substring(4,aniomes.length),
		beforeSend: function(xhr) {
							  $("#imgload").show();
					        },	
        success : function(data) {
        		if (data.search(/Login page/i) != -1) {
        			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }
        		tabla = tabla + '<table id="tablePag" class="table-hover table-bordered mx-auto">';
        		tabla = tabla + '<thead> <tr> ';
        		tabla = tabla + '<th>Cliente</th>';
        		tabla = tabla + '<th>Vendedor</th>';
        		tabla = tabla + '<th>Pronóstico</th>';
        		tabla = tabla + '<th>TKG</th>';
        		tabla = tabla + '<th>Diferencia</th>';
        		tabla = tabla + '<th>Porcentaje</th>';
        		tabla = tabla + '<th>Fecha</th>';
        		tabla = tabla + '</tr> </thead>';
        		tabla = tabla + '<tbody> ';
        		//alert(data);
	        	$.each(jQuery.parseJSON(data),function(index, value){
	        		//alert(value.id);
	        		tabla = tabla + '<tr>';
	        		tabla = tabla + '<td align="left">'+value.cliente+'</td>';
	        		tabla = tabla + '<td align="left">'+value.vendedor+'</td>';
	        		tabla = tabla + '<td align="right">'+value.pron.toString().match(/^-?\d+(?:\.\d{0,2})?/)[0]+'</td>';
	        		tabla = tabla + '<td align="right">'+value.tkg.toString().match(/^-?\d+(?:\.\d{0,2})?/)[0]+'</td>';
	        		tabla = tabla + '<td align="right">'+value.diferencia.toString().match(/^-?\d+(?:\.\d{0,2})?/)[0]+'</td>';
	        		tabla = tabla + '<td align="right">'+value.porc.toString().match(/^-?\d+(?:\.\d{0,2})?/)[0]+'</td>';
	        		tabla = tabla + '<td align="left">'+value.fecha+'</td>';
	        		tabla = tabla + '</tr>';
	        		totalpron = totalpron + value.pron;
	        		totaltkg = totaltkg + value.tkg;
	        	});
	        	tabla = tabla + '<tr>';
	        	tabla = tabla + '<td colspan="2" align="right">Total: </td>';
	        	tabla = tabla + '<td align="right">'+totalpron.toString().match(/^-?\d+(?:\.\d{0,2})?/)[0]+'</td>';
	        	tabla = tabla + '<td align="right">'+totaltkg.toString().match(/^-?\d+(?:\.\d{0,2})?/)[0]+'</td>';
	        	tabla = tabla + '</tr>';
	        	tabla = tabla + '</tbody> ';
	        	tabla = tabla + '</table>';
	        	document.getElementById("DLista").innerHTML = tabla;
	        	$( "#imgload").hide();
	        	$( "#mensajes" ).text("").removeClass();
        },
        error: function(xhr, status, error) {
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  $( "#imgload").hide();
		  }
	 });
}
</script>
</head>
<body>
<br>
	<div align="center">
		<span class="badge badge-secondary">Comparativo</span>
	</div>
	<br>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
		<div class="row small">
			<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
				<div class="row border border-right">
					<div class="col-sm-1">Vendedor</div>
					<div class="col-sm-4">
						<select id="SVendedores" class="border border-primary">
							<option value="0">Seleccione un vendedor</option>
							<c:forEach var="ven" items="${listavend}">
									<option value="${ven.clavevendedor}" ${ven.clavevendedor == selectedValueSlpCode ? 'selected' : ''}
										<sec:authorize access="!hasRole('ADMIN') or !hasRole('VENTAS')">
											'disabled'
										</sec:authorize>
									><c:out value="${ven.nombre}"/></option>
								
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-1">Fecha</div>
					<div class="col-sm-2">
						<form:select id="aniomes" path="mesesanio" class="border border-primary">
						<c:forEach var="i" items="${mesesanio}">
							<c:set var="anio_mes_">${i.anio}${i.mes}</c:set>
							<c:choose>
								<c:when
									test="${not empty selectedValue && selectedValue eq anio_mes_ }">
									<option value="${i.anio}${i.mes}" selected>${i.anio} - ${i.mes}</option>
								</c:when>
								<c:otherwise>
									<option value="${i.anio}${i.mes}">${i.anio} - ${i.mes} </option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
					 </div>
					 <div class="col-sm-1"><button type="button" class="btn btn-outline-primary btn-sm" onClick="FBuscar()">
						<i class="fa fa-search"></i>
							Buscar
						</button>
					</div>
					<div class="col-sm-1">
						Por Cliente:  <input type="checkbox" id = "CBanCte"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="DLista">
	</div>
	<div align="center">
	 	<span id="imgload" style='display: none;'><img width="20px" height="20px" src='<c:url value="/static/img/sun_watch.gif"/>' /></span>
	 	<div id = "mensajes" class = "${!empty mensajes ? 'alert alert-success' : ''}">${mensajes}</div>
	</div>
</body>
</html>