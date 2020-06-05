package com.websystique.springmvc.dao.produccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.produccion.Validacion_ped_rollo;


@Repository("Validacion_ped_rolloDAO")
public class Validacion_ped_rolloDAOImpl extends AbstractDao<Integer, Validacion_ped_rollo> implements Validacion_ped_rolloDAO{

	
	
	@Override
	public void Save(Validacion_ped_rollo prog) {
		getSession().save(prog);
		
	}

	@Override
	public List<Validacion_ped_rollo>ListValid(String prog_corru) {
		List<Validacion_ped_rollo> valid = new ArrayList<Validacion_ped_rollo>();
		List<ParamsGeneral> Params= new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"progCorru_ID",prog_corru,"EQ"));
		Map<String,String> mOrd =  new HashMap<String, String>();
		valid= criteriaGeneralList(Params, mOrd);
		return valid;
	}

	@Override
	public Validacion_ped_rollo BuscarxPedRollo(String progCorru_ID, String numerorolloid) {
		List<ParamsGeneral> Params= new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"progCorru_ID",progCorru_ID,"EQ"));
		Params.add(new ParamsGeneral(1,"numerorolloid",numerorolloid,"EQ"));
		
		return (Validacion_ped_rollo) criteriaGeneralObj(Params); 
	}


}
