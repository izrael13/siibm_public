package com.websystique.springmvc.model.mp.inventario;

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
@Table(name= "CONTEO_INVENTARIO_MP")
public class Conteo_inventario_mp implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private Integer id_inv;
	@Size(min = 1, max = 50)
	private String rollo_id;
	@Size(min = 1, max = 15)
	private String tipo;
	@NotNull
	private Double ancho;
	@NotNull
	private Double peso;
	@Size(min = 1, max = 15)
	private String ubicacion;
	private Double conteo;
	private Integer usuario_insert;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_insert;
}
