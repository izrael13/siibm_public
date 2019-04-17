package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.ConsumoKilos;
public interface ConsumoKilosDAO {
	List<ConsumoKilos> findByAll(String fecha_ini, String fecha_fin, Integer per_ini, Integer per_fin);
}
