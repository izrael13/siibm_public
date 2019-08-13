package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Embarque_diario_detalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer tipo;
	private Integer docnum;
	private String numatcard;
	private String dscription;
	private Double quantity;
	private Date docdate;
	private Integer doctime;
	private String u_chofer;
	private String cardcode;
	private String cardname;
	private Double u_peso;
	private String baseref;
	private String u_maniobras;
	private String u_embarca;
	private String comments;
	private String slpname;

}
