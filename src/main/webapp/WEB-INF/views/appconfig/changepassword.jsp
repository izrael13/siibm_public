<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cambio de contraseña</title>
	<%@include file="authheader2.jsp"%>
<script type="text/javascript">
function validar()
{
	if($("#password").val().trim() == "")
	{
		$( "#mensajes" ).text("Capture contraseña nueva");
		$( "#mensajes").removeClass().addClass("alert alert-danger");
		$("#password").focus();
	}
	else
	{
		if($("#password_conf").val().trim() == "")
		{
			$( "#mensajes" ).text("Confirme contraseña");
			$( "#mensajes").removeClass().addClass("alert alert-danger");
			$("#password_conf").focus();
		}
		else
		{
			if($("#password").val().trim() != $("#password_conf").val().trim())
				{
					$( "#mensajes" ).text("No coincide la contraseña, favor de verificar");
					$( "#mensajes").removeClass().addClass("alert alert-danger");
				}
			else
			{
				var encodedString = btoa($("#password").val());
				var token =  $('input[name="csrfToken"]').attr('value');
				
				var jsonStr ={
						p: encodedString //,
						//ajxDemoras: $("#txtDemoras"+nviaje).val(),
						//ajxDevoluciones: $("#txtDevoluciones"+nviaje).val()
				};
				//alert(JSON.stringify(jsonStr));
				$.ajax({
					  dataType: 'text',
					  contentType : 'application/json',
					  type: 'post',
					  headers: {
			              'X-CSRF-Token': token 
			           },
					  url: '<c:url value="/act_pass"/>',
					  cache: false,    
					  data: JSON.stringify(jsonStr),
					  beforeSend: function(xhr) {
						  $( "#mensajes").text("Ejecutando, por favor espere...");
				            xhr.setRequestHeader("Accept", "application/json");
				            xhr.setRequestHeader("Content-Type", "application/json");
				        },		  
					  success: function(response){
						  if(response != "Contraseña actualizada correctamente.")
						  {
							  window.location.replace('<c:url value="/login"/>');
						  }
						  else
						  {
							  $( "#mensajes" ).text(response);
							  $( "#mensajes").removeClass().addClass("alert alert-success");
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
}

function Showpass()
{
	var x = document.getElementById("password");
	var y = document.getElementById("password_conf");
	
	  if (x.type === "password" && x.type === "password") {
		  $( "#Eye").removeClass().addClass("fa fa-eye fa-2x");
	    x.type = "text";
	    y.type = "text";
	  } else {
		  $( "#Eye").removeClass().addClass("fa fa-eye-slash fa-2x");
	    x.type = "password";
	    y.type = "password";
	  }
}
</script>
</head>
<body>
	<br>
		<div align="center">
			<span class="badge badge-secondary">Cambio de contraseña</span>
		</div>
		
			<div align="center">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Nueva Contraseña</label>
					<div class="col-md-7">
						<input type="password"  id="password" class="form-control input-sm" />
					</div>
				</div>
			</div>
			
			<div align="center">
			<div class="form-group col-md-12">
			<a href=""><i onmouseover="Showpass()" onmouseout="Showpass()" id="Eye" class="fa fa-eye-slash fa-2x" aria-hidden="true"></i></a>
			</div>
			</div>
			
			<div align="center">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Confirme Contraseña</label>
					<div class="col-md-7">
						<input type="password"  id="password_conf" class="form-control input-sm" />
					</div>
				</div>
			</div>
			<div align="center">
				<div class="form-group col-md-12">
					<div class="col-md-7">
						<button type="button" class="btn btn-outline-primary" onclick="validar()">Enviar</button>
					</div>
				</div>
			</div>
			<div class="container">
				<div>
					<div class="col-lg-2 col-md-2">
					</div>
					<div class="col-lg-8 col-md-8">
						<div id="mensajes" class="${not empty result ? 'alert alert-success' : ''}">${result} </div>
					</div>
				</div>
			</div>
	<br>
	<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
	<%@include file="authfootter.jsp"%>
</body>
</html>