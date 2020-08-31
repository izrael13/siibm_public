package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DESEMPENIO_ANUAL_VENDEDOR")
public class Desempenio_anual_vendedor implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private String slpname;
	private Double anio1;
	private Double porcen1; 
	private Double anio2;
	private Double porcen2; 
	private Double anio3;
	private Double porcen3; 
	private Double anio4;
	private Double porcen4; 
	private Double anio5;
	private Double porcen5; 
}
