package com.websystique.springmvc.model.produccion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="RollosPapel")
	public class RollosPapel {
	
		private String PapelID;
		@Id
		@GeneratedValue (strategy= GenerationType.IDENTITY)
		private String numeroRolloID;
		private String Descripcion;
		private String tipoPapel;
		private Double Ancho;
		private Double pesoBase;
		private Double pesoRollo;
		private String Resistencia;
		private String numeroLote;
		private Double Saldo;
		private String numeroAlmacen;
		private String RemisionID;
		private String partidaREM;
		private Double cantidadRecibidaRem;
		private Double cantidadPedidaRem;
		private String unidadMedidaRem;
		private String ordenCompraID;
		private String UbicacionID;
		private String origenRollo;
		private Integer VOD;
		
		
		public String getPapelID() {
			return PapelID;
		}
		public void setPapelID(String papelID) {
			PapelID = papelID;
		}
		public String getnumeroRolloID() {
			return numeroRolloID;
		}
		public void setnumeroRolloID(String numerorolloid) {
			this.numeroRolloID = numerorolloid;
		}
		public String getDescripcion() {
			return Descripcion;
		}
		public void setDescripcion(String descripcion) {
			Descripcion = descripcion;
		}
		public String getTipoPapel() {
			return tipoPapel;
		}
		public void setTipoPapel(String tipoPapel) {
			this.tipoPapel = tipoPapel;
		}
		public Double getAncho() {
			return Ancho;
		}
		public void setAncho(Double ancho) {
			Ancho = ancho;
		}
		public Double getPesoBase() {
			return pesoBase;
		}
		public void setPesoBase(Double pesoBase) {
			this.pesoBase = pesoBase;
		}
		public Double getPesoRollo() {
			return pesoRollo;
		}
		public void setPesoRollo(Double pesoRollo) {
			this.pesoRollo = pesoRollo;
		}
		public String getResistencia() {
			return Resistencia;
		}
		public void setResistencia(String resistencia) {
			Resistencia = resistencia;
		}
		public String getNumeroLote() {
			return numeroLote;
		}
		public void setNumeroLote(String numeroLote) {
			this.numeroLote = numeroLote;
		}
		public Double getSaldo() {
			return Saldo;
		}
		public void setSaldo(Double saldo) {
			Saldo = saldo;
		}
		public String getNumeroAlmacen() {
			return numeroAlmacen;
		}
		public void setNumeroAlmacen(String numeroAlmacen) {
			this.numeroAlmacen = numeroAlmacen;
		}
		public String getRemisionID() {
			return RemisionID;
		}
		public void setRemisionID(String remisionID) {
			RemisionID = remisionID;
		}
		public String getPartidaREM() {
			return partidaREM;
		}
		public void setPartidaREM(String partidaREM) {
			this.partidaREM = partidaREM;
		}
		public Double getCantidadRecibidaRem() {
			return cantidadRecibidaRem;
		}
		public void setCantidadRecibidaRem(Double cantidadRecibidaRem) {
			this.cantidadRecibidaRem = cantidadRecibidaRem;
		}
		public Double getCantidadPerdidaRem() {
			return cantidadPedidaRem;
		}
		public void setCantidadPerdidaRem(Double cantidadPerdidaRem) {
			this.cantidadPedidaRem = cantidadPerdidaRem;
		}
		public String getUnidadMedidaRem() {
			return unidadMedidaRem;
		}
		public void setUnidadMedidaRem(String unidadMedidaRem) {
			this.unidadMedidaRem = unidadMedidaRem;
		}
		public String getOrdenCompraID() {
			return ordenCompraID;
		}
		public void setOrdenCompraID(String ordenCompraID) {
			this.ordenCompraID = ordenCompraID;
		}
		public String getUbicacionID() {
			return UbicacionID;
		}
		public void setUbicacionID(String ubicacionID) {
			UbicacionID = ubicacionID;
		}
		public String getOrigenRollo() {
			return origenRollo;
		}
		public void setOrigenRollo(String origenRollo) {
			this.origenRollo = origenRollo;
		}
		public Integer getVOD() {
			return VOD;
		}
		public void setVOD(Integer vOD) {
			VOD = vOD;
		}
	
	
}
