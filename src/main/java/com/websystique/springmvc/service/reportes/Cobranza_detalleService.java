package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Cobranza_detalle;

public interface Cobranza_detalleService {
	List<Cobranza_detalle> findByCteVen();
}
