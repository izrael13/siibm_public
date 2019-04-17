package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "paros_maquina_dia")
public class Paros_maquina_dia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Date fecha_ini;
	private Double flexografica;
	private Double flexotroqueladora;
	private Double troqueladora1;
	private Double troqueladora2;
	private Double troqueladora3;
}
