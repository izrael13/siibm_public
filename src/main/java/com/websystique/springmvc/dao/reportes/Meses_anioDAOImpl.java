package com.websystique.springmvc.dao.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.Meses_anio;
import com.websystique.springmvc.model.ParamsGeneral;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@SuppressWarnings("all")
@Repository("Meses_anioDAO")
public class Meses_anioDAOImpl extends AbstractDao<Integer, Meses_anio> implements Meses_anioDAO{

	@Override
	public List<Meses_anio> findallRegistros() {
		// TODO Auto-generated method stub
		
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		//mRes.put("anio", "2018");
		Params.add(new ParamsGeneral(1,"anio","2018","GTE"));
		mOrd.put("1", "anio");
		mOrd.put("2", "mes");
		
		List<Meses_anio> mesesanio = criteriaGeneralList(Params, mOrd);
		return mesesanio;
	}
	
}
