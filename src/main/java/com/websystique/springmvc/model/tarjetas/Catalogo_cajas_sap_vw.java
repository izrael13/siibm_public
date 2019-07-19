package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_CAJAS_SAP")
public class Catalogo_cajas_sap_vw implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer idtipocaja;  
	private String nombrecorto; 
	private String nombrelargo; 
	private Double mll;
	private Double mla;
	private Double mlf;
	private Double maa;
	private Double maf;
	private Double tr;
	private Double deslm;
	private Double deslmi;
	private Double desam;
	private Double desami;
	private Integer dospartes;
	private String tipocajabarca;
	private String corrugado;
}
