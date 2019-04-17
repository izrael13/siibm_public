package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.ConsumoKilos;

public interface ConsumoKilosService {
	List<ConsumoKilos> findByAll(String fecha_ini, String fecha_fin, Integer per_ini, Integer per_fin);
}
