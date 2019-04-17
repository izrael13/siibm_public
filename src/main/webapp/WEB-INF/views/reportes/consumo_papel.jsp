<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- < % @ taglib uri="http://www.springframework.org/tags" prefix="spring"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function FBuscar()
{
	//alert('/buscarSem?aniosem='+document.getElementById("aniosem").value);
	window.location.replace('<c:url value="/reportes/papel/buscarSem" />?aniosem='+document.getElementById("aniosem").value);
	//document.location = '/buscarSem?aniosem='+document.getElementById("aniosem").value;
}
function FExcel()
{
	/*$("#Mensaje").html("Descargando . . .")
	 $.ajax({url: '<c:url value="/Excel" />?aniosem='+document.getElementById("aniosem").value, 
			success: function(result)
			{
	        	$("#Mensaje").html(result);
	    	}
	 }); */
	window.location.replace('<c:url value="/reportes/papel/Excel" />?aniosem='+document.getElementById("aniosem").value);
}
</script>
<title>Consumo de papel - Acumulado por semana</title>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
<body>
	<div align="center">
		<span class="badge badge-secondary">Consumo de papel - Acumulado por semana</span>
	</div>
	<!-- <spring:url value="/Excel/?type=xls" var="xlsURL" />
	 <spring:url value="/Excel/?type=pdf" var="pdfURL" />
	 <a href="${xlsURL }">Download Excel</a>
	 <a href="${pdfURL }">Download PDF</a> -->
	<br>
	<div align="center" class="container">
	<div class="row">
    	<div class="col-md-2"></div>
        <div class="col-md-2">Año - Semana</div>
        <div class="col-md-2">
        	<form:select id="aniosem" path="semanas_anio" class="form-control input-sm" onchange="FBuscar()">
				<c:forEach var="i" items="${semanas_anio}">
						<c:choose>
							<c:when
								test="${not empty selectedValue && selectedValue eq i.key}">
								<option value="${i.key}" selected>${i.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${i.key}">${i.value}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
        </div>
        <div class="col-md-2">
        	<button type="button" class="btn btn-outline-primary" Onclick="FExcel()">
					<i class="fa fa-file-excel-o"></i>
					Descargar Excel
				</button>
        </div>
        <div class="col-md-2">
        	<button type="button" class="btn btn-outline-primary" onClick="Imprimir('tabla_ult_sem')">
					<i class="fa fa-print"></i>
					Imprimir
				</button>
        </div>
    </div>
    </div>
	<br>
	<div id="tabla_ult_sem" class = "table table-responsive-xl">
	<table class="table-hover table-bordered text-center small">
		<thead>
		<tr>
			<td>Año</td>
			<td>Semana</td>
			<td>Ancho</td>
			<td>M90U</td>
			<td>M90</td>
			<td>M195U</td>
			<td>M190E</td>
			<td>M170RMSGR</td>
			<td>M170MSGR</td>
			<td>M155E</td>
			<td>M150U</td>
			<td>M150RMSGR</td>
			<td>M150MSGR</td>
			<td>M130MSGR</td>
			<td>M110U</td>
			<td>M110P</td>
			<td>M110</td>
			<td>LB38A</td>
			<td>LB185C</td>
			<td>LB150C</td>
			<td>LB130C</td>
			<td>L56A</td>
			<td>L42A</td>
			<td>L35A</td>
			<td>L33A</td>
			<td>L305A</td>
			<td>L275T</td>
			<td>L275A</td>
			<td>L26A</td>
			<td>L260RLSGR</td>
			<td>L260NLSGN</td>
			<td>L200T</td>
			<td>L200RLSGR</td>
			<td>L200A</td>
			<td>L170U</td>
			<td>L170C</td>
			<td>L150LSGS</td>
			<td>L150LNM</td>
			<td>L140T</td>
			<td>L140C</td>
			<td>L130LSGS</td>
			<td>L125T</td>
			<td>L125C</td>
			<td>L120LSGS</td>
			<td>Total Semana</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${reporte}">
			<c:set var="i" value="${i = i + 1}"/>
			<c:if test = "${(anchoant > 0 && anchoant != item.ancho)}">
					<tr>
						<td colspan="3">Total</td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m90ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m90t}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m195ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m190et}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m170rmsgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m170msgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m155et}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150rmsgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150msgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m130msgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110pt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110t}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb38at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb185ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb150ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb130ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l56at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l42at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l35at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l33at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l305at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l275tt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l275at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l26at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l260rlsgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l260nlsgnt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200tt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200rlsgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l170ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l170ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l150lsgst}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l150lnmt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l140tt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l140ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l130lsgst}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l125tt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l125ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l120lsgst}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${totalsem}" /></td>
					</tr>
					<tr><td colspan="3">Promedio</td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m90ut / numsem}" /><c:set var="m90ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m90t / numsem}" /><c:set var="m90t" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m195ut / numsem}" /><c:set var="m195ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m190et / numsem}" /><c:set var="m190et" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m170rmsgrt / numsem}" /><c:set var="m170rmsgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m170msgrt / numsem}" /><c:set var="m170msgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m155et / numsem}" /><c:set var="m155et" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150ut / numsem}" /><c:set var="m150ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150rmsgrt / numsem}" /><c:set var="m150rmsgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150msgrt / numsem}" /><c:set var="m150msgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m130msgrt / numsem}" /><c:set var="m130msgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110ut / numsem}" /><c:set var="m110ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110pt / numsem}" /><c:set var="m110pt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110t / numsem}" /><c:set var="m110t" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb38at / numsem}" /><c:set var="lb38at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb185ct / numsem}" /><c:set var="lb185ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb150ct / numsem}" /><c:set var="lb150ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb130ct / numsem}" /><c:set var="lb130ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l56at / numsem}" /><c:set var="l56at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l42at / numsem}" /><c:set var="l42at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l35at / numsem}" /><c:set var="l35at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l33at / numsem}" /><c:set var="l33at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l305at / numsem}" /><c:set var="l305at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l275tt / numsem}" /><c:set var="l275tt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l275at / numsem}" /><c:set var="l275at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l26at / numsem}" /><c:set var="l26at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l260rlsgrt / numsem}" /><c:set var="l260rlsgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l260nlsgnt / numsem}" /><c:set var="l260nlsgnt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200tt / numsem}" /><c:set var="l200tt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200rlsgrt / numsem}" /><c:set var="l200rlsgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200at / numsem}" /><c:set var="l200at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l170ut / numsem}" /><c:set var="l170ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l170ct / numsem}" /><c:set var="l170ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l150lsgst / numsem}" /><c:set var="l150lsgst" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l150lnmt / numsem}" /><c:set var="l150lnmt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l140tt / numsem}" /><c:set var="l140tt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l140ct / numsem}" /><c:set var="l140ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l130lsgst / numsem}" /><c:set var="l130lsgst" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l125tt / numsem}" /><c:set var="l125tt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l125ct / numsem}" /><c:set var="l125ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l120lsgst / numsem}" /><c:set var="l120lsgst" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${totalsem / numsem}" /><c:set var="totalsem" value="0"/></td>
					</tr>
            		<tr><td colspan="30"><br></td></tr>
            		<tr>
						<td>Año</td>
						<td>Semana</td>
						<td>Ancho</td>
						<td>M90U</td>
						<td>M90</td>
						<td>M195U</td>
						<td>M190E</td>
						<td>M170RMSGR</td>
						<td>M170MSGR</td>
						<td>M155E</td>
						<td>M150U</td>
						<td>M150RMSGR</td>
						<td>M150MSGR</td>
						<td>M130MSGR</td>
						<td>M110U</td>
						<td>M110P</td>
						<td>M110</td>
						<td>LB38A</td>
						<td>LB185C</td>
						<td>LB150C</td>
						<td>LB130C</td>
						<td>L56A</td>
						<td>L42A</td>
						<td>L35A</td>
						<td>L33A</td>
						<td>L305A</td>
						<td>L275T</td>
						<td>L275A</td>
						<td>L26A</td>
						<td>L260RLSGR</td>
						<td>L260NLSGN</td>
						<td>L200T</td>
						<td>L200RLSGR</td>
						<td>L200A</td>
						<td>L170U</td>
						<td>L170C</td>
						<td>L150LSGS</td>
						<td>L150LNM</td>
						<td>L140T</td>
						<td>L140C</td>
						<td>L130LSGS</td>
						<td>L125T</td>
						<td>L125C</td>
						<td>L120LSGS</td>
						<td>Total Semana</td>
					</tr>
            <c:set var="numsem" value="0"/>
       		</c:if>
			<tr>
				<td>${item.anio}</td>
				<td>${item.semana}</td>
				<td>${item.ancho}</td>
				<td>${item.m90u}</td>
				<td>${item.m90}</td>
				<td>${item.m195u}</td>
				<td>${item.m190e}</td>
				<td>${item.m170rmsgr}</td>
				<td>${item.m170msgr}</td>
				<td>${item.m155e}</td>
				<td>${item.m150u}</td>
				<td>${item.m150rmsgr}</td>
				<td>${item.m150msgr}</td>
				<td>${item.m130msgr}</td>
				<td>${item.m110u}</td>
				<td>${item.m110p}</td>
				<td>${item.m110}</td>
				<td>${item.lb38a}</td>
				<td>${item.lb185c}</td>
				<td>${item.lb150c}</td>
				<td>${item.lb130c}</td>
				<td>${item.l56a}</td>
				<td>${item.l42a}</td>
				<td>${item.l35a}</td>
				<td>${item.l33a}</td>
				<td>${item.l305a}</td>
				<td>${item.l275t}</td>
				<td>${item.l275a}</td>
				<td>${item.l26a}</td>
				<td>${item.l260rlsgr}</td>
				<td>${item.l260nlsgn}</td>
				<td>${item.l200t}</td>
				<td>${item.l200rlsgr}</td>
				<td>${item.l200a}</td>
				<td>${item.l170u}</td>
				<td>${item.l170c}</td>
				<td>${item.l150lsgs}</td>
				<td>${item.l150lnm}</td>
				<td>${item.l140t}</td>
				<td>${item.l140c}</td>
				<td>${item.l130lsgs}</td>
				<td>${item.l125t}</td>
				<td>${item.l125c}</td>
				<td>${item.l120lsgs}</td>
				<td>
					<c:set var="totalpapel" value="${item.m90u+item.m90+item.m195u+item.m190e+item.m170rmsgr+item.m170msgr+item.m155e+item.m150u+item.m150rmsgr+item.m150msgr+item.m130msgr+item.m110u+item.m110p+
item.m110+item.lb38a+item.lb185c+item.lb150c+item.lb130c+item.l56a+item.l42a+item.l35a+item.l33a+item.l305a+item.l275t+item.l275a+item.l26a+item.l260rlsgr+item.l260nlsgn+item.l200t+item.l200rlsgr+item.l200a+item.l170u+item.l170c+item.l150lsgs+item.l150lnm+item.l140t+item.l140c+item.l130lsgs+item.l125t+item.l125c+
item.l120lsgs}"/>
					<c:set var="totalsem" value="${totalsem = totalsem + totalpapel}"/>
					<fmt:formatNumber type = "number" groupingUsed = "false" value = "${totalpapel}" />
				</td>
				<c:set var="m90ut" value="${m90ut=m90ut+ item.m90u}"/>
				<c:set var="m90t" value="${m90t=m90t+ item.m90}"/>
				<c:set var="m195ut" value="${m195ut=m195ut+ item.m195u}"/>
				<c:set var="m190et" value="${m190et=m190et+ item.m190e}"/>
				<c:set var="m170rmsgrt" value="${m170rmsgrt=m170rmsgrt+ item.m170rmsgr}"/>
				<c:set var="m170msgrt" value="${m170msgrt=m170msgrt+ item.m170msgr}"/>
				<c:set var="m155et" value="${m155et=m155et+ item.m155e}"/>
				<c:set var="m150ut" value="${m150ut=m150ut+ item.m150u}"/>
				<c:set var="m150rmsgrt" value="${m150rmsgrt=m150rmsgrt+ item.m150rmsgr}"/>
				<c:set var="m150msgrt" value="${m150msgrt=m150msgrt+ item.m150msgr}"/>
				<c:set var="m130msgrt" value="${m130msgrt=m130msgrt+ item.m130msgr}"/>
				<c:set var="m110ut" value="${m110ut=m110ut+ item.m110u}"/>
				<c:set var="m110pt" value="${m110pt=m110pt+ item.m110p}"/>
				<c:set var="m110t" value="${m110t=m110t+ item.m110}"/>
				<c:set var="lb38at" value="${lb38at=lb38at+ item.lb38a}"/>
				<c:set var="lb185ct" value="${lb185ct=lb185ct+ item.lb185c}"/>
				<c:set var="lb150ct" value="${lb150ct=lb150ct+ item.lb150c}"/>
				<c:set var="lb130ct" value="${lb130ct=lb130ct+ item.lb130c}"/>
				<c:set var="l56at" value="${l56at=l56at+ item.l56a}"/>
				<c:set var="l42at" value="${l42at=l42at+ item.l42a}"/>
				<c:set var="l35at" value="${l35at=l35at+ item.l35a}"/>
				<c:set var="l33at" value="${l33at=l33at+ item.l33a}"/>
				<c:set var="l305at" value="${l305at=l305at+ item.l305a}"/>
				<c:set var="l275tt" value="${l275tt=l275tt+ item.l275t}"/>
				<c:set var="l275at" value="${l275at=l275at+ item.l275a}"/>
				<c:set var="l26at" value="${l26at=l26at+ item.l26a}"/>
				<c:set var="l260rlsgrt" value="${l260rlsgrt=l260rlsgrt+ item.l260rlsgr}"/>
				<c:set var="l260nlsgnt" value="${l260nlsgnt=l260nlsgnt+ item.l260nlsgn}"/>
				<c:set var="l200tt" value="${l200tt=l200tt+ item.l200t}"/>
				<c:set var="l200rlsgrt" value="${l200rlsgrt=l200rlsgrt+ item.l200rlsgr}"/>
				<c:set var="l200at" value="${l200at=l200at+ item.l200a}"/>
				<c:set var="l170ut" value="${l170ut=l170ut+ item.l170u}"/>
				<c:set var="l170ct" value="${l170ct=l170ct+ item.l170c}"/>
				<c:set var="l150lsgst" value="${l150lsgst=l150lsgst+ item.l150lsgs}"/>
				<c:set var="l150lnmt" value="${l150lnmt=l150lnmt+ item.l150lnm}"/>
				<c:set var="l140tt" value="${l140tt=l140tt+ item.l140t}"/>
				<c:set var="l140ct" value="${l140ct=l140ct+ item.l140c}"/>
				<c:set var="l130lsgst" value="${l130lsgst=l130lsgst+ item.l130lsgs}"/>
				<c:set var="l125tt" value="${l125tt=l125tt+ item.l125t}"/>
				<c:set var="l125ct" value="${l125ct=l125ct+ item.l125c}"/>
				<c:set var="l120lsgst" value="${l120lsgst=l120lsgst+ item.l120lsgs}"/>
			 <c:set var="anchoant" value="${item.ancho}"/>
			 <c:set var="numsem" value="${numsem = numsem + 1}"/>
			 
			 			<c:if test = "${i == fn:length(reporte)}">
					<tr>
						<td colspan="3">Total</td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m90ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m90t}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m195ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m190et}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m170rmsgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m170msgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m155et}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150rmsgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150msgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m130msgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110pt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110t}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb38at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb185ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb150ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb130ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l56at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l42at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l35at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l33at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l305at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l275tt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l275at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l26at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l260rlsgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l260nlsgnt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200tt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200rlsgrt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200at}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l170ut}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l170ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l150lsgst}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l150lnmt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l140tt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l140ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l130lsgst}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l125tt}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l125ct}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l120lsgst}" /></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${totalsem}" /></td>
					</tr>
					<tr><td colspan="3">Promedio</td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m90ut / numsem}" /><c:set var="m90ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m90t / numsem}" /><c:set var="m90t" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m195ut / numsem}" /><c:set var="m195ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m190et / numsem}" /><c:set var="m190et" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m170rmsgrt / numsem}" /><c:set var="m170rmsgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m170msgrt / numsem}" /><c:set var="m170msgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m155et / numsem}" /><c:set var="m155et" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150ut / numsem}" /><c:set var="m150ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150rmsgrt / numsem}" /><c:set var="m150rmsgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m150msgrt / numsem}" /><c:set var="m150msgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m130msgrt / numsem}" /><c:set var="m130msgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110ut / numsem}" /><c:set var="m110ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110pt / numsem}" /><c:set var="m110pt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${m110t / numsem}" /><c:set var="m110t" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb38at / numsem}" /><c:set var="lb38at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb185ct / numsem}" /><c:set var="lb185ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb150ct / numsem}" /><c:set var="lb150ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${lb130ct / numsem}" /><c:set var="lb130ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l56at / numsem}" /><c:set var="l56at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l42at / numsem}" /><c:set var="l42at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l35at / numsem}" /><c:set var="l35at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l33at / numsem}" /><c:set var="l33at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l305at / numsem}" /><c:set var="l305at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l275tt / numsem}" /><c:set var="l275tt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l275at / numsem}" /><c:set var="l275at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l26at / numsem}" /><c:set var="l26at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l260rlsgrt / numsem}" /><c:set var="l260rlsgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l260nlsgnt / numsem}" /><c:set var="l260nlsgnt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200tt / numsem}" /><c:set var="l200tt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200rlsgrt / numsem}" /><c:set var="l200rlsgrt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l200at / numsem}" /><c:set var="l200at" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l170ut / numsem}" /><c:set var="l170ut" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l170ct / numsem}" /><c:set var="l170ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l150lsgst / numsem}" /><c:set var="l150lsgst" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l150lnmt / numsem}" /><c:set var="l150lnmt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l140tt / numsem}" /><c:set var="l140tt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l140ct / numsem}" /><c:set var="l140ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l130lsgst / numsem}" /><c:set var="l130lsgst" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l125tt / numsem}" /><c:set var="l125tt" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l125ct / numsem}" /><c:set var="l125ct" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${l120lsgst / numsem}" /><c:set var="l120lsgst" value="0"/></td>
						<td><fmt:formatNumber type = "number" groupingUsed = "false" value = "${totalsem / numsem}" /><c:set var="totalsem" value="0"/></td>
					</tr>
            		<tr><td colspan="30"><br></td></tr>
            		<c:set var="numsem" value="0"/>
       		</c:if>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
</body>
</html>