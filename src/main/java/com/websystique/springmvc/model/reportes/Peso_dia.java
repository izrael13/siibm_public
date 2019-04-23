package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "peso_dia")
public class Peso_dia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Date fecha_ini;
	private Double totalkg;
	private Double tortonkg;
	private Double trailerkg;
	private Double otroskg;
	private Integer totalrem;
	private Integer tortonrem;
	private Integer trialerrem;
	private Integer otrosrem;
	/*private Integer trailer_emb;
	private Integer otros_emb;
	private Integer diferencia_emb;*/
}
