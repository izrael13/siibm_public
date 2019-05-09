package com.websystique.springmvc.model.tarjetas.prospectos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Prospectos_ventas_busqueda implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String cardname;
	private String groupname;
	private String contacto;
	private Double opor_ton;
	private String fecha_cierre;
	private String tel;
	private String edo;
	private String ciudad;
	private String nom_vendedor;
	private Integer prioridad;
	private Double porc_avance;
	private Integer estatus;
	private String observaciones;
	private String fecha_insert;
}
