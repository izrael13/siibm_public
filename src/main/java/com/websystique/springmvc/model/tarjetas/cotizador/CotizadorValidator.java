package com.websystique.springmvc.model.tarjetas.cotizador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CotizadorValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Cotizador.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cotizador cot = (Cotizador) target;
		
		//GENERALES
		if(cot.getLinenum_dir_entrega() < 0)
			errors.rejectValue("cotizador.linenum_dir_entrega", "Range");
		
		if(cot.getIdtiporequerimiento() == 0) //DISEÑO
		{
			if(cot.getCosto_flete() == null)
				errors.rejectValue("cotizador.costo_flete", "NotNull");
			else
			{
				if(cot.getCosto_flete() < 1)
					errors.rejectValue("cotizador.costo_flete", "Range");
			}
			if((cot.getCardcode_factura() == null ? "" : cot.getCardcode_factura()).length() < 2)
				errors.rejectValue("cotizador.cardcode_factura", "Size");
			
		}
					
	}

}
