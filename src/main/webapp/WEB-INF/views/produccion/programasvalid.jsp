<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../appconfig/authheader2.jsp"%>
<meta charset="ISO-8859-1">
<title>Programs validados Con rollos</title>
</head>
<body>
<div align="center"><br>
	<h1>Programas Validados Con Rollos</h1>
</div>

<table style="width:100%">

  <tr>
  	<th>IDvalidado</th>
  	<th>Prog_Corru</th>
	<th>Numero Rollo Id</th>
	<th>Fecha</th>
    <th>Usuario</th>
     <th>papel id</th>
  </tr>
  
	<c:forEach items="${oS_ProgCorrugado}" var="i">
	
	<tr>     

			<td>${i.ID}</td>
			<td>${i.Programa}</td>
			<td>${i.Pedido}</td>
			<td>${i.Cliente}</td>
			<td>${i.Simbolo}</td>
	
			
	
	</tr>	
	</c:forEach>
		
</table>


<div align="center">
<%@include file="../appconfig/authfootter.jsp"%>
</div>
</body>
</html>