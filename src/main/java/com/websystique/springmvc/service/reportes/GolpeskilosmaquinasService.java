package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Golpeskilosmaquinas;

public interface GolpeskilosmaquinasService {
	List<Golpeskilosmaquinas> findAllRegistros();
	List<Golpeskilosmaquinas> findAllRegistrosByMes(Integer anio,Integer mes);
}
