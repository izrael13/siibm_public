package com.websystique.springmvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "MENUS")
public class Menus implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer master_id; 
	private String nombre;
	private String url;
	private Integer padre;
	private Integer ismenu;
	private Integer perfil_acceso;
	private String icon;
	private Integer nivel;
}
