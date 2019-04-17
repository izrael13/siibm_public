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
			function SolCamPass()
			{
				popupwindow('<c:url value="/sol_cam_passs_" />','Solicitud de reset contraseña',800,800);
				//window.open('<c:url value="/sol_cam_passs_" />',null,
				//"height=800,width=1000,status=yes,toolbar=no,menubar=no,location=no");
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
	</head>

	<body>
		<div id="mainWrapper">
			<div class="login-container">
				<div class="login-card">
					<div align="center">
						<img class="img-fluid" alt="Responsive image" src="<c:url value='/static/img/BarcaLogoV.png'/>"align="top">
						<img class="img-fluid" alt="Responsive image" src="<c:url value='/static/img/Barca.png' />" align="top">
					</div>
					<div align="center" class="border-top-0 font-weight-light h4">
						<p>Sistema de Información Integral de Barca de México</p>
						<p>SIIBM</p>
					</div>
					<div class="login-form">
						<c:url var="loginUrl" value="/login" />
						<form th:action="@{/loginUrl}" method="post" class="form-horizontal" autocomplete="off">
							<c:if test="${param.error != null}">
									<div class="alert alert-danger">
									<p>Usuario y contraseña incorrectos.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>Ha cerrado sesión correctamente.</p>
								</div>
							</c:if>
							<c:if test="${param.expired != null}">
								<div class="alert alert-warning">
									<p>La sesión ha expirado.</p>
								</div>
							</c:if>
							<br>
							<div class="input-group input-sm">
								<i class="fa fa-user"></i>
								<input type="text" class="form-control" id="username" name="ssoId" placeholder="Capture usuario" required>
							</div>
							<br>
							<div class="input-group input-sm">
								<i class="fa fa-lock"></i> 
								<input type="password" class="form-control" id="password" name="password" placeholder="Capture contraseña" required>
								<div class="input-group-addon">
									<a href="">
										<i onmouseover="Showpass()" onmouseout="Showpass()" id="Eye" class="fa fa-eye-slash" aria-hidden="true"></i>
									</a>
								</div>
							</div>
							<br>
							<div class="alert alert-info h5" role="alert"><a href="javascript:SolCamPass()">¿Has olvidado tu contraseña?</a></div>
							<br>
							  <!-- <div class="input-group input-sm">
                              <div class="checkbox">
                                <label><input type="checkbox" id="rememberme" name="remember-me"> Recuérdame</label>  
                              </div>
                            </div>  -->
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
								
							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Log in">
							</div>
						</form>
					</div>
				</div>
				<%@include file="authfootter.jsp"%>
			</div>
		</div>
	</body>
</html>