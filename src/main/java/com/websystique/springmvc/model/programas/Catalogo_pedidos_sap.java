package com.websystique.springmvc.model.programas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CATALOGO_PEDIDOS_SAP")
@Data
public class Catalogo_pedidos_sap implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String clavecliente;
	private Integer pedido;
	private Date fechapedido;
	private Date fechaentrega;
	private Double totaldocumento;
	private String codigoarticulo;
	private String descripcion;
	private Double cantidad;
	private String tf;
	private Double totaldocumentosin;
	private Double cantidadcliente;
}
