package com.websystique.springmvc.model.tarjetas.cotizador;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.websystique.springmvc.model.tarjetas.Especialidades_cotizacion;

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
											  
		List<Integer> idCajas = Arrays.asList(102,3,4,5,56,7,8); //CAJAS QEU DEBEN LLEVAR FONDO
		List<Integer> idCajas2 = Arrays.asList(43,44,46,51,52,53,54,85);//CAJAS QUE DEBEN LLEVAR ESP SUP Y ESO INF.
		
		if(!idCajas.contains(cotizador_detalles.getIdcaja_sap()) && (cotizador_detalles.getFondo() == null || cotizador_detalles.getFondo() == 0.0) )
			errors.rejectValue("cotizador_detalles.fondo", "NotNull");
		else
		{
			if(idCajas.contains(cotizador_detalles.getIdcaja_sap()) &&  (cotizador_detalles.getFondo() == null ? 0.0   : cotizador_detalles.getFondo() ) > 0.0)
				errors.rejectValue("cotizador_detalles.fondo", "Required");
		}
		
		if(cotizador_detalles.getIddetalle() > 1 && (cotizador_detalles.getPiezasxjuego() == null || cotizador_detalles.getPiezasxjuego() == 0))
		{
			errors.rejectValue("cotizador_detalles.piezasxjuego", "NotNull");
		}
		
		if(cotizador_detalles.getEspecialidades_cotizacion().size() > 0)
		{
			Supplier<Stream<Especialidades_cotizacion>> stream = () -> cotizador_detalles.getEspecialidades_cotizacion().stream().filter(a -> (a.getIdespecialidad() != null) && (a.getIdespecialidad() == 9 || a.getIdespecialidad() == 19));
			if(stream.get().count() > 0 &&  (cotizador_detalles.getPiezasxtarima() == null ? 0.0   : cotizador_detalles.getPiezasxtarima() ) == 0.0)
			{
				errors.rejectValue("cotizador_detalles.piezasxtarima", "NotNull");
			}
		}
		
		if(idCajas2.contains(cotizador_detalles.getIdcaja_sap()) && (cotizador_detalles.getEsp_sup() == null ? 0.0 : cotizador_detalles.getEsp_sup()) == 0.0) 
			errors.rejectValue("cotizador_detalles.esp_sup", "NotNull");
		else
		{
			if(!idCajas2.contains(cotizador_detalles.getIdcaja_sap()) && (cotizador_detalles.getEsp_sup() == null ? 0.0 : cotizador_detalles.getEsp_sup()) > 0.0) 
				errors.rejectValue("cotizador_detalles.esp_sup", "Required");
		}
			
		
		if(idCajas2.contains(cotizador_detalles.getIdcaja_sap()) && (cotizador_detalles.getEsp_inf() == null ? 0.0 : cotizador_detalles.getEsp_inf()) == 0.0) 
			errors.rejectValue("cotizador_detalles.esp_inf", "NotNull");
		else
		{
			if(!idCajas2.contains(cotizador_detalles.getIdcaja_sap()) && (cotizador_detalles.getEsp_inf() == null ? 0.0  : cotizador_detalles.getEsp_inf()) > 0.0)
				errors.rejectValue("cotizador_detalles.esp_inf", "Required");
		}
		
		
		
		
	}

}
