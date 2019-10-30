package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_colores;

@Repository("catalogo_coloresDAO")
public class Catalogo_coloresDAOImpl  extends AbstractDao<Integer,Catalogo_colores> implements Catalogo_coloresDAO{

	@Override
	public List<Catalogo_colores> ListaColores() {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "color");
		
		List<Catalogo_colores> ListaColores = criteriaGeneralList(Params, mOrd);
		return ListaColores;
	}

	@Override
	public Catalogo_colores BuscarxId(Integer id) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"id",id == null ? 0 : id,"EQ"));
		return (Catalogo_colores) criteriaGeneralObj(Params);
	}

	@Override
	public void Guardar(Catalogo_colores color) {
		// TODO Auto-generated method stub
		persist(color);
	}

	@Override
	public void Actualizar(Catalogo_colores color) {
		// TODO Auto-generated method stub
		update(color);
	}

}
