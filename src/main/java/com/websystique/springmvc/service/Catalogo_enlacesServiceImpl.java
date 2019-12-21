package com.websystique.springmvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.Catalogo_enlacesDAO;
import com.websystique.springmvc.model.Catalogo_enlaces;
import com.websystique.springmvc.model.ParamsGeneral;

@Service("catalogo_enlacesService")
@Transactional
public class Catalogo_enlacesServiceImpl implements Catalogo_enlacesService{

	@Autowired Catalogo_enlacesDAO dao;
	
	@Override
	public Catalogo_enlaces BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

	@Override
	public List<Catalogo_enlaces> BuscarxParams(List<ParamsGeneral> Params, Map<String,String> mOrd) {
		return dao.BuscarxParams(Params, mOrd);
	}

	@Override
	public void Guardar(Catalogo_enlaces Enlace) {
		dao.Guardar(Enlace);
	}

	@Override
	public void Actualizar(Catalogo_enlaces Enlace) {
		dao.Actualizar(Enlace);
	}

	@Override
	public void Eliminar(Catalogo_enlaces Enlace) {
		dao.Eliminar(Enlace);
	}

	@Override
	public List<Catalogo_enlaces> BuscarTodos() {
		return dao.BuscarTodos();
	}

}
