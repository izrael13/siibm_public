package com.websystique.springmvc.model.viajes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "viajes_detalle")
public class Viajes_detalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private String remision;
	private String descripcion;
	private String cliente;
	private String estado;
	private String ciudad;
	private String colonia;
	private String calle;
	private String u_chofer;
	private Double kilos;
	private String u_vehiculo;
	private String u_aut_ambarques;
	private String u_aut_logistica;
	private String factura;
	private String pedido;
	private Double cantidad;
	
}
