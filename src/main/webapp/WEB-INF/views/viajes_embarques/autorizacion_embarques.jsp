<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Autorización Embarques</title>
<%@include file="../appconfig/authheader2.jsp"%>
<script>
function Detalle(nviaje)
{
	//alert(nviaje);
	//window.open('<c:url value="/viajes/detalle" />?num_viaje='+nviaje,null,
	//"height=800,width=1000,status=yes,toolbar=no,menubar=no,location=no");
	popupwindow('<c:url value="/viajes/detalle" />?num_viaje='+nviaje,'Detalle de viaje',800,1000);
}
function Autorizar(nviaje)
{
	if(confirm("¿Autorizar el viaje: "+nviaje +"?"))
	{
		window.location.replace('<c:url value="/viajes/set_aut_embarques" />?num_viaje='+nviaje);
	}
}
function Actualizar(nviaje)
{
	/*if($.trim($("#txtManiobras"+nviaje).val()) == "" || $.trim($("#txtManiobras"+nviaje).val()) < 0)
	{
		$( "#mensajes" ).text(nviaje + ": Maniobras: Capture un valor mayor o igual a Cero (0).");
		$( "#mensajes").removeClass().addClass("alert alert-warning");
		$("#txtManiobras"+nviaje).focus();
	}
	else
	{*/
		if($.trim($("#txtCboEdo"+nviaje).val()) == "" || $.trim($("#txtCboEdo"+nviaje).val()) < 0)
		{
			$( "#mensajes" ).text(nviaje + ": Cambios de estado: Capture un valor mayor o igual a Cero (0).");
			$( "#mensajes").removeClass().addClass("alert alert-warning");
			$("#txtCboEdo"+nviaje).focus();
		}
		else
		{
			if($.trim($("#txtDemoras"+nviaje).val()) == "" || $.trim($("#txtDemoras"+nviaje).val()) < 0)
			{
				$( "#mensajes" ).text(nviaje + ": Demoras: Capture un valor mayor o igual a Cero (0).");
				$( "#mensajes").removeClass().addClass("alert alert-warning");
				$("#txtDemoras"+nviaje).focus();
			}
			else
			{
				if($.trim($("#txtDevoluciones"+nviaje).val()) == "" || $.trim($("#txtDevoluciones"+nviaje).val()) < 0)
				{
					$( "#mensajes").text(nviaje + ": Devoluciones: Capture un valor mayor o igual a Cero (0).");
					$( "#mensajes").removeClass().addClass("alert alert-warning");
					$("#txtDevoluciones"+nviaje).focus();
				}
				else
				{
					var token =  $('input[name="csrfToken"]').attr('value'); 
					var jsonStr ={
							ajxnum_viaje: nviaje,
							ajxDemoras: $("#txtDemoras"+nviaje).val(),
							ajxDevoluciones: $("#txtDevoluciones"+nviaje).val(),
							ajxCboEdo: $("#txtCboEdo"+nviaje).val() /*,
							ajxManiobras: $("#txtManiobras"+nviaje).val()*/
					};
					//alert(JSON.stringify(jsonStr));
					$.ajax({
						  dataType: 'text',
						  contentType : 'application/json',
						  type: 'post',
						  headers: {
				              'X-CSRF-Token': token 
				           },
						  url: '<c:url value="/viajes/act_viaje"/>',
						  cache: false,    
						  data: JSON.stringify(jsonStr),
						  beforeSend: function(xhr) {
							  $( "#mensajes").text("Ejecutando, por favor espere...");
							  $( "#mensajes").removeClass().addClass("alert alert-primary");
					            xhr.setRequestHeader("Accept", "application/json");
					            xhr.setRequestHeader("Content-Type", "application/json");
					        },		  
						  success: function(response){
							  try
							  {
								  var content = JSON.parse(response);
								  //alert(response)
								  $("#txtCboEdo"+nviaje).val(content.U_cambio_edo);
								  $("#txtDemoras"+nviaje).val(content.U_demoras);
								  $("#txtDevoluciones"+nviaje).val(content.U_devoluciones);
								  $("#divRepartos"+nviaje).text(content.U_repartos);
								  $("#divCosto"+nviaje).text(content.U_costo);
								  $( "#mensajes" ).text(nviaje+ " : " +"Viaje actualizado correctamente");
								  $( "#mensajes").removeClass().addClass("alert alert-success");
							  }
							  catch(e)
							  {
								  alert(nviaje+ " : " + e.message);
								  window.location.replace('<c:url value="/viajes/aut_embarques"/>');
							  }
						  },
						  error: function(xhr, status, error) {
							  $( "#mensajes" ).text("Error: " + xhr.responseText + " Codigo" +  error);
							  $( "#mensajes").removeClass().addClass("alert alert-danger");
						  }
						  
					 });
				}
			}
		}
	//}
}
$(document).ready(function() {
	$('#tablePag').DataTable();
} );
</script>
</head>
<body>
	<br>
	<div align="center">
		<span class="badge badge-secondary">Autorización Embarques</span>
	</div>
	<br>
	<div align="center" id="tabla1" class = "table-responsive-xl container">
		<table id="tablePag" class="table-hover table-bordered text-center small mx-auto">
		<thead>
			<tr>
				<th>Viaje</th>
				<th>Fecha registro</th>
				<th>Kilos</th>
				<th>Repartos</th>
				<th>Cambios de estado</th>
				<th>Maniobras</th>
				<th>Costo</th>
				<th>Demoras</th>
				<th>Devoluciones</th>
				<th>Actualizar</th>
				<th>Autorizar</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${viajes}">
			<tr>
				<td><a href="javascript:Detalle(${item.u_num_viaje})">${item.u_num_viaje}</a></td>
				<td>${item.u_fecha_reg}</td>
				<td>${item.u_kilos}</td>
				<td><div id="divRepartos${item.u_num_viaje}">${item.u_repartos}</div></td>
				<td><input style ="width: 70px;" id="txtCboEdo${item.u_num_viaje}" class="form-control" type="number" value="${item.u_cambio_edo}"
							onkeypress="return Enteros(event)" maxlength="4" /></td>
				<td><!-- <input style ="width: 70px;" id="txtManiobras${item.u_num_viaje}" class="form-control" type="number" value="${item.u_maniobras}"
							onkeypress="return Enteros(event)" maxlength="4" /></td> -->
							${item.u_maniobras}
				<td><div id="divCosto${item.u_num_viaje}">${item.u_costo}</div></td>
				<td>
					<input style ="width: 70px;" id="txtDemoras${item.u_num_viaje}" class="form-control" type="number" value="${item.u_demoras}"
							onkeypress="return Enteros(event)" maxlength="4" />
				</td>
				<td>
					<input style ="width: 70px;" id="txtDevoluciones${item.u_num_viaje}"  class="form-control" type="number" value="${item.u_devoluciones}"
							onkeypress="return Enteros(event)" maxlength="4" />
				</td>
				<td>
					<a href="javascript:Actualizar(${item.u_num_viaje})"><i class="fa fa-save fa-2x"></i></a>
				</td>
				<td>
					<a href="javascript:Autorizar(${item.u_num_viaje})"><i class="fa fa-thumbs-o-up fa-2x"></i></a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<br>
	<!-- <div align="center">
		<button onclick="FHistorial()" class="btn btn-outline-info">Historial de viajes</button>
	</div> -->
	<div class="container">
		<div class="row">
		<div class="col-lg-2 col-md-2">
		</div>
			<div class="col-lg-8 col-md-8">
				<div id="mensajes" class="${not empty result ? 'alert alert-success' : ''}">${result} </div>
			</div>
		</div>
	</div>
	<%@include file="../appconfig/authfootter.jsp"%>
	<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
</body>
</html>