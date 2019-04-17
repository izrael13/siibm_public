package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "inventario_almacen")
public class Inventario_almacen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String itemcode;
	private String itemname;
	private Integer batchnum;
	private Double millar;
	private Double kilos;
	private String cardname;
	private String slpname;
	private Date fecha;
	private Date fechaentregasap;
}
