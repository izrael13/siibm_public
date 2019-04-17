package com.websystique.springmvc.dao.reportes;
import java.util.List;

import com.websystique.springmvc.model.reportes.Reporte_consumo_papel;

public interface Reporte_consumo_papelDAO {
	
	List<Reporte_consumo_papel> findByAnioSem(int anio, int semana);
	
	List<Reporte_consumo_papel> findByAnioSemInt(int anio_ini, int semana_ini,int anio_fin, int semana_fin);
	
	List<Reporte_consumo_papel> findAllRegistros();

}
