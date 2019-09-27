package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_MAQUINAS_SAP")
public class Catalogo_maquinas_sap_vw implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer code;
	private String name;
	private Double u_minlargo;
	private Double u_maxlargo;
	private Double u_minancho;
	private Double u_maxancho;
	private Double u_prioridad;
	private String u_cierre;
	private Integer u_grupo;
	private Integer u_tintas;
	private Integer u_pegado;
	private Integer u_grapado;	
}
