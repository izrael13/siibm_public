package com.websystique.springmvc.service.reportes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Meses_anioDAO;
import com.websystique.springmvc.model.Meses_anio;

@Service("meses_anioService")
@Transactional
public class Meses_anioServiceImpl implements Meses_anioService{
	
	@Autowired
	private Meses_anioDAO dao;	
	
	
	@Override
	public List<Meses_anio> findallRegistros() {
		// TODO Auto-generated method stub
		return dao.findallRegistros();
	}

}
