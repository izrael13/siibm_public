package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_CLIENTES_SAP")
public class Catalogo_clientes_sap_vw implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String cardcode;	
	private String cardname;
	private Integer slpcode;
	private Integer groupcode;
	private String groupname;
	
}
