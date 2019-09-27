package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_herramentales;

@Repository("catalogo_herramentalesDAO")
public class Catalogo_herramentalesDAOImpl extends AbstractDao<Integer, Catalogo_herramentales> implements Catalogo_herramentalesDAO{

	@Override
	public Catalogo_herramentales BuscarxId(Integer id) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"id",id,"EQ"));
		return (Catalogo_herramentales) criteriaGeneralObj(Params);
	}

	@Override
	public List<Catalogo_herramentales> BuscarxTipo(Integer tipo) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"tipo",tipo,"EQ"));
		Map<String,String> mOrd =  new HashMap<String, String>();		
		mOrd.put("1", "nombre");
		return criteriaGeneralList(Params, mOrd);
	}

	@Override
	public void Guardar(Catalogo_herramentales CatHerra) {
		// TODO Auto-generated method stub
		persist(CatHerra);
	}

	@Override
	public void Actualziar(Catalogo_herramentales CatHerra) {
		// TODO Auto-generated method stub
		update(CatHerra);
	}

}
