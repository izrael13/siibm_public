package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Flautas_prom_semDAO;
import com.websystique.springmvc.model.reportes.Flautas_prom_sem;

@Service("flautas_prom_semService")
@Transactional
public class Flautas_prom_semServiceImpl implements Flautas_prom_semService{
	
	@Autowired
	Flautas_prom_semDAO dao;
	
	@Override
	public List<Flautas_prom_sem> findbySemAnio(Integer anio, Integer semana) {
		// TODO Auto-generated method stub
		return dao.findbySemAnio(anio, semana);
	}

}
