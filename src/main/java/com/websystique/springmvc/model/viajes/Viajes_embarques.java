package com.websystique.springmvc.model.viajes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "viajes_embarques")
public class Viajes_embarques implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String Code;
	
	private String Name;
	private String U_num_viaje;
	private Date U_fecha_reg;
	private Double U_kilos;
	private Integer U_repartos;
	private Integer U_cambio_edo;
	private Double U_costo;
	private Integer U_demoras;
	private Integer U_devoluciones;
	private String U_aut_ambarques;
	private String U_aut_logistica;
	private Integer U_maniobras;
}
