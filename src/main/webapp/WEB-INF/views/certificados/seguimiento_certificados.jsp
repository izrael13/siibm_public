<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Seguimiento de certificados</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
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
function FImprimirCert(id)
{
	var redirectWindow = window.open('<c:url value="/certificados/calidad/certificadosimpr"/>?id='+id);
	redirectWindow.replace;
}
function FBuscar()
{
	var remi = $("#remi").val();
	var tf = $("#tf").val();
	if(remi == "" && tf == "")
		alert("Capture Remisión o TF.");
	else
	{
		$( "#DivMensaje" ).text("Ejecutando petición, por favor espere...").removeClass().addClass("alert alert-info");
		$.ajax({
			url: '<c:url value="/certificados/calidad/seguimiento_certificadoslista"/>?remi='+remi+'&tf='+tf,
			beforeSend: function(xhr) {
								 
						        },	
	        success : function(data) {

	        		var tabla = " <table class=\"table table-sm table-bordered table-hover\">";
	        			tabla = tabla + " <thead>";
	        			tabla = tabla + " <tr>";
	        			tabla = tabla + " <th>Folio</th>";
	        			tabla = tabla + " <th>Remisión</th>";
	        			tabla = tabla + " <th>Símbolo</th>";
	        			tabla = tabla + " <th>TF</th>";
	        			tabla = tabla + " <th>Fecha certificado</th>";
	        			tabla = tabla + " </tr>";
	        			tabla = tabla + " </thead>";
	        			tabla = tabla + " </tbody>";
	        		$.each(jQuery.parseJSON(data),function(index, value){
	        			tabla = tabla + " <tr> <td><a href=\"javascript:FBuscarCerf("+value.id+") \">"+value.id+"</a> </td> ";
	        			tabla = tabla + " <td>"+value.remision+" </td> ";
	        			tabla = tabla + " <td>"+value.itemcodecert+" </td> ";
	        			tabla = tabla + " <td>"+value.folio_tf+" </td> ";
	        			tabla = tabla + " <td>"+value.fecha_certificado+" </td> </tr> ";
		        	});
	        		tabla = tabla + " </tbody>";
	        		tabla = tabla + " </table>";
	        		document.getElementById("DTabla").innerHTML = tabla;
	        		$("#RemCert").modal(); 
	        		$( "#DivMensaje" ).text("").removeClass();
	        },
	        error: function(xhr, status, error) {
	        		document.getElementById("DTabla").innerHTML = "";
				  $( "#DivMensaje" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
			  }
		 });
	}
}
function FBuscarCerf(id)
{
	window.location.replace('<c:url value="/certificados/calidad/seguimiento_certificados" />?id='+id);
}
</script>
</head>
<body>
<div class="col-12">
	<div id="DivMensaje" class="${!empty mensaje ? 'alert alert-success' : ''}">${mensaje}</div>
	<div class="${!empty errors ? 'alert alert-warning' : ''}">${errors}</div>	
	<div class="row badge badge-primary col-12">Seguimiento de certificados</div>
	<div class = "container-fluid">
		<div class="col-12">
			<div class="row small border border-right">
				  <fmt:parseNumber var = "id" integerOnly = "true" pattern="##############" type = "number" value = "${CertInfo.id}" />
			      <div class="col-2">Remisión: <input id="remi" name="remi" type="text" class="border border-primary" size="10" maxlength="10"/></div>
			      <div class="col-2">TF: <input id="tf" name="tf" type="text" class="border border-primary" size="10" maxlength="10"/></div>
			      <div class="col-2"><button  class="btn btn-outline-primary btn-sm" type="button" onClick="FBuscar()" value="Buscar"/>Buscar</button></div>
			      <div class="col-2"><a class="btn btn-outline-primary btn-sm" href="/barcasii/certificados/calidad/seguimiento_certificados" target="_self">Limpiar</a></div>
			      <div class="col-2"><button id="BIngBoc" type="button" onClick="FImprimirCert(${id})" class="btn btn-outline-primary btn-sm"><i class="fa fa-paper-plane-o" aria-hidden="true"> Imprimir</i></button></div>
			</div>
		</div>
	
		<div class="row">
			<div class="badge badge-primary col-12">Certificado</div>
		</div>
		<div class="col-12">
			<div class="row small border border-right">
				<div class="col-1">Folio: ${id}</div>
				<div class="col-2">Remisión: ${CertInfo.remision}</div>
				<div class="col-1">TF:${CertInfo.folio_tf}</div>
				<div class="col-3">Fecha certificado: ${CertInfo.fecha_certificado}</div>
			</div>
		</div>
		<div class="col-12">
			<div class="row small border border-right">
				<div class="col-sm-2">Estatus: ${empty CertInfo.estatus ? '' : CertInfo.estatus == true ? 'GENERADO' : 'CANCELADO'}</div>
				<div class="col-sm-3">Cliente: ${CertInfo.cardname_normal}</div>
				<div class="col-sm-4">Símbolo: ${CertInfo.itemcode}: ${CertInfo.itemname}</div>
				<div class="col-sm-3">Proveedor: ${CertInfo.proveedor}</div>
			</div>
		</div>
		<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
			<div class="row small border border-right">
				<div class="col-sm-2">Cantidad: ${CertInfo.cant}</div>
				<div class="col-sm-2">Fecha embarque: ${!empty CertInfo.fecha_embarque ? fn:substring(CertInfo.fecha_embarque, 0, 10) : ''}</div>
				<div class="col-sm-2">Fecha fabricación: ${!empty CertInfo.fecha_fabricacion ? fn:substring(CertInfo.fecha_fabricacion, 0, 10) : ''}</div>
				<div class="col-sm-2">Lote fab: ${CertInfo.lotefab}</div>
				<div class="col-sm-2">Lote fab imp: ${CertInfo.lote_fab_imp}</div>
			</div>
		</div>
		<div class="col-12">
		<div class="row badge badge-info col-12">Evaluación de calidad</div>
	</div>
	<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
		<div class="row small">
			<div align="center" class="col-sm-4 border border-right font-weight-bold">Características</div>
			<div align="center" class="col-sm-4 border border-right font-weight-bold">Especificación</div>
			<div align="center" class="col-sm-4 border border-right font-weight-bold">Resultados</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">IMPRESIÓN:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.impresion}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.impresion}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">ESTILOS:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.estilos}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.estilos}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">TEXTOS:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.textos}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.textos}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">CODIGOS DE BARRAS:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.cod_barras}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.cod_barras}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">COLOR:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.color}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.color}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">TIPO DE FLAUTA:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.flauta}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.flauta}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">UNION:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.unionn}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.unionn}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES INTERNAS ± 3mm ) LARGO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.dil}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.dil}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES INTERNAS ± 3mm ) ANCHO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.dia}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.dia}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES INTERNAS ± 3mm ) FONDO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.dif}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.dif}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES EXTERNAS ± 3mm ) LARGO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.del}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.del}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES EXTERNAS ± 3mm ) ANCHO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.dea}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.dea}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES EXTERNAS ± 3mm ) FONDO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.def}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.def}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">PIEZAS POR PALLET:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.pzasxpallet}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.pzasxpallet}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">PIEZAS POR ATADO:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.pzasxatado}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.pzasxatado}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">ECT (lb/in) MINIMO ESPECIFICADO:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.ect_me}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.ect_me_r}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">BCT (lbf) MINIMO ESPECIFICADO:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.bct_me}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.bct_me_r}
			</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">MULLEN (kg/cm2) ± 5 %:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.mullen_me}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.mullen_me_r}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">COBB GR/M2 A 105 SEG:</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.cobb}</div>
			<div align="center" class="col-sm-4 border border-right">${CertInfo.cobb}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">Generado por:${CertInfo.generado_por}</div>
			<div class="col-sm-4 border border-right">Puesto:${CertInfo.puesto}</div>
			<div class="col-sm-4 border border-right">Observaciones:${CertInfo.observaciones}</div>
		</div>
	</div>
		<div class="col-12">
			<div class="row badge badge-info col-12">Liberación CM</div>
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
	        		<td>${CertInfo.a}</td>
	        		<td rowspan="14">
		     			<img height="200" width="200" src="<c:url value="/static/img_certificados/${CertInfo.tf_lcm}.jpg"/>" alt="${CertInfo.tf_lcm}" id="" onclick="AmpliarImg('<c:url value="/static/img_certificados/${CertInfo.tf_lcm}.jpg"/>')">
	        		</td>
        		</tr>
        		<tr>
	        		<td>B</td>
	        		<td>${CertInfo.b}</td>
        		</tr>
        		<tr>
	        		<td>C</td>
	        		<td>${CertInfo.c}</td>
        		</tr>
        		<tr>
	        		<td>D</td>
	        		<td>${CertInfo.d}</td>
        		</tr>
        		<tr>
	        		<td>E</td>
	        		<td>${CertInfo.e}</td>
        		</tr>
        		<tr>
	        		<td>F</td>
	        		<td>${CertInfo.f}</td>
        		</tr>
        		<tr>
	        		<td>G</td>
	        		<td>${CertInfo.g}</td>
        		</tr>
        		<tr>
	        		<td>H</td>
	        		<td>${CertInfo.h}</td>
        		</tr>
        		<tr>
	        		<td>I</td>
	        		<td>${CertInfo.i}</td>
        		</tr>
        		<tr>
	        		<td>J</td>
	        		<td>${CertInfo.j}</td>
        		</tr>
        		<tr>
	        		<td>K</td>
	        		<td>${CertInfo.k}</td>
        		</tr>
        		<tr>
	        		<td>L</td>
	        		<td>${CertInfo.l}</td>
        		</tr>
        		<tr>
	        		<td>M</td>
	        		<td>${CertInfo.m}</td>
        		</tr>
        		<tr>
	        		<td>N</td>
	        		<td>${CertInfo.m}</td>
        		</tr>
        	</tbody>
        </table>
		</div>
	</div>
</div>
<div class="modal fade bd-example-modal-lg" id="RemCert" tabindex="-1" role="dialog" aria-labelledby="RemCertLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header alert alert-info">
        <h5 class="modal-title">Certificados</h5>
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
</body>
</html>