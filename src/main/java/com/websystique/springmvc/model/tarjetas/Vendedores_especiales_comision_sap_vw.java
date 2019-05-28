package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "VENDEDORES_ESPECIALES_COMISION_SAP")
public class Vendedores_especiales_comision_sap_vw implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer code;
	private Integer u_slpcode;
	private String u_cardcode;
	private Double u_comision;
}
