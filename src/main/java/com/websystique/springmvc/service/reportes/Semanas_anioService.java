package com.websystique.springmvc.service.reportes;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.Semanas_anio;

public interface Semanas_anioService {
	
	Semanas_anio findByAnioSem(int anio, int semana);
	
	List<Semanas_anio> findByAnioSemInt(int anio_ini, int semana_ini,int anio_fin, int semana_fin);
	
	Map<Integer, String> findAllSemanas();
}
