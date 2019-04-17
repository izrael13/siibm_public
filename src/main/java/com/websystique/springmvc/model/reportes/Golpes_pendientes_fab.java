package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "golpes_pendientes_fab")
public class Golpes_pendientes_fab implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String fecha;
	private Double flexosgp;
	private Double troqgp;
	private Double otrosgp;
	private Double flexoskl;
	private Double troqkl;
	private Double otroskl;
	
	private Double flexosgpalm;
	private Double troqgpalm;
	private Double otrosgpalm;
	private Double flexosklalm;
	private Double troqklalm;
	private Double otrosklalm;
	
	private Double kilos_tkf;
	private Double kilos_tke;
	private Double kilos_pend;
	
	private Double gppenflx;
	private Double gppentroq;
	private Double gppenotros;
	private Double cap_flexos;
	private Double cap_troq;
}
