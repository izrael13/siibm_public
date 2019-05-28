package com.websystique.springmvc.model.tarjetas.cotizador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.websystique.springmvc.model.tarjetas.Especialidades_cotizacion;

import lombok.Data;

@Data
@Entity
@Table(name= "COTIZADOR_DETALLES")
public class Cotizador_detalles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idcotizacion;
	@Id
	private Integer iddetalle;

	@NotBlank
	private String simbolo;
	@NotNull @Range(min = 1)
	private Integer idcaja_sap;
	@NotNull @Range(min = 1)
	private Double largo;
	@NotNull @Range(min = 1)
	private Double ancho;
	
	private Double fondo;
	@NotNull @Range(min = 1)
	private Integer resistencia_cte;
	@NotNull @Range(min = 1)
	private Integer idresistencia_barca;
	@NotNull @Range(min = 1)
	private Double preciom2resistencia;
	//@NotNull @Range(min = 1)
	//private Double peso_resistencia;
	@NotBlank @Size(min=1)
	private String cierre;
	@NotBlank @Size(min=1)
	private String cierre_detalle;
	//@NotNull @Range(min = 1)
	private Integer piezasxjuego;
	private String observaciones_vendedor;
	@NotNull @Range(min = 0)
	private Double esp_inf;
	@NotNull @Range(min = 0)
	private Double esp_sup;
	@NotNull @Range(min = 1)
	private Integer cantidad_pedido_mes;
	@NotNull @Range(min = 1)
	private Double precio_objetivo;
	@NotNull @Range(min = 0)
	private Integer piezasxtarima;
	@NotNull @Range(min = 0)
	private Integer score;
	@NotNull @Range(min = 0)
	private Integer num_tintas;
	@NotBlank
	private String medida_lamina;
	@NotNull @Range(min = 0)
	private Double area_unitaria;
	@NotNull @Range(min = 1)
	private Double m2;
	@NotNull @Range(min = 1)
	private Double kg;
	@NotNull @Range(min = 0)
	private Double peso_teorico;
	@NotNull @Range(min = 1)
	private Double precio_neto;
	@NotNull @Range(min = 1)
	private Double precio_sugerido;
	@NotNull @Range(min = 1)
	private Double costo_papel;
	@NotNull @Range(min = 0)
	private Double porcentaje_comision;
	@NotNull @Range(min = 1)
	private Double descuento_vendedor;
	@NotNull @Range(min = 0)
	private Double peso_pieza;
	@NotNull @Range(min = 1)
	private Double ref_para_comision;
	@NotNull @Range(min = 1)
	private Double costo_flete;
	@NotNull @Range(min = 1)
	private Double comisionxmillar;
	@Column(updatable=false)
	private Date fecha_insert;
	@Column(updatable=false)
	private Integer usuario_insert;
	private Date fecha_update;
	private Integer usuario_update;
	
	private Double comision_directo;
	private Double cpcc;
	private Double porc_flete;
	private Double peso_resis;
	private Double costo_papel_resis;
	
	//@NotEmpty
	@Transient
	private List<Especialidades_cotizacion>  especialidades_cotizacion;  
}
