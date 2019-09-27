package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_maquinas_sap_vw;

@Repository("catalogo_maquinas_sap_vwDAO")
public class Catalogo_maquinas_sap_vwDAOImpl extends AbstractDao<Integer, Catalogo_maquinas_sap_vw> implements Catalogo_maquinas_sap_vwDAO{

	@Override
	public List<Catalogo_maquinas_sap_vw> ListaMaquinas() {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "name");
		
		List<Catalogo_maquinas_sap_vw> ListaMaq = criteriaGeneralList(Params, mOrd);
		return ListaMaq;
	}

	@Override
	public Catalogo_maquinas_sap_vw BuscarxId(Integer id) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"code",id,"EQ"));
		
		Catalogo_maquinas_sap_vw ListaMaq = (Catalogo_maquinas_sap_vw) criteriaGeneralObj(Params);
		return ListaMaq;
	}

	@Override
	public List<Catalogo_maquinas_sap_vw> ListaMaquinas(Integer grupo) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		Params.add(new ParamsGeneral(1,"u_grupo",grupo,"EQ"));
		//mOrd.put("1", "name");
		
		List<Catalogo_maquinas_sap_vw> ListaMaq = criteriaGeneralList(Params, mOrd);
		return ListaMaq;
	}

	@Override
	public List<Catalogo_maquinas_sap_vw> ListaMaquinas(Integer grupo, Integer numTintas) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		Params.add(new ParamsGeneral(1,"u_grupo",grupo,"EQ"));
		Params.add(new ParamsGeneral(1,"u_tintas",numTintas,"GTE"));
		//mOrd.put("1", "name");
		
		List<Catalogo_maquinas_sap_vw> ListaMaq = criteriaGeneralList(Params, mOrd);
		return ListaMaq;
	}

	@Override
	public List<Catalogo_maquinas_sap_vw> ListaMaquinas(Boolean u_grapado) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		Params.add(new ParamsGeneral(1,"u_grapado",1,"LTE"));
		//mOrd.put("1", "name");
		
		List<Catalogo_maquinas_sap_vw> ListaMaq = criteriaGeneralList(Params, mOrd);
		return ListaMaq;
	}


}
