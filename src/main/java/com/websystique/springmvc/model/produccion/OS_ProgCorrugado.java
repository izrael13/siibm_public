package com.websystique.springmvc.model.produccion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OS_ProgCorrugado")


	public class OS_ProgCorrugado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private Integer ID;
	private String Programa;
	private String Pedido;
	private String Cliente;
	private String Simbolo;
	private String Resistencia;
	private Double PesoPapel;
	private Double ALamina;
	private Double LdeLamina;
	private Double MTSLineal;
	private Double MtsLinealR;
	private Double Ancho;
	private Integer TotalPedido;
	private Integer Programadas;
	private Date FechaCorrugado;
	private Date FechaEntrega;
	private Double KgsPro;
	private Double KgsReal;
	private String SpecID;
	private Integer Formato;
	private String Status;
	private String Tarjeta; 
	private String LINER1;
	private Double LINER1WIDTH;
	private String MEDIUM1;
	private Double MEDIUM1WIDTH;
	private String LINER2;
	private Double LINER2WIDTH;
	private String MEDIUM2;
	private Double MEDIUM2WIDTH;
	private String LINER3;
	private Double LINER3WIDTH;
	private String FLUTE;
	
	
	
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getPrograma() {
		return Programa;
	}
	public void setPrograma(String programa) {
		Programa = programa;
	}

	public String getPedido() {
		return Pedido;
	}
	public void setPedido(String pedido) {
		Pedido = pedido;
	}
	public String getCliente() {
		return Cliente;
	}
	public void setCliente(String cliente) {
		Cliente = cliente;
	}
	public String getSimbolo() {
		return Simbolo;
	}
	public void setSimbolo(String simbolo) {
		Simbolo = simbolo;
	}
	public String getResistencia() {
		return Resistencia;
	}
	public void setResistencia(String resistencia) {
		Resistencia = resistencia;
	}
	public Double getPesoPapel() {
		return PesoPapel;
	}
	public void setPesoPapel(Double pesoPapel) {
		PesoPapel = pesoPapel;
	}
	public Double getALamina() {
		return ALamina;
	}
	public void setALamina(Double aLamina) {
		ALamina = aLamina;
	}
	public Double getLde_Lamina() {
		return LdeLamina;
	}
	public void setLde_Lamina(Double lde_Lamina) {
		LdeLamina = lde_Lamina;
	}
	public Double getMTSLineal() {
		return MTSLineal;
	}
	public void setMTSLineal(Double mTSLineal) {
		MTSLineal = mTSLineal;
	}
	public Double getMtsLinealR() {
		return MtsLinealR;
	}
	public void setMtsLinealR(Double mtsLinealR) {
		MtsLinealR = mtsLinealR;
	}
	public Double getAncho() {
		return Ancho;
	}
	public void setAncho(Double ancho) {
		Ancho = ancho;
	}
	public Integer getTotalPedido() {
		return TotalPedido;
	}
	public void setTotalPedido(Integer totalPedido) {
		TotalPedido = totalPedido;
	}
	public Integer getProgramadas() {
		return Programadas;
	}
	public void setProgramadas(Integer programadas) {
		Programadas = programadas;
	}
	public Date getFechaCorrugado() {
		return FechaCorrugado;
	}
	public void setFechaCorrugado(Date fechaCorrugado) {
		FechaCorrugado = fechaCorrugado;
	}
	public Date getFechaEntrega() {
		return FechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		FechaEntrega = fechaEntrega;
	}
	public Double getKgsPro() {
		return KgsPro;
	}
	public void setKgsPro(Double kgsPro) {
		KgsPro = kgsPro;
	}
	public Double getKgsReal() {
		return KgsReal;
	}
	public void setKgsReal(Double kgsReal) {
		KgsReal = kgsReal;
	}
	public String getSpecID() {
		return SpecID;
	}
	public void setSpecID(String specID) {
		SpecID = specID;
	}
	public Integer getFormato() {
		return Formato;
	}
	public void setFormato(Integer formato) {
		Formato = formato;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getTarjeta() {
		return Tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		Tarjeta = tarjeta;
	}
	public String getLINER1() {
		return LINER1;
	}
	public void setLINER1(String lINER1) {
		LINER1 = lINER1;
	}
	public Double getLINER1WIDTH() {
		return LINER1WIDTH;
	}
	public void setLINER1WIDTH(Double lINER1WIDTH) {
		LINER1WIDTH = lINER1WIDTH;
	}
	public String getMEDIUM1() {
		return MEDIUM1;
	}
	public void setMEDIUM1(String mEDIUM1) {
		MEDIUM1 = mEDIUM1;
	}
	public Double getMEDIUM1WIDTH() {
		return MEDIUM1WIDTH;
	}
	public void setMEDIUM1WIDTH(Double mEDIUM1WIDTH) {
		MEDIUM1WIDTH = mEDIUM1WIDTH;
	}
	public String getLINER2() {
		return LINER2;
	}
	public void setLINER2(String lINER2) {
		LINER2 = lINER2;
	}
	public Double getLINER2WIDTH() {
		return LINER2WIDTH;
	}
	public void setLINER2WIDTH(Double lINER2WIDTH) {
		LINER2WIDTH = lINER2WIDTH;
	}
	public String getMEDIUM2() {
		return MEDIUM2;
	}
	public void setMEDIUM2(String mEDIUM2) {
		MEDIUM2 = mEDIUM2;
	}
	public Double getMEDIUM2WIDTH() {
		return MEDIUM2WIDTH;
	}
	public void setMEDIUM2WIDTH(Double mEDIUM2WIDTH) {
		MEDIUM2WIDTH = mEDIUM2WIDTH;
	}
	public String getLINER3() {
		return LINER3;
	}
	public void setLINER3(String lINER3) {
		LINER3 = lINER3;
	}
	public Double getLINER3WIDTH() {
		return LINER3WIDTH;
	}
	public void setLINER3WIDTH(Double lINER3WIDTH) {
		LINER3WIDTH = lINER3WIDTH;
	}
	public String getFLUTE() {
		return FLUTE;
	}
	public void setFLUTE(String fLUTE) {
		FLUTE = fLUTE;
	}
	

	
	
}
