<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Perfiles</title>
	<%@include file="authheader2.jsp"%>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Perfiles</span>
	</div>
	<br>
	<div align="center" id="tabla1" class = "container">
	 <div class="row">
	 <div class="col-6"><!-- mx-auto  para centrar en pantalla -->
	<div id="tablePag" class="container">
		<div class="row border border-dark">
			  <div class="col-sm">
			    ID
			  </div>
			  <div class="col-sm">
			    Perfil
			  </div>
		</div>
		<c:forEach var="item" items="${perfileslist}">
		<div class="row border border-dark">
			  <div class="col-sm">
			    ${item.id}
			  </div>
			  <div class="col-sm">
			    ${item.type}
			  </div>
		</div>
		</c:forEach>
	</div>
	<br>
	</div>
	<div class="col-6">
	<form:form method="POST" modelAttribute="userprofile" class="form-horizontal">
	<table>
		<thead>
			<tr>
				<td>
					<form:input type="hidden" path="id" id="id"/>
					<label for="type">Nombre Perfil</label>
				</td>
			</tr>
			<tr>
				<th>
				<form:input type="text" path="type" id="type" class="text-uppercase form-control input-sm"/>
					<div class="has-error">
						<form:errors path="type" class="help-inline"/>
					</div>
				<br>
				<input type="submit" value="Registrar" class="btn btn-primary btn-sm"/>
				</th>
			</tr>
		</thead>
	</table>
	</form:form>
	</div>
	</div>
	</div>
</body>
</html>