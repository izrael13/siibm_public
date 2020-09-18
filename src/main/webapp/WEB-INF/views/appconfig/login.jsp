<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login page</title>
<link rel="shortcut icon" href="<c:url value='/static/img/BarcaLogoV.png' />"/>
<%@include file="imports.jsp"%>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<script>
$(document).ready(function () {
    $('.forgot-pass').click(function(event) {
      $(".pr-wrap").toggleClass("show-pass-reset");
    }); 
    
    $('.pass-reset-submit').click(function(event) {
      $(".pr-wrap").removeClass("show-pass-reset");
    }); 
});
function SolCamPass()
{
	popupwindow('<c:url value="/sol_cam_passs_" />','Solicitud de reset contraseña',800,800);
}
function Showpass()
{
	var x = document.getElementById("password");
	if (x.type === "password") {
	 $( "#Eye").removeClass().addClass("fa fa-eye");
	  x.type = "text";
	} else {
	 $( "#Eye").removeClass().addClass("fa fa-eye-slash");
	  x.type = "password";
	}
}
</script>
<style type="text/css">
.login-form-block {
  margin: 30px auto;
  max-width: 340px;
}
h2{
	margin-bottom:10px;
}
form {
    border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

.logo {
    text-align: center;
    margin: 16px 0 10px 0;
}

.logo img {
    width: 40px;
    border-radius: 50%;
}
.input100 {
  font-family: Poppins-Medium;
  font-size: 15px;
  line-height: 1.5;
  color: #666666;

  display: block;
  width: 100%;
  height: 50px;
  border-radius: 25px;
  padding: 0 30px 0 68px;
}
.container-login100-form-btn {
  width: 100%;
  justify-content: center;
  border-radius: 25px;
  padding-top: 20px;
}
</style>
</head>
<body>
<div class="login-form-block">
<h2>Acceso SIIBM</h2>
<form th:action="@{/loginUrl}" method="post" class="form-horizontal" autocomplete="off">
  <div class="logo">
    <img class="img-fluid" alt="Responsive image" src="<c:url value='/static/img/icon-lanczos3.jpg'/>"align="top">
  </div>
  <div class="container">
    <label><i class="fa fa-user"></i> <b>Usuario</b></label>
    	<input type="text" class="input100" placeholder="Ingrese usuario" id="username" name="ssoId" required>
    <label><i class="fa fa-lock"></i>  <b>Contraseña</b> 
		   <i onmouseover="Showpass()" onmouseout="Showpass()" id="Eye" class="fa fa-eye-slash" aria-hidden="true"></i>
	</label>
    <input type="password" class="input100" placeholder="Ingrese contraseña" id="password" name="password" required>
        
    <button class="container-login100-form-btn" type="submit">Login</button>
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <span class="psw">¿Olvidó <a href="javascript:SolCamPass()">contraseña?</a></span>
  </div>
  <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
</form>
</div>
<%@include file="authfootter.jsp"%>
</body>
</html>