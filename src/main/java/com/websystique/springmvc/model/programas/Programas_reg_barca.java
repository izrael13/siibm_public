package com.websystique.springmvc.model.programas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "PROGRAMAS_REG_BARCA")
@Data
public class Programas_reg_barca implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private Integer pedido;
	@NotBlank @Size(min=1, max=15)
	private String tf_sap;
	@NotBlank @Size(min=1, max=15)
	private String tf_programa;
	@NotBlank @Size(min=1, max=20)
	private String programa;
	@NotNull
	private Double cant_acumulada;
	@NotNull @DecimalMin("1.0") 
	private Double cant_programada;
	@NotNull
	private Double cant_pendiente;
	@Size(max=100)
	private String observaciones;
	@Column(updatable=false)
	private Integer usuario_insert;
	@Column(updatable=false) @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_insert;
	private Integer usuario_update;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_update;
}
