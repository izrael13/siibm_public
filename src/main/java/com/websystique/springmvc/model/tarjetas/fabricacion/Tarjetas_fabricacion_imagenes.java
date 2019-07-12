package com.websystique.springmvc.model.tarjetas.fabricacion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "TARJETAS_FABRICACION_IMAGENES")
public class Tarjetas_fabricacion_imagenes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer idcotizacion;
	private Integer iddetalle;
	private String nombre;
	
	private String path;
	private Boolean cama;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_insert;
}
