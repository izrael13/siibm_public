package com.websystique.springmvc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Catalogo_enlaces;
import com.websystique.springmvc.model.ParamsGeneral;

@Repository("catalogo_enlacesDAO")
public class Catalogo_enlacesDAOImpl extends AbstractDao<Integer, Catalogo_enlaces>implements Catalogo_enlacesDAO{

	@Override
	public Catalogo_enlaces BuscarxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public List<Catalogo_enlaces> BuscarxParams(List<ParamsGeneral> Params, Map<String,String> mOrd) {
		return criteriaGeneralList(Params, mOrd);
	}

	@Override
	public void Guardar(Catalogo_enlaces Enlace) {
		persist(Enlace);
		
	}

	@Override
	public void Actualizar(Catalogo_enlaces Enlace) {
		update(Enlace);
	}

	@Override
	public void Eliminar(Catalogo_enlaces Enlace) {
		delete(Enlace);
	}

	@Override
	public List<Catalogo_enlaces> BuscarTodos() {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		mOrd.put("101", "nivel");
		return criteriaGeneralList(Params, mOrd);
	}

}
