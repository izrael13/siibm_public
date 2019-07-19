function preventDef(event) {
	event.preventDefault();
}


var ids = ""
var costoscapturados = "";
var ajustes = "";
var esquemas = "";

function SumarEsp()
{
	ids = ""
	costoscapturados = "";
	ajustes = "";
	esquemas = "";

$("input[id='ChEsp']").each(function (){
	idEsp = $(this).val();
	if($(this).prop('checked'))
	{
		if(idEsp == 19)
		{		
			$("#TAjuste"+idEsp).attr("required","required");
			$("#TAjuste"+idEsp).attr("type","text");
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
