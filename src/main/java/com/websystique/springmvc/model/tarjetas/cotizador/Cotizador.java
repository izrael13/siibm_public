package com.websystique.springmvc.model.tarjetas.cotizador;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
@Entity
@Table(name= "COTIZADOR")
public class Cotizador implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank @Size(min=2)
	private String cardcode;
	@NotNull @Range(min = 1)
	private Integer linenum_dir_entrega;
	@NotNull @Range(min = 1)
	private Double costo_flete;
	
	@Column(updatable=false)
	private Date fecha_insert;
	@Column(updatable=false)
	private Integer usuario_insert;
	
	private Date fecha_update;

	private Integer usuario_update;
	
	@Column(updatable=false)
	private Integer idtiporequerimiento;
	
	private Integer usuario_aut_ventas;   
	private Date fecha_aut_ventas;
	private Integer usuario__rech_ventas;
	private Date fecha_rech_ventas;
	private Integer usuario_aut_prog;
	private Date fecha_aut_prog;
	private Integer usuario_rech_prog;
	private Date fecha_rech_prog;
	private Integer usuario_env_a_ing;
	private Date fecha_env_a_ing;
	private String observaciones_ventas;
	private String observaciones_prog;
	
}
