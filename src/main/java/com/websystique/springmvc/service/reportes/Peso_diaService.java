package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Peso_dia;

public interface Peso_diaService {
	List<Peso_dia> findByAnioMes(Integer anio, Integer mes);
}
