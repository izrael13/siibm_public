package com.websystique.springmvc.dao.reportes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Amortiza_herramentales;

@Repository("amortiza_herramentalesDAO")
public class Amortiza_herramentalesDAOImpl extends AbstractDao<Integer,Amortiza_herramentales>implements Amortiza_herramentalesDAO{

	@Override
	public List<Amortiza_herramentales> findAmortHerram(Integer select, String herramental) {
		// FIXME Auto-generated method stub
		List<Amortiza_herramentales> result = null;
		Map<String,Integer> mResInt =  new HashMap<String, Integer>();
		Map<String,String> mRes =  new HashMap<String, String>();
		if(select == 2)
			mRes.put("amortizado", "AMORTIZADO");
		else
		{
			if(select == 1)
				mRes.put("amortizado", "NO AMORTIZADO");
		}
		
		if(!herramental.trim().equals(""))
			mRes.put("herramental", herramental);		
		
		Map<String,String> mOrd =  new HashMap<String, String>();
		mOrd.put("1", "clientes");
		result = criteriaQueryEqStrInt(mRes,mResInt,mOrd);
		
		return result;
	}

}
