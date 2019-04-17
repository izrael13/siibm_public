package com.websystique.springmvc.service.reportes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Semanas_anioDAO;
import com.websystique.springmvc.model.Semanas_anio;

@Service("semanas_anioService")
@Transactional
public class Semanas_anioServiceImpl implements Semanas_anioService{

	@Autowired
	private Semanas_anioDAO dao;	
	
	@Override
	public Semanas_anio findByAnioSem(int anio, int semana) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Semanas_anio> findByAnioSemInt(int anio_ini, int semana_ini, int anio_fin, int semana_fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer,String> findAllSemanas() {
		return dao.findAllSemanas();
	}

}
