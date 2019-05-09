package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "viajes_mes_ciudad")
public class Viajes_mes_ciudad implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer mes;
	private String estado;
	private String ciudad;
	private Integer tortone;
	private Integer trailere;
	private Integer otrose;
}
