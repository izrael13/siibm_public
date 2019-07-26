package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ParamsGeneral implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParamsGeneral(Integer id, String paramstringN, String paramstringV, String operador) {
		super();
		this.id = id;
		this.paramstringN = paramstringN;
		this.paramstringV = paramstringV;
		this.operador = operador;
	}
	
	public ParamsGeneral(Integer id, String paramintegerN, Integer paramintegerV, String operador) {
		super();
		this.id = id;
		this.paramintegerN = paramintegerN;
		this.paramintegerV = paramintegerV;
		this.operador = operador;
	}
	
	public ParamsGeneral(Integer id, String paramdoubleN, Double paramdoubleV, String operador) {
		super();
		this.id = id;
		this.paramdoubleN = paramdoubleN;
		this.paramdoubleV = paramdoubleV;
		this.operador = operador;
	}
	
	public ParamsGeneral(Integer id, String parambooleanN, Boolean parambooleanV, String operador) {
		super();
		this.id = id;
		this.parambooleanN = parambooleanN;
		this.parambooleanV = parambooleanV;
		this.operador = operador;
	}
	
	public ParamsGeneral(Integer id, String paramdateN, Date paramdateV, String operador) {
		super();
		this.id = id;
		this.paramdateN = paramdateN;
		this.paramdateV = paramdateV;
		this.operador = operador;
	}
	
	public ParamsGeneral(Integer id, String paramnullN, String operador) {
		super();
		this.id = id;
		this.paramnullN = paramnullN;
		this.operador = operador;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String paramstringN;
	private String paramstringV;
	
	private String paramintegerN;
	private Integer paramintegerV;
	
	private String paramdoubleN;
	private Double paramdoubleV;
	
	private String parambooleanN;
	private Boolean parambooleanV;
	
	private String paramdateN;
	private Date paramdateV;
	
	private String paramnullN;
	private String paramnullV;
	
	private String operador;
}
