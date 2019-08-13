package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Embarque_diario_detalle;

public interface Embarque_diario_detalleDAO {
	List<Embarque_diario_detalle> Lista(String fecha, Integer SlpCode);
}
