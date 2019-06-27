package com.websystique.springmvc.model.classespk;

import java.io.Serializable;

import lombok.Data;

@Data
public class Codigo_barras_cotizadorPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idcotizacion;
	private Integer iddetalle;
	private String idcodigo;

}
