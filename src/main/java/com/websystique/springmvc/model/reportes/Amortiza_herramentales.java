package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "AMORTIZA_HERRAMENTALES")
public class Amortiza_herramentales implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String herramental;
	private Double totalherramental;
	private Double totalfacturado;
	private Double totalnotacredito;
	private Double total;
	private String amortizado;
	private Double porcentaje;
	private String clientes;
	private String vendedor;
	private String grabados_suajes;
	private String fecha_recepcion;
	private String grab_suaj_nom;
	private String fecha_ult_ped;
	private Double fac10porc;
}
