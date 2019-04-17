package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cobranza_detalle")
public class Cobranza_detalle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String cte;
	private String ven;
	private String fecha;
	private String factura;
	private String dc;
	private Double retencion;
	private String fechaven;
	private Double vencido; 
	private Double semana1; 
	private Double semana2; 
	private Double semana3; 
	private Double semana4;
	private Double porvencer;
	private Double totalgral;
}
