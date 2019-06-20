package com.websystique.springmvc.model.tarjetas.cotizador;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Cotizador_busqueda implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer count;
	
	private Integer id;
	private Integer iddet;
	private String cardname;
	private String direccion;
	private String fecha_insert;
	private String simbolo;
	private String caja;
	private Double comision_directo;
	private Double precio_objetivo;
	private Double precio_sugerido;
	private Double precio_neto;
	private Double descuento_vendedor;
	private String nombrecorto;
}
