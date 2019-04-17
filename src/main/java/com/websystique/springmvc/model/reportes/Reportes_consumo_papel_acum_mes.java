package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name= "reportes_consumo_papel_acum_mes")
public class Reportes_consumo_papel_acum_mes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String tipo_papel;
	private Double peso_papel_mant;
	private Double peso_papel_mact;
	private Double prom_3_meses_ant;
	private Double inc_dec_porc_mant;
	private Double inc_dec_porc_3mant;
}
