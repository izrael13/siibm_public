package com.websystique.springmvc.dao.mp.inventario;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.mp.inventario.Catalogo_inventarios_mp;

@Repository("catalogo_inventarios_mpDAO")
public class Catalogo_inventarios_mpDAOImpl extends AbstractDao<Integer,Catalogo_inventarios_mp> implements Catalogo_inventarios_mpDAO{
	
	@Override
	public void Guardar(Catalogo_inventarios_mp obj) {
		persist(obj);
	}

	@Override
	public void Actualizar(Catalogo_inventarios_mp obj) {
		update(obj);
	}

	@Override
	public void Eliminar(Catalogo_inventarios_mp obj) {
		delete(obj);
	}
	@Override
	public Catalogo_inventarios_mp BuscarxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public Catalogo_inventarios_mp BuscarxParamsObj(List<ParamsGeneral> Params) {
		return (Catalogo_inventarios_mp) criteriaGeneralObj(Params);
	}

	@Override
	public List<Catalogo_inventarios_mp> BuscarxParamsList(List<ParamsGeneral> Params, Map<String,String> mOrd) {
		return criteriaGeneralList(Params, mOrd);
	}

	@Override
	public Integer Maximo(String atributo) {
		return (Integer) Max(atributo);
	}

}
