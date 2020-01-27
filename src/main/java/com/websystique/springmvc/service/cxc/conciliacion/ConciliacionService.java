package com.websystique.springmvc.service.cxc.conciliacion;

public interface ConciliacionService {
	void GrabarArchivo(String cfdi, String fecha_Fac, String fecha_conci, Double importe);
}
