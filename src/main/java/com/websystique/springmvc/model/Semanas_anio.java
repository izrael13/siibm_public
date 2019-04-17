package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import com.websystique.springmvc.model.classespk.semanas_anioPK;

import lombok.Data;

@Data
@Entity
@Table(name="semanas_anio")
@IdClass(value = semanas_anioPK.class)
public class Semanas_anio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer anio;
	@Id
	private Integer semana;
	private Date fecha_inicial;
	private Date fecha_final;
	
}
