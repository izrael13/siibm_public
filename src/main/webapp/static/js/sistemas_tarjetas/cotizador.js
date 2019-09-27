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
	$("#TFlejesAtado" ).val("").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TPzasAtado" ).val("").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TAtaCama" ).val("").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TPzasxTar" ).val("").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TAltPallet" ).val("").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TCamasPallet" ).val("").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	$("#TFlejesPallet" ).val("").attr("readonly",ag).removeClass().addClass(ag == true ? "border border-secondary" : "border border-primary");
	
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
