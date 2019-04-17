package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Paros_maquina_diaDAO;
import com.websystique.springmvc.model.reportes.Paros_maquina_dia;

@Service("paros_maquina_diaService")
@Transactional
public class Paros_maquina_diaServiceImpl implements Paros_maquina_diaService{
	
	@Autowired
	Paros_maquina_diaDAO dao;
	
	@Override
	public List<Paros_maquina_dia> findbyAnioMes(Integer anio, Integer mes) {
		// TODO Auto-generated method stub
		return dao.findbyAnioMes(anio, mes);
	}
	
}
