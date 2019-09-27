package com.websystique.springmvc.model.tarjetas.fabricacion;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;

import lombok.Data;

@Data
public class TarjetaDataBean implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Valid
	Tarjeta_fabricacion tarjeta_fabricacion;
	
	@Valid
	Cotizador cotizador;
	
	@Valid
	Cotizador_detalles cotizador_detalles;
	
}
