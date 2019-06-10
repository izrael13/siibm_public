<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Para evitar cahé -->
<meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache">

<!-- Imports necesarios -->
<script src="<c:url value="/static/js/bootstrap4/popper.min.js" />"></script>
<link href="<c:url value='/static/css/bootstrap4/css/bootstrap.css' />" rel="stylesheet"></link>
<script src="<c:url value="/static/js/jquery-3.3.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap4/bootstrap.js" />"></script>
<script src="<c:url value="/static/js/printThis.js" />"></script>
<script src="<c:url value="/static/js/CommonFunctions.js" />"></script>
<script src="<c:url value="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" />"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
/*Propiedades de submenus*/
.dropdown-submenu{position:relative;}
.dropdown-submenu>.dropdown-menu{
	left:200px;
	top:0px;
	font-size: small;
	width: 300px;
}
.dropdown-item:hover{color:blue;}/*Color azul para los links*/
a:hover{background-color: lightblue;}/*Color de fondo azul para los links*/
.dropdown:hover>.dropdown-menu{display:block;} /*Desplieque de meú principal (Módulos)*/
.dropdown-submenu:hover>.dropdown-menu{display:block;}/*Desplieque de submenus (reportes)*/
.dropdown-menu .dropdown-menu-right{width: 200px;font-size: smaller;}/*Propiedades menu (Módulos)*/
.fa-user-circle-o:hover{color: green;}/**/
</style>

<link rel="shortcut icon" href="<c:url value='/static/img/BarcaLogoV.png' />"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
<a class="text-dark" href="<c:url value='/principal'/>">SIIBM <i class="fa fa-user-o" aria-hidden="true"></i> ${loggedinuser}</a>
<div id = "navbarSupportedContent" class="collapse navbar-collapse">
<ul class="navbar-nav mr-auto">
	<li class="nav-item dropdown">				
		<a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-file-text" aria-hidden="true">	Reportes</i></a>
		<ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu">
			<li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-newspaper-o" aria-hidden="true"></i> Papel</a>
			    <ul class="dropdown-menu">
			    	<li><a class="dropdown-item" href = "<c:url value='/reportes/papel/consumo_kilos' />"><i class="fa fa-file-o" aria-hidden="true"></i> Consumo en kilos</a></li>
			    	<li><a class="dropdown-item" href = "<c:url value='/reportes/papel/consumo_papel' />"><i class="fa fa-file-o" aria-hidden="true"></i> Consumo papel acumulado por semana</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/papel/consumo_papel_ult_sem' />"><i class="fa fa-file-o" aria-hidden="true"></i> Consumo papel última semana</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/papel/consumo_papel_mes' />"><i class="fa fa-file-o" aria-hidden="true"></i> Consumo papel mes</a></li>
			  	</ul>
			</li>
			<li class="dropdown-submenu dropdown-menu-right">
				<a class="nav-link dropdown-toggle"><i class="fa fa-product-hunt" aria-hidden="true"></i> Producción</a>
				<ul class="dropdown-menu">
				    <li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/golpes_maquina_mes__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Golpes por máquina producidos</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/flautaPromSem__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Velocidad promedio semana corrugadora</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/paros_maq_dia__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Paros máquina por día</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/paros_concepto_d__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Paros por concepto diarios</a></li>
				</ul>
			</li>
			<li class="dropdown-submenu dropdown-menu-right">
		  		<a class="nav-link dropdown-toggle"><i class="fa fa-sellsy" aria-hidden="true"></i>	Ventas</a>
		  		<ul class="dropdown-menu">
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/golpes_kilos_maquina__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Golpes/Kilos por máquina captados</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/peso_dia_d__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Embarque diario</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/viajes_mes_ciudad' />"><i class="fa fa-file-o" aria-hidden="true"></i> Viajes por ciudad</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/inven_alm____' />"><i class="fa fa-file-o" aria-hidden="true"></i> Inventario almacen</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/golpes_pend_fab_' />"><i class="fa fa-file-o" aria-hidden="true"></i> Captación</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/golpes_pend_fab_2' />"><i class="fa fa-file-o" aria-hidden="true"></i> Golpes pendientes de fabricar</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/todos_pedidos___' />"><i class="fa fa-file-o" aria-hidden="true"></i> Todos pedidos</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/media_pedidos_cte1' />"><i class="fa fa-file-o" aria-hidden="true"></i> Media pedidos por cliente</a></li>
			    </ul>
		  	</li>	
		  	<li class="dropdown-submenu dropdown-menu-right">
			    <a class="nav-link dropdown-toggle"><i class="fa fa-newspaper-o" aria-hidden="true"></i> Ingeniería</a>
			    <ul class="dropdown-menu">
			    	<li><a class="dropdown-item" href = "<c:url value='/reportes/ingenieria/amortizacion_herram___' />"><i class="fa fa-file-o" aria-hidden="true"></i> Amortización</a></li>
			    	<li><a class="dropdown-item" href = "<c:url value='/reportes/ingenieria/todos_pedidos_ing_' />"><i class="fa fa-file-o" aria-hidden="true"></i> Todos pedidos</a></li>
			  	</ul>
			</li>
			<li class="dropdown-submenu dropdown-menu-right">				
				<a class="nav-link dropdown-toggle"><i class="fa fa-money" aria-hidden="true"></i> Cobranza</a>
				<ul class="dropdown-menu">
			  		<li><a class="dropdown-item" href = "<c:url value='/reportes/cobranza/detalle_cobranza' />"><i class="fa fa-file-o" aria-hidden="true"></i>	Detalle de cobranza</a></li>
			  	</ul>
			</li>	  	
		</ul>
  	</li>
	 <sec:authorize access="hasRole('ADMIN') or hasRole('VENTAS')">
	 <li class="nav-item dropdown"><a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-industry" aria-hidden="true"> Ventas</i></a>
	 	<ul class="dropdown-menu dropdown-menu-right">
	   		<li class="dropdown-submenu dropdown-menu-right">
	   			<a class="dropdown-item" href = "<c:url value='/ventas/pronosticoscte____' />"><i class="fa fa-lightbulb-o" aria-hidden="true"></i>	Pronósticos</a>
	  		</li>
	   </ul>
	 </li>
	 </sec:authorize>
	 
	 <li class="nav-item dropdown">				
		<a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-id-card" aria-hidden="true">	Sistema de tarjetas</i></a>
		<ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu">
			<li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-user-circle" aria-hidden="true"></i> Prospectos</a>
			    <ul class="dropdown-menu">
			    	<li><a class="dropdown-item" href = "<c:url value='/ventas/tarjetas/prospectos/prospectosabc' />?id=0"><i class="fa fa-user-plus" aria-hidden="true"></i> Registro prospectos y actividades</a></li>
			    	<li><a class="dropdown-item" href = "<c:url value='/ventas/tarjetas/prospectos/prospectosgerenteventas'/>"><i class="fa fa-male" aria-hidden="true"></i> Prospectos gerente de ventas</a></li>
			    </ul>
			 </li>
			 <li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-dot-circle-o" aria-hidden="true"></i> Cotizador</a>
			    <ul class="dropdown-menu">
			    	<li><a class="dropdown-item" href = "<c:url value='/ventas/tarjetas/cotizador/cotizadorabc'/>?id=0&iddet=0"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Registro cotizaciones</a></li>
			    	<li><a class="dropdown-item" href = "<c:url value='/ventas/tarjetas/cotizador/cotizadorabc'/>?id=0&iddet=0"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Autorización de cotizaciones Vtas</a></li>
			    </ul>
			 </li>
			 <li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-check-square-o" aria-hidden="true"></i> Requerimientos</a>
			    <ul class="dropdown-menu">
			    	<li><a class="dropdown-item" href = "<c:url value='/ventas/tarjetas/requerimientos/requerimientoabc'/>"><i class="fa fa-check-circle-o" aria-hidden="true"></i> Registro requerimientos</a></li>
			    </ul>
			 </li>
		</ul>
	</li>
</ul>
<ul class="navbar-nav">
	<li class="nav-item dropdown"><a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-cog" aria-hidden="true"> Opciones</i></a>
		<ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu">
			<sec:authorize access="hasRole('ADMIN')">
				<li class="dropdown-submenu dropdown-menu-right"><a class="dropdown-item" href="<c:url value='/list' />"><i class="fa fa-users" aria-hidden="true"></i> Lista de usuarios (admin)</a></li>
				<li class="dropdown-submenu dropdown-menu-right"><a class="dropdown-item" href="<c:url value='/perfiles/list' />"><i class="fa fa-user" aria-hidden="true"></i> Perfiles (admin)</a></li>
				<li class="dropdown-submenu dropdown-menu-right"><a class="dropdown-item" href="<c:url value='/permisos_perfiles/list' />"><i class="fa fa-universal-access" aria-hidden="true"></i> Permisos a perfiles (admin)</a></li>
			 </sec:authorize>
			 
			<li class="dropdown-submenu dropdown-menu-right"><a class="dropdown-item" href="<c:url value='/edit_data_user' />"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Editar datos de usuario</a></li>
			<li class="dropdown-submenu dropdown-menu-right"><a class="dropdown-item" href="<c:url value='/cambio_p__' />"><i class="fa fa-exchange" aria-hidden="true"></i> Cambiar contraseña</a></li>
			<li class="dropdown-submenu dropdown-menu-right"><a class="dropdown-item" href="<c:url value='/logout' />"><i class="fa fa-minus-square" aria-hidden="true"></i> Cerrar Sesión</a></li>
			 <!-- <li class="dropdown-submenu dropdown-menu-right">
			    <a class="dropdown-item" href="<c:url value='/helloReport1' />">
			    	<i class="fa fa-minus-square" aria-hidden="true"></i>
			    	Rerpot 
			    </a>
			 </li> -->
		</ul>
	</li>
</ul>
</div>

</nav>