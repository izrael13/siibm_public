package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "todos_pedidos")
public class Todos_pedidos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer docentry;
	
	private String u_tf;
	private String clavesap;
	private String pedido;
	private String cardname;
	private String orden_compra;
	private Date elaboracion;
	private Date entrega;
	private String clavesap2;
	private String simbolo;
	private String impresion;
	private String cierre;
	private String piezasxatado;
	private String resistencia;
	private Double u_l;
	private Double u_a;
	private Double u_f;
	private Double areajuego;
	private Double cantidad;
	private Double peso;
	private Double totalmetros;
	private String combinacion;
	private Double totalkilos;
	private Double totaldocumento;
	private Double precio;
	private String shiptocode;
	private String entrega2;
	private Double preciom2;
	private String docstatus;
	private Double precioneto;
	private Double descuento;
	private String canceled;
	private Double pk;
	private Double comision_porc;
	private Double importecomision;
	private Double pkr;
	private String tipo;
	private Double importetotal;
	private String comments;
	private String especialidad;
	private Double u_ctopapel;
	private Double u_costo_flete;
	private Double u_costesp;
	private Double u_ctopsc;
	private Double u_ctopcc;
	private Double anchopliego;
	private Double largopliego;
}
