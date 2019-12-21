package com.websystique.springmvc.service.mp.inventario;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.mp.inventario.Catalogo_inventarios_mpDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.mp.inventario.Catalogo_inventarios_mp;

@Service("catalogo_inventarios_mpService")
@Transactional
public class Catalogo_inventarios_mpServiceImpl implements Catalogo_inventarios_mpService{

	@Autowired Catalogo_inventarios_mpDAO dao;
	
	@Override
	public void Guardar(Catalogo_inventarios_mp obj) {
		dao.Guardar(obj);
	}

	@Override
	public void Actualizar(Catalogo_inventarios_mp obj) {
		dao.Actualizar(obj);
	}

	@Override
	public void Eliminar(Catalogo_inventarios_mp obj) {
		dao.Eliminar(obj);
	}
	@Override
	public Catalogo_inventarios_mp BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

	@Override
	public Catalogo_inventarios_mp BuscarxParamsObj(List<ParamsGeneral> Params) {
		return dao.BuscarxParamsObj(Params);
	}

	@Override
	public List<Catalogo_inventarios_mp> BuscarxParamsList(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return dao.BuscarxParamsList(Params, mOrd);
	}

	@Override
	public Integer Maximo(String atributo) {
		return dao.Maximo(atributo);
	}

}
