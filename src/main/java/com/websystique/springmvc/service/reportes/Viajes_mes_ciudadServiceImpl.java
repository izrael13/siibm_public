package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Viajes_mes_ciudadDAO;
import com.websystique.springmvc.model.reportes.Viajes_mes_ciudad;

@Service("viajes_mes_ciudadService")
@Transactional
public class Viajes_mes_ciudadServiceImpl implements Viajes_mes_ciudadService{

	@Autowired
	Viajes_mes_ciudadDAO dao;
	
	@Override
	public List<Viajes_mes_ciudad> findByAnioMes(Integer anio, Integer mes) {
		// FIXME Auto-generated method stub
		return dao.findByAnioMes(anio, mes);
	}

}
