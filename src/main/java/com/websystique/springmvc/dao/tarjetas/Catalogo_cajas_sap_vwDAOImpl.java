package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_cajas_sap_vw;

@Repository("catalogo_cajas_sap_vwDAO")
public class Catalogo_cajas_sap_vwDAOImpl extends AbstractDao<Integer,Catalogo_cajas_sap_vw> implements Catalogo_cajas_sap_vwDAO{

	@Override
	public List<Catalogo_cajas_sap_vw> ListaCajas() {
		// FIXME Auto-generated method stub
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		/*Params.add(new ParamsGeneral(1,"idtipocaja","10","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","19","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","20","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","21","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","22","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","23","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","24","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","25","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","26","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","29","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","30","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","31","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","32","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","33","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","34","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","35","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","36","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","37","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","38","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","39","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","40","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","41","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","42","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","45","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","48","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","49","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","50","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","64","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","65","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","47","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","79","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","72","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","55","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","59","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","60","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","61","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","62","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","63","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","66","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","68","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","69","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","70","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","71","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","73","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","74","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","75","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","76","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","77","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","6","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","57","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","58","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","82","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","80","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","81","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","78","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","83","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","84","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","9","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","10","NE"));
		Params.add(new ParamsGeneral(1,"idtipocaja","74","NE")); */

		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "nombrecorto");
		
		List<Catalogo_cajas_sap_vw> ListaCajas = criteriaGeneralList(Params, mOrd);
		return ListaCajas;
	}

	@Override
	public Catalogo_cajas_sap_vw BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		return getByKey(id);
	}

}
