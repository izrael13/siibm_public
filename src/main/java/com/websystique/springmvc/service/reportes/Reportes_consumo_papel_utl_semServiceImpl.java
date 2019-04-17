package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Reportes_consumo_papel_utl_semDAO;
import com.websystique.springmvc.model.reportes.Reportes_consumo_papel_utl_sem;

@Service("reportes_consumo_papel_utl_semService")
@Transactional
public class Reportes_consumo_papel_utl_semServiceImpl implements Reportes_consumo_papel_utl_semService{
	
	@Autowired
	private Reportes_consumo_papel_utl_semDAO dao;
	
	@Override
	public List<Reportes_consumo_papel_utl_sem> findByAnioSem(int anio, int semana) {
		return dao.findByAnioSem(anio, semana);
	}

	@Override
	public List<Reportes_consumo_papel_utl_sem> findByAnioSemInt(int anio_ini, int semana_ini, int anio_fin,
			int semana_fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reportes_consumo_papel_utl_sem> findAllRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

}
