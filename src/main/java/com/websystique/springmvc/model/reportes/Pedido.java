package com.websystique.springmvc.model.reportes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data   // lombok
@Entity
@Table(name = "PedidosAtrasados")
@IdClass(value = PedidoPk.class)
public class Pedido implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PEDIDO")
	private Integer id;
	@Id
	@Column(name="CLIENTE")
	private String cliente;
	
	@Column(name="SIMBOLO")
	private String simbolo;
	
	@Column(name="FECHA")
	private String fecha;
	
	@Column(name="KILOS")
	private String kilos;
	
	@Column(name="GOLPES")
	private String golpes;
	
	@Column(name="MAQUINA")
	private String maquina;
	
	
	
	
	
//	esc_id  int not null,
//    alu_exp int NOT NULL,  
//    alu_nombre varchar(10) NOT NULL,  
//    alu_fecNac date,
//	primary key (esc_id, alu_exp) 
}
