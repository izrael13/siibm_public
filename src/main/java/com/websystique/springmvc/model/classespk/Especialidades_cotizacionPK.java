package com.websystique.springmvc.model.classespk;

import java.io.Serializable;
import java.util.Objects;

public class Especialidades_cotizacionPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer IDDETALLE;
	private Integer IDCOTIZACION;
	private Integer IDESPECIALIDAD;
	
	public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.IDDETALLE);
        hash = 59 * hash + Objects.hashCode(this.IDCOTIZACION);
        hash = 59 * hash + Objects.hashCode(this.IDESPECIALIDAD);
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
        final Especialidades_cotizacionPK other = (Especialidades_cotizacionPK) obj;
        if (!Objects.equals(this.IDDETALLE, other.IDDETALLE)) {
            return false;
        }
        if (!Objects.equals(this.IDCOTIZACION, other.IDCOTIZACION)) {
            return false;
        }
        if (!Objects.equals(this.IDESPECIALIDAD, other.IDESPECIALIDAD)) {
            return false;
        }
        //if (!Objects.equals(this.tipo_papel, other.tipo_papel)) {
         //   return false;
        //}
        return true;
    }
}
