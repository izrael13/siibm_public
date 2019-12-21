<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="authheader2.jsp"%>
<title>Enlaces</title>
<script type="text/javascript">
function FLimpar()
{
	window.location.replace('<c:url value="/permisos_perfiles/enlaces"/>');
}
function FBuscar(id)
{
	window.location.replace('<c:url value="/permisos_perfiles/enlaces"/>?id='+id);
}
function FEliminar(id)
{
	var r = confirm("¿Desea eliminar este Enlace: ? "+id);
	if (r == true) 
	{	 	
		var data = new FormData();
		data.append('id', id);
		
		$("#mensajes" ).text("Eliminando enlace.");
		$("#mensajes").removeClass().addClass("alert alert-info");
		
		$.ajax({
		    url: '<c:url value="/permisos_perfiles/enlacesdel"/>',
		    data: data,
		    type : 'POST',
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false,
	        before: function() {
	  	    },
		    success: function(r) {
		    	
		    	if (r.search(/Login page/i) != -1) {
        			window.location.replace('<c:url value="/login?expired"/>');
				    return true;
				  }		    	
		    	$("#mensajes" ).text("Enlace eliminado OK"+ r).removeClass().addClass("alert alert-success");
		    	window.location.replace('<c:url value="/permisos_perfiles/enlaces"/>');
		    },
		    error: function(xhr, status, error) {
		    	var err = xhr.responseText;		    	
		    	$("#mensajes" ).text("Error: "+err.Message+" - "+error).removeClass().addClass("alert alert-danger");
		    }
		  }); 
	}
}
</script>
</head>
<body>
<c:if test="${error != null}">
	<div class="alert alert-warning">
		<p>${error}</p>
	</div>
</c:if>
<c:if test="${mensaje != null}">
	<div id="mensajes" class="alert alert-success">
		<p>${mensaje}</p>
	</div>
</c:if>
<div class="container">
	<div class="badge badge-primary col-12">Enlaces</div>
</div>
<br>
<div align="center" class="alert alert-info">Para que se apliquen los cambios se debe reiniciar el servidor tomcat </div>
<form:form method="POST" modelAttribute="Enlaces" class="form-horizontal" autocomplete="off">
<div class="container small">
	<div class="row">
		 <div class="col-lg border border-secondary">N</div>
		 <div class="col-lg border border-secondary">
		 	<form:input type="text" readonly="true" size="10" path="id" class="border border-secondary"/>
		 </div>
		 <div class="col-lg border border-secondary">
		   Enlace
		 </div>
		 <div class="col-lg border border-secondary">
		    <form:input type="text" style="width:600px" maxlength="100" path="descripcion" class="border border-primary"/>
			<div class="has-error">
				<form:errors path="descripcion" class="badge badge-danger small"/>
			</div> 
		 </div>
	</div>
	<div class="row">
		 <div class="col-lg border border-secondary">Perfiles</div>
		 <div>
		 	<form:checkboxes id="CSPerfiles" items="${perfileslist}" itemLabel="type" itemValue="id" path="userProfiles" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;"/>							
			<div class="has-error">
				<form:errors path="userProfiles" class="badge badge-danger small"/>
			</div>
		 </div>
	</div>
</div>
<div align="left" class = "container">
<div class="row" align="center">
	<div class="col-sm-3"></div>
	<div class="col-sm-2"><form:button id="BGrabar" class="btn btn-outline-primary"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
	<div class="col-sm-2"><a href="javascript:FLimpar()" class="btn btn-outline-primary"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a></div>
</div>
</div>
</form:form>
<table class="table table-hover small">
  <thead>
    <tr>
      <th scope="col">N</th>
      <th scope="col">Enlace</th>
      <th scope="col">Perfiles</th>
      <th scope="col">Eliminar</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="it" items="${ListaEnlaces}">
    <tr>
      <th scope="row"><a href="javascript:FBuscar(${it.id})">${it.id}</a></th>
      <td>${it.descripcion}</td>
      <td>
      	<c:forEach var="var" items="${it.userProfiles}">
      		${var.type} / 
      	</c:forEach>
      </td>
      <td><a href="javascript:FEliminar(${it.id})"><i class="fa fa-times" aria-hidden="true"></i></a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>