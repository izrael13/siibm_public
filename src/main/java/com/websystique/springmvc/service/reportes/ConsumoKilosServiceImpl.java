package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.ConsumoKilosDAO;
import com.websystique.springmvc.model.reportes.ConsumoKilos;

@Service("consumoKilosService")
@Transactional
public class ConsumoKilosServiceImpl implements ConsumoKilosService{
	
	@Autowired
	ConsumoKilosDAO dao;
	
	@Override
	public List<ConsumoKilos> findByAll(String fecha_ini, String fecha_fin, Integer per_ini, Integer per_fin) {
		// FIXME Auto-generated method stub
		return dao.findByAll(fecha_ini, fecha_fin, per_ini, per_fin);
	}
	
}
