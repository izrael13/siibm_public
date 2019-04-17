package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Peso_diaDAO;
import com.websystique.springmvc.model.reportes.Peso_dia;

@Service("peso_diaService")
@Transactional
public class Peso_diaServiceImpl implements Peso_diaService{
	
	@Autowired
	private Peso_diaDAO dao;	
	
	@Override
	public List<Peso_dia> findByAnioMes(Integer anio, Integer mes) {
		// FIXME Auto-generated method stub
		return dao.findByAnioMes(anio, mes);
	}

}
