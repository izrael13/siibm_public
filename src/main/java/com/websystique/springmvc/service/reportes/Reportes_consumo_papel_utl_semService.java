package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Reportes_consumo_papel_utl_sem;

public interface Reportes_consumo_papel_utl_semService {

	List<Reportes_consumo_papel_utl_sem> findByAnioSem(int anio, int semana);
	
	List<Reportes_consumo_papel_utl_sem> findByAnioSemInt(int anio_ini, int semana_ini,int anio_fin, int semana_fin);
	
	List<Reportes_consumo_papel_utl_sem> findAllRegistros();
	
}