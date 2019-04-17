package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Golpes_pendientes_fab;

public interface Golpes_pendientes_fabService {
	List<Golpes_pendientes_fab> findByAnioMes(Integer anio, Integer mes);
}
