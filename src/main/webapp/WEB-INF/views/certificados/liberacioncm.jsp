<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Liberación CM</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscar()
{
var tf = $("#TTF").val();
	
	if(tf == "")
		alert("Capture una TF.");
	else
	{
		$( "#DivMensaje" ).text("Ejecutando petición, por favor espere...").removeClass().addClass("alert alert-info");
		$.ajax({
			url: '<c:url value="/certificados/ingenieria/buscarsimbolocert"/>?tf='+tf,
			beforeSend: function(xhr) {
								 
						        },	
	        success : function(data) {
	        		var tabla = " <table class=\"table table-sm table-bordered table-hover\">";
	        			tabla = tabla + " <thead>";
	        			tabla = tabla + " <tr>";
	        			tabla = tabla + " <th>TF</th>";
	        			tabla = tabla + " <th>Id Símbolo</th>";
	        			tabla = tabla + " <th>Símbolo</th>";
	        			tabla = tabla + " </tr>";
	        			tabla = tabla + " </thead>";
	        			tabla = tabla + " </tbody>";
	        		$.each(jQuery.parseJSON(data),function(index, value){
	        			tabla = tabla + " <tr> <td><a href=\"javascript:FBuscarSI('"+value.itemcode+"') \">"+value.tf+"</a> </td> ";
	        			tabla = tabla + " <td>"+value.itemcode+" </td> ";
	        			tabla = tabla + " <td>"+value.itemname+" </td> </tr> ";
		        	});
	        		tabla = tabla + " </tbody>";
	        		tabla = tabla + " </table>";
	        		document.getElementById("DTabla").innerHTML = tabla;
	        		$("#TfCert").modal();
	        		$( "#DivMensaje" ).text("").removeClass();
	        },
	        error: function(xhr, status, error) {
	        		document.getElementById("DTabla").innerHTML = "";
				  $( "#DivMensaje" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  }
		 });
	}
}
function FBuscarSI(itemcode)
{
	window.location.replace('<c:url value="/certificados/ingenieria/liberacioncm"/>?itemcode='+itemcode);
}
function AmpliarImg(scr)
{
	foto=new Image();
	foto.src=scr;
	//document.images[0].src=foto.src;
	ancho=foto.width;
	alto=foto.height;

	foto.width = ancho;
	foto.height = alto;
	popupwindow(scr,'imagenestarjetas',alto,ancho);
	//alert(ancho)
	//alert(alto)
}
</script>
</head>
<body>
<div class="col-12">
	<div class="row badge badge-primary col-12">Liberación CM</div>
</div>
<form:form method="POST" modelAttribute="Liberacioncm" class="form-horizontal" enctype="multipart/form-data" autocomplete="off">
<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
	<div class="row small border border-right">
		<div class="col-sm-1">
			<form:input class="border border-secondary" type="text" path="id" size="8" maxlength="10" readonly="true"/>
		</div>
		<div class="col-sm-2">TF:
			<form:input id="TTF" class="border border-primary" type="text" path="tf_lcm" size="10" maxlength="10"
				value="${empty Liberacioncm.tf_lcm ? Tarjeta.tf : Liberacioncm.tf_lcm}"/>
			<div class="has-error">
				<form:errors path="tf_lcm" class="badge badge-danger small"/>
			</div>
		</div>
		<div class="col-sm-4">Observaciones:
			<form:input class="border border-primary" type="text" path="observaciones_lcm" size="35" maxlength="100"/>
		 </div>
		 <div class="col col-lg-2">Estado: ${empty Liberacioncm.activo ? '' : Liberacioncm.activo == true ? 'Activo' : 'Inactivo'}</div>
		 <div class="col col-lg-1"><a href="javascript:FBuscar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
		<div class="col col-lg-1"><a type="button" href="/barcasii/certificados/ingenieria/liberacioncm" target="_self" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a></div>
		<div class="col col-lg-1"><form:button id="BGrabar" class="btn btn-outline-primary btn-sm" disabled="${empty Tarjeta.itemcode ? 'true' : 'false'}"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
		</div>
		<div class="row small border border-right">
			<div class="col-sm-6">Cliente:
				${Tarjeta.cardcode_normal} -> ${Tarjeta.cardname_normal}
			</div>
			<div class="col-sm-6">Símbolo:
				<form:input class="border border-secondary" type="text" path="itemcode_lcm" size="10" maxlength="10"
				value="${empty Liberacioncm.itemcode_lcm ? Tarjeta.itemcode : Liberacioncm.itemcode_lcm}"/>
				 -> ${Tarjeta.itemname}
			</div>
		</div>
		<div class="row small border border-right">
		<table class="table table-sm table-bordered table-hover">
        	<thead>
        		<tr>
        			<th>Secuencia</th>
        			<th>ESPECIFICACIÓN</th>
        			<th>Imagen</th>
        		</tr>
        	</thead>
        	<tbody>
        		<tr>
	        		<td>A</td>
	        		<td><form:input size="10" type="text" path="a" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
	        		<td rowspan="14">
	        		 	<div><input class="btn btn-outline-primary btn-sm" name="file" id="file" type="file" accept="image/*" /></div>
		     			<img height="200" width="200" src="<c:url value="/static/img_certificados/${Liberacioncm.tf_lcm}.jpg"/>" alt="${Liberacioncm.tf_lcm}" id="" onclick="AmpliarImg('<c:url value="/static/img_certificados/${Liberacioncm.tf_lcm}.jpg"/>')">
	        		</td>
        		</tr>
        		<tr>
	        		<td>B</td>
	        		<td><form:input size="10" type="text" path="b" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>C</td>
	        		<td><form:input size="10" type="text" path="c" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>D</td>
	        		<td><form:input size="10" type="text" path="d" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>E</td>
	        		<td><form:input size="10" type="text" path="e" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>F</td>
	        		<td><form:input size="10" type="text" path="f" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>G</td>
	        		<td><form:input size="10" type="text" path="g" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>H</td>
	        		<td><form:input size="10" type="text" path="h" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>I</td>
	        		<td><form:input size="10" type="text" path="i" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>J</td>
	        		<td><form:input size="10" type="text" path="j" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>K</td>
	        		<td><form:input size="10" type="text" path="k" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>L</td>
	        		<td><form:input size="10" type="text" path="l" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>M</td>
	        		<td><form:input size="10" type="text" path="m" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        		<tr>
	        		<td>N</td>
	        		<td><form:input size="10" type="text" path="n" onkeypress="return filterFloat1(event,this);" class="border border-primary"/></td>
        		</tr>
        	</tbody>
        </table>
	</div>
	<div id="DivMensaje" class="${!empty mensaje ? 'alert alert-success' : ''}">${mensaje}</div>
	<div class="${!empty errors ? 'alert alert-warning' : ''}">${errors}</div>
</div>

	<div class="modal fade bd-example-modal-lg" id="TfCert" tabindex="-1" role="dialog" aria-labelledby="TfCertLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header alert alert-info">
		        <h5 class="modal-title">Símbolos</h5>
		      </div>
		      <div class="modal-body">
		        <div id="DTabla"></div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		      </div>
		    </div>
		  </div>
		</div>
</form:form>
<div class="col col-lg-1">
	<c:url var="deleteUrl" value="/certificados/ingenieria/liberacioncmdelete"/>    
	<form id="deletecm" action="${deleteUrl}" method="POST">
	      <input id="TID" name="TID" type="hidden" value="${Liberacioncm.id}"/>
	      <input  class="btn btn-outline-primary btn-sm" type="submit" value="delete" onClick="return confirm('Desea borrar?')"/>
	</form>
</div>
</body>
</html>