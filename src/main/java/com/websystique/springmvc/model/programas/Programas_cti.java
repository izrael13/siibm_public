package com.websystique.springmvc.model.programas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PROGRAMAS_CTI")
@Data
public class Programas_cti implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String programa;
	private Double cantidadsolicitada;
	private Integer pedido;
	private String  tarjeta;
	private Double cantidad;
	private Double pzasprogramadas;
	private String codigo;
	private String simbolo;
	private Date fechaentrega;
}
