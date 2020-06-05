package com.websystique.springmvc.dao.produccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.produccion.OS_ProgCorrugado;

@Repository("OS_ProgCorrugadoDAO")
public class OS_ProgCorrugadoDAOImpl extends AbstractDao<Integer, OS_ProgCorrugado> implements OS_ProgCorrugadoDAO{

	@Override
	public List<OS_ProgCorrugado> OS_ProgCorrugado(String Programa) {
		
		List<OS_ProgCorrugado> ped = new ArrayList<OS_ProgCorrugado>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		Params.add(new ParamsGeneral(1,"Programa",Programa,"EQ"));
		ped= criteriaGeneralList(Params, mOrd);
		return ped;
		
	}
	
	
	
}
