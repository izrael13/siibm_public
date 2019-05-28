package com.websystique.springmvc.model.tarjetas.cotizador;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class Cotizador_detallesValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// FIXME Auto-generated method stub
		return Cotizador_detalles.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// FIXME Auto-generated method stub
		Cotizador_detalles cotizador_detalles = (Cotizador_detalles) target;
		List<Integer> idCajas = Arrays.asList(102,4,5,56,8);
		if(idCajas.contains(cotizador_detalles.getIdcaja_sap()) && (cotizador_detalles.getFondo() == null || cotizador_detalles.getFondo() == 0.0) )
		{
			errors.rejectValue("cotizador_detalles.fondo", "NotNull");
		}
		
		if(cotizador_detalles.getIddetalle() > 1 && (cotizador_detalles.getPiezasxjuego() == null || cotizador_detalles.getPiezasxjuego() == 0))
		{
			errors.rejectValue("cotizador_detalles.piezasxjuego", "NotNull");
		}
		
	}

}
