<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Desempeño mensual por cliente</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscar()
{
	window.location.replace('<c:url value="/reportes/ventas/desempeniomesxcte" />?anio='+document.getElementById('SAnio').value+'&cardcode='+document.getElementById('SClientes').value+'&slpcode='+document.getElementById('SVendedores').value);
}
function Excel()
{
	window.location.replace('<c:url value="/reportes/ventas/desempeniomesxcteexcel" />?anio='+document.getElementById('SAnio').value+'&cardcode='+document.getElementById('SClientes').value+'&slpcode='+document.getElementById('SVendedores').value);
}
</script>
</head>
<body>
<br>
	<div align="center">
		<span class="badge badge-secondary">Desempeño mensual por cliente</span>
	</div>
		<br>
	<div align="center" class="container-fluid">
    <div class="row">
        <div class="col-md-1">
        	<select id="SAnio" class="border border-primary small">
				<option value="2015" ${not empty selectedValueAnio && selectedValueAnio eq 2015 ? 'selected' : '' }>2015</option>
				<option value="2016" ${not empty selectedValueAnio && selectedValueAnio eq 2016 ? 'selected' : '' }>2016</option>
				<option value="2017" ${not empty selectedValueAnio && selectedValueAnio eq 2017 ? 'selected' : '' }>2017</option>
				<option value="2018" ${not empty selectedValueAnio && selectedValueAnio eq 2018 ? 'selected' : '' }>2018</option>
				<option value="2019" ${not empty selectedValueAnio && selectedValueAnio eq 2019 ? 'selected' : '' }>2019</option>
				<option value="2020" ${not empty selectedValueAnio && selectedValueAnio eq 2020 ? 'selected' : '' }>2020</option>
				<option value="2021" ${not empty selectedValueAnio && selectedValueAnio eq 2021 ? 'selected' : '' }>2021</option>
				<option value="2022" ${not empty selectedValueAnio && selectedValueAnio eq 2022 ? 'selected' : '' }>2022</option>
				<option value="2023" ${not empty selectedValueAnio && selectedValueAnio eq 2023 ? 'selected' : '' }>2023</option>
				<option value="2024" ${not empty selectedValueAnio && selectedValueAnio eq 2024 ? 'selected' : '' }>2024</option>
				<option value="2025" ${not empty selectedValueAnio && selectedValueAnio eq 2025 ? 'selected' : '' }>2025</option>
			</select>
        </div>
        <div class="col-md-6">
        
        <select id="SClientes" class="border border-primary small">
			<option value="">Seleccione un cliente</option>
			<c:forEach var="cte" items="${listactes}">
				<option value="${cte.cardcode}" ${cte.cardcode == selectedValueCardCode ? 'selected' : ''}><c:out value="${cte.cardname}"/></option>
			</c:forEach>
		</select>
        
		</div>
        <div class="col-md-3">
			<select id="SVendedores" class="border border-primary small">
				<option value="0">Seleccione un vendedor</option>
				<c:forEach var="ven" items="${listavend}">
					<option value="${ven.clavevendedor}" ${ven.clavevendedor == selectedValueSlpCode ? 'selected' : ''}><c:out value="${ven.nombre}"/></option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-1"><button type="button" class="btn btn-outline-primary btn-sm" onClick="FBuscar()">
				<i class="fa fa-search"></i>
				Buscar
				</button>
		</div>
        <div class="col-md-1"><button type="button" class="btn btn-outline-primary btn-sm" onClick="Excel()">
				<i class="fa fa-file-excel-o"></i>
				Excel
				</button>
		</div>
    </div>
    </div>
	<br>
	<div align="center" id="tabla1" class = "table-responsive-xl">
	<table id="tablePag" class="table-hover table-bordered mx-auto small"><!-- mx-auto  para centrar en pantalla -->
		<thead>
			<tr align="center">
				<th>Cliente</th>
				<th>Vendedor</th>
				<th>Enero</th>
				<th>Febrero</th>
				<th>Marzo</th>
				<th>Abril</th>
				<th>Mayo</th>
				<th>Junio</th>
				<th>Julio</th>
				<th>Agosto</th>
				<th>Septiembre</th>
				<th>Octubre</th>				
				<th>Noviembre</th>
				<th>Diciembre</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<tr class="${item.id == 1 ? 'alert alert-info' : '' }" >
				<td align="left">${item.cardname == 'zzTOTAL' ? fn:substring(item.cardname, 2, 7) : item.cardname} </td>
				<td align="left">${item.slpname} </td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.enero}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.febrero}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.marzo}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.abril}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.mayo}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.junio}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.julio}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.agosto}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.septiembre}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.octubre}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.noviembre}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.diciembre}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.totaltenanio}" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>