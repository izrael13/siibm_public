package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_SELLOS")
public class Catalogo_sellos implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull @Size(min=1)
	private String sellos;
	private String nombre;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_insert;
	private Integer idusuario_captura;
}
