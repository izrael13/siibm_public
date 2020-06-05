package com.websystique.springmvc.dao.produccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.produccion.RollosPapel;

@Repository("RollosPapelDAO")
public class RollosPapelDAOImpl  extends AbstractDao<String, RollosPapel> implements RollosPapelDAO{

	@Override
	public RollosPapel RollosPapel(String numeroRolloID) {
		// TODO Auto-generated method stub
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"numeroRolloID",numeroRolloID,"EQ"));
		return (RollosPapel) criteriaGeneralObj(Params);
	
	
		
	}

	@Override
	public List<RollosPapel> ListRollos(String numeroRolloID) {
		List<RollosPapel> ListRollos = new ArrayList<RollosPapel>();
		List<ParamsGeneral> Params= new ArrayList<ParamsGeneral>();
		//consecutivo,field's name, value, equals - not equals - etc (AbstracDAO class)
		Params.add(new ParamsGeneral(1,"numeroRolloID",numeroRolloID,"EQ"));
		Map<String,String> mOrd =  new HashMap<String, String>();
		ListRollos= criteriaGeneralList(Params, mOrd);
		return ListRollos;
		
		
	}

}
