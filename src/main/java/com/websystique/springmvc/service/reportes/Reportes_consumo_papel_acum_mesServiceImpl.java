package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Reportes_consumo_papel_acum_mesDAO;
import com.websystique.springmvc.model.reportes.Reportes_consumo_papel_acum_mes;

@Service("reportes_consumo_papel_acum_mesService")
@Transactional
public class Reportes_consumo_papel_acum_mesServiceImpl implements Reportes_consumo_papel_acum_mesService{

	@Autowired
	Reportes_consumo_papel_acum_mesDAO dao;
	
	@Override
	public List<Reportes_consumo_papel_acum_mes> findAllRegistros() {
		// TODO Auto-generated method stub
		return dao.findAllRegistros();
	}

	@Override
	public List<Reportes_consumo_papel_acum_mes> findAllRegistrosByMes(Integer anio, Integer mes) {
		// TODO Auto-generated method stub
		return dao.findAllRegistrosByMes(anio, mes);
	}
	
}
