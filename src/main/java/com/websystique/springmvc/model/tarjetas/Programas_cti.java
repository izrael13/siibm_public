package com.websystique.springmvc.model.tarjetas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PROGRAMAS_CTI")
@Data
public class Programas_cti implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cblineupitemid;
	private Double totalitemsrequired;
	private Integer orderid;
	private String  specid;
	private Double cantidad;
	private Double cpiecesscheduled;
	private String itemcode;
	private String dscription;
	private Date duedatetime;
}
