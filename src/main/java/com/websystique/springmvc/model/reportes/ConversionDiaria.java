package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "conversionDiaria")
public class ConversionDiaria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String     pedido;
	private Integer    programadas;
	private Integer    corrugadas;
	private String     inicioSetup;
	private String     terminoSetup;
	private String     inicioConversion;
	private String     finConversion;
	private Integer    piezasContadas;
	private Integer    piezasBuenas;
	private Integer    laminasMalas;
	private Integer    malasSetup;
	private Integer    malasProceso;
	private Integer    laminasPorProcesar;
	private String     workCenterID;
	private String     numberOut;
	private Integer    piezasEntregadas;
}
