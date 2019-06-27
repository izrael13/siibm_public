package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.websystique.springmvc.model.classespk.Codigo_barras_cotizadorPK;

import lombok.Data;

@Data
@Entity
@Table(name= "CODIGO_BARRAS_COTIZADOR")
@IdClass(value = Codigo_barras_cotizadorPK.class)
public class Codigo_barras_cotizador implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer idcotizacion;
	@Id
	private Integer iddetalle;
	@Id
	private String idcodigo;
	
	private String observaciones;
}
