/**
 * 
 */
function onlyDotsAndNumbers(event,txtbox) {         
    var charCode = (event.which) ? event.which : event.keyCode
    var valor = $("input[name*=" + txtbox + "]").val(); //document.getElementById('< %=txtPrecioObj.ClientID%>').value;
    var b = true;
    if (valor.length > 0) {
        if (/^[0-9]{1,15}([.])?([0-9]{0,1})?$/.test(valor))
            b = true;
        else
            b = false;
    }
    if (b) {
        if (charCode == 46) {
            if (valor.indexOf('.') > -1 || valor.length == 0)
                return false;
                
            return true;
        }
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
    else
        return false;
}
function onlyDotsAndNumbers2(event, txtbox) {
    var charCode = (event.which) ? event.which : event.keyCode
    var valor = $("input[name*=" + txtbox + "]").val(); //document.getElementById('< %=txtPrecioObj.ClientID%>').value;
    var b = true;
    if (valor.length > 0) {
        if (/^[0-9]{1,15}([.])?([0-9]{0,3})?$/.test(valor))
            b = true;
        else
            b = false;
    }
    if (b) {
        if (charCode == 46) {
            if (valor.indexOf('.') > -1 || valor.length == 0)
                return false;

            return true;
        }
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
    else
        return false;
}
function CaracteresEspeciales() {
	var inp = String.fromCharCode(event.keyCode);
	if (/[a-zA-Z0-9-_ ]/.test(inp))
		return true;
	else
		return false;
}

function decimales() {
    if ((event.keyCode > 47 && event.keyCode < 58) || (event.keyCode == 46) || (event.keyCode == 44)) {
        return true
    }
    return false
}
	
function decimales2() {
    if ((event.keyCode > 47 && event.keyCode < 58) || (event.keyCode == 46)) {
        return true
    }
    return false
}
	
function Enteros(e) {
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla==8){
        return true;
    }
        
    // Patron de entrada, en este caso solo acepta numeros
    patron =/[0-9]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}				  

function Imprimir(div)
{	
	$('#'+div).printThis(
			{
				  debug: false,               // show the iframe for debugging
				  importCSS: true,            // import page CSS
				  importStyle: true,         // import style tags
				  printContainer: true		
			}
			);
}
function popupwindow(url, title, win, w, h) {
	var userAgent = navigator.userAgent,
    mobile = function() {
      return /\b(iPhone|iP[ao]d)/.test(userAgent) ||
        /\b(iP[ao]d)/.test(userAgent) ||
        /Android/i.test(userAgent) ||
        /Mobile/i.test(userAgent);
    },
    screenX = typeof window.screenX != 'undefined' ? window.screenX : window.screenLeft,
    screenY = typeof window.screenY != 'undefined' ? window.screenY : window.screenTop,
    outerWidth = typeof window.outerWidth != 'undefined' ? window.outerWidth : document.documentElement.clientWidth,
    outerHeight = typeof window.outerHeight != 'undefined' ? window.outerHeight : document.documentElement.clientHeight - 22,
    targetWidth = mobile() ? null : w,
    targetHeight = mobile() ? null : h,
    V = screenX < 0 ? window.screen.width + screenX : screenX,
    left = parseInt(V + (outerWidth - targetWidth) / 2, 10),
    right = parseInt(screenY + (outerHeight - targetHeight) / 2.5, 10),
    features = [];
	if (targetWidth !== null) {
	  features.push('width=' + targetWidth);
	}
	if (targetHeight !== null) {
	  features.push('height=' + targetHeight);
	}
	features.push('left=' + left);
	features.push('top=' + right);
	features.push('scrollbars=1');
	
	var newWindow = window.open(url, title, features.join(','));
	
	if (window.focus) {
	  newWindow.focus();
	}
	
	return newWindow;
} 
