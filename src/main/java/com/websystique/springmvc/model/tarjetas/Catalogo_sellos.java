package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_SELLOS")
public class Catalogo_sellos implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id; 
	private String sellos;
	private String nombre;
	private Date fecha_insert;
}
