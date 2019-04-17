package com.websystique.springmvc.model.ventas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRONOSTICOS_TEMP")
public class Pronosticoscte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	private String CVE_CLIENTE;
	private String CLIENTE;
	private String VENDEDOR;
	private Integer PRONOSTICO;	
}
