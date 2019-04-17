package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.GolpeskilosmaquinasDAO;
import com.websystique.springmvc.model.reportes.Golpeskilosmaquinas;

@Service("golpeskilosmaquinasService")
@Transactional
public class GolpeskilosmaquinasServiceImpl implements GolpeskilosmaquinasService{

	@Autowired
	GolpeskilosmaquinasDAO dao;
	
	@Override
	public List<Golpeskilosmaquinas> findAllRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Golpeskilosmaquinas> findAllRegistrosByMes(Integer anio, Integer mes) {
		// TODO Auto-generated method stub
		return dao.findAllRegistrosByMes(anio, mes);
	}

}
