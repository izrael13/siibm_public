package com.websystique.springmvc.model.certificados;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "REMISIONES_PARA_CERTIFICADO")
public class Remisiones_para_certificado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String numatcard;
	@Id
	private String itemcodesi;
	private String tfi;
	private Double cantidad;
	private Integer lotefab;
	private Date fecha_embarque;
	private Date fecha_fabricacion;
}
