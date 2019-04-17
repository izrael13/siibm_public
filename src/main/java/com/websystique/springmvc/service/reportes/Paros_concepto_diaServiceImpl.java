package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Paros_concepto_diaDAO;
import com.websystique.springmvc.model.reportes.Paros_concepto_dia;

@Service("paros_concepto_diaService")
@Transactional
public class Paros_concepto_diaServiceImpl implements Paros_concepto_diaService{
	
	@Autowired
	Paros_concepto_diaDAO dao;
	
	@Override
	public List<Paros_concepto_dia> findAllParosConceptodia(String fecha_ini) {
		// TODO Auto-generated method stub
		return dao.findAllParosConceptodia(fecha_ini);
	}
	
}
