package com.websystique.springmvc.dao.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.reportes.Amortiza_herramentales;

@Repository("amortiza_herramentalesDAO")
public class Amortiza_herramentalesDAOImpl extends AbstractDao<Integer,Amortiza_herramentales>implements Amortiza_herramentalesDAO{

	@Override
	public List<Amortiza_herramentales> findAmortHerram(Integer select, String herramental) {
		// FIXME Auto-generated method stub
		List<Amortiza_herramentales> result = null;
		/*Map<String,Integer> mResInt =  new HashMap<String, Integer>();
		Map<String,String> mRes =  new HashMap<String, String>();*/
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		if(select == 2)//mRes.put("amortizado", "AMORTIZADO");
			Params.add(new ParamsGeneral(1,"amortizado","AMORTIZADO","EQ"));
		else
		{
			if(select == 1)//mRes.put("amortizado", "NO AMORTIZADO");
				Params.add(new ParamsGeneral(1,"amortizado","NO AMORTIZADO","EQ"));
		}
		
		if(!herramental.trim().equals(""))//mRes.put("herramental", herramental);
			Params.add(new ParamsGeneral(1,"herramental",herramental,"EQ"));		
		
		Map<String,String> mOrd =  new HashMap<String, String>();
		mOrd.put("1", "clientes");
		
		result = criteriaGeneralList(Params,mOrd);
		
		return result;
	}

}
