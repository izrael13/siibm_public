package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_coloresDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_colores;

@Service("catalogo_coloresService")
@Transactional
public class Catalogo_coloresServiceImpl implements Catalogo_coloresService{
	
	@Autowired
	Catalogo_coloresDAO dao;
	
	@Override
	public List<Catalogo_colores> ListaColores() {
		// FIXME Auto-generated method stub
		return dao.ListaColores();
	}

}