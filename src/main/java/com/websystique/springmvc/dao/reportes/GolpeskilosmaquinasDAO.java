package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Golpeskilosmaquinas;

public interface GolpeskilosmaquinasDAO {
	List<Golpeskilosmaquinas> findAllRegistros();
	List<Golpeskilosmaquinas> findAllRegistrosByMes(Integer anio,Integer mes);
}
