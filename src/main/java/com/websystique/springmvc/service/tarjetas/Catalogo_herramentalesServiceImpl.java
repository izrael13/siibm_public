package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_herramentalesDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_herramentales;

@Service("catalogo_herramentalesService")
@Transactional
public class Catalogo_herramentalesServiceImpl implements Catalogo_herramentalesService{

	@Autowired
	Catalogo_herramentalesDAO dao;
	
	@Override
	public Catalogo_herramentales BuscarxId(Integer id) {
		// TODO Auto-generated method stub
		return dao.BuscarxId(id);
	}

	@Override
	public List<Catalogo_herramentales> BuscarxTipo(Integer tipo) {
		// TODO Auto-generated method stub
		return dao.BuscarxTipo(tipo);
	}

	@Override
	public void Guardar(Catalogo_herramentales CatHerra) {
		// TODO Auto-generated method stub
		dao.Guardar(CatHerra);
	}

	@Override
	public void Actualziar(Catalogo_herramentales CatHerra) {
		// TODO Auto-generated method stub
		dao.Actualziar(CatHerra);
	}

}
