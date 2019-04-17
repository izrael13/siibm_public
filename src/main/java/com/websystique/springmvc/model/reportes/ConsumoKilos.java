package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "consumoKilos")
public class ConsumoKilos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String tipo_papel;
	private String inv_ini;
	private Double entradas;
	private Double salidas;
	private Double inv_fin;
	private Double mtrslineales;
	private Double programado;
	private Double diferencia;
	private String tipo;
	private String ancho;
}
