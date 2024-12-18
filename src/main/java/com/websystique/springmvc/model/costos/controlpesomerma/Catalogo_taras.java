package com.websystique.springmvc.model.costos.controlpesomerma;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_TARAS")
public class Catalogo_taras implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descripcion;
	private Double pesokg;
}
