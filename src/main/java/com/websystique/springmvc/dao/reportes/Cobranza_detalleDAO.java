package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Cobranza_detalle;

public interface Cobranza_detalleDAO {
	List<Cobranza_detalle> findByCteVen();
}
