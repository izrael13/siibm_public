package com.websystique.springmvc.model.tarjetas.cotizador;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

//import javax.validation.constraints.NotNull;

//import org.hibernate.validator.constraints.Range;
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
		
		if(cotizador_detalles.getIdtiporequerimiento() == 0) //DISEÑO
		{			
			if((cotizador_detalles.getIdcaja_sap() == null ? 0 : cotizador_detalles.getIdcaja_sap()) < 1)
				errors.rejectValue("cotizador_detalles.idcaja_sap", "Range");
			
			if(!idCajas.contains(cotizador_detalles.getIdcaja_sap()) && (cotizador_detalles.getFondo() == null || cotizador_detalles.getFondo() == 0.0) )
				errors.rejectValue("cotizador_detalles.fondo", "NotNull");
			else
			{
				if(idCajas.contains(cotizador_detalles.getIdcaja_sap()) &&  (cotizador_detalles.getFondo() == null ? 0.0   : cotizador_detalles.getFondo() ) > 0.0)
					errors.rejectValue("cotizador_detalles.fondo", "Required");
			}
			
			if((cotizador_detalles.getIddetalle() == null ? 0 : cotizador_detalles.getIddetalle()) > 1 && (cotizador_detalles.getPiezasxjuego() == null || cotizador_detalles.getPiezasxjuego() == 0))
			{
				errors.rejectValue("cotizador_detalles.piezasxjuego", "NotNull");
			}
			
			if(cotizador_detalles.getEspecialidades_cotizacion().size() > 0)
			{
				Supplier<Stream<Especialidades_cotizacion>> stream = () -> cotizador_detalles.getEspecialidades_cotizacion().stream().filter(a -> (a.getIdespecialidad() != null) && (a.getIdespecialidad() == 9 || a.getIdespecialidad() == 19 || a.getIdespecialidad() == 24));
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
			
			if(cotizador_detalles.getIdcaja_sap() == 7 && (cotizador_detalles.getNum_raturas() == null || cotizador_detalles.getNum_raturas() <= 0)) //Si es  SEPARADOR debe llevar número de ranuras.
			{
				errors.rejectValue("cotizador_detalles.num_raturas", "Required");
			}
			
			if((cotizador_detalles.getSimbolo() == null ? "" : cotizador_detalles.getSimbolo()) == "")
				errors.rejectValue("cotizador_detalles.simbolo", "NotBlank");			
			
			if((cotizador_detalles.getCierre() == null ? "" : cotizador_detalles.getCierre()).length() < 1)
				errors.rejectValue("cotizador_detalles.cierre", "NotBlank");
			
			if((cotizador_detalles.getCierre_detalle() == null ? "" : cotizador_detalles.getCierre_detalle()).length() < 1)
				errors.rejectValue("cotizador_detalles.cierre_detalle", "NotBlank");
			
			if((cotizador_detalles.getMedida_lamina() == null ? "" : cotizador_detalles.getMedida_lamina()).length() < 1)
				errors.rejectValue("cotizador_detalles.medida_lamina", "NotBlank");
			
			////////////////
			if((cotizador_detalles.getLargo() == null ? 0 : cotizador_detalles.getLargo()) < 1)
				errors.rejectValue("cotizador_detalles.largo", "Range");
			if((cotizador_detalles.getAncho() == null ? 0 : cotizador_detalles.getAncho()) < 1)
				errors.rejectValue("cotizador_detalles.ancho", "Range");
			if((cotizador_detalles.getResistencia_cte() == null ? 0 : cotizador_detalles.getResistencia_cte()) < 1)
				errors.rejectValue("cotizador_detalles.resistencia_cte", "Range");
			if((cotizador_detalles.getIdresistencia_barca() == null ? 0 : cotizador_detalles.getIdresistencia_barca()) < 1)
				errors.rejectValue("cotizador_detalles.idresistencia_barca", "Range");
			if((cotizador_detalles.getPreciom2resistencia() == null ? 0 : cotizador_detalles.getPreciom2resistencia()) < 1)
				errors.rejectValue("cotizador_detalles.preciom2resistencia", "Range");
			if((cotizador_detalles.getPiezasxjuego() == null ? 0 : cotizador_detalles.getPiezasxjuego()) < 1)
				errors.rejectValue("cotizador_detalles.piezasxjuego", "Range");
			if((cotizador_detalles.getCantidad_pedido_mes() == null ? 0 : cotizador_detalles.getCantidad_pedido_mes()) < 1)
				errors.rejectValue("cotizador_detalles.cantidad_pedido_mes", "Range");
			if((cotizador_detalles.getPrecio_objetivo() == null ? 0 : cotizador_detalles.getPrecio_objetivo()) < 1)
				errors.rejectValue("cotizador_detalles.precio_objetivo", "Range");
			if((cotizador_detalles.getScore() == null ? 0 : cotizador_detalles.getScore()) < 0)
				errors.rejectValue("cotizador_detalles.score", "Range");
			if((cotizador_detalles.getNum_tintas() == null ? 0 : cotizador_detalles.getNum_tintas()) < 0)
				errors.rejectValue("cotizador_detalles.num_tintas", "Range");
			if((cotizador_detalles.getArea_unitaria() == null ? 0 : cotizador_detalles.getArea_unitaria()) < 0)
				errors.rejectValue("cotizador_detalles.area_unitaria", "Range");
			if((cotizador_detalles.getM2() == null ? 0 : cotizador_detalles.getM2()) < 1)
				errors.rejectValue("cotizador_detalles.m2", "Range");
			if((cotizador_detalles.getKg() == null ? 0 : cotizador_detalles.getKg()) < 1)
				errors.rejectValue("cotizador_detalles.kg", "Range");
			if((cotizador_detalles.getPeso_teorico() == null ? 0 : cotizador_detalles.getPeso_teorico()) < 0)
				errors.rejectValue("cotizador_detalles.peso_teorico", "Range");
			if((cotizador_detalles.getPrecio_neto() == null ? 0 : cotizador_detalles.getPrecio_neto()) < 1)
				errors.rejectValue("cotizador_detalles.precio_neto", "Range");
			if((cotizador_detalles.getPrecio_sugerido() == null ? 0 : cotizador_detalles.getPrecio_sugerido()) < 1)
				errors.rejectValue("cotizador_detalles.precio_sugerido", "Range");
			if((cotizador_detalles.getCosto_papel() == null ? 0 : cotizador_detalles.getCosto_papel()) < 1)
				errors.rejectValue("cotizador_detalles.costo_papel", "Range");
			if((cotizador_detalles.getPorcentaje_comision() == null ? 0 : cotizador_detalles.getPorcentaje_comision()) < 0)
				errors.rejectValue("cotizador_detalles.porcentaje_comision", "Range");
			if((cotizador_detalles.getDescuento_vendedor() == null ? 0 : cotizador_detalles.getDescuento_vendedor()) < 1)
				errors.rejectValue("cotizador_detalles.descuento_vendedor", "Range");
			if((cotizador_detalles.getPeso_pieza() == null ? 0 : cotizador_detalles.getPeso_pieza()) < 0)
				errors.rejectValue("cotizador_detalles.peso_pieza", "Range");
			if((cotizador_detalles.getRef_para_comision() == null ? 0 : cotizador_detalles.getRef_para_comision()) < 1)
				errors.rejectValue("cotizador_detalles.ref_para_comision", "Range");
			if((cotizador_detalles.getCosto_flete() == null ? 0 : cotizador_detalles.getCosto_flete()) < 1)
				errors.rejectValue("cotizador_detalles.costo_flete", "Range");
			////////////////
			
			if(cotizador_detalles.getBan() != null)
				errors.rejectValue("cotizador_detalles.piezasxtarima", "NotLess");
			
		}//FIN DISEÑO
		else
		{
			if(cotizador_detalles.getIdtiporequerimiento() == 3) //Arrastre
			{
				if((cotizador_detalles.getSimbolo() == null ? "" : cotizador_detalles.getSimbolo()) == "")
					errors.rejectValue("cotizador_detalles.simbolo", "NotBlank");
				
				if((cotizador_detalles.getCantidad_pedido_mes() == null ? 0 : cotizador_detalles.getCantidad_pedido_mes()) == 0)
					errors.rejectValue("cotizador_detalles.cantidad_pedido_mes", "Range");
			}//Fin Arrastre
			else
			{
				if(cotizador_detalles.getIdtiporequerimiento() == 1 || cotizador_detalles.getIdtiporequerimiento() == 2) //Muestras
				{
					if((cotizador_detalles.getSimbolo() == null ? "" : cotizador_detalles.getSimbolo()) == "")
						errors.rejectValue("cotizador_detalles.simbolo", "NotBlank");	
					if((cotizador_detalles.getIdcaja_sap() == null ? 0 : cotizador_detalles.getIdcaja_sap()) < 1)
						errors.rejectValue("cotizador_detalles.idcaja_sap", "Range");
					
					if(!idCajas.contains(cotizador_detalles.getIdcaja_sap()) && (cotizador_detalles.getFondo() == null || cotizador_detalles.getFondo() == 0.0) )
						errors.rejectValue("cotizador_detalles.fondo", "NotNull");
					else
					{
						if(idCajas.contains(cotizador_detalles.getIdcaja_sap()) &&  (cotizador_detalles.getFondo() == null ? 0.0   : cotizador_detalles.getFondo() ) > 0.0)
							errors.rejectValue("cotizador_detalles.fondo", "Required");
					}
					
					if((cotizador_detalles.getResistencia_cte() == null ? 0 : cotizador_detalles.getResistencia_cte()) < 1)
						errors.rejectValue("cotizador_detalles.resistencia_cte", "Range");
					if((cotizador_detalles.getIdresistencia_barca() == null ? 0 : cotizador_detalles.getIdresistencia_barca()) < 1)
						errors.rejectValue("cotizador_detalles.idresistencia_barca", "Range");
					
					if((cotizador_detalles.getCierre() == null ? "" : cotizador_detalles.getCierre()).length() < 1)
						errors.rejectValue("cotizador_detalles.cierre", "NotBlank");
					
					if((cotizador_detalles.getCierre_detalle() == null ? "" : cotizador_detalles.getCierre_detalle()).length() < 1)
						errors.rejectValue("cotizador_detalles.cierre_detalle", "NotBlank");
					
					if((cotizador_detalles.getCantidad_pedido_mes() == null ? 0 : cotizador_detalles.getCantidad_pedido_mes()) < 1)
						errors.rejectValue("cotizador_detalles.cantidad_pedido_mes", "Range");
					
					if((cotizador_detalles.getLargo() == null ? 0 : cotizador_detalles.getLargo()) < 1)
						errors.rejectValue("cotizador_detalles.largo", "Range");
					if((cotizador_detalles.getAncho() == null ? 0 : cotizador_detalles.getAncho()) < 1)
						errors.rejectValue("cotizador_detalles.ancho", "Range");
					if((cotizador_detalles.getScore() == null ? 0 : cotizador_detalles.getScore()) < 0)
						errors.rejectValue("cotizador_detalles.score", "Range");
					
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
					
					if((cotizador_detalles.getMedida_lamina() == null ? "" : cotizador_detalles.getMedida_lamina()).length() < 1)
						errors.rejectValue("cotizador_detalles.medida_lamina", "NotBlank");
					
					if((cotizador_detalles.getArea_unitaria() == null ? 0 : cotizador_detalles.getArea_unitaria()) < 0)
						errors.rejectValue("cotizador_detalles.area_unitaria", "Range");
					
					if((cotizador_detalles.getPeso_teorico() == null ? 0 : cotizador_detalles.getPeso_teorico()) < 0)
						errors.rejectValue("cotizador_detalles.peso_teorico", "Range");
					
					if(cotizador_detalles.getIdcaja_sap() == 7 && (cotizador_detalles.getNum_raturas() == null || cotizador_detalles.getNum_raturas() <= 0)) //Si es  SEPARADOR debe llevar número de ranuras.
						errors.rejectValue("cotizador_detalles.num_raturas", "Required");
					
					if(cotizador_detalles.getIddetalle() > 1 && (cotizador_detalles.getPiezasxjuego() == null || cotizador_detalles.getPiezasxjuego() == 0))
						errors.rejectValue("cotizador_detalles.piezasxjuego", "NotNull");
					
					
				}//Fin Muestras
			}
		}
		
	}

}
