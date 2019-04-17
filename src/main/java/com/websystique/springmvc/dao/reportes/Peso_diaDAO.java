package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Peso_dia;

public interface Peso_diaDAO {
	List<Peso_dia> findByAnioMes(Integer anio, Integer mes);
}
