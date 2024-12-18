<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Desempeño anual vendedor</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscar()
{
	window.location.replace('<c:url value="/reportes/vendedores/desempenioanualvend" />?anio='+document.getElementById('SAnio').value);
}
function Excel()
{
	window.location.replace('<c:url value="/reportes/vendedores/desempenioanualvendexcel" />?anio='+document.getElementById('SAnio').value);
}

</script>
</head>
<body>
<br>
	<div align="center">
		<span class="badge badge-secondary">Desempeño anual por vendedor</span>
	</div>
		<br>
	<div align="center" class="container">
    <div class="row">
    	<div class="col-md-2"></div>
        <div class="col-md-2">Año</div>
        <div class="col-md-2">
        	<select id="SAnio" class="form-control input-sm" onchange="FBuscar()">
				<option value="2015" ${not empty selectedValue && selectedValue eq 2015 ? 'selected' : '' }>2015</option>
				<option value="2016" ${not empty selectedValue && selectedValue eq 2016 ? 'selected' : '' }>2016</option>
				<option value="2017" ${not empty selectedValue && selectedValue eq 2017 ? 'selected' : '' }>2017</option>
				<option value="2018" ${not empty selectedValue && selectedValue eq 2018 ? 'selected' : '' }>2018</option>
				<option value="2019" ${not empty selectedValue && selectedValue eq 2019 ? 'selected' : '' }>2019</option>
				<option value="2020" ${not empty selectedValue && selectedValue eq 2020 ? 'selected' : '' }>2020</option>
				<option value="2021" ${not empty selectedValue && selectedValue eq 2021 ? 'selected' : '' }>2021</option>
				<option value="2022" ${not empty selectedValue && selectedValue eq 2022 ? 'selected' : '' }>2022</option>
				<option value="2023" ${not empty selectedValue && selectedValue eq 2023 ? 'selected' : '' }>2023</option>
				<option value="2024" ${not empty selectedValue && selectedValue eq 2024 ? 'selected' : '' }>2024</option>
				<option value="2025" ${not empty selectedValue && selectedValue eq 2025 ? 'selected' : '' }>2025</option>
			</select>
        </div>
        <div class="col-md-2"><button type="button" class="btn btn-outline-primary" onClick="Excel()">
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
				<th>Vendedor</th>
				<th>Año 1</th>
				<th>Porcentaje1</th>
				<th>Año 2</th>
				<th>Porcentaje2</th>
				<th>Año 3</th>
				<th>Porcentaje3</th>
				<th>Año 4</th>
				<th>Porcentaje4</th>
				<th>Año 5</th>
				<th>Porcentaje5</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${lista}">
			<tr class="${item.id == 1 ? 'alert alert-info' : '' }" >
				<td align="left">${item.slpname == 'zzTOTAL' ? fn:substring(item.slpname, 2, 7) : item.slpname} </td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.anio1}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.porcen1}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.anio2}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.porcen2}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.anio3}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.porcen3}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.anio4}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.porcen4}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${item.anio5}" /></td>
				<td align="right"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${item.porcen5}" /></td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>