package com.websystique.springmvc.model.costos.controlpesomerma;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name= "CONTROL_PESO_MERMA")
public class Control_peso_merma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank @Size(min=3)
	private String pedido;
	@Column(updatable=false)
	private Integer usuario_empacador;
	@NotNull @Range(min = 1)
	private Integer idtara;
	private Double pesokg_tara;
	@NotNull
	private Double pesokg_total;
	@NotNull
	private Double pesokg_real;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") @Column(updatable=false)
	private Date fecha_registro;
	private String comentarios;
}
