package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DESEMPENIO_MENSUAL_VENDEDOR")
public class Desempenio_mensual_vendedor implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private String slpname;
	private Double enero;
	private Double febrero;
	private Double marzo;
	private Double abril;
	private Double mayo;
	private Double junio;
	private Double julio;
	private Double agosto;
	private Double septiembre;
	private Double octubre;
	private Double noviembre;
	private Double diciembre;
	private Double totaltenanio;
	private Double porcentaje;
}
