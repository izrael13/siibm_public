function preventDef(event) {
	event.preventDefault();
}


var ids = ""
var costoscapturados = "";
var ajustes = "";
var esquemas = "";
var cm = "";
function SumarEsp(idcaja)
{
	ids = ""
	costoscapturados = "";
	ajustes = "";
	esquemas = "";
	cm = "";

$("input[id='ChEsp']").each(function (){
	idEsp = $(this).val();
	if($(this).prop('checked'))
	{
		if(idEsp == 19)
		{		
			$("#TAjuste"+idEsp).attr("required","required");
			$("#TAjuste"+idEsp).attr("type","text");
		}
		
		if(idEsp == 9 || idEsp == 19 || idEsp == 24)
		{		
			
			$("#TMedidas"+idEsp).attr("type","text");
			$("#TMedidas"+idEsp).val("1.20 x 1");
		}
		
		if((idEsp == 4 || idEsp == 5 || idEsp == 6 || idEsp == 7 || idEsp == 20 || idEsp == 8) && (idcaja == 56 || idcaja == 8) )
		{		
			$("#TCM"+idEsp).attr("required","required");
			$("#TCM"+idEsp).attr("type","text");
		}
		else
		{
			$("#TCM"+idEsp).attr("type","hidden");
			$("#TCM"+idEsp).attr("required",false);
			$("#TCM"+idEsp).val("");
		}
		
		$("#TCosto"+idEsp).attr("required","required");
		if($("#TCosto"+idEsp).prop('type') == 'select-one')
		{
			$("#TCosto"+idEsp).css('visibility', 'visible')
		}
		else
		{
			$("#TCosto"+idEsp).attr("type","text"); 
		}
		
		ids = idEsp+"|"+ids;
		costoscapturados = ($("#TCosto"+idEsp).val() == "" ? 0 : ($("#TCosto"+idEsp).val() == null ? 0 : $("#TCosto"+idEsp).val())) +"|"+costoscapturados;
		ajustes = ($("#TAjuste"+idEsp).val() == "" ? 0 : ($("#TAjuste"+idEsp).val() == null ? 0 : $("#TAjuste"+idEsp).val())) +"|"+ajustes;
		esquemas = ($("#TEsquema"+idEsp).val() == "" ? 0 : ($("#TEsquema"+idEsp).val() == null ? 0 : $("#TEsquema"+idEsp).val())) +"|"+esquemas;
		cm = ($("#TCM"+idEsp).val() == "" ? 0 : ($("#TCM"+idEsp).val() == null ? 0 : $("#TCM"+idEsp).val())) +"|"+cm;
	}
	else
	{
		$("#TCosto"+idEsp).attr("required",false);
		
		if(idEsp == 19)
		{
			$("#TAjuste"+idEsp).attr("type","hidden");
			$("#TAjuste"+idEsp).attr("required",false);
			$("#TAjuste"+idEsp).val("");
		}
		
		if(idEsp == 9 || idEsp == 19 || idEsp == 24)
		{		
			$("#TMedidas"+idEsp).attr("type","hidden");
			$("#TMedidas"+idEsp).val("");
		}
		
		if((idEsp == 4 || idEsp == 5 || idEsp == 6 || idEsp == 7 || idEsp == 20 || idEsp == 8) && (idcaja != 56 || idcaja != 8) )
		{		
			$("#TCM"+idEsp).attr("required",false);
			$("#TCM"+idEsp).attr("type","hidden");
			$("#TCM"+idEsp).val("");
		}
		
		if($("#TCosto"+idEsp).prop('type') == 'select-one')
		{
			$("#TCosto"+idEsp).css('visibility', 'hidden')
			$("#TCosto"+idEsp).val("0");
		}
		else
		{
			$("#TCosto"+idEsp).attr("type","hidden");
			$("#TCosto"+idEsp).val("");
		}
		
	}
	
});
}

function FEmbarques(b)
{
	
	var ag = $("#CAGranel").prop('checked');
	//alert(ag);
	//$("#TFlejesAtado" ).val(ag == false ? $("#TFlejesAtado").val() : "").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	//$("#TPzasAtado" ).val(ag == false ? $("#TPzasAtado").val() : "").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TAtaCama" ).val(ag == false ? $("#TAtaCama").val() : "").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TPzasxTar" ).val(ag == false ? $("#TPzasxTar").val() : "").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TAltPallet" ).val(ag == false ? $("#TAltPallet").val() : "").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TCamasPallet" ).val(ag == false ? $("#TCamasPallet").val() : "").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TFlejesPallet" ).val(ag == false ? $("#TFlejesPallet").val() : "").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	
	$("input[id='ChEsp']").each(function ()
	{
		idEsp = $(this).val();
		
		if(idEsp == 19 || idEsp == 24 || idEsp == 9)
		{	
			if(ag){
				$(this).bind("click", preventDef, false).prop('checked',false);
				$(this).attr("onchange","");
				
				$("#TCosto"+idEsp).val("").attr("type", "hidden");
				
				if(idEsp == 19)
					$("#TAjuste"+idEsp).val("").attr("type", "hidden");
				
			}
			else
			{
				$(this).unbind('click').prop('checked');
				$(this).attr("onchange","CalcularDatos()");
			}
		}
		
	});
	
	if(b == 1)
		CalcularDatos();
}

function FColores()
{
	var ntintas = $("#SNumTintas").val();

	for(var i = 1; i <= 7; i++)
	{
		if(i <= ntintas)
		{
			$("#SColor"+i).css('visibility', 'visible');
			$("#SColor"+i).attr("required","required");
		}
		else
		{
			$("#SColor"+i).css('visibility', 'hidden');
			$("#SColor"+i).attr("required",false);
			$("#SColor"+i).val("");
		}
	}
	
}

$(document).on("keypress", "input", function (e) {//deshabilitar enter submit
    var code = e.keyCode || e.which;
    if (code == 13) {
        e.preventDefault();
        return false;
    }
});

function FAddFila()
{
	var numfilas = $("#TBodyCodBarras tr").length;
	
	 var nuevaFila   = '<tr>';
	 nuevaFila   = nuevaFila + '<td><input id="TCodBarras'+numfilas+'" name="cotizador_detalles.codigo_barra_cotizador['+numfilas+'].idcodigo" type="text" value="" onkeypress="return SinCaracteresEspeciales(event)" class="border border-primary" maxlength="50"></td>';
	 nuevaFila   = nuevaFila + '<td><input id="TCodComent'+numfilas+'" name="cotizador_detalles.codigo_barra_cotizador['+numfilas+'].observaciones" type="text" value="" onkeypress="return SinCaracteresEspeciales(event)" class="border border-primary" maxlength="100"></td>';
	 nuevaFila   = nuevaFila + '</tr>';
	
	 document.getElementById("TBodyCodBarras").insertRow(-1).innerHTML = nuevaFila;

}
function FDelFila()
{
	var table = document.getElementById("TBodyCodBarras");
	var rowCount = table.rows.length;
	table.deleteRow(rowCount -1);
}
function FDisableElemens()
{
	$("#SClientes option:not(:selected)").prop("disabled", true);
	$("#SClientesFactura option:not(:selected)").prop("disabled", true);
	$("#direcciones option:not(:selected)").prop("disabled", true);
	$("#SCajas option:not(:selected)").prop("disabled", true);
	$("#SResisBarca option:not(:selected)").prop("disabled", true);
	
	//$("#BCancel").prop('disabled',true);
	$("#BGrabar").prop('disabled',true);
	
	$("#TSimbolo").attr("readonly","readonly");				
	$("#TLargo").attr("readonly","readonly");
	$("#TAncho").attr("readonly","readonly");
	$("#TFondo").attr("readonly","readonly");
	$("#TLargoInt").attr("readonly","readonly");
	$("#TAnchoInt").attr("readonly","readonly");
	$("#TFondoInt").attr("readonly","readonly");
	$("#SResisCte option:not(:selected)").prop("disabled", true);
	$("#SCierre option:not(:selected)").prop("disabled", true);
	$("#SCierreDet option:not(:selected)").prop("disabled", true);
	$("#TPzasxjgo").attr("readonly","readonly");
	$("#TObs").attr("readonly","readonly");
	$("#TEspSup").attr("readonly","readonly");
	$("#TEspInf").attr("readonly","readonly");
	$("#TCantPedMes").attr("readonly","readonly");
	$("#SScore option:not(:selected)").prop("disabled", true);
	$("#SNumTintas option:not(:selected)").prop("disabled", true);			

	$("#TPreciObj").attr("readonly","readonly");
	$("#TPzasxTar").attr("readonly","readonly");
	$("#TCOBB").attr("readonly","readonly");
	////////////****ESPECIALIDADES****///////////////////
	var nodes = document.getElementById("DEsp").getElementsByTagName('*');
	for(var i = 0; i < nodes.length; i++)
	{				
		if(nodes[i].type == 'select-one')
			$("#"+nodes[i].id+" option:not(:selected)").prop("disabled", true);
		else
		{
			if(nodes[i].type == 'checkbox')
			{
				if(nodes[i].id != "")
					{
						nodes[i].addEventListener("click", preventDef, false);
						nodes[i].onchange = "";
					}
			}
			else
			{
				if(nodes[i].id != "")
					$("#"+nodes[i].id).prop("readonly", true);
			}						
	    		
		}
	}
	////////////****FIN ESPECIALIDADES****///////////////
	////////////****CODIGO DE BARRAS****/////////////////
	var nodescb = document.getElementById("TBodyCodBarras").getElementsByTagName('*');
	for(var i = 0; i < nodescb.length; i++)
	{
		if(nodescb[i].id != "")
		{
			if(nodescb[i].type == 'text')
				$("#"+nodescb[i].id).prop("readonly", true);
		}
	}
	$("#BAddFila").prop("disabled",true);
	$("#BDelFila").prop("disabled",true);
	////////////****FIN CODIGO DE BARRAS****//////////////
	$("#STipoReq option:not(:selected)").prop("disabled", true);
	$("#SEntrego option:not(:selected)").prop("disabled", true);
	$("#SColor1 option:not(:selected)").prop("disabled", true);
	$("#SColor2 option:not(:selected)").prop("disabled", true);
	$("#SColor3 option:not(:selected)").prop("disabled", true);
	$("#SColor4 option:not(:selected)").prop("disabled", true);
	$("#SColor5 option:not(:selected)").prop("disabled", true);
	$("#SColor6 option:not(:selected)").prop("disabled", true);
	$("#SColor7 option:not(:selected)").prop("disabled", true);
	$("#CProtecciones option:not(:selected)").prop("disabled", true);//Se hizo lista después
	$("#CIdentificador option:not(:selected)").prop("disabled", true);//Se hizo lista después
	
	$("#TNumRanuras").attr("readonly","readonly");
	$("#TFechaOC").attr("readonly","readonly");
	$("#CCejaDesp").bind("click", preventDef, false);
	$("#CSinBoceto").bind("click", preventDef, false);
	$("#STolerancia option:not(:selected)").prop("disabled", true);
	$("#SDisenio option:not(:selected)").prop("disabled", true);
	$("#CCancSust").bind("click", preventDef, false);
	$("#TTF").attr("readonly","readonly");

	$("#CEmplayado").bind("click", preventDef, false);
	$("#TVueltasEmp").attr("readonly","readonly");
	$("#CFactura").bind("click", preventDef, false);
	$("#CCertCal").bind("click", preventDef, false);
	$("#CImpOC").bind("click", preventDef, false);
	$("#CCajaSeca").bind("click", preventDef, false);
	$("#CCertFum").bind("click", preventDef, false);
	$("#CEPP").bind("click", preventDef, false);
	$("#CAGranel").bind("click", preventDef, false);
	$("#CImpFech").bind("click", preventDef, false);
	$("#CImpPed").bind("click", preventDef, false);
	$("#CTarxUni").bind("click", preventDef, false);
	$("#TAltPallet").attr("readonly","readonly");
	$("#TCamasPallet").attr("readonly","readonly");
	$("#TFlejesPallet").attr("readonly","readonly");
	$("#TFlejesAtado").attr("readonly","readonly");
	$("#TPzasAtado").attr("readonly","readonly");
	$("#TAtaCama").attr("readonly","readonly");
	$("#TFechaCancelTF").attr("readonly","readonly");
}

function FDisenio()
{
	if($("#CCancSust").prop('checked'))
	{
		$("#TTF").attr("required",true);
		$("#TFechaCancelTF").attr("required",true);
	}
	else
	{
		
		$("#TTF").attr("required",false);
		$("#TFechaCancelTF").attr("required",false);
	}
}