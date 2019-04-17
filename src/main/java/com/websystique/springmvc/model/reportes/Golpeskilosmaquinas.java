package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "golpeskilosmaquinas")
public class Golpeskilosmaquinas implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer anio;
	private String mes;
	private Integer dia;
	private Double flexografica_kilos;
	private Double flexografica_golpes;
	private Double flexotroqueladora_kilos;
	private Double flexotroqueladora_golpes;
	private Double troqueladora_kilos;
	private Double troqueladora_golpes;
	private Double otros_kilos;
	private Double otros_golpes;
}
