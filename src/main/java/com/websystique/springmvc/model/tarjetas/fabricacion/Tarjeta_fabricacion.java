package com.websystique.springmvc.model.tarjetas.fabricacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.websystique.springmvc.model.classespk.Tarjeta_fabricacionPK;

import lombok.Data;

@Data
@Entity
@Table(name= "TARJETA_FABRICACION")
@IdClass(value = Tarjeta_fabricacionPK.class)
public class Tarjeta_fabricacion implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer idcotizacion;
	@Id
	private Integer iddetalle;
	private String folio_tarjeta;
	private Integer num_partes;
	private String descripcion_factura;
	private Integer pzasxlargo;
	private Integer pzasxancho;
	private String medida_pliego;
	private String medidas_internas;
	private Integer cliche;
	private Double costo_cliche;
	private Integer suaje;
	private Double rayado1;
	private Double rayado2;
	private Double rayado3;
	private Double rayado4;
	private Double rayado5;
	private Double rayado6;
	private Double pegado_grapado;
	private String observaciones_tf;
	private String observaciones;
	private Integer usuario_aut_diseniador;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_aut_diseniador;
	private String observaciones_disenador;
	private Integer usuario_aut_calidad;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_aut_calidad;
	private Integer usuario_rech_calidad;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_rech_calidad;
	private String observaciones_calidad;
	private Integer usuario_aut_produccion;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_aut_produccion;
	private Integer usuario_rech_produccion;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_rech_produccion;
	private String observaciones_produccion;
	private Integer usuario_aut_ing;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_aut_ing;
	private Integer usuario_recha_ing;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_rech_ing;
	private String observaciones_ing;
	private Integer usuario_aut_cliente;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_aut_cliente;
	private Integer usuario_rech_cliente;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_rech_cliente;
	private String observaciones_cliente;
	
	private Integer ban;// Ver la clase Tarjeta_fabricacionValidator
	
	private Integer usuario_cancela;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_cancela;
	
	@Transient
	List<Tarjetas_fabricacion_imagenes> tarjeta_img = new ArrayList<Tarjetas_fabricacion_imagenes>();

}
