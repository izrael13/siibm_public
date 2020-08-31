package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "LISTA_APROBACION_AREAS")
public class Lista_aprobacion_areas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer code;
	private String modulo;
	private String area;
	private String actividad; 
	private String descripcion;
}
