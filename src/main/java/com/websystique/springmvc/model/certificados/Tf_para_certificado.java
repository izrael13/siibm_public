package com.websystique.springmvc.model.certificados;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TF_PARA_CERTIFICADO")
public class Tf_para_certificado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String itemcode;
	private String itemname;
	private String tf;
	private String cardcode_cert;
	private String cardname_cert;
	private String cardcode_normal;
	private String cardname_normal;
	private String proveedor;
	private String impresion;
	private String estilos;
	private String textos;
	private String cod_barras;
	private String color;
	private String flauta;
	private String unionn;
	private Integer pzasxpallet;
	private Integer pzasxatado;
	private String sello;
	private Double dil;
	private Double dia;
	private Double dif;
	private Double del;
	private Double dea;
	private Double def;
	private String ect;
	private String bct;
	private String ect_r;
	private String bct_r;
	private String mullen;
	private String mullen_r;
	private Double cobb;
	private Integer pronostico;
}
