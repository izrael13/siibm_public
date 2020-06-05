package com.websystique.springmvc.model.produccion;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Validacion_ped_rollo")
public class Validacion_ped_rollo {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer validado_ID;
	private String progCorru_ID;
	private String  numerorolloid;
	private Date date_valid;
	private Integer USER_ID;
	
	
	
	public Integer getValidado_ID() {
		return validado_ID;
	}
	public void setValidado_ID(Integer validado_ID) {
		this.validado_ID = validado_ID;
	}
	public String getProgCorru_ID() {
		return progCorru_ID;
	}
	public void setProgCorru_ID(String progCorru_ID) {
		this.progCorru_ID = progCorru_ID;
	}
	public String getNumerorolloid() {
		return numerorolloid;
	}
	public void setNumerorolloid(String numerorolloid) {
		this.numerorolloid = numerorolloid;
	}
	public Date getDate_valid() {
		return date_valid;
	}
	public void setDate_valid(Date date_valid) {
		this.date_valid = date_valid;
	}
	public Integer getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(Integer uSER_ID) {
		USER_ID = uSER_ID;
	}
	

	
	
	
}
