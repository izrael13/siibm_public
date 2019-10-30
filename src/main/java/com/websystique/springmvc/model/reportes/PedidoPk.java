package com.websystique.springmvc.model.reportes;

import java.io.Serializable;
import java.util.Objects;

public class PedidoPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
//	private Integer cliente;

	public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
   //     hash = 59 * hash + Objects.hashCode(this.cliente);
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
        final PedidoPk other = (PedidoPk) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
   //     if (!Objects.equals(this.cliente, other.cliente)) {
  //          return false;
   //    }
        return true;
    }

	
}
