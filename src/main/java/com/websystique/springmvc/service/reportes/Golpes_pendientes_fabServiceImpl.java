package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Golpes_pendientes_fabDAO;
import com.websystique.springmvc.model.reportes.Golpes_pendientes_fab;

@Service("Golpes_pendientes_fabService")
@Transactional
public class Golpes_pendientes_fabServiceImpl implements Golpes_pendientes_fabService{
	
	@Autowired
	Golpes_pendientes_fabDAO dao;

	@Override
	public List<Golpes_pendientes_fab> findByAnioMes(Integer anio, Integer mes) {
		// FIXME Auto-generated method stub
		return dao.findByAnioMes(anio, mes);
	}
	
	
	
}
