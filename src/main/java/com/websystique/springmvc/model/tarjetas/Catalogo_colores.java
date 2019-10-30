package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "CATALOGO_COLORES")
public class Catalogo_colores implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String color;
	private Integer id_tipo_color;
	private String cmyk;
	private Integer version;
	private String arrastre;
	private String color_est;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_captura;
	private Integer idusuario_captura;
}
