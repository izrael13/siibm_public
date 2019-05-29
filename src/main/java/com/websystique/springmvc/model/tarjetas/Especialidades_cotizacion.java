package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ESPECIALIDADES_COTIZACION")
public class Especialidades_cotizacion implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer iddetalle;
	private Integer idespecialidad;
	private Double cantidad;
	private Double costo;
	private Double ajuste;
	private Double esquema;
}
