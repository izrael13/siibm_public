package com.websystique.springmvc.model.tarjetas.cotizador;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import lombok.Data;

@Data
public class CotizadorDataBean implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Valid
	Cotizador cotizador;
	
	@Valid
	Cotizador_detalles cotizador_detalles;
}
