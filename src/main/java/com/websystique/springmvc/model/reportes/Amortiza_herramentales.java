package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "amortiza_herramentales")
public class Amortiza_herramentales implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String u_tf;
	private String vendedor;
	private String cliente;
	private String descripcion;
	private String herramental;
	private Date fecha_recep;
	private Double total_pedidos;
	private Double facturado;
	private Double linetotal;
	private Double price;
	private Double porcherra;
}
