package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_DIRECCIONES_SAP")
public class Catalogo_direcciones_sap_vw implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String cardcode;
	private Integer linenum;
	private String address;
	private String direccion;
	private String ciudad;
	private String estado;
	private Double flete;
	private String contacto;
	private String telefono;
	private String email;
}
