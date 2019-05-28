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
	private Integer id;
	private String cardname;
	private String direccion;
	private String fecha_insert;
}
