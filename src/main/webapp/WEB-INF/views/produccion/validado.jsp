<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../appconfig/authheader2.jsp"%>
<title>Validar</title>
<script type="text/javascript">

function FLimpiar()
{
	window.location.replace('<c:url value="/produccion/validacion/pedidopapel"/>');
}

function FValLinerMed(tipop)
{
	var programa = document.getElementById("keyword_num_programa").value;
	var tipopapel = tipop;
	var rollo = "";
	var tabla_pedido = "";
	var tabla_rollos = "";
	switch (tipopapel)
	{
		case "L1": rollo = document.getElementById('keyword_num_rollol1').value; break;
		case "L2": rollo = document.getElementById('keyword_num_rollol2').value; break;
		case "L3": rollo = document.getElementById('keyword_num_rollol3').value; break;
		case "M1": rollo = document.getElementById('keyword_num_rollom1').value; break;
		case "M2": rollo = document.getElementById('keyword_num_rollom2').value; break;
		default: rollo = ""; break;
		
	}
	
	$.ajax({
		url: '<c:url value="/produccion/validacion/validar"/>?programa='+programa+'&rollo='+rollo+'&tipopapel='+tipopapel,
		beforeSend: function(xhr) {
							  $("#mensajes" ).text("Validando, por favor espere . . .").removeClass().addClass("alert alert-info");
					        },	
        success : function(data) {
	        	if(data == "0")
	        		$( "#mensajes" ).text("No se encontró ninguna coincidencia. Favor de verificar.").removeClass().addClass("alert alert-danger");
	        	else
	        	{
	        		var dataArray= data.split("||");
	        		
	        		tabla_pedido = ' <div align="center" id="tabla1" class = "table-responsive-xl"> <table id="tablePag" class="table-hover table-bordered text-center mx-auto"> ';
	        		tabla_pedido = tabla_pedido + ' <thead> '
	        		tabla_pedido = tabla_pedido + ' <tr> '
	        		tabla_pedido = tabla_pedido + ' <th>Programa</th> '
	        		tabla_pedido = tabla_pedido + ' <th>Pedido</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Símbolo</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Liner 1</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Liner 1 W</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Liner 2</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Liner 2 W</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Liner 3</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Liner 3 W</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Medium 1</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Medium 1 W</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Medium 2</th> '
	    	    	tabla_pedido = tabla_pedido + ' <th>Medium 2 W</th> '
	    	    	tabla_pedido = tabla_pedido + ' </tr> '
	    	    	tabla_pedido = tabla_pedido + ' </thead> ';
	    	    	tabla_pedido = tabla_pedido + ' <tbody> ';
	        		
	        		$.each(jQuery.parseJSON(dataArray[0]),function(index, value){
	        			tabla_pedido = tabla_pedido + ' <tr> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.Programa+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.Pedido+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.Simbolo+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.LINER1+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.LINER1WIDTH+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.LINER2+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.LINER2WIDTH+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.LINER3+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.LINER3WIDTH+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.MEDIUM1+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.MEDIUM1WIDTH+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.MEDIUM2+'</td> ';
	        			tabla_pedido = tabla_pedido + ' <td>'+value.MEDIUM2WIDTH+'</td> ';
	        			tabla_pedido = tabla_pedido + ' </tr> ';
	        		});
	        		tabla_pedido = tabla_pedido + ' </tbody> </table> </div> ';
	        		document.getElementById("tabla_pedidos").innerHTML = tabla_pedido;
	        		
	        		tabla_rollos = ' <div align="center" id="tabla1" class = "table-responsive-xl"> <table id="tablePag" class="table-hover table-bordered text-center mx-auto"> ';
	        		tabla_rollos = tabla_rollos + ' <thead> '
	        		tabla_rollos = tabla_rollos + ' <tr> '
	        		tabla_rollos = tabla_rollos + ' <th>Papel ID</th> '
	        		tabla_rollos = tabla_rollos + ' <th>Núm Rollo</th> '
	        		tabla_rollos = tabla_rollos + ' <th>Descripción</th> '
	        		tabla_rollos = tabla_rollos + ' <th>Tipo Papel</th> '
	        		tabla_rollos = tabla_rollos + ' <th>Ancho</th> '
	        		tabla_rollos = tabla_rollos + ' </tr> '
	        		tabla_rollos = tabla_rollos + ' </thead> ';
	        		tabla_rollos = tabla_rollos + ' <tbody> ';
	        		
	        		$.each(jQuery.parseJSON(dataArray[1]),function(index, value){
	        			tabla_rollos = tabla_rollos + ' <tr> ';
	        			tabla_rollos = tabla_rollos + ' <td>'+value.PapelID+'</td> ';
	        			tabla_rollos = tabla_rollos + ' <td>'+value.numeroRolloID+'</td> ';
	        			tabla_rollos = tabla_rollos + ' <td>'+value.Descripcion+'</td> ';
	        			tabla_rollos = tabla_rollos + ' <td>'+value.tipoPapel+'</td> ';
	        			tabla_rollos = tabla_rollos + ' <td>'+value.Ancho+'</td> ';
	        			tabla_rollos = tabla_rollos + ' </tr> ';
	        		});
	        		
	        		tabla_rollos = tabla_rollos + ' </tbody> </table> </div> ';
	        		document.getElementById("tabla_rollos").innerHTML = tabla_rollos;
	        		
	        		$("#mensajes" ).text("").removeClass();	
	        		
	        		
	        		//$("#keyword_num_rollol1,#keyword_num_rollol2,#keyword_num_rollol3,#keyword_num_rollol4,#keyword_num_rollol5,#keyword_num_programa").val("");
	        	}
        },
        error: function(xhr, status, error) {
			  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error).removeClass().addClass("alert alert-danger");
		  }
	 });
}

</script>
</head>
<body>
<div align="center"><br>
<h4>Validación de programas con rollos</h4>
<table class="table table-hover small">
  <tr>
    <th>Ingresa número programa</th>
    <td><input type="text" id="keyword_num_programa">	</td>
  </tr> 
  <tr>
    <td>Número rollo papel LINER 1</td>
    <td><input type="text" id="keyword_num_rollol1"></td>
    <td><input type="button" value="Validar Liner 1" onclick="FValLinerMed('L1')"></td>
  </tr>
  <tr>
    <td>Número rollo papel LINER 2</td>
    <td><input type="text" id="keyword_num_rollol2"></td>
    <td><input type="button" value="Validar Liner 2" onclick="FValLinerMed('L2')"></td>
  </tr>
  <tr>
    <td>Número rollo papel LINER 3</td>
    <td><input type="text" id="keyword_num_rollol3"></td>
    <td><input type="button" value="Validar Liner 3" onclick="FValLinerMed('L3')"></td>
  </tr> 
  <tr>
    <td>Número rollo papel MEDIUM 1</td>
    <td><input type="text" id="keyword_num_rollom1"></td>
    <td><input type="button" value="Validar Medium 1" onclick="FValLinerMed('M1')"></td>
  </tr>
  <tr>
    <td>Número rollo papel MEDIUM 2</td>
    <td><input type="text" id="keyword_num_rollom2"></td>
    <td><input type="button" value="Validar Medium 2" onclick="FValLinerMed('M2')"></td>
  </tr> 
  
</table>	
	<div id="mensajes"></div>
	<div id="tabla_pedidos"></div>
	<div id="tabla_rollos"></div>
	<br/>
	<div align="center"><button type="button" onClick="FLimpiar()" class="btn btn-outline-primary btn-sm"><i class="fa fa-refresh" aria-hidden="true"> Limpiar</i></button></div>
	
<div align="center">
<%@include file="../appconfig/authfootter.jsp"%>
</div>
</div>
</body>
</html>