package com.websystique.springmvc.model.tarjetas.cotizador;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "BOCETOS_COTIZADOR")
public class Bocetos_cotizador implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id //@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull @Range(min = 1)
	private Integer idcotizacion;
	@NotBlank
	private String num_proyecto;
	@NotNull
	private Double version;
	private String observaciones_vendedor;
	private String observaciones_diseniador;
	private String ruta_archivo;
	private String nombre_archivo;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_insert;
	private Integer usuario_insert;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_acepta;
	private Integer usuario_acepta;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_rechazo;
	private Integer usuario_rechazo;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_envio;
	private Integer usuario_envio;
}
