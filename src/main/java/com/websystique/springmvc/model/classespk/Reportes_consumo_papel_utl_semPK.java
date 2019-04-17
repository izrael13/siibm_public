package com.websystique.springmvc.model.classespk;
import java.io.Serializable;
import java.util.Objects;

public class Reportes_consumo_papel_utl_semPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private int anio;
	private int semana;
	private double ancho;
	//private String tipo_papel;
	
	public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.anio);
        hash = 59 * hash + Objects.hashCode(this.semana);
        hash = 59 * hash + Objects.hashCode(this.ancho);
        //hash = 59 * hash + Objects.hashCode(this.tipo_papel);
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
        final Reportes_consumo_papel_utl_semPK other = (Reportes_consumo_papel_utl_semPK) obj;
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        if (!Objects.equals(this.semana, other.semana)) {
            return false;
        }
        if (!Objects.equals(this.ancho, other.ancho)) {
            return false;
        }
        //if (!Objects.equals(this.tipo_papel, other.tipo_papel)) {
         //   return false;
        //}
        return true;
    }
	
}
