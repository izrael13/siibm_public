package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Viajes_mes_ciudad;

public interface Viajes_mes_ciudadDAO {
	List<Viajes_mes_ciudad> findByAnioMes(Integer anio, Integer mes);
}
