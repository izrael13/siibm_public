package com.websystique.springmvc.model.classespk;

import java.util.Objects;
import java.io.Serializable;

public class semanas_anioPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private int anio;
	private int semana;
	
	public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.anio);
        hash = 59 * hash + Objects.hashCode(this.semana);
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
        final semanas_anioPK other = (semanas_anioPK) obj;
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        if (!Objects.equals(this.semana, other.semana)) {
            return false;
        }
        return true;
    }
}
