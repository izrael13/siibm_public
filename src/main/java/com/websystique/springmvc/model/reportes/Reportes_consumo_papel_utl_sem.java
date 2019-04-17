package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name= "reportes_consumo_papel_utl_sem")
public class Reportes_consumo_papel_utl_sem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private Integer anio;	
	private Integer semana;	
	private Double ancho;	
	private String tipo_papel;	
	private Double prom_utl_sem;
	private Double prom_act_sem;
	private Double diferencia;
	private Double inventario_inicial;
}
