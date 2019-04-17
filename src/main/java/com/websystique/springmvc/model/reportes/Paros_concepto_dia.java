package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "paros_concepto_dia")
public class Paros_concepto_dia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Date fecha;
	private String motivo;
	private Integer flexografica;
	private Integer flexotroqueladora;
	private Integer troqueladora1;
	private Integer troqueladora2;
	private Integer troqueladora3;
}
