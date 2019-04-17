package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Reporte_consumo_papelDAO;
import com.websystique.springmvc.model.reportes.Reporte_consumo_papel;

@Service("reporte_consumo_papelService")
@Transactional
public class Reporte_consumo_papelServiceImpl implements Reporte_consumo_papelService{
	
	@Autowired
	private Reporte_consumo_papelDAO dao;	
	
	@Override
	public List<Reporte_consumo_papel> findByAnioSem(int anio, int semana) {
		// TODO Auto-generated method stub
		return dao.findByAnioSem(anio, semana);
	}

	@Override
	public List<Reporte_consumo_papel> findByAnioSemInt(int anio_ini, int semana_ini, int anio_fin, int semana_fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reporte_consumo_papel> findAllRegistros() {
		return dao.findAllRegistros();
	}
	
}
