package com.websystique.springmvc.model.tarjetas.cotizador;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "COTIZADOR")
public class Cotizador implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank @Size(min=2)
	private String cardcode;
	@NotNull @Range(min = 0)
	private Integer linenum_dir_entrega;
	@NotNull @Range(min = 1)
	private Double costo_flete;
	
	@Column(updatable=false)
	private Date fecha_insert;
	@Column(updatable=false)
	private Integer usuario_insert;
	
	private Date fecha_update;

	private Integer usuario_update;
	
	@Column(updatable=false)
	private Integer idtiporequerimiento;
	
	private Integer usuario_envia_ventas;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_envia_ventas;
	private Integer usuario_aut_ventas;   
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_aut_ventas;
	private Integer usuario_rech_ventas;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_rech_ventas;
	private Integer usuario_envia_a_prog;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_envia_a_prog;
	private Integer usuario_aut_prog;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_aut_prog;
	private Integer usuario_rech_prog;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_rech_prog;
	private String observaciones_ventas;
	private String observaciones_prog;
	private Integer usuario_cancel;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_cancel;
	
	private Integer id_diseniador;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_asign_diseniador;
	private String observaciones_diseniador;

	
}
