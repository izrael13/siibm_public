<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Desempeño comparativo</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscar()
{
	var meses = $('#SMeses').val();
	var anioant = $('#SAnioAnt').val();
	var anioact = $('#SAnioAct').val();
	var cteven = $('#SCteVen').val();
	
	if(anioant >= anioact)
		alert("El año actual debe ser mayor al año anterior");
	else
		if(meses.length == 0)
			alert("Seleccione meses a comparar");
		else
			window.location.replace('<c:url value="/reportes/ventas/desempenio_comparativo" />?meses='+meses+'&anioant='+anioant+'&anioact='+anioact+'&cteven='+cteven);
	
}
</script>
</head>
<body>
	<div align="center">
		<span class="badge badge-secondary">Desempeño comparativo</span>
	</div>
		<br>
	<div align="center" class="container">
    <div class="row small">
        <div class="col-md-1">Año ant</div>
        <div class="col-md-2">
        	<select id="SAnioAnt" class="form-control input-sm">
				<option value="2015" ${not empty selectedValueActAnt && selectedValueActAnt eq 2015 ? 'selected' : '' }>2015</option>
				<option value="2016" ${not empty selectedValueActAnt && selectedValueActAnt eq 2016 ? 'selected' : '' }>2016</option>
				<option value="2017" ${not empty selectedValueActAnt && selectedValueActAnt eq 2017 ? 'selected' : '' }>2017</option>
				<option value="2018" ${not empty selectedValueActAnt && selectedValueActAnt eq 2018 ? 'selected' : '' }>2018</option>
				<option value="2019" ${not empty selectedValueActAnt && selectedValueActAnt eq 2019 ? 'selected' : '' }>2019</option>
				<option value="2020" ${not empty selectedValueActAnt && selectedValueActAnt eq 2020 ? 'selected' : '' }>2020</option>
				<option value="2021" ${not empty selectedValueActAnt && selectedValueActAnt eq 2021 ? 'selected' : '' }>2021</option>
				<option value="2022" ${not empty selectedValueActAnt && selectedValueActAnt eq 2022 ? 'selected' : '' }>2022</option>
				<option value="2023" ${not empty selectedValueActAnt && selectedValueActAnt eq 2023 ? 'selected' : '' }>2023</option>
				<option value="2024" ${not empty selectedValueActAnt && selectedValueActAnt eq 2024 ? 'selected' : '' }>2024</option>
				<option value="2025" ${not empty selectedValueActAnt && selectedValueActAnt eq 2025 ? 'selected' : '' }>2025</option>
			</select>
        </div>
        <div class="col-md-1">Año actual</div>
        <div class="col-md-2">
        	<select id="SAnioAct" class="form-control input-sm">
				<option value="2015" ${not empty selectedValueAct && selectedValueAct eq 2015 ? 'selected' : '' }>2015</option>
				<option value="2016" ${not empty selectedValueAct && selectedValueAct eq 2016 ? 'selected' : '' }>2016</option>
				<option value="2017" ${not empty selectedValueAct && selectedValueAct eq 2017 ? 'selected' : '' }>2017</option>
				<option value="2018" ${not empty selectedValueAct && selectedValueAct eq 2018 ? 'selected' : '' }>2018</option>
				<option value="2019" ${not empty selectedValueAct && selectedValueAct eq 2019 ? 'selected' : '' }>2019</option>
				<option value="2020" ${not empty selectedValueAct && selectedValueAct eq 2020 ? 'selected' : '' }>2020</option>
				<option value="2021" ${not empty selectedValueAct && selectedValueAct eq 2021 ? 'selected' : '' }>2021</option>
				<option value="2022" ${not empty selectedValueAct && selectedValueAct eq 2022 ? 'selected' : '' }>2022</option>
				<option value="2023" ${not empty selectedValueAct && selectedValueAct eq 2023 ? 'selected' : '' }>2023</option>
				<option value="2024" ${not empty selectedValueAct && selectedValueAct eq 2024 ? 'selected' : '' }>2024</option>
				<option value="2025" ${not empty selectedValueAct && selectedValueAct eq 2025 ? 'selected' : '' }>2025</option>
			</select>
        </div>
        <div class="col-md-1">Meses</div>
        <div class="col-md-2">
        	<select id="SMeses" class="form-control input-sm" multiple>
				<option value="1">Enero</option>
				<option value="2">Febrero</option>
				<option value="3">Marzo</option>
				<option value="4">Abril</option>
				<option value="5">Mayo</option>
				<option value="6">Junio</option>
				<option value="7">Julio</option>
				<option value="8">Agosto</option>
				<option value="9">Septiembre</option>
				<option value="10">Octubre</option>
				<option value="11">Noviembre</option>
				<option value="12">Diciembre</option>
			</select>
        </div>
        <div class="col-md-2">
        	<select id="SCteVen" class="form-control input-sm">
				<option value="1" ${not empty selectedValueCteVen && selectedValueCteVen eq 1 ? 'selected' : '' }>Vendedor</option>
				<option value="2" ${not empty selectedValueCteVen && selectedValueCteVen eq 2 ? 'selected' : '' }>Cliente</option>
			</select>
        </div>
        <div class="col-md-1">
        	<a href="javascript:FBuscar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a>
        </div>
    </div>
    <br>
    <table class="table-hover table-bordered mx-auto small">
    	<tbody>
    	<c:forEach var="item" items="${Lista}" varStatus="status">
    		<tr>
    			<c:if test="${status.index == 0}">
    				<c:if test="${selectedValueCteVen == 1}">
    					<td class="font-weight-bold">Vendedor</td>
    					<td class="font-weight-bold">${(fn:substring(item[1],0,2)) - 10} ${(fn:substring(item[1],2,6))}</td>
    					<td class="font-weight-bold">${(fn:substring(item[2],0,2)) - 10} ${(fn:substring(item[2],2,6))}</td>
    					
    					<c:if test="${not empty item[3]}">
    						<td class="font-weight-bold">${(fn:substring(item[3],0,2)) - 10} ${(fn:substring(item[3],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[4]}">
    						<td class="font-weight-bold">${(fn:substring(item[4],0,2)) - 10} ${(fn:substring(item[4],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[5]}">
    						<td class="font-weight-bold">${(fn:substring(item[5],0,2)) - 10} ${(fn:substring(item[5],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[6]}">
    						<td class="font-weight-bold">${(fn:substring(item[6],0,2)) - 10} ${(fn:substring(item[6],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[7]}">
    						<td class="font-weight-bold">${(fn:substring(item[7],0,2)) - 10} ${(fn:substring(item[7],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[8]}">
    						<td class="font-weight-bold">${(fn:substring(item[8],0,2)) - 10} ${(fn:substring(item[8],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[9]}">
    						<td class="font-weight-bold">${(fn:substring(item[9],0,2)) - 10} ${(fn:substring(item[9],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[10]}">
    						<td class="font-weight-bold">${(fn:substring(item[10],0,2)) - 10} ${(fn:substring(item[10],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[11]}">
    						<td class="font-weight-bold">${(fn:substring(item[11],0,2)) - 10} ${(fn:substring(item[11],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[12]}">
    						<td class="font-weight-bold">${(fn:substring(item[12],0,2)) - 10} ${(fn:substring(item[12],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[13]}">
    						<td class="font-weight-bold">${(fn:substring(item[13],0,2)) - 10} ${(fn:substring(item[13],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[14]}">
    						<td class="font-weight-bold">${(fn:substring(item[14],0,2)) - 10} ${(fn:substring(item[14],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[15]}">
    						<td class="font-weight-bold">${(fn:substring(item[15],0,2)) - 10} ${(fn:substring(item[15],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[16]}">
    						<td class="font-weight-bold">${(fn:substring(item[16],0,2)) - 10} ${(fn:substring(item[16],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[17]}">
    						<td class="font-weight-bold">${(fn:substring(item[17],0,2)) - 10} ${(fn:substring(item[17],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[18]}">
    						<td class="font-weight-bold">${(fn:substring(item[18],0,2)) - 10} ${(fn:substring(item[18],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[19]}">
    						<td class="font-weight-bold">${(fn:substring(item[19],0,2)) - 10} ${(fn:substring(item[19],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[20]}">
    						<td class="font-weight-bold">${(fn:substring(item[20],0,2)) - 10} ${(fn:substring(item[20],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[21]}">
    						<td class="font-weight-bold">${(fn:substring(item[21],0,2)) - 10} ${(fn:substring(item[21],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[22]}">
    						<td class="font-weight-bold">${(fn:substring(item[22],0,2)) - 10} ${(fn:substring(item[22],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[23]}">
    						<td class="font-weight-bold">${(fn:substring(item[23],0,2)) - 10} ${(fn:substring(item[23],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[24]}">
    						<td class="font-weight-bold">${(fn:substring(item[24],0,2)) - 10} ${(fn:substring(item[24],2,6))}</td>
    					</c:if>
    				</c:if>
    				<c:if test="${selectedValueCteVen == 2}">
    					<td class="font-weight-bold">Cliente</td>
    					<td class="font-weight-bold">Vendedor</td>
    					<td class="font-weight-bold">${(fn:substring(item[2],0,2)) - 10} ${(fn:substring(item[2],2,6))}</td>
    					<td class="font-weight-bold">${(fn:substring(item[3],0,2)) - 10} ${(fn:substring(item[3],2,6))}</td>
    					
    					<c:if test="${not empty item[4]}">
    						<td class="font-weight-bold">${(fn:substring(item[4],0,2)) - 10} ${(fn:substring(item[4],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[5]}">
    						<td class="font-weight-bold">${(fn:substring(item[5],0,2)) - 10} ${(fn:substring(item[5],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[6]}">
    						<td class="font-weight-bold">${(fn:substring(item[6],0,2)) - 10} ${(fn:substring(item[6],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[7]}">
    						<td class="font-weight-bold">${(fn:substring(item[7],0,2)) - 10} ${(fn:substring(item[7],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[8]}">
    						<td class="font-weight-bold">${(fn:substring(item[8],0,2)) - 10} ${(fn:substring(item[8],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[9]}">
    						<td class="font-weight-bold">${(fn:substring(item[9],0,2)) - 10} ${(fn:substring(item[9],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[10]}">
    						<td class="font-weight-bold">${(fn:substring(item[10],0,2)) - 10} ${(fn:substring(item[10],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[11]}">
    						<td class="font-weight-bold">${(fn:substring(item[11],0,2)) - 10} ${(fn:substring(item[11],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[12]}">
    						<td class="font-weight-bold">${(fn:substring(item[12],0,2)) - 10} ${(fn:substring(item[12],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[13]}">
    						<td class="font-weight-bold">${(fn:substring(item[13],0,2)) - 10} ${(fn:substring(item[13],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[14]}">
    						<td class="font-weight-bold">${(fn:substring(item[14],0,2)) - 10} ${(fn:substring(item[14],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[15]}">
    						<td class="font-weight-bold">${(fn:substring(item[15],0,2)) - 10} ${(fn:substring(item[15],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[16]}">
    						<td class="font-weight-bold">${(fn:substring(item[16],0,2)) - 10} ${(fn:substring(item[16],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[17]}">
    						<td class="font-weight-bold">${(fn:substring(item[17],0,2)) - 10} ${(fn:substring(item[17],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[18]}">
    						<td class="font-weight-bold">${(fn:substring(item[18],0,2)) - 10} ${(fn:substring(item[18],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[19]}">
    						<td class="font-weight-bold">${(fn:substring(item[19],0,2)) - 10} ${(fn:substring(item[19],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[20]}">
    						<td class="font-weight-bold">${(fn:substring(item[20],0,2)) - 10} ${(fn:substring(item[20],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[21]}">
    						<td class="font-weight-bold">${(fn:substring(item[21],0,2)) - 10} ${(fn:substring(item[21],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[22]}">
    						<td class="font-weight-bold">${(fn:substring(item[22],0,2)) - 10} ${(fn:substring(item[22],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[23]}">
    						<td class="font-weight-bold">${(fn:substring(item[23],0,2)) - 10} ${(fn:substring(item[23],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[24]}">
    						<td class="font-weight-bold">${(fn:substring(item[24],0,2)) - 10} ${(fn:substring(item[24],2,6))}</td>
    					</c:if>
    					<c:if test="${not empty item[25]}">
    						<td class="font-weight-bold">${(fn:substring(item[25],0,2)) - 10} ${(fn:substring(item[25],2,6))}</td>
    					</c:if>
    				</c:if>
    			</c:if>
    			<c:if test="${status.index > 0}">
    				<c:if test="${selectedValueCteVen == 1}">
    					<td>${item[0] == 'zzzz' ? 'Total' : item[0]}</td>
    					<td>${item[1]}</td>
    					<td>${item[2]}</td>
    					<c:if test="${not empty item[3]}">
    						<td>${item[3]}</td>
    					</c:if>
    					<c:if test="${not empty item[4]}">
    						<td>${item[4]}</td>
    					</c:if>
    					<c:if test="${not empty item[5]}">
    						<td>${item[5]}</td>
    					</c:if>
    					<c:if test="${not empty item[6]}">
    						<td>${item[6]}</td>
    					</c:if>
    					<c:if test="${not empty item[7]}">
    						<td>${item[7]}</td>
    					</c:if>
    					<c:if test="${not empty item[8]}">
    						<td>${item[8]}</td>
    					</c:if>
    					<c:if test="${not empty item[9]}">
    						<td>${item[9]}</td>
    					</c:if>
    					<c:if test="${not empty item[10]}">
    						<td>${item[10]}</td>
    					</c:if>
    					<c:if test="${not empty item[11]}">
    						<td>${item[11]}</td>
    					</c:if>
    					<c:if test="${not empty item[12]}">
    						<td>${item[12]}</td>
    					</c:if>
    					<c:if test="${not empty item[13]}">
    						<td>${item[13]}</td>
    					</c:if>
    					<c:if test="${not empty item[14]}">
    						<td>${item[14]}</td>
    					</c:if>
    					<c:if test="${not empty item[15]}">
    						<td>${item[15]}</td>
    					</c:if>
    					<c:if test="${not empty item[16]}">
    						<td>${item[16]}</td>
    					</c:if>
    					<c:if test="${not empty item[17]}">
    						<td>${item[17]}</td>
    					</c:if>
    					<c:if test="${not empty item[18]}">
    						<td>${item[18]}</td>
    					</c:if>
    					<c:if test="${not empty item[19]}">
    						<td>${item[19]}</td>
    					</c:if>
    					<c:if test="${not empty item[20]}">
    						<td>${item[20]}</td>
    					</c:if>
    					<c:if test="${not empty item[21]}">
    						<td>${item[21]}</td>
    					</c:if>
    					<c:if test="${not empty item[22]}">
    						<td>${item[22]}</td>
    					</c:if>
    					<c:if test="${not empty item[23]}">
    						<td>${item[23]}</td>
    					</c:if>
    					<c:if test="${not empty item[24]}">
    						<td>${item[24]}</td>
    					</c:if>
    				</c:if>
    				<c:if test="${selectedValueCteVen == 2}">
    					<td>${item[0] == 'zzzz' ? 'Total' : item[0]}</td>
    					<td>${item[1] == 'zzzz' ? '' : item[1]}</td>
    					<td>${item[2]}</td>
    					<td>${item[3]}</td>
    					<c:if test="${not empty item[4]}">
    						<td>${item[4]}</td>
    					</c:if>
    					<c:if test="${not empty item[5]}">
    						<td>${item[5]}</td>
    					</c:if>
    					<c:if test="${not empty item[6]}">
    						<td>${item[6]}</td>
    					</c:if>
    					<c:if test="${not empty item[7]}">
    						<td>${item[7]}</td>
    					</c:if>
    					<c:if test="${not empty item[8]}">
    						<td>${item[8]}</td>
    					</c:if>
    					<c:if test="${not empty item[9]}">
    						<td>${item[9]}</td>
    					</c:if>
    					<c:if test="${not empty item[10]}">
    						<td>${item[10]}</td>
    					</c:if>
    					<c:if test="${not empty item[11]}">
    						<td>${item[11]}</td>
    					</c:if>
    					<c:if test="${not empty item[12]}">
    						<td>${item[12]}</td>
    					</c:if>
    					<c:if test="${not empty item[13]}">
    						<td>${item[13]}</td>
    					</c:if>
    					<c:if test="${not empty item[14]}">
    						<td>${item[14]}</td>
    					</c:if>
    					<c:if test="${not empty item[15]}">
    						<td>${item[15]}</td>
    					</c:if>
    					<c:if test="${not empty item[16]}">
    						<td>${item[16]}</td>
    					</c:if>
    					<c:if test="${not empty item[17]}">
    						<td>${item[17]}</td>
    					</c:if>
    					<c:if test="${not empty item[18]}">
    						<td>${item[18]}</td>
    					</c:if>
    					<c:if test="${not empty item[19]}">
    						<td>${item[19]}</td>
    					</c:if>
    					<c:if test="${not empty item[20]}">
    						<td>${item[20]}</td>
    					</c:if>
    					<c:if test="${not empty item[21]}">
    						<td>${item[21]}</td>
    					</c:if>
    					<c:if test="${not empty item[22]}">
    						<td>${item[22]}</td>
    					</c:if>
    					<c:if test="${not empty item[23]}">
    						<td>${item[23]}</td>
    					</c:if>
    					<c:if test="${not empty item[24]}">
    						<td>${item[24]}</td>
    					</c:if>
    					<c:if test="${not empty item[25]}">
    						<td>${item[25]}</td>
    					</c:if>
    				</c:if>
    			</c:if>
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>    
    </div>
	<br>
<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>