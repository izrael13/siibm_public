package com.websystique.springmvc.model.tarjetas.fabricacion;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class Tarjeta_fabricacionValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Tarjeta_fabricacion.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Tarjeta_fabricacion tarjeta = (Tarjeta_fabricacion) target;
		if(tarjeta.getBan() == 0)//Si se inserta por primera vez (Cuando se asigna diseñador)
		{
			
			if(tarjeta.getDescripcion_factura() == null || tarjeta.getDescripcion_factura() == "")
				errors.rejectValue("tarjeta_fabricacion.descripcion_factura", "Required");
			
			if(tarjeta.getFolio_tarjeta() == null || tarjeta.getFolio_tarjeta() == "")
				errors.rejectValue("tarjeta_fabricacion.folio_tarjeta", "Required");
			
		}
		else//Cuando se actualiza por parte de ingeniería
		{
			if(tarjeta.getDescripcion_factura() == null || tarjeta.getDescripcion_factura() == "")
				errors.rejectValue("tarjeta_fabricacion.descripcion_factura", "Required");
			
			if(tarjeta.getFolio_tarjeta() == null || tarjeta.getFolio_tarjeta() == "")
				errors.rejectValue("tarjeta_fabricacion.folio_tarjeta", "Required");
			
			if(tarjeta.getNum_partes() == null || tarjeta.getNum_partes() == 0)
				errors.rejectValue("tarjeta_fabricacion.num_partes", "Required");
			
			if(tarjeta.getCatalogo_maquinas_sap_vw().size() == 0)
				errors.rejectValue("tarjeta_fabricacion.catalogo_maquinas_sap_vw", "NotEmpty");
			
			if(tarjeta.getPzasxlargo() == null || tarjeta.getPzasxlargo() == 0)
				errors.rejectValue("tarjeta_fabricacion.pzasxlargo", "NotEmpty");
			
			if(tarjeta.getPzasxancho() == null || tarjeta.getPzasxancho() == 0)
				errors.rejectValue("tarjeta_fabricacion.pzasxancho", "NotEmpty");
			
			if(tarjeta.getMedidas_internas() == null || tarjeta.getMedidas_internas() == "")
				errors.rejectValue("tarjeta_fabricacion.medidas_internas", "NotEmpty");
			
			if(tarjeta.getSuaje() == null || tarjeta.getSuaje() == 0)
				errors.rejectValue("tarjeta_fabricacion.suaje", "NotEmpty");
			
			if(tarjeta.getGrabado() == null || tarjeta.getGrabado() == 0)
				errors.rejectValue("tarjeta_fabricacion.grabado", "NotEmpty");
			
		}
	}

}
