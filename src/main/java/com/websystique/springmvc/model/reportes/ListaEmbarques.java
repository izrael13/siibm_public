package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "LISTAEMBARQUES")
public class ListaEmbarques implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer pedido;	
	private String fechaproduccion;	
	private String fechaentregacliente;	
	private String nombre;
	private String clave;
	private String simbolo;	
	private Double cansol;	
	private Double canembarcada;	
	private Double stocklote;
	private Double kilos;
	private String lugarentrega;	
	private String observaciones;	
	private String slpname;
	//private Double posibleembarcar;
}
