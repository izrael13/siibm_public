package com.websystique.springmvc.model.tarjetas.prospectos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

//import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
@Entity
@Table(name= "PROSPECTOS_VENTAS_DETALLE")
public class Prospectos_ventas_detalle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//@ManyToOne
	//@NotNull @Range(min = 1)
	private Integer id_prospecto_ventas;
	
	@Column(updatable=false)
	private Date fecha_insert;
	//@NotBlank
	private String fecha_cierre;
	//@NotNull @Range(min = 1)
	private Integer cve_actividad;
	//@NotBlank
	private String observaciones;
	@Column(updatable=false)
	private Integer usuario_insert;
}
