package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "golpes_maquina_mes")
public class Golpes_maquina_mes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private Date oprunstartdatetime;
	private Double flexograficagp;
	private Double flexotroqueladoragp;
	private Double troqueladora1gp;
	private Double troqueladora2gp;
	private Double troqueladora3gp;
	
	private Double flexograficapz;
	private Double flexotroqueladorapz;
	private Double troqueladora1pz;
	private Double troqueladora2pz;
	private Double troqueladora3pz;
	
	private Double flexograficakl;
	private Double flexotroqueladorakl;
	private Double troqueladora1kl;
	private Double troqueladora2kl;
	private Double troqueladora3kl;
}
