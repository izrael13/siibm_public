<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Gestión de archivos</title>
<%@include file="../../appconfig/authheader2.jsp"%>
<script type="text/javascript">
function FSubirArchivo()
{
	var data = new FormData();
	jQuery.each(jQuery('#TSubirF')[0].files, function(i, file) {
		data.append('file', file);
	});
	data.append('fecha',$("#TFecha").val());
	
	$("#mensajes" ).text("Subiendo archivo...").removeClass().addClass("alert alert-info");
	var tabla = "";
	$.ajax({
	    url: '<c:url value="/cuentasxcobrar/conciliacion/gestionarchivos"/>',
	    data: data,
	    type : 'POST',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        before: function() {
        	if (r.search(/Login page/i) != -1) {
    			window.location.replace('<c:url value="/login?expired"/>');
			    return true;
			  }
  	    },
	    success: function(r) {
	    	tabla = ' <div align="center" id="tabla1" class = "table-responsive-xl"> <table id="tablePag" class="table-hover table-bordered text-center mx-auto"> ';
	    	tabla = tabla + ' <thead> '
	    	tabla = tabla + ' <tr> '
	    	tabla = tabla + ' <th>CFDI</th> '
	    	tabla = tabla + ' <th>Fecha factura</th> '
	    	tabla = tabla + ' <th>Fecha conciliación</th> '
	    	tabla = tabla + ' <th>Importe</th> '
	    	tabla = tabla + ' </tr> '
	    	tabla = tabla + ' </thead> ';
	    	tabla = tabla + ' <tbody> ';
	    	$.each(jQuery.parseJSON(r),function(index, value){
	    		tabla = tabla + ' <tr> ';
	    		tabla = tabla + ' <td>'+value.cfdi+'</td> ';
	    		tabla = tabla + ' <td>'+value.fecha_fac+'</td> ';
	    		tabla = tabla + ' <td>'+value.fecha_conci+'</td> ';
	    		tabla = tabla + ' <td>'+value.importe+'</td> ';
	    		tabla = tabla + ' </tr> ';
        	}); 
	    	tabla = tabla + ' </tbody> </table> </div> ';
	    	document.getElementById("Tabla").innerHTML = tabla;
	    	$("#mensajes" ).text("Archivo subido correctamente...").removeClass().addClass("alert alert-success");
	    },
	    error: function(r) {	  
	    	$("#mensajes" ).text("Error: "+r.body).removeClass().addClass("alert alert-danger");
	    }
	  }); 
}
function FLimpar()
{
	window.location.replace('<c:url value="/cuentasxcobrar/conciliacion/gestionarchivos"/>');
}
</script>
</head>
<body>
<div id="mensajes"></div>
<c:if test="${error != null}">
	<div class="alert alert-warning">
		<p>${error}</p>
	</div>
</c:if>
<c:if test="${mensaje != null}">
	<div class="alert alert-success">
		<p>${mensaje}</p>
	</div>
</c:if>
<div class="container">
	<div class="badge badge-primary col-12">Gestión de archivos</div>
</div>
<div class="alert alert-warning col-12" align="center">Atención: Se actualizarán los datos si ya existen</div>
<div class="container">
	<div class="row small">
		 <div class="col-lg-3 border border-secondary">
		   Seleccione archivo
		 </div>
		 <div class="col-lg-4 border border-secondary">
			<input type="file" id="TSubirF" class="btn btn-outline-primary btn-sm">
		 </div>
		 <div class="col-lg-2 border border-secondary">
		   Fecha de pago
		 </div>
		 <div class="col-lg-3 border border-secondary">
		    <div class="">
			<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
	            <input onkeypress="return false" id="TFecha" data-target="#datetimepicker4" placeholder="yyyy-mm-dd" class="border border-primary"/>
	            <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
	                <div class="input-group-text"><i class="fa fa-calendar"></i></div>
	            </div>
            </div>
            </div>
			<script type="text/javascript">
	            $(function () {
	                $('#datetimepicker4').datetimepicker({
	                    format: 'YYYY-MM-DD'
	                });
	            });
	        </script>
		 </div>
	</div>
	<div class="col-12" align="center">
		<a href="javascript:window.FSubirArchivo()" class="btn btn-outline-primary btn-sm"><i class="fa fa-cloud-upload" aria-hidden="true">Subir</i></a>
		<a href="javascript:FLimpar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a>
	</div>
</div>
<br>
<div id = "Tabla"></div>
</body>
</html>