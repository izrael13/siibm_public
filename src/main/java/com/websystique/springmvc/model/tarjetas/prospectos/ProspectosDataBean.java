package com.websystique.springmvc.model.tarjetas.prospectos;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import lombok.Data;

@Data
public class ProspectosDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Valid
	private Prospectos_ventas prospectos_ventas;
	@Valid
	private Prospectos_ventas_detalle prospectos_ventas_detalle;
	
}
