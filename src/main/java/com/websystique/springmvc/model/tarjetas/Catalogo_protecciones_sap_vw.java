package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_PROTECCIONES_SAP")
public class Catalogo_protecciones_sap_vw implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer code;
	private String u_observaciones;

}
