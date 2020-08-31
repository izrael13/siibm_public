package com.websystique.springmvc.model.certificados;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "CERTIFICADOS_CALIDAD")
public class Certificados implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
    private String remision;
	@NotNull
	private String itemcodecert;
	@NotNull
	private Double cant;
	@NotBlank
    private String folio_tf;
    private Boolean estatus;
    private String observaciones;
    @NotBlank
    private String generado_por;
    @NotBlank
    private String puesto;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") @Column(updatable=false)
    private Date fecha_certificado;
    private String ect_me;
    private String ect_me_r;
    private String bct_me;
    private String bct_me_r;
    private String mullen_me;
    private String mullen_me_r;
    @Column(updatable=false)
    private Integer usuario_insert;
    private Integer usuario_cancel;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date fecha_cancel;
    @NotBlank
    private String lote_fab_imp;
    private Date fecha_update;
    private Integer usuario_update;
}
