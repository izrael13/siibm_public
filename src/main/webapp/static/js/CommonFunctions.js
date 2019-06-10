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
function filterFloat(evt,input){
    // Backspace = 8, Enter = 13, ‘0′ = 48, ‘9′ = 57, ‘.’ = 46, ‘-’ = 43
    var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if(key >= 48 && key <= 57){
        if(filter(tempValue)=== false){
            return false;
        }else{       
            return true;
        }
    }else{
          if(key == 8 || key == 13 || key == 0) {     
              return true;              
          }else if(key == 46){
                if(filter(tempValue)=== false){
                    return false;
                }else{       
                    return true;
                }
          }else{
              return false;
          }
    }
}
function filter(__val__){
    var preg = /^([0-9]){1,5}(\.?[0-9]{0,2})$/;  ///^\d{1,2}(\.\d{1,2})?$/
    var np = __val__.split(".");
    if(np[0].length <= 5)
    {
	    if(preg.test(__val__) === true){
	        return true;
	    }else{
	       return false;
	    }
    }
    else
    	return false;
    
}

function filterFloat1(evt,input){
    // Backspace = 8, Enter = 13, ‘0′ = 48, ‘9′ = 57, ‘.’ = 46, ‘-’ = 43
    var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if(key >= 48 && key <= 57){
        if(filter1(tempValue)=== false){
            return false;
        }else{       
            return true;
        }
    }else{
          if(key == 8 || key == 13 || key == 0) {     
              return true;              
          }else if(key == 46){
                if(filter1(tempValue)=== false){
                    return false;
                }else{       
                    return true;
                }
          }else{
              return false;
          }
    }
}
function filter1(__val__){
    var preg = /^([0-9]){1,5}(\.?[0-9]{0,1})$/;  ///^\d{1,2}(\.\d{1,2})?$/
    var np = __val__.split(".");
    if(np[0].length <= 5)
    {
	    if(preg.test(__val__) === true){
	        return true;
	    }else{
	       return false;
	    }
    }
    else
    	return false;
    
}

function filterFloat2(evt,input){
    // Backspace = 8, Enter = 13, ‘0′ = 48, ‘9′ = 57, ‘.’ = 46, ‘-’ = 43
    var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if(key >= 48 && key <= 57){
        if(filter2(tempValue)=== false){
            return false;
        }else{       
            return true;
        }
    }else{
          if(key == 8 || key == 13 || key == 0) {     
              return true;              
          }else if(key == 46){
                if(filter2(tempValue)=== false){
                    return false;
                }else{       
                    return true;
                }
          }else{
              return false;
          }
    }
}
function filter2(__val__){
    var preg = /^([0-9]){1,5}(\.?[02468]{0,1})$/;  ///^\d{1,2}(\.\d{1,2})?$/
    var np = __val__.split(".");
    if(np[0].length <= 5)
    {
	    if(preg.test(__val__) === true){
	        return true;
	    }else{
	       return false;
	    }
    }
    else
    	return false;
    
}

function SinCaracteresEspeciales(e) {//Con espacios
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla == 8) {
        return true;
    }

    // Patron de entrada, en este caso solo acepta numeros y letras
    patron = /[A-Za-z0-9-\.\-_@ ]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}
function SinCaracteresEspeciales2(e) { //Sin espacios
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla == 8) {
        return true;
    }

    // Patron de entrada, en este caso solo acepta numeros y letras
    patron = /[A-Za-z0-9-\.\-_@]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
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
	var msie = navigator.userAgent.indexOf('MSIE '); // IE 10 or older
    var trident = navigator.userAgent.indexOf('Trident/'); //IE 11
    if(msie > 0 || trident > 0)
    {
    	var newwindow = window.open(url,title,'toolbar=0,scrollbars=2,location=0,statusbar=0,menubar=0,resizable=2,width=1000,height=800');
    	if (window.focus) {
    		newwindow.focus();
  		}
    	return newwindow;
    }
    else
    {
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
		features.push('scrollbars=2');
		features.push('resizable=1');
		
		var newWindow = window.open(url, title, features.join(','));
		
		if (window.focus) {
		  newWindow.focus();
		}
		
		return newWindow;
    }
} 
