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
<script src="<c:url value="/static/DataTables/datatables.min.js" />"></script>
<link href="<c:url value='/static/font-awesome-4.7.0/css/font-awesome.css' />" rel="stylesheet"></link>

<script src="<c:url value="/static/js/moment.js" />"></script>
<script src="<c:url value="/static/js/tempusdominus-bootstrap-4.js" />"></script>

<script src="<c:url value="/static/js/selectize.js" />"></script>
<link href="<c:url value='/static/css/selectize.css' />" rel="stylesheet"></link>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
/*Propiedades de submenus*/
.dropdown-submenu{position:relative;}
.dropdown-submenu>.dropdown-menu{
	left:200px;
	top:0px;
	font-size: small;
	width: 350px;
}
.dropdown-item:hover{color:blue;}/*Color azul para los links*/
a:hover{background-color: lightblue;}/*Color de fondo azul para los links*/
.dropdown:hover>.dropdown-menu{display:block;} /*Desplieque de menú principal (Módulos)*/
.dropdown-submenu:hover>.dropdown-menu{display:block;}/*Desplieque de submenus (reportes)*/
.dropdown-menu .dropdown-menu-right{width: 220px;font-size: smaller;}/*Propiedades menu (Módulos)*/
.fa-user-circle-o:hover{color: green;}/**/

</style>

<link rel="shortcut icon" href="<c:url value='/static/img/BarcaLogoV.png' />"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
<img class="rounded img-fluid" alt="Responsive image" src="<c:url value='/static/img/BarcaLogoM.png'/>" align="top">&nbsp;<a class="text-dark" href="<c:url value='/principal'/>"> SIIBM ${loggedinuser}</a>
<div id = "navbarSupportedContent" class="collapse navbar-collapse">
<ul class="navbar-nav mr-auto">
	<li class="nav-item dropdown"><a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-file-text" aria-hidden="true">	Reportes</i></a>
		<ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu">
			<sec:authorize access="hasRole('ADMIN') or hasRole('MATERIAPRIMA')">
			<li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-newspaper-o" aria-hidden="true"></i> Papel</a>
			    <ul class="dropdown-menu">
			    	<li><a class="dropdown-item" href = "<c:url value='/reportes/papel/consumo_kilos' />"><i class="fa fa-file-o" aria-hidden="true"></i> Consumo en kilos</a></li>
			    	<li><a class="dropdown-item" href = "<c:url value='/reportes/papel/consumo_papel' />"><i class="fa fa-file-o" aria-hidden="true"></i> Consumo papel acumulado por semana</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/papel/consumo_papel_ult_sem' />"><i class="fa fa-file-o" aria-hidden="true"></i> Consumo papel última semana</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/papel/consumo_papel_mes' />"><i class="fa fa-file-o" aria-hidden="true"></i> Consumo papel mes</a></li>
			  	</ul>
			</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN') or hasRole('PRODUCCION')">
			<li class="dropdown-submenu dropdown-menu-right">
				<a class="nav-link dropdown-toggle"><i class="fa fa-product-hunt" aria-hidden="true"></i> Producción</a>
				<ul class="dropdown-menu">
				    <li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/golpes_maquina_mes__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Golpes por máquina producidos</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/flautaPromSem__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Velocidad promedio semana corrugadora</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/paros_maq_dia__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Paros máquina por día</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/paros_concepto_d__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Paros por concepto diarios</a></li>
					<li><a class="dropdown-item" href = "<c:url value='/reportes/produccion/PedidosConRetraso_' />"><i class="fa fa-file-o" aria-hidden="true"></i> Pedidos con Retraso</a></li>
				</ul>
			</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN') or hasRole('VENTAS')">
			<li class="dropdown-submenu dropdown-menu-right">
		  		<a class="nav-link dropdown-toggle"><i class="fa fa-sellsy" aria-hidden="true"></i>	Ventas</a>
		  		<ul class="dropdown-menu">
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/golpes_kilos_maquina__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Golpes/Kilos por máquina captados</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/viajes_mes_ciudad' />"><i class="fa fa-file-o" aria-hidden="true"></i> Viajes por ciudad</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/golpes_pend_fab_2' />"><i class="fa fa-file-o" aria-hidden="true"></i> Golpes pendientes de fabricar</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/todos_pedidos___' />"><i class="fa fa-file-o" aria-hidden="true"></i> Todos pedidos</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/ventas/media_pedidos_cte1' />"><i class="fa fa-file-o" aria-hidden="true"></i> Media pedidos por cliente</a></li>
			    </ul>
		  	</li>
		  	</sec:authorize>
		  	<sec:authorize access="hasRole('ADMIN') or hasRole('VENDEDOR')">
		  	<li class="dropdown-submenu dropdown-menu-right">
		  		<a class="nav-link dropdown-toggle"><i class="fa fa-sellsy" aria-hidden="true"></i>	Vendedores</a>
		  		<ul class="dropdown-menu">
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/golpes_pend_fab_' />"><i class="fa fa-file-o" aria-hidden="true"></i> Captación</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/inven_alm____' />"><i class="fa fa-file-o" aria-hidden="true"></i> Inventario almacen</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/desempeniomesvend' />"><i class="fa fa-file-o" aria-hidden="true"></i> Desempeño mensual por vendedor</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/desempeniomesxcte' />"><i class="fa fa-file-o" aria-hidden="true"></i> Desempeño mensual por cliente</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/desempeniomesxprod' />"><i class="fa fa-file-o" aria-hidden="true"></i> Desempeño mensual por producto</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/peso_dia_d__' />"><i class="fa fa-file-o" aria-hidden="true"></i> Embarque diario</a></li>
		    		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/embarquediariodetalle' />"><i class="fa fa-file-o" aria-hidden="true"></i> Embarque diario detalle</a></li>
		    	</ul>
		    </li>
		    </sec:authorize>
		    <sec:authorize access="hasRole('ADMIN') or hasRole('INGENIERIA')">
		  	<li class="dropdown-submenu dropdown-menu-right">
			    <a class="nav-link dropdown-toggle"><i class="fa fa-newspaper-o" aria-hidden="true"></i> Ingeniería</a>
			    <ul class="dropdown-menu">
			    	<li><a class="dropdown-item" href = "<c:url value='/reportes/ingenieria/amortizacion_herram___' />"><i class="fa fa-file-o" aria-hidden="true"></i> Amortización</a></li>
			    	<li><a class="dropdown-item" href = "<c:url value='/reportes/ingenieria/todos_pedidos_ing_' />"><i class="fa fa-file-o" aria-hidden="true"></i> Todos pedidos</a></li>
			  	</ul>
			</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN') or hasRole('VENDEDOR')">
			<li class="dropdown-submenu dropdown-menu-right">				
				<a class="nav-link dropdown-toggle"><i class="fa fa-money" aria-hidden="true"></i> Cobranza</a>
				<ul class="dropdown-menu">
			  		<li><a class="dropdown-item" href = "<c:url value='/reportes/cobranza/detalle_cobranza' />"><i class="fa fa-file-o" aria-hidden="true"></i>	Detalle de cobranza</a></li>
			  	</ul>
			</li>
			</sec:authorize>
		</ul>
  	</li>
	 <sec:authorize access="hasRole('ADMIN') or hasRole('VENTAS')">
	 <li class="nav-item dropdown"><a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-industry" aria-hidden="true"> Ventas</i></a>
	 	<ul class="dropdown-menu dropdown-menu-right">
	   		<li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-line-chart" aria-hidden="true"></i> Pronósticos</a>
			    <ul class="dropdown-menu">
			    		<li><a class="dropdown-item" href = "<c:url value='/qlikview/ventas/pronosticoscte____' />"><i class="fa fa-cloud-upload" aria-hidden="true"></i> Subir pronóstico</a></li>
			    		<li><a class="dropdown-item" href = "<c:url value='/qlikview/ventas/comparativo' />"><i class="fa fa-area-chart" aria-hidden="true"></i> Comparativo</a></li>
			    </ul>
			 </li>
	   </ul>
	 </li>
	 </sec:authorize>
	 
	 <sec:authorize access="hasRole('ADMIN') or hasRole('EMPACADOR')">
	 <li class="nav-item dropdown"><a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-money" aria-hidden="true"> Costos</i></a>
	 	<ul class="dropdown-menu dropdown-menu-right">
	   		<li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-inbox" aria-hidden="true"></i> Control merma</a>
			    <ul class="dropdown-menu">
			    		<li><a class="dropdown-item" href = "<c:url value='/costos/controlpesomerma/controlmermaabc' />"><i class="fa fa-balance-scale" aria-hidden="true"></i> Control de peso</a></li>
			    		<li><a class="dropdown-item" href = "<c:url value='/costos/controlpesomerma/historialcontrolmermaabc' />"><i class="fa fa-balance-scale" aria-hidden="true"></i> Historial control de peso</a></li>
			    </ul>
			 </li>
	   </ul>
	 </li>
	 </sec:authorize>
	 
	 <li class="nav-item dropdown">				
		<a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-id-card" aria-hidden="true"> Sistema de tarjetas</i></a>
		<ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu">
			<li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-user-circle" aria-hidden="true"></i> Prospectos</a>
			    <ul class="dropdown-menu">
			    	<sec:authorize access="hasRole('ADMIN') or hasRole('VENDEDOR')">
			    		<li><a class="dropdown-item" href = "<c:url value='/prospectos/vendedor/prospectosabc' />?id=0"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Registro prospectos y actividades</a></li>
			    	</sec:authorize>
			    	<sec:authorize access="hasRole('ADMIN') or hasRole('VENTAS')">
			    		<li><a class="dropdown-item" href = "<c:url value='/prospectos/ventas/prospectosgerenteventas'/>"><i class="fa fa-male" aria-hidden="true"></i> Prospectos gerente de ventas</a></li>
			    	</sec:authorize>
			    </ul>
			 </li>
			 <li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-dot-circle-o" aria-hidden="true"></i> Cotizador/Requerimiento</a>
			    <ul class="dropdown-menu">
			    	<sec:authorize access="hasRole('ADMIN') or hasRole('VENDEDOR')">
			    		<li><a class="dropdown-item" href = "<c:url value='/cotizador/vendedor/cotizadorabc'/>"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Registro Cotizaciones/Requerimientos/Muestras</a></li>
			    		<li><a class="dropdown-item" href = "<c:url value='/cotizador/vendedor/arrastresabc'/>"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Registro Arrastres</a></li>
			    	</sec:authorize>
			    	<sec:authorize access="hasRole('ADMIN') or hasRole('VENTAS')">
			    		<li><a class="dropdown-item" href = "<c:url value='/cotizador/ventas/autorizacion_cotizacion_vtas'/>"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Autorización de cotizaciones Ventas</a></li>
			    	</sec:authorize>
			    	<sec:authorize access="hasRole('ADMIN') or hasRole('PROGRAMACION')">
			    		<li><a class="dropdown-item" href = "<c:url value='/cotizador/programacion/autorizacion_cotizacion_prog'/>"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Autorización de requerimientos Programación</a></li>
			    	</sec:authorize>
			    	<sec:authorize access="hasRole('ADMIN') or hasRole('INGENIERIA')">
			    		<li><a class="dropdown-item" href = "<c:url value='/cotizador/ingenieria/requerimientoabc'/>"><i class="fa fa-check-circle-o" aria-hidden="true"></i> Imprimir/Asignar diseñador requerimientos</a></li>
			    		<li><a class="dropdown-item" href = "<c:url value='/cotizador/ingenieria/seguimiento_arrastres_muestras'/>"><i class="fa fa-check-circle-o" aria-hidden="true"></i> Seguimiento arrastres/muestras</a></li>
			    	</sec:authorize>
			    	<sec:authorize access="hasRole('ADMIN') or hasRole('ARRASTRE')">
				    	<li><a class="dropdown-item" href = "<c:url value='/cotizador/arrastres/asignar_arrastres'/>"><i class="fa fa-check-circle-o" aria-hidden="true"></i> Asignar arrastres</a></li>
				    	<li><a class="dropdown-item" href = "<c:url value='/cotizador/arrastres/liberar_arrastres'/>"><i class="fa fa-lightbulb-o" aria-hidden="true"></i> Liberar arrastres</a></li>
			    	</sec:authorize>
			    	<sec:authorize access="hasRole('ADMIN') or hasRole('MUESTRISTA')">
				    	<li><a class="dropdown-item" href = "<c:url value='/cotizador/muestras/asignar_muestras'/>"><i class="fa fa-check-circle-o" aria-hidden="true"></i> Asignar muestras</a></li>
				    	<li><a class="dropdown-item" href = "<c:url value='/cotizador/muestras/liberar_muestras'/>"><i class="fa fa-lightbulb-o" aria-hidden="true"></i> Liberar muestras</a></li>
			    	</sec:authorize>
			    </ul>
			 </li>
			 <li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-file-code-o" aria-hidden="true"></i> Tarjetas de fabricación</a>
			 	<ul class="dropdown-menu">
			 		<sec:authorize access="hasRole('ADMIN') or hasRole('INGENIERIA')">
			 			<li><a class="dropdown-item" href = "<c:url value='/tarjeta/ingenieria/tarjeta_fabricacion'/>"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Registro tarjetas</a></li>
			 		</sec:authorize>
			 		<sec:authorize access="hasRole('ADMIN') or hasRole('CALIDAD')">
			 			<li><a class="dropdown-item" href = "<c:url value='/tarjeta/calidad/tarjeta_aut_calidad'/>"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Autorización de tarjetas Calidad</a></li>
			 		</sec:authorize>
			 		<sec:authorize access="hasRole('ADMIN') or hasRole('PRODUCCION')">
			 			<li><a class="dropdown-item" href = "<c:url value='/tarjeta/produccion/tarjeta_aut_produccion'/>"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Autorización de tarjetas Producción</a></li>
			 		</sec:authorize>
			 		<sec:authorize access="hasRole('ADMIN') or hasRole('INGENIERIA')">
				 		<li><a class="dropdown-item" href = "<c:url value='/tarjeta/ingenieria/tarjeta_aut_ingenieria'/>"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Autorización de tarjetas Ingeniería</a></li>
				 		<li><a class="dropdown-item" href = "<c:url value='/tarjeta/ingenieria/tarjetas_seguimiento'/>"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Tarjetas Seguimiento</a></li>
			 		</sec:authorize>
			 		<sec:authorize access="hasRole('ADMIN') or hasRole('CLIENTE')">
			 			<li><a class="dropdown-item" href = "<c:url value='/tarjeta/cliente/tarjeta_aut_cliente'/>"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Autorización de tarjetas Cliente</a></li>
			 		</sec:authorize>
			 	</ul>
			 </li>
			 <sec:authorize access="hasRole('ADMIN') or hasRole('INGENIERIA')">
			 <li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-inbox" aria-hidden="true"></i> Catálogos</a>
			    <ul class="dropdown-menu">
			    	<li><a class="dropdown-item" href = "<c:url value='/herramentales/ingenieria/herramentalesabc'/>"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Herramentales</a></li>
			    	<li><a class="dropdown-item" href = "<c:url value='/colores/ingenieria/coloresabc'/>"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Colores</a></li>
			    	<li><a class="dropdown-item" href = "<c:url value='/sellos/ingenieria/sellosabc'/>"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Sellos</a></li>
			    </ul>
			 </li>
			 </sec:authorize>
			 <sec:authorize access="hasRole('ADMIN') or hasRole('PROGRAMACION')">
			 <li class="dropdown-submenu dropdown-menu-right"><a class="nav-link dropdown-toggle"><i class="fa fa-file-powerpoint-o" aria-hidden="true"></i> Programas</a>
			    <ul class="dropdown-menu">
			    		<li><a class="dropdown-item" href = "<c:url value='/programas/programacion/programacionabc'/>"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Registro de programas</a></li>
			    </ul>
			 </li>
			 </sec:authorize>
		</ul>		
	</li>
	
	<sec:authorize access="hasRole('DIRECCION') or hasRole('ADMIN')">
	<ul class="navbar-nav">
		<li class="nav-item dropdown"><a class="dropdown-item" href="" data-toggle="dropdown"><i class="fa fa-square-o" aria-hidden="true"> Dirección</i></a>
			<ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu">
		 		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/golpes_pend_fab_' />"><i class="fa fa-file-o" aria-hidden="true"></i> Captación</a></li>
		 		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/desempeniomesvend' />"><i class="fa fa-file-o" aria-hidden="true"></i> Desempeño mensual por vendedor</a></li>
	    		<li><a class="dropdown-item" href = "<c:url value='/reportes/vendedores/desempeniomesxcte' />"><i class="fa fa-file-o" aria-hidden="true"></i> Desempeño mensual por cliente</a></li>
	    		<li><a class="dropdown-item" href = "<c:url value='/qlikview/ventas/comparativo' />"><i class="fa fa-area-chart" aria-hidden="true"></i> Comparativo</a></li>
			 </ul>
		 </li>
	</ul>
	</sec:authorize>
	
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
		</ul>
	</li>
</ul>
</div>

</nav>