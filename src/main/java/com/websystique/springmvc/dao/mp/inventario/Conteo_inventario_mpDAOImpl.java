package com.websystique.springmvc.dao.mp.inventario;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.mp.inventario.Conteo_inventario_mp;

@Repository("conteo_inventario_mpDAO")
public class Conteo_inventario_mpDAOImpl extends AbstractDao<Integer,Conteo_inventario_mp> implements Conteo_inventario_mpDAO{

	@Override
	public void Guardar(Conteo_inventario_mp obj) {
		persist(obj);
	}
	
	@Override
	public void Guardar(List<Conteo_inventario_mp> Listaobj) {
		persist(Listaobj);
	}
	
	@Override
	public void Actualizar(Conteo_inventario_mp obj) {
		update(obj);
	}

	@Override
	public void Eliminar(Conteo_inventario_mp obj) {
		delete(obj);
	}
	
	@Override
	public void Eliminar(List<Conteo_inventario_mp> Listaobj) {
		delete(Listaobj);		
	}
	
	@Override
	public Conteo_inventario_mp BuscarxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public Conteo_inventario_mp BuscarxParamsObj(List<ParamsGeneral> Params) {
		return (Conteo_inventario_mp) criteriaGeneralObj(Params);
	}

	@Override
	public List<Conteo_inventario_mp> BuscarxParamsList(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return criteriaGeneralList(Params, mOrd);
	}

	@Override
	public Integer Maximo(String atributo) {
		return (Integer) Max(atributo);
	}

}
