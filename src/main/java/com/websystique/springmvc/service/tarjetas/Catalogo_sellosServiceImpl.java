package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_sellosDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_sellos;

@Service("catalogo_sellosService")
@Transactional
public class Catalogo_sellosServiceImpl implements Catalogo_sellosService{
	
	@Autowired
	Catalogo_sellosDAO dao;
	
	@Override
	public List<Catalogo_sellos> ListaSellos() {
		// FIXME Auto-generated method stub
		return dao.ListaSellos();
	}

	@Override
	public Catalogo_sellos BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		return dao.BuscarxId(id);
	}

	@Override
	public void Guardar(Catalogo_sellos sello) {
		// TODO Auto-generated method stub
		dao.Guardar(sello);
	}

	@Override
	public void Actualizar(Catalogo_sellos sello) {
		// TODO Auto-generated method stub
		dao.Actualizar(sello);
	}

}
