package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Golpes_maquina_mes;


public interface Golpes_maquina_mesDAO {
	
	List<Golpes_maquina_mes> findAllRegistros();
	List<Golpes_maquina_mes> findAllRegistrosByMes(Integer anio,Integer mes);
	
}
