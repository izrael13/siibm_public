package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.websystique.springmvc.model.classespk.Reporte_consumo_papelPK;
import lombok.Data;

@Data
@Entity
@Table(name="reporte_consumo_papel")
@IdClass(value = Reporte_consumo_papelPK.class)
public class Reporte_consumo_papel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer anio;
	@Id
	private Integer semana;
	@Id
	private Integer nivel;
	@Id
	private Double ancho;
	
	private Double m90u ;
	private Double m90 ;
	private Double m195u ;
	private Double l140c ;
	private Double lb130c ;
	private Double l150lnm ;
	private Double l125c ;
	private Double l33a ;
	private Double lb150c ;
	private Double m155e ;
	private Double l120lsgs ;
	private Double l200a ;
	private Double lb185c ;
	private Double l35a ;
	private Double l130lsgs ;
	private Double l26a ;
	private Double m170rmsgr ;
	private Double l42a ;
	private Double m130msgr ;
	private Double l260nlsgn ;
	private Double m150msgr ;
	private Double m190e ;
	private Double l140t ;
	private Double l200t ;
	private Double l125t ;
	private Double m110p ;
	private Double l170u ;
	private Double m150u ;
	private Double m170msgr ;
	private Double l305a ;
	private Double m110u ;
	private Double l200rlsgr ;
	private Double m150rmsgr ;
	private Double l56a ;
	private Double l260rlsgr ;
	private Double l150lsgs ;
	private Double m110 ;
	private Double l170c ;
	private Double l275t ;
	private Double lb38a ;
	private Double l275a ;
}
