<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Permisos a perfiles</title>
	<%@include file="authheader2.jsp"%>
</head>
<body>
<br>
	<div align="center">
		<span class="badge badge-secondary">Permisos a perfiles</span>
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
			    Link
			  </div>
			  <div class="col-sm">
			    Borrar
			  </div>
		</div>
		<c:forEach var="item" items="${perfilesmatcherslist}">
		<div class="row border border-dark">
			  <div class="col-sm">
			    <c:forEach var="pli" items="${perfileslist}">
					<c:if test="${item.ID_PROFILE == pli.id}">
						${pli.type}
					</c:if>
				</c:forEach>
			  </div>
			  <div class="col-sm">
			    ${item.MATCHER}
			  </div>
			  <div class="col-sm">
			    <a href="<c:url value='/permisos_perfiles/delete'/>?ID=${item.ID}" class="fa fa-window-close-o"></a>
			  </div>
		</div>
		</c:forEach>
	</div>
	<br>
	</div>
	<div class="col-6">
	<form:form method="POST" modelAttribute="profilematchers" class="form-horizontal" autocomplete="off">
		<table>
		<thead>
			<tr>
				<td>
					<form:input type="hidden" path="ID" id="ID"/>
					<label for="ID_PROFILE">Nombre Perfil</label>
				</td>
			</tr>
			<tr>
				<th>
				<form:select id ="algo" path="ID_PROFILE" items="${perfileslist}" multiple="false" itemValue="id" itemLabel="type" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="ID_PROFILE" class="help-inline"/>
					</div>
				</th>
			</tr>
			<tr>
				<td>Link</td>
			</tr>
			<tr>
				<th>
					<form:input type="text" path="MATCHER" id="MATCHER" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="MATCHER" class="help-inline"/>
					</div>
				</th>
			</tr>
		</thead>
	</table>
	<br>
	<input type="submit" value="Registrar" class="btn btn-primary btn-sm"/>
	</form:form>
	</div>
	</div>
	</div>
	
</body>
</html>