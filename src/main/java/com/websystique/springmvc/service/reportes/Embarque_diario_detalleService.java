package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Embarque_diario_detalle;

public interface Embarque_diario_detalleService {
	List<Embarque_diario_detalle> Lista(String fecha, Integer SlpCode);
}
