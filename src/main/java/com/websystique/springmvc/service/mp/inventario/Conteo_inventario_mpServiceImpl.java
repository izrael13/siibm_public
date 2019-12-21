package com.websystique.springmvc.service.mp.inventario;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.mp.inventario.Conteo_inventario_mpDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.mp.inventario.Conteo_inventario_mp;

@Service("conteo_inventario_mpService")
@Transactional
public class Conteo_inventario_mpServiceImpl implements Conteo_inventario_mpService{
	
	@Autowired Conteo_inventario_mpDAO dao;
	
	@Override
	public void Guardar(Conteo_inventario_mp obj) {
		dao.Guardar(obj);
	}
	
	@Override
	public void Guardar(List<Conteo_inventario_mp> Listaobj) {
		dao.Guardar(Listaobj);
	}
	
	@Override
	public void Actualizar(Conteo_inventario_mp obj) {
		dao.Actualizar(obj);
	}

	@Override
	public void Eliminar(Conteo_inventario_mp obj) {
		dao.Eliminar(obj);
	}
	
	@Override
	public void Eliminar(List<Conteo_inventario_mp> Listaobj) {
		dao.Eliminar(Listaobj);
	}

	@Override
	public Conteo_inventario_mp BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

	@Override
	public Conteo_inventario_mp BuscarxParamsObj(List<ParamsGeneral> Params) {
		return dao.BuscarxParamsObj(Params);
	}

	@Override
	public List<Conteo_inventario_mp> BuscarxParamsList(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return dao.BuscarxParamsList(Params, mOrd);
	}

	@Override
	public Integer Maximo(String atributo) {
		return dao.Maximo(atributo);
	}

}
