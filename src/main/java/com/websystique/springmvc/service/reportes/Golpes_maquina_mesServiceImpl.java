package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Golpes_maquina_mesDAO;
import com.websystique.springmvc.model.reportes.Golpes_maquina_mes;

@Service("troqueladoraProdService")
@Transactional
public class Golpes_maquina_mesServiceImpl implements Golpes_maquina_mesService{
	
	@Autowired
	Golpes_maquina_mesDAO dao;

	@Override
	public List<Golpes_maquina_mes> findAllRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Golpes_maquina_mes> findAllRegistrosByMes(Integer anio, Integer mes) {
		// TODO Auto-generated method stub
		return dao.findAllRegistrosByMes(anio, mes);
	}
	
	
}
