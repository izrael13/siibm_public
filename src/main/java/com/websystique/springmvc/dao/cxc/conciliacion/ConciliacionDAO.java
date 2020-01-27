package com.websystique.springmvc.dao.cxc.conciliacion;

public interface ConciliacionDAO {
	void GrabarArchivo(String cfdi, String fecha_Fac, String fecha_conci, Double importe);
}
