package com.websystique.springmvc.dao.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.reportes.Inventario_almacen;


@Repository("inventario_almacenDAO")
public class Inventario_almacenDAOImpl extends AbstractDao<Integer, Inventario_almacen> implements Inventario_almacenDAO{

	@Override
	public List<Inventario_almacen> findByAlmacen(String almacen, Integer SlpCode) {
		List<Inventario_almacen> results = null;
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		
		Params.add(new ParamsGeneral(1,"whscode",almacen,"EQ"));
		if(SlpCode > 0)
			Params.add(new ParamsGeneral(1,"slpcode",SlpCode,"EQ"));
		
		Map<String,String> mOrd =  new HashMap<String, String>();
		mOrd.put("1", "whscode");
		mOrd.put("2", "cardname");
		results = criteriaGeneralList(Params, mOrd);
		return results;
	}
	
}
