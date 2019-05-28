package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_ESPECIALIDADES_SAP")
public class Catalogo_especialidades_sap_vw implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer code;
	private String name;
	private Integer u_esquema;
	private Double u_ajuste;
	private Double u_propiedad;
	private Double u_valmin;
	private Double u_valmax;
}
