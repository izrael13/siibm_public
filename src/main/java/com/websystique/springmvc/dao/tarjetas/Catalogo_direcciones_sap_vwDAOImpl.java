package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;

@Repository("catalogo_direcciones_sap_vwDAO")
public class Catalogo_direcciones_sap_vwDAOImpl extends AbstractDao<Integer,Catalogo_direcciones_sap_vw> implements Catalogo_direcciones_sap_vwDAO{

	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDir() {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "address");
		
		List<Catalogo_direcciones_sap_vw> ListaDir =  criteriaQuery(mRes,mOrd);
		return ListaDir;
	}

	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDirCardCode(String CardCode) {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		
		mRes.put("cardcode",CardCode);
		
		List<Catalogo_direcciones_sap_vw> ListaDir =  criteriaQueryEqList(mRes);
		return ListaDir;
	}

	@Override
	public List<Catalogo_direcciones_sap_vw> ListaDirCardCodeNumLine(String CardCode, Integer NumLine) {
		// FIXME Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,Integer> mResInt =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mRes.put("cardcode",CardCode);
		mResInt.put("linenum",NumLine);
		
		mOrd.put("1", "address");
		
		List<Catalogo_direcciones_sap_vw> ListaDir =  criteriaQueryEqStrInt(mRes,mResInt,mOrd);
		return ListaDir;
	}

}
