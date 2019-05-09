package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Viajes_mes_ciudad;

public interface Viajes_mes_ciudadService {
	List<Viajes_mes_ciudad> findByAnioMes(Integer anio, Integer mes);
}
