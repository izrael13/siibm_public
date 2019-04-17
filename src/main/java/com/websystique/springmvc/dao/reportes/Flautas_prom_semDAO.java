package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Flautas_prom_sem;

public interface Flautas_prom_semDAO {
	List<Flautas_prom_sem> findbySemAnio(Integer anio, Integer semana);
}
