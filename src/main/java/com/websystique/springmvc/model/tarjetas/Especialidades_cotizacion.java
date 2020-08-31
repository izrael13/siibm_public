package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ESPECIALIDADES_COTIZACION")
public class Especialidades_cotizacion implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer count;
	private Integer iddetalle;
	private Integer idcotizacion;
	private Integer idespecialidad;
	private Double costo;
	private Double ajuste;
	private Integer esquema;
	private Double cm;
	private Integer propiedadoitm;
	private String medidas;
}
