<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cotizador</title>
<%@include file="../appconfig/authheader2.jsp"%>
</head>
	<body>		
	<div align="center" class = "container">
	 <form:form  class="form-horizontal">
	 <div align="center" class = "container">
		 <div class="row justify-content-md-center">
			 <div class="badge badge-primary col-4">
			 	Datos del cliente
			 </div>
			 <div class="badge badge-info col-6">
			 	Detalle
			 </div>
			 <div class="badge badge-success col-2">
			 	Especialidades
			 </div>
		 </div>
	 </div>
		<!-- <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
	  		<li class="nav-item">
	   			<a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Datos del Cliente</a>
	  		</li>
		  	<li class="nav-item">
		    	<a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Detalle</a>
		   	</li>
		  	<li class="nav-item">
		    	<a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact" role="tab" aria-controls="pills-contact" aria-selected="false">Especialidades</a>
		  	</li>
		</ul>
		<div class="tab-content" id="pills-tabContent">
			<div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
				Datos del Cliente
			</div>
			<div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
				Detalle
			</div>
			<div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">
				Especialidades
			</div>
		</div>  -->
		</form:form>
		</div>
		<%@include file="../appconfig/authfootter.jsp"%>
	</body>
</html>