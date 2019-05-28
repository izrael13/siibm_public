package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_RESISTENCIAS_SAP")
public class Catalogo_resistencias_sap_vw implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer idresistencia;
	private String resistencia;
	private String mullen;
	private String corrugado;   
	private String color;
	private Double peso; 
	private String papeles;
	private Double preciom2;
	private Double u_descr;
	private Double u_costopapel;
}
