package com.websystique.springmvc.model.certificados;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "LIBERACIONCM")
public class Liberacioncm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String tf_lcm;
	private String itemcode_lcm;
	private Double a;
	private Double b;
	private Double c;
	private Double d;
	private Double e;
	private Double f;
	private Double g;
	private Double h;
	private Double i;
	private Double j;
	private Double k;
	private Double l;
	private Double m;
	private Double n;
	private String observaciones_lcm;
	private String dir_file;
	@Column(updatable=false)
	private Integer usuario_insert;
	@Column(updatable=false)
	private Date fecha_insert;
	private Integer usuario_update;
	private Date fecha_update;
	private Boolean activo;
}
