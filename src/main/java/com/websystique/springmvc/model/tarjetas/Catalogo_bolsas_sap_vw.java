package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_BOLSAS_SAP")
public class Catalogo_bolsas_sap_vw implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer code;
	private String name;
	private Double u_ajuste;

}
