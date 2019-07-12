package com.websystique.springmvc.model.tarjetas.fabricacion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Tarjeta_fabricacion_Busqueda  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer count;
	
	private Integer idcotizacion;
	private String folio_tarjeta;
	private String simbolo;
	
}
