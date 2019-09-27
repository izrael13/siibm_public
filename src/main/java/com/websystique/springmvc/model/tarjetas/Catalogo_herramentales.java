package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_HERRAMENTALES")
public class Catalogo_herramentales implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String nombre;
	@NotBlank
	private Integer tipo;
	@NotBlank
	private Double costo;
	@Column(updatable=false)
	private Integer usuario_registro;
	@Column(updatable=false)
	private Date fecha_registro;
	private Integer usuario_update;
	private Date fecha_update;

}
