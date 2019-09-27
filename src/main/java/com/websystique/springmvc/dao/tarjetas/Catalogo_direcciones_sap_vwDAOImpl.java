package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;

@Repository("catalogo_direcciones_sap_vwDAO")
public class Catalogo_direcciones_sap_vwDAOImpl extends AbstractDao<Integer,Catalogo_direcciones_sap_vw> implements Catalogo_direcciones_sap_vwDAO{

	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDir() {
		// FIXME Auto-generated method stub
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "address");
		
		List<Catalogo_direcciones_sap_vw> ListaDir =  criteriaGeneralList(Params, mOrd);
		return ListaDir;
	}

	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDirCardCode(String CardCode) {
		// FIXME Auto-generated method stub
		/*Map<String,String> mRes =  new HashMap<String, String>();
		mRes.put("cardcode",CardCode);*/
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"cardcode",CardCode,"EQ"));
		Map<String,String> Ord =  new HashMap<String, String>();
		List<Catalogo_direcciones_sap_vw> ListaDir =  criteriaGeneralList(Params,Ord);
		return ListaDir;
	}

	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDirCardCodeNumLine(String CardCode, Integer NumLine) {
		// FIXME Auto-generated method stub
		//Map<String,String> mRes =  new HashMap<String, String>();
		//Map<String,Integer> mResInt =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		/*mRes.put("cardcode",CardCode);
		mResInt.put("linenum",NumLine);*/
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"cardcode",CardCode,"EQ"));
		Params.add(new ParamsGeneral(1,"linenum",NumLine,"EQ"));
		mOrd.put("1", "address");
		
		List<Catalogo_direcciones_sap_vw> ListaDir =  criteriaGeneralList(Params,mOrd);
		return ListaDir;
	}

	@Override
	public Catalogo_direcciones_sap_vw DirCardCodeNumLine(String CardCode, Integer NumLine) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"cardcode",CardCode,"EQ"));
		Params.add(new ParamsGeneral(1,"linenum",NumLine,"EQ"));
		
		Catalogo_direcciones_sap_vw Dir =  (Catalogo_direcciones_sap_vw) criteriaGeneralObj(Params);
		return Dir;
	}

}
