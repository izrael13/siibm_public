package com.websystique.springmvc.model.tarjetas.prospectos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
@Entity
@Table(name= "PROSPECTOS_VENTAS")
public class Prospectos_ventas implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank @Size(min=2)
	private String cardcode;
	@NotBlank
	private String contacto;
	@NotBlank
	private String telefono;
	@NotBlank @Email
	private String email;
	
	@NotNull @Range(min = 1)
	private Integer cve_estado;
	@NotNull @Range(min = 1)
	private Integer cve_ciudad;
	//@NotNull @Range(min = 1)
	//private Integer cve_giro;
	@NotNull @Range(min = 1)
	private Integer prioridad;
	@NotNull @Range(min = 1)
	private Double oportunidad_toneladas;
	@NotBlank //@Future @DateTimeFormat(pattern = "DD/MM/YYYY") 
	private String fecha_cierre;
	@NotNull @Range(min = 1)
	private Integer estatus;
	
	//@Transient
	@Column(updatable=false)
	private Date fecha_insert;
	
	private Date fecha_update;
	
	@Column(updatable=false)
	private Integer usuario_insert;
	
	private Integer usuario_update;
	private String observaciones;
	private Integer porcentaje_avance;
	
	//@Transient
	@Column(insertable=false, updatable=false)
	private Double prevision_pon_ton;
	
}
