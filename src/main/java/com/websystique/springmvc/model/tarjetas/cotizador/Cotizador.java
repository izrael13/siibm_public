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
	@Column(updatable=false) @Range(min = 1)
	private Integer usuario_insert;
	
	@NotNull
	private Date fecha_update;
	@NotNull @Range(min = 1)
	private Integer usuario_update;
	
	private Integer idtiporequerimiento;
}
