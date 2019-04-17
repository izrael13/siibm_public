package com.websystique.springmvc.model.classespk;

import java.util.Objects;
import java.io.Serializable;

public class Reporte_consumo_papelPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private int anio;
	private int semana;
	private int nivel;
	private double ancho;
	
	public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.anio);
        hash = 59 * hash + Objects.hashCode(this.semana);
        hash = 59 * hash + Objects.hashCode(this.nivel);
        hash = 59 * hash + Objects.hashCode(this.ancho);
        return hash;
    }
	
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reporte_consumo_papelPK other = (Reporte_consumo_papelPK) obj;
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        if (!Objects.equals(this.semana, other.semana)) {
            return false;
        }
        if (!Objects.equals(this.nivel, other.nivel)) {
            return false;
        }
        if (!Objects.equals(this.ancho, other.ancho)) {
            return false;
        }
        return true;
    }

}
