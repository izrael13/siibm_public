package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Reportes_consumo_papel_acum_mes;


public interface Reportes_consumo_papel_acum_mesDAO {
	List<Reportes_consumo_papel_acum_mes> findAllRegistros();
	List<Reportes_consumo_papel_acum_mes> findAllRegistrosByMes(Integer anio,Integer mes);
}
