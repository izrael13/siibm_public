package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

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
	private String Herramental;
	private Double TotalHerramental;
	private Double TotalFacturado;
	private Double TotalNotaCredito;
	private Double TOTAL;
	private String AMORTIZADO;
	private Double PORCENTAJE;
	private String clientes;
	private String vendedor;
	private String grabados_suajes;
	private String fecha_recepcion;
	private String grab_suaj_nom;
}
