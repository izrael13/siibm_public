package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Paros_maquina_dia;

public interface Paros_maquina_diaService {
	List<Paros_maquina_dia> findbyAnioMes(Integer anio, Integer mes);
}
