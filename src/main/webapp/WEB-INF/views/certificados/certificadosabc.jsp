<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Certificados de calidad</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function FBuscarRem()
{
	var rem = $("#TRem").val();
	
	if(rem == "")
		alert("Capture una remisión o TF.");
	else
	{
		$( "#DivMensaje" ).text("Ejecutando petición, por favor espere...").removeClass().addClass("alert alert-info");
		$.ajax({
			url: '<c:url value="/certificados/calidad/buscarremisiones"/>?rem='+rem,
			beforeSend: function(xhr) {
								 
						        },	
	        success : function(data) {

	        		var tabla = " <table class=\"table table-sm table-bordered table-hover\">";
	        			tabla = tabla + " <thead>";
	        			tabla = tabla + " <tr>";
	        			tabla = tabla + " <th>Remisión</th>";
	        			tabla = tabla + " <th>Símbolo</th>";
	        			tabla = tabla + " <th>TF</th>";
	        			tabla = tabla + " </tr>";
	        			tabla = tabla + " </thead>";
	        			tabla = tabla + " </tbody>";
	        		$.each(jQuery.parseJSON(data),function(index, value){
	        			tabla = tabla + " <tr> <td><a href=\"javascript:FBuscar('"+value.numatcard+"','"+value.itemcodesi+"') \">"+value.numatcard+"</a> </td> ";
	        			tabla = tabla + " <td>"+value.itemcodesi+" </td> ";
	        			tabla = tabla + " <td>"+value.tfi+" </td> </tr> ";
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
function FBuscar(remi, itemcode)
{	
	window.location.replace('<c:url value="/certificados/calidad/certificadosabc"/>?remi='+remi+'&itemcode='+itemcode);
}
function FCancelar()
{
	var id = $("#TId").val();

	if(id > 0)
	{
		var http = new XMLHttpRequest();
		var url = '<c:url value="/certificados/calidad/cancelarcer"/>';
		var params = 'id='+id;
		
		$("#DivMensaje").text("Procesando petición. Por favor espere...").removeClass().addClass("alert alert-danger");
		
		http.open('POST', url, true);
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	
		http.onreadystatechange = function() {//Call a function when the state changes.
		    if(http.readyState == 4 && http.status == 200) 
		    {
		    	if (http.responseText.search(/Login page/i) != -1) {
		    		alert("La sessión ha expirado, Por favor vuelva a intentarlo.");
	    			window.location.replace('<c:url value="/login?expired"/>');
		    	}
	    		else{
	    			if(http.responseText === 'OK')
	    			{
	    				alert("Certificado cancelado.");
	    				window.location.replace('<c:url value="/certificados/calidad/certificadosabc"/>');
	    			}
	    			else
	    			{
	    				alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
	    				window.location.replace('<c:url value="/certificados/calidad/certificadosabc"/>');
	    			}
	    		}
		    }
		    else
		    {
		    	if(http.readyState == 4 && http.status != 200){
		    		alert("Algo salió mal, por favor vuelva a intentarlo: "+http.responseText);
		    		window.location.replace('<c:url value="/certificados/calidad/certificadosabc"/>');
		    	}
		    }
		    
		}
		http.send(encodeURI(params));
	} 
}
function FImprimirCert()
{
	var id = $("#TId").val();
	var redirectWindow = window.open('<c:url value="/certificados/calidad/certificadosimpr"/>?id='+id);
	redirectWindow.replace;
}
</script>
</head>
<body>
<div class="col-12">
	<div class="row badge badge-primary col-12">Certificado de calidad</div>
</div>
<form:form method="POST" modelAttribute="Certificado" class="form-horizontal" autocomplete="off">
	<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
		<div class="row small border border-right">
			<div class="col-sm-2">Folio:
				<form:input id="TId" class="border border-secondary" readonly="true" type="text" path="id" size="5"/>
			</div>
			<div class="col-sm-3">Remisión:
				<form:input id="TRem" class="border border-primary" type="text" path="remision" size="10" maxlength="10" 
					value="${empty Certificado.remision ? Remision.numatcard : Certificado.remision}"/>
				<div class="has-error">
					<form:errors path="remision" class="badge badge-danger small"/>
				</div>
			</div>
			<div class="col-sm-3">TF:
				<form:input id="TTF" class="border border-secondary" readonly="true" type="text" path="folio_tf" size="10" maxlength="10"
					value="${empty Certificado.folio_tf ? Remision.tfi : Certificado.folio_tf}"/>
				<div class="has-error">
					<form:errors path="folio_tf" class="badge badge-danger small"/>
				</div>
			</div>
			<div class="col-sm-3">Fecha certificado: ${!empty Certificado.fecha_certificado ? fn:substring(Certificado.fecha_certificado, 0, 16) : ''}</div>
		</div>
	</div>
	<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
		<div class="row small border border-right">
			<div class="col-sm-2">Estatus: ${empty Certificado.estatus ? '' : Certificado.estatus == true ? 'GENERADO' : 'CANCELADO'}</div>
			<div class="col-sm-3">Cliente: ${Tarjeta.cardname_normal}</div>
			<div class="col-sm-4">Símbolo: 
					<form:input id="TTF" class="border border-secondary" readonly="true" type="text" path="itemcodecert" size="10" maxlength="10"
					value="${empty Certificado.itemcodecert ? Tarjeta.itemcode : Certificado.itemcodecert}"/> 
					: ${Tarjeta.itemname}</div>
			<div class="col-sm-3">Proveedor: ${Tarjeta.proveedor}</div>
		</div>
	</div>
	<div class="col-12"><!-- mx-auto  para centrar en pantalla -->
		<div class="row small border border-right">
			<div class="col-sm-2">
			Cantidad:
				<form:input class="border border-primary" type="text" path="cant" size="10" maxlength="8" onkeypress="return filterFloat(event,this);"
					value="${empty Certificado.cant ? Remision.cantidad : Certificado.cant}"/>
				<div class="has-error">
					<form:errors path="cant" class="badge badge-danger small"/>
				</div>
			</div>
			<div class="col-sm-2">Fecha embarque: ${!empty Remision.fecha_embarque ? fn:substring(Remision.fecha_embarque, 0, 10) : ''}</div>
			<div class="col-sm-2">Fecha fabricación: ${!empty Remision.fecha_fabricacion ? fn:substring(Remision.fecha_fabricacion, 0, 10) : ''}</div>
			<div class="col-sm-2">Lote fab: ${Remision.lotefab}</div>
			<div class="col-sm-2">Lote fab imp:
				<form:input class="border border-primary" type="text" path="lote_fab_imp" size="10" maxlength="10"
					value="${empty Certificado.lote_fab_imp ? Remision.lotefab : Certificado.lote_fab_imp}"/>
				<div class="has-error">
					<form:errors path="lote_fab_imp" class="badge badge-danger small"/>
				</div>
			</div>
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
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.impresion}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.impresion}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">ESTILOS:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.estilos}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.estilos}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">TEXTOS:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.textos}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.textos}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">CODIGOS DE BARRAS:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.cod_barras}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.cod_barras}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">COLOR:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.color}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.color}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">TIPO DE FLAUTA:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.flauta}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.flauta}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">UNION:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.unionn}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.unionn}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES INTERNAS ± 3mm ) LARGO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.dil}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.dil}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES INTERNAS ± 3mm ) ANCHO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.dia}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.dia}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES INTERNAS ± 3mm ) FONDO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.dif}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.dif}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES EXTERNAS ± 3mm ) LARGO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.del}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.del}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES EXTERNAS ± 3mm ) ANCHO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.dea}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.dea}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">(DIMENSIONES EXTERNAS ± 3mm ) FONDO (cm) :</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.def}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.def}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">PIEZAS POR PALLET:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.pzasxpallet}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.pzasxpallet}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">PIEZAS POR ATADO:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.pzasxatado}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.pzasxatado}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">ECT (lb/in) MINIMO ESPECIFICADO:</div>
			<div align="center" class="col-sm-4 border border-right">
				<form:input class="border border-secondary" readonly="true" type="text" path="ect_me" size="15"
					value="${empty Certificado.ect_me ? Tarjeta.ect : Certificado.ect_me}"/>
			</div>
			<div align="center" class="col-sm-4 border border-right"><form:input class="border border-secondary" readonly="true" type="text" path="ect_me_r" size="15"
					value="${empty Certificado.ect_me_r ? Tarjeta.ect_r : Certificado.ect_me_r}"/>
			</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">BCT (lbf) MINIMO ESPECIFICADO:</div>
			<div align="center" class="col-sm-4 border border-right"><form:input class="border border-secondary" readonly="true" type="text" path="bct_me" size="15"
					value="${empty Certificado.bct_me ? Tarjeta.bct : Certificado.bct_me}"/>
			</div>
			<div align="center" class="col-sm-4 border border-right"><form:input class="border border-secondary" readonly="true" type="text" path="bct_me_r" size="15"
					value="${empty Certificado.bct_me_r ? Tarjeta.bct_r : Certificado.bct_me_r}"/>
			</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">MULLEN (kg/cm2) ± 5 %:</div>
			<div align="center" class="col-sm-4 border border-right"><form:input class="border border-secondary" readonly="true" type="text" path="mullen_me" size="15"
					value="${empty Certificado.mullen_me ? Tarjeta.mullen : Certificado.mullen_me}"/>
			</div>
			<div align="center" class="col-sm-4 border border-right"><form:input class="border border-secondary" readonly="true" type="text" path="mullen_me_r" size="15"
					value="${empty Certificado.mullen_me_r ? Tarjeta.mullen_r : Certificado.mullen_me_r}"/>
			</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">COBB GR/M2 A 105 SEG:</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.cobb}</div>
			<div align="center" class="col-sm-4 border border-right">${Tarjeta.cobb}</div>
		</div>
		<div class="row small">
			<div class="col-sm-4 border border-right">Generado por:
				<form:input class="border border-primary" type="text" path="generado_por" size="30" maxlength="100"/>
				<div class="has-error">
					<form:errors path="generado_por" class="badge badge-danger small"/>
				</div>
			</div>
			<div class="col-sm-4 border border-right">Puesto:
				<form:input class="border border-primary" type="text" path="puesto" size="30" maxlength="100"/>
				<div class="has-error">
					<form:errors path="puesto" class="badge badge-danger small"/>
				</div>
			</div>
			<div class="col-sm-4 border border-right">Observaciones:
			<form:input class="border border-primary" type="text" path="observaciones" size="35" maxlength="100"/>
				<div class="has-error">
					<form:errors path="observaciones" class="badge badge-danger small"/>
				</div>
			</div>
		</div>
	</div>
	<div align="left" class = "container">
		<div class = "row" align="center">			
			<div class="col col-lg-2"><form:button id="BGrabar" class="btn btn-outline-primary btn-sm" disabled="${empty Tarjeta.itemcode ? 'true' : 'false'}"><i class="fa fa-floppy-o" aria-hidden="true"> Grabar</i></form:button></div>
			<div class="col col-lg-2"><a href="javascript:FBuscarRem()" class="btn btn-outline-primary btn-sm"><i class="fa fa-search" aria-hidden="true"> Buscar</i></a></div>
			<div class="col col-lg-2"><a type="button" href="/barcasii/certificados/calidad/certificadosabc" target="_self" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></a></div>
			<div class="col col-lg-2"><button id="BIngBoc" type="button" onClick="FImprimirCert()" class="btn btn-outline-primary btn-sm"><i class="fa fa-paper-plane-o" aria-hidden="true"> Imprimir</i></button></div>
			<div class="col col-lg-2"><button id="BCancel" type="button" onClick="FCancelar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-times-circle-o" aria-hidden="true"> Cancelar</i></button></div>
		</div>
	</div>
	<div id="DivMensaje" class="${!empty mensaje ? 'alert alert-success' : ''}">${mensaje}</div>
	<div class="${!empty errors ? 'alert alert-warning' : ''}">${errors}</div>	
	
	<div class="modal fade bd-example-modal-lg" id="RemCert" tabindex="-1" role="dialog" aria-labelledby="RemCertLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header alert alert-info">
	        <h5 class="modal-title">Remisiones</h5>
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
</body>
</html>